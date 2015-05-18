/**
 * Created by shiling on 14-12-1.
 */
require(['jquery','bootstrap','jquery-form'],function($){

  if($("#picForCertification3").src =="" || $("#picForCertification3").src ==null){
    $('#picForCertification3').hide();
  }
  if($("#picForCertification2").src =="" || $("#picForCertification2").src ==null){
    $('#picForCertification2').hide();
  }
  if($("#picForCertification1").src =="" || $("#picForCertification1").src ==null){
    $('#picForCertification1').hide();
  }

  $("#modal-contractPrompt").modal('show');

  $("#ord").click(function(){
    $("#orders").css("display","block");
  });

  $("#interest").click(function(){
    $("#interests").css("display","block");
  })

//取消删除订单(关闭对话框)
  $("#orderCancel_btn").click(function(){
    $("#modal-orderConfirm").modal('hide');
  });


//取消操作
  $("#btn_cancel").click(function(){
    $("#modal-interestConfirm").modal('hide');
  })

  //返回买货订单
  $("#cancelOrder").click(function(){
    if($("#reqsource").val()=="buyContinueOrder"){
      window.location.href="/account/getMyOrders";
    }
    else if($("#reqsource").val()=="buyCompletedOrder"){
      window.location.href="/account/getMyFinishedOrders";
    }
    else if($("#reqsource").val()=="buyReturnOrder"){
      window.location.href="/account/getMyReturnedOrders";
    }
    else if($("#reqsource").val()=="buyCancelOrder"){
      window.location.href="/account/getMyCanceledOrders";
    }
    else if($("#reqsource").val()=="sellContinuedOrder"){
      window.location.href="/account/getMySellOrders";
    }
    else if($("#reqsource").val()=="sellCompletedOrder"){
      window.location.href="/account/getMySellFinishedOrders";
    }
    else{
      window.location.href="/account/getMySellCanceledOrders";
    }
  });

  //返回修改订单
  $("#gotoUpdateOrder").click(function(){
    window.location.href="/buy/editOrder?id="+$("#idText").val();
  });

  //前往合同页面
  $("#gotoContract").click(function(){
    window.location.href="/mall/confirmOrder?id="+$("#idText").val();
  });

  $("#downloadContract").click(function(){
    window.location.href='/downloadContract?id='+$("#idText").val();
  });

  //判断电子合同是否过期
  $("#btn-contractConfirm").click(function(){
    $.ajax({
        url:'/mall/elecContract',
        data:{id:$("#idText").val()},
        success:function(data){
            if(data.success){
              $("#modal-contractConfirm").modal('show');
              $("#btn-gotoPay").click(function(){
                window.location.href="/mall/payment?id="+$("#idText").val();
              })
            } else{
                if(data.error=="notlogin"){
                  window.location.href="/login";
                } else if(data.error=="cancel"){
                  $("#modal-contractDelay").modal('show');
                } else if(data.error=="repeat"){
                  window.location.href="/";
                }
            }
        }
    });
  });


  //前往支付页面
  $("#contract-agree").click(function(){
    window.location.href="/mall/payment?id="+$("#idText").val();
  });
  //上传支付凭证
  $("#payCertificationClick1").click(function(){
    var certification = $("#certification1").val();
    var mime = certification.toLowerCase().substr(certification.lastIndexOf("."));
    if(certification == ""){
      $("#certificationInfo1").text("请选择您要上传的文件!").css("color","red");
      return ;
    } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#certificationInfo1").text("请选择jpg,bmp,png,jpeg格式的照片上传!");
      return ;
    } else{
      $("#certificationInfo1").text("");
    }

    $('#form-pay1').ajaxSubmit({url:'/saveCertificationPic?id='+$("#pay_orderId").val(),success:function(data){
      $('#certificationInfoError').text('');
      $("#payCertificationClick1").hide();
      $("#hide-certification1").val(data.picSavePath);
      $("#paymentid01").val(data.pid);
      $("#certificationFirstImg").css("display","block");
      $("#picForCertification1").attr("width",400);
      $("#picForCertification1").attr("src",data.picSavePath);
      $("#picForCertification1").show();
      $("#delFirst").show();
    }
    });
  });


  $("#payCertificationClick2").click(function(){
    var certification = $("#certification2").val();
    var mime = certification.toLowerCase().substr(certification.lastIndexOf("."));
    if(certification == ""){
      $("#certificationInfo2").text("请选择您要上传的文件!").css("color","red");
      return ;
    } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#certificationInfo2").text("请选择jpg,bmp,png,jpeg格式的照片上传!");
      return ;
    } else{
      $("#certificationInfo2").text("");
    }

    $('#form-pay2').ajaxSubmit({url:'/saveCertificationPic?id='+$("#pay_orderId").val(),success:function(data){
      $('#certificationInfoError').text('');
      $("#payCertificationClick2").hide();
      $("#hide-certification2").val(data.picSavePath);
      $("#paymentid02").val(data.pid);
      $("#picForCertification2").css("width",400);
      $("#picForCertification2").attr("src",data.picSavePath);
      $("#picForCertification2").show();
      $("#delSecond").show();
      $("#reduceFirst").hide();
    }
    });
  });

  $("#payCertificationClick3").click(function(){
    var certification = $("#certification3").val();
    var mime = certification.toLowerCase().substr(certification.lastIndexOf("."));
    if(certification == ""){
      $("#certificationInfo3").text("请选择您要上传的文件!").css("color","red");
      return ;
    } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#certificationInfo3").text("请选择jpg,bmp,png,jpeg格式的照片上传!");
      return ;
    } else{
      $("#certificationInfo3").text("");
    }

    $('#form-pay3').ajaxSubmit({url:'/saveCertificationPic?id='+$("#pay_orderId").val(),success:function(data){
      $('#certificationInfoError').text('');
      $("#payCertificationClick3").hide();
      $("#hide-certification3").val(data.picSavePath);
      $("#paymentid03").val(data.pid);
      $("#picForCertification3").attr("width",400);
      $("#picForCertification3").attr("src",data.picSavePath);
      $("#picForCertification3").show();
      $("#delThird").show();
      $("#reduceSecond").hide();
    }
    });
  });

  $("#delFirst").click(function(){
      $("#picForCertification1").removeAttr("src");
      $("#certification1").val("");
      $("#picForCertification1").attr("width",0);
      $("#delFirst").hide();
      $("#payCertificationClick1").css("display","block");
      $.ajax({
        url:"/deleteCertificationPic",
        data:{id:$("#paymentid01").val()},
        success:function(){
          $("#hide-certification1").val("");
          $("#paymentid01").val("");
        }
      });
  });
  $("#delSecond").click(function(){
    $("#picForCertification2").removeAttr("src");
    $("#certification2").val("");
    $("#picForCertification2").css("width",0);
    $("#delSecond").hide();
    $("#reduceFirst").css('display','block');
    $("#payCertificationClick2").css("display","block");
    $.ajax({
      url:"/deleteCertificationPic",
      data:{id:$("#paymentid02").val()},
      success:function(){
        $("#hide-certification2").val("");
      }
    });
  });
  $("#delThird").click(function(){
    $("#picForCertification3").removeAttr("src");
    $("#picForCertification3").attr("width",0);
    $("#certification3").val("");
    $("#delThird").hide();
    $("#reduceSecond").css('display','block');
    $("#payCertificationClick3").css("display","block");
    $.ajax({
      url:"/deleteCertificationPic",
      data:{id:$("#paymentid03").val()},
      success:function(){
        $("#hide-certification3").val("");
      }
    });
  });


  $("#btn-gotoOrder").click(function(){
      var picForCertification1 = $("#picForCertification1").attr("src");
      var picForCertification2 = $("#picForCertification2").attr("src");
      var picForCertification3 = $("#picForCertification3").attr("src");
    if("undefined" == typeof picForCertification1 && "undefined" == typeof picForCertification2 && "undefined" == typeof picForCertification3){
      $("#certificationInfoError").text("请点击上面的按钮以进行上传支付凭证的操作").css("color","red");
        return;
    } else{
      $.ajax({
        url:'/account/payMyOrders',
        data:{id:$("#oId").val()},
        success:function(data){
          if(data){
            window.location.href="/account/getMyOrders";
          }
        }
      })
    }
  });


  $("#btn_getMyOrder").click(function(){
    window.location.href='/account/getMyOrders';
  })

  //支付
  $("#pay-btn").click(function(){
    window.location.href="/mall/payment?id="+$("#idText").val();
  });

  $("#agreementChk").click(function(){
    if(document.getElementById("agreementChk").checked == true){
      $("#btn-contractConfirm").removeAttr("disabled");
    } else{
      $("#btn-contractConfirm").attr("disabled","disabled");
    }
  })

  //删除卖家订单(已完成的订单)
  $("#sellOrderDel_btn").click(function(){
    $.ajax({
      url:"/account/deleteSellOrder",
      data:{id:$("#myOrderId").val(),delType:$("#delType").val()},
      success:function(data){
        if(data.success){
          if(data.delType=='finishedOrder'){
            $("#modal-sellOrderDelConfirm").modal('hide');
            window.location.href='/account/getMySellFinishedOrders';
          }
          else{
            $("#modal-sellOrderDelConfirm").modal('hide');
            window.location.href='/account/getMySellCanceledOrders';
          }
        } else{
          window.location.href="/login";
        }
      }
    });
  });

  //支付凭证
  $("#add").click(function(){
      if($("#payPicTwo").is(':visible')){
        if($("#certification2").val()!=""){
          $("#delSecond").show();
        }
        $("#payPicThree").show();
        $("#reduceFirst").hide();
        $("#payCertificationClick3").show();
      }else{
        $("#payPicTwo").show();
        $("#payCertificationClick2").show();
      }
  });

  $("#reduceSecond").click(function(){
    $("#payPicThree").hide();
    $("#reduceFirst").show();
    $("#certification3").val("");
    if($("#picForCertification3").src !="" || $("#picForCertification3").src !=null) {
      $('#picForCertification3').hide();
      $('#picForCertification3').removeAttr('src');
      $("#delThird").hide();
    }
  });

  $("#reduceFirst").click(function(){
    $("#payPicTwo").hide();
    $("#certification2").val("");
    if($("#picForCertification2").src !="" || $("#picForCertification2").src !=null){
      $('#picForCertification2').hide();
      $('#picForCertification2').removeAttr('src');
      $("#delSecond").hide();
    }
  });

  //详情页面的合同查看
  $("#showContract").click(function(){
      if($("#orderPayType").val()=='付全款'){
        window.open("/account/showContract?payType=payAll&orderId="+$("#orderId").val(),'_blank');
      } else{
        window.open("/account/showContract?payType=payHalf&orderId="+$("#orderId").val(),'_blank');
      }
  });
});

//卖货订单[已完成]
function delMySellOrder(id,type){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该订单吗?");
  $('.my_demandButton').attr("id","delMySellOrder_btn");
  $("#delMySellOrder_btn").bind("click", function(){
    $.ajax({
      url:"/account/deleteOrder",
      data:{id:id},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMySellFinishedOrders');
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}

//取消订单(买家订单中的进行中的订单)
function CancelMyContinuedOrder(id,type){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要取消该订单吗?");
  $('.my_demandButton').attr("id","myOrder_confirm");
  $("#myOrder_confirm").bind("click", function(){
    $.ajax({
      url:"/account/cancelOrder",
      data:{id:id,cancelType:type},
      success:function(data){
        if(data.success){
          if(data.cancelType=='buy'){
            $(".my_demand").modal('hide');
            goToLocation('/account/getMyOrders');
          } else{
            $(".my_demand").modal('hide');
            goToLocation('/account/getMySellOrders');
          }
        } else{
          location.reload();
        }
      }
    });
  });
}

//删除订单(已完成的订单)
function delMyFinishedOrder(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该订单吗?");
  $('.my_demandButton').attr("id","orderConfirm_btn");
  $("#orderConfirm_btn").bind("click", function(){
    $.ajax({
      url:"/account/deleteOrder",
      data:{id:id},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMyFinishedOrders');
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}
function delMyCanceledOrder(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该订单吗?");
  $('.my_demandButton').attr("id","orderConfirm_btn");
  $("#orderConfirm_btn").bind("click", function(){
    $.ajax({
      url:"/account/deleteOrder",
      data:{id:id},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMyCanceledOrders');
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}
//删除我关注的产品
function delMyInterest(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除关注的该产品吗?");
  $('.my_demandButton').attr("id","modal_interestConfirm");
  $("#modal_interestConfirm").bind("click", function(){
      $.ajax({
        url:"/account/cancelMyInterest",
        data:{id:id},
        success:function(data){
          if(data){
            $(".my_demand").modal('hide');
            goToLocation('/account/getMyInterest?type=supply');
          }
        }
    });
  });
}
//删除我关注的需求
function delMyInerestedDeamnd(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要取消关注的该条需求吗?");
  $('.my_demandButton').attr("id","btn_demandConfirm");
  $("#btn_demandConfirm").bind("click", function(){
    $.ajax({
      url:"/account/cancelMyInterest",
      data:{id:id},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          window.location.href='/account/getMyInterestDemand';
        }
      }
    });
  });
}
//申请退货
function ApplyReturn(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要退货吗?");
  $('.my_demandButton').attr("id","certainReturn_btn");
  $("#certainReturn_btn").bind("click", function(){
    $.ajax({
      url:"/account/applyReturnGoods",
      data:{id:id},
      success:function(data){
        if(data.success){
          $(".my_demand").modal('hide');
          window.location.href='/account/getMyOrders';
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}
//取消退货[退货中]
function CancelMyReturnedOrder(id){
  $.ajax({
    url:"/account/getOrderStatus",
    data:{id:id},
    success:function(data){
      if(data == "ReturnGoods"){
        $(".my_demand").modal('show');
        $('.my_demandBody').text("您确定要取消退货申请吗?");
        $('.my_demandButton').attr("id","certainCanceledReturn_btn");
        $("#certainCanceledReturn_btn").bind("click", function(){
          $.ajax({
            url:"/account/cancelReturnGoods",
            data:{id:id},
            success:function(data){
              if(data){
                $(".my_demand").modal('hide');
                goToLocation('/account/getMyReturnedOrders');
              } else{
                window.location.href="/login";
              }
            }
          });
        });
      } else{
        $(".my_demand").modal('show');
        $('.my_demandBody').text("温馨提示，该订单已经不能取消退货！");
        $('.my_demandButton').css("display","none");
        $('.my_cancelButton').attr("id","Cancel_btn");
        $("#Cancel_btn").bind("click", function() {
          location.reload();
        });
      }
    }
  })
}
//重新发布
function reSupply(id){
  $("#supplyId").val(id);
}
//取消供应发布
function cancelMySupply(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要取消该供应发布?");
  $('.my_demandButton').attr("id","btn_cancelConfirm");
  $("#btn_cancelConfirm").bind("click", function(){
    $.ajax({
      url:'/account/cancelRelease',
      data:{supplyId:id},
      success:function(data){
        if(data.success){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMySupply');
        }else{
          $(".my_demand").modal('show');
          $('.my_demandBody').text("该条供应信息下还存在订单,不能取消!");
          $('.my_demandButton').text('').css('display','none');
        }
      }
    });
  });
}
//删除我的供应
function delMySupply(id){
  $.ajax({
    url:"/getSellinfoStatus",
    data:{id:id},
    success:function(data){
      if(data == '已删除'){
        location.reload();
      } else{
      $(".my_demand").modal('show');
      $('.my_demandBody').text("您确定要删除该供应发布?");
      $('.my_demandButton').attr("id","mySupply_confirm");
      $("#mySupply_confirm").bind("click", function(){
        $.ajax({
          url: "/account/deleteMySupply",
          data: {id:id},
          success: function (data) {
            if (data) {
              $(".my_demand").modal('hide');
              goToLocation('/account/getMySupply');
            }
          }
        });
      });
    }
    }
  });
}

//确认修改我的供应
/*function updateMySupply(id) {
  $("#supplyUpdeteComfirm").modal('show');
  $("#supplyUpdeteComfirmBtn").click(function() {
    window.location.href = "/account/updateSupply?id=" + id;
  });
}*/

//url 跳转的url地址
function goToLocation(url, currentPage){
  var totalCount = $("#count").val();
  if(url.indexOf("?") != -1){
    if(currentPage == 1){
      window.location.href=url;
    } else if((totalCount-1)%10 == 0){
      window.location.href=url+'&page='+(currentPage - 1);
    } else{
      window.location.href=url+'&page='+currentPage;
    }
  } else{
    if(currentPage == 1){
      window.location.href=url;
    } else if((totalCount-1)%10 == 0){
      window.location.href=url + '?page=' + (currentPage - 1);
    } else{
      window.location.href=url+'?page='+currentPage;
    }
  }
}



