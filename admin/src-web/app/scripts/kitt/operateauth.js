
define(['angular','kitt','modal'],function(angular, kitt){
  return angular.module('kitt.operateauth', ['kitt']).config(function($routeProvider){
    $routeProvider.when("/operateauth",{
      templateUrl:'views/system/operate/list.html',
      controller:'OperateAuthCtrl'
    })
  })

    .controller('OperateAuthCtrl',function($scope,$params) {

    $scope.pageTo=function(){
      goto('/operateauth', {page:$scope.operateauth.page});
    };
    service({
      url:'/operateauth/list',
      data:{page:$params('page',1)},
      success:function(data){
        $scope.operateauth=data.operateauth;
        $scope.page=data.operateauth.page;
        $scope.$apply();
      }
    });

    $scope.deleteOperate=function(id){
      $("#operateauthId").val(id);
      $("#deleteOperateConfirmDialog").modal('show');
    };

    $scope.deleteOperateClick=function(){
      var id = $("#operateauthId").val();
      service({
        url:'/operateauth/deleteOperateauth',
        data:{id:id},
        success:function(data){
          $("#deleteOperateConfirmDialog").modal('hide');
          location.reload();
        }
      });

    };

    //添加
    $scope.addOperate=function(){
      $("#addOperateauthDialog").modal('show');
      service({
        url:'/operateauth/getParentMenus',
        success:function(data){
          //$scope.pmenusList = data.pmenusList;
          //$scope.$apply();
          var jsonStr = "";
          $.each(data.pmenusList, function (i, item) {
            jsonStr += '<option value=' + item.id + '>' + item.menuname + '</option>';
          });
          $("#pMenuid").html(jsonStr);
        }
      });
    };

    $scope.getChildMenus=function(){
      var pmenuid = $("#pMenuid").val();
      service({
        url:'/operateauth/getChildMenus',
        data:{id:pmenuid},
        success:function(data){
          //$scope.cmenusList = data.cmenusList;
          //$scope.cmenusList.id=data.cmenusList[0].id;
          //$scope.$apply();

          var jsonStr = "";
          $.each(data.cmenusList, function (i, item) {
            jsonStr += '<option value=' + item.id + '>' + item.menuname + '</option>';
          });
          $("#cMenuid").html(jsonStr);
        }
      });
    };

    $scope.addOperateClick=function(){
      if(checkForm()){

        //ajax验证是否操作代码唯一
        var operatecode = $("#operatecode").val();
        service({
          url: '/operateauth/checkOperatecode',
          data: {operatecode: operatecode},
          success: function (data) {
            if (data == "1") {
              $("#operatecodeError").text("操作代码已存在").css("color", "red");
              return;
            } else {
              $("#operatecodeError").text("");

              //请求保存
              var pmenuid = $("#pMenuid").val();
              var cmenuid = $("#cMenuid").val();
              var operatename = $("#operatename").val();
              var operatecode = $("#operatecode").val();

              var menuid = null;
              if (cmenuid != null && cmenuid != "") {
                menuid = cmenuid;
              } else {
                menuid = pmenuid;
              }
              service({
                url: '/operateauth/saveOperateauth',
                data: {menuid: menuid, operatename: operatename, operatecode: operatecode},
                success: function (data) {
                  $("#addOperateauthDialog").modal('hide');
                  location.reload();
                }
              });

            }

          }
        });
      }

    };

  });

  function checkForm(){
    var pass = true;
    var menuid = $("#menuid").val();
    var operatename = $("#operatename").val();
    var operatecode = $("#operatecode").val();
    if(menuid == null || menuid == ""){
      $("#menuidError").text("所属菜单不能为空").css("color","red");
      pass = false;
    }else{
      $("#menuidError").text("");
    }
    if(operatename == null || operatename == ""){
      $("#operatenameError").text("操作名称不能为空").css("color","red");
      pass = false;
    }else{
      $("#operatenameError").text("");
    }

    if(operatecode == null || operatecode == ""){
      $("#operatecodeError").text("操作代码不能为空").css("color","red");
      pass = false;
    }else{
      $("#operatecodeError").text("");
    }

  }
});
