require(['jquery', 'jquery.validation', 'bootstrap', 'pagination', 'bootstrap3-datetimepicker','CN'], function ($) {
  //委托图标隐藏
  $("#isShow").css("display",'none');
  //表单验证
  check();
  function manuallookup() {
    $("#dealAddress").text("提货地点:");
    $("#dealCount").text("需求数量:");
    $("#dealMode").text("提货方式:");
    $("#dealDate").text("提货时间:");
    //("委托找货")
  }

  function manualsell() {
    $("#dealAddress").text("交货地点:");
    $("#dealCount").text("供货数量:");
    $("#dealMode").text("交货方式:");
    $("#dealDate").text("交货时间:");
  }

  //判断找货&销售类型
  if ($("#delegationType").val() == "1") {
    $(":radio[name='optionsRadios']")[0].checked = true;
    manuallookup();
  } else {
    $(":radio[name='optionsRadios']")[1].checked = true;
    manualsell();
  }

  $("#lookupRadio").click(function () {
    manuallookup();
  });

  $("#sellRadio").click(function () {
    manualsell();
  });

  var $province= $("#deliveryProvince");
  var $otherPlace=$("#otherplaceDisplay");
  var $port=$("#deliveryAddr");
  /***********************提货地点select联动*********************************/
  $("#deliveryDistrict").bind("change", function () {
    $.ajax({
      url: "/getProvincesByParentname",
      data: {name: $(this).val()},
      success: function (responseObj) {
        if (responseObj != null) {
          //清空省份下拉框
          $province.html();
          var json = "";
          $.each(responseObj, function (index, data) {
            json += "<option value=" + data.name + ">" + data.name + "</option>";
          });
          //填空省份下拉框
          $province.html(json);

          if ($province.val() == "河南省" || $province.val() == "山西省" || $province.val() == "内蒙古") {
            $otherPlace.css("display", "block");
          } else {
            $otherPlace.css("display", "none");
          }
          $.ajax({
            url: "/getPortsByParentname",
            data: {name: $province.val()},
            success: function (data) {
              if (null != data) {
                $port.html("");
                var json = "";
                $.each(data, function (n, value) {
                  json += "<option value=" + value.name + ">" + value.name + "</option>";
                });
                $port.html(json);
              }
            }
          });
        }

      }
    });
  });

  //根据省份名称获取子集-获取港口
  $province.bind("change", function () {
    if ($province.val() == "河南省" || $province.val() == "山西省" || $province.val() == "内蒙古") {
      $otherPlace.css("display", "block");
    } else {
      $otherPlace.css("display", "none");
    }
    $.ajax({
      url: "/getPortsByParentname",
      data: {name: $province.val()},
      success: function (data) {
        if (null != data) {
          $port.html("");
          var json = "";
          $.each(data, function (n, value) {
            json += "<option value=" + value.name + ">" + value.name + "</option>";
          });
          $port.html(json);
        }
      }
    });
  });

  //提货地点-港口为其它时的出现和隐藏
  $port.change(function () {
    if ($port.val() == "其它") {
      $otherPlace.css("display", "block");
    }
    else {
      $otherPlace.css("display", "none");
      $("#otherplaceError").text("");
    }
  });

  $('#datetimepicker1').datetimepicker({
    pickTime: false,
    todayBtn: true,
    language:'zh-cn'
  });
var nowDate = new Date();
  $('#datetimepicker1').data("DateTimePicker").setMinDate(nowDate);

  $('#datetimepicker2').datetimepicker({
    pickTime: false,
    //todayBtn: true,
    language:'zh-cn'
  });
  var now = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate()+1);
  $('#datetimepicker2').data("DateTimePicker").setMinDate(now);
  //提货方式为场地自提时，出现提货截止日期时间框
  $("#deliverymode").bind("change", function () {
    if ($(this).val() == "场地自提") {
      $("#datetimepicker2").show();
      $("#timeInfo").html("");
      return;
    }
    $("#deliveryEndDate").val("");
    $("#datetimepicker2").hide();

  });

  function CurentTime() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var clock = year + "-";
    if (month < 10)
      clock += "0";
    clock += month + "-";
    if (day < 10)
      clock += "0";
    clock += day + " ";
    return (clock);
  }

  var count = 60;
  //发送验证码之前必须确认当前手机号码是有效的

  var sendCodeButton=$("#sendCode");
  sendCodeButton.click(sendCode);
  var $tooltip =$("#code");
 function sendCode () {
   $tooltip.text("");
   $tooltip.html("");
   if ($("#securephone").val() == "") {
     $tooltip.text("请先输入手机号!").css("color", "red").css("font-size", "16px");
   } else {
     if (/^[1][3,4,5,7,8][0-9]{9}$/.test($("#securephone").val()) != true||$("#securephone").val().length!=11) {
       $tooltip.text("当前手机号码不合法!").css("color", "red").css("font-size", "16px");
       return;
     }
     //禁用获取验证码按钮
     var btn = $("#sendCode");
     btn.attr("disabled", "true");
     $.ajax({
       url: "/manualsell/sendVerifyCode",
       data: {securephone: $("#securephone").val().replace(/\s+/g, "")},
       success: function (data) {
         if (data.success) {
           timeLoop(btn, count);
         } else {
           $.each(data.errors, function (index, error) {
             $tooltip.text(error.defaultMessage).css("color", "red");
           })
         }
       }
     });
   }
 }

  $("#verifyCode").focus(function () {
    //清空
    $tooltip.html("");
    $tooltip.text("");
  });
  //获取验证码
  function timeLoop(btn, count) {
    setTimeout(function () {
      btn.text("重新发送(" + count-- + ")");
      //去除绑定事件
      btn.unbind("click");
      if (count == -1) {
        btn.text("获取验证码");
        //绑定事件
        sendCodeButton.click(sendCode);
        return;
      }
      timeLoop(btn, count);
    }, 1000);
  }
  $("#securephone").blur(function(){
    //隐藏域的值
    var oldPhone=$("#oldPhone").val();
    //页面上文本框的值
    var newPhone=$(this).val();
    var pattern = new RegExp(/^[1][3,4,5,7,8][0-9]{9}$/);
    if(newPhone.length==11&&pattern.test(newPhone)&&newPhone!=oldPhone){
      $("#verifyCodegroup").css("display","block");
    }else if(newPhone===oldPhone){
      $("#verifyCodegroup").css("display","none");
    }
  });
  //表单提交
  $("#submit").bind("click", function () {
    $tooltip.html("");
    $tooltip.text("");
    check().form();
    //绑定验证规则
    if ($("#deliverydatestart").val() == "") {
      $("#timeInfo").html("请填写提货时间").css("color", "red");
      return;
    } else if ($("#deliverymode").val() == "场地自提") {
      if ($("#deliveryEndDate").val() == "") {
        $("#timeInfo").html("请输入结束时间").css("color", "red");
        return;
      } else if (dateCompare($("#deliveryEndDate").val(), $("#deliverydatestart").val()) == true) {
        $("#timeInfo").html("结束时间不能大于开始时间").css("color", "red");
        return;
      } else {
        $("#timeInfo").html("");
      }
    } else {
      $("#timeInfo").html("");
    }

    if (!check().form()) {
      return;
    } else {
      //委托类型
      var type = $("input[name='optionsRadios']:checked").val();
      var paramObj = $("#lookupForm").serialize() + "&type=" + type;
      $.ajax({
        url: "/manualsell/save",
        data: paramObj,
        success: function (data) {
          if (data.success) {
            $("#modal-confirm-dialog").modal("show");
          } else {
            $.each(data.errors, function (index, error) {
              $tooltip.css("display", "block");
              $tooltip.text(error.defaultMessage).css("color", "red").css("margin-left","-150px");
            });

          }
        }
      });
    }
  });

  $("#close_confirm_button").click(function () {
    $("#modal-confirm-dialog").modal("hide");
    window.location.href="/";
  });
});


function check() {
  jQuery.validator.addMethod("floatCheck", function (value, element) {
    return this.optional(element) || /^(\d+(\.\d{1,2})?)$/.test(value);
  }, "请输入两位小数!");
  jQuery.validator.addMethod("numberCheck", function (value, element) {
    var firstChar = value.substr(0, 1);
    return this.optional(element) || firstChar != 0;
  }, "请输入合法数字!");
  jQuery.validator.addMethod("multiple", function (value, element) {
    return this.optional(element) || (value % 100) == 0;
  }, " 输入的数字必须是100的倍数!");
  jQuery.validator.addMethod("demandAmountValidate", function (value, element) {
    return this.optional(element) || (value % 50) == 0;
  }, " 输入的数字必须是50的倍数!");
  jQuery.validator.addMethod("mobileNum", function (value, element) {
    return this.optional(element) || /^[1][3,4,5,7,8][0-9]{9}$/.test(value);
  }, "请输入有效的手机号码!");
  jQuery.validator.addMethod("decimalValidate", function (value, element) {
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
  }, "请输入正确的小数!");
  //酒类指标1必须大于含糖量
  jQuery.validator.addMethod("ADSgtRS", function (value, element) {
   return  $("input[name=airdrybasisvolatile]").val()>$("input[name=receivebasissulfur]").val();
  }, "酒类指标1必须大于含糖量!");
  return $("#lookupForm").validate({
    onsubmit: false,
    onkeyup: false,
    focusInvalid: false,
    errorElement: 'span',
    errorClass: 'help-block',
    rules: {
      lowcalorificvalue: {
        required: true,
        digits: true,
        numberCheck: true,
        range: [3000, 7000]
      },
      //airdrybasissulfur: {
      //  required: true,
      //  range: [0.1, 6],
      //  floatCheck: true
      //},
      demandAmount: {
        required: true,
        digits: true,
        numberCheck:true,
        maxlength:6,
        demandAmountValidate:true,
        min:1
      },
      contactName: {
        required: true,
        minlength: 2,
        maxlength: 20
      },
      phone: {
        required: true,
        minlength:11,
        maxlength: 11,
        mobileNum: true
      },
      companyName: {
        required: true,
        maxlength: 20
      },
      airdrybasisvolatile: {
        required: true,
        range:[0.1,50],
        floatCheck: true,
        decimalValidate:true
      },
      receivebasissulfur: {
        required: true,
        range:[0.1,50],
        floatCheck: true,
       decimalValidate:true
      },
      verifyCode: {
        required: true
      }
    },
    messages: {
      airdrybasisvolatile: {
        required: "请输入酒类指标2!",
        range: $.validator.format("请输入一个介于0-50之间的数值!")
      },
      receivebasissulfur: {
        required: "请输入含糖量!",
        range: $.validator.format("请输入一个介于0-50之间的数值!")

      },
      lowcalorificvalue: {
        required: "请输入酒精度数!",
        digits: "请输入正整数!",
        range: $.validator.format("请输入一个介于3000-7000之间的值!")
      },
      //airdrybasissulfur: {
      //  required: "请输入酒类指标1!",
      //  range: $.validator.format("请输入一个介于0.1-6!")
      //},
      demandAmount:{
        required: "请输入需求数量!",
        digits:"请输入正整数!",
        maxlength:"数量只能输入6位数!",
        min:"输入值不能小于1!"
      },
      contactName: {
        required: "请输入联系人!",
        minlength: "请输入有效的联系人姓名!",
        maxlength: "长度不能超过20位!"
      },
      phone: {
        required: "请输入手机号码!",
        minlength:"长度不足为11位!",
        maxlength: "长度不能超过11位!",
        mobileNum: "请输入有效的手机号码!"
      },
      companyName: {
        required: "请输入公司名称!",
        maxlength: "长度不能超过20位!"
      },
      verifyCode: {
        required: "请输入验证码!"
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
      if (element.attr("name") == "demandAmount"||element.attr("name") == "lowcalorificvalue"||element.attr("name") =="receivebasissulfur"||element.attr("name")=="airdrybasisvolatile") {
        error.insertAfter(element.parent().parent());
      }else if(element.attr("name")=="verifyCode"){
        $("#code").html("");
        $("#code").text("");
       $("#code").html(error);
      }else {
        error.insertAfter(element.parent());
      }
    }
  });
}

/****************************************************************************/
function dateCompare(startdate, enddate) {
  var arr = startdate.split("-");
  var startTime = (new Date(arr[0], arr[1], arr[2])).getTime();
  var arrs = enddate.split("-");
  var endTime = (new Date(arrs[0], arrs[1], arrs[2])).getTime();

  if (startTime >= endTime) {
    return false;
  }
  else {
    return true;
  }
}
