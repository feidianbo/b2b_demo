/**
 * Created by jack on 15/3/13.
 */
/**
 * Created by jack on 15/3/5.
 */
define(['angular','kitt', 'modal', 'jquery.validation'],function(angular, kitt) {
  return angular.module('kitt.center', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/center', {
          templateUrl: 'views/system/center/center.html',
          controller: 'center.View'
        })
        .when('/center/view',{
          templateUrl: 'views/system/center/center.html',
          controller: 'center.View'
        });
    })

    //个人中心
    .controller('center.View',function($scope,$params) {
      service({
        url: '/center/view',
        success: function (data) {
          $scope.admin = data.admin;
          $scope.roles = data.roles;
          $scope.$apply();
        }
      });

      $scope.editPhone=function(){
        $("#resetMyPassword").css("display", "none");
        $("#editMyPhone").css("display", "block");
        $("#newPhoneNum").val("");
      }

      $scope.editPassword=function(){
        $("#editMyPhone").css("display", "none");
        $("#resetMyPassword").css("display", "block");
        $("#formerPassword").val("");
        $("#newPassword").val("");
        $("#repeatNewPassword").val("");
      }

      $scope.confirmEditPhone = function(){
        var parttenPhone=/^((0?1[358]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
        if($("#newPhoneNum").val() == ""){
          $("#phoneerror").text("请输入新手机号！").css("color", "red");
        } else if(!parttenPhone.test($("#newPhoneNum").val())){
          $("#phoneerror").text("请输入正确的手机号！").css("color", "red");
        } else {
          $("#phoneerror").text("");
          service({
            url: '/center/editphone',
            data: {phone: $("#newPhoneNum").val()},
            success: function (data) {
              if (data.success) {
                $("#CommonDialog").modal("show");
                service({
                  url: '/center/view',
                  success: function (data) {
                    $scope.admin = data.admin;
                    $scope.roles = data.roles;
                    $scope.$apply();
                    thisDisplayNone();
                  }
                });
              } else{
                $("#phoneerror").text("此手机号已被其他人员使用， 请更换手机号！").css("color", "red");
              }
            }
          })
        }
      }

      $scope.confirmEditPassword = function(){
        if($("#formerPassword").val() == "" || $("#newPassword").val() == "" || $("#repeatNewPassword").val() == ""){
          $("#pwderror").text("原密码，密码和确认密码都不能为空！");
        } else if($("#newPassword").val() != $("#repeatNewPassword").val()){
          $("#pwderror").text("两次输入的密码不一样！").css("color", "red");
        } else if($("#newPassword").val().length <6 || $("#newPassword").val().length > 16){
          $("#pwderror").text("密码为6-16个字符").css("color", "red");
        } else{
          service({
            url: '/center/resetpassword',
            data:{formerpassword:$("#formerPassword").val(), password:$("#newPassword").val(), repeatpassword:$("#repeatNewPassword").val()},
            success: function(data){
              if(data.success){
                $("#CommonDialog").modal("show");
                service({
                  url: '/center/view',
                  success: function (data) {
                    $scope.admin = data.admin;
                    $scope.roles = data.roles;
                    $scope.$apply();
                    thisDisplayNone();
                  }
                });
              } else{
                if(data.error == "incorrect"){
                  $("#formerpwderror").text("原密码不正确！").css("color", "red");
                } else {
                  $("#pwderror").text("两次输入的密码不相同！").css("color", "red");
                }
              }
            }
          })
        }
      }

      $scope.checkFormerPassword=function(){
        if($("#formerPassword").val() == ""){
          $("#formerpwderror").text("原密码不能为空！").css("color", "red");
        } else if($("#formerPassword").val().length < 6 || $("#formerPassword").val().length > 16){
          $("#formerpwderror").text("密码应该为6-16个字符！")
        } else{
          $("#formerpwderror").text("");
        }
      }

      $scope.checkNewPassword=function(){
        if($("#newPassword").val() == ""){
          $("#pwderror").text("密码不能为空！").css("color", "red");
        } else if($("#newPassword").val().length < 6 || $("#newPassword").val().length > 16){
          $("#pwderror").text("密码应该为6-16个字符！").css("color", "red");
        } else{
          $("#pwderror").text("");
        }
      }

      $scope.checkRepeatPassword=function(){
        if($("#repeatNewPassword").val() == ""){
          $("#pwderror").text("确认密码不能为空！").css("color", "red");
        } else if($("#repeatNewPassword").val().length < 6 || $("#repeatNewPassword").val().length > 16){
          $("#pwderror").text("确认密码应该为6-16个字符！").css("color", "red");
        } else if($("#newPassword").val() != $("#repeatNewPassword").val()){
          $("#pwderror").text("密码和确认密码不相同！");
        } else{
          $("#pwderror").text("");
        }
      }

      $scope.checkPhone=function(){
        var parttenPhone=/^((0?1[358]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
        if($("#newPhoneNum").val() == ""){
          $("#phoneerror").text("请输入手机号！").css("color", "red");
        } else if(!parttenPhone.test($("#newPhoneNum").val())){
          $("#phoneerror").text("请输入正确的手机号！").css("color", "red");
        } else{
          $("#phoneerror").text("");
        }
      }

    });
})

function thisDisplayNone(){
  $("#editMyPhone").css("display", "none");
  $("#resetMyPassword").css("display", "none");
}
