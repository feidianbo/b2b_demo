define(['jquery', 'big', 'angular', 'angular-resource', 'angular-route', 'angular-cookies', 'angular-sanitize','angular-ui-select', 'angular-file-upload', 'angular-bootstrap', 'angular-bootstrap-datetimepicker', 'angular-pagedown', 'jquery-form'], function($, Big, angular){

  $.ajaxSetup({dataType:'json', type:"POST",
    error:function(jqXHR, textStatus, exception){
      var XHRstatus = jqXHR.status;
      var errorStatus = [400, 401, 403, 404, 409,500];
      var message  =  jqXHR.responseText;
      if ($.inArray(XHRstatus, errorStatus) >=0){
        showServerError(XHRstatus,message);
      }
    }
  });

  /** show server ajax error msg */
  var showServerError = function(status,messge) {
    var errorMsg = {
      401: "请重新登录...",
      403: "您没有权限访问该网页...",
      404: "您访问的网页不存在...",
      400: "服务器出错，请稍后重试...",
      500: "服务器出错，请稍后重试...",
      409: messge
    };
    $("#server-error-msg").text(errorMsg[status]);
    $('#modal-server-error').modal(true);
  };

  window.service=function(settings){
    //settings.data={json:angular.toJson(settings.data)};
    return $.ajax(settings);
  };
  window.url=function(path, params){
    return URI(path).query(params).toString();
  };
  window.goto=function(url,params){
    var url=URI(url);
    if(params)
      url=url.query(params);
    window.location.href='/#'+ url.toString();
  };
  window.refresh=function(){
    window.location.reload(true);
  };
  window.Big=Big;

  //初始化权限
  window.initAuth=function(){
    //var operatecodeList = window.operatecodeList;
    //if (operatecodeList != null) {
    //  $('button,a').each(function () {
    //    var id = $(this).attr('id');
    //    var objName = $(this).attr('name');
    //    if (id != null) {
    //      var isExist = false;
    //      for (var j = 0; j < operatecodeList.length; j++) {
    //        if (id == operatecodeList[j]) {
    //          isExist = true;
    //          break;
    //        }
    //      }
    //      if (!isExist) {
    //        $("#" + id).hide();
    //      }
    //    }
    //
    //    if (objName != null) {
    //      var isExist = false;
    //      for (var k = 0; k < operatecodeList.length; k++) {
    //        if (objName == operatecodeList[k]) {
    //          isExist = true;
    //          break;
    //        }
    //      }
    //      if (!isExist) {
    //        $('a[name="'+objName+'"]').hide();
    //      }
    //    }
    //
    //  });
    //}
  };

  return module=angular.module('kitt', ['ngCookies', 'ngResource', 'ngSanitize', 'ngRoute', 'ui.bootstrap', 'ui.bootstrap.datetimepicker', 'angularFileUpload', 'ui.pagedown'])
    .factory('$params', function($route){
      return function(name, defaultValue){
        var val=$route.current.params[name];
        if(!val && defaultValue!=undefined)
          val=defaultValue;
        return val;
      }
    });
});
