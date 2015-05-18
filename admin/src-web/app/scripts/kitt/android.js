
define(['angular','kitt'],function(angular, kitt){
  return angular.module('kitt.android', ['kitt'])
    .config(function($routeProvider){
      $routeProvider
        .when("/android",{
          templateUrl:'views/android/android.html',
          controller:'AndroidCtrl'
        })
    })

    .controller('AndroidCtrl',function($scope,$params) {
      service({
        url:'/android/getVersion',
        success:function(data){
          $scope.version=data.version;
          $scope.today=data.today;
          $scope.$apply();
        }
      });

      $scope.changeAPK=function(){
        $("#changeBtn").hide();
        $("#uploadAPK").show();
      };

      $scope.uploadAPK=function(){
        var filePath = $("#filePath").val();
        var mime = filePath.toLowerCase().substr(filePath.lastIndexOf("."));
        if(filePath == ""){
          $("#typeError").text("请选择文件!").css("color","red");
          return ;
        }else if(mime != ".apk"){
          $("#typeError").text("请选择apk文件上传!").css("color","red");
          return ;
        }else{
          $("#typeError").text("");
        }
        $('#form-apk').ajaxSubmit({success:function(data){
          $("#uploadPath").val(data.uploadPath);
          $('#form-apk').hide();
          $('#tipMsg').show();
          }
        });
      };

      $scope.releaseAPK=function(){
        var uploadPath = $("#uploadPath").val();
        var newVersion = $("#newVersion").val();
        if(uploadPath == null || uploadPath ==""){
          $("#errorMsg").text("请上传文件!");
          return;
        }else{
          $("#errorMsg").text("");
        }

        if(newVersion == null || newVersion ==""){
          $("#errorMsg").text("请填写版本号");
          return;
        }else{
          $("#errorMsg").text("");
        }

        service({
          url:'/android/saveVersion',
          data:{newVersion:newVersion},
          success:function(data){
            if(data.success) {
              location.reload();
            }
          }
        });

      };

    })

});
