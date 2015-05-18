require(['jquery'],function($) {

});

function expandPorts(obj){
  if($("#"+obj).is(":visible")){
    $("#"+obj).hide();
    $("#"+obj+'Icon').removeClass();
    $("#"+obj+'Icon').addClass("glyphicon glyphicon-chevron-up");
  }else{
    $("#"+obj).show();
    $("#"+obj+'Icon').removeClass();
    $("#"+obj+'Icon').addClass("glyphicon glyphicon-chevron-down");
  }
}

function menuShowDetail(obj){
  $("#storageDiv").hide();
  if(obj == 'guangzhou'){
    $("#fangchengDetail").hide();
    $("#caofeidianDetail").hide();
    $("#qinhuangdaoDetail").hide();
    $("#wanzhaiDetail").hide();
    $("#guangzhouDetail").show();
  }

  if(obj == 'fangcheng'){
    $("#caofeidianDetail").hide();
    $("#qinhuangdaoDetail").hide();
    $("#wanzhaiDetail").hide();
    $("#guangzhouDetail").hide();
    $("#fangchengDetail").show();
  }

  if(obj == 'caofeidian'){
    $("#fangchengDetail").hide();
    $("#qinhuangdaoDetail").hide();
    $("#wanzhaiDetail").hide();
    $("#guangzhouDetail").hide();
    $("#caofeidianDetail").show();
  }

  if(obj == 'qinhuangdao'){
    $("#fangchengDetail").hide();
    $("#caofeidianDetail").hide();
    $("#wanzhaiDetail").hide();
    $("#guangzhouDetail").hide();
    $("#qinhuangdaoDetail").show();
  }

  if(obj == 'wanzhai'){
    $("#fangchengDetail").hide();
    $("#caofeidianDetail").hide();
    $("#qinhuangdaoDetail").hide();
    $("#guangzhouDetail").hide();
    $("#wanzhaiDetail").show();
  }
}

function showDetail(obj){
  $("#storageDiv").hide();
  $("#"+obj+'Detail').show();
}

function gotoGoods(obj){
  if(obj == 'guangzhou') {
    window.location.href = "/buy?region=5&province=18&harbour=95&page=1";
  }

  if(obj == 'fangcheng') {
    window.location.href = "/buy?region=5&province=19&harbour=109&page=1";
  }

  if(obj == 'caofeidian') {
    window.location.href = "/buy?region=2&province=7&harbour=32&page=1";
  }

  if(obj == 'qinhuangdao') {
    window.location.href = "/buy?region=2&province=7&harbour=31&page=1";
  }

  if(obj == 'wanzhai') {
    window.location.href = "/buy?region=3&province=10&harbour=49&page=1";
  }
}
