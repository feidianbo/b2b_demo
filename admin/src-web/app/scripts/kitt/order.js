/**
 * Created by shiling on 15-1-16.
 */
define(['angular','kitt'],function(angular, kitt) {
  return angular.module('kitt.order',['kitt'])
    .config(function ($routeProvider){
        $routeProvider
          .when('/order/underway',{
            templateUrl: 'views/order/list.html',
            controller: 'dealOrder.UnderWay'
          })
          .when('/order/recheck',{
            templateUrl: 'views/order/list.html',
            controller: 'dealOrder.waitReview'
        })
        .when('/order/finished',{
          templateUrl: 'views/order/list.html',
          controller: 'dealOrder.Finish'
        })
        .when('/order/canceled',{
          templateUrl: 'views/order/list.html',
          controller: 'dealOrder.Cancel'
        })
        .when("/order/detail",{
          templateUrl:'views/order/orderDetail.html',
          controller:'dealOrder.Detail'
        })
    })

    //撮合中订单[进行中]
    .controller('dealOrder.UnderWay',function($scope,$params){
      $scope.type='underway';
      //后台区分订单类型、状态
      var statusType='matchunderway';
      var orderType="OtherOrder";
      //缓存页面上Dom元素
      var $date1=$("#date1");
      var $date2=$("#date2");
      var $createtime1 = $("#createtime1");
      var $createtime2 = $("#createtime2");
      //绑定时间控件
      $date1.datetimepicker({
        pickTime: false,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date2.datetimepicker({
        pickTime: false,
        todayBtn: false,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date1.on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
          $date2.data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date = $createtime2.val();
        if(date){
          var newDate = new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $date1.data("DateTimePicker").setMaxDate(newDate);
        }
      });
      $scope.clearDate1=function(){
        $createtime1.val('');
        $date1.data("DateTimePicker").hide();
      }
      $scope.clearDate2=function(){
        $createtime2.val('');
        $date2.data("DateTimePicker").hide();
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
      }
      function loadParams(){
        var params = {page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid};
        if($createtime1.val()!=""){
          params['startDate']=$createtime1.val();
        }
        if($createtime2.val()!=""){
          params['endDate']=$createtime2.val();
        }
        return params;
      }
      $scope.searchUnderWayOrder=function(){
        if($createtime1.val()=='' && $createtime2.val()!=''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
          return;
        }
        var params = loadParams();
        params['page']=1;
        goto('/order/underway',params);
      };
      $scope.pageToUnderWay=function(){
        goto('/order/underway',loadParams());
      };

      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),startDate:$params('startDate'),endDate:$params('endDate'),orderType:orderType,status:statusType,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderList=data.orderList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.orderList.page=data.orderList.page;
          $scope.createtime1=data.startDate;
          $scope.createtime2=data.endDate;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
          window.initAuth();
        }
      })
      $scope.exportExcel=function(){
        if($createtime2.val()!='' && $createtime1.val()==''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
        }else{
          service({
            url: '/order/datacount',
            data: loadParams(),
            success: exportFunction
          });
        }
      }
      //执行导出
      function exportFunction(count){
        if(count>20000){
          $("#alert > span:last").text('当前导出数据太多,请增加筛选条件进行导出');
          $("#alert").show();
        }else if(count<=0){
          $("#alert > span:last").text('当前没有数据，请增加筛选条件进行导出');
          $("#alert").show();
        }else{
          var url=URI("/order/exportExcel");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }
      //申请取消
      $scope.cancelUnderWayOrder=function(id){
          $("#cancelOrderDialog").modal('show');
          $scope.cancelOrderClick=function(){
          service({
            url:'/order/applyCancel',
            data:{id:id},
            success:function(data){
              $("#cancelOrderDialog").modal('hide');
              service({
                url:'/order/orderlist',
                //data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),orderType:orderType,status:statusType},
                data:loadParams(),
                success:function(data){
                  $scope.deliveryRegions=data.regionList;
                  $scope.deliveryProvinces=data.provinceList;
                  $scope.deliveryHarbours=data.harbourList;
                  $scope.orderList=data.orderList;
                  $scope.orderList.page=data.orderList.page;
                  $scope.deliveryRegion=data.deliveryRegion;
                  $scope.deliveryProvince=data.deliveryProvince;
                  $scope.deliveryHarbour=data.deliveryHarbour;
                  $scope.$apply();
                }
              });
            }
          })
        }
      }

      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion_underway").change(function(){
        if($(this).val()!=0) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $("#deliveryRegion_underway").val()},
            success: function (data) {
              $scope.deliveryProvinces = data;
              $scope.$apply();
            }
          })
        }else{
            clearHistory();
        }
      });
      $("#deliveryProvince_underway").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince_underway").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }
          })
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });
      //申请完成
      $scope.confirmUnderWayFinished=function(id,amount){
        $("#confirmOrderDialog").modal('show');
        $scope.confirmOrder=function(){
          service({
            url:'/order/applyComplete',
            data:{id:id, amount:amount},
            success:function(data){
              if(data){
                $("#confirmOrderDialog").modal('hide');
                service({
                  url: '/order/orderlist',
                  data: loadParams(),
                  success: function (data) {
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderList = data.orderList;
                    $scope.$apply();
                  }
                });
              } else{
                $("#PromptInfoDialog").modal('show');
                 location.reload();
              }
            }
          })
        }
      }
    })

    //已完成订单
    .controller('dealOrder.Finish',function($scope,$params){
      $scope.type='finished';
      //后台区分订单类型、状态
      var statusType='matchCompleteway';
      var orderType="OtherOrder";
      //缓存页面上Dom元素
      var $date1=$("#date3");
      var $date2=$("#date4");
      var $createtime1 = $("#createtime3");
      var $createtime2 = $("#createtime4");
      //绑定时间控件
      $date1.datetimepicker({
        pickTime: false,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date2.datetimepicker({
        pickTime: false,
        todayBtn: false,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date1.on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
          $date2.data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date = $createtime2.val();
        if(date){
          var newDate = new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $date1.data("DateTimePicker").setMaxDate(newDate);
        }
      });

      $scope.clearDate1=function(){
        $createtime1.val('');
        $date1.data("DateTimePicker").hide();
      }
      $scope.clearDate2=function(){
        $createtime2.val('');
        $date2.data("DateTimePicker").hide();
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
      }
      function loadParams(){
        var params = {page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid};
        if($createtime1.val()!=""){
          params['startDate']=$createtime1.val();
        }
        if($createtime2.val()!=""){
          params['endDate']=$createtime2.val();
        }
        return params;
      }
      $scope.exportExcel=function(){
        if($createtime2.val()!='' && $createtime1.val()==''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
        }else{
          service({
            url: '/order/datacount',
            data: loadParams(),
            success: exportFunction
          });
        }
      }
      //执行导出
      function exportFunction(count){
        if(count>20000){
          $("#alert > span:last").text('当前导出数据太多,请增加筛选条件进行导出');
          $("#alert").show();
        }else if(count<=0){
          $("#alert > span:last").text('当前没有数据，请增加筛选条件进行导出');
          $("#alert").show();
        }else{
          var url=URI("/order/exportExcel");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }


      $scope.searchFinishedOrder=function(){
        if($createtime1.val()=='' && $createtime2.val()!=''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
          return;
        }
        var params = loadParams();
        params['page']=1;
        goto('/order/finished',params);
      };
      $scope.pageToFinished=function(){
        goto('/order/finished',loadParams());
      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),startDate:$params('startDate'),endDate:$params('endDate'),orderType:orderType,status:statusType,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderList=data.orderList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.createtime3=data.startDate;
          $scope.createtime4=data.endDate;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
        }
      })
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion_finished").change(function(){
        if($(this).val()!=0) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $("#deliveryRegion_finished").val()},
            success: function (data) {
              $scope.deliveryProvinces = data;
              $scope.$apply();
            }
          })
        }else{
          clearHistory();
        }
      });

      $("#deliveryProvince_finished").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince_finished").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }
          })
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });
      $scope.deleteFinishedOrder=function(id){
        $("#orderId").val(id);
        $("#DeleteOrderDialog").modal('show');
      }

      $scope.deleteOrderClick=function(){
        service({
          url:'/order/deleteOrder',
          data:{id:$("#orderId").val()},
          success:function(data){
            $("#DeleteOrderDialog").modal('hide');
            service({
              url:'/order/orderlist',
              data:loadParams(),
              success:function(data){
                $scope.deliveryRegions=data.regionList;
                $scope.deliveryProvinces=data.provinceList;
                $scope.deliveryHarbours=data.harbourList;
                $scope.orderList=data.orderList;
                $scope.$apply();
              }
            });
          }
        })
      }
    })
    //待复审订单
    .controller('dealOrder.waitReview',function($scope,$params){
      $scope.type='waitReview';
      var orderType="OtherOrder";
      var statusType = "matchrecheckway";

      $scope.searchWaitReviewOrder=function(){
        goto('/order/recheck',{page:1,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderid:$scope.orderid,pid:$scope.pid});
      };
      $scope.pageToWaitReview=function(){
        goto('/order/recheck',{page:$scope.orderWaitReviewed.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderid:$scope.orderid,pid:$scope.pid});
      };
      service({
        url:'/order/recheck',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderWaitReviewed=data.orderRecheckList;  //待复审列表集合
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.orderWaitReviewed.page=data.orderRecheckList.page;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
          window.initAuth();
        }
      })
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion_waitReview").change(function(){
        if($(this).val()!=0){
          clearHistory();
          service({
            url:'/mall/getProvinces',
            data:{id:$("#deliveryRegion_waitReview").val()},
            success:function(data){
              $scope.deliveryProvinces=data;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });

      $("#deliveryProvince_waitReview").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours=null;
          $scope.deliveryHarbour=0;
          service({
            url:'/mall/getPorts',
            data:{id:$("#deliveryProvince_waitReview").val()},
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

      //确认完成
      $scope.confirmCompleted=function(id){
        $("#confirmOrderDialog").modal('show');
        $scope.confirmOrder=function(){
          service({
            url:'/mall/confirmComplete',
            data:{id:id},
            success:function(data){
              $("#confirmOrderDialog").modal('hide');
              if(data) {
                service({
                  url: '/order/recheck',
                  data: {
                    page: $params('page', 1),
                    deliveryRegion: $params('deliveryRegion', 0),
                    deliveryProvince: $params('deliveryProvince', 0),
                    deliveryHarbour: $params('deliveryHarbour', 0)
                  },
                  success: function (data) {
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderWaitReviewed = data.orderRecheckList;  //待复审列表集合
                    $scope.deliveryRegion = data.deliveryRegion;
                    $scope.deliveryProvince = data.deliveryProvince;
                    $scope.deliveryHarbour = data.deliveryHarbour;
                    $scope.orderWaitReviewed.page = data.orderRecheckList.page;
                    $scope.$apply();
                  }
                })
              } else{
              }
            }
          });
        }
      }

      $scope.verifyNotPass=function(id){
        $("#verifyNotPassDialog").modal("show");
        $scope.confirmVerifyNotPass=function(){
          service({
            url:"/order/verifyNotPass",
            data:{id:id},
            success:function(data){
              if(data){
                $("#verifyNotPassDialog").modal("hide");
                location.reload();
              } else{
              }
            }
          });
        }
      }

      //确认取消
      $scope.confirmCanceled=function(id){
        $("#cancelOrderDialog").modal('show');
        $scope.cancelOrderClick=function(){
          service({
            url:'/order/confirmCancel',
            data:{id:id},
            success:function(data){
              $("#cancelOrderDialog").modal('hide');
              if(data) {
                service({
                  url: '/order/recheck',
                  data: {
                    page: $params('page', 1),
                    deliveryRegion: $params('deliveryRegion', 0),
                    deliveryProvince: $params('deliveryProvince', 0),
                    deliveryHarbour: $params('deliveryHarbour', 0)
                  },
                  success: function (data) {
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderWaitReviewed = data.orderRecheckList;  //待复审列表集合
                    $scope.deliveryRegion = data.deliveryRegion;
                    $scope.deliveryProvince = data.deliveryProvince;
                    $scope.deliveryHarbour = data.deliveryHarbour;
                    $scope.orderWaitReviewed.page = data.orderRecheckList.page;
                    $scope.$apply();
                  }
                })
              }
            }
          });
        }
      }
})

    //已取消订单
    .controller('dealOrder.Cancel',function($scope,$params){
      $scope.type='canceled';
      var orderType="OtherOrder";
      var statusType = "cancelway";

      $scope.searchCanceledOrder=function(){
        goto('/order/canceled',{page:1,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid});
      };
      $scope.pageToCanceled=function(){
        goto('/order/canceled',{page:$scope.orderCanceled.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid});
      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),orderType:orderType,status:statusType,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderCanceled=data.orderList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.orderCanceled.page = data.orderList.page;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
        }
      })
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion_canceled").change(function(){
        if($(this).val()!=0){
          clearHistory();
          service({
            url:'/mall/getProvinces',
            data:{id:$("#deliveryRegion_canceled").val()},
            success:function(data){
              $scope.deliveryProvinces=data;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince_canceled").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince_canceled").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }
          })
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });

      $scope.deleteCanceledOrder=function(id){
        $("#orderId").val(id);
        $("#DeleteOrderDialog").modal('show');
      }

      $scope.deleteOrderClick=function(){
        service({
          url:'/order/deleteOrder',
          data:{id:$("#orderId").val()},
          success:function(data){
            $("#DeleteOrderDialog").modal('hide');
            service({
              url:'/order/canceled',
              data:{page:$params('page',1)},
              success:function(data){
                $scope.deliveryRegions=data.regionList;
                $scope.deliveryProvinces=data.provinceList;
                $scope.deliveryHarbours=data.harbourList;
                $scope.orderCanceled=data.orderCanceledList;
                $scope.$apply();
              }
            });
          }
        })
      }
    })

    //查看详情
    .controller('dealOrder.Detail',function($scope,$params){
      service({
        url: '/order/detail',
        data: {id: $params('id')},
        success: function (data) {
          if(data.success) {
            $scope.order = data.order;
            $scope.buyer=data.buyer;
            $scope.seller=data.seller;
            $scope.buyCompany=data.buyCompany;
            $scope.$apply();
          } else{
          }
        }
      });

      $scope.back=function(){
        history.go(-1);
      }
    })
});
