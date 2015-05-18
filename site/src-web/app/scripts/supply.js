/**
 * Created by shiling on 14-12-8.
 */
require(['jquery','jquery.validation','bootstrap','bootstrap3-datetimepicker','CN'],function($){
      initData();

      $.ajax({
          url:'/doCheckCompany',
          success:function(data){
            if(!data.success) {
              switch(data.error){
                case "lackinfo":
                  $(".my_supply").modal('show');
                  $('.my_supplyBody').text("您的公司信息不完整,请完善!");
                  $('.my_supplyButton').attr("id","changeInfo_btn");
                  $("#changeInfo_btn").bind("click", function(){
                    window.location.href="/account/getMyAccount";
                  });
                      break;
                case "notpass":
                  $(".my_supply").modal('show');
                  $('.my_supplyBody').text("您的公司信息未通过审核!");
                  $('.my_supplyButton').attr("id","updateInfo_btn");
                  $("#updateInfo_btn").bind("click", function(){
                    window.location.href="/account/getMyAccount";
                  });
                      break;
                case "verifying":
                  $(".my_supply").modal('show');
                  $('.my_supplyBody').text("您的公司信息正在审核中,请您耐心等待!");
                  $('.my_supplyButton').attr("id","updateInfoVerifying_btn");
                  $("#updateInfoVerifying_btn").bind("click", function(){
                    window.location.href="/account/getMyAccount";
                  });
                      break;
                default:
                      break;
              }
            }
          }
        });

      function initData(){
        $('#datetimepicker1').datetimepicker({
          pickTime: false,
          todayBtn: true,
          language:'zh-cn'
        });
        $('#datetimepicker2').datetimepicker({
          pickTime: false,
          todayBtn: true,
          language:'zh-cn'
        });

        //时间控件显示
        if($("#datetimepicker1").length >0 ){
          $('#datetimepicker1').data("DateTimePicker").setMinDate(new Date());
          $('#datetimepicker2').data("DateTimePicker").setMinDate(new Date());
        }

        var type=$("input[name='optionsRadiosinline']:checked").val();
        if(type=="fixed"){
          $("#jtj012").attr("readonly",true);
          $("#jtj022").attr("readonly",true);
          $("#jtj032").attr("readonly",true);
          $("#jtj042").attr("readonly",true);
          $("#jtj052").attr("readonly",true);
          $("#addDiv").css("display","none");
          $("#small").attr("readonly",true);
          $("#middle").attr("readonly",true);
          $("#larger").attr("readonly",true);
          $("#bigger").attr("readonly",true);
          $("#biggest").attr("readonly",true);
          $("#error").text("");
          $("#smallest").val("");
          $("#small").val("");
          $("#more").val("");
          $("#middle").val("");
          $("#large").val("");
          $("#larger").val("");
          $("#big").val("");
          $("#bigger").val("");
          $("#littleBig").val("");
          $("#biggest").val("");
          $("#jtj012").val("");
          $("#jtj022").val("");
          $("#jtj032").val("");
          $("#jtj042").val("");
          $("#jtj052").val("");
        } else{
          $("#ykj2").attr("readonly",true);
          $("#ykj2").val("");
          $("#ykj2-error").text("");
          $("#addDiv").css("display","block");
          if($("#larger").val()!=""){
            $("#stepDiv3").css("display", "block");
          }
          if($("#bigger").val()!=""){
            $("#stepDiv4").css("display","block");
            $("#reduceZero").css("display", "none");
          }
          if($("#littleBig").val()!=""){
            $("#stepDiv5").css("display","block");
            $("#reduceFirst").css("display", "none");
            $("#reduceZero").css("display", "none");
          }
        }
      }
      check();
      //当检验机构为"无"
      $("#orgSelect").change(function(){
         if($("#orgSelect").val()=="其它"){
            $("#otherorgDisplay").slideDown();
         } else{
            $("#otherorgDisplay").slideUp();
            $("#otherorgError").val("");
         }
      });
    //当付款方式为账期
    $("#paymode").change(function(){
      if($("#paymode").val()=="2"){
        $("#payperiodDisplay").slideDown();
      } else{
        $("#payperiodDisplay").slideUp();
        $("#payperiodDisplay2").slideUp();
      }
    });

    //修改时，当联系方式为"让买家联系我公司客服"的时候
    if($("#linkType").val()){
      $("#linkInfoDiv").css('display','block');
    }

      //阶梯价填写
      $("#small").blur(function(){
        if(eval($("#small").val())<50){
            $("#error").text("最少瓶数为50").css("color","red");
            return;
        } else{
          $("#error").text("");
          if(($("#small").val() % 50) != 0){
            showjtjError("nonQualifiedNum");
            return;
          } else if(eval(($("#small").val())>eval($("#supplyNumber1").val()))){
            showjtjError("moreThanSupply");
            return;
          } else{
            $("#more").val($("#small").val());
          }
        }
      });
      $("#middle").blur(function(){
        if(eval($("#middle").val())<= eval($("#small").val())){
            showjtjError("lessThanPrevious");
            return;
        } else{
          $("#error").text("");
          if(($("#middle").val()% 50) != 0){
            showjtjError("nonQualifiedNum");
          } else if(eval(($("#middle").val())>eval($("#supplyNumber1").val()))){
            showjtjError("moreThanSupply");
            return;
          } else{
            $("#large").val($("#middle").val());
          }
        }
      });
      $("#larger").blur(function(){
          if(eval($("#larger").val())<=eval($("#middle").val())){
            showjtjError("lessThanPrevious");
            return;
          } else{
            $("#error").text("");
            if(($("#larger").val()% 50) != 0){
              showjtjError("nonQualifiedNum");
            } else if(eval(($("#larger").val())>eval($("#supplyNumber1").val()))){
              showjtjError("moreThanSupply");
              return;
            } else{
              $("#big").val($("#larger").val());
            }
          }
      });

      $("#bigger").blur(function(){
        if(eval($("#bigger").val())<=eval($("#larger").val())){
          showjtjError("lessThanPrevious");
          return;
        } else{
          $("#error").text("");
          if(($("#bigger").val()% 50) != 0){
            showjtjError("nonQualifiedNum");
          } else if(eval(($("#bigger").val())>eval($("#supplyNumber1").val()))){
            showjtjError("moreThanSupply");
            return;
          } else{
            $("#littleBig").val($("#bigger").val());
          }
        }
      });
      $("#biggest").blur(function(){
        if(eval($("#biggest").val())<=eval($("#bigger").val())){
          showjtjError("lessThanPrevious");
          return;
        } else{
          $("#error").text("");
          if(($("#biggest").val()% 50) != 0){
            showjtjError("nonQualifiedNum");
          } else if(eval(($("#biggest").val())>eval($("#supplyNumber1").val()))){
            showjtjError("moreThanSupply");
            return;
          } else{}
        }
      });

  //查询港口
      $('#deliveryprovince').change(function(){
        $.ajax({
            url:'/buy/getPorts',
            data:{id:$('#deliveryprovince').val()},
            success:function(data){
              $("#deliveryplace").empty();
              var jsonStr="";
              $.each(data, function (i, item) {
                if( i != data.length){
                  jsonStr += '<option value='+item.id+'>' + item.name + '</option>';
                }
              });
              if(data[0].id=="999"){
                $("#otherplaceDisplay").css("display","block");
              } else{
                $("#otherplaceDisplay").css("display","none");
              }
              $("#deliveryplace").html(jsonStr);
            }
        });
      });

      $("#deliveryplace").change(function(){
        if($("#deliveryplace").val()=='999'){
          $("#otherplaceDisplay").css("display","block");
        } else if($("#deliveryplace").val().replace(',','')=='10000'){
          $("#otherplaceDisplay").css("display","block");
        }else{
          $("#otherplaceDisplay").css("display","none");
        }
      })

      //默认显示最大瓶数
      $("#supplyNumber1").blur(function(){
        var type=$("input[name='optionsRadiosinline']:checked").val();
        if(type!="fixed") {
          if ($("#jtj032").is(":visible") && $("#jtj042").is(":visible") && $("#jtj052").is(":visible")) {
            $("#biggest").val($("#supplyNumber1").val());
          } else if ($("#jtj032").is(":visible") && $("#jtj042").is(":visible")) {
            $("#bigger").val($("#supplyNumber1").val());
          } else if ($("#jtj032").is(":visible")) {
            $("#larger").val($("#supplyNumber1").val());
          } else {
            $("#middle").val($("#supplyNumber1").val());
          }
        }
      });

      //radio选中
      $(".radioItem").change(function(){
          var type=$("input[name='optionsRadiosinline']:checked").val();
          if(type=="fixed"){
            $("#ykj2").attr("readonly",false);
            $("#jtj012").val("");
            $("#jtj022").val("");
            $("#jtj032").val("");
            $("#jtj042").val("");
            $("#jtj012").attr("readonly",true);
            $("#jtj022").attr("readonly",true);
            $("#jtj032").attr("readonly",true);
            $("#jtj042").attr("readonly",true);
            $("#jtj052").attr("readonly",true);
            $("#addDiv").css("display","none");
            $("#small").attr("readonly",true);
            $("#middle").attr("readonly",true);
            $("#larger").attr("readonly",true);
            $("#bigger").attr("readonly",true);
            $("#biggest").attr("readonly",true);
            $("#error").text("");
            $("#smallest").val("");
            $("#small").val("");
            $("#more").val("");
            $("#middle").val("");
            $("#large").val("");
            $("#larger").val("");
            $("#big").val("");
            $("#bigger").val("");
            $("#littleBig").val("");
            $("#biggest").val("");
            $("#jtj012").val("");
            $("#jtj022").val("");
            $("#jtj032").val("");
            $("#jtj042").val("");
            $("#jtj052").val("");
          } else{
            $("#smallest").val("50");
            $("#middle").val($("#supplyNumber1").val());
            $("#ykj2").val("");
            $("#ykj2-error").text("");
            $("#jtj012").val("");
            $("#jtj022").val("");
            $("#jtj032").val("");
            $("#jtj042").val("");
            $("#jtj052").val("");
            $("#ykj2").attr("readonly",true);
            $("#jtj012").attr("readonly",false);
            $("#jtj022").attr("readonly",false);
            $("#jtj032").attr("readonly",false);
            $("#jtj042").attr("readonly",false);
            $("#jtj052").attr("readonly",false);
            $("#small").attr("readonly",false);
            $("#middle").attr("readonly",true);
            $("#larger").attr("readonly",false);
            $("#bigger").attr("readonly",false);
            $("#biggest").attr("readonly",false);
            $("#addDiv").css("display","block");
          }
      });

      //选择联系方式
      $(".linkItem").change(function(){
        var selectedValue=$('input:radio[name="linktype"]:checked').val();
        if(selectedValue==1){
          $("#linkInfoDiv").css('display','block');
          $("#linkmanname_error").text("");
          $("#linkmanphone_error").text("");
        }else{
          //$("#linkmanname").val('');
          //$("#linkmanphone").val('');
          $("#linkInfoDiv").css('display','none');
        }
      });
      // 校验检验机构，提货地点，，时间
      function validateForm() {
        var validateFlag = true;
        //提货地点是其它时，对文本框的效验
        if($("#otherplaceDisplay").is(":visible")) {
          if ($("#deliveryplace").val() == "999") {
            if ($("#otherharbour").val() == "") {
            $("#otherplaceError").text("请输入提货地点");
            validateFlag=false;
            }
          } else {
            $("#otherplaceError").text("");
          }
        }
        //检验机构是其它时，对文本框的校验
        if($("#otherorgDisplay").is(":visible")){
          if($("#orgSelect").val() == "其它"){
            if($("#otherinspectorg").val() == ""){
            $("#otherorgError").text("请输入检验机构");
            validateFlag=false;
            } else{
            $("#otherorgError").text("");
            }
          } else{
            $("#otherorgError").text("");
          }
        }

        if($("#deliverydate").val()==""||$("#deliverydate2").val()==""){
          $("#errorInfo").html("请填写交货时间").css("color","red");
          validateFlag=false;
        } else if(dateCompare($("#deliverydate").val(),($("#deliverydate2").val()))==false){
          $("#errorInfo").html("结束时间不能大于开始时间").css("color","red");
          validateFlag=false;
        } else{
          $("#errorInfo").html("");
        }

        //根据联系方式判断，如果是"让买家联系我公司客服",则进行姓名和联系方式的校验。
        if($("#linkInfoDiv").is(":visible")){
           if(!checkLinkmanName() || !checkLinkmanPhone())
           validateFlag=false;
        }
        return validateFlag;
      }

      $("#linkmanname").blur(function(){
        checkLinkmanName();
      });
      $("#linkmanphone").blur(function(){
        checkLinkmanPhone();
      });

      //校验联系人姓名
      function checkLinkmanName(){
        if ($("#linkmanname").val() == "") {
          $("#linkmanname_error").text("姓名不能为空").css("color", "red");
          return false;
        } else {
          $("#linkmanname_error").text("");
        }
        return true;
      }
      //校验联系人电话
      function checkLinkmanPhone(){
        var reg = /^0?(13[0-9]|17[0-9]|15[0-9PP]|18[0-9]|14[57])[0-9]{8}$/;
        var linkPhone=$("#linkmanphone").val();
        if(linkPhone==""){
          $("#linkmanphone_error").text("手机号不能为空").css("color", "red");
          return false;
        } else if(!reg.test(linkPhone) || linkPhone.length != 11){
          $("#linkmanphone_error").text("请输入正确的手机号").css("color", "red");
          return false;
        } else{
          $("#linkmanphone_error").text("");
        }
        return true;
      }



      //“确认发布”按钮点击时事件
      $("#btn-confirmSupply").click(function(){
        // 清除错误
        $("#commonError").text("");

        // 如果有校验错误就返回
        // validateForm()校验检验机构，提货地点，时间
        // check().form()校验指标， checkSupplyNum()校验瓶数
        if (! (check().form() & validateForm() & checkSupplyNum() ) ) {
          $("#commonError").text("您的输入有误，请根据上面红色的提示，修改信息。").css("color","red");
          return;
        }
        var supplyUrl = '/addSellinfo'; //未曾审核通过，跳到/addSellinfo
        var verifiedOnce = $("#verifiedOnce").val();  // 曾审核通过过，跳到/editSellinfo

        if (verifiedOnce) {
          supplyUrl = '/editSellinfo';
        }
        releaseSupplyInfo(supplyUrl);
    });

      //取消发布供应信息
      $("#btn-cancelSupply").click(function(){
          window.location.href="/sell";
      })

      $("#btn-supplyUpdate").click(function(){
          window.location.href="/account/updateSupply?id="+$("#supplyId").val();
      })

      //确认发布(校验)
      $("#confirm-announce").click(function(){
          $.ajax({
            url:'/confirmSellinfo',
            data:{id:$("#supplyId").val(), version:$("#version").val()},
            success:function(data){
              if(data){
                $("#modal-checkSuccess").modal('show');
                $("#supply-close").click(function(){
                  window.location.href="/account/getMySupply";  //确认发布后跳转到需求信息发布页面
                });
              } else{
                return "/";
              }
            }
          })
      });

      //更改供应发布信息(发布校验)
      $("#update-announce").click(function(){
        window.location.href="/account/updateSupply?id="+$("#supplyId").val() + "&type=update";
      })

      //对发布的信息进行校验
      function check(){
        jQuery.validator.addMethod("floatCheck", function(value, element) {
          return this.optional(element) || /^(\d+(\.\d)?)$/.test(value);
        }, "只能输入一位小数");
        jQuery.validator.addMethod("onceCheck", function(value, element) {
          return this.optional(element) || (/^\d+(\.\d{0,2})?$/).test(value);
        }, "只能输入两位小数");
        jQuery.validator.addMethod("numberCheck", function(value, element) {
          var firstChar = value.substr(0,1);
          return this.optional(element) || firstChar != 0;
        }, "请输入合法数字");
        jQuery.validator.addMethod("secCheck", function(value, element) {
          var regular;
          if(value.indexOf(".") != -1){
            if(value.substr(0,value.indexOf(".")).length >=2){
              if(value.substr(0,1) == 0){
                regular = 0;
              }
            }
          }else{
            if(value.length >= 2){
              if(value.substr(0,1) == 0){
                regular = 0;
              }
            }
          }
          return this.optional(element) || regular != 0 ;
        }, "请输入合法的格式");
        jQuery.validator.addMethod("multiple", function(value, element) {
          return this.optional(element) || (value % 50) == 0;
        }, " 输入的数字必须是50的倍数!");
        jQuery.validator.addMethod("hundredCheck", function(value, element) {
          return this.optional(element) || (value % 100) == 0;
        }, " 输入的数字必须是100的倍数!");
        jQuery.validator.addMethod("compare", function(value, element) {
          var firstValue = $("#TM").val();
          return this.optional(element) || eval(value) < eval(firstValue);
        }, "酒类指标4必须大于酒类指标5");

      return  $("#supply-form").validate({
      onsubmit:false,
      onkeyup:false,
      focusInvalid:false,
      errorElement: 'span',
      errorClass: 'help-block',
      rules: {
        NCV: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[3000,7000]
        },

        ADS: {
          range:[0.1,10],
          secCheck:true,
          onceCheck:true
        },

        RS: {
          required: true,
          secCheck:true,
          range:[0.1,9.99],
          onceCheck:true
        },

        TM: {
          required: true,
          secCheck:true,
          range:[0.1,50],
          onceCheck:true
        },

        IM: {
          onceCheck:true,
          secCheck:true,
          compare:true,
          range:[0.1,50]
        },

        ADV: {
          required: true,
          numberCheck:true,
          secCheck:true,
          range:[0.1,50],
          onceCheck:true
        },

        RV: {
          numberCheck:true,
          onceCheck:true,
          secCheck:true,
          range:[0.1,50]
        },

        AFT: {
          numberCheck:true,
          range:[900,1600]
        },

        ASH: {
          numberCheck:true,
          floatCheck:true,
          secCheck:true,
          range:[0.1,50]
        },

        HGI: {
          digits:true,
          range:[0.1,100],
          numberCheck:true
        },

        deliveryplace: {
          required: true
        },

        deliverymode: {
          required: true
        },

        deliverytime1: {
          required: true
        },

        deliverytime2: {
          required: true
        },

        supplyquantity: {
          required: true,
          digits:true,
          maxlength:6,
          numberCheck:true,
          multiple:true
        },

        inspectorg: {
          required: true
        },

        ykj: {
          required: true,
          numberCheck:true,
          digits:true,
          range:[50,1200]
        },

        jtj01: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[50,1200]
        },

        jtj02: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[50,1200]
        },

        jtj03: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[50,1200]
        },

        jtj04: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[50,1200]
        },
        jtj05: {
          required: true,
          digits:true,
          numberCheck:true,
          range:[50,1200]
        },
        org:{
          required:true
        },
        originplace:{
          required:true
        }
      },


      messages: {
        NCV: {
          required: "请输入酒精度数",
          digits:"请输入整数",
          range: $.validator.format("请输入一个介于3000-7000之间的整数!")
        },
        ADS:{
          range: $.validator.format("请输入一个介于0-10之间的数值[不包括0]!")
        },
        RS:{
          required: "请输入含糖量",
          range: $.validator.format("请输入一个介于0-10之间的数值[不包括0和10]!")
        },
        TM:{
          required: "请输入酒类指标4",
          range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
        },
        IM:{
          range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
        },
        ADV:{
          required: "请输入酒类指标2",
          range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!"),
          digits:"酒类指标2只能输入整数"
        },
        RV:{
          range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!"),
          digits:"酒类指标3只能输入整数"
        },
        AFT:{
          range: $.validator.format("请输入一个介于900-1600之间的整数!"),
          digits:"酒类指标7必须输入整数"
        },
        ASH:{
          range:$.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
        },
        HGI:{
          range:$.validator.format("请输入一个介于0-100之间的整数[不包括0]!"),
          digits:"酒类指标8必须输入整数"
        },
        deliveryplace:{
          required: "请输入交货地点"
        },
        deliverymode:{
          required: "请输入交货方式"
        },
        deliverytime1:{
          required: "请输入交货时间"
        },
        deliverytime2:{
          required: "请输入交货时间"
        },
        supplyquantity:{
          required: "请输入供货数量",
          digits:"请输入正整数",
          maxlength:"供货数量只能输入6位数"
        },
        inspectorg:{
          required: "请输入检验机构"
        },
        ykj:{
          required: "请输入单价",
          digits:"请输入正整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        jtj01:{
          required: "请输入单价",
          digits:"只能输入整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        jtj02:{
          required: "请输入单价",
          digits:"只能输入整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        jtj03:{
          required: "请输入单价",
          digits:"只能输入整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        jtj04:{
          required: "请输入单价",
          digits:"只能输入整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        jtj05:{
          required: "请输入单价",
          digits:"只能输入整数",
          range:$.validator.format("单价为50-1200间的数值")
        },
        org:{
          required:"请输入检验机构"
        },
        originplace:{
          required:"请输入产地"
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
        error.insertAfter(element.parent().parent());
      }
    });
  }

      function dateCompare(startdate,enddate){
        var startTime = new Date(startdate.replace('-','/').replace('-','/')).getTime();
        var endTime = new Date(enddate.replace('-','/').replace('-','/')).getTime();
        if(startTime > endTime) {
          return false;
        }
        else{
          return true;
        }
      }

    //返回按钮事件
    $("#back-announce").bind("click",function(){
      window.location.href="/account/getMySupply";
    });

    $("#add").click(function(){
      if($("#jtj032").is(":visible") && $("#jtj042").is(":visible")) {
        $("#stepDiv5").css("display", "block");
        $("#reduceFirst").hide();
        $("#bigger").val("");
        $("#bigger").removeAttr("readonly");
        $("#biggest").val($("#supplyNumber1").val());
        $("#biggest").attr("readonly",true);
        $("#reduceSecond").show();
      } else if($("#jtj032").is(":visible")) {
        $("#stepDiv4").css("display", "block");
        $("#reduceZero").hide();
        $("#larger").val("");
        $("#larger").removeAttr("readonly");
        $("#bigger").val($("#supplyNumber1").val());
        $("#bigger").attr("readonly",true);
        $("#reduceFirst").show();
      } else {
        $("#stepDiv3").css("display","block");
        $("#larger").val($("#supplyNumber1").val());
        $("#reduceSecond").show();
        $("#middle").val("");
        $("#large").val("");
        $("#larger").attr("readonly",true);
        $("#middle").removeAttr("readonly");
        $("#reduceZero").show();
        $("#reduceFirst").hide();
      }
    });
    $("#reduceZero").click(function(){
      $("#stepDiv3").css("display","none");
      $("#jtj032").val("");
      $("#larger").val("");
      $("#reduceFirst").hide();
      $("#reduceSecond").hide();
      $("#middle").attr("readonly",true);
      $("#middle").val($("#supplyNumber1").val());
    })
    $("#reduceFirst").click(function(){
      $("#stepDiv4").css("display","none");
      $("#jtj042").val("");
      $("#bigger").val("");
      $("#reduceZero").show();
      $("#reduceSecond").hide();
      $("#larger").attr("readonly",true);
      $("#larger").val($("#supplyNumber1").val());
    })
    $("#reduceSecond").click(function(){
      $("#reduceFirst").show();
      $("#reduceZero").hide();
      $("#stepDiv5").css("display","none");
      $("#jtj052").val("");
      $("#biggest").val("");
      $("#bigger").val($("#supplyNumber1").val());
      $("#bigger").attr("readonly", true);
    })
    $("#otherinspectorg").change(function(){
      if($("#otherinspectorg").val()=="") {
        $("#otherorgError").text("请输入检验机构");
      } else{
        $("#otherorgError").text("");
      }
    });
  });

  function showjtjError(errorInfo) {
    if (errorInfo === "nonQualifiedNum") {
      $("#error").text("输入的瓶数必须是50的倍数").css("color", "red");
    } else if (errorInfo === "moreThanSupply") {
      $("#error").text("您所填写瓶数不能超过供应数量").css("color", "red");
    } else if (errorInfo === "lessThanPrevious") {
      $("#error").text("您所填写的阶梯价瓶数必须大于前面的瓶数").css("color", "red");
    }
  }

  function checkSupplyNum(){
    // 如果不是阶梯价就返回
    var type=$("input[name='optionsRadiosinline']:checked").val();
    if(type=="fixed"){
      return true;
    }

    if(!$("#small").val()||!$("#middle").val()){
      $("#error").text("请填写瓶数范围").css("color","red");
      return false;
    } else{
      if(eval($("#small").val())<50){
        $("#error").text("最少瓶数为50").css("color","red");
        return false;
      } else{
        $("#error").text("");
        if((eval($("#small").val()) % 50) != 0){
          showjtjError("nonQualifiedNum");
          return false;
        } else if(eval(($("#small").val())>eval($("#supplyNumber1").val()))){
          showjtjError("moreThanSupply");
          return false;
        } else{
          $("#more").val($("#small").val());
        }
      }
      if(eval($("#middle").val())<= eval($("#small").val())){
        showjtjError("lessThanPrevious");
        return false;
      } else{
        $("#error").text("");
        if((eval($("#middle").val())% 50) != 0){
          showjtjError("nonQualifiedNum");
          return false;
        } else if(eval(($("#middle").val())>eval($("#supplyNumber1").val()))){
          showjtjError("moreThanSupply");
          return false;
        } else{
          $("#large").val($("#middle").val());
        }
      }
      if($("#stepDiv3").is(":visible")) {
        if($("#jtj032").val()!="") {
          if($("#larger").val()==""){
            $("#error").text("请填写瓶数！").css("color", "red");
            return false;
          } else if (eval($("#larger").val()) <= eval($("#middle").val())) {
            showjtjError("lessThanPrevious");
            return false;
          } else {
            $("#error").text("");
            if ((eval($("#larger").val()) % 50) != 0) {
              showjtjError("nonQualifiedNum");
              return false;
            } else if (eval(($("#larger").val()) > eval($("#supplyNumber1").val()))) {
              showjtjError("moreThanSupply");
              return false;
            } else {
              $("#big").val($("#larger").val());
            }
          }
        } else{
          $("#error").text("");
        }
      }

      if($("#stepDiv4").is(":visible")) {
        if($("#jtj042").val()!=""){
          $("#error").text("");
          if($("#bigger").val()==""){
            $("#error").text("请填写瓶数").css("color","red");
            return false;
          } else if(eval($("#bigger").val())<=eval($("#larger").val())){
            showjtjError("lessThanPrevious");
            return false;
          } else{
            $("#error").text("");
            if((eval($("#bigger").val()% 50)) != 0){
              showjtjError("nonQualifiedNum");
              return false;
            } else if(eval(($("#bigger").val())>eval($("#supplyNumber1").val()))){
              showjtjError("moreThanSupply");
              return false;
            } else {
              $("#littleBig").val($("#bigger").val());
            }
          }
        };
      } else{
        $("#error").text("");
      }

      if($("#stepDiv5").is(":visible")) {
        if($("#jtj052").val()!=""){
          if(eval($("#biggest").val())==""){
            $("#error").text("请填写瓶数").css("color","red");
          } else{
            if(eval($("#biggest").val())<=eval($("#bigger").val())){
              showjtjError("lessThanPrevious");
              return false;
            } else{
              $("#error").text("");
              if((eval($("#biggest").val())% 50) != 0){
                showjtjError("nonQualifiedNum");
                return false;
              } else if(eval(($("#biggest").val())>eval($("#supplyNumber1").val()))){
                showjtjError("moreThanSupply");
                return false;
              } else{
                $("#biggest").val($("#supplyNumber1").val());
              }
            }
          }
        }
      } else{
        $("#error").text("");
      }
    }
    return true;
  }

//确认修改我的供应
function updateMySupply(id, status) {
  if(status == 'VerifyPass') {
    $("#supplyUpdeteComfirm").modal('show');
    $("#supplyUpdeteComfirmBtn").click(function () {
      window.location.href = "/account/updateSupply?id=" + id;
    });
  } else{
    window.location.href = "/account/updateSupply?id=" + id;
  }
}

function releaseSupplyInfo(supplyUrl){
  $.ajax({
    url: '/doCheckCompany',
    success: function (data) {
      if(data.success){
        var formData = $("#supply-form").serialize();
        $.ajax({
          //url: '/addSellinfo',
          url: supplyUrl,
          data: formData,
          success: function (data) {
            if (data.success) {
              var id = data.id;
              var verifiedOnce = $("#verifiedOnce").val();  // 曾审核通过过
              if (verifiedOnce) {
                $('.my_supplyBody').text("您的供应已提交，请等待审核。");
                $('.my_supplyButton').attr("id","changeInfo_btn");
                $("#changeInfo_btn").text("回到我的供应");
                $("#closeBtn").hide();
                $(".my_supply").modal('show');
                $("#changeInfo_btn").bind("click", function(){
                  window.location.href="/account/getMySupply";
                });
              } else {
                window.location.href = "/account/getSupplyDetail?id=" + id;
              }
            } else {
              if (data.error.indexOf("before") >=0 ) {
                $("#errorInfo").html("生效时间不能在今天之前").css("color", "red");
                $("#commonError").text("您的输入有误，请根据上面红色的提示，修改信息。").css("color","red");
              }
              if (data.error.indexOf("wrongQuantity") >=0 ) {
                $("#commonError").html("修改后的供应数量必须大于已售数量。").css("color", "red");
              }
            }
          }
        });
      } else {
        if (data.error == "notlogin") {
          window.location.href = "/login";
        } else if (data.error == "lackinfo") {
          $(".my_supply").modal('show');
          $('.my_supplyBody').text("您的公司信息不完整,请完善!");
          $('.my_supplyButton').attr("id","changeInfo_btn");
          $("#changeInfo_btn").bind("click", function(){
            window.location.href="/account/getMyAccount";
          });
        } else if (data.error == "notpass") {
          $(".my_supply").modal('show');
          $('.my_supplyBody').text("您的公司信息未通过审核!");
          $('.my_supplyButton').attr("id","updateInfo_btn");
          $("#updateInfo_btn").bind("click", function(){
            window.location.href="/account/getMyAccount";
          });
        } else if (data.error == 'verifying') {
          $(".my_supply").modal('show');
          $('.my_supplyBody').text("您的公司信息正在审核中,请您耐心等待!");
          $('.my_supplyButton').attr("id","updateInfoVerifying_btn");
          $("#updateInfoVerifying_btn").bind("click", function(){
            window.location.href="/account/getMyAccount";
          });
        }
      }
    }
  });
}
