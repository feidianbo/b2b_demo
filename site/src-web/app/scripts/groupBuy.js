/**
 * Created by shiling on 15-1-27.
 */
require(['jquery', 'scroll', 'bootstrap','jquery-form'],function($){

  var time = null;
  var calTime = null;
  var serverTime = new Date($("#serverTime").val()).getTime() ;//服务器的时间
  var startTime = null;
  var str = $("#endDate").val();
  var endtime =  null;

  $("#remainAmount").text($("#groupNum").text()-$("#soldAmount").text());

  if($("#endDate").length > 0 ) {
    startTime = new Date($("#beginDate").val().replace('-','/').replace('-','/')).getTime();//团购开始时间;
    endtime = new Date(str.replace('-','/').replace('-','/')).getTime();//团购结束时间
    calTime = window.setInterval(increaseTime, 1000); //间隔函数，1秒执行
  }

  function increaseTime(){
    serverTime += 1000;
    if(serverTime < startTime){
      $("#groupActivity1").show();
      $("#doGroupPurchase").attr('disabled',true);
      $("#demandNum").attr('disabled',true);
    }else if(serverTime >= startTime && serverTime <= endtime ){
      $("#groupActivity").show();
      $("#groupActivity1").hide();
      $("#doGroupPurchase").attr('disabled',false);
      $("#demandNum").attr('disabled',false);
      time = window.setInterval(function(){updateTime(serverTime);},1000);
    }else{
      clearInterval(calTime);
      $("#groupActivity").show();
      $("#groupActivity").text('团购已经结束啦!').css("font-size","34px");
      $("#doGroupPurchase").attr('disabled',true);
      $("#demandNum").attr('disabled',true);
    }
  }



  function updateTime(serverTime){
    var stime = endtime - serverTime;
    var lag = stime / 1000; //当前时间和结束时间之间的秒数
    if(lag > 0)
    {
      var second = Math.floor(lag % 60);
      var minute = Math.floor((lag / 60) % 60);
      var hour = Math.floor((lag / 3600) % 24);
      var day = Math.floor((lag / 3600) / 24);
      $("#day").val(day);
      $("#hour").val(hour);
      $("#minute").val(minute);
      $("#second").val(second);
    }
  }
  //完善公司信息
  $("#changeInfo").click(function(){
    window.location.href="/account/getMyAccount";
  });
  $("#updateInfo").click(function(){
    window.location.href="/account/getMyAccount";
  });
  $("#updateInfoVerifying").click(function(){
    window.location.href="/account/getMyAccount";
  })

  //获取团购资格--验证
  $("#getGroupBuyQualification").click(function(){
      if($("#userName").val()==""){
        $("#modal-login").modal('show');
      }
      else{
        var url="/group/getGroupCertification";
        checkInfo(url,'deposit');
      }
  })

  //同意团购保证金合约
  $("#agreementChk").click(function(){
    if(document.getElementById("agreementChk").checked == true){
      $("#btn-depositConfirm").removeAttr("disabled");
    } else{
      $("#btn-depositConfirm").attr("disabled","disabled");
    }
  })
  //获取参团资格
  $("#btn-depositConfirm").click(function(){
      $("#uploadGroupCertification").css('display','block');
      $("#delGroupClick").hide();
  });

  $("#getGroupCert").click(function(){
    checkInfo('/group/getGroupCertification','deposit');
  })

  //团购详情页面的立即团购
  $("#doGroupPurchase").click(function(){
    if(checkGroup()){
      //判断是否有团购资质
      $.ajax({
        url:'/group/checkActiveQualify',
        success:function(data){
          if(data.success){
            //跳转至团购活动详情页面进行确认下单操作
            $.ajax({
              url:"/group/checkGroupBuyOrder",
              data:{groupBuySupplyId:$("#groupSupplyId").val(),volume:$("#demandNum").val()},
              success:function(data){
                if(data.success){
                  window.location.href="/group/generateGroupBuyOrder?groupBuySupplyId="+$("#groupSupplyId").val()+'&volume='+$("#demandNum").val();
                }
                else{
                  if(data.message=='notbegin'){
                    $(".my_supply").modal('show');
                    $('.my_supplyBody').text("团购还未开始");
                    $('.my_supplyButton').css('display','none');
                  }
                  if(data.message=='finished') {
                    $(".my_supply").modal('show');
                    $('.my_supplyBody').text("团购已结束");
                    $('.my_supplyButton').css('display','none');
                  }
                  if(data.message=='minimumamount'){
                    $(".my_supply").modal('show');
                    $('.my_supplyBody').text("团购量小于团购起订量");
                    $('.my_supplyButton').css('display','none');
                  }
                }
              }
            })
          }else{
            if(data.error=='none'){
              $(".my_supply").modal('show');
              $('.my_supplyBody').text("您目前还没有团购资格!您可以通过点击上面的按钮来获取团购资格!");
              $('.my_supplyButton').css('display','none');
            }
            else{
              $(".my_supply").modal('show');
              $('.my_supplyBody').text("您的团购资格不可用,请查看个人中心!");
              $('.my_supplyButton').attr('id','group-updateInfo');
              $('.my_supplyButton').text('进入个人中心');
              $("#group-updateInfo").bind("click", function(){
                window.location.href="/account/getMyQualifyApply";
              });
            }
          }
        }
      });
    }
  });

  //上传团购支付凭证
  $("#groupCertificationClick").click(function(){

    var certification = $("#groupCertificationFile").val();
    var mime = certification.toLowerCase().substr(certification.lastIndexOf("."));
    if(certification == ""){
      $("#errorInfo").text("请选择您要上传的文件!").css("color","red");
      return ;
    } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#errorInfo").text("请选择jpg,bmp,png,jpeg格式的照片上传!").css('color','red');
      return ;
    } else{
      $("#errorInfo").text("");
      $("#delGroupClick").removeAttr("disabled");
    }
    //执行上传操作
    $('#form-payGroup').ajaxSubmit({url:'/group/saveCompanyPic?id='+$("#idText").val(),success:function(data){
      $('#errorInfo').text('');
      $("#groupCertificationClick").hide();
      $("#delGroupClick").show();
      $("#hide-groupCertification").val(data.picSavePath);
      $("#groupCertification").val(data.id);
      $("#certificationImg").css("display","block");
      //$("#picForGroupCertification").attr("width",300);
      $("#picForGroupCertification").attr("height",135);
      $("#picForGroupCertification").attr("src",data.picSavePath);
    }
    });
  });

  //确认上传
  $("#uploadConfirm").click(function(){
    if($("#groupCertificationFile").val()=="" || $("#hide-groupCertification").val()==""||typeof($("#picForGroupCertification").attr("src"))== "undefined"){
      $("#errorInfo").text("请选择您要上传的文件!").css('color','red');
    }
    else{
      $("#errorInfo").text("");
      $("#modal-uploadSuccess").modal('show');
    }
  });

  //上传成功跳转到个人中心
  $("#uploadToCenter").click(function(){
    window.location.href="/account/getMyQualification";
  })

  //删除图片
  $("#delGroupClick").click(function(){
    $("#picForGroupCertification").removeAttr("src");
    $("#groupCertificationFile").val("");

    //$("#picForGroupCertification").attr("width",0);
    $("#picForGroupCertification").attr("height",0);
    $("#delFirst").hide();
    $.ajax({
      url:"/group/deletePicture",
      data:{id:$("#groupCertification").val()},
      success:function(data){
          if(data){
            $("#hide-groupCertification").val("");
            $("#groupCertification").val("");
            $("#groupCertificationClick").show();
            $("#delGroupClick").hide();
          }
      }
    });
  });


  //保证金返回
  $("#depositBack").click(function(){
    history.go(-1);
  });

  //履约金返回
  $("#executionBack").click(function(){
    history.go(-1);
  });

  //团购时校验数量和价格
  $("#demandNum").bind("focus",function(){
    $("#errors").text("");
  });

//团购需求瓶数判断
  var regular = /^[1-9]+[0-9]*]*$/;
  $("#demandNum").bind("blur",function(){
    checkGroup();
  });
  function checkGroup(){
    if($("#demandNum").val() == ""){
      $("#errors").text("请输入正确的需求瓶数!");
      return false;
    }else if(!regular.test($("#demandNum").val())){
      $("#errors").text("需求瓶数为正整数!");
      return false;
    }
    else if(eval($("#demandNum").val()) > eval($("#remainAmount").text())){
      $("#errors").text("需求量不能大于剩余量!");
      return false;
    }else if(eval($("#demandNum").val()) < eval($("#startAmount").val())){
      $("#errors").text("需求量不能小于起订量!");
      return false;
    }
    else{
      $("#errors").text("");
      $("#groupTotal").val($("#demandNum").val()*$("#groupPrice").val());
      return true;
    }
  }

  //下载
  $("#downloadDepositContract").click(function(){
    window.location.href='/group/downloadContract?id='+$("#idText").val()+'&type=deposit';
  });

  //团购下单时修改订单
  $("#btn-updateGroupOrder").click(function(){
    window.location.href="/group/showGroupInfo?groupBuySupplyId="+$("#groupBuySupplyId").val();
  });

  //详情页面提交订单,跳至履约保证金页面
  $("#btn-submitOrder").click(function(){
      $("#modal-orderSuccessPrompt").modal("show");
  });

  //确认下单
  $("#contract-confirm").click(function(){
    $.ajax({
        url:'/group/confirmGroupBuyOrder',
        data:{volume:$("#volume").val(),groupbuyordercode:$("#groupBuyOrderCode").val()},
        success:function(data){
          if(data){
            $("#modal-orderSuccessPrompt").modal('hide');
            window.location.href="/account/getMyOrderActive";
          }
          else{
            $("#modal-orderFailPrompt").modal('show');
          }
        }
    });
  });
  //下载履约保证金合约
  $("#downloadExecution").click(function(){
    window.location.href="/group/downloadContract?type=execution&id="+$("#groupOrderId").val();
  });
  //同意履约合同跳转至个人中心
  $("#executionFinish").click(function(){
      window.location.href="/account/getMyOrderActive";
  });
  //查看履约合同
  $("#btn-showExecutionContract").click(function(){
    window.location.href="/account/showExecutionContract?type=execution&root=account&id="+$("#groupBuyOrderId").val();
  })

  //倒计时函数
  function SetRemainTime() {
    if (sysSecond > 0) {
      sysSecond = sysSecond- 1;
      var hour = parseInt((sysSecond / 3600)); //时
      var minute = parseInt((sysSecond /60) % 60); //分
      var second = parseInt(sysSecond % 60); //秒
      //$("#day").val(day);
      $("#hour").text(hour);
      $("#minute").text(minute);
      $("#second").text(second);
    }
  }

  $(".list_lh").myScroll({
    speed:150,
    rowHeight:150
  });

});

//删除已结束的团购
function delFinishedGroupOrder(id){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要删除该团购订单吗?");
  $('.my_demandButton').attr("id","delGroupOrder_btn");
  $("#delGroupOrder_btn").bind("click", function(){
    $.ajax({
      url:"/account/deleteFinishedGroupOrder",
      data:{groupbuyordercode:id},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMyOrderFinish');
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}

//放弃团购资格
function giveUpQualification(qualificationId){
  $(".my_demand").modal('show');
  $('.my_demandBody').text("您确定要放弃团购资格吗?");
  $('.my_demandButton').attr("id","giveUp_btn");
  $("#giveUp_btn").bind("click", function(){
    $.ajax({
      url:"/account/giveupQualify",
      data:{qualificationcode:qualificationId},
      success:function(data){
        if(data){
          $(".my_demand").modal('hide');
          goToLocation('/account/getMyQualification');
        } else{
          window.location.href="/login";
        }
      }
    });
  });
}

//验证相关信息
function checkInfo(url,type){
  $.ajax({
    url:'/doCheckCompany',
    success:function(data){
      if(!data.success) {
        switch(data.error){
          case "lackinfo":
            $(".my_supply").modal('show');
            $('.my_supplyBody').text("您的公司信息不完整,请完善!");
            $('.my_supplyButton').attr("id","changeInfo_btn");
            $('.my_supplyButton').text('完善公司信息');
            $("#changeInfo_btn").bind("click", function(){
              window.location.href="/account/getMyAccount";
            });
            break;
          case "notpass":
            $(".my_supply").modal('show');
            $('.my_supplyBody').text("您的公司信息未通过审核!");
            $('.my_supplyButton').attr("id","updateInfo_btn");
            $('.my_supplyButton').text('完善公司信息');
            $("#updateInfo_btn").bind("click", function(){
              window.location.href="/account/getMyAccount";
            });
            break;
          case "verifying":
            $(".my_supply").modal('show');
            $('.my_supplyBody').text("您的公司信息正在审核中,请您耐心等待!");
            $('.my_supplyButton').attr("id","updateInfoVerifying_btn");
            $('.my_supplyButton').text('完善公司信息');
            $("#updateInfoVerifying_btn").bind("click", function(){
              window.location.href="/account/getMyAccount";
            });
            break;
          default:
            break;
        }
      }
      else{
        if(type=='deposit'){
          window.location.href=url+'?type='+type;
        }
        else{
          //验证是否有团购资格
          $.ajax({
            url:'/group/checkActiveQualify',
            success:function(data){
              if(data.success){
                window.location.href=url;
              }else{
                if(data.error=='none'){
                  $(".my_supply").modal('show');
                  $('.my_supplyBody').text("您目前还没有团购资格!您可以通过点击上面的按钮来获取团购资格!");
                  $('.my_supplyButton').css('display','none');
                }
                else{
                  $(".my_supply").modal('show');
                  $('.my_supplyBody').text("您的团购资格不可用,请查看个人中心!");
                  $('.my_supplyButton').attr('id','group-updateInfo');
                  $('.my_supplyButton').text('进入个人中心');
                  $("#group-updateInfo").bind("click", function(){
                    window.location.href="/account/getMyQualification";
                  });
                }
              }
            }
          });
        }
      }
    }
  });
}




//团购首页的立即团购--验证
function Show(id){
    if($("#userName").val()==""){
    $("#modal-login").modal('show');
    }
    else{
    var url="/group/showGroupInfo?groupBuySupplyId="+id;
    checkInfo(url);
  }
}

function fillMoney(obj){
    $("#groupTotal").val(obj.value*$("#groupUnitPrice").text());
}

function autoScroll(obj){
  $(obj).find("dl").animate({
    marginTop : "-86px"
  },800,function(){
    $(this).css({marginTop : "0px"}).find("dt:first").appendTo(this);
  })
}

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
