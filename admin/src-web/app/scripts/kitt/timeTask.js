/**
 * Created by fanjun on 15-1-27.
 */
define(['angular','kitt'],function(angular, kitt){
  return angular.module('kitt.timeTask', ['kitt'])
    .config(function($routeProvider){
    $routeProvider
      .when("/timeTask",{
      templateUrl:'views/task/list.html',
      controller:'TimeTaskCtrl'
    })
  })

    .controller('TimeTaskCtrl',function($scope,$params) {
      $scope.confirmStart=function(){
        $("#startTasksConfirmDialog").modal('show');
      };

      $scope.startTimeTasks=function(){
        $("#startTasksConfirmDialog").modal('hide');
        service({
          url:'/startTimeTasks',
          success:function(data){
            alert("执行成功");
          }
        })
      }
  })

});
