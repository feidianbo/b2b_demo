/**
 * Created by shiling on 14-12-12.
 */
define(['angular','kitt'],function(angular, kitt) {
  return angular.module('kitt.certificate', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/certificate/wait', {
          templateUrl: 'views/certification/list.html',
          controller: 'certificate.Wait'
        })
        .when('/certificate/tail', {
          templateUrl: 'views/certification/list.html',
          controller: 'certificate.Tail'
        })
        .when('/certificate/pass', {
          templateUrl: 'views/certification/list.html',
          controller: 'certificate.Pass'
        })
        .when('/certificate/fail', {
          templateUrl: 'views/certification/list.html',
          controller: 'certificate.Fail'
        })
        .when('/certificate/detail', {
          templateUrl: 'views/certification/checkOrderCertification.html',
          controller: 'order.Detail'
        })
        .when('/groupbuy/wait',{
          templateUrl: 'views/certification/groupbuylist.html',
          controller: 'groupbuy.Wait'
        })
        .when('/groupbuy/pass',{
          templateUrl: 'views/certification/groupbuylist.html',
          controller: 'groupbuy.Pass'
        })
        .when('/groupbuy/fail',{
          templateUrl: 'views/certification/groupbuylist.html',
          controller: 'groupbuy.Fail'
        })
        .when('/groupbuy/payment',{
          templateUrl: 'views/certification/payment.html',
          controller: 'groupbuy.Payment'
        });
    })

    .controller('certificate.Wait',function($scope,$params){
      $scope.searchMall=function(){
          goto('/certificate/wait',{content:$scope.content, pid:$scope.pid});
      }
      $scope.pageTo=function(){
        goto('/certificate/wait', {page:$scope.orderList.page,content:$scope.content,pid:$scope.pid});
      };
      service({
        url:'/certificate/wait',
        data:{page:$params('page',1),content:$params("content", ""),pid:$params("pid", "")},
        success:function(data){
          $scope.orderList=data.orderList;
          $scope.content=data.content;
          $scope.pid=data.pid;
          $scope.page=data.orderList.page;
          $scope.$apply();
        }
      });
    })

    .controller('certificate.Tail',function($scope,$params){
      $scope.searchMall=function(){
        goto('/certificate/tail',{content:$scope.content, pid:$scope.pid});
      }
      $scope.pageTo=function(){
        goto('/certificate/tail', {page:$scope.orderList.page,content:$scope.content,pid:$scope.pid});
      };
      service({
        url:'/certificate/tail',
        data:{page:$params('page',1),content:$params("content", ""),pid:$params("pid", "")},
        success:function(data){
          $scope.orderList=data.orderList;
          $scope.content=data.content;
          $scope.pid=data.pid;
          $scope.page=data.orderList.page;
          $scope.$apply();
        }
      });
    })

    .controller('certificate.Pass',function($scope,$params){
      $scope.searchMall=function(){
        goto('/certificate/pass',{content:$scope.content, pid:$scope.pid});
      }
      $scope.pageTo=function(){
        goto('/certificate/pass', {page:$scope.orderList.page,content:$scope.content,pid:$scope.pid});
      };
      service({
        url:'/certificate/pass',
        data:{page:$params('page',1),content:$params("content", ""),pid:$params("pid", "")},
        success:function(data) {
          $scope.orderList=data.orderList;
          $scope.content=data.content;
          $scope.pid=data.pid;
          $scope.page=data.orderList.page;
          $scope.$apply();
        }
      });
    })

    .controller('certificate.Fail',function($scope,$params){
      $scope.searchMall=function(){
        goto('/certificate/fail',{content:$scope.content, pid:$scope.pid});
      }
      $scope.pageTo=function(){
        goto('/certificate/fail', {page:$scope.orderList.page,content:$scope.content,pid:$scope.pid});
      };
      service({
        url:'/certificate/fail',
        data:{page:$params('page',1),content:$params("content", ""),pid:$params("pid", "")},
        success:function(data) {
          $scope.orderList=data.orderList;
          $scope.content=data.content;
          $scope.pid=data.pid;
          $scope.page=data.orderList.page;
          $scope.$apply();
        }
      });
    })

    //财务详情
    .controller('order.Detail',function($scope,$params) {
      service({
        url: '/certificate/detail',
        data: {id: $params('id')},
        success: function (data) {
          if(data.success) {
            $("#pass").attr("checked", "checked");
            $scope.order = data.order;
            $scope.payment1=data.payment1;
            $scope.payment2=data.payment2;
            $scope.payment3=data.payment3;
            if(data.payment1!=null){
              $scope.id01=data.payment1.id;
            } else{
              $scope.id01=0;
            }
            if(data.payment2!=null){
              $scope.id02=data.payment2.id;
            } else{
              $scope.id02=0;
            }
            if(data.payment3!=null){
              $scope.id03=data.payment3.id;
            } else{
              $scope.id03=0;
            }
            $scope.$apply();
            window.initAuth();
          } else{
          }
        },error:function(data){
            if(data.responseText==="404"){
                alert("资源没找到！");
            }
        }
      });

      $("img").click(function(){
          var imgSrc=$(this).attr('src');
          $(".img-dialog").modal('show');
          $("#showPic").attr("src",imgSrc);
      });

      $scope.clearErrorFirst=function(){
        $("#errorFirst").text("");
      }

      $scope.clearErrorSecond=function(){
        $("#errorSecond").text("");
      }

      $scope.clearErrorThird=function(){
        $("#errorThird").text("");
      }

      //下载支付凭证
      $scope.downloadCertification=function(url){
        window.location.href='/certificate/downloadCertification?url=' + url;
      }

      var partten=/^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;

      $scope.checkCertification=function(id){
      if($("#checkStatus").text()==""){
        $("#errorInfo").text("请认真完善相关审核信息").css("color","red");
        return;
      } else if(($scope.checkStatus == '审核未通过') && $("#comment").val()==""){
        $("#errorInfo").text("");
        $("#errorInfo").text("请填写不通过的理由").css("color","red");
        return;
      } else {
        var money01;
        var money02;
        var money03;
        if($("#money01").val() != "") {
          money01 = $("#money1").val().replace(/,/gm, "");
        }
        if($("#money2").val() != "") {
          money02 = $("#money2").val().replace(/,/gm, "");
        }
        if($("#money3").val() != "") {
          money03 = $("#money3").val().replace(/,/gm, "");
        }
        service({
          url:'/order/checkCertification',
          data:{id:id,checkStatus:$("#checkStatus").text(),comment:$("#comment").val(),id01:$scope.id01,id02:$scope.id02,id03:$scope.id03,money01:money01,money02:money02,money03:money03},
          success:function(data){
            if(data){
              goto('/certificate/wait');
            }
          }
        })
      }
    }

      $scope.back = function(){
        history.go(-1);
      };
    })

    .controller('groupbuy.Wait',function($scope,$params){
      $scope.pageTo=function(){
        goto('/groupbuy/wait', {page:$scope.payment.page,status:'wait'});
      };
      service({
        url:'/groupBuy/getGroupBuyQualifyList',
        data:{page:$params('page',1),status:'wait'},
        success:function(data){
          $("#theme").text("-待审核");
          $scope.payment=data.groupBuyQualifications;
          $scope.page=data.groupBuyQualifications.page;
          $scope.$apply();
        }
      });
    })

    .controller('groupbuy.Pass',function($scope,$params){
      $scope.pageTo=function(){
        goto('/groupbuy/pass', {page:$scope.payment.page,status:'pass'});
      };
      service({
        url:'/groupBuy/getGroupBuyQualifyList',
        data:{page:$params('page',1),status:'pass'},
        success:function(data){
          $("#theme").text("-审核通过");
          $scope.payment=data.groupBuyQualifications;
          $scope.page=data.groupBuyQualifications.page;
          $scope.$apply();
        }
      });
    })

    .controller('groupbuy.Fail',function($scope,$params){
      $scope.pageTo=function(){
        goto('/groupbuy/fail', {page:$scope.payment.page,status:'fail'});
      };
      service({
        url:'/groupBuy/getGroupBuyQualifyList',
        data:{page:$params('page',1),status:'fail'},
        success:function(data){
          $("#theme").text("-审核未通过");
          $scope.payment=data.groupBuyQualifications;
          $scope.page=data.groupBuyQualifications.page;
          $scope.$apply();
        }
      });
    })

    .controller('groupbuy.Payment',function($scope,$params) {
      service({
        url: '/groupBuy/getGroupBuyQualifyDetail',
        data: {code: $params('qualificationcode')},
        success: function (data) {
          $scope.groupBuyQualification=data.groupBuyQualification;
          $scope.$apply();
          window.initAuth();
        }
      });

      $scope.checkInput=function(){
          $("#inputPayedError").text("");
          $("#result").text("");
          var partten = /^([1-9][\d]{0,9}|0)?$/;
          var inputPayed = $("#inputPayed").val();
          if (!partten.test(inputPayed)) {
            $("#inputPayedError").text("请输入合法数字").css("color", "red");
          } else {
            $("#payed").text(inputPayed);
            if (inputPayed < 100000) {
              $("#result").text("未通过").css("color", "red");
            }else{
              $("#result").text("通过").css("color", "red");
            }
          }

      };

      $scope.submitPayment=function(qualificationcode,status){
        if(status == 'QUALIFY_APPLY') {
          $("#errorInfo").text("");
          var inputPayed = $("#inputPayed").val();
          var comment = $("#comment").val();
          if (inputPayed < 100000) {
            if (comment == null || comment == "") {
              $("#errorInfo").text("请填写不通过的原因").css("color", "red");
              return;
            }
          }

        service({
          url: '/groupBuy/confirmGroupBuyQualifyDetail',
          data: {qualifyCode: qualificationcode,margins:inputPayed,comment:comment},
          success: function (data) {
            if(data.message == "succeed") {
              window.location.href = "/#/groupbuy/wait";
            }else{
              alert(data.message);
            }
          }, error: function () {
          }
        });
       }else{
          if(status == 'QUALIFY_ACTIVE' || status == 'QUALIFY_INPROCESS'){
            window.location.href="/#/groupbuy/pass";
          }

          if(status == 'QUALIFY_NOT_ENOUGH'){
            window.location.href="/#/groupbuy/fail";
          }
        }

      };
    })
});

    //转换金额格式
    function tran(id)
    {
      var partten=/^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/;
      if(!partten.test($("#money1").val().replace(/,/g,"")) && $("#money1").val() != ""){
        $("#errorFirst").text("只能输入数字").css("color","red");
        return;
      }else{

        $("#errorFirst").text("");
      }
      if(!partten.test($("#money2").val().replace(/,/g,"")) && $("#money2").val() != ""){
        $("#errorSecond").text("只能输入数字").css("color","red");
        return;
      }else{
        $("#errorSecond").text("");
      }
      if(!partten.test($("#money3").val().replace(/,/g,"")) && $("#money3").val() != ""){
        $("#errorThird").text("只能输入数字").css("color","red");
        return;
      }else{
        $("#errorThird").text("");
      }
      var num1 = $("#money1").val().replace(/,/g,"");
      var num2 = $("#money2").val().replace(/,/g,"");
      var num3 = $("#money3").val().replace(/,/g,"");
      var sum = null;
      sum = Number(num1)+Number(num2)+Number(num3);
      var v, j, sj, rv = "";
      v = id.value.replace(/,/g,"").split(".");
      j = v[0].length % 3;
      sj = v[0].substr(j).toString();
      for (var i = 0; i < sj.length; i++)
      {
        rv = (i % 3 == 0) ? rv + "," + sj.substr(i, 1): rv + sj.substr(i, 1);
      }
      var rvalue = (v[1] == undefined) ? v[0].substr(0, j) + rv: v[0].substr(0, j) + rv + "." + v[1];
      if (rvalue.charCodeAt(0) == 44)
      {
        rvalue = rvalue.substr(1);
      }
      id.value = rvalue;
      $("#currentMoney").val(formatMoney(sum,2));
      if(Number($("#paidMoney").val())+sum<Number($("#totalM").val())*0.1){
        $("#checkStatus").text("审核未通过");
      }else if(Number($("#paidMoney").val())+sum>=Number($("#totalM").val())){
        $("#checkStatus").text("审核通过");
      }else{
        $("#checkStatus").text("待付尾款");
      }
     }


    function formatMoney(s, n)
    {
      n = n > 0 && n <= 20 ? n : 2;
      s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
      var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
      var t = "";
      for(i = 0; i < l.length; i ++ )
      {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
      }
       return t.split("").reverse().join("") + "." + r;
    }



