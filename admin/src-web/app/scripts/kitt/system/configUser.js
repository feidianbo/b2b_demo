
define(['angular','kitt','jquery.validation','modal'],function(angular, kitt) {
  return angular.module('kitt.system.configUser', ['kitt'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/system/addUser', {
          templateUrl: 'views/system/user/addUser.html',
          controller: 'UserCtrl'
        })
        .when('/system/addRole', {
          templateUrl: 'views/system/user/addRole.html',
          controller: 'RoleCtrl'
        })

        .when('/system/configMenuAuth', {
          templateUrl: 'views/system/user/configMenu.html',
          controller: 'RoleMenuCtrl'
        })
    })
    .controller('RoleCtrl',function($scope,$params) {
      service({
        url: '/admin/showRoles',
        success: function (data) {
          $scope.rolelist = data.list;
          $scope.pageNum = data.page;
          $scope.totalCount = data.count;
          $scope.$apply();
        }
      });
      $scope.pageTo=function(){
        goto('/admin/showRoles', {page: $scope.pageNum});
      }

      $scope.deleteRole=function(id){
        $("#roleId").val(id);
        $("#deleteRoleConfirmDialog").modal('show');
      };

      $scope.deleteRoleClick=function(){
        var id = $("#roleId").val();
        service({
          url:'/admin/deleteRole/'+id,
          success:function(data){
            if(data) {
              $("#deleteRoleConfirmDialog").modal('hide');
              location.reload();
            }
          }
        });
      };

      $scope.addRole=function(){
        checkForm("roleAddForm");
        $("#addRoleDialog").modal('show');
      };

      $scope.addRoleClick=function(){
        if(checkForm("roleAddForm").form()){
          service({
            url:'/admin/addRole',
            data:$scope.role,
            success:function(data){
              if(data) {
                $("#addRoleDialog").modal('hide');
                location.reload();
              }
            }
          });
        }

      };
      $scope.updateRoleForm=function(id){
        checkForm("updateRoleForm");
        service({
          url:'/admin/load/'+id,
          success:function(data){
              $scope.updateRole=data;
             $scope.$apply();
          }
        });
        $("#updateRoleDialog").modal('show');
      };

      $scope.updateRoleClick=function(){
        if(checkForm("updateRoleForm").form()) {
          service({
            url: '/admin/updateRole',
            data: $scope.updateRole,
            success: function (data) {
              if (data === true) {
                $("#updateRoleDialog").modal('hide');
                location.reload();
              }
            }
          });
        }
      };

      //跳转到配置菜单及权限页面
      $scope.configMenuAuth=function(id){
        window.location.href="/#/system/configMenuAuth?roleid="+id;
      };

      function checkForm(formID) {
        return $("#"+formID).validate({
          onsubmit: false,
          onkeyup: false,
          focusInvalid: false,
          errorElement: 'span',
          errorClass: 'help-block',
          rules: {
            rolename: {
              required: true,
              minlength: 2,
              maxlength: 30,
              remote:{
                url:'/admin/checkRoleExist'
              }
            },
            rolecode: {
              required: true,
              minlength: 2,
              maxlength: 30,
              remote:{
                url:'/admin/checkRoleExist'
              }
            }
          },
          messages: {
            rolename: {
              required: '请输入角色名',
              minlength: '长度不能小于2位',
              maxlength: '长度不能大于30位',
              remote:"角色名称已经存在"

            },
            rolecode: {
              required: '请输入角色编码',
              minlength: '长度不能小于2位',
              maxlength: '长度不能大于30位',
              remote:"角色编码已经存在"

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
    })
    .controller('UserCtrl',function($scope,$params) {
      //加载用户列表
      service({
        url:'/admin/adminList',
        data:{page:$params('page',1)},
        success:function(data){
          $scope.admin=data.admin;
          $scope.page=data.admin.page;
          $scope.$apply();
        }
      });
      //加载所有角色
      service({
        url:'/admin/loadAllRoles',
        data:{},
        success:function(data){
          $scope.roleList=data;
          $scope.$apply();
        }
      });
      $scope.pageTo=function(){
        goto('/system/addUser', {page:$scope.admin.page});
      };
      $scope.configUserRole=function(userId,username){
        //加载所有角色信息
       var $roleCheckbox= $('[name=userRoles]:checkbox');
        $roleCheckbox.attr("checked",false);
        $scope.username=username;
        $scope.userId=userId;
        service({
          url:'/admin/findRoleByUserId',
          data:{userId:userId},
          success:function(data){
            $.each(data,function(){
                var that = this;
                $roleCheckbox.each(function(){
                  if($(this).val()==that){
                    $(this).attr("checked",true);
                  }
                });
            });
            $("#configUserRoleDialog").modal('show');
          }
        });
      }

      $scope.configUserRoleConfirm=function() {
        service({
          url:'/admin/updateRole',
          data:$("#configUserRoleForm").serialize()+"&userId="+$scope.userId,
          success:function(data){
            if(data){
              $("#configUserRoleDialog").modal('hide');
                location.reload();
            }
          }
        });
      }

      //添加用户
      $scope.addUser=function(){
        checkAddUser();
        $("#addUserDialog").modal('show');
      };

      $scope.addUserClick=function(){
       if(checkAddUser().form()){
        service({
          url:'/admin/addAdmin',
          data:$("#addUserForm").serialize(),
          success:function(data){
            $("#addUserDialog").modal('hide');
            location.reload();
          }
        });
      }}
      //表单验证
      function checkAddUser(){
        jQuery.validator.addMethod("mobileNum", function (value, element) {
          return this.optional(element) || /^[1][3,4,5,8][0-9]{9}$/.test(value);
        }, "请输入有效的手机号码!");
        return $("#addUserForm").validate({
          onsubmit: false,
          onkeyup: false,
          focusInvalid: false,
          errorElement: 'span',
          errorClass: 'help-block',
          rules: {
            username: {
              required: true,
              minlength: 2,
              maxlength: 30,
              remote:{
                url:'/admin/checkUsernameExist'
              }
            },
            name:{
              required: true,
              minlength: 2,
              maxlength: 30
            },
            phone: {
              required: true,
              mobileNum:true,
              remote:{
                url:'/admin/checkphoneExist'
              }
            }
          },
          messages:{
            username: {
              required: '请输入用户名',
              minlength: '长度不能小于{0}位',
              maxlength: '长度不能大于{0}位',
              remote:'用户名已存在!'
            },
            phone: {
              required: '请输入手机号码',
              remote:'手机号已存在!'
            },
            name: {
              required: '请输入用户名',
              minlength: '长度不能小于{0}位',
              maxlength: '长度不能大于{0}位'

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
        })
      }

      $scope.changeStatus=function(id,isActive){
        service({
          url:'/admin/changeStatus',
          data:{id:id,isActive:isActive},
          success:function(data){
            location.reload();
          }
        });
      };

      //重置密码
      $scope.initPassword=function(id){
        $("#id").val(id);
        $("#initPwdConfirmDialog").modal('show');
      };

      $scope.initPasswordClick=function(){
        var id = $("#id").val();
        service({
          url:'/admin/initPassword',
          data:{id:id},
          success:function(data){
            $("#initPwdConfirmDialog").modal('hide');
          }
        });
      };

    })
      .controller('RoleMenuCtrl',function($scope,$params) {

      $("#roleid").val($params('roleid'));
      service({
        url: '/admin/getMenusAuth',
        data:{roleid:$params('roleid')},
        success: function (data) {
          if (data) {
            $scope.parentMenusList = data.parentMenusList;
            $scope.childMenusList = data.childMenusList;
            $scope.operateauthList = data.operateauthList;
            $scope.$apply();
            var userMenusList = data.userMenusList;
            var userOperateList = data.userOperateList;
            for(var i=0;i<userMenusList.length;i++){
              $('[name=menuid]:checkbox').each(function(){
                  if(userMenusList[i].menuid == $(this).val()){
                    $(this).attr("checked",true);
                  }
              });
            }

            for(var i=0;i<userOperateList.length;i++){
              $('[name=operatecode]:checkbox').each(function(){
                if(userOperateList[i].operatecode == $(this).val()){
                  $(this).attr("checked",true);
                }
              });
            }

          }
        }
      });

      //取消配置
      $scope.cancelConfigMenu=function(){
        window.location.href="/#/system/addRole";
      };

      //保存配置
      $scope.confirmConfigMenu=function(){
        var menuidArr = "";
        var operatecodeArr = "";
        $('[name=menuid]:checkbox:checked').each(function(){
          menuidArr += $(this).val()+",";
        });

        $('[name=operatecode]:checkbox:checked').each(function(){
          operatecodeArr += $(this).val()+",";
        });

        var roleid = $("#roleid").val();
        service({
          url: '/admin/saveRoleMenuAuth',
          data:{roleid:roleid,menuidArr:menuidArr,operatecodeArr:operatecodeArr},
          success: function (data) {
            window.location.href="/#/system/addRole";
          }
        });
      };

  });

})

