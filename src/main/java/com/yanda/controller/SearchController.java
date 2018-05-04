package com.yanda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.generated.KeywordInfo;
import com.yanda.entity.generated.UserSearchInfo;
import com.yanda.service.KeywordService;
import com.yanda.service.UserSearchService;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {
	
    @Autowired
    private KeywordService keywordsService;
    @Autowired
    private UserSearchService searchHistoryService;

    /**
     * 搜索页面信息
     *
     * 如果用户已登录，则给出用户历史搜索记录。
     *
     * @param userId 用户ID
     * @return 搜索页面信息
     */
    @GetMapping("index")
    public Object index(HttpServletRequest request) {
    	String userId = request.getParameter("userId");
        //取出输入框默认的关键词
        KeywordInfo defaultKeyword = keywordsService.queryDefault();
        //取出热闹关键词
        List<KeywordInfo> hotKeywordList = keywordsService.queryHots();

        List<UserSearchInfo> historyList = null;
        if(userId != null) {
            //取出用户历史关键字
            historyList = searchHistoryService.queryByUid(Integer.valueOf(userId));
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("defaultKeyword", defaultKeyword);
        data.put("historyKeywordList", historyList);
        data.put("hotKeywordList", hotKeywordList);
        return result(200, "", data);
    }

    /**
     * 关键字提醒
     *
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param keyword 关键字
     * @return 合适的关键字
     */
    @GetMapping("helper")
    public Object helper(String keyword) {
        if(keyword == null){
            return result(200, "");
        }

        Integer page = 1;
        Integer size = 10;
        List<KeywordInfo> keywordsList = keywordsService.queryByKeyword(keyword, page, size);
        String[] keys = new String[keywordsList.size()];
        int index = 0;
        for (KeywordInfo key : keywordsList) {
           keys[index++] = key.getKeyword();
        }
        return result(200, "", keys);
    }

    /**
     * 关键字清理
     *
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param userId 用户ID
     * @return 清理是否成功
     */
    @PostMapping("clearhistory")
    public Object clearhistory(Integer userId) {
        if(userId == null){
            return result(-1, "用户未登录");
        }

        searchHistoryService.deleteByUid(userId);
        return result(200, "");
    }
}
