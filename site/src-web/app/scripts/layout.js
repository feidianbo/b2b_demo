/**
 * Created by shiliang on 15-1-5.
 */
require(['jquery','bootstrap'],function($){
  //移动光标事件
  $("#navShow").mouseover(function(){
    $("#menu").css("display","block");
  }).mouseout(function(){
    $("#menu").css("display","none");
    $('#menu').find('.glyphicon').show();
  });

  $("#finance").mouseover(function(){
    $("#finance_children").css("display","block");
  }).mouseout(function(){
    $("#finance_children").css("display","none");
  });

  $("#finance_children1").mouseover(function(){
    $("#finance_children1").css("background-color","#4393ff");
  }).mouseout(function(){
    $("#finance_children1").css("background-color","#317ee6");
  });

  $("#finance_children2").mouseover(function(){
    $("#finance_children2").css("background-color","#4393ff");
  }).mouseout(function(){
    $("#finance_children2").css("background-color","#317ee6");
  });

  //绑定人工买卖图标样式
  $("#manual_lookup").hover(function(){
    $("#manual_lookup").attr("src","/images/manual_lookup_hover.png");
  },function(){
    $("#manual_lookup").attr("src","/images/manual_lookup.png");
  });

  $("#manual_sell").hover(function(){
    $("#manual_sell").attr("src","/images/manual_sell_hover.png");
  },function(){
    $("#manual_sell").attr("src","/images/manual_sell.png");
  });

  //初始化
  $(".huadong").css("display",'none');
  $(".dongbei").css("display",'none');
  $(".huazhong").css("display",'none');
  $(".huanan").css("display",'none');
});

function ToggleLi(obj,cls)
{
  $(obj).closest('ul').find('li:gt(0)').hide();
  $(obj).closest('ul').find('.'+cls).show();
}

function IsShowIcon(obj){
  $("#menu").find('.glyphicon').show();
  $(obj).closest('div').find('.glyphicon').hide();
}

