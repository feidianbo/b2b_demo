require(['jquery','bootstrap'],function($){
  var demandValue = $("#demandCount").val().replace(",","");

  var regluar = /^[1-9]+[0-9]*]*$/;
  $("#supplyton").bind("blur",function(){
    checkSupply();
  });

  if($("#modifyPrice").val() != 0){
    $("html,body").animate({scrollTop: $("#btnShow").offset().top - 20}, 10);
    $('#isShowDialog').show();
  }
  $("#btnShow").bind("click", function(){
    if(!isCheck()){
      return;
    }else{
      if($("#isShowDialog").is(":visible")){
        $('#isShowDialog').hide();
      }else{
        $('#isShowDialog').show();
      }
    }

  });

  function checkSupply(){
    if($("#supplyton").val() == ""){
      $("#errors").text("请输入正确的申供瓶数!");
      return false;
    }else if(!regluar.test($("#supplyton").val())){
      $("#errors").text("申供瓶数为正整数!");
      return false;
    }else if($("#supplyton").val() % 50 !=0){
      $("#errors").text("申供瓶数应是50的倍数!");
      return false;
    }else if(eval(demandValue) < eval($("#supplyton").val())){
      $("#errors").text("申供瓶数应小于需求数量!");
      return false;
    }else{
      $("#errors").text("");
      return true;
    }
  }

  function checkQuote(){
    if($("#quote").val() == ""){
      $("#errorsForQuote").text("请输入单价!");
      return false;
    }else if(!regluar.test($("#quote").val())){
      $("#errorsForQuote").text("请输入正确的单价!");
      return false;
    }else{
      $("#errorsForQuote").text("");
      return true;
    }
  }

  $("#supplyton").bind("focus",function(){
    $("#errors").text("");
  });

  $("#quote").bind("focus",function(){
    $("#errorsForQuote").text("");
  });

  $("#quote").bind("blur",function(){
    checkQuote();
  });

  $("#btnOK").click(function(){
    //判断用户是否登录(新增的需求)
    //if(!isCheck()) return;
    //判断用户是否已报过价(需求已变动，可以重复报价)
    if(!isQuote()) return;
    $.ajax({
      async:false,
      url:"/checkCompanyInfo",
      success:function(data){
        if(data  == "improve"){
          $(".dialog_companyInfo").modal('show');
          $(".dialog_body").text("请完善公司信息!");
          $('.dialog_btn').attr("id","improve_companyInfo");
          $("#improve_companyInfo").bind("click", function(){
            $(".dialog_companyInfo").modal('hide');
            location.href = "/account/getMyAccount";
          });
        }else if(data == "verifying"){
          $(".dialog_companyInfo").modal('show');
          $(".dialog_body").text("您的公司信息正在审核中,请您耐心等待!");
          $('.dialog_btn').attr("id","verify-companyInfo");
          $("#verify-companyInfo").bind("click", function(){
            $(".dialog_companyInfo").modal('hide');
            location.href = "/account/getMyAccount";
          });
        }else if( data == "notPass"){
          $(".dialog_companyInfo").modal('show');
          $(".dialog_body").text("公司认证审核未通过!");
          $('.dialog_btn').attr("id","notPass_companyInfo");
          $("#notPass_companyInfo").bind("click", function(){
            $(".dialog_companyInfo").modal('hide');
            location.href = "/account/getMyAccount";
          });
        }else if( data  == "notLogin"){
          $("#modal-login").modal('show');
        }else{
          if(checkSupply() && checkQuote()){
            $.ajax({
              url:"/quote",
              data:{id:$("#demandcode").val(),supplyton:$("#supplyton").val(),quote:$("#quote").val()},
              success:function(data){
                if(data == "success"){
                  $(".dialog_Info").modal('show');
                  $(".dialog_InfoBody").text('您的报价已提交,若您的报价中标,交易员会及时和您联系.');
                  $(".dialog_InfoBtn").attr("id","close_modal");
                  $("#dialog_InfoContent").css("top","200px").css("left","auto").css("width","auto");
                  $("#close_modal").click(function(){
                    $(".dialog_Info").modal('hide');
                    window.location.href='/account/getMyQuote';
                  });
                }
              }
            });
          }
        }
      }
    });
  });

  $("#sellInfo_supply").click(function(){
    window.location.href="/sell/createSupply";
  });

  //需求关注按钮
  $("#btnAttend").click(function(){
    if(!isCheck()) return;
    $.ajax({
      url:'/buy/addInterest',
      data:{id:$("#idText").val(),type:"demand"},
      success:function(data){
        if(data){
          $(".dialog_Info").modal('show');
          $(".dialog_InfoBody").text("关注成功!");
        }
        else{
          if(data.error == "notlogin") {
            $("#modal-login").modal('show');
          }
        }
      }
    })
  });

  //我的报价返回按钮
  $("#back_myQuote").bind("click", function(){
    window.location.href="/account/getMyQuote";
  });

  //发布
  $("#release_supply").bind("click", function(){
    location.href="/supplyInfo";
  });

  //判断用户是否登录
  function isCheck(){
    var result = true;
    $.ajax({
      async:false,
      url: "/checkCompanyInfo",
      success: function (data) {
        if(data == "notLogin"){
          $("#modal-login").modal('show');
          result = false;
        }
        else{
          result = true;
        }
      }
    });
    return result;
  }

  //判断用户是否已经对该条需求是否报过价
  function isQuote(){
    var result = true;
    $.ajax({
      async:false,
      url:"/checkIsQuoted",
      data:{demandid:$("#demandcode").val()},
      success: function (msg) {
        //if(msg == "quoted"){
        //  $("#modal-quote").modal('show');
        //  result = false;
        //}
        //else
        if( msg == "mydemand"){
          $(".dialog_Info").modal('show');
          $(".dialog_InfoBody").text("您不能对自己发布的需求报价!");
          result = false;
        }
        else{
          result = true;
        }
      }
    });
    return result;
  }
});

