layui.use(['form', 'layedit', 'laydate', 'jquery'], function(){
  var form = layui.form, layer = layui.layer, layedit = layui.layedit
  ,laydate = layui.laydate ,$ = layui.jquery, isAll = false, isCustom = false, isColumn = false;
  
  //日期
  laydate.render({
    elem: '#date',
    type: 'datetime'
  });
  
  form.on('select(dbType)', function(data){
	 if (data.value == '1') {
		 $("#port").val(1433);
	 } else if (data.value == '2') {
		 $("#port").val(3306);
	 } else if (data.value == '3') {
		 $("#port").val(1521);
	 }
  });
  
  form.on('radio(type)', function(data){
	  var inputName = data.value;
	  $(".tab-input").hide();
	  if (inputName == "channel") {
		  $("input[name=newchnlId],input[name=oldchnlId]").closest(".tab-input").show();
	  } else if (inputName == "channels") {
		  $("input[name=oldchnlIds]").closest(".tab-input").slideDown();
	  } else if (inputName == "document") {
		  $("input[name=newchnlId],input[name=docId]").closest(".tab-input").show();
	  }
  });
 
  //自定义验证规则
  form.verify({
	  domain: function(value) {
	      if (isCustom && !/^http(s)?:\/\/(.*?)/.test(value)){
	        return '域名格式不对';
	      }
      },
      ip: function(value) {
    	  if(isCustom && value.length == 0) {
    		  return '主机地址不能为空';
    	  }
      },
      port: function(value) {
    	  if(isCustom && value.length == 0) {
    		  return '端口不能为空';
    	  }
      },
      dbName: function(value) {
    	  if(isCustom && value.length == 0) {
    		  return '数据库名称不能为空';
    	  }
      },
      dbUserName: function(value) {
    	  if(isCustom && value.length == 0) {
    		  return '数据库用户名不能为空';
    	  }
      },
      dbPassword: function(value) {
    	  if(isCustom && value.length == 0) {
    		  return '数据库密码不能为空';
    	  }
      }
  });
  
  form.on('switch(switchChnl)', function(data){
	  	if (this.checked) {
	  		isAll = true;
	  		$("#chnlRef").slideDown();
	  	} else {
	  		isAll = false;
	  		$("#chnlRef").slideUp();
	  	}
  });
  
  //监听指定开关
  form.on('switch(switchTest)', function(data){
  	if (this.checked) {
  		isCustom = true;
  		$("#config").slideDown();
  	} else {
  		isCustom = false;
  		$("#config").slideUp();
  	}
  });
  
  form.on('switch(switchColumn)', function(data){
	  	if (this.checked) {
	  		isColumn = true;
	  		$("#column").slideDown();
	  	} else {
	  		isColumn = false;
	  		$("#column").slideUp();
	  	}
  });
  
  form.on('switch(switchSql)', function(data){
	  	if (this.checked) {
	  		$("#sql").slideDown();
	  	} else {
	  		$("#sql").slideUp();
	  	}
  });
  
  //监听提交
  form.on('submit(doSubmit)', function(data){
	saveFormCookies();
	  
  	var index = layer.load(1, {shade: [0.8, '#393D49']});
    $.ajax({
    	type: 'post',
    	url: '/datachange/change',
    	data: data.field,
    	success: function(data) {
    		layer.close(index);
    		data=data.replace(/失败/g,"失败<br/>");
    		layer.alert(data, {
   		      title: '迁移结果',
   		      area:['500px','500px']
   		    });
    	},
    	error: function(e) {
    		alert(e);
    		layer.close(index);
    	}
    });
    return false;
  });
  
  function saveFormCookies() {
	  $.cookie("domain", $("input[name=domain]").val());
	  //$.cookie("newDomain", $("input[name=newDomain]").val());
	  $.cookie("dbType", $("select[name=dbType]").val());
	  $.cookie("ip", $("input[name=ip]").val());
	  $.cookie("port", $("input[name=port]").val());
	  $.cookie("dbName", $("input[name=dbName]").val());
	  $.cookie("dbUserName", $("input[name=dbUserName]").val());
	  $.cookie("dbPassword", $("input[name=dbPassword]").val());
	  $.cookie("docSql", $("input[name=docSql]").val());
	  $.cookie("dlinkSql", $("input[name=dlinkSql]").val());
  };
  
  function removeFormCookies() {
	  var cookieArr = ["domain", "newDomain", "dbType", "ip", "port", "dbName", "dbUserName", "dbPassword", "docSql", "dlinkSql"];
	  $.each(cookieArr, function(index, value) {
		  delCookie(value);
	  })
  };
  
  function initFromCookies() {
	  var domain = $.cookie("domain");
	  //var newDomain = $.cookie("newDomain");
	  var dbType = $.cookie("dbType");
	  var ip = $.cookie("ip");
	  var port = $.cookie("port");
	  var dbName = $.cookie("dbName");
	  var dbUserName = $.cookie("dbUserName");
	  var dbPassword = $.cookie("dbPassword");
	  var docSql = $.cookie("docSql");
	  var dlinkSql = $.cookie("dlinkSql");
	  if (isNotEmpty(domain)) {
		  $("input[name=domain]").val(domain);
	  }
//	  if (isNotEmpty(newDomain)) {
//		  $("input[name=newDomain]").val(newDomain);
//	  } 
	  if (isNotEmpty(dbType)) {
		  $("select[name=dbType]").val(dbType);
	  } 
	  if (isNotEmpty(ip)) {
		  $("input[name=ip]").val(ip);
	  } 
	  if (isNotEmpty(port)) {
		  $("input[name=port]").val(port);
	  } 
	  if (isNotEmpty(dbName)) {
		  $("input[name=dbName]").val(dbName);
	  } 
	  if (isNotEmpty(dbUserName)) {
		  $("input[name=dbUserName]").val(dbUserName);
	  }
	  if (isNotEmpty(dbPassword)) {
		  $("input[name=dbPassword]").val(dbPassword);
	  }
	  if (isNotEmpty(docSql)) {
		  $("input[name=docSql]").val(docSql);
	  }
	  if (isNotEmpty(dlinkSql)) {
		  $("input[name=dlinkSql]").val(dlinkSql);
	  }
  }
  
  function isNotEmpty(val) {
	  if (val == "null" || val == "" || typeof(val)=="undefined") {
		  return false; 
	  } else {
		  return true;
	  }
  }
  
  function delCookie(name) {
	  var exp = new Date();
	  exp.setTime(exp.getTime() - 1);
	  var cval=$.cookie(name);
	  if(cval!=null)
	  document.cookie= name + "="+cval+";expires="+exp.toGMTString();
  }
  
  $(function() {
	  initFromCookies();
	  
	  $("#clear_cookie").on("click", function() {
		  removeFormCookies();
		  window.location.reload();
	  });
  });
});