define(['angular', 'kitt', 'kitt/user','kitt/article','kitt/supply','kitt/demand','kitt/certificate','kitt/dealer','kitt/order','kitt/customer','kitt/finance','kitt/timeTask','kitt/supplier','kitt/mallOrder','kitt/chart','kitt/operateauth','kitt/system/menu','kitt/system/configUser', 'kitt/system/center','kitt/port','kitt/android'],function(angular, kitt){
  return angular.module('index', ['kitt', 'kitt.user','kitt.article','kitt.supply','kitt.demand','kitt.certificate','kitt.dealer','kitt.order','kitt.customer','kitt.finance','kitt.timeTask','kitt.supplier','kitt.mallOrder','kitt.chart','kitt.operateauth','kitt.menu','kitt.system.configUser', 'kitt.center','kitt.port','kitt.android'])
    .config(function($routeProvider){
      $routeProvider
        .when('/', {
          templateUrl:'views/index.html',
          controller:'index.Index'})
        .otherwise({
          redirectTo:'/'
        })
    })
    .controller('index.Index', function($scope,$route){
        /*service({
          url:'/admin/getUserAuth',
          success:function(data){
           var operatecodeList = data.operatecodeList;
            if(operatecodeList != null) {
              window.operatecodeList = operatecodeList;
            }
          }
        });*/

    });

});
