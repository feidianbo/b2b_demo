/**
 * Created by shiling on 15-02-04.
 */
define(['angular','kitt'],function(angular, kitt) {
  return angular.module('kitt.mallOrder',['kitt'])
    .config(function ($routeProvider){
      $routeProvider
        .when('/mall/underway',{
          templateUrl: 'views/order/mallOrderList.html',
          controller: 'mallOrder.UnderWay'
        })
        .when('/mall/returning',{
          templateUrl: 'views/order/mallOrderList.html',
          controller: 'mallOrder.Returning'
        })
        .when('/mall/recheck',{
          templateUrl: 'views/order/mallOrderList.html',
          controller: 'mallOrder.waitReview'
        })
        .when('/mall/finished',{
          templateUrl: 'views/order/mallOrderList.html',
          controller: 'mallOrder.Finish'
        })
        .when('/mall/canceled',{
          templateUrl: 'views/order/mallOrderList.html',
          controller: 'mallOrder.Cancel'
        })
        .when("/mall/detail",{
          templateUrl:'views/order/orderDetail.html',
          controller:'mallOrder.Detail'
        })
    })

    //商城订单[进行中]
    .controller('mallOrder.UnderWay',function($scope,$params){
      $scope.type ="underway";
      //后台区分订单类型、状态
      var statusType='underway';
      var orderType="MallOrder";
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
        $scope.createtime1=null;
      }
      $scope.clearDate2=function(){
        $createtime2.val('');
        $date2.data("DateTimePicker").hide();
        $scope.createtime2=null;
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
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
      $scope.searchUnderWayMallOrder=function(){
        if($createtime1.val()=='' && $createtime2.val()!=''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
          return;
        }
        var params = loadParams();
        params['page']=1;
        goto('/mall/underway',params);
      };
      $scope.pageToMallUnderWay=function(){
        goto('/mall/underway',loadParams());

      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),startDate:$params('startDate'),endDate:$params('endDate'),orderid:$params('orderid',''),pid:$params('pid',''),orderType:orderType,status:statusType},
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
          });
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
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });

      //申请完成
      $scope.applyFinished=function(id){
        $("#finishOrderDialog").modal('show');
        $scope.confirmApplyFinished=function(){
          service({
            url:'/mall/applyComplete',
            data:{id:id},
            success:function(data){
              if(data) {
                location.reload();
              }
            }
          });
        }
      }
    })
    //退货中订单
    .controller('mallOrder.Returning',function($scope,$params){
      $scope.type='returning';
      //后台区分订单类型、状态
      var statusType='returnway';
      var orderType="MallOrder";
      var $date3=$("#date3");
      var $date4=$("#date4");
      var $createtime3 = $("#createtime3");
      var $createtime4 = $("#createtime4");
      //绑定时间控件
      $date3.datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date4.datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date3.on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
          $date4.data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date = $date4.val();
        if(date){
          var newDate = new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $date3.data("DateTimePicker").setMaxDate(newDate);
        }
      });

      $scope.clearDate1=function(){
        $createtime3.val('');
        $date3.data("DateTimePicker").hide();
        $scope.createtime3=null;
      }
      $scope.clearDate2=function(){
        $createtime4.val('');
        $date4.data("DateTimePicker").hide();
        $scope.createtime4=null;
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
      }
      function loadParams(){
        var params = {page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid};
        if($createtime3.val()!=""){
          params['startDate']=$createtime3.val();
        }
        if($createtime4.val()!=""){
          params['endDate']=$createtime4.val();
        }
        return params;
      }
      $scope.searchReturningOrder=function(){
        if($createtime3.val()=='' && $createtime4.val()!=''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
          return;
        }
        var params =loadParams();
        params['page']=1;
        goto('/mall/returning',params);
      };
      $scope.pageToReturning=function(){
        goto('/mall/returning',loadParams());
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
          $scope.createtime3=data.startDate;
          $scope.createtime4=data.endDate;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
          window.initAuth();
        }
      });
      $scope.exportExcel=function(){
        if($createtime4.val()!='' && $createtime3.val()==''){
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
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion_returning").change(function(){
        if($(this).val()!=0) {
            clearHistory();
            service({
              url: '/mall/getProvinces',
              data: {id: $("#deliveryRegion_returning").val()},
              success: function (data) {
                $scope.deliveryProvinces = data;
                $scope.$apply();
              }
            });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince_returning").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince_returning").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });

      //提报退货申请
      $scope.reportReturnApply=function(id){
        $("#applyReturnDialog").modal('show');
        $scope.confirmApplyReturn=function(){
          service({
            url:'/mall/applyReturn',
            data:{id:id},
            success:function(data) {
              if(data) {
                service({
                  url: '/order/orderlist',
                  data:loadParams(),
                  success: function (data) {
                    $("#applyReturnDialog").modal('hide');
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderList = data.orderList;
                    $scope.deliveryRegion = data.deliveryRegion;
                    $scope.deliveryProvince = data.deliveryProvince;
                    $scope.deliveryHarbour = data.deliveryHarbour;
                    $scope.orderList.page = data.orderList.page;
                    $scope.$apply();
                  }
                })
              }
            }
          });
        }
      }
    })
    //已完成订单
    .controller('mallOrder.Finish', function($scope,$params){
      $scope.type='finished';
      var orderType="MallOrder";
      var statusType="completeway";
      var $date5=$("#date5");
      var $date6=$("#date6");
      var $createtime5 = $("#createtime5");
      var $createtime6 = $("#createtime6");
      //绑定时间控件
      $date5.datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date6.datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $date5.on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
          $date6.data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date = $("#createtime6").val();
        if(date){
          var newDate = new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $date5.data("DateTimePicker").setMaxDate(newDate);
        }
      });

      $scope.clearDate1=function(){
        $createtime5.val('');
        $date5.data("DateTimePicker").hide();
      }
      $scope.clearDate2=function(){
        $createtime6.val('');
        $date6.data("DateTimePicker").hide();
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
      }

      function loadParams(){
        var params = {page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid};
        if($createtime5.val()!=""){
          params['startDate']=$createtime5.val();
        }
        if($createtime6.val()!=""){
          params['endDate']=$createtime6.val();
        }
        return params;
      }
      $scope.exportExcel=function(){
        if($createtime6.val()!='' && $createtime5.val()==''){
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
        if($createtime5.val()=='' && $createtime6.val()!=''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
          return;
        }
        var params = loadParams();
        params['page']=1;
        goto('/mall/finished',params);

      };
      $scope.pageToFinished=function(){
        goto('/mall/finished',loadParams());
      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),startDate:$params('startDate'),endDate:$params('endDate'),orderType:orderType,status:statusType,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderList=data.orderList;
          $scope.orderList.page=data.orderList.page;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.createtime5=data.startDate;
          $scope.createtime6=data.endDate;
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
          $scope.deliveryHarbours=null;
          $scope.deliveryHarbour=0;
            service({
            url:'/mall/getPorts',
            data:{id:$("#deliveryProvince_finished").val()},
            success:function(data){
              $scope.deliveryHarbours=data;
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
            if(data) {
              $("#DeleteOrderDialog").modal('hide');
              service({
                url: '/order/orderlist',
                data:loadParams(),
                success: function (data) {
                  $scope.deliveryRegions = data.regionList;
                  $scope.deliveryProvinces = data.provinceList;
                  $scope.deliveryHarbours = data.harbourList;
                  $scope.orderList = data.orderList;
                  $scope.$apply();
                }
              });
            }
          }
        })
      }
    })
    //待复审订单
    .controller('mallOrder.waitReview',function($scope,$params){
      $scope.type='waitReview';
      var orderType="MallOrder";
      var orderStatus="recheckway";
      $scope.searchWaitReviewOrder=function(){
        goto('/mall/recheck',{page:1,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:orderStatus,orderid:$scope.orderid,pid:$scope.pid});
      };
      $scope.pageToWaitReview=function(){
        goto('/mall/recheck',{page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:orderStatus,orderid:$scope.orderid,pid:$scope.pid});
      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),orderType:orderType,status:orderStatus,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderList=data.orderList;  //待复审列表集合
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.orderList.page=data.orderList.page;
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
      $("#deliveryRegion_waitReviewed").change(function(){
        if($(this).val()!=0) {
            clearHistory();
            service({
              url:'/mall/getProvinces',
              data:{id:$("#deliveryRegion_waitReviewed").val()},
              success:function(data){
                $scope.deliveryProvinces=data;
                $scope.$apply();
              }
            });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince_waitReviewed").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince_waitReviewed").val()},
            success: function (data) {
              $scope.deliveryHarbours = data;
              $scope.$apply();
            }
          });
        }else{
          $scope.deliveryHarbours=null;
          $scope.deliveryProvince=0;
          $scope.deliveryHarbour=0;
        }
      });

      //确认退货
      $scope.confirmReturn=function(id){
        $("#confirmReturnDialog").modal('show');
        $scope.confirmReturnGoods=function(){
          service({
            url:'/mall/confirmReturn',
            data:{id:id},
            success:function(data) {
              if(data) {
                service({
                  url: '/order/orderlist',
                  data: {
                    page: $params('page', 1),
                    deliveryRegion: $params('deliveryRegion', 0),
                    deliveryProvince: $params('deliveryProvince', 0),
                    deliveryHarbour: $params('deliveryHarbour', 0)
                  },
                  success: function (data) {
                    $("#confirmReturnDialog").modal('hide');
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderList = data.orderList;  //待复审列表集合
                    $scope.deliveryRegion = data.deliveryRegion;
                    $scope.deliveryProvince = data.deliveryProvince;
                    $scope.deliveryHarbour = data.deliveryHarbour;
                    $scope.orderList.page = data.orderList.page;
                    $scope.$apply();
                  }
                })
              } else{
              }
            }
          });
        }
      }

      //确认完成
      $scope.confirmCompleted=function(id){
        $("#confirmWaitReviewedDialog").modal('show');
        $scope.confirmWaitReviewed=function(){
          service({
            url:'/mall/confirmComplete',
            data:{id:id},
            success:function(data) {
              if (data) {
                service({
                  url: '/order/orderlist',
                  data: {page: $params('page', 1), deliveryRegion: $params('deliveryRegion', 0), deliveryProvince: $params('deliveryProvince', 0), deliveryHarbour: $params('deliveryHarbour', 0),orderType:orderType,status:orderStatus},
                  success: function (data) {
                    $("#confirmWaitReviewedDialog").modal('hide');
                    $scope.deliveryRegions = data.regionList;
                    $scope.deliveryProvinces = data.provinceList;
                    $scope.deliveryHarbours = data.harbourList;
                    $scope.orderList = data.orderList;  //待复审列表集合
                    $scope.deliveryRegion = data.deliveryRegion;
                    $scope.deliveryProvince = data.deliveryProvince;
                    $scope.deliveryHarbour = data.deliveryHarbour;
                    $scope.orderList.page=data.orderList.page;
                    $scope.$apply();
                  }
                })
              }
            }
          });
        }
      }
      //复审未通过
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
              }
            }
          });
        }
      }
    })
    //已取消订单
    .controller('mallOrder.Cancel',function($scope,$params){
      $scope.type='canceled';
      var orderType ="MallOrder";
      var statusType = "cancelway";
      $scope.searchCanceledOrder=function(){
        goto('/mall/canceled',{page:1,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid});
      };
      $scope.pageToCanceled=function(){
        goto('/mall/canceled',{page:$scope.orderList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,orderType:orderType,status:statusType,orderid:$scope.orderid,pid:$scope.pid});
      };
      service({
        url:'/order/orderlist',
        data:{page:$params('page',1), deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),orderType:orderType,status:statusType,orderid:$params('orderid',''),pid:$params('pid','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.orderList=data.orderList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.orderid=data.orderid;
          $scope.pid=data.pid;
          $scope.$apply();
        }
      })

      $("#deliveryRegion_canceled").change(function(){
        if($(this).val()!=0) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $("#deliveryRegion_canceled").val()},
            success: function (data) {
              $scope.deliveryProvinces = data;
              $scope.$apply();
            }
          })
        }else{
          clearHistory();
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

      $("#deliveryProvince_canceled").change(function(){
          if($(this).val()!=0) {
            $scope.deliveryHarbours = null;
            $scope.deliveryHarbour = 0;
            service({
              url: '/mall/getPorts',
              data: {id: $("#deliveryProvince_canceled").val()},
              success: function (data) {$scope.deliveryHarbours = data;
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
            location.reload();
            //service({
            //  url:'/order/orderList',
            //  data: {page: $params('page', 1), deliveryRegion: $params('deliveryRegion', 0), deliveryProvince: $params('deliveryProvince', 0), deliveryHarbour: $params('deliveryHarbour', 0)},
            //  success:function(data) {
            //          $scope.deliveryRegions = data.regionList;
            //          $scope.deliveryProvinces = data.provinceList;
            //          $scope.deliveryHarbours = data.harbourList;
            //          $scope.orderList = data.orderList;
            //          $scope.deliveryRegion = data.deliveryRegion;
            //          $scope.deliveryProvince = data.deliveryProvince;
            //          $scope.deliveryHarbour = data.deliveryHarbour;
            //          $scope.$apply();
            //  }
            //});
          }
        })
      }
    })
    //查看详情
    .controller('mallOrder.Detail',function($scope,$params){
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

