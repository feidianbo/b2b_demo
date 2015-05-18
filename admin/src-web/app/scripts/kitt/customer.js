define(['angular', 'kitt', 'modal','bootstrap3-datetimepicker'], function (angular, kitt) {
  return angular.module('kitt.customer', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when("/customer/customerlist", {
          templateUrl: 'views/customer/customerlist.html',
          controller: 'CustomerCtrl'
        }).when("/customer/customerdeatil", {
          templateUrl: 'views/customer/customerdetail.html',
          controller: 'CustomerDetailCtrl'
        }).when("/customer/orderlist", {
          templateUrl: 'views/customer/orderlist.html',
          controller: 'OrderListCtrl'
        }).when("/customer/demandlist",{
          templateUrl: 'views/customer/demandlist.html',
          controller: 'DemandListCtrl'
        }).when("/customer/supplylist",{
          templateUrl: 'views/customer/supplylist.html',
          controller: 'SupplyListCtrl'
        }).when("/customer/quotelist",{
          templateUrl: 'views/customer/quotelist.html',
          controller: 'QuoteListCtrl'
        }).when("/customer/manualsellIn",{
          templateUrl: 'views/customer/manualsell_in.html',
          controller: 'ManualsellInCtrl'
        }).when("/customer/manualsellout",{
          templateUrl: 'views/customer/manualsell_out.html',
          controller: 'ManualselloutCtrl'
        }).when("/customer/quotedeatil",{
          templateUrl: 'views/customer/quotedetail.html',
          controller: 'QuoteDetailCtrl'
        }).when("/customer/bidquote",{
          templateUrl: 'views/customer/bidquote.html',
          controller: 'InQuoteCtrl'
        }).when("/customer/manualsellDetail",{
          templateUrl: 'views/customer/manualselldetail.html',
          controller: 'ManualsellDetailCtrl'
        }).when("/customer/bringdeal_sellinfo",{
          templateUrl: 'views/customer/bringdeal_supply.html',
          controller: 'BringDealSupplyInfoCtrl'
        }).when("/customer/bringdeal_demand",{
          templateUrl: 'views/customer/bringdeal_demand.html',
          controller: 'BringDealDemandInfoCtrl'
        }).when("/customer/customersearch",{
          templateUrl: 'views/customer/customersearch.html',
          controller: 'CustomerSearchCtrl'
        }).when("/customer/teamorderqualification",{
          templateUrl:'views/customer/teamqualification.html',
          controller:"TeamQualificationCtrl"
        }).when("/customer/teamorder",{
          templateUrl:'views/customer/teamorder.html',
          controller:"TeamOrderCtrl"
        }).when("/customer/teamorderdetail",{
          templateUrl:'views/customer/teamorderdetail.html',
          controller:"TeamOrderDetailCtrl"
        })
    })
    .controller('CustomerCtrl', function ($scope, $params) {
      //分页
      $scope.pageTo = function () {
        goto('/customer/customerlist', {page: $scope.pageNum});
      }
      //显示客户列表
      service({
        url: '/customer/list',
        data: {page: $params('page', 1)},
        success: function (data) {
          $scope.customerList = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
      $scope.showCustomerlist=function(id){
        goto("/customer/customerdeatil?id="+id)
      }

    })//显示客户详细信息
    .controller('CustomerDetailCtrl', function ($scope, $params) {
      $scope.back = function () {
        //返回上一步
        history.go(-1);
      }
      service({
        url: '/customer/showCustomerDetail',
        data: {id: $params('id')},
        success: function (data) {
          $scope.customer = data;
          $scope.$apply();
        }
      })
    })
    .controller("OrderListCtrl", function ($scope, $params) {
      $scope.pageTo = function () {
        var requestParam = {page:$scope.pageNum,paytype:$params("paytype"),status:$params("status")};
        goto("/customer/orderlist",requestParam);
      }
      //查询未签合同
      $scope.searchUnsign=function(){
        goto("/customer/orderlist",{status:'WaitVerify'})
      };
      //查询待支付
      $scope.searchUnpay=function(){
        goto("/customer/orderlist",{status:'WaitPayment'})
      };
      //已支付全部货款
      $scope.searchPayAll=function(){
        goto("/customer/orderlist",{paytype:'PayTheWhole'})
      };
      $scope.searchPaybalance=function(){
        goto("/customer/orderlist",{status:'WaitBalancePayment'})
      };
      service({
        url: '/customer/orderlist',
        data: {page: $params('page',1),status:$params('status'),paytype:$params('paytype')},
        success: function (data) {
          $scope.orderlist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("DemandListCtrl", function ($scope, $params) {
      //分页
      $scope.pageTo = function () {
        var requestParam = {page:$scope.pageNum,checkstatus:$params("checkstatus")};
        goto("/customer/demandlist",requestParam);
      }
      //查询未签合同
      $scope.searchUncheck=function(){
        goto("/customer/demandlist",{checkstatus:'待审核'});
      };
      //查询待支付
      $scope.searchUnpass=function(){
        goto("/customer/demandlist",{checkstatus:'审核未通过'});
      };
      //已支付全部货款
      $scope.searchInQuote=function(){
        goto("/customer/demandlist",{checkstatus:'报价中'});
      };
      $scope.searchInMatch=function(){
        goto("/customer/demandlist",{checkstatus:'匹配中'});
      };
      $scope.searchIndeal=function(){
        goto("/customer/demandlist",{checkstatus:'交易结束'});
      };
      service({
        url: '/customer/demandlist',
        data: {page: $params('page', 1),checkstatus:$params("checkstatus")},
        success: function (data) {
          $scope.demandlist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("SupplyListCtrl", function ($scope, $params) {
      //分页
      $scope.pageTo = function () {
        goto('/customer/supplylist', {page: $scope.pageNum});
      }
      service({
        url: '/customer/supplylist',
        data: {page: $params('page', 1)},
        success: function (data) {
          $scope.supplylist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("QuoteListCtrl", function ($scope, $params) {
      $scope.pageTo = function () {
        var requestParam = {page:$scope.pageNum,status:$params("status")};
        goto("/customer/quotelist",requestParam);
      }
      $scope.searchInQuote=function(){
        goto("/customer/quotelist",{status:"报价中"});
      }
      $scope.searchCompare=function(){
        goto("/customer/quotelist",{status:"比价中"});
      }
      $scope.searchSuccess=function(){
        goto("/customer/quotelist",{status: "已中标"});
      }
      $scope.searchFail=function(){
        goto("/customer/quotelist",{status: "未中标"});
      }
      service({
        url: '/customer/quotelist',
        data: {page: $params('page', 1),status: $params("status")},
        success: function (data) {
          $scope.quotelist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("ManualsellInCtrl", function ($scope, $params) {
      //加载委托人工找货信息信息
      service({
        url: '/customer/manualsell',
        data: {type:true,page: $params('page', 1)},
        success: function (data) {
          $scope.deliveryRegions=data.areas;
          $scope.manualselllist = data.manualselllist.list;
          $scope.pageNum = data.manualselllist.page;
          $scope.totalCount = data.manualselllist.count;
          $scope.$apply();
        }
      });

      $('#date1').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $('#date2').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $('#date1').on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
           $('#date2').data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date = $("#deliveryEndDate").val();
        if(date){
          var newDate = new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $("#date1").data("DateTimePicker").setMaxDate(newDate);
        }
      });

      $scope.clearDate1=function(){
        $("#deliveryStartDate").val('');
        $('#date1').data("DateTimePicker").hide();
      }
      $scope.clearDate2=function(){
        $("#deliveryEndDate").val('');
        $('#date2').data("DateTimePicker").hide();
      }
      $scope.closeAlert=function(){
          $("#alert").hide();
      }
      //判断参数
      function loadParams(){
        return  {page:$scope.pageNum==undefined?1:$scope.pageNum,
          deliveryDistrict:$scope.deliveryregion==undefined?undefined:$scope.deliveryregion.name,
          deliveryProvince:$scope.deliveryprovince==undefined?undefined:$scope.deliveryprovince.name,
          deliveryAddr:$scope.deliveryport==undefined?undefined:$scope.deliveryport.name,
          type:true,
          deliveryStartDate:$("#deliveryStartDate").val(),
          deliveryEndDate:$("#deliveryEndDate").val()
        };
      }
      //分页
      $scope.pageTo = function () {
        service({
          url: '/customer/manualsell',
          data: loadParams(),
          success: function (data) {
            $scope.manualselllist = data.manualselllist.list;
            $scope.pageNum = data.manualselllist.page;
            $scope.totalCount = data.manualselllist.count;
            $scope.$apply();
          }
        });
      }
      //调用查询按钮
      $scope.searchmanuasell=function(){
        if($("#deliveryEndDate").val()!='' && $("#deliveryStartDate").val()==''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
        }else{
          service({
            url: '/customer/manualsell',
            data: loadParams(),
            success: function (data) {
              $scope.manualselllist = data.manualselllist.list;
              $scope.pageNum = data.manualselllist.page;
              $scope.totalCount = data.manualselllist.count;
              $scope.$apply();
            }
          });
        }
      }

      $scope.exportExcel=function(){
        if($("#deliveryEndDate").val()!='' && $("#deliveryStartDate").val()==''){
          $("#alert").show();
        }else{
          service({
            url: '/customer/countmanualsell',
            data: loadParams(),
            success: exportFunction
          });
        }
      }
      //执行导出
      function exportFunction(data){
        if(data>20000){
         $("#alert > span:last").text('当前导出数据太多,请增加筛选条件进行导出');
          $("#alert").show();
        }else if(data<=0){
          $("#alert > span:last").text('当前没有数据，请增加筛选条件进行导出');
          $("#alert").show();
        }else{
          var url=URI("/customer/downloadmanualsell");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }

      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinceslist=null;
        $scope.deliveryprovince=null;
        $scope.deliveryPortList=null;
        $scope.deliveryport=null;
      }
//区域下拉框级联
      $scope.changeRegion=function(){
        if($scope.deliveryregion!=null) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $scope.deliveryregion.id},
            success: function (data) {
              $scope.deliveryProvinceslist = data;
              $scope.$apply();
            }
          });
        }else{
          //区域选择全部时情况，刷新省份列表
          clearHistory();
        }
      }
      $scope.changeProvince=function(){
        if($scope.deliveryprovince!=null){
          $scope.deliveryport=null;
          service({
            url:'/mall/getPorts',
            data:{id:$scope.deliveryprovince.id},
            success:function(data){
              $scope.deliveryPortList=data;
              $scope.$apply();
            }
          });
        }else{
          //省份选择全部时，刷新港口列表
          $scope.deliveryPortList=null;
          $scope.deliveryport=null;
        }
      }
    })
    .controller("ManualselloutCtrl", function ($scope, $params) {
      //加载委托人工找货信息信息
      //加载委托人工找货信息信息
      service({
        url: '/customer/manualsell',
        data: {type:true,page: $params('page', 1)},
        success: function (data) {
          $scope.deliveryRegions=data.areas;
          $scope.manualselllist = data.manualselllist.list;
          $scope.pageNum = data.manualselllist.page;
          $scope.totalCount = data.manualselllist.count;
          $scope.$apply();
        }
      });

      $('#date1').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $('#date2').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn',
        format:'YYYY-MM-DD'
      });
      $('#date1').on("dp.change",function(e){
        if(e.date){
          var newDate = new Date(e.date);
          newDate.setDate(newDate.getDate() + 1);
          $('#date2').data("DateTimePicker").setMinDate(newDate);
        }
      }).on("dp.show",function(){
        var date=$("#deliveryEndDate").val();
        if(date){
          var newDate =new Date(date);
          newDate.setDate(newDate.getDate()-1);
          $(this).data("DateTimePicker").setMaxDate(newDate);
        }
      });

      $scope.clearDate1=function(){
        $("#deliveryStartDate").val('');
        $('#date1').data("DateTimePicker").hide();
      }
      $scope.clearDate2=function(){
        $("#deliveryEndDate").val('');
        $('#date2').data("DateTimePicker").hide();
      }
      $scope.closeAlert=function(){
        $("#alert").hide();
      }
      //判断参数
      function loadParams(){
        return  {page:$scope.pageNum==undefined?1:$scope.pageNum,
          deliveryDistrict:$scope.deliveryregion==undefined?undefined:$scope.deliveryregion.name,
          deliveryProvince:$scope.deliveryprovince==undefined?undefined:$scope.deliveryprovince.name,
          deliveryAddr:$scope.deliveryport==undefined?undefined:$scope.deliveryport.name,
          type:false,
          deliveryStartDate:$("#deliveryStartDate").val(),
          deliveryEndDate:$("#deliveryEndDate").val()
        };
      }
      //分页
      $scope.pageTo = function () {
        service({
          url: '/customer/manualsell',
          data: loadParams(),
          success: function (data) {
            $scope.manualselllist = data.manualselllist.list;
            $scope.pageNum = data.manualselllist.page;
            $scope.totalCount = data.manualselllist.count;
            $scope.$apply();
          }
        });
      }
      //调用查询按钮
      $scope.searchmanuasell=function(){
        if($("#deliveryEndDate").val()!='' && $("#deliveryStartDate").val()==''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
        }else{
          service({
            url: '/customer/manualsell',
            data: loadParams(),
            success: function (data) {
              $scope.manualselllist = data.manualselllist.list;
              $scope.pageNum = data.manualselllist.page;
              $scope.totalCount = data.manualselllist.count;
              $scope.$apply();
            }
          });
        }
      }

      $scope.exportExcel=function(){
        if($("#deliveryEndDate").val()!='' && $("#deliveryStartDate").val()==''){
          $("#alert").show();
        }else{
          service({
            url: '/customer/countmanualsell',
            data: loadParams(),
            success: exportFunction
          });
        }
      }
      //执行导出
      function exportFunction(data){
        if(data>20000){
          $("#alert > span:last").text('当前导出数据太多,请增加筛选条件进行导出');
          $("#alert").show();
        }else if(data<=0){
          $("#alert > span:last").text('当前没有数据，请增加筛选条件进行导出');
          $("#alert").show();
        }else{
          var url=URI("/customer/downloadmanualsell");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }

      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinceslist=null;
        $scope.deliveryprovince=null;
        $scope.deliveryPortList=null;
        $scope.deliveryport=null;
      }
//区域下拉框级联
      $scope.changeRegion=function(){
        if($scope.deliveryregion!=null) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $scope.deliveryregion.id},
            success: function (data) {
              $scope.deliveryProvinceslist = data;
              $scope.$apply();
            }
          });
        }else{
          //区域选择全部时情况，刷新省份列表
          clearHistory();
        }
      }
      $scope.changeProvince=function(){
        if($scope.deliveryprovince!=null){
          $scope.deliveryport=null;
          service({
            url:'/mall/getPorts',
            data:{id:$scope.deliveryprovince.id},
            success:function(data){
              $scope.deliveryPortList=data;
              $scope.$apply();
            }
          });
        }else{
          //省份选择全部时，刷新港口列表
          $scope.deliveryPortList=null;
          $scope.deliveryport=null;
        }
      }
    })
    .controller("QuoteDetailCtrl", function ($scope, $params) {
      $scope.pageTo = function () {
        goto("/customer/quotedetail",{demandcode:$params('demandcode')});
      }
      service({
        url: '/customer/quoteresult',
        data: {page: $params('page', 1),demandcode: $params('demandcode')},
        success: function (data) {
          $scope.quotelist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("InQuoteCtrl", function ($scope, $params) {
      $scope.pageTo = function () {
        goto("/customer/bidquote",{demandcode:$params('demandcode')});
      }
      service({
        url: '/customer/inbidquote',
        data: {page: $params('page', 1),demandcode: $params('demandcode')},
        success: function (data) {
          $scope.quotelist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
    })
    .controller("ManualsellDetailCtrl", function ($scope, $params) {
      service({
        url: '/customer/manualselldetail/'+$params('id'),
        success: function (data) {
          $scope.manualsell = data;
          $scope.$apply();
        }
      })
    })
    .controller("BringDealSupplyInfoCtrl",function($scope,$params){
      //存放表单数据
      $scope.formData={};
      //页面加载时，准备基础数据
      service({
        url: '/customer/bringdealsellinfo',
        data: {page: $params('page', 1)},
        success: function (data) {
          $scope.deliveryRegions=data.area;
          $scope.sellinfolist = data.sellinfolist.list;
          $scope.deliveryModes = data.deliverymodeList;
          $scope.cocaltypeList=data.cocaltypeList;
          $scope.pageNum = data.sellinfolist.page;
          $scope.totalCount = data.sellinfolist.count;
          $scope.$apply();
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinceslist=null;
        $scope.formData.deliveryprovince=null;
        $scope.deliveryPortList=null;
        $scope.formData.deliveryplace=null;
      }
      //区域下拉框级联
      $scope.changeRegion=function(){
        if($scope.formData.deliveryregion!=null) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $scope.formData.deliveryregion.id},
            success: function (data) {
              $scope.deliveryProvinceslist = data;
              $scope.$apply();
            }
          });
        }else{
          //区域选择全部时情况，刷新省份列表
          clearHistory();
        }
      }
      $scope.changeProvince=function(){
        if($scope.formData.deliveryprovince!=null){
          $scope.formData.deliveryplace=null;
          service({
            url:'/mall/getPorts',
            data:{id:$scope.formData.deliveryprovince.id},
            success:function(data){
              $scope.deliveryPortList=data;
              $scope.$apply();
            }
          });
        }else{
          //省份选择全部时，刷新港口列表
          $scope.deliveryPortList=null;
          $scope.formData.deliveryplace=null;
        }
      }
      function replaceJson(props){
        //获取当前$scope上表单值的拷贝，和页面上数据绑定的$scope区分开。
        var newFormData ={};
        angular.copy($scope.formData,newFormData);
        angular.forEach(props,function(key,value){
          if(newFormData[key]!=undefined){
            newFormData[key]=newFormData[key].name;
          }
        });
        newFormData['page']=$scope.pageNum;
        return newFormData;
      }
      // 处理表单提交
      $scope.processForm=function(){
        service({
          url:"/customer/bringdealsellinfo",
          data:replaceJson(['deliveryregion','deliveryprovince','deliveryplace']),
          success:function(data){
            $scope.sellinfolist = data.sellinfolist.list;
            $scope.pageNum = data.sellinfolist.page;
            $scope.totalCount = data.sellinfolist.count;
            $scope.$apply();
          }
        });
      }
      $scope.pageTo=function(){
        service({
          url:"/customer/bringdealsellinfo",
          data:replaceJson(['deliveryregion','deliveryprovince','deliveryplace']),
          success:function(data){
            $scope.sellinfolist = data.sellinfolist.list;
            $scope.pageNum = data.sellinfolist.page;
            $scope.totalCount = data.sellinfolist.count;
            $scope.$apply();
          }
        });
      }
    })
    .controller("CustomerSearchCtrl",function($scope,$params){
      //搜索按钮
      $scope.searchCustomer=function(){
        if($scope.keyword!=undefined){
          service({
            url:'/customer/searchCustomer?keyword='+$scope.keyword,
            success: function (data) {
              $scope.customer = data;
              $scope.keyword=$scope.keyword;
              $scope.$apply();
            }
          })
        }else{
          alert("请输入信息进行查询");
        }
      }

    })
    .controller("BringDealDemandInfoCtrl",function($scope,$params){
      //存放表单数据
      $scope.formData={};
      //页面加载时，准备基础数据
      service({
        url: '/customer/bringdealdemandinfo',
        data: {page: $params('page', 1)},
        success: function (data) {
          $scope.deliveryRegions=data.area;
          $scope.demandlist = data.demandlist.list;
          $scope.deliveryModes = data.deliverymodeList;
          $scope.cocaltypeList=data.cocaltypeList;
          $scope.pageNum = data.demandlist.page;
          $scope.totalCount = data.demandlist.count;
          $scope.$apply();
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinceslist=null;
        $scope.formData.deliveryprovince=null;
        $scope.deliveryPortList=null;
        $scope.formData.deliveryplace=null;
      }
      //区域下拉框级联
      $scope.changeRegion=function(){
        if($scope.formData.deliverydistrict!=null) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $scope.formData.deliverydistrict.id},
            success: function (data) {
              $scope.deliveryProvinceslist = data;
              $scope.$apply();
            }
          });
        }else{
          //区域选择全部时情况，刷新省份列表
          clearHistory();
        }
      }
      $scope.changeProvince=function(){
        if($scope.formData.deliveryprovince!=null){
          $scope.formData.deliveryplace=null;
          service({
            url:'/mall/getPorts',
            data:{id:$scope.formData.deliveryprovince.id},
            success:function(data){
              $scope.deliveryPortList=data;
              $scope.$apply();
            }
          });
        }else{
          //省份选择全部时，刷新港口列表
          $scope.deliveryPortList=null;
          $scope.formData.deliveryplace=null;
        }
      }
      function replaceJson(props){
        //获取当前$scope上表单值的拷贝，和页面上数据绑定的$scope区分开。
        var newFormData ={};
        angular.copy($scope.formData,newFormData);
        angular.forEach(props,function(key,value){
          if(newFormData[key]!=undefined){
            newFormData[key]=newFormData[key].name;
          }
        });
        newFormData['page']=$scope.pageNum;
        return newFormData;
      }
      // 处理表单提交
      $scope.processForm=function(){

        service({
          url:"/customer/bringdealdemandinfo",
          data:replaceJson(['deliverydistrict','deliveryprovince','deliveryplace']),
          success:function(data){
            $scope.demandlist = data.demandlist.list;
            $scope.pageNum = data.demandlist.page;
            $scope.totalCount = data.demandlist.count;
            $scope.$apply();
          }
        });
      }
      $scope.pageTo=function(){
        service({
          url:"/customer/bringdealdemandinfo",
          data:replaceJson(['deliverydistrict','deliveryprovince','deliveryplace']),
          success:function(data){
            $scope.demandlist = data.demandlist.list;
            $scope.pageNum = data.demandlist.page;
            $scope.totalCount = data.demandlist.count;
            $scope.$apply();
          }
        });
      }
    })
    .controller("TeamQualificationCtrl",function($scope,$params){
      //页面load时加载数据
      service({
        url:"/customer/teamorderQualification",
        data:{page:$params('page',1),status:$params("status")},
        success:function(data){
          $scope.qualificationlist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      })
      //查询审核中
      $scope.searchverifyIn=function(){
        goto("/customer/teamorderqualification",{status:'QUALIFY_APPLY'})
      };
      //查询已激活
      $scope.searchActive=function(){
        goto("/customer/teamorderqualification",{status:'QUALIFY_ACTIVE'})
      };
      //查询已绑定
      $scope.searchBind=function(){
        goto("/customer/teamorderqualification",{status:'QUALIFY_INPROCESS'})
      };

      $scope.pageTo=function(){
        goto("/customer/teamorderqualification",{page: $scope.pageNum,status:$params("status")});
      }

    })
    .controller("TeamOrderCtrl",function($scope,$params){
      //页面加载发送请求
      service({
        url:"/customer/teamorder",
        data:{page:$params("page",1),status:$params("status")},
        success:function(data){
          $scope.teamorderlist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      });
      //查询进行中的团购
      $scope.searchIn=function(){
        goto("/customer/teamorder",{status:'GROUP_BUY_SUPPLY_INPROGRESS'})
      };
      //查询已结束的团购
      $scope.searchFinish=function(){
        goto("/customer/teamorder",{status:'GROUP_BUY_SUPPLY_FINISH'})
      };

      $scope.pageTo=function(){
        goto("/customer/teamorder",{page: $scope.pageNum,status:$params("status")})
      };
    })
    .controller("TeamOrderDetailCtrl",function($scope,$params){
      //从url得到团购详细，方便页面取值
      $scope.qualificationcode= $params('qualificationcode');
      $scope.volume = $params('volume');
      $scope.userid = $params('userid');
      $scope.constractNo=$params('performancecode');
      //查询团购详细
      service({
        url: '/customer/teamorderDetail?supplyCode='+$params('supplyCode'),
        success: function (data) {
          $scope.teamorder = data;
          $scope.$apply();
        }
      })
      //查看团购
      $scope.lookconstract=function(){
        window.location.href="/customer/lookagreementContract?userid="+$scope.userid+"&performancecode="+$scope.constractNo;
      }
    })

});



