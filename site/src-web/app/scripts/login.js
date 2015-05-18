require(['jquery'],function($){
    $("#create-btn").click(function(){
        login();
    });

    function login(){
      if(!checkPhoneLogin())return;
      if(!checkPasswordLogin())return;
      if(checkPhoneLogin() && checkPasswordLogin()){
        $.ajax({
          url:"/login",
          data:{securephone:$("#securephone").val(),password:$("#password").val()},
          success:function(data){
            if(data.success){
              $("#loginInfo").text("");
              if($("#t").val() != "" && $("#t").val() != null ){
                location.href =$("#t").val();
              }else{
                location.href ="/";
              }
            }
            else{
              $.each(data.errors, function(index, error){
                $('#loginInfo').css("color","red").text(error.defaultMessage);
              });
            }
          }
        });
      }
    }

    //失去焦点的时候判断用户名和密码
    $("#securephone").blur(function(){
      checkPhoneLogin();
    });

    $("#password").blur(function(){
      checkPasswordLogin();
    });

    //得到光标时隐藏错误信息
    $("#securephone").focus(function(){
      $("#loginInfo").text("");
    });

    $("#password").focus(function(){
      $("#pwdErrorInfo").text("");
    });

    $(document).keyup(function(event){
      if(event.keyCode == 13){
        login();
      }
    })
});

    //手机号验证
    function checkPhoneLogin()
    {
      var phone = $("#securephone").val().replace(/\s+/g,"");
      var reg = /^0?(13[0-9]|17[0-9]|15[0-9]|18[0-9]|14[57])[0-9]{8}$/;
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
    function checkPasswordLogin()
    {
      var pwd=$("#password").val();
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









