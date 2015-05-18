define(['angular', 'kitt','modal'],function(angular, kitt){
  return angular.module('kitt.article', ['kitt'])
    .config(function($routeProvider){
      $routeProvider
        .when("/article",{
          templateUrl:'views/article/list.html',
          controller:'article.List'
        })
        .when("/article/list",{
          templateUrl:'views/article/list.html',
          controller:'article.List'
        })
        .when("/article/addArticle",{
          templateUrl:'views/article/addArticle.html',
          controller:'article.Add'
        })
        .when("/article/editArticle",{
          templateUrl:'views/article/addArticle.html',
          controller:'article.Add'
        });
    })

    //文章信息列表
    .controller('article.List',function($scope, $route, $params){
      $scope.pid=$params('pid');
      $scope.content=$params('content',null);
      $scope.todelete={};
      $scope.searchArticle=function(){
        goto('/article/list', {pid:$scope.pid, content:$scope.content});
      };
      $scope.pageTo=function(page){
        goto('/article/list', {pid:$scope.pid, page:page, content:$params('content')});
      };
      service({
        url:'/article/list',
        data:{page:$params('page',1),content:$params("content",null),parentid:$params("pid",0)},
        success:function(data){
          $scope.data=data;
          $scope.parents=data.parents.reverse();
          $scope.$apply();
        }
      });
      $scope.changeLevel=function(article){
        if(article.sequence <= 100 && article.sequence > 0 && article.sequence % 1.0 == 0){
          service({
            url: '/article/changeSequence',
            data: {id: article.id, sequence: article.sequence},
            success: function (data) {
              if (data.success) {
                service({
                  url:'/article/list',
                  data:{page:$params('page',1),content:$params("content",null),parentid:$params("pid",0)},
                  success:function(data){
                    $scope.data=data;
                    $scope.parents=data.parents.reverse();
                    $scope.$apply();
                  }
                });
              }
            }
          })
        } else {
          alert("只能输入1-100之间的整数！");
          service({
            url:'/article/list',
            data:{page:$params('page',1),content:$params("content",null),parentid:$params("pid",0)},
            success:function(data){
              $scope.data=data;
              $scope.parents=data.parents.reverse();
              $scope.$apply();
            }
          });
        }
      };
      var deleteByIds=function(ids){
        service({
          url:'/article/delete',
          data:{ids:ids},
          success:function(data){
            if(data.success){
              $("#deleteConfirmDialog").modal('hide');
              refresh();
            }
          }
        })
      };
      //单条记录删除
      $scope.deleteConfirm=function(id){
        $("#deleteConfirmDialog").modal('show');
        $scope.deleteArticle=function(){
          deleteByIds([id]);
        }
      };

      //批量删除
      $scope.bulkDelete=function(){
        var ids=[];
        $.each($scope.todelete, function(k,v){
          if(v)
          ids.push(k);
        });
        $("#deleteConfirmDialog").modal('show');
        $scope.deleteArticle=function() {
          deleteByIds(ids);
        }
      }
    })

    //添加文章
    .controller('article.Add',function($scope,$params){
      var pid=$params('pid');
      var page=$params('page');
      $scope.page=page;
      $scope.article={};
      service({
        url: '/article/queryById',
        data: {id: $params('id',0)},
        success: function (data) {
          if (data.success) {
            $scope.article = data.article;
            $scope.$apply();
          }
        }
      })

      $scope.addArticle=function(){
        if($("#title").val().length < 2){
          $("#errorInfo").css("color", "red").text("标题不能少于2个字符");
        } else if($scope.article.content.length > 50000) {
          $("#errorContent").css("color", "red").text("文章正文不能超过5万个字符");
        } else{
          if($scope.article.parentid == null){
            $scope.article.parentid=pid;
          };
          service({
            url: '/article/add',
            data: $scope.article,
            success: function (data) {
              if (data.success) {
                $("#errorinfo").text("");
                goto("/article/list", {pid:pid});
              } else{
                if(data.error == "patherror"){
                  $("#errorContent").css("color", "red").text("文章路径错误，请更改标题！");
                } else{
                  $("#errorContent").css("color", "red").text("系统出错！");
                }
              }
            }
          })
        }
      }

      $scope.cancelArticle=function(){
        goto("/article/list");
      }
    })
});



