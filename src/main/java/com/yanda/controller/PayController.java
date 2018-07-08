package com.yanda.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.yanda.entity.JsonResult;
import com.yanda.entity.PageResult;
import com.yanda.entity.RequestParam;
import com.yanda.entity.generated.PayInfo;
import com.yanda.entity.generated.ProductInfo;
import com.yanda.exception.DOPException;
import com.yanda.service.PayService;
import com.yanda.service.ProductService;
import com.yanda.util.StringUtil;

@RestController
@RequestMapping(value = "/pay")
public class PayController extends BaseController {
	
	/**
	 * 初始化微信支付
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		wxPayConfig = new MyConfig();
		WXPay = new WXPay(wxPayConfig);
	}

	@Autowired
	private PayService payService;
	
	private WXPay WXPay;
	
	private WXPayConfig wxPayConfig;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/recordList", method = RequestMethod.GET)
	public JsonResult getPayRecordsByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId)) 
			return result(-1, "userId不能为空");
		String pageNum = getValue(request, "pageNum", "1");
		String pageSize = getValue(request, "pageSize", "10");
		PageResult<PayInfo> result = payService.list(Integer.valueOf(pageNum), Integer.valueOf(pageSize), Integer.valueOf(userId));
		return result(200, "success", result);
	}
	
	@RequestMapping(value = "/addRecord", method = RequestMethod.POST)
	public JsonResult addRecordByUserId(HttpServletRequest request) {
		String userId = getNotEmptyValue(request, "userId");
		if(StringUtil.isEmpty(userId))
			return result(-1, "userId 不能为空");
		String payStatus = getNotEmptyValue(request, "payStatus");
		if(StringUtil.isEmpty(payStatus)) 
			return result(-1, "支付状态不能为空");
		String payAmout = getValue(request, "payAmount", "0.01");
		String payMsg = getValue(request, "payMsg", "");
		String tradeNo = getValue(request, "tradeNo", "");
		PayInfo payInfo = new PayInfo();
		payInfo.setUserId(Integer.valueOf(userId));
		payInfo.setPayAmount(Float.valueOf(payAmout));
		payInfo.setPayStatus(Integer.valueOf(payStatus));
		payInfo.setPayTime(new Date());
		payInfo.setTradeNo(tradeNo);
		payInfo.setPayMsg(payMsg);
		try {
			payService.save(payInfo);
			return result(200, "success");
		} catch (DOPException e) {
			e.printStackTrace();
			return result(-1, "保存失败");
		}
	}
	
	/*@PostMapping(value = "/getPaySign")
	public JsonResult getPaySign(HttpServletRequest request) {
		String str = getNotEmptyValue(request, "str");
		if (StringUtil.isEmpty(str)) {
			return result(-1, "传入字符串为空");
		}
		str = HtmlUtils.htmlUnescape(str);
		String md5Str = payService.getPaySign(str);
		return result(200, "success", md5Str);
	}*/
	
	@GetMapping(value= "/notifyUrl") 
	public void getNotify(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		List<RequestParam> params = new ArrayList<RequestParam>();
		for (String key : parameterMap.keySet()) {
			RequestParam param = new RequestParam();
			param.setName(key);
			param.setValue(request.getParameter(key));
			params.add(param);
		}
		LOG.info("微信支付完成，回调参数："+JSON.toJSONString(params));
	}
	
	/**
	 * 统一下单
	 * @param request
	 * @openid 微信用户id
	 * @out_trade_no 商户订单号
	 * @total_fee 标价金额
	 * @body 商品描述
	 * @return 
	 * @throws Exception
	 */
	@PostMapping(value = "/getOrder")
	public Object getOrder(HttpServletRequest request) throws Exception {
		String openId = request.getParameter("openid");
		if (StringUtil.isEmpty(openId)) {
			return result(-1, "openid为空");
		}
		String outTradeNo = request.getParameter("outTradeNo");
		if (StringUtil.isEmpty(outTradeNo)) {
			return result(-1, "商户订单号为空");
		}
		String totalFee = request.getParameter("totalFee");
		if (StringUtil.isEmpty(totalFee)) {
			return result(-1, "总费用为空");
		}
		String productId = request.getParameter("productId");
		if (StringUtil.isEmpty(productId)) {
			return result(-1, "购买的产品编号为空");
		}
		String body = request.getParameter("body");
		if (StringUtil.isEmpty(body)) {
			return result(-1, "商品描述为空");
		}
		
		ProductInfo product = productService.selectById(Integer.valueOf(productId));
		
		Map<String, String> reqData = new HashMap<>();
		reqData.put("openid", openId);
		reqData.put("out_trade_no", outTradeNo);
		reqData.put("total_fee", String.valueOf(product.getCurrentPrice().multiply(new BigDecimal(100)).intValue()));  //正式上线后已此价格为主 product.getCurrentPrice();
		reqData.put("body", body);
		reqData.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
		reqData.put("trade_type", "JSAPI");
		return result(200, "", WXPay.unifiedOrder(reqData));
	}
	
	/**
	 * 小程序支付-生成PaySign
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getPaySign")
	public Object getPaySign(HttpServletRequest request) throws Exception {
		String prepayId = request.getParameter("prepayId");
		if (StringUtil.isEmpty(prepayId)) {
			return result(-1, "预支付交易会话标识为空");
		}
		Map<String, String> reqData = new HashMap<>();
		String timeStamp = String.valueOf(new Date().getTime()/1000);
		String nonceStr = StringUtil.generateRandomCode(32);
		reqData.put("appId", this.wxPayConfig.getAppID());
		reqData.put("nonceStr", nonceStr);
		reqData.put("package", "prepay_id="+prepayId);
		reqData.put("signType", "MD5");
		reqData.put("timeStamp", timeStamp);
		String paySign = WXPayUtil.generateSignature(reqData, this.wxPayConfig.getKey(), WXPayConstants.SignType.MD5);
		
		reqData.clear();
		reqData.put("paySign", paySign);
		reqData.put("timeStamp", timeStamp);
		reqData.put("nonceStr", nonceStr);
		return result(200, "", reqData);
	}
	
	/**
	 * 统一下单
	 * @param request
	 * @openid 微信用户id
	 * @out_trade_no 商户订单号
	 * @total_fee 标价金额
	 * @body 商品描述
	 * @return 
	 * @throws Exception
	 */
	@PostMapping(value = "/refund")
	public Object refund(HttpServletRequest request) throws Exception {
		String outTradeNo = request.getParameter("out_trade_no");
		if (StringUtil.isEmpty(outTradeNo)) {
			return result(-1, "商户订单号为空");
		}
		String totalFee = request.getParameter("total_fee");
		if (StringUtil.isEmpty(totalFee)) {
			return result(-1, "总费用为空");
		}
		
		Map<String, String> reqData = new HashMap<>();
		reqData.put("out_trade_no", outTradeNo);
		reqData.put("out_refund_no", outTradeNo);
		reqData.put("total_fee", "1");
		reqData.put("refund_fee", "1");
		return result(200, "", WXPay.refund(reqData));
	}

}
