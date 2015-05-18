require(['jquery','bootstrap','jquery.validation'],function($) {
  check();

  $("#loadMenu3").click(function(){
    var sessionPhone = $("#sessionPhone").val();
    if(sessionPhone == null || sessionPhone == ""){
      $("#modal-login").modal('show');
    }else{
      $("#loadMenu1").css("background-color", "#ffffff").css("color", "#000000");
      $("#loadMenu2").css("background-color", "#ffffff").css("color", "#000000");
      $("#loadMenu3").css("background-color", "#317ee6").css("color", "#ffffff");
      $("#loanIntroduction").hide();
      $("#tradeExplain").hide();
      $("#wantFinancing").show();

      $.ajax({
        url:'/finance/getUserInfo',
        success:function(data){
          if(data.company != null) {
            $("#companyname").val(data.company.name);
            $("#address").val(data.company.address);
            $("#phone").val(data.phone);
          }
        }
      });
    }
  });

  $("#financingMenu3").click(function(){
    var sessionPhone = $("#sessionPhone").val();
    if(sessionPhone == null || sessionPhone == ""){
      $("#modal-login").modal('show');
    }else{
      $("#financingMenu1").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingMenu2").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingMenu3").css("background-color", "#317ee6").css("color", "#ffffff");
      $("#financingIntroduction").hide();
      $("#tradeExplain").hide();
      $("#wantFinancing").show();

      $.ajax({
        url:'/finance/getUserInfo',
        success:function(data){
          if(data.company != null){
            $("#companyname").val(data.company.name);
            $("#address").val(data.company.address);
            $("#phone").val(data.phone);
          }
        }
      });
    }
  });

  $("#create-btn").click(function(){
    if(!check().form()) return;
    var type = $("#type").val();
    var companyname = $("#companyname").val();
    var address = $("#address").val();
    var businessarea = $("#businessarea").val();
    var contact = $("#contact").val();
    var phone = $("#phone").val();
    var amountnum = $("#amountnum").val()*10000;
    if(amountnum >100000000){
      $("#amountnumError").text("融资金额不超过1亿").css("color","red");
      return;
    }else{
      $("#amountnumError").text("")
    }
    $.ajax({
        url:"/finance/saveContact",
        data:{type:type,companyname:companyname,address:address,businessarea:businessarea,
        amountnum:amountnum,contact:contact,phone:phone},
        success:function(data){
          if(data == "success"){
            $("#modal-attendSuccess").modal('show');
            $("#closeBtn").click(function(){
              $("#modal-attendSuccess").modal('hide');
              if($("#type").val() == "煤易贷") {
                location.href = "/finance/loan";
              }else{
                location.href = "/finance/financing";
              }
            });
          }
        }
      });
  });

});

function changeLoadMenu(menuOrder) {
  if (menuOrder == 1) {
    $("#loadMenu1").css("background-color", "#317ee6").css("color", "#ffffff");
    $("#loadMenu2").css("background-color", "#ffffff").css("color", "#000000");
    $("#loadMenu3").css("background-color", "#ffffff").css("color", "#000000");
    $("#loanIntroduction").show();
    $("#tradeExplain").hide();
    $("#wantFinancing").hide();
  }

  if (menuOrder == 2) {
    $("#loadMenu1").css("background-color", "#ffffff").css("color", "#000000");
    $("#loadMenu2").css("background-color", "#317ee6").css("color", "#ffffff");
    $("#loadMenu3").css("background-color", "#ffffff").css("color", "#000000");
    $("#loanIntroduction").hide();
    $("#tradeExplain").show();
    $("#wantFinancing").hide();
  }

  if (menuOrder == 3) {
    $("#loadMenu1").css("background-color", "#ffffff");
    $("#loadMenu2").css("background-color", "#ffffff");
    $("#loadMenu3").css("background-color", "#317ee6");
    $("#loadMenu3").hide();
    $("#tradeExplain").hide();
    $("#wantFinancing").show();
  }
}

function changeFinancingMenu(menuOrder) {
    if (menuOrder == 1) {
      $("#financingMenu1").css("background-color", "#317ee6").css("color", "#ffffff");
      $("#financingMenu2").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingMenu3").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingIntroduction").show();
      $("#tradeExplain").hide();
      $("#wantFinancing").hide();
    }

    if (menuOrder == 2) {
      $("#financingMenu1").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingMenu2").css("background-color", "#317ee6").css("color", "#ffffff");
      $("#financingMenu3").css("background-color", "#ffffff").css("color", "#000000");
      $("#financingIntroduction").hide();
      $("#tradeExplain").show();
      $("#wantFinancing").hide();
    }

    if (menuOrder == 3) {
      $("#financingMenu1").css("background-color", "#ffffff");
      $("#financingMenu2").css("background-color", "#ffffff");
      $("#financingMenu3").css("background-color", "#317ee6");
      $("#financingIntroduction").hide();
      $("#tradeExplain").hide();
      $("#wantFinancing").show();
    }
}

function check(){
  jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
    var telephonePattern = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    return this.optional(element) || (telephonePattern.test(value) || mobile.test(value));
  }, "请正确输入联系电话!");
  jQuery.validator.addMethod("floatCheck", function(value, element) {
    return this.optional(element) || /^(\d+(\.\d)?)$/.test(value);
  }, "只能输入一位小数");

  return  $("#contact_form").validate({
    onsubmit:false,
    onkeyup:false,
    focusInvalid:false,
    errorElement: 'span',
    errorClass: 'help-block',
    rules: {
      companyname: {
        required: true
      },
      address: {
        required: true
      },
      businessarea: {
        required: true
      },
      amountnum: {
        required: true,
        floatCheck:true
      },
      contact: {
        required: true
      },
      phone: {
        required: true,
        isMobile:true
      }
    },

    messages: {
      companyname: {
        required: "请输入公司名称!"
      },
      address:{
        required: "请输入公司地址!"
      },
      businessarea:{
        required: "请输入业务区域!"
      },
      amountnum:{
        required: "请输入融资金额!",
        floatCheck:"请正确输入金额,限一位小数"
      },
      contact:{
        required: $.validator.format("请输入联系人!")
      },
      phone:{
        required: "请输入联系电话!"
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
      if(element.attr("name") == "amountnum"){
        error.insertAfter(element.parent().next());
      }else{
        error.insertAfter(element.parent());
      }
    }
  });
}
