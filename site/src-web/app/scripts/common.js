define(['jquery'],function($){
  $(document.body).on('click', '#modal-login #login-btn', function(){
    var ajax$setting=$("#modal-login").data('ajax$setting');
    var continue401= ajax$setting? ajax$setting['continue401']:null;
    dialog_login();
  });

  $(".dropdown-menu table td").each(function(){
    $(this).hover(function(){
      $(this).addClass('selectStyle');
    },function(){
      $(this).removeClass('selectStyle');
    })
  });

  $(".dropdown-menu table td a").each(function(){
    if($('#pageSize').text()==$(this).text()){
      $("#pageSize").addClass('fontStyle');
      $(this).addClass('fontStyle');
      $(".dropdown-menu table td a:not(this)").removeClass('class');
    }
  })

  function dialog_login(){
    if(!checkPhone()) return;
    if(!checkPassword()) return;
    if(checkPhone() && checkPassword()){
      $.ajax({
        url: "/login",
        data: {securephone: $("#login-securephone").val(), password: $("#login-password").val()},
        success: function (data) {
          if (data.success) {
            $("#modal-login").modal('hide');
            if($("#gotoUrl").val() != ""){
              location.href = $("#gotoUrl").val();
            }else{
              window.location.reload(true);
            }
            //if(continue401){
            //  continue401();
            //}else{
            //  window.location.reload(true);
            //}
          }
          else {
            $.each(data.errors, function (index, error) {
              $('#loginInfo').css("color", "red").text(error.defaultMessage);
            });
          }
        }
      });
    }
  }

  $("#modal-login").keyup(function(event){
    if(event.keyCode == 13){
      dialog_login();
    }
  })

  //XX直通车港口比对
  $.ajax({
    url: "/getCoal",
    type: "POST",
    success: function (data) {
    $.each(data.powerCoalList,function(n,value){
      //$(".frist_li >a").each(function(){
      //  if(value.deliveryregion == $(this).text()){
      //    $(this).css("color",'red');
      //  }
      //})
      $(".ulSub >li >a").each(function(){
        if(value.deliveryplace == $(this).text()){
          $(this).css("color",'red');
        }
      })
    });

    $.each(data.AnthrAciteCoalList,function(n,value){
      $(".ulSub1 >li >a").each(function(){
        if(value.deliveryplace == $(this).text()){
          $(this).css("color",'red');
        }
      })
    });
  }
});

  //得到光标时隐藏错误信息
  $("#login-securephone").focus(function(){
    $("#loginInfo").text("");
  });

  $("#login-password").focus(function(){
    $("#pwdErrorInfo").text("");
  });

//失去焦点的时候判断用户名和密码
  $("#login-securephone").blur(function(){
    checkPhone();
  });

  $("#login-password").blur(function(){
    checkPassword();
  });

  var login=function(ajax$setting){
    $("#loginInfo").text("");
    var $modal=$("#modal-login");
    $modal.data("ajax$setting", ajax$setting);
    $modal.modal('show');
  };

  /** server ajax error msg */
  var showServerError = function(status,message) {
    var errorMsg = {
      401: "请重新登录...",
      403: "您没有权限访问该网页...",
      404: "您访问的网页不存在...",
      400: "服务器出错，请稍后重试...",
      500: "服务器出错，请稍后重试...",
      409:  message
    };
    $("#server-error-msg").text(errorMsg[status]);
    $('#modal-server-error').modal(true);
  };

  return {login:login, showServerError:showServerError};
});

//手机号验证
function checkPhone()
{
  var phone = $("#login-securephone").val().replace(/\s+/g,"");
  var reg = /^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
  if(phone == ""){
    $("#loginInfo").css("color","red").text("请输入手机号码!");
    return false;}
  else if(!reg.test(phone) || phone.length != 11){
    $("#loginInfo").css("color","red").text("请输入正确的手机号码!");
    return false;}
  else{
    $("#loginInfo").text("");
    return true;}
}

//密码验证
function checkPassword()
{
  var pwd=$("#login-password").val();
  if(pwd == ""){
    $("#pwdErrorInfo").css("color","red").text("请输入密码!");
    return false;}
  else if(pwd.indexOf(" ") != -1){
    $("#pwdErrorInfo").text("密码不能有空格!");
    return false;}
  else if(pwd.length < 6 || pwd.length > 16){
    $("#pwdErrorInfo").text("密码长度为6-16个字符!").css("color","red");
    return false;}
  else{
    $("#pwdErrorInfo").text("");
    return true;}
}










