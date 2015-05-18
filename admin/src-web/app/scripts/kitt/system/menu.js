/**
 * Created by jack on 15/3/5.
 */
define(['angular','kitt', 'modal', 'jquery.validation','bootstrap3-datetimepicker'],function(angular, kitt) {
  return angular.module('kitt.menu', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/menu', {
          templateUrl: 'views/system/menu/list.html',
          controller: 'menu.List'
        })
        .when('/menu/list',{
          templateUrl: 'views/system/menu/list.html',
          controller: 'menu.List'
        });
    })

    //菜单列表
    .controller('menu.List',function($scope,$params) {
      service({
        url: '/menu/list',
        success: function (data) {
          $scope.frameList = data.frameList;
          $("#parentid").html("<select><option selected value='0'> -- 无 -- </option></select>");
          $scope.$apply();
        }
      });

      //菜单等级切换事件
      $("#menulevel").change(function(){
        if($("#menulevel").val() != 1){
          service({
            data:{level: $("#menulevel").val()},
            url: '/menu/getParentMenus',
            success: function(data){
              var jsonStr = "";
              $.each(data, function (i, item) {
                jsonStr += '<option value=' + item.id + '>' + item.menuname + '</option>';
              });
              $("#parentid").html(jsonStr);
            }
          })
        } else{
          $("#parentid").html("<option selected value='0'> -- 无 -- </option>");
        }
      })

      //菜单URL blur 事件
      $("#url").blur(function(){
        checkURL();
      })

      //菜单顺序 blur 事件
      $("#sequence").blur(function(){
        checkSequence();
      })

      $("#menuname").blur(function(){
        checkMenuName();
      })

      $scope.deleteMenu=function(id){
        $(".dialog_Common").modal('show');
        $(".dialog_CommonHeader").text("删除菜单");
        $(".dialog_CommonBody").text("该菜单将要被删除，如果有子菜单，会被一起删除，你确定要这样做吗？")
        $(".dialog_confirmbtn").text("确认删除");
        $('.dialog_confirmbtn').attr("id","delete_confirm");
        $("#delete_confirm").bind("click", function(){
          deleteMenu(id);
        });
      }
      function deleteMenu(id){
        service({
          data:{id: id},
          url: "/menu/deleteMenu",
          success: function(data){
            if(data) {
              $(".dialog_Common").modal('hide');
              service({
                url: '/menu/list',
                success: function (data) {
                  $scope.frameList = data.frameList;
                  $("#parentid").html("<select><option selected value='0'> -- 无 -- </option></select>");
                  $scope.$apply();
                }
              });
            } else{
              $("#commonErrorInfo").text("系统出错，删除失败！");
            }
          }
        })
      }

      $scope.updateMenu=function(id){
        service({
          data: {id: id},
          url: "/menu/getMenuById",
          success: function(data){
            $scope.menuid=id;
            if(data.parentid == 0) {
              $scope.menulevel = 1;
            } else{
              $scope.menulevel = 2;
            }
            $scope.newmenu = data;
            $scope.$apply();
            if(data.parentid == 0){
              $("#parentid").html("<option value='0' selected> -- 无 -- </option>");
            } else{
              service({
                data:{level: $scope.menulevel},
                url: '/menu/getParentMenus',
                success: function(data){
                  var jsonStr = "";
                  $.each(data, function (i, item) {
                    if ($scope.newmenu.parentid == item.id) {
                      jsonStr += '<option value=' + item.id + ' selected>' + item.menuname + '</option>';
                    } else {
                      jsonStr += '<option value=' + item.id + '>' + item.menuname + '</option>';
                    }
                    });
                  $("#parentid").html(jsonStr);
                }
              })
            }
            $(".dialog_Menu").modal('show');
            $(".dialog_Header").text("修改菜单");
          }
        })
      }

      $scope.addMenu=function(){
        $scope.newmenu={};
        $scope.menulevel=1;
        $("#parentid").html("<option value='0' selected> -- 无 -- </option>");
        $(".dialog_Menu").modal('show');
        $(".dialog_Header").text("添加菜单");
      }

      $scope.addUpdateMenu = function(){
        if(checkURL() && checkSequence() && checkMenuName()) {
          $scope.newmenu.parentid = $("#parentid").val();
          service({
            method: 'POST',
            data:$scope.newmenu,
            url:"/menu/addUpdate",
            success:function(data){
              if(data){
                $(".dialog_Menu").modal("hide");
                service({
                  url: '/menu/list',
                  success: function (data) {
                    $scope.frameList = data.frameList;
                    $("#parentid").html("<select><option selected value='0'> -- 无 -- </option></select>");
                    $scope.$apply();
                  }
                });
              } else{
                $("#errorInfo").text("同一目录下已存在相同名字的菜单，操作失败！");
              }
            }
          })
        }
      }

      $scope.showMenus=function(id){
        var sub = "sub" + id;
        if(document.getElementById){
          var el = document.getElementById(sub);
          var ar = document.getElementById("allMenus").getElementsByTagName("span");
          if(el.style.display != "block"){
            for (var i=0; i<ar.length; i++) {
              if (ar[i].className == "secondMenu")
                ar[i].style.display = "none";
            }
            el.style.display = "block";
          } else{
            el.style.display = "none";
          }
        }
      };

      $scope.changeSequence=function(menu){
        if(!(menu.sequence > 0 && menu.sequence <= 100 && menu.sequence % 1.0 == 0)) {
          alert("只能输入1-100之间的整数！");
          service({
            url: '/menu/list',
            success: function (data) {
              $scope.frameList = data.frameList;
              $("#parentid").html("<select><option selected value='0'> -- 无 -- </option></select>");
              $scope.$apply();
            }
          });
        } else{
          service({
            data: {id: menu.id, sequence: menu.sequence},
            url: '/menu/changeSequence',
            success: function (data) {
              if (data) {
                service({
                  url: '/menu/list',
                  success: function (data) {
                    $scope.frameList = data.frameList;
                    $("#parentid").html("<select><option selected value='0'> -- 无 -- </option></select>");
                    $scope.$apply();
                  }
                });
              } else {
                alert("系统出错，更改失败！");
              }
            }
          })
        }
      }

      function SwitchMenu(obj){
        if(document.getElementById){
          var el = document.getElementById(obj);
          var ar = document.getElementById("masterdiv").getElementsByTagName("span");
          if(el.style.display != "block"){
            for (var i=0; i<ar.length; i++){
              if (ar[i].className=="submenu")
                ar[i].style.display = "none";
            }
            el.style.display = "block";
          } else{
            el.style.display = "none";
          }
        }
      }

      window.onload=function(e) {
        e = e || window.event;
        SwitchMenu('sub1');
      }

    });
})

function checkURL(){
  if($("#url").val().length > 50 || $("#url").val().length < 1){
    $("#urlerror").css("color", "red");
    return false;
  } else{
    $("#urlerror").css("color", "green");
    return true;
  }
}

function checkSequence(){
  if($("#sequence").val() != "") {
    if (!($("#sequence").val() > 0 && $("#sequence").val() <= 100 && $("#sequence").val() % 1.0 == 0)) {
      $("#sequenceerror").css("color", "red");
      return false;
    } else {
      $("#sequenceerror").css("color", "green");
    }
  }
  return true;
}

function checkMenuName(){
  if($("#menuname").val().length > 20 || $("#menuname").val().length < 2){
    $("#menunameerror").css("color", "red");
    return false;
  } else{
    $("#menunameerror").css("color", "green");
    return true;
  }
}


