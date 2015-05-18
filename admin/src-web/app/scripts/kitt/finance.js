
define(['angular','kitt','modal'],function(angular, kitt){
  return angular.module('kitt.finance', ['kitt']).config(function($routeProvider){
      $routeProvider.when("/finance",{
          templateUrl:'views/finance/list.html',
          controller:'FinanceCustomerCtrl'
        })
    }).controller('FinanceCustomerCtrl',function($scope,$params) {

      $scope.pageTo=function(){
        goto('/finance', {page:$scope.finance.page});
      };
      service({
        url:'/finance/list',
        data:{page:$params('page',1)},
        success:function(data){
          $scope.finance=data.finance;
          $scope.page=data.finance.page;
          $scope.$apply();
        }
      });

    $scope.exportExcel=function(obj){
      window.location.href='/finance/exportExcel?scope='+obj;
    }

    })
});
