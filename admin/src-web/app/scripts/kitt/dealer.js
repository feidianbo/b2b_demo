/**
 * Created by liuxinjie on 14-12-10.
 */
define(['angular','kitt','jquery.validation','bootstrap3-datetimepicker','angular-ui-select'],function(angular, kitt) {
  return angular.module('kitt.dealer', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
       .when('/dealer', {
          templateUrl: 'views/dealer/list.html',
          controller: 'dealer.List'
        })
        .when('/dealer/list',{
          templateUrl: 'views/dealer/list.html',
          controller: 'dealer.List'
        })
    }).controller('dealer.List',function($scope,$params){

      $scope.searchDealer=function(){
        goto('/dealer/list', {deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour});
      };
      $scope.pageTo=function(){
        goto('/dealer/list', {page:$scope.dealerList.page,deliveryRegion:$scope.deliveryRegion,deliveryProvince:$scope.deliveryProvince,deliveryHarbour:$scope.deliveryHarbour});
      };
      service({
        url:'/dealer/list',
        data:{page:$params('page',1),deliveryRegion:$params('deliveryRegion', 0),deliveryProvince:$params('deliveryProvince', 0),deliveryHarbour:$params('deliveryHarbour', 0)},
        success:function(data){
          $scope.deliveryRegions=data.regionList;
          $scope.deliveryProvinces=data.provinceList;
          $scope.deliveryHarbours=data.harbourList;
          $scope.addDeliveryRegions=data.regionList;
          $scope.addDeliveryProvinces=data.addProvinceList;
          //$scope.addDeliveryHarbours=data.addHarbourList;
          $scope.deliveryRegion=data.region;
          $scope.deliveryProvince=data.province;
          $scope.deliveryHarbour=data.harbour;
          $scope.dealerList=data.dealerList;
          $scope.page=data.dealerList.page;
          $scope.$apply();
        }
      });

      $("#deliveryRegion").change(function(){
        if($(this).val()!=0) {
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
          $scope.deliveryHarbours = null;
          $scope.deliveryHarbour = 0;
          service({
            url: '/getDealerPorts',
            data: {id: $("#deliveryProvince").val()},
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
      //清除历史信息
      function clearHistory(){
        $scope.deliveryProvinces=null;
        $scope.deliveryProvince=0;
        $scope.deliveryHarbours=null;
        $scope.deliveryHarbour=0;
      }

      $("#addDeliveryRegion").change(function(){
        service({
          url:'/mall/getProvinces',
          data:{id:$("#addDeliveryRegion").val()},
          success:function(data){
            $scope.addDeliveryProvinces=data;
            $scope.addDeliveryHarbours=null;
            $scope.$apply();
          }
        });
      });
      $("#addDeliveryProvince").change(function(){
        service({
          url:'/mall/getPorts',
          data:{id:$("#addDeliveryProvince").val()},
          success:function(data){
            $scope.addDeliveryHarbours=data;
            $scope.$apply();
          }
        });
      });

      $scope.closeAlert=function(){
        $("#alert").hide();
      }
      $scope.addDealer=function(){
        //添加表单验证
        checkForm("addDealerForm");
        $('#AddDealerDialog').modal('show');
      };
      $scope.dealerObj={};
      $scope.cancelAddDealer = function(){
        $('#addDealerForm .help-block').remove();
        $("#addDeliveryRegion").val(0);
        $("#addDeliveryProvince").val(0);
        $scope.addDeliveryHarbours=null;
        $scope.dealerObj=null;
      }
      //验证添加交易员
      function checkForm(formId) {
        jQuery.validator.addMethod("mobileNum", function (value, element) {
          return this.optional(element) || /^[1][3,4,5,7,8][0-9]{9}$/.test(value);
        }, "请输入有效的手机号码!");
        return $("#"+formId).validate({
          onsubmit: false,
          onkeyup: false,
          focusInvalid: false,
          errorElement: 'span',
          errorClass: 'help-block',
          rules: {
            addDealerPhone: {
              required: true,
              mobileNum:true,
              remote: {
                url: '/dealer/phoneIfExist'
              }
            },
            addDeliveryPlace:{
              required:true,
              minlength:1
            },
            addDealerName:{
              required:true,
              minlength:2,
              maxlength:15
            }
          },
          messages: {
            addDeliveryPlace:{
              required:"请选择所管理的港口",
              minlength:"至少选择{0}个所管理的港口"
            },
            addDealerPhone:{
              required:"请输入交易员电话",
              remote:"手机号已注册!"
            },
            addDealerName:{
              minlength:'不能少于2位',
              maxlength:'不能大于15位',
              required:'请输入交易员姓名'
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
            error.insertAfter(element.parent());
          }
        });
      }
      //添加交易员
      $scope.addDealerClick=function(){
         //验证表单字段
          if(checkForm("addDealerForm").form()){
            //遍历港口集合，由于传过来的json字符串有$$hashKey有这个属性，对应的entity没有这个属性，
            // 请求到后台jackson没法转换，有问题，暂时先这样处理。后续再研究
            $.each($scope.dealerObj.ports,function(){delete this.$$hashKey;});
            //添加交易员
            service({
              url:'/dealer/addDealer',
              data:JSON.stringify($scope.dealerObj),
              contentType:"application/json",
              success:function(result){
                  if(result){
                    $scope.dealerObj=null;
                    $("#AddDealerDialog").modal("hide");
                    location.reload();
                    $("#alert > span:last").text('交易员已添加成功!');
                  }else{
                    $("#alert").addClass("alert alert-danger");
                    $("#alert > span:last").text('交易员添加失败!');
                  }
                 $("#alert").show();
              }
            });
         }
      }
      //加载所有港口信息
      service({
        url: 'dealer/listAllPort',
        success:function(data){
          $scope.allPorts=data;
          $scope.$apply();
        }
      });
      function checkUpdateDealer() {
        jQuery.validator.addMethod("mobileNum", function (value, element) {
          return this.optional(element) || /^[1][3,4,5,7,8][0-9]{9}$/.test(value);
        }, "请输入有效的手机号码!");
        return $("#updateDealerForm").validate({
          onsubmit: false,
          onkeyup: false,
          focusInvalid: false,
          errorElement: 'span',
          errorClass: 'help-block',
          rules: {
            addDealerPhone: {
              required: true,
              mobileNum:true,
              remote: {
                url: '/dealer/phoneIfExist'
              }
            },
            portCheckbox:{
              required:true,
              minlength:1
            }
          },
          messages: {
            portCheckbox:{
              required:"请选择所管理的港口",
              minlength:"至少选择{0}个所管理的港口"
            },
            addDealerPhone:{
              required:"请输入交易员电话",
              remote:"手机号已注册!"
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
            error.insertAfter(element.parent());
          }
        });
      }
      $scope.updateDealer=function(item){
        //添加表单验证
        var updateDealer={};
        angular.copy(item,updateDealer);
        var $portCheckbox= $('[name=portCheckbox]:checkbox');
        $portCheckbox.attr("checked",false)
        $.each(updateDealer.ports,function(){
          var that = this.id;
          $portCheckbox.each(function(){
            if($(this).val()==that){
              $(this).attr("checked",true);
            }
          });
        });
        $scope.dealer=updateDealer;
        $('#updateDealerDialog').modal('show');
        $("#dealerphone").val($scope.dealer.dealerphone);
        checkUpdateDealer();
      }
      $scope.updateDealerClick=function(){
        var oldphone=$("#dealerphone").val();
        var newphone=$("#updateDealerphone").val();
       if(checkUpdateDealer().form()==true||oldphone===newphone){
         $('#updateDealerForm .help-block').remove();
          service({
            url:'/dealer/update',
            data:$("#updateDealerForm").serialize()+"&dealerId="+$scope.dealer.id,
            success:function(data){
              if(data){
                $("#updateDealerDialog").modal('hide');
                location.reload();
              }
            }
          });
        }
      }
      $scope.cancelUpdateDealer=function(){
        $('#updateDealerForm .help-block').remove();
      }
      $scope.forbiddenDealer=function(id,  ports, dealername, dealerphone){
          $("#idtext").val(id);
          $("#forbiddenDealerName").val(dealername);
          $("#forbiddenDealerPhone").val(dealerphone);
          $('#ForbiddenDealerDialog').modal('show');
      };

      $scope.forbiddenDealerClick=function() {
        service({
          url: '/dealer/changeStatus',
          data: {id: $("#idtext").val(), status: "已离职"},
          success: function (data) {
            if (data) {
              $('#ForbiddenDealerDialog').modal('hide');
              goto('/dealer/list');
              location.reload();
            } else {
              $("#forbiddenErrorInfo").css("color", "red").text("禁用交易员失败！");
            }
          }
        })
      }

      $scope.deleteDealer=function(id, deliveryregion, deliveryprovince, deliveryplace, dealername, dealerphone){
        $("#idtext").val(id);
        $("#deleteDeliveryRegion").val(deliveryregion);
        $("#deleteDeliveryProvince").val(deliveryprovince);
        $("#deleteDeliveryPlace").val(deliveryplace);
        $("#deleteDealerName").val(dealername);
        $("#deleteDealerPhone").val(dealerphone);
        $('#DeleteDealerDialog').modal('show');
      };

      $scope.deleteDealerClick=function() {
        service({
          url: '/dealer/changeStatus',
          data: {id: $("#idtext").val(), status:"已删除"},
          success: function (data) {
            if (data) {
              $('#DeleteDealerDialog').modal('hide');
              goto('/dealer/list');
              location.reload();
            } else {
              $("#deleteErrorInfo").css("color", "red").text("删除交易员失败！");
            }
          }
        })
      }
    })
})

function doAddDealer(){
  service({
    url:'/dealer/addDealer',
    data:{
      addDealerName:$("#addDealerName").val(),
      addDealerPhone:$("#addDealerPhone").val(),
      addDeliveryRegion:$("#addDeliveryRegion").val(),
      addDeliveryProvince:$("#addDeliveryProvince").val(),
      addDeliveryPlace:$("#addDeliveryPlace").val()
    },
    success:function(data){
      if(data.success){
        $("#AddDealerDialog").modal("hide");
        window.location.href="/#/dealer/list";
        location.reload();
      } else{
        if(data.error == "exist"){
          $("#addErrorInfo").css("color", "red").text("该港口已存在此交易员！");
        } else if(data.error == "phoneexist"){
          $("#addErrorInfo").css("color", "red").text("此手机号已被其他交易员使用，请填写其它手机号！");
        } else{
          $("#addErrorInfo").css("color", "red").text("添加交易员失败!").css("color", "red");
        }
      }
    }
  })
}


