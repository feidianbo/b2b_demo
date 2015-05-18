/**
 * Created by liuxinjie on 14-12-10.
 */
define(['angular','kitt','jquery.validation','bootstrap3-datetimepicker','angular-ui-select'],function(angular, kitt) {
  return angular.module('kitt.port', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/port/list', {
          templateUrl: 'views/port/list.html',
          controller: 'PortCtrl'
        }).when("/port/portdeatil", {
          templateUrl: 'views/port/portDetail.html',
          controller: 'PortDetailCtrl'
        })
    }).controller('PortCtrl',function($scope,$params){
      service({
        url:'/port/list',
        data:{page:$params('page',1),provinceId:$params('provinceId',-1),portName:$params('portName',"")},
        success:function(data){
          $scope.provinceList=data.provinceList;
          $scope.provinceId=data.provinceId;
          $scope.portName=data.portName;
          $scope.portList=data.portList;
          $scope.pageNum = data.portList.page;
          $scope.totalCount = data.portList.count;
          $scope.$apply();
        }
      });
      $scope.searchPort=function(){
        goto("/port/list",{page: $scope.pageNum,provinceId:$scope.provinceId,portName:$scope.portName});
      }
      $scope.pageTo=function(){
        goto('/port/list',{page: $scope.pageNum,provinceId:$scope.provinceId,portName:$scope.portName});
      };
      $scope.portDetail=function(portId){
          goto("/port/portdeatil",{portId:portId});
      }
    }).controller('PortDetailCtrl',function($scope,$params){
        $scope.confirmBtn=function(){
          $('#portPrompt').modal('hide');
        }
      //港口详细
      service({
        url: '/port/portDetail',
        data: {portId: $params('portId')},
        success: function (data) {
          $scope.port = data.port;
          $scope.currentDealer=data.currentDealer;
          $scope.allDealer=data.allDealer;
          $scope.$apply();
        }
      });
      //配置港口交易员
      $scope.addDealer=function(){
        var $option=$('#allDealerSel option:selected');
        var $remove=$option.remove();
        $remove.appendTo("#currentPortDealerSel");
      }

      $scope.deleteDealer=function(){
        var $option=$('#currentPortDealerSel option:selected');
        var $remove=$option.remove();
        $remove.appendTo("#allDealerSel");
      }
      $scope.saveDealer=function(portId){
        var newIds= "";
        $("#currentPortDealerSel > option").each(function(){
          newIds+=this.value+",";
        });
        var newParameter=newIds.substr(0,newIds.length-1);
        service({
              url: '/dealer/updatePortInDealer?dealeId='+newParameter+"&portId="+portId,
              success: function (data) {
                  if(data){
                    $("#contentInfo").text('交易员配置成功!');
                    $('#portPrompt').modal('show');
                  }else{
                    $("#contentInfo").text('交易员配置失败!');
                    $('#portPrompt').modal('show');
                  }
            }
          });
      }
    })

})




