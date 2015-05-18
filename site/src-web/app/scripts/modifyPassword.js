require(['jquery','jquery.validation','bootstrap'],function($,jquery_validation){
    checkNewPassword();
    $("#changePwd-btn").click(function(){
        $("#modifyInfo").text("");
        if(!checkNewPassword().form()) return;
        $.ajax({
             url:"/modifypasswd",
             data:{securephone:$("#securephone").val(),password:$("#password").val()},
             success:function(data){
                 if(data.success){
                   $("#modal-pwdSuccess").modal('show');
                   //2014/03/26修改
                   //setTimeout(function(){
                     // autoToIndex();
                   //}, 2000);
                    changeTime();
                 }else{
                     $("#modifyInfo").html('<span style="color:red; margin-left:24%;">密码更新失败!</span>');
                 }
             }
         });
    });
});

  var waitTime=3;

  function changeTime() {
    var p=document.getElementById("time");
    waitTime--;
    p.innerHTML="(" + waitTime + ")";
    if(waitTime==0){
      autoToIndex();
    }
    else{
      setTimeout("change()",1000);
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
