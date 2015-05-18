/**
 * Created by xiajing on 14-12-10.
 */
define(['angular','kitt'],function(angular, kitt){
  return angular.module('kitt.demand', ['kitt'])
    .config(function($routeProvider){
      $routeProvider
        .when("/demand/view",{
          templateUrl:'views/demand/showDetail.html',
          controller:'demand.View'
        })
        .when('/demand/wait',{
          templateUrl:'views/demand/list.html',
          controller:'demand.waitList'
        })
        .when('/demand/pass',{
          templateUrl:'views/demand/list.html',
          controller:'demand.passList'
        })
        .when('/demand/fail',{
          templateUrl:'views/demand/list.html',
          controller:'demand.failList'
        });
    })

    //待审核
    .controller('demand.waitList',function($scope,$params){

      $scope.searchDemand=function(){
        goto('/demand/wait', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,content:$scope.content});
      };
      $scope.pageTo=function(){
        goto('/demand/wait', {page:$scope.demand.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour});
      };

      var content = $params('content');
      if(content == true || content == ""){
        content = null;
      }
      service({
        url:'/demand/wait',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),content:content},
        success:function(data){
          $("#theme").text("-待审核");
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.demand=data.demand;
          $scope.page=data.demand.page;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.content=data.content;
          $scope.$apply();
          window.initAuth();
        }
      });
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0){
          clearHistory();
          service({
            url:'/mall/getProvinces',
            data:{id:$("#deliveryRegion").val()},
            success:function(data){
              $scope.deliveryProvinces=data;
              $scope.deliveryProvince=0;
              $scope.deliveryHarbours=null;
              $scope.deliveryHarbour=0;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours=null;
          $scope.deliveryHarbour=0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }, error: function () {
            }
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
    })
    //审核通过
    .controller('demand.passList',function($scope,$params) {
      $scope.searchDemand=function(){
        goto('/demand/pass', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,content:$scope.content});
      };
      $scope.pageTo=function(){
        goto('/demand/pass', {page:$scope.demand.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour});
      };

      var content = $params('content');
      if(content == true || content == ""){
        content = null;
      }
      service({
        url:'/demand/pass',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),content:content},
        success:function(data){
          $("#theme").text("-审核通过");
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.demand=data.demand;
          $scope.page=data.demand.page;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.content=data.content;
          $scope.$apply();
          window.initAuth();
        }
      });
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0){
          clearHistory();
          service({
            url:'/mall/getProvinces',
            data:{id:$("#deliveryRegion").val()},
            success:function(data){
              $scope.deliveryProvinces=data;
              $scope.deliveryProvince=0;
              $scope.deliveryHarbours=null;
              $scope.deliveryHarbour=0;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours=null;
          $scope.deliveryHarbour=0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }, error: function () {
            }
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

      //取消审核通过的需求信息
      $scope.cancelDemand=function(demandcode){
        $("#demandcode").val(demandcode);
        $("#cancelDemandConfirmDialog").modal('show');
      };

      $scope.cancelDemandClick=function(){
        var demandcode = $("#demandcode").val();
        service({
          url:'/demand/cancelDemand',
          data:{demandcode:demandcode},
          success:function(data){
            $("#cancelDemandConfirmDialog").modal('hide');
            goto('/demand/pass');
            location.reload();
          }
        });
      };
    })
    //审核未通过
    .controller('demand.failList',function($scope,$params) {
      $scope.searchDemand=function(){
        goto('/demand/fail', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,content:$scope.content});
      };
      $scope.pageTo=function(){
        goto('/demand/fail', {page:$scope.demand.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour});
      };

      var content = $params('content');
      if(content == true || content == ""){
        content = null;
      }
      service({
        url:'/demand/fail',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),content:content},
        success:function(data){
          $("#theme").text("-审核未通过");
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.demand=data.demand;
          $scope.page=data.demand.page;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.content=data.content;
          $scope.$apply();
          window.initAuth();
        }
      });
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0){
          clearHistory();
          service({
            url:'/mall/getProvinces',
            data:{id:$("#deliveryRegion").val()},
            success:function(data){
              $scope.deliveryProvinces=data;
              $scope.deliveryProvince=0;
              $scope.deliveryHarbours=null;
              $scope.deliveryHarbour=0;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours=null;
          $scope.deliveryHarbour=0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }, error: function () {
            }
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

    })
    .controller('demand.View',function($scope,$params) {
      service({
        url: '/demand/view',
        data: {demandcode: $params('demandcode')},
        success: function (data) {
          $("#pass").attr("checked","checked");
          $scope.demand = data.demand;
          $scope.dealerList=data.dealerList;
          $scope.dealerCount=data.dealerList===undefined?0:data.dealerList;
          $scope.dealer=data.dealer;
          $scope.$apply();
          window.initAuth();
        }, error: function () {
        }
      });

      $scope.checkCompany=function(demandcode){
        //状态是1是未通过审核，0通过审核
        var status = $("input[type='radio']:checked").val();
        var param = {demandcode: demandcode, checkstatus: $("input[type='radio']:checked").val(), comment: $scope.demand.comment};
        if (status === '1' && $("#comment").val() === "") {
          $("#errorInfo").text("请填写不通过的理由").css("color", "red");
          return;
        }
        if (status === '0') {
          if ($("#selectDealer option:selected")[0].value === "") {
            $("#errorInfo").text("请选择交易员").css("color", "red");
            return;
          }
          param['dealerId'] = $scope.dealerId;
        }
        //修改状态
        service({
          url:'/demand/modifyCheckstatusAndComment',
          data:param,
          success:function(data){
            if(data){
              $("#alert > span:last").text('需求审核成功!');
              $("#alert").show();
              goto('/demand/wait');
            } else{
              $("#alert").addClass("alert alert-danger");
              $("#alert > span:last").text('需求审核失败!');
              $("#alert").show();
            }
          }
        })
      };
      $scope.back=function(){
        window.history.go(-1);
      };
    });

});
