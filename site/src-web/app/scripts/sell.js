require(['jquery','bootstrap','tooltip','popover'],function($) {


  //显示交易员的姓名和手机号
  $(".example").mouseover(function(){
    $(this).popover('show');
  }).mouseout(function(){
    $(this).popover("hide");
  });

  $(".showDatail").mouseover(function(){
    $(this).popover('show');
  }).mouseout(function(){
      $(this).popover("hide");
    });

  //初始化控件
 // initFirHeat();
 // initFirSulfur();

  function initFirHeat(){
    if($("#firHeat").val() == "0") {
      $("#secHeat").css("display", "none");
      $("#label_heat").css("display", "none");
    }
    else{
      $("#label_heat").css("display","inline-block");
      $("#secHeat").css("display", "inline-block");
    }
  };

  function initFirSulfur(){
    if($("#firSulfur").val() == "0") {
      $("#label_sulfur").css("display", "none");
      $("#secSulfur").css("display", "none");

    }else{
      $("#label_sulfur").css("display","inline-block");
      $("#secSulfur").css("display", "inline-block");
    }
  };

 /* $("#firHeat").bind("change",function(){
    var firstHeat = $("#firHeat").val();
      initFirHeat();
      $.ajax({
        url:"/getHighHeatValue",
        data:{lowHotValue:firstHeat},
        success:function(data) {
          if (null != data) {
            $("#secHeat").html("");
            var jsonStr = "";
            $.each(data, function (n, value) {
              if( n != data.length - 1){
                jsonStr += "<option value="+value+">" + value + "</option>";
              }else{
                jsonStr += "<option value='10000'>" + value + "</option>";
              }
            });
            $("#secHeat").html(jsonStr);
          }
        }
      });
  });*/

  /*$("#firSulfur").bind("change",function(){
    initFirSulfur();
    var firSulfur = $("#firSulfur").val();
    $.ajax({
      url:"/getHighSulfurContent",
      data:{lowSulfurContent:firSulfur},
      success:function(data){
        if(null != data){
          $("#secSulfur").html("");
          var jsonStr = "";
          $.each(data, function (n, value) {
            if( n != data.length - 1){
              jsonStr += "<option value="+value+">" + value + "</option>";
            }else{
              jsonStr += '<option value="10">' + value + '</option>';
            }
          });
          $("#secSulfur").html(jsonStr);
        }
      }
    });
  });*/

  $("#smallNCV").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  });
  $("#smallNCV").blur(function () {
    checkNCV(this);
  });
  $("#bigNCV").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  });
  $("#bigNCV").blur(function () {
    checkNCV(this);
  });

  function checkNCV(obj) {
    var reg=/^[1-9]+[0-9]*]*$/;
    if(obj.value==""){
      $(obj).popover('hide');
      return true;
    }
    //else if (obj.value.length != 4) {
    //  $(obj).addClass('noneSelectedStyle');
    //  $(obj).popover('show');
    //  return false;
    //}
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
  });
  $("#smallRS").blur(function () {
    checkRS(this);
  });
  $("#bigRS").focus(function(){
    $(this).removeClass('noneSelectedStyle');
  });
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

  $("#btnSearch").bind("click",function(){
    if (!validate_NCV_RS()) {
      return;
    }
    var coaltype = $("#coalType_select").val();
    var area = $("#area_select").val();
    var province = $("#province_select").val();
    var port = $("#port_select").val();
    var selectFirHeat = $("#smallNCV").val();
    var selectSecHeat = $("#bigNCV").val();
    var selectFirSulfur = $("#smallRS").val();
    var selectSecSulfur = $("#bigRS").val();

    //initFirHeat();
    //initFirSulfur();

    if($("#firHeat").val() == "0"){
      selectSecHeat ="10000";
    }
    if($("#firSulfur").val() == "0"){
      selectSecSulfur ="10";
    }

    location.href='/sell?coaltype='+coaltype +'&area='+area +'&province='+province+'&port='+port +'&lowHotValue=' + selectFirHeat +
    '&highHotValue='+selectSecHeat +'&lowSulfurContent='+selectFirSulfur +'&highSulfurContent='+selectSecSulfur+'&page=1';
  });

  $("#close_quote").bind("click",function(){
    $("#modal-quote").modal('hide');
  });

//发布供应信息
  $("#release_supply").bind("click",function(){
    var url = "/sell/createSupply";
    $("#gotoUrl").val(url);
    $.ajax({
      url: "/doCheckLogin",
      success: function (data) {
        if(data){
          window.location.href=url;
        } else {
          $("#modal-login").modal('show');
        }
      }
    });
  });

  //根据划区名称获取子集-获取省份,同时获取港口,联动效果
  $("#area_select").bind("change", function(){
    if($("#area_select").val() != "0") {
      $.ajax({
        url: "/getProvincesByParentid",
        data: {id: $("#area_select").val()},
        success: function (data) {
          if (null != data) {
            $("#province_select").html("");
            var json = "";
            json += "<option value=\"0\">全部</option>";
            $.each(data, function (n, value) {
              json += "<option value=" + value.id + ">" + value.name + "</option>";
            });

            $("#province_select").html(json);

            var portsContent = "<option value=\"0\">全部</option>";
            $("#port_select").html(portsContent);

            gotoCheck();
          }
        }
      });
    }else {
      $.ajax({
        url: "/getProvincesByParentid",
        data: {id: $("#area_select").val()},
        success: function (data) {
          if (null != data) {
            $("#province_select").html("");
            var json = "";
            json += "<option value=\"0\">全部</option>";
            $.each(data, function (n, value) {
              json += "<option value=" + value.id + ">" + value.name + "</option>";
            });

            $("#province_select").html(json);

            var portsContent = "<option value=\"0\">全部</option>";
            $("#port_select").html(portsContent);

            gotoCheck();
          }
        }
      });
    }
  });

  $("#coalType_select").bind("change",function(){
    gotoCheck();
  });

  //根据省份名称获取子集-获取港口
  $("#province_select").bind("change", function(){
    if($("#province_select").val() != "0") {
      $.ajax({
        url: "/getPortsByParentid",
        data: {id: $("#province_select").val()},
        success: function (data) {
          if (null != data) {
            $("#port_select").html("");
            var json = "";
            json += "<option value=\"0\">全部</option>";
            $.each(data.portsList, function (n, value) {
              json += "<option value=" + value.id + ">" + value.name + "</option>";
            });
            $("#port_select").html(json);
            //$("#area_select").find("option[value ='"+data.area.id+"']").attr("selected",true);需求改变
            gotoCheck();

          }
        }
      });
    }else{
      var portsContent = "<option value=\"0\">全部</option>";
      $("#port_select").html(portsContent);
      gotoCheck();
    }
  });

  $("#port_select").bind("change",function(){
    gotoCheck();
  });

  //给创建时间附上样式
 /* $("#createTime").addClass("f-sort");
  orderByStyle();*/
});

function showId(id){
  window.open("/sell/gotoQuote?id="+id+"&reqsource=quote",'_blank');
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

function gotoCheck(){
  var coaltype = $("#coalType_select").val();
  var area = $("#area_select").val();
  var province = $("#province_select").val();
  var port = $("#port_select").val();
  var selectFirHeat = $("#smallNCV").val();
  var selectSecHeat = $("#bigNCV").val();
  var selectFirSulfur = $("#smallRS").val();
  var selectSecSulfur = $("#bigRS").val();

  location.href='/sell?coaltype='+coaltype +'&area='+area +'&province='+province+'&port='+port +'&lowHotValue=' + selectFirHeat +
  '&highHotValue='+selectSecHeat +'&lowSulfurContent='+selectFirSulfur +'&highSulfurContent='+selectSecSulfur+'&page=1';
}

/*function searchByOrderBy(num){
  orderByStyle();
  if(num ==1){
    gotoCheck("releasetime");
  }

  if(num ==2){
    gotoCheck("NCV");

  }

  if(num ==3){
    gotoCheck("RS");
  }
}

function orderByStyle(){
  var orderByColumn = $("#orderByColumn").val();
  if(orderByColumn == "releasetime"){
    $("#createTime").addClass("f-sort");
    $("#ncvNum").removeClass("f-sort");
    $("#rsNum").removeClass("f-sort");
  }

  if(orderByColumn == "NCV"){
    $("#createTime").removeClass("f-sort");
    $("#ncvNum").addClass("f-sort");
    $("#rsNum").removeClass("f-sort");
  }

  if(orderByColumn == "RS"){
    $("#createTime").removeClass("f-sort");
    $("#ncvNum").removeClass("f-sort");
    $("#rsNum").addClass("f-sort");
  }
}*/




