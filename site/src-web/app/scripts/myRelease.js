/**
 * Created by shiliang on 14-12-11.
 */

require(['jquery','bootstrap'],function($){




  //取消删除我的报价
  $("#myQuote_cancel").bind("click",function(){
    $("#myQuote-deleteConfirm").modal('hide');
  });

  $("#btn_cancelRelease").click(function(){
    $("#modal-cancelReleaseConfirm").modal('hide');
  })

  //修改手机
  $("#modifySecurephone").bind("click", function(){
    $("#changePhoneDialog").modal('show');
  });

  //修改手机中得关闭按钮事件
  $("#modifyPhone_close").bind("click",function(){
    $("#changePhoneDialog").modal('hide');
      location.href ="/account/accountSecurity";
  });

  //认证公司信息按钮
  $("#approve").bind("click",function(){
    location.href="/account/getMyAccount";
  });

  //重新当前刷新页面
  $("#btn_reLoad").bind("click",function(){
    $("#modal-reLoad").modal('hide');
    window.history.go(0);
  });

  //认证公司信息取消按钮
  $("#cancelComBtn").click(function(){
    location.href ="/account/accountInfo";
  });

  //账户安全的修改手机号
  $("#modify_securephone").bind("click", function(){
    $("#selectModifyPwd").val("Security");
    $("#changePhoneDialog").modal('show');
  });

  //修改密码
  $("#modify_pwd").bind("click", function(){
    $("#changePasswordDialog").modal('show');
  });

  //修改密码成功后关闭按钮
  $("#close_successPasswordDialog").bind("click", function(){
    location.href ="/account/accountSecurity";
  });

  //用户信息的phone和QQ保存
  $("#telephone").blur(function(){
    $("#telephoneError").text("");
  });

  $("#qq").blur(function(){
    $("#qqError").text("");
  });

  $("#userSave").bind("click", function(){
    var pattern = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    var regluar = /^[1-9]+[0-9]*]*$/;
    if(!($("#telephone").val() == "")){
      if(!pattern.test($("#telephone").val())){
        $("#telephoneError").text("请输入正确填写固话!");
        return;
      }else{
        $("#telephoneError").text("");
      }
    }

    if(!($("#qq").val() == "")){
      if(!regluar.test($("#qq").val())){
        $("#qqError").text("请输入正确的QQ号码!");
        return;
      }else{
        $("#qqError").text("");
      }
    }

    $.ajax({
      url:"/account/saveMyaccount",
      data:{telephone:$("#telephone").val(),qq:$("#qq").val()},
      success:function(data){
        if(data == "success"){
          $("#userInfo_Dialog").modal('show');
        }
      }
    });
  });

  $("#close_userInfoDialog").bind("click", function(){
    location.href="/account/accountInfo";
  });


})


//删除已发布需求的信息
function deleteMyrelease(demadId){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该条需求?");
  $('.my_demandButton').attr("id","myRelease_confirm");
  $("#myRelease_confirm").bind("click", function(){
    $.ajax({
      url:"/account/deleteMydemand",
      data:{demandcode:demadId},
      success:function(data){
        if(data == "success"){
          $("#myRelease-deleteConfirm").modal('hide');
          goToLocation('/account/getMyDemand');
        }
      }
    });
  });
}

//取消我的发布需求
function stopMyrelease(demandcode){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要取消该项需求吗?");
  $('.my_demandButton').attr("id","myRelease_stopConfirm");
  $("#myRelease_stopConfirm").bind("click", function(){
    $.ajax({
      url:"/account/cancelMydemand",
      data:{demandcode:demandcode},
      success:function(data){
        if(data == "success"){
          $(".my_demand").modal('hide');
          //window.location.href='/account/getMyDemand';
          window.history.go(0);
        }else{
          $("#modal-reLoad").modal('show');
        }
      }
    });
  });
}

//取消我的报价
function cancelMyquote(id){
  $("#myQuote-cancelConfirm").modal('show');
  //确认取消我的报价
  $("#myQuote_cancelConfirm").bind("click", function(){
    $.ajax({
      url:"/cancelMyquote",
      data:{id:id},
      success:function(data){
        if(data == "success"){
          $("#myQuote-cancelConfirm").modal('hide');
          window.location.href="/account/getMyQuote";
        }
      }
    });
  });
}

//删除我的报价
function deleteMyquote(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该报价?");
  $('.my_demandButton').attr("id","myQuote_confirm");

  //确认删除我的报价
  $("#myQuote_confirm").bind("click", function(){
    $.ajax({
      url:"/account/deleteMyquote",
      data:{id:id},
      success:function(data){
        if(data == "success"){
          $("#myQuote-deleteConfirm").modal('hide');
          goToLocation('getMyQuoteNotBid');
        }
      }
    });
  });
}

//查看我的发布需求
function showMyRelease(demandcode){
  window.location.href='/account/viewMyDemand?demandcode='+demandcode +"&reqsource=myrelease";
  //location.href ="/viewMyrelease?demandcode="+demandcode;
};

function cancelMySupply(id){
  $('#mySupplyId').val(id);
  $("#modal-cancelReleaseConfirm").modal('show');
}

function deleteManualInfo(id,flag){
   $("#confirm_deletemanual_modal").modal("show");
  var confirmBtn = $("#manualsell_confirm");
  confirmBtn.unbind("click");
  confirmBtn.bind("click",function(){
    $.ajax({
      url:"/manualsell/delete/"+id,
      success:function(responseMsg){
        if(responseMsg == true){
          $("#confirm_deletemanual_modal").modal("hide");
          var redirectUrl= flag==true?"/manualsell/list_manualsel_in":"/manualsell/list_manualsel_out";
          window.location.href=redirectUrl;
        }
      }
    });
  });
  $("#manualsell_cancel").unbind("click");
  //绑定取消事件
  $("#manualsell_cancel").click(function(){
    $("#confirm_deletemanual_modal").modal("hide");
  });
}




//分页跳转
//url 跳转的url地址
function goToLocation(url){
  var currentPage = $("#currentPage").val();
  var totalCount = $("#count").val();
  if(url.indexOf("?") != -1){
    if(currentPage == 1){
      window.location.href=url;
    }else if((totalCount-1)%10 == 0){
      window.location.href=url+'&page='+(currentPage - 1);
    }else{
      window.location.href=url+'&page='+currentPage;
    }
  }else{
    if(currentPage == 1){
      window.location.href=url;
    }else if((totalCount-1)%10 == 0){
      window.location.href=url + '?page=' + (currentPage - 1);
    }else{
      window.location.href=url+'?page='+currentPage;
    }
  }
}


