/**
 * Created by shiliang on 15-2-2.
 */


require(['jquery'],function($) {
  //当前标题对应的div，计算出改位置对应的当前浏览器的位置
  var scroll = $("#scrollTop").val();
  $("html,body").animate({scrollTop:$('#'+scroll).offset().top-20},10);
})
