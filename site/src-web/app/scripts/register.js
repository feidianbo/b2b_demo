require(['jquery','bootstrap'],function($){
    //注册
    $("#register-btn").click(function(){
    			      CheckUser();
                CheckPassword();
                CheckConPwd();
                CheckCertificationCode();
                CheckAgreement();
                if(CheckUser()=="valid" && CheckPassword()=="valid" && CheckConPwd()=="valid" && CheckCertificationCode()=="valid"&&CheckAgreement()=="valid"){
                   $.ajax({
                    url:"/register",
                    data:{securephone:$("#securephone").val().replace(/\s+/g,""),password:$("#password").val().replace(/\s+/g,""),smsCode:$("#smsCode").val().replace(/\s+/g,"")},
                    success:function(data)
                    {
                      if(data.success){
                          window.location.href="/account/getMyAccount";
                      } else {
                          $.each(data.errors, function(index, error){
                              $("#code").text(error.defaultMessage).css("color","red");
                          });
                      }
                    }
                  }); //ajax请求结束
                }  //验证通过
         }); //注册按钮单击事件结束

        //手机号验证
            function CheckUser()
            {
                var phoneResult;
                var securephone = $("#securephone").val().replace(/\s+/g,"");
                var partten = /^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
                if(partten.test(securephone)){
                $.ajax({
                    url:"/userCheck",
                    data:{securephone:securephone},
                    success:function(data){
                    if(data.success){
                        $("#gradeInfo").css("color","#1E90FF").text("√");
                        phoneResult="valid";
                    } else{
                        $.each(data.errors, function(index, error){
                            $("#gradeInfo").html(error.defaultMessage+",可<a href='/login?securephone="+securephone+"'>直接登录</a>").css("color","red");
                            phoneResult="invalid";
                        });
                      }
                    }
                    }); //ajax请求结束
                    phoneResult="valid";
                }
                else{
                    $("#gradeInfo").css("color","red").html("请输入正确的手机号码!");
                    $("#phone").css("display","none");
                    phoneResult="invalid";
                }
                return phoneResult;
            }
        //密码验证
            function CheckPassword()
            {
                var passwordResult;
                var pwd=$("#password").val();
                var partten=/[a-zA-Z0-9][6,16]/;
                if(pwd.length<6||pwd.length>16){
                    $("#pass").text("密码长度为6-16个字符!");
                    passwordResult="invalid";
                } else if(pwd.length>=6 && pwd.length<=16 && pwd.indexOf(" ")!=-1){
                    $("#pass").text("密码不能有空格!").css("color","red");
                    passwordResult="invalid";
                } else{
                    $("#pass").css("color","#1E90FF").text("√");
                    passwordResult="valid";
                }
                return passwordResult;
            }
        //确认密码
            function CheckConPwd()
            {
                var confirmPassResult;
                var pwd=$("#password").val();
                var repeatPass=$("#confirm_password").val();
                if(repeatPass.length<=0){
                    $("#pwd").text("请输入确认密码!");
                    confirmPassResult="invalid";
                } else if(pwd!=repeatPass){
                    $("#pwd").text("两次输入的密码不一致!").css("color","red");
                    confirmPassResult="invalid";
                } else{
                    $("#pwd").css("color","#1E90FF").text("√");
                    confirmPassResult="valid";
                }
                return confirmPassResult;
            }
        //验证码校验
            function CheckCertificationCode(){
                var validateCodeResult;
                var certificationCode=$("#smsCode").val().replace(/\s+/g,"");
                if(certificationCode==""){
                    $("#code").text("请输入验证码!").css("color","red");
                    validateCodeResult="invalid";
                } else{
                    $("#code").text("");
                    validateCodeResult="valid";
                }
                return validateCodeResult;
            }
        ////协议条款校验
            function CheckAgreement(){
                var itemResult="";
                if(document.getElementById("agreementChk").checked == true){
                  $("#itemInfo").text("");
                  itemResult="valid";
                } else{
                   $("#itemInfo").text("请仔细阅读并同意条款!").css("color","red");
                   itemResult="invalid";
                }
              return itemResult;
            }

            $("#agreementChk").click(function(){
              if(document.getElementById("agreementChk").checked == true){
                $("#register-btn").removeAttr("disabled");
              } else{
                $("#register-btn").attr("disabled","disabled");
              }
            })

            var wait = 60;
            function sendSmsCode(btn) {
                if(wait == 0){
                    btn.val("获取验证码");
                    btn.removeAttr("disabled");
                    wait = 60;
                }
                else{
                    var securephone = $("#securephone").val().replace(/\s+/g,"");
                    if(securephone==""){
                        $("#code").text("请先输入手机号!").css("color","red").css("font-size","14px");
                    }
                    else if(CheckUser()=="valid"){
                      $("#gradleInfo").text("");
                      $("#code").text("");
                      $.ajax({
                        url:"/sendSmsCode",
                        data:{securephone:$("#securephone").val().replace(/\s+/g,"")},
                        success:function(data){
                             if(data.success){
                               timeLoop(btn, wait);
                             }
                            else{
                                $.each(data.errors, function(index, error){
                                  $("#code").text(error.defaultMessage).css("color","red");
                                });
                            }
                        }
                });//ajax请求结束
                }
            }
         }

          function timeLoop(btn, wait){
              btn.attr({"disabled":"disabled"});
              setTimeout(function () {
                  btn.val("重新发送("+ wait +")");
                  wait--;
                  if(wait < 0){
                      btn.val("获取验证码");
                      btn.removeAttr("disabled");
                      return;
                  }
                  timeLoop(btn, wait);
              },1000)
          }

        //动态获取验证码
            $("#sendCode").click(function(){
                sendSmsCode($(this));
            }); //获取验证码按钮单击事件结束

            $("#securephone").focus(function(){
              var securephone = $("#securephone").val().replace(/\s+/g,"");
              if(CheckUser()=="invalid"){
                  if(!securephone){
                      $("#phone").css("display","block");
                      $("#gradeInfo").text("");
                      $("#code").text();
                  }
              }
            });
            $("#password").focus(function(){
              if(CheckPassword()=="invalid"){
                  if($("#password").val().replace(/\s+/g,"").length<1){
                      $("#passRule").css("display","block");
                      $("#pass").text("");
                  }
               }
            });

            $("#confirm_password").focus(function(){
              if(CheckConPwd()=="invalid"){
               $("#repeatPass").css("display","block");
               $("#pwd").text("");
              }
             });
            $("#smsCode").focus(function(){
               if(CheckCertificationCode()=="invalid"){
                $("#code").text("•请输入验证码").css("color","gray");
               }
             });
            $("#securephone").blur(function(){
               $("#phone").css("display","none");
               CheckUser();
             });
            $("#password").blur(function(){
               $("#passRule").css("display","none");
               if(CheckPassword()=="invalid"){
                $("#pass").css("display","block");
               }
             });
            $("#confirm_password").blur(function(){
               $("#repeatPass").css("display","none");
               CheckConPwd();
             });
            $("#smsCode").blur(function(){
              if(CheckCertificationCode()=="invalid"){
                $("#code").text("请输入验证码!");
              }
                $("#smsphonecode").css("display","none");
             });

            //隐私条款
            $("#show-private-policy").click(function(){
              $("#modal-secretDialog").modal('show');
            })

            $("#show-use-agreement").click(function(){
              $("#modal-signDialog").modal('show');
            })
});
