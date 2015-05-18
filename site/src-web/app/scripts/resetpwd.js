require(['jquery','jquery.validation','bootstrap'],function($,jquery_validation){
  jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
  }, "请正确填写您的手机号码");

        check_resetForm();
        check_verifyAccountForm();
        checkNewPassword();

        $("#imgObj").click(function(){
            var imgSrc = $("#imgObj");
            var src = imgSrc.attr("src");
            imgSrc.attr("src",chgUrl(src));
        })

        function chgUrl(url){
            var timestamp = (new Date()).valueOf();
            var newUrl = url.substring(0,17);
            if((url.indexOf("&")>=0)){
                newUrl = url + "×tamp=" + timestamp;
            }else{
                newUrl = url + "?timestamp=" + timestamp;
            }
            return newUrl;
          }

        $("#securephone").focus(function(){
            $("#securephoneInfo").text("");
        });

        $("#vcode").focus(function(){
            $("#vcodeInfo").text("");
        });

        $("#code").blur(function(){
            $("#vcodeInfo").text("");
        });

        $("#changePwd-btn").click(function(){
          $("#modifyInfo").text("");
          if(!checkNewPassword().form()) return;
          $.ajax({
            url:"/modifypasswd",
            data:{securephone:$("#securephone").val(),password:$("#password").val()},
            success:function(data){
              if(data.success){
                $("#modal-pwdSuccess").modal('show');
                changeTime();
              }else{
                $("#modifyInfo").html('<span style="color:red; margin-left:24%;">密码更新失败!</span>');
              }
            }
          });
        });

        $("#reset-btn").click(function(){
            if(!check_resetForm().form()) return;
              $.ajax({
                   url:"/validphone",
                   data:{securephone:$("#securephone").val(),picCode:$("#vcode").val()},
                   success:function(data){
                      if(data.success){
                           $("#securephoneInfo").text("");
                           $("#reset_form").css('display','none');
                           $("#verifyAccount_form").css('display','block');
                           $("#pre_securephone").val($("#securephone").val());
                           change();
                           //location.href ="/verifyAccount?securephone="+$("#securephone").val();
                      }else{
                              $.each(data.errors, function(index, error){
                                    if(error.defaultMessage == "用户不存在"){
                                        $('#securephoneInfo').css("color","red").text(error.defaultMessage);
                                    }if(error.defaultMessage == "验证码错误"){
                                        $('#vcodeInfo').css("color","red").text(error.defaultMessage);
                                    }
                              });
                           }
                   }
               });
          });

        $("#checkCode").click(function(){
         $.ajax({
             url:"/resendValidcode",
             data:{securephone:$("#pre_securephone").val()},
             success:function(data){
                 if(!data.success){
                     $('#pre_vcodeInfo').css("color","red").text("发送验证码失败!");
                 }
             }
         })
        });

        $("#vCode-btn").click(function(){
            if(!check_verifyAccountForm().form()) return;
            $.ajax({
                 url:"/validcode",
                 data:{securephone:$("#pre_securephone").val(),code:$("#code").val()},
                 success:function(data){
                     if(data.success){
                         $("#securephoneInfo").html("");
                         $("#verifyAccount_form").hide();
                         $("#modifypassword_form").show();
                         //location.href ="/modifypassword?securephone="+$("#pre_securephone").val();

                     }
                     else{
                        $.each(data.errors, function(index, error){
                           $('#pre_vcodeInfo').css("color","red").text(error.defaultMessage);
                        });
                     }
                   }
             });
        });
});


  var wait=60;

  var waitTime=3;

  function change() {
         var p=document.getElementById("checkCode");
         wait--;
         p.value="重新发送(" + wait + ")";
         if(wait==0){
             p.disabled=false;
             p.value="获取验证码";
             wait =60;
         }
         else{
             setTimeout("change()",1000)
         }
     }

  function changeTime() {
  var p=document.getElementById("time");
  waitTime--;
  p.innerHTML="(" + waitTime + ")";
  if(waitTime==0){
    autoToIndex();
  }
  else{
    setTimeout("changeTime()",1000);
  }
}

  function autoToIndex(){
  $.ajax({
    url:"/login",
    data:{securephone:$("#securephone").val(),password:$("#password").val()},
    success:function(data){
      if(data.success){
        location.href ="/";
      }
    }
  });
}

  function check_resetForm(){
            jQuery.validator.addMethod("isLength", function(value, element) {
               return (value.length == 4 );
                }, "验证码的长度必须为4!");
            return  $("#reset_form").validate({
                onsubmit:false,
                onkeyup:false,
                focusInvalid:false,
                errorElement: 'span',
                errorClass: 'help-block',
                rules: {
                   securephone: {
                    required: true,
                    isMobile: true
                   },
               vcode: {
                    required: true,
                    isLength:true
                   }
                  },

               messages: {
                   securephone: {
                    required: "请输入手机号",
                    minlength: jQuery.validator.format("手机号不能小于{0}个字符")
                   },
                   vcode:{
                    required: "请输入验证码"
                   }
                  },

                highlight: function (element) {
                    $(element).closest('.form-control').addClass('has-error');
                },

                success: function (label) {
                    label.closest('.form-control').removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent().next());
                }
                });
 }

  function check_verifyAccountForm(){
         return  $("#verifyAccount_form").validate({
             onsubmit:false,
             onkeyup:false,
             focusInvalid:false,
             errorElement: 'span',
             errorClass: 'help-block',
             rules: {
                code: {
                    required: true
                }
             },
             messages: {
                code:{
                    required:"请输入验证码!"}
             },
             highlight: function (element) {
                 $(element).closest('.form-control').addClass('has-error');
             },
             success: function (label) {
                 label.closest('.form-control').removeClass('has-error');
                 label.remove();
             },
             errorPlacement: function (error, element) {
                 error.insertAfter(element.parent().next());
             }
     });
 }

  function checkNewPassword(){
  return  $("#modifypassword_form").validate({
    onsubmit:false,
    onkeyup:false,
    focusInvalid:false,
    errorElement: 'span',
    errorClass: 'help-block',
    rules: {
      password: {
        required: true,
        minlength: 6,
        maxlength:16
      },
      confrimPwd: {
        required: true,
        minlength: 6,
        maxlength:16,
        equalTo:"#password"
      }
    },
    messages: {
      password: {
        required: "请输入密码",
        minlength: jQuery.validator.format("密码不能小于{0}个字符"),
        maxlength:jQuery.validator.format("密码不能多于{0}个字符")
      },
      confrimPwd:{
        required: "请输入确认密码",
        minlength: jQuery.validator.format("确认密码不能小于{0}个字符"),
        maxlength:jQuery.validator.format("密码不能多于{0}个字符"),
        equalTo:"确认密码和密码不相同"
      }
    },

    highlight: function (element) {
      $(element).closest('.form-control').addClass('has-error');
    },

    success: function (label) {
      label.closest('.form-control').removeClass('has-error');
      label.remove();
    },
    errorPlacement: function (error, element) {
      error.insertAfter(element.parent());
    }
  });
}




