/**
 * Created by shiling on 14-11-25.
 */
require(['jquery','jquery.validation','bootstrap','pagination','bootstrap3-datetimepicker','tooltip','popover','CN'],function($) {
  if ($("#typeObj").val() == "mall" || $("#typeObj").val() == "buy") {
    $("#addOrder").show();
    $("html,body").animate({scrollTop: $("#addOrder").offset().top - 20}, 10);
  }
  if ($("#promiseChecked").val() == 1) {
    $("#totalPay").css("display", "none");
  }
  initData();
  $(".example").mouseover(function () {
    $(this).popover('show');
  }).mouseout(function () {
    $(this).popover("hide");
  });

  $(".address").mouseover(function(){
    if($(this).text().indexOf('...') == -1){
      $(this).popover('hide');
    }else{
      $(this).popover('show');
    }
  }).mouseout(function () {
    $(this).popover("hide");
  });

  initTag();
  initTagPrice();
  initPage();

  //初始化查询条件
  if($("#sortType").val()!=""){
    if($("#sortType").val()=='0'){
      $("#createTime").addClass('f-sort');
    }
    //else if($("#sortType").val()=='NCV'){
    //  $("#ncvNum").addClass('f-sort');
    //  $("#createtime").attr('class','');
    //}else if($("#sortType").val()=='RS'){
    //  $("#rsNum").addClass('f-sort');
    //  $("#createtime").removeClass('curr');
    //}
    else if($("#sortType").val()=='1' && $("#sequence").val()=='1'){
      $("#saleNum").addClass('f-sort');
      $("#quantity").attr('src','images/jiantou_up.png').css('display',"block");
      changeAmountStyle();
      $("#createtime").removeClass('curr');
    }else if($("#sortType").val()=='1' && $("#sequence").val()=='0'){
      $("#saleNum").addClass('f-sort');
      $("#quantity").attr('src','images/jiantou_down.png').css('display',"block");
      changeAmountStyle();
      $("#createtime").removeClass('curr');
    }else if($("#sortType").val()=='2' && $("#sequence").val()=='1'){
      $("#priceNum").addClass('f-sort');
      $("#price").attr('src','images/jiantou_up.png').css('display',"block");
      changePriceStyle();
      $("#createtime").removeClass('curr');
    }
    else if($("#sortType").val()=='2' && $("#sequence").val()=='0'){
      $("#priceNum").addClass('f-sort');
      $("#price").attr('src','images/jiantou_down.png').css('display',"block");
      changePriceStyle();
      $("#createtime").removeClass('curr');
    }
    else{
      //$("#rsNum").addClass('f-sort');
      $("#createtime").removeClass('curr');
    }
  }else{
    $("#createTime").addClass('f-sort');
  }

  function initData() {
    $('#datetimepicker').datetimepicker({
      pickTime: false,
      todayBtn: true,
      language: 'zh-cn'
    });
    $('#datetimepicker1').datetimepicker({
      pickTime: false,
      todayBtn: true,
      language: 'zh-cn'
    });
    $('#datetimepicker2').datetimepicker({
      pickTime: false,
      todayBtn: true,
      language: 'zh-cn'
    });
  }

  $("#delegate-close").click(function () {
    $("#modal-delegateInfo").modal('hide');
    window.location.href = "/buy";
  });

//根据区域筛选
  $("#region_select").change(function(){
    $.ajax({
      url:'/mall/getProvinces',
      data:{id:$("#region_select").val()},
      success:function(data){
        $("#province_select").empty();
        var jsonStr="";
        $.each(data, function (i, item) {
          jsonStr += "<option value="+item.id+">" + item.name + "</option>";
        });
        $("#province_select").html(jsonStr);
        $.ajax({
          url:'/mall/getPorts',
          data:{id:data[0].id},
          success:function(data){
            $("#deliveryplace").empty();
            var jsonStr="";
            $.each(data, function (i, item) {
              if( i != data.length){
                jsonStr += '<option value='+item.id+'>' + item.name + '</option>';
              }
            });
            $("#harbour_select").html(jsonStr);
            saveCondition($('#mallType').val());
          }
        });
      }
    });
  });
  //根据省份查询港口及筛选
  $('#province_select').change(function () {
    $.ajax({
      url: '/mall/getPorts',
      data: {id: $('#province_select').val()},
      success: function (data) {
        $("#harbour_select").empty();
        var jsonStr = "";
        $.each(data, function (i, item) {
          if (i != data.length) {
            jsonStr += '<option value=' + item.id + '>' + item.name + '</option>';
          }
        });
        $("#harbour_select").html(jsonStr);
        saveCondition($('#mallType').val());
      }
    });
  });

  //根据港口筛选
  $('#harbour_select').change(function () {
    saveCondition($('#mallType').val());
  });

  //根据酒类筛选
  $("#coalType_select").change(function(){
    saveCondition($('#mallType').val());
  });
  $("#changeInfo").click(function () {
    window.location.href = "/account/getMyAccount";
  });

  $("#updateInfo").click(function () {
    window.location.href = "/account/getMyAccount";
  });

  $("#modifyInfo").click(function () {
    window.location.href = "/account/getMyAccount";
  });

  $("#smallNCV").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  })
  $("#smallNCV").blur(function () {
    checkNCV(this);
  })
  $("#bigNCV").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  })
  $("#bigNCV").blur(function () {
    checkNCV(this);
  })

  function checkNCV(obj) {
    var reg=/^[1-9]+[0-9]*]*$/;
    if(obj.value==""){
      $(obj).popover('hide');
      return true;
    }
    else if(!reg.test(obj.value)){
      $(obj).addClass('noneSelectedStyle');
      $(obj).popover('show');
      return false;
    }
    $(obj).removeClass('noneSelectedStyle');
    $(obj).popover('hide');
    return true;
  }

  $("#smallRS").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  })
  $("#smallRS").blur(function () {
    checkRS(this);
  })
  $("#bigRS").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  })
  $("#bigRS").blur(function () {
    checkRS(this);
  });

  function checkRS(obj){
    var rsReg=/(^[1-9]$)|((^[0-9]).\d{1,2}$)/;
    if(obj.value==""){
      $(obj).popover('hide');
      return true;
    }
    else if (!rsReg.test(obj.value)) {
      $(obj).addClass('noneSelectedStyle');
      $(obj).popover('show');
      return false;
    }
    else if (obj.value >= 10 || obj.value == 0.00) {
      $(obj).addClass('noneSelectedStyle');
      $(obj).popover('show');
      return false;
    }
    $(obj).removeClass('noneSelectedStyle');
    $(obj).popover('hide');
    return true;
  }

  function validate_NCV_RS () {
    return checkNCV($("#smallNCV")[0]) & checkNCV($("#bigNCV")[0]) & checkRS($("#smallRS")[0]) & checkRS($("#bigRS")[0]);
  }



//搜索
  $("#btn_search").click(function() {
    if (!validate_NCV_RS()) {
      return;
    }
    initData();
    initVariable();
    location.href="/buy" + '?' + initVariable();
  });

  $("#btn_searchMall").click(function () {
    if (!validate_NCV_RS()) {
      return;
    }
    initData();
    initVariable();
    location.href="/mall" + '?' + initVariable();
    location.href = '/mall?region='+region+'&province=' + province + '&harbour=' + harbour +'&coaltype='+coalType+ '&NCV01=' + NCV01 +
    '&NCV02=' + NCV02 + '&RS01=' + RS01 + '&RS02=' + RS02 + '&page=1'+'&sorttype='+sorttype+'&sequence='+sequence+'&tag='+result+'&tagPrice='+resultPrice;
  });

  //用户关注商品
  $("#mallAttend").click(function () {
    $("#source").val("mallAttend");
    attendProduct();
  });

  $("#announce").click(function () {
    window.location.href = "/buy/releaseDemand";
  });

  $("#buyAttend").click(function () {
    $("#source").val("buyAttend");
    attendProduct();
  });

  //认购
  $("#subscribe").click(function () {
    $("#source").val("mall");
    //判断商品是否已销售完
    $.ajax({
      url: "/doCheckLogin",
      success: function (data) {
        if (data) {
          if (eval($("#availableQuantity").val().replace(",", "")) <= 0) {
            $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand'>发布需求</a>或<a href='/manualsell_in'>委托人工找货</a>");
          } else {
            $.ajax({
              url: "/checkAvailableAmount",
              data: {id: $("#idText").val().replace(/\,/g, ""), amount: 1},
              success: function (data) {
                if (data) {
                  buygoodsclick();
                } else {
                  $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand'>发布需求</a>或<a href='/manualsell_in'>委托人工找货</a>");
                }
              }
            })
          }
        } else {
          $("#modal-login").modal('show');
        }
      }
    })
  });

  //委托下单按钮点击事件
  $("#delegateOrder").click(function () {
    $("#source").val("buy");
    $.ajax({
      url: "/doCheckLogin",
      success: function (data) {
        if (data) {
          $.ajax({
            url: "/doCheckSeller",
            data:{id: $("#idText").val().replace(/\,/g, "")},
            success: function(data){
              if(data){
                if (eval($("#availableQuantity").val().replace(",", "")) <= 0) {
                  $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand'>发布需求</a>或<a href='/manualsell_in'>委托人工找货</a>");
                } else {
                  $.ajax({
                    url: "/checkAvailableAmount",
                    data: {id: $("#idText").val().replace(/\,/g, ""), amount: 1},
                    success: function (data) {
                      if (data) {
                        buygoodsclick();
                      } else {
                        $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand'>发布需求</a>或<a href='/manualsell_in'>委托人工找货</a>");
                      }
                    }
                  });
                }
              } else{
                $("#infoPrompt").text("不能购买自己公司发布的产品！").css("color", "red");
              }
            }
          })
        } else {
          $("#modal-login").modal('show');
        }
      }
    })
  });

  $("#num").blur(function () {
    var regluar = /^[1-9]+[0-9]*]*$/;
    if ($("#num").val() == "") {
      $("#errorInfo").text("请填写您要认购的瓶数").css("color", "red");
      clearContent();
      return;
    } else if (!regluar.test($("#num").val())) {
      $("#errorInfo").text("认购瓶数为正整数!").css("color", "red");
      $("#num").val("");
      clearContent();
      return;
    } else if ($("#num").val().toString().length > 6) {
      $("#errorInfo").text("认购瓶数在6位整数内!").css("color", "red");
      $("#num").val("");
      clearContent();
      return;
    } else if (($("#num").val().toString() % 50) != 0) {
      $("#errorInfo").text("认购瓶数必须是50的整数倍!").css("color", "red");
      $("#num").val("");
      clearContent();
      return;
    } else {
      $.ajax({
        url: '/checkAvailableAmount',
        data: {id: $("#idText").val().replace(/\,/g, ""), amount: $("#num").val()},
        success: function (data) {
          if (!data) {
            $("#errorInfo").text("认购瓶数不能超过卖家的可销售库存").css("color", "red");
            $("#num").val("");
            clearContent();
            return;
          } else {
            $("#errorInfo").text("");
            var unitPrice = eval($("#unitPrice").val().replace(/\,/g, ""));
            if (unitPrice > 0) {
              $("#signalPrice").text(unitPrice).css("color", "red");
              if($("#payWay").is(":visible")){
                $("#signMoney").text("￥" + formatMoney(eval($("#unitPrice").val()) * $("#num").val(), 2)).css("color", "red");
              }
              $("#totalMoney").text("￥" + formatMoney(unitPrice * $("#num").val(), 2)).css("color", "red");
              $("#calMoney").val(formatMoney(unitPrice * $("#num").val(), 2));
            } else {
              $.ajax({
                url: '/getjtj',
                data: {amount: $("#num").val(), id: $("#idText").val().replace(/\,/g, "")},
                success: function (data) {
                  $("#signalPrice").text(data).css("color", "red");
                  if($("#payWay").is(":visible")){
                    $("#signMoney").text("￥" + formatMoney(eval($("#unitPrice").val()) * $("#num").val(), 2)).css("color", "red");
                  }
                  $("#totalMoney").text("￥" + formatMoney(data * $("#num").val(), 2)).css("color", "red");
                }
              });
            }
          }
        }
      })
    }
  });
  $("#type").change(function () {
    if ($("#type").val() == "场地自提") {
      $("#cdzt").slideDown();
      $("#fcdzt").slideUp();
    } else {
      $("#fcdzt").slideDown();
      $("#cdzt").slideUp();
    }
  });

  //取消认购
  $("#cancelBuy").click(function () {
    window.location.href = "/buy";
  });

  $("#datetimepicker2").on("dp.change", function (e) {
    var deliverytime1;
    var time = $("#time").val();
    if ($("#datetimepicker2").length > 0) {
      if ($("#deliveryWay").val() == '场地自提') {
        $('#datetimepicker1').data("DateTimePicker").setMinDate(new Date());
        $('#datetimepicker1').data("DateTimePicker").setMaxDate(time);
      }
      $('#datetimepicker2').data("DateTimePicker").setMinDate(new Date());
      $('#datetimepicker2').data("DateTimePicker").setMaxDate(time);
    }
    if ($("#signalPrice").text() == '') {
      $("#totalMoney").text("￥0.00").css("color", 'red');
    }
    else {
      if ($("#deliveryWay").val() == '到岸舱底') {
        $("#payWay").css("display", "none");
      } else if ($("#deliveryWay").val() == '港口平仓') {
        $("#payWay").css("display", "block");
        deliverytime1 = $("#deliverytime").val();
        calculateDate(deliverytime1);
      } else {
        $("#payWay").css("display", "block");
        deliverytime1 = $("#deliverytime1").val();
        calculateDate(deliverytime1);
      }
    }
  });

  function calculateDate(deliverytime1) {
    $.ajax({
      async: false,
      url: '/check7days',
      data: {deliverytime1: deliverytime1},
      success: function (data) {
        if (data) {
          $("#payWay").css("display", "none");  //7天以内
          if ($("input[name='payWayRadio']:checked").val() == 1) {
            var obj = document.getElementsByName('payWayRadio');
            obj[0].checked = true;
            obj[1].checked = false;
          }
          $("#promiseMoney").css("display", "none");
          $("#totalPay").css("display", "block");
        } else {
          $("#payWay").css("display", "block");
        }
      }
    });
    $("#totalMoney").text("￥" + formatMoney(eval($("#signalPrice").text()) * $("#num").val(), 2)).css("color", "red");
    $("#calMoney").val(formatMoney(eval($("#signalPrice").text()) * $("#num").val(), 2)).css("color", "red");
  }

  $("#datetimepicker").on("dp.change", function (e) {
    $.ajax({
      asyn: false,
      url: '/check7days',
      data: {deliverytime1: $("#deliverytime1").val()},
      success: function (data) {
        if (data) {
          $("#totalPay").css("display", "block");
          $("#promiseMoney").css("display", "none");
        } else {
          $("#payWay").css("display", "block");
        }
      }
    });
  });

  //下单
  $("#ordered").click(function () {
    addOrders();
  });

  //下单(一口价)
  function order2(deliverytime1, deliverytime2) {
    $.ajax({
      url: '/buy/addOrder',
      data: {
        id: $("#idText").val().replace(/\,/g, ""),
        amount: $("#num").val(),
        deliverytime1: deliverytime1,
        deliverytime2: deliverytime2,
        paytype: $("input[name='payWayRadio']:checked").val()
      },
      success: function (data) {
        if (data.result.success) {
          if ($("#saleType").val() == "自营") {
            location.href = "/mall/OrderInfo?id=" + data.id;
          } else {
            $("#modal-delegateInfo").modal('show');
            $("#delegate-close").click(function () {
              $("#modal-delegateInfo").modal('hide');
              location.href = "/buy";
            });
          }
        } else {
          $.each(data.result.errors, function (index, error) {
            $("#errorInfo").text(error.defaultMessage).css("color", "red");
          });
        }
      }
    });
  }

  function order(deliverytime1) {
    $.ajax({
      url: '/buy/addOrder',
      data: {
        id: $("#idText").val().replace(/\,/g, ""),
        amount: $("#num").val(),
        price: $("#signalPrice").text(),
        //deliverymode: $("#type").val(),
        deliverytime1: deliverytime1,
        paytype: $("input[name='payWayRadio']:checked").val()
      },
      success: function (data) {
        if (data.result.success) {
          if ($("#saleType").val() == "自营") {
            location.href = "/mall/OrderInfo?id=" + data.id;
          } else {
            $("#modal-delegateInfo").modal('show');
            //跳转到"供应信息列表"
            $("#delegate-close").click(function () {
              $("#modal-delegateInfo").modal('hide');
              location.href = "/buy";
            });
          }
        } else {
          $.each(data.result.errors, function (index, error) {
            $("#errorInfo").text(error.defaultMessage).css("color", "red");
          });
        }
      }
    });
  }

  function buygoodsclick(){
    var flag = false;
    $.ajax({
      async: false,
      url: '/doCheckCompany',
      success: function (data) {
        if (!data.success) {
          switch (data.error) {
            case "lackinfo":
              $(".my_buy").modal('show');
              $('.my_buyBody').text("您的公司信息不完整,请完善!");
              $('.my_buyButton').attr("id", "changeInfo_btn");
              $("#changeInfo_btn").bind("click", function () {
                window.location.href = "/account/getMyAccount";
              });
              break;
            case "notpass":
              $(".my_buy").modal('show');
              $('.my_buyBody').text("您的公司信息未通过审核!");
              $('.my_buyButton').attr("id", "updateInfo_btn");
              $("#updateInfo_btn").bind("click", function () {
                window.location.href = "/account/getMyAccount";
              });
              break;
            case "verifying":
              $(".my_buy").modal('show');
              $('.my_buyBody').text("您的公司信息正在审核中,请您耐心等待!");
              $('.my_buyButton').attr("id", "updateInfoVerifying_btn");
              $("#updateInfoVerifying_btn").bind("click", function () {
                window.location.href = "/account/getMyAccount";
              });
              break;
            default:
              flag = true;
          }
          return flag;
        }
        else {
          $("#addOrder").show();
          $("html,body").animate({scrollTop: $("#addOrder").offset().top - 20}, 10);
          if ($("#type").val() == "场地自提") {
            $("#cdzt").css("display", "block");
            $("#fcdzt").css("display", "none");
          } else {
            $("#cdzt").css("display", "none");
            $("#fcdzt").css("display", "block");
          }
          flag = true;
        }
      }
    });
    return flag;
  }

  function addOrders() {
    var deliverytime2 = "";
    var deliverytime1 = "";

    if ($("#num").val() == "") {
      $("#errorInfo").text("请填写您要认购的瓶数").css("color", "red");
    } else if ($("#num").val().toString().length > 6) {
      $("#errorInfo").text("认购瓶数在6位整数内!").css("color", "red");
      return;
    } else {
      $.ajax({
        url: '/buy/checkAmount',
        data: {buyNum: $("#num").val(), id: $("#idText").val().replace(/\,/g, "")},
        success: function (data) {
          if (data) {
            $("#errorInfo").text("");
            if ($("#type").val() == "场地自提") {
              if ($("#deliverytime1").val() == "" || $("#deliverytime2").val() == "") {
                $("#errorInfo").text("请填写提货时间").css("color", "red");
              } else {
                $("#errorInfo").text("");
                deliverytime1 = $("#deliverytime1").val();
                deliverytime2 = $("#deliverytime2").val();
                order2(deliverytime1, deliverytime2);
              }
            } else {
              if ($("#deliverytime").val() == "") {
                $("#errorInfo").text("请填写提货时间").css("color", "red");
              } else {
                deliverytime1 = $("#deliverytime").val();
                order(deliverytime1);
              }
            }
          } else {
            $("#errorInfo").text("认购瓶数不能超过卖家的可销售库存").css("color", "red");
            return;
          }
        }
      })
    }
  }

  $("#release_supply").bind("click", function () {
    var url = "/buy/releaseDemand";
    $("#gotoUrl").val(url);
    $.ajax({
      url: "/doCheckLogin",
      success: function (data) {
        if (data) {
          window.location.href = url;
        } else {
          $("#modal-login").modal('show');
        }
      }
    });
  });

  function attendProduct(){
    $.ajax({
      url: "/doCheckLogin",
      success: function (data) {
        if (data) {
          if (eval($("#availableQuantity").val().replace(",", "")) <= 0) {
            $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand' style='color:#317ee6;'>发布需求</a>或<a href='/manualsell_in' style='color:#317ee6;'>委托人工找货</a>");
          } else {
            $.ajax({
              url: '/checkAvailableAmount',
              data: {id: $("#idText").val().replace(/\,/g,""), amount: 1},
              success: function (data) {
                if (data) {
                  $.ajax({
                    url: '/buy/addInterest',
                    data: {id: $("#idText").val().replace(/\,/g,""), type: "supply"},
                    success: function (data) {
                      if (data) {
                        $("#modal-attendSuccess").modal('show');
                      } else {
                      }
                    }
                  })
                } else {
                  $("#infoPrompt").html("该商品已售罄，您可以<a href='/buy/releaseDemand' style='color:#317ee6;'>发布需求</a>或<a href='/manualsell_in' style='color:#317ee6;'>委托人工找货</a>");
                }
              }
            })
          }
        } else {
          $("#modal-login").modal('show');
        }
      }
    })
  }

  function clearContent(){
    $("#singalPrice").text("");
    $("#totalMoney").text("");
  }
});



//金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数
function formatMoney(s, n) {
  n = n > 0 && n <= 20 ? n : 2;
  s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
  var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
  t = "";
  for (i = 0; i < l.length; i++) {
    t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
  }
  return t.split("").reverse().join("") + "." + r;
}

//打开新的页面显示详情
function openNewPage(id, seller) {
  if (seller == '自营') {
    window.open("/mall/info?id=" + id, '_blank');
  } else {
    window.open("/buy/info?id=" + id, '_blank');
  }
}

function radio_click(obj) {
  if (obj.value == 1) {
    $("#promiseMoney").css('display', 'block');
    $("#totalPay").css('display', 'none');
    if ($('#num').val() == "") {
      $("#signMoney").text('￥0.00').css("color", "red");
    } else {
      if ($('#allMoney').val() == "") {
        if ($("#calMoney").val().indexOf(",") != -1) {
          $("#signMoney").text("￥" + formatMoney(parseFloat(parseInt($("#calMoney").val().replace(/,/g, "")), 2) * 0.1), 2).css("color", "red");
        } else {
          $("#signMoney").text("￥" + formatMoney(parseFloat(parseInt($("#calMoney").val().replace(/,/g, "")), 2) * 0.1), 2).css("color", "red");
        }
      }
      else {
        $("#signMoney").text("￥" + formatMoney(parseFloat(parseInt($("#allMoney").val().replace(/,/g, "")), 2) * 0.1), 2).css("color", "red");
      }
    }
  } else {
    $("#promiseMoney").css('display', 'none');
    $("#totalPay").css('display', 'block');
  }
}

function filterRS(event) {
  //只能输入数字和小数点
  if(((event.keyCode<48 && event.keyCode!=46) || event.keyCode>57)){
    event.returnValue=false;
  }else{
    event.returnValue=true;
  }
}
function filterNCV(event){
  if((event.keyCode<48 || event.keyCode>57)){
    event.returnValue=false;
  }else{
    event.returnValue=true;
  }
}

function changeAmountStyle(){
  $("#createTime").removeClass('curr');
  $('#saleNum').addClass('f-sort');
  $("#priceNum").css('padding-right','10px');
  $("#priceNum").removeClass('f-sort');
  $("#saleNum").css('padding-right','17px');
}
function changePriceStyle(){
  $("#createTime").removeClass('curr');
  $('#priceNum').addClass('f-sort');
  $("#saleNum").css('padding-right','10px');
  $("#saleNum").removeClass('f-sort');
  $("#priceNum").css('padding-right','17px');
}
function changeOtherStyle(){
  $("#priceNum").attr('class','').css('padding-right','10px');
  $("#saleNum").attr('class','').css('padding-right','10px');
}

var result=0;
function initTag(){
  if($("#selectedTag").val() != 0){
    result = Number($("#selectedTag").val());
  }
}
var resultPrice=0;
function initTagPrice(){
  if($("#resultPrice").val() != 0){
    resultPrice = Number($("#resultPrice").val());
  }
}

var page=1;
function initPage(){
  if($("#currentPage").val() != 1){
    page=$("#currentPage").val();
  }
}

function getParams(page,sortType,sequence,tag,tagPrice) {
  var params =  {region: $("#region_select").val(),
    province:$("#province_select").val(),
    harbour:$("#harbour_select").val(),
    coaltype:$("#coalType_select").val(),
    NCV01:$("#smallNCV").val(),
    NCV02:$("#bigNCV").val(),
    RS01: $("#smallRS").val(),
    RS02:$("#bigRS").val(),
    page:page,
    sorttype:sortType,
    sequence:sequence,
    tag:tag,
    tagPrice:tagPrice};
  return $.param(params);
}

function searchByAmount(){
  if(result == 0){
    result+=1;
  }else{
    result -=1;
  }
  $("#createTime").attr('class','');
  $("#priceNum").attr('class','');
  //$("#ncvNum").attr('class','');
  //$("#rsNum").attr('class','');
  $("#price").css('display','none');
  var quantityUrlContext='';
  if(result%2==0){
    $("#quantity").attr('src','images/jiantou_down.png').css('display',"block");
    changeAmountStyle();
    if($("#mallType").val()=='mall'){
      quantityUrlContext='/mall'
      location.href=quantityUrlContext + '?' + getParams(page,'1','0',result,resultPrice);
    }else{
      quantityUrlContext='/buy';
      location.href=quantityUrlContext + '?' + getParams(page,'1','0',result,resultPrice);
    }
  }else{
    $("#quantity").attr('src','images/jiantou_up.png').css('display',"block");
    changeAmountStyle();
    if($("#mallType").val()=='mall'){
      quantityUrlContext='/mall';
      location.href=quantityUrlContext + '?' + getParams(page,'1','1',result,resultPrice);
    }else{
      quantityUrlContext='/buy';
      location.href=quantityUrlContext + '?' + getParams(page,'1','1',result,resultPrice);
    }
  }
}


function searchByPrice(){
  if(resultPrice == 0){
    resultPrice+=1;
  }else{
    resultPrice -=1;
  }
  $("#createTime").attr('class','');
  $("#quantity").css('display','none');
  //$("#ncvNum").attr('class','');
  //$("#rsNum").attr('class','');
  $("#saleNum").attr('class','');
  var quantityUrlContext = '';
  if(resultPrice%2==0){
    $("#price").attr('src','images/jiantou_down.png').css('display',"block");
    changePriceStyle();
    if($("#mallType").val()=='mall'){
      quantityUrlContext='/mall';
      location.href=quantityUrlContext + '?' + getParams(page,'2','0',result,resultPrice);
    }else{
      quantityUrlContext='/buy';
      location.href=quantityUrlContext + '?' + getParams(page,'2','0',result,resultPrice);
    }
  }else{
    $("#price").attr('src','images/jiantou_up.png').css('display',"block");
    changePriceStyle();
    if($("#mallType").val()=='mall'){
      quantityUrlContext='/mall';
      location.href=quantityUrlContext + '?' + getParams(page,'2','1',result,resultPrice);
    }else{
      quantityUrlContext='/buy';
      location.href=quantityUrlContext + '?' + getParams(page,'2','1',result,resultPrice);
    }
  }
}

function searchByTime(){
  var timeUrlContext = '';
  //$("#ncvNum").attr('class','');
  //$("#rsNum").attr('class','');
  $('#createTime').addClass('curr');
  changeOtherStyle();
  var paramsByTime = getParams(page,'0','','','');
  if ($("#mallType").val() == 'mall') {
    timeUrlContext = '/mall';
  }else{
    timeUrlContext = '/buy';
  }
  location.href = timeUrlContext + '?' + paramsByTime;
}
//function searchByNCV(){
//  var ncvUrlContext = '';
//  $("#createTime").attr('class','');
//  $("#rsNum").attr('class','');
//  $('#ncvNum').addClass('f-sort');
//  changeOtherStyle();
//  var paramsByNCV = getParams(page,'NCV','','','');
//  if($("#mallType").val()=='mall'){
//    ncvUrlContext = '/mall';
//  }else{
//    ncvUrlContext = '/buy';
//  }
//  location.href = ncvUrlContext + '?' + paramsByNCV;
//}
//function searchByRS() {
//  var rsUrlContext = '';
//  $("#ncvNum").attr('class', '');
//  $("#createTime").attr('class', '');
//  $('#rsNum').addClass('f-sort');
//  changeOtherStyle();
//  var paramsByRS = getParams(page,'RS','','','');
//  if ($("#mallType").val() == 'mall') {
//    rsUrlContext = '/mall';
//  } else {
//    rsUrlContext = '/buy';
//  }
//  location.href = rsUrlContext + '?' + paramsByRS;
//}
function saveCondition(obj){
  if(obj =='mall'){
    location.href="/mall" + '?' + initVariable();
  }else {
    location.href = "/buy" + '?' + initVariable();
  }
}

function initVariable(){
  var sorttype='';
  var sequence='';
  var resultPrice='';
  var result='';
  if($("#createTime").attr('class')=='f-sort'){
    sorttype='0';
    sequence='';
    resultPrice='';
    result='';
  }
  //else if($("#ncvNum").attr('class')=='f-sort'){
  //  sorttype='NCV';
  //  sequence='';
  //  resultPrice='';
  //  result='';
  //}
  //else if($("#rsNum").attr('class')=='f-sort'){
  //  sorttype='RS';
  //  sequence='';
  //  resultPrice='';
  //  result='';
  //}
  else if($("#saleNum").attr('class')=='f-sort' && $('#quantity').attr('src')=='images/jiantou_down.png'){
    sorttype='1';
    sequence=0;
    result=0;
    resultPrice=0;
  }else if($("#saleNum").attr('class')=='f-sort' && $('#quantity').attr('src')=='images/jiantou_up.png'){
    sorttype='1';
    sequence=1;
    result=1;
    resultPrice=0;
  }else if($("#priceNum").attr('class')=='f-sort' && $('#price').attr('src')=='images/jiantou_down.png'){
    sorttype='2';
    sequence=0;
    result=1;
    resultPrice=0;
  }else if($("#priceNum").attr('class')=='f-sort' && $('#price').attr('src')=='images/jiantou_up.png') {
    sorttype = '2';
    sequence = 1;
    result = 1;
    resultPrice = 1;
  }else{}
  return getParams(page,sorttype,sequence,result,resultPrice);
}
