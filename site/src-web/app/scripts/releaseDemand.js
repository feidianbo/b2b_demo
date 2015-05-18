/**
 * Created by shiliang on 14-12-8.
 */
require(['jquery','bootstrap','bootstrap3-datetimepicker','CN','jquery.validation'],function($) {

  initOrg();
  function initOrg(){
    if($("#inspectionagency").val() == "其它"){
      $("#otherorgDisplay").css("display","block");
    }else{
      $("#otherorgDisplay").css("display","none");
    }
  }

  init();
  function init(){
    if($("#deliveryplace").val() == "其它"){
      $("#otherplaceDisplay").css("display","block");
    }else{
      $("#otherplaceDisplay").css("display","none");
    }
  }

  //获取系统当前时间
  //var nowDate = CurrentTime();
  //function CurrentTime()
  //{
  //  var now = new Date();
  //  var year = now.getFullYear();
  //  var month = now.getMonth() + 1;
  //  var day = now.getDate();
  //  var clock = year + "-";
  //  if(month < 10)
  //    clock += "0";
  //  clock += month + "-";
  //  if(day < 10)
  //    clock += "0";
  //  clock += day + " ";
  //  alert(clock);
  //  return clock;
  //}

  //初始化时间控件
  $('#datetimepicker1').datetimepicker({
    pickTime: false,
    language:'zh-cn'
  });

  //$("#datetimepicker1").on("dp.change",function (e) {
  //  $('#datetimepicker2').data("DateTimePicker").setMinDate(e.date);
  //});
  //
  //$("#datetimepicker2").on("dp.change",function (e) {
  //  $('#datetimepicker1').data("DateTimePicker").setMaxDate(e.date);
  //});

  $('#datetimepicker2').datetimepicker({
    pickTime: false,
    language:'zh-cn'
  });

  $('#datetimepicker3').datetimepicker({
    pickTime: false,
    language:'zh-cn'
  });

  $('#datetimepicker4').datetimepicker({
    pickTime: false,
    language:'zh-cn'
  });

  //时间比较
  if($("#datetimepicker4").length >0 ){
    $('#datetimepicker4').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker3').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker1').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker2').data("DateTimePicker").setMinDate(new Date());
  }
  //选择哪种提货方式显示对应的提货时间控件
  showGoodTime();
  function showGoodTime(){
    if($("#deliverymode").val() != "场地自提"){
        $("#defaultShow").css("display","none");
        $("#changeShow").css("display","block");
    }else{
        $("#defaultShow").css("display","block");
        $("#changeShow").css("display","none");
    }
  };

  //场地自提时改变提货时间
  $("#deliverymode").bind("change", function(){
    if($("#deliverymode").val() == "场地自提"){
      $("#defaultShow").css("display","block");
      $("#changeShow").css("display","none");
    }else{
      $("#defaultShow").css("display","none");
      $("#changeShow").css("display","block");
    }
  });

  check();

  //根据划区名称获取子集-获取省份,同时获取港口,联动效果
  $("#deliverydistrict").bind("change", function(){
    $.ajax({
      url:"/getProvincesByParentname",
      data:{name:$("#deliverydistrict").val()},
      success:function(data) {
        if (null != data) {
          $("#deliveryprovince").html("");
          var json = "";
          $.each(data, function (n, value) {
            json += "<option value="+value.name+">" + value.name + "</option>";
          });
          $("#deliveryprovince").html(json);

          if($("#deliveryprovince").val() == "河南省" || $("#deliveryprovince").val() == "山西省" || $("#deliveryprovince").val() == "内蒙古"){
            $("#otherplaceDisplay").css("display","block");
          }else{
            $("#otherplaceDisplay").css("display","none");}
            $.ajax({
              url:"/getPortsByParentname",
              data:{name:$("#deliveryprovince").val()},
              success:function(data) {
                if (null != data) {
                  $("#deliveryplace").html("");
                  var json = "";
                  $.each(data, function (n, value) {
                    json += "<option value="+value.name+">" + value.name + "</option>";
                  });
                  $("#deliveryplace").html(json);
                }
              }
            });
        }
      }
    });
  });

  //根据省份名称获取子集-获取港口
  $("#deliveryprovince").bind("change", function(){
    if($("#deliveryprovince").val() == "河南省" || $("#deliveryprovince").val() == "山西省" || $("#deliveryprovince").val() == "内蒙古"){
        $("#otherplaceDisplay").css("display","block");
    }else {
      $("#otherplaceDisplay").css("display", "none");
    }
      $.ajax({
        url:"/getPortsByParentname",
        data:{name:$("#deliveryprovince").val()},
        success:function(data) {
          if (null != data) {
            $("#deliveryplace").html("");
            var json = "";
            $.each(data, function (n, value) {
              json += "<option value="+value.name+">" + value.name + "</option>";
            });
            $("#deliveryplace").html(json);
          }
        }
      });
  });

  //发布按钮事件
  $("#releaseButton").bind("click",function(){
    //提货地点是其它时，对文本框的校验
    if($("#otherplaceDisplay").is(":visible")) {
      if ($("#deliveryplace").val() == "其它") {
        if ($("#otherplace").val() == "") {
          $("#otherplaceError").text("请输入提货地点");
          return;
        }
      }
      else {
        $("#otherplaceError").text("");
      }
    }
    //检验机构是其它时，对文本框的校验
    if($("#otherorgDisplay").is(":visible")){
      if($("#inspectionagency").val() == "其它"){
        if($("#otherorg").val() == ""){
          $("#otherorgError").text("请输入检验机构");
          return;
        }
      }
      else{
        $("#otherorgError").text("");
      }
    }

    if($("#quoteenddate").val() =="" || $("#quoteenddate").val() == null){
      $("#endTimeInfo").text("请填写截止时间").css("color","red");
      return;
    }
    //else if(dateCompare($("#quoteenddate").val(),nowDate) == true){
    //  $("#endTimeInfo").text("截止时间不能在当前时间之前!").css("color","red");
    //  return;
    //}
    else{
      $("#endTimeInfo").text("");
    }

    if($("#deliverymode").val() == "场地自提"){
      $("#deliverydate").val() == "";
      if($("#deliverydatestart").val()=="" || $("#deliverydateend").val()==null){
        $("#errorInfo").text("请填写提货时间").css("color","red");
        return;
      }
      else if(!dateCompare($("#deliverydatestart").val(),$("#deliverydateend").val())){
        $("#errorInfo").text("结束时间不能在开始时间之前!").css("color","red");
        return;
      } else if(days($("#quoteenddate").val(),$("#deliverydatestart").val()) < 3){
        $("#errorInfo").text("");
        $("#endTimeInfo").text("报价截止日至少要比提货时间早3天!").css("color","red");
        return;
      }
      else{
        $("#errorInfo").text("");
      }
    }else{
      $("#deliverydatestart").val() == "";
      $("#deliverydateend").val() == "";
      if($("#deliverydate").val() == ""){
        $("#timeInfo").text("请填写提货时间").css("color","red");
        return;
      }
      //else if(dateCompare($("#deliverydate").val(),nowDate)== true){
      //  $("#timeInfo").text("提货时间不能在当前时间之前!").css("color","red");
      //  return;
      //}
      else if(days($("#quoteenddate").val(),$("#deliverydate").val()) < 3){
          $("#timeInfo").text("");
          $("#endTimeInfo").text("报价截止日至少要比提货时间早3天!").css("color","red");
          return;
      }
      else{
        $("#timeInfo").text("");
      }
    }

    if(!check().form()) {
      return;
    }
    else{
      $.ajax({
        url:"/checkCompanyInfo",
        success:function(data){
          if(data  == "success"){
            $.ajax({
              url:"/checkDemand",
              data:$("#requirement_form").serialize(),
              success:function(data){
                if(data.success){
                  var id = data.demandcode;
                  location.href ="/buy/gotoCheck?demandcode="+id;
                }
              }
            });
          }else{
            if(data  == "improve"){
              $("#improve-companyInfo").modal('show');
              return;
            }else if(data == "verifying"){
              $("#verify-companyInfo").modal('show');
              return;
            }else if( data == "notPass"){
              $("#notPass-companyInfo").modal('show');
              return;
            }else if( data  == "notLogin"){
              location.href = "/login";
            }else{

            }
          }
        }
      });
    }
  });

  //取消发布按钮事件
  $("#releaseCancel").bind("click",function(){
      window.location.href="/buy";
  });

  //点击确认按钮触发的事件
  $("#confirm-announce").click(function(){
    $.ajax({
      url:'/saveDemand',
      data:{demandcode:$("#code").val()},
      success:function(data){
        if(data == "success"){
          $("#modal-checkSuccess").modal('show');
          $("#supply-close").click(function(){
            window.location.href="/account/getMyDemand";  //确认发布后跳转到需求信息列表页面
        });
        }
      }
    })
  });

  //更改供应发布信息(发布校验)
  $("#update-announce").click(function(){
    location.href="/buy/releaseDemand?demandcode="+$("#code").val();
  });

  //返回按钮事件
  $("#back-announce").bind("click",function(){
    window.location.href="/account/getMyDemand";
  });

  //个人中心查看的修改按钮
  $("#back-modify").bind("click", function(){
    window.location.href="/buy/releaseDemand?demandcode="+$("#code").val();
  });

 //检验机构为其它时的出现和隐藏
  $("#inspectionagency").change(function(){
    if($("#inspectionagency").val()== "其它" ){
      $("#otherorgDisplay").css("display","block");
    }
    else{
      $("#otherorgDisplay").css("display","none");
      $("#otherorgError").text("");
    }
  });

  //提货地点-港口为其它时的出现和隐藏
  $("#deliveryplace").change(function(){
    if($("#deliveryplace").val()== "其它" ){
      $("#otherplaceDisplay").css("display","block");
    }
    else{
      $("#otherplaceDisplay").css("display","none");
      $("#otherplaceError").text("");
    }
  });

  //(新增加)判断用户是否已经完善公司信息
  checkCompanyInfo();
});

//选择报价
function disabledBtn(obj,val){
  $("#modal-selectDialog").modal('show');
  $("#selectDialog_OK").bind("click", function(){
    $.ajax({
      url:'/quoteBid',
      data:{quoteid:val},
      success:function(data){
        if(data == "success"){
          $(obj).attr("disabled","disabled");
          $("#modal-selectDialog").modal('hide');
        }
      }
    })
  });
}

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
  jQuery.validator.addMethod("compare", function(value, element) {
    var firstValue = $("#TM").val();
    return this.optional(element) || eval(value) < eval(firstValue);
  }, "酒类指标4必须大于酒类指标5");
  return  $("#requirement_form").validate({
    onsubmit:false,
    onkeyup:false,
    focusInvalid:false,
    errorElement: 'span',
    errorClass: 'help-block',
    rules: {
      NCV: {
        required: true,
        digits:true,
        range:[3000,7000]
      },
      ADS: {
        range:[0.1,10],
        secCheck:true,
        onceCheck:true
      },
      RS: {
        required: true,
        onceCheck:true,
        secCheck:true,
        range:[0.1,9.99]
      },
      TM: {
        required: true,
        onceCheck:true,
        secCheck: true,
        range:[0.1,50]
      },
      IM: {
        onceCheck:true,
        secCheck:true,
        compare:true,
        range:[0.1,50]
      },
      ADV: {
        required: true,
        onceCheck:true,
        secCheck:true,
        range:[0.1,50]
      },
      RV: {
        range:[0.1,50],
        onceCheck:true,
        secCheck:true
      },
      AFT: {
        range:[900,1600],
        digits:true
      },
      ASH: {
        range:[0.1,50],
        secCheck:true,
        floatCheck:true
      },
      HGI:{
        range:[0.1,100],
        digits:true
      },
      deliveryplace: {
        required: true
      },
      deliverydate: {
        required: true
      },
      deliverydateend: {
        required: true
      },
      deliverydatestart:{
        required: true
      },
      demandamount: {
        required: true,
        digits:true,
        multiple:true,
        maxlength:6,
        min:1
      },
      unitprice: {
        required: true,
        digits:true,
        maxlength:4,
        min:1
      },
      inspectionagency:{
        required: true
      }
    },

    messages: {
      NCV: {
        required: "请输入酒精度数!",
        digits:"请输入正整数!",
        range: $.validator.format("请输入一个介于3000-7000之间的整数!")
      },
      ADS:{
        required: "请输入酒类指标1!",
        range: $.validator.format("请输入一个介于0-10之间的数值[不包括0]!")
      },
      RS:{
        required: "请输入含糖量!",
        range: $.validator.format("请输入一个介于0-10之间的数值[不包括0和10]!")
      },
      TM:{
        required: "请输入全水分!",
        range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
      },
      IM:{
        range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
      },
      ADV:{
        required: "请输入酒类指标2!",
        range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
      },
      RV:{
        range: $.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
      },
      AFT:{
        range: $.validator.format("请输入一个介于900-1600之间的整数!"),
        digits:"酒类指标7必须输入整数!"
      },
      ASH:{
        range:$.validator.format("请输入一个介于0-50之间的数值[不包括0]!")
      },
      HGI:{
        range:$.validator.format("请输入一个介于0-100之间的整数[不包括0]!"),
        digits:"酒类指标8必须输入整数!"
      },
      deliveryplace:{
        required: "请输入详细提货地点!"
      },
      deliverydate:{
        required: "请输入提货时间!"
      },
      deliverydatestart:{
        required: "请输入提货时间!"
      },
      deliverydateend:{
        required: "请输入提货时间!"
      },
      demandamount:{
        required: "请输入需求数量!",
        digits:"请输入正整数!",
        maxlength:"单价只能输入6位数!",
        min:"输入值不能小于1!"
      },
      unitprice:{
        required: "请输入单价!",
        digits:"请输入正整数!",
        maxlength:"单价只能输入4位数!",
        min:"输入值不能小于1!"
      },
      inspectionagency:{
        required: "请输入检验机构!"
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
  if(startTime >= endTime) {
    return false;
  }
  else{
    return true;
  }
}

//比较时间的天数
function days(startdate,enddate){
  var startTime = new Date(startdate.replace('-','/').replace('-','/')).getTime();
  var endTime = new Date(enddate.replace('-','/').replace('-','/')).getTime();
  return (endTime - startTime)/3600000/24;
}

function checkCompanyInfo(){
  $.ajax({
    url:"/checkCompanyInfo",
    success:function(data){
      switch(data){
        case "improve":
          $(".companyInfo_dialog").modal('show');
          $(".companyInfo_body").text("请完善公司信息!");
          $(".companyInfo_btn").attr("id","improve_companyInfo");
          //完善公司的确认按钮
          $("#improve_companyInfo").bind("click", function(){
            $(".companyInfo_dialog").modal('hide');
            location.href = "/account/getMyAccount";
          });
            break;
        case "verifying":
          $(".companyInfo_dialog").modal('show');
          $(".companyInfo_body").text("您的公司信息正在审核中,请您耐心等待!");
          $(".companyInfo_btn").attr("id","verify_companyInfo");
          //审核中公司的确认按钮
          $("#verify_companyInfo").bind("click", function(){
            $(".companyInfo_dialog").modal('hide');
            location.href = "/account/getMyAccount";
          });
            break;
        case "notPass":
          $(".companyInfo_dialog").modal('show');
          $(".companyInfo_body").text("公司认证审核未通过!");
          $(".companyInfo_btn").attr("id","notPass-companyInfo");
          //未通过审核的确认按钮
          $("#notPass-companyInfo").bind("click", function(){
            $(".companyInfo_dialog").modal('hide');
            location.href = "/account/getMyAccount";
          });
            break;
        default:
            break;
      }
    }
  });
}




