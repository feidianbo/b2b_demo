/**
 * Created by shiling on 14-12-10.
 */
define(['angular','kitt','jquery.validation','bootstrap3-datetimepicker','CN'],function(angular, kitt){
  return angular.module('kitt.supply', ['kitt'])
    .config(function($routeProvider){
      $routeProvider
      .when('/supply/pass',{
        templateUrl:'views/supply/list.html',
        controller:'supply.Pass'
      })
      .when('/supply/wait',{
        templateUrl:'views/supply/list.html',
        controller:'supply.Wait'
      })
      .when('/supply/fail',{
        templateUrl:'views/supply/list.html',
        controller:'supply.Fail'
      })
      .when("/supply/detail",{
        templateUrl:'views/supply/detailInfo.html',
        controller:'supply.Detail'
      })
      .when("/mall/sales",{
        templateUrl:'views/supply/releaseInfoList.html',
        controller:'mall.Sales'
      })
      .when("/mall/pulls",{
        templateUrl:'views/supply/releaseInfoList.html',
        controller:'mall.Pulls'
      })
      .when("/release/addSupply",{
        templateUrl:'views/supply/releaseSupply.html',
        controller:'supply.Add'
      });
    })

    //供应管理---审核通过
    .controller('supply.Pass',function($scope,$params){
      $scope.searchSupply=function(){
        goto('/supply/pass', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,productNo:$scope.productNo});
      };
      $scope.pageTo=function(){
        goto('/supply/pass', {page:$scope.sellInfo.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour, productNo:$scope.productNo});
      };
      service({
        url:'/supply/pass',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),productNo:$params('productNo', '')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.sellInfo=data.sellInfo;
          $scope.page=data.sellInfo.page;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.productNo = data.productNo;
          $scope.$apply();
          window.initAuth();
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $("#deliveryRegion").val()},
            success: function (data) {
              $scope.deliveryProvinces = data;
              $scope.$apply();
            }
          });
        }else{
          clearHistory();
        }
      });

      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
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

      //取消供应
      $scope.cancelSupply=function(id, version){
        $("#supplyId").val(id);
        $("#version").val(version);
        $("#cancelConfirmDialog").modal('show');
      }
      $scope.canceledSupplyConfirm=function(){
        service({
          url:'/supply/cancelSupply',
          data:{id:$("#supplyId").val(), version:$("#version").val()},
          success:function(data){
            if(data){
              $('#cancelConfirmDialog').modal('hide');
              service({
                url:'/supply/pass',
                data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),productNo:$params('productNo', '')},
                success:function(data){
                  $scope.deliveryRegions=data.regionList;
                  $scope.deliveryProvinces=data.provinceList;
                  $scope.deliveryHarbours=data.harbourList;
                  $scope.sellInfo=data.sellInfo;
                  $scope.page=data.sellInfo.page;
                  $scope.deliveryRegion=data.deliveryRegion;
                  $scope.deliveryProvince=data.deliveryProvince;
                  $scope.deliveryHarbour=data.deliveryHarbour;
                  $scope.productNo=data.productNo;
                  $scope.$apply();
                }
              });
            }
          }
        })
      }

    })
    //供应管理---审核未通过
    .controller('supply.Fail',function($scope,$params){
      $scope.searchSupply=function(){
        goto('/supply/fail', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour, productNo:$scope.productNo});
      };
      $scope.pageTo=function(){
        goto('/supply/fail', {page:$scope.sellInfo.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour, productNo:$scope.productNo});
      };
      service({
        url:'/supply/fail',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0), productNo:$params('productNo', '')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.productNo=data.productNo;
          $scope.sellInfo=data.sellInfo;
          $scope.page=data.sellInfo.page;
          $scope.$apply();
        }
      });
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0) {
          clearHistory();
          service({
            url: '/mall/getProvinces',
            data: {id: $("#deliveryRegion").val()},
            success: function (data) {
              $scope.deliveryProvinces = data;
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
    })
    //供应管理---待审核
    .controller('supply.Wait',function($scope,$params){
      //判断参数
      $scope.searchSupply=function(){
        goto('/supply/wait', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour, productNo:$scope.productNo});
      };
      $scope.pageTo=function(){
        goto('/supply/wait', {page:$scope.sellInfo.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour, productNo:$scope.productNo});
      };
      service({
        url:'/supply/wait',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0), productNo:$params('productNo', '')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.productNo=data.productNo;
          $scope.sellInfo=data.sellInfo;
          $scope.page=data.sellInfo.page;
          $scope.$apply();
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

    //商城产品--[销售中的产品]
    .controller('mall.Sales',function($scope, $route, $params){
      $scope.searchMall=function(){
        goto('/mall/sales',{deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,productNo:$scope.productNo});
      };
      $scope.pageTo=function(){
        goto('/mall/sales', {page:$scope.mallList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,productNo:$scope.productNo});
      };
      service({
        url:'/mall/sales',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),productNo:$params('productNo','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.productNo=data.productNo;
          $scope.mallList=data.mallList;
          $scope.page=data.mallList.page;
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
                $scope.$apply();
              }
            });
          }else{
            clearHistory();
          }
      });
      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
            service({
              url: '/mall/getPorts',
              data: {id: $("#deliveryProvince").val()},
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

      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

      //下架产品
      $scope.putOffShelves=function(id){
        $("#idtext").val(id);
        $('#putOffShelfConfirmDialog').modal('show');
      };

      $scope.putOffShelf=function(){
        service({
          url:'/mall/putOffShelves',
          data:{id:$("#idtext").val()},
          success:function(data){
            if(data){
              $('#putOffShelfConfirmDialog').modal('hide');
              service({
                url:'/mall/sales',
                data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0), productNo:$params('productNo', "")},
                success:function(data){
                  $scope.deliveryRegions=data.regionList;
                  $scope.deliveryProvinces=data.provinceList;
                  $scope.deliveryHarbours=data.harbourList;
                  $scope.deliveryRegion=data.deliveryRegion;
                  $scope.deliveryProvince=data.deliveryProvince;
                  $scope.deliveryHarbour=data.deliveryHarbour;
                  $scope.productNo=data.productNo;
                  $scope.mallList=data.mallList;
                  $scope.page=data.mallList.page;
                  $scope.$apply();
                }
              });
            }
          }
        })
      }

      //推荐产品
      $scope.recommendProduct=function(id){
        $("#idtext").val(id);
        $("#recommend").val('Recommend');
        $('#recommendConfirmDialog').modal('show');
      }

      //取消推荐产品
      $scope.cancelRecommend=function(id){
        $("#idtext").val(id);
        $("#recommend").val('Common');
        $('#cancelRecommendDialog').modal('show');
      }

      $scope.recommendConfirm=function(){
        service({
          url:'/mall/changeRecommend',
          data:{id:$("#idtext").val(), recommend:$("#recommend").val()},
          success:function(data){
            if(data.success){
              if(data.recommend=='Common'){
                $('#cancelRecommendDialog').modal('hide');
              }else{
                $('#recommendConfirmDialog').modal('hide');
              }
              service({
                url:'/mall/sales',
                data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0), productNo:$params('productNo', "")},
                success:function(data){
                  $scope.deliveryRegions=data.regionList;
                  $scope.deliveryProvinces=data.provinceList;
                  $scope.deliveryHarbours=data.harbourList;
                  $scope.deliveryRegion=data.deliveryRegion;
                  $scope.deliveryProvince=data.deliveryProvince;
                  $scope.deliveryHarbour=data.deliveryHarbour;
                  $scope.productNo=data.productNo;
                  $scope.mallList=data.mallList;
                  $scope.page=data.mallList.page;
                  $scope.$apply();
                }
              });
            } else {
            }
          }
        })
      }
    })

    //下架的产品
    .controller('mall.Pulls',function($scope,$params){
      $scope.searchMall=function(){
        goto('/mall/pulls',{deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,productNo:$scope.productNo});
      };
      $scope.pageTo=function(){
        goto('/mall/pulls', {page:$scope.offList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour,productNo:$scope.productNo});
      };
      //下架的产品
      service({
        url:'/mall/pulls',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0),productNo:$params('productNo','')},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.deliveryRegion=data.deliveryRegion;
          $scope.deliveryProvince=data.deliveryProvince;
          $scope.deliveryHarbour=data.deliveryHarbour;
          $scope.mallList=data.mallList;
          $scope.page=data.mallList.page;
          $scope.productNo=data.productNo;
          $scope.$apply();
        }
      });
      $("#deliveryRegion").change(function(){
        if($(this).val()!=0) {
          clearHistory();
            service({
              url: '/mall/getProvinces',
              data: {id: $("#deliveryRegion").val()},
              success: function (data) {
                $scope.deliveryProvinces = data;
                $scope.$apply();
              }
            });
        }else{
          clearHistory();
        }
      });
      $("#deliveryProvince").change(function(){
        if($(this).val()!=0) {
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/mall/getPorts',
            data: {id: $("#deliveryProvince").val()},
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
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

    })
    //发布信息
    .controller('supply.Add',function($scope,$params){
      service({
        url: '/supplyInfo',
        success: function (data) {
          $scope.deliveryproviences = data.deliveryproviences;
          $scope.deliveryregions = data.deliveryregions;
          $scope.deliveryplaces = data.deliveryplaces;
          $scope.inspectorgs = data.inspectorgs;
          $scope.$apply();
        }
      });

      initDate();
      initInput();
      harbourChange();
      orgChange();

      $("#deliveryprovience").change(function(){
         //console.log($(this).val());
        service({
          url: '/mall/getPorts',
          data: {id: $(this).val()},
          success: function (data) {
            $scope.deliveryplaces = data;
            $scope.$apply();
          }, error: function () {
          }
        });
      });
      $("#deliveryplace").change(function(){
        var provinceId = $("#deliveryprovience").val();
        var portId=$(this).val();
        service({
          url:'/dealer/loadDealer',
          data:{provinceId:provinceId,portId:portId},
          success:function(data){
            $scope.dealerList=data;
            $scope.dealerLength=data.length;
            if(data.length==0){
              var portName=$("#deliveryplace option:selected").text();
              if(portName != '其它' && portName != '---请选择---'){
                $scope.portName=portName;
                $scope.portId=portId;
              }
            }else{

            }
            $scope.$apply();
          }
        });
      });

      //radio选中
      $(".radioItem").change(function(){
        if($("input[name='optionsRadiosinline']:checked").val() == 1){
          $("#jtj012").val("");
          $("#jtj022").val("");
          $("#jtj032").val("");
          $("#jtj042").val("");
          $("#ykj2").attr("readonly", false);
          $("#jtj012").attr("readonly",true);
          $("#jtj022").attr("readonly",true);
          $("#jtj032").attr("readonly",true);
          $("#jtj042").attr("readonly",true);
          $("#jtj052").attr("readonly",true);
          $("#small").attr("readonly",true);
          $("#middle").attr("readonly",true);
          $("#larger").attr("readonly",true);
          $("#bigger").attr("readonly",true);
          $("#biggest").attr("readonly",true);
          $("#error").text("");
          $("#smallest").val("");
          $("#small").val("");
          $("#more").val("");
          $("#middle").val("");
          $("#large").val("");
          $("#larger").val("");
          $("#big").val("");
          $("#bigger").val("");
          $("#littleBig").val("");
          $("#biggest").val("");
          $("#jtj012").val("");
          $("#jtj022").val("");
          $("#jtj032").val("");
          $("#jtj042").val("");
          $("#jtj052").val("");
        } else{
          $("#smallest").val("50");
          $("#ykj2").val("");
          $("#jtj012").val("");
          $("#jtj022").val("");
          $("#jtj032").val("");
          $("#jtj042").val("");
          $("#jtj052").val("");
          $("#ykj2").attr("readonly",true);
          $("#jtj012").attr("readonly",false);
          $("#jtj022").attr("readonly",false);
          $("#jtj032").attr("readonly",false);
          $("#jtj042").attr("readonly",false);
          $("#jtj052").attr("readonly",false);
          $("#small").attr("readonly",false);
          $("#middle").attr("readonly",true);
          $("#larger").attr("readonly",false);
          $("#bigger").attr("readonly",false);
          $("#biggest").attr("readonly",false);
          $("#middle").val($("#supplyNumber1").val());
        }
      });
      check();

      $scope.confirmSupply=function(){
        // 清除错误
        $("#commonError").text("");
        // validateForm()校验检验机构，提货地点，时间
        // check().form()校验指标， checkSupplyNum()校验瓶数
        if (! (check().form() & validateForm() & checkSupplyNum() ) ) {
          $("#commonError").text("您的输入有误，请根据上面红色的提示，修改信息").css("color","red");
          return;
        }
        releaseSupplyInfo();
      }

      function validateForm(){
        var validateFlag = true;
        //校验时间
        if($("#deliverydate").val()==""||$("#deliverydate2").val()==""){
          $("#errorInfo").html("请填写交货时间").css("color","red");
          validateFlag=false;
        } else if(dateCompare($("#deliverydate").val(),($("#deliverydate2").val()))==false){
          $("#errorInfo").html("结束时间不能大于开始时间").css("color","red");
          validateFlag=false;
        } else{
          $("#errorInfo").html("");
        }
        //提货地点校验
        if($("#deliveryregions").val()==""||$("#deliveryprovience").val()==""||$("#deliveryplace").val()==""){
          $("#wrongInfo").text("请选择具体的港口").css("color","red");
          return;
        }
        if($("#deliveryplace").val()==-1 && $("#otherharbour").val()==""){
          $("#wrongInfo").text("");
          $("#otherplaceError").text("请输入交货地点").css("color","red");
          $("#otherharbour").blur(function(){
            $("#otherplaceError").text("");
          });
          return;
        }
        //检验机构校验
        if($("#inspectorg").val()=="其它" && $("#otherinspectorg").val().length==0){
          $("#otherorgError").text("请输入检验机构").css("color","red");
          $("#otherinspectorg").blur(function(){
            $("#otherorgError").text("");
          });
          return;
        }
        return  validateFlag;
      }

      $("#add").click(function(){
        if($("#jtj032").is(":visible") && $("#jtj042").is(":visible")) {
          $("#stepDiv5").css("display", "block");
          $("#bigger").val("");
          $("#bigger").removeAttr("readonly");
          $("#biggest").val($("#supplyNumber1").val());
          $("#biggest").attr("readonly",true);
          $("#reduceZero").hide();
          $("#reduceFirst").hide();
          $("#reduceSecond").show();
        } else if($("#jtj032").is(":visible")) {
          $("#stepDiv4").css("display", "block");
          $("#larger").val("");
          $("#larger").removeAttr("readonly");
          $("#bigger").val($("#supplyNumber1").val());
          $("#bigger").attr("readonly",true);
          $("#reduceZero").hide();
          $("#reduceFirst").show();
        } else{
          $("#stepDiv3").css("display","block");
          $("#larger").val($("#supplyNumber1").val());
          $("#reduceSecond").show();
          $("#middle").val("");
          $("#larger").attr("readonly",true);
          $("#middle").removeAttr("readonly");
          $("#reduceZero").show();
        }
      });
      $("#reduceZero").click(function(){
        $("#stepDiv3").css("display","none");
        $("#jtj032").val("");
        $("#larger").val("");
        $("#middle").attr("readonly",true);
        $("#middle").val($("#supplyNumber1").val());
      })
      $("#reduceFirst").click(function(){
        $("#stepDiv4").css("display","none");
        $("#jtj042").val("");
        $("#bigger").val("");
        $("#reduceZero").show();
        $("#larger").attr("readonly",true);
        $("#larger").val($("#supplyNumber1").val());
      })
      $("#reduceSecond").click(function(){
        $("#stepDiv5").css("display","none");
        $("#jtj052").val("");
        $("#biggest").val("");
        $("#reduceFirst").show();
        $("#reduceZero").hide();
        $("#bigger").attr("readonly", true);
        $("#bigger").val($("#supplyNumber1").val());
      })
      $("#otherinspectorg").change(function(){
        if($("#otherinspectorg").val()=="") {
          $("#otherorgError").text("请输入检验机构");
        } else{
          $("#otherorgError").text("");
        }
      });

      //阶梯价填写
      $("#small").blur(function(){
        if(eval($("#small").val())<50){
          $("#error").text("最少瓶数为50").css("color","red");
          return;
        } else{
          $("#error").text("");
          if(($("#small").val() % 50) != 0){
            $("#error").text("输入的瓶数必须是50的倍数").css("color","red");
            return;
          } else if(eval($("#small").val())>eval($("#supplyNumber1").val())){
            $("#error").text("您所填写的瓶数不能超过供应数量").css("color","red");
            return;
          } else{
            $("#more").val($("#small").val());
          }
        }
      });
      $("#middle").blur(function(){
        if(eval($("#middle").val())<= eval($("#small").val())){
          $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
          return;
        } else{
          $("#error").text("");
          if(($("#middle").val()% 50) != 0){
            $("#error").text("输入的瓶数必须是50的倍数").css("color","red");
          } else if(eval($("#middle").val())>eval($("#supplyNumber1").val())){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return;
          } else{
            $("#large").val($("#middle").val());
          }
        }
      });
      $("#larger").blur(function(){
        if(eval($("#larger").val())<=eval($("#middle").val())){
          $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
          return;
        } else{
          $("#error").text("");
          if(($("#larger").val()% 50) != 0){
            $("#error").text("输入的瓶数必须是50的倍数").css("color","red");
          } else if(eval($("#larger").val()) > eval($("#supplyNumber1").val())){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return;
          } else{
            $("#big").val($("#larger").val());
          }
        }
      });
      $("#bigger").blur(function(){
        if(eval($("#bigger").val())<=eval($("#larger").val())){
            $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
            return;
        } else{
          $("#error").text("");
          if(($("#bigger").val()% 50) != 0){
            $("#error").text("输入的瓶数必须是50的倍数").css("color","red");
          } else if(eval($("#bigger").val())>eval($("#supplyNumber1").val())){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return false;
          } else{
            $("#littleBig").val($("#bigger").val());
          }
        }
      });
      $("#biggest").blur(function(){
        if(eval($("#biggest").val())<=eval($("#bigger").val())){
          $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
          return;
        } else{
          $("#error").text("");
          if(($("#biggest").val()% 50) != 0){
            $("#error").text("输入的瓶数必须是50的倍数").css("color","red");
          } else if(eval($("#biggest").val())>eval($("#supplyNumber1").val())){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return false;
          }
        }
      });

      $scope.cancelSupply=function(){
        goto("/mall/sales");
      };
    })

    //查看详情
    .controller('supply.Detail',function($scope,$params) {
      service({
        url: '/supply/detail',
        data: {id: $params('id'),type:$params('type')},
        success: function (data) {
          if(data.success) {
            $scope.supply = data.sellInfo;
            $scope.jtj01Obj = data.jtj01Obj;
            $scope.jtj02Obj = data.jtj02Obj;
            $scope.jtj03Obj = data.jtj03Obj;
            $scope.jtj04Obj = data.jtj04Obj;
            $scope.jtj05Obj = data.jtj05Obj;
            $scope.dealerList=data.dealerList;
            $scope.dealerCount=data.dealerList===undefined?0:data.dealerList;
            $scope.dealer=data.dealer;
            $scope.type=data.type;
            $scope.$apply();
            window.initAuth();
          } else{
          }
        }
      });

      $scope.closeAlert=function(){
        $("#alert").hide();
      }

      //点击确认按钮
      $scope.checkSupplyInfo=function(id, version) {
        //状态是1是未通过审核，0通过审核
        var status = $("input[type='radio']:checked").val();

        var param = {id: id, version: version, checkResult: $("input[type='radio']:checked").val(), comment: $("#comment").val(),
                      editnum:$scope.supply.editnum };
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
        $("#errorInfo").text("");

        //修改状态
        service({
          url:'/supply/checkInfo',
          data:param,
          success:function(data){
            if(data){
              $("#alert > span:last").text('供应审核成功!');
              $("#alert").show();
              goto('/supply/wait');
            } else{
              $("#alert").addClass("alert alert-danger");
              $("#alert > span:last").text('供应审核失败，请稍后刷新重试！');
              $("#alert").show();
            }
          }
        })
      }

    //返回
    $scope.back = function(){
      history.go(-1);
    };

    $scope.confirmSellinfo=function(id){
      $.ajax({
        url:'/confirmSellinfo',
        data:{id:id},
        success:function(data){
          if(data.success) {
            history.go(-1);
          } else{
            $("#errorInfo").text("确认发布信息失败").css("color", "red");
          }
        }
      });
    }
  })

    //对发布的信息进行校验
    function check(){
        jQuery.validator.addMethod("floatCheck", function(value, element) {
          return this.optional(element) || /^(\d+(\.\d)?)$/.test(value);
        }, "只能输入一位小数");
        jQuery.validator.addMethod("onceCheck", function(value, element) {
          return this.optional(element) || (/^\d+(\.\d{0,2})?$/).test(value);
        }, "只能输入两位小数");
        jQuery.validator.addMethod("numberCheck", function(value, element) {
          var firstChar = value.substr(0,1);
          return this.optional(element) || firstChar != 0;
        }, "请输入合法数字");
        jQuery.validator.addMethod("secCheck", function(value, element) {
          var regular;
          if(value.indexOf(".") != -1){
            if(value.substr(0,value.indexOf(".")).length >=2){
              if(value.substr(0,1) == 0){
                regular = 0;
              }
            }
          }else{
            if(value.length >= 2){
              if(value.substr(0,1) == 0){
                regular = 0;
              }
            }
          }
          return this.optional(element) || regular != 0 ;
        }, "请输入合法的格式");
        jQuery.validator.addMethod("multiple", function(value, element) {
          return this.optional(element) || (value % 50) == 0;
        }, " 输入的瓶数必须是50的倍数!");
        jQuery.validator.addMethod("hundredCheck", function(value, element) {
          return this.optional(element) || (value % 100) == 0;
        }, " 输入的数字必须是100的倍数!");
        jQuery.validator.addMethod("compare", function(value, element) {
          var firstValue = $("#TM").val();
          return this.optional(element) || eval(value) < eval(firstValue);
        }, "酒类指标4必须大于酒类指标5");
      //jQuery.validator.addMethod("requiredDealer", function(value, element) {
      //  return this.optional(element) || eval(value) < eval(firstValue);
      //}, "酒类指标4必须大于酒类指标5");
        return  $("#supply-form").validate({
          onsubmit:false,
          onkeyup:false,
          focusInvalid:false,
          errorElement: 'span',
          errorClass: 'help-block',
          rules: {
            pname: {
              required: true
            },
            NCV: {
              required: true,
              digits:true,
              numberCheck:true,
              range:[3000,7000]
            },
            ADS: {
              range:[0.1,10],
              onceCheck:true
            },
            RS: {
              required: true,
              onceCheck:true,
              range:[0.1,9.99]
            },

            TM: {
              required: true,
              secCheck:true,
              range:[0.1,50],
              onceCheck:true
            },

            IM: {
              onceCheck:true,
              secCheck:true,
              compare:true,
              range:[0.1,50]
            },
            ADV: {
              required: true,
              numberCheck:true,
              onceCheck:true,
              range:[0.1,50]
            },
            RV: {
              numberCheck:true,
              onceCheck:true,
              range:[0.1,50]
            },
            AFT: {
              numberCheck:true,
              range:[900,1600]
            },
            ASH: {
              numberCheck:true,
              floatCheck:true,
              range:[0.1,50]
            },
            HGI: {
              digits:true,
              range:[0.1,100],
              numberCheck:true
            },
            deliverymode: {
              required: true
            },
            deliverytime1: {
              required: true
            },
            deliverytime2: {
              required: true
            },
            supplyquantity: {
              required: true,
              digits:true,
              maxlength:6,
              numberCheck:true,
              multiple:true
            },
            ykj: {
              required: true,
              numberCheck:true,
              digits:true,
              range:[50,1200]
            },
            jtj01: {
              required: true,
              digits:true,
              numberCheck:true,
              range:[50,1200]
            },
            jtj02: {
              required: true,
              digits:true,
              numberCheck:true,
              range:[50,1200]
            },
            jtj03: {
              required: true,
              digits:true,
              numberCheck:true,
              range:[50,1200]
            },
            org:{
              required:true
            },
            producttype:{
              required:true
            },
            dealerSel: {
             // requiredDealer:true
              required:true
            },
            originplace:{
              required:true
            }
          },
          messages: {
            dealerSel:{
              required:'请选择交易员'
            },
            pname:{
              required: "请选择酒类"
            },
            NCV: {
              required: "请输入酒精度数",
              digits:"请输入整数",
              range: $.validator.format("请输入一个介于3000-7000之间的整数!")
            },
            ADS:{
              range: $.validator.format("请输入一个介于0-10之间的数值!")
            },
            RS:{
              required: "请输入含糖量",
              range: $.validator.format("请输入一个介于0-10之间的数值!")
            },
            TM:{
              required: "请输入酒类指标4",
              range: $.validator.format("请输入一个介于0-50之间的数值!")
            },
            IM:{
              range: $.validator.format("请输入一个介于0-50之间的数值!")
            },
            ADV:{
              required: "请输入酒类指标2",
              range: $.validator.format("请输入一个介于0-50之间的数值!"),
              digits:"酒类指标2只能输入整数"
            },
            RV:{
              range: $.validator.format("请输入一个介于0-50之间的数值!"),
              digits:"酒类指标3只能输入整数"
            },
            AFT:{
              range: $.validator.format("请输入一个介于900-1600之间的整数!"),
              digits:"酒类指标7必须输入整数"
            },
            ASH:{
              range:$.validator.format("请输入一个介于1-50之间的数值!")
            },
            HGI:{
              range:$.validator.format("请输入一个介于0-100之间的整数!"),
              digits:"酒类指标8必须输入整数"
            },
            deliverymode:{
              required: "请选择交货方式"
            },
            deliverytime1:{
              required: "请输入交货时间"
            },
            deliverytime2:{
              required: "请输入交货时间"
            },
            supplyquantity:{
              required: "请输入供货数量",
              digits:"请输入正整数",
              maxlength:"供货数量只能输入6位数"
            },
            ykj:{
              required: "请输入单价",
              digits:"请输入正整数",
              range:$.validator.format("单价为50-1200间的数值")
            },
            jtj01:{
              required: "请输入单价",
              digits:"只能输入整数",
              range:$.validator.format("单价为50-1200间的数值")
            },
            jtj02:{
              required: "请输入单价",
              digits:"只能输入整数",
              range:$.validator.format("单价为50-1200间的数值")
            },
            jtj03:{
              required: "请输入单价",
              digits:"只能输入整数",
              range:$.validator.format("单价为50-1200间的数值")
            },
            org:{
              required:"请输入检验机构"
            },
            producttype:{
              required:"请选择产品类型"
            },
            originplace:{
              required:"请输入产地"
            }
          },

          highlight: function (element) {
            $(element).closest('.form-control').addClass('has-error');
          },
          success: function (label) {
            label.closest('.form-control').removeClass('has-error');
            label.remove();
          },
          errorPlacement: function (error, element) {
            error.insertAfter(element.parent().parent());
          }
        });
      }

  function loadDealer(dealerId){
      service({
        url:'/dealer/loadDealer'

      });
  }
    function harbourChange(){
      $('#deliveryplace').change(function(){
        if($("#deliveryplace").val()==-1){
          $("#wrongInfo").text("");
          $("#otherplaceDisplay").slideDown();
        } else{
          $("#otherplaceDisplay").slideUp();
        }
      });
    }


    function orgChange(){
      $('#inspectorg').change(function(){
        if($("#inspectorg").val()=="其它"){
          $("#otherorgDisplay").slideDown();
        } else{
          $("#otherorgDisplay").slideUp();
        }
      });
    }

    function initInput(){
      $("#jtj012").attr("readonly", true);
      $("#jtj0121").attr("readonly", true);
      $("#jtj022").attr("readonly", true);
      $("#jtj0222").attr("readonly", true);
      $("#jtj032").attr("readonly", true);
      $("#jtj0323").attr("readonly", true);
      $("#jtj042").attr("readonly", true);
      $("#jtj0424").attr("readonly", true);
      $("#jtj052").attr("readonly", true);
      $("#jtj0525").attr("readonly", true);
      $("#small").attr("readonly", true);
      $("#middle").attr("readonly", true);
      $("#larger").attr("readonly", true);
      $("#bigger").attr("readonly", true);
      $("#biggest").attr("readonly", true);
      $("#small2").attr("readonly", true);
      $("#middle2").attr("readonly", true);
      $("#more2").attr("readonly", true);
      $("#large2").attr("readonly", true);
      $("#larger2").attr("readonly", true);
      $("#big2").attr("readonly", true);
      $("#bigger2").attr("readonly", true);
      $("#littleBig2").attr("readonly", true);
      $("#biggest2").attr("readonly", true);
    }

    function initDate(){
      $('#datetimepicker1').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn'
      });
      $('#datetimepicker2').datetimepicker({
        pickTime: false,
        todayBtn: true,
        language:'zh-cn'
      });
    }

    function checkSupplyNum(){
      // 如果不是阶梯价就返回
      var type=$("input[name='optionsRadiosinline']:checked").val();
      if(type==1){
        return true;
      }
      if(!$("#small").val()||!$("#middle").val()){
        $("#error").text("请填写瓶数范围").css("color","red");
        return false;
      } else{
        if(eval($("#small").val())<50){
          $("#error").text("最少瓶数为50").css("color","red");
          return false;
        } else{
          $("#error").text("");
          if((eval($("#small").val()) % 50) != 0){
            $("#error").text("输入的数字必须是50的倍数").css("color","red");
            return false;
          } else if(eval(($("#small").val())>eval($("#supplyNumber1").val()))){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return false;
          } else{
            $("#more").val($("#small").val());
          }
        }
        if(eval($("#middle").val())<= eval($("#small").val())){
          $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
          return false;
        } else{
          $("#error").text("");
          if((eval($("#middle").val())% 50) != 0){
            $("#error").text("输入的数字必须是50的倍数").css("color","red");
            return false;
          } else if(eval(($("#middle").val())>eval($("#supplyNumber1").val()))){
            $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
            return false;
          } else{
            $("#large").val($("#middle").val());
          }
        }
        if($("#stepDiv3").is(":visible")) {
          if($("#jtj032").val()!="") {
            if($("#larger").val()==""){
              $("#error").text("请填写瓶数！").css("color", "red");
              return false;
            } else if (eval($("#larger").val()) <= eval($("#middle").val())) {
              $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color", "red");
              return false;
            } else {
              $("#error").text("");
              if ((eval($("#larger").val()) % 50) != 0) {
                $("#error").text("输入的数字必须是50的倍数").css("color", "red");
                return false;
              } else if (eval(($("#larger").val()) > eval($("#supplyNumber1").val()))) {
                $("#error").text("您所填写瓶数不能超过供应数量").css("color", "red");
                return false;
              } else {
                $("#big").val($("#larger").val());
              }
            }
          } else{
            $("#error").text("");
          }
        }

        if($("#stepDiv4").is(":visible")) {
          if($("#jtj042").val()!=""){
            $("#error").text("");
            if($("#bigger").val()==""){
              $("#error").text("请填写瓶数").css("color","red");
              return false;
            } else if(eval($("#bigger").val())<=eval($("#larger").val())){
              $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
              return false;
            } else{
              $("#error").text("");
              if((eval($("#bigger").val()% 50)) != 0){
                $("#error").text("输入的数字必须是50的倍数").css("color","red");
                return false;
              } else if(eval(($("#bigger").val())>eval($("#supplyNumber1").val()))){
                $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
                return false;
              } else {
                $("#littleBig").val($("#bigger").val());
              }
            }
          };
        } else{
          $("#error").text("");
        }

        if($("#stepDiv5").is(":visible")) {
          if($("#jtj052").val()!=""){
            if(eval($("#biggest").val())==""){
              $("#error").text("请填写瓶数").css("color","red");
            } else{
              if(eval($("#biggest").val())<=eval($("#bigger").val())){
                $("#error").text("您所填写的瓶数必须大于前面的瓶数").css("color","red");
                return false;
              } else{
                $("#error").text("");
                if((eval($("#biggest").val())% 50) != 0){
                  $("#error").text("输入的数字必须是50的倍数").css("color","red");
                  return false;
                } else if(eval(($("#biggest").val())>eval($("#supplyNumber1").val()))){
                  $("#error").text("您所填写瓶数不能超过供应数量").css("color","red");
                  return false;
                } else{
                  $("#biggest").val($("#supplyNumber1").val());
                }
              }
            }
          }
        } else{
          $("#error").text("");
        }
      }
      return true;
    }

    function releaseSupplyInfo(){
      var formData=$("#supply-form").serialize();
      $.ajax({
        url: '/addSellinfo',
        data: formData,
        success: function (data) {
          if (data.success) {
            goto('/mall/sales');
          } else {
            if (data.error == "before") {
              $("#errorInfo").html("生效时间不能在今天之前").css("color", "red");
            }
          }
        }
      });
    }

    //日期比较
    function dateCompare(startdate,enddate){
    var arr=startdate.split("-");
    var starttime=new Date(arr[0],arr[1],arr[2]);
    var starttimes=starttime.getTime();
    var arrs=enddate.split("-");
    var lktime=new Date(arrs[0],arrs[1],arrs[2]);
    var lktimes=lktime.getTime();
    if(starttimes>lktimes) {
      return false;
    } else {
      return true;
    }
  }
});
