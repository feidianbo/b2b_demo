define(['angular', 'kitt'],function(angular, kitt){
  return angular.module('kitt.user', ['kitt'])
    .config(function($routeProvider){
      $routeProvider
        .when("/user/wait",{
          templateUrl:'views/user/list.html',
          controller:'user.Wait'
        })
        .when("/user/pass",{
          templateUrl:'views/user/list.html',
          controller:'user.Pass'
        })
        .when("/user/fail",{
          templateUrl:'views/user/list.html',
          controller:'user.Fail'
        })
        .when("/user/info",{
          templateUrl:'views/user/list.html',
          controller:'user.Info'
        })
        .when("/user/view",{
          templateUrl:'views/user/userInfo.html',
          controller:'user.View'
        });
    })
    //待审核
    .controller('user.Wait', function($scope, $route, $params){
      $scope.type='wait';
      var status ="待审核";
      $scope.securephone=$params('securephone',null);
      $scope.searchWaitInfo=function(){
        goto('/user/wait', {securephone:$scope.securephone});
      };
      $scope.pageToWait=function(){
        goto('/user/wait', {page:$scope.wait.page, secrephone:$params('securephone')});
      };
      service({
        url:'/user/userlist',
        data:{page:$params('page',1), securephone:$params('securephone', null),status:status},
        success:function(data){
          $scope.wait=data.userList;
          $scope.$apply();
        }
      })
    })
    //审核通过
    .controller('user.Pass', function($scope, $route, $params){
      $scope.type='pass';
      var status="审核通过";
      function loadParams(){
        var params = {page:$scope.pass.page,status:status};
        if($createtime1.val()!=""){
          params['startDate']=$createtime1.val();
        }
        if($createtime2.val()!=""){
          params['endDate']=$createtime2.val();
        }
        if($scope.securephone!=""){
          params['securephone']=$scope.securephone;
        }
        return params;
      }
      $scope.searchPassInfo=function(){
        var params=loadParams();
        params['page']=1;
        goto('/user/pass', params);
      };
      $scope.pageToPass=function(){
        goto('/user/pass', loadParams());
      };
      //存页面上Dom元素
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
            url: '/user/count',
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
          var url=URI("/user/downloadData");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }
      service({
        url:'/user/userlist',
        data:{page:$params('page',1),securephone:$params('securephone',""),status:$params('status',status),startDate:$params('startDate'),endDate:$params('endDate')},
        success:function(data){
          $scope.pass=data.userList;
          $scope.securephone=data.securephone;
          $scope.createtime1=data.startDate;
          $scope.createtime2=data.endDate;
          $scope.$apply();
        }
      })
    })
    //审核未通过
    .controller('user.Fail', function($scope, $route, $params){
      $scope.type='fail';
      var status="审核未通过";
      //存页面上Dom元素
      var $date1=$("#date3");
      var $date2=$("#date4");
      var $createtime1 = $("#createtime3");
      var $createtime2 = $("#createtime4");
      function loadParams(){
        var params = {page:$scope.fail.page,status:status};
        if($createtime1.val()!=""){
          params['startDate']=$createtime1.val();
        }
        if($createtime2.val()!=""){
          params['endDate']=$createtime2.val();
        }
        if($scope.securephone!=""){
          params['securephone']=$scope.securephone;
        }
        return params;
      }
      $scope.searchFailInfo=function(){
        var params=loadParams();
        params['page']=1;
        goto('/user/fail', params);
      };
      $scope.pageToFail=function(){
        goto('/user/fail', loadParams());
      };
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
            url: '/user/count',
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
          var url=URI("/user/downloadData");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }


      service({
        url:'/user/userlist',
        data:{page:$params('page',1),securephone:$params('securephone',""),status:$params('status',status),startDate:$params('startDate'),endDate:$params('endDate')},
        success:function(data){
          $scope.fail=data.userList;
          $scope.securephone=data.securephone;
          $scope.createtime1=data.startDate;
          $scope.createtime2=data.endDate;
          $scope.$apply();
        }
      })
    })
    //待完善信息
    .controller('user.Info', function($scope, $route, $params){
      $scope.type='infos';
      var status="待完善信息";
      //存页面上Dom元素
      var $date1=$("#date5");
      var $date2=$("#date6");
      var $createtime1 = $("#createtime5");
      var $createtime2 = $("#createtime6");
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
      function loadParams(){
        var params = {page:$scope.infos.page,status:status};
        if($createtime1.val()!=""){
          params['startDate']=$createtime1.val();
        }
        if($createtime2.val()!=""){
          params['endDate']=$createtime2.val();
        }
        if($scope.securephone!=""){
          params['securephone']=$scope.securephone;
        }
        return params;
      }
      $scope.searchImproveInfo=function(){
        var params=loadParams();
        params['page']=1;
        goto('/user/info', params);
      };
      $scope.pageToImproveInfo=function(){
        goto('/user/info', loadParams());
      };
      $scope.exportExcel=function(){
        if($createtime2.val()!='' && $createtime1.val()==''){
          $("#alert > span:last").text('请选择开始时间');
          $("#alert").show();
        }else{
          service({
            url: '/user/count',
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
          var url=URI("/user/downloadData");
          url=url.query(loadParams());
          window.location.href=url.toString();
        }
      }
      service({
        url:'/user/userlist',
        data:{page:$params('page',1),securephone:$params('securephone',""),status:$params('status',status),startDate:$params('startDate'),endDate:$params('endDate')},
        success:function(data){
          $scope.infos=data.userList;
          $scope.securephone=data.securephone;
          $scope.createtime1=data.startDate;
          $scope.createtime2=data.endDate;
          $scope.$apply();
        }
      })
    })

    //用户详情
    .controller('user.View',function($scope,$route,$params){
      if($params('status') != 'pass'){
        $("#groupbuy").hide();
      }

      //按钮权限
      window.initAuth();

      $("#form-companyInformation").css("display","none");
      $("#form-businessLicense").css("display","none");
      $("#form-identificationNumber").css("display","none");
      $("#form-operatingLicense").css("display","none");
      $("#form-organizationCode").css("display","none");
      $("#form-openingLicense").css("display", "none");

      /*$("iframe").click(function(){
        var imgSrc=$(this).attr('src');
        $(".img-dialog").modal('show');
        $("#imgBig").attr("src",imgSrc);
        $("#img-dialog").modal('show');
      });*/

    //$scope.checkPhone=function(){
    //  var partten=/^((0?1[358]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
    //  if(!partten.test($scope.company.traderphone)){
    //    $("#error").css("color","red").html("请输入合法的手机号码!");
    //  }
    //}

    //$scope.clearPhoneInfo=function(){
    //  $("#error").text("");
    //}

    $scope.clearAccountInfo=function(){
      $("#error").text("");
    }

    $scope.checkAccount=function(){
      var partten=/^(- ¦\+)?\d+$/;
      if(!partten.test($scope.company.account)){
        $("#error").css("color","red").html("请输入合法的银行账号!");
      }
    }

    $scope.checkZipcode=function(){
      var partten=/^\d{6}$/;
      if(!partten.test($scope.company.zipcode)){
        $("#error").css("color", "red").html("请输入合法的邮政编码!");
      }
    }

    $scope.clearZipcodeInfo=function(){
      $("#error").text("");
    }

    //用户公司信息
    service({
       url:'/user/view',
       data:{securephone:$params('securephone')},
       success:function(data){
         if(data.success) {
           if (data.user.isactive == '1') {
             $("#btn_status").text("禁用此用户");
           } else {
             $("#btn_status").text("启用此用户");
           }
           $scope.user = data.user;
           $scope.company = data.company;
           $scope.checkList = data.compverifyList;
           $scope.$apply();
           $("#user-isactive").val(data.user.isactive);
           if (!data.company) {
             $("#btn_passCheck").attr("disabled", "disabled");
             $("#btn_rejectCheck").attr("disabled", "disabled");
           } else {
             $("#companyId").val(data.company.id);
             if (data.company.verifystatus == "待审核") {
               $("#btn_passCheck").attr("disabled", false);
               $("#btn_rejectCheck").attr("disabled", false);
             } else {
               $("#btn_passCheck").attr("disabled", "disabled");
               $("#btn_rejectCheck").attr("disabled", "disabled");
             }
           }
         } else{
         }
       }
     })
    $scope.checkPass=function(){
      var reason=$("#reason").val();
      if(reason.length > 100){
        $("#info").text("原因不能超过100个字符！").css("color", "red").css("margin-left", "28px");
      } else {
        service({
          url: '/user/verifypass',
          data: {companyId: $("#companyId").val(), remarks: $("#reason").val()},
          success: function (data) {
            if (data) {
              $("#info").text("审核已通过").css("color", "blue").css("margin-left", "28px");
              $("#btn_passCheck").attr("disabled", "disabled");
              $("#btn_rejectCheck").attr("disabled", "disabled");
            } else {
            }
          }
        })
      }
    }

    $scope.checkReject=function(){
      var reason=$("#reason").val();
      if(reason==""){
        $("#info").text("请填写审核不通过原因!").css("color","red").css("margin-left", "28px");
        return;
      } else if(reason.length > 100){
        $("#info").text("原因不能超过100个字符！").css("color", "red").css("margin-left", "28px");
      } else{
        service({
          url:'/user/verifyreject',
          data:{companyId:$("#companyId").val(),remarks:reason},
          success:function(data){
            if(data){
              $("#info").text("审核未通过").css("color","red").css("margin-left", "28px");
              $("#btn_passCheck").attr("disabled","disabled");
              $("#btn_rejectCheck").attr("disabled","disabled");
            } else{
            }
          }
        })
      }
    }

    //更换图片 公司法人
    $scope.changeCompanyInformation=function(){
      $("#form-companyInformation").css('display','block');
      $("#btn_companyInformation").css("display","none");
      $("#companyInformationClick").click(function(){
        var companyInformation = $("#companyInformation").val();
        var mime = companyInformation.toLowerCase().substr(companyInformation.lastIndexOf("."));
        if(companyInformation == ""){
          $("#companyInformationInfo").text("企业开票信息!").css("color","red");
          return ;
        } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
          $("#companyInformationInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
          return ;
        } else{
          $("#companyInformationInfo").text("");
        }
        $('#form-companyInformation').ajaxSubmit({success:function(data){
          if(data.success) {
            $("#picForCompanyInformation").attr("width", 600);
            $("#picForCompanyInformation").attr("height", 500);
            $("#picForCompanyInformation").attr("src", data.filePath);
            $("#invoicinginformation").val(data.filePath);
            $("#form-companyInformation").css("display", "none");
            $("#btn_companyInformation").css("display", "inline-block");
          } else{
            $("#companyInformationInfo").text("只能上传大小10M以内的图片！").css("color", "red");
          }
        }
        });
      });
    };

    //下载
    $scope.downloadCertification=function(url){
      window.location.href='/certificate/downloadCertification?url=' + url;
    }

    //营业执照
    $scope.changeBusinessLicense=function(){
      $("#form-businessLicense").css('display','block');
      $("#btn_businessLicense").css("display","none");
      $("#businessLicenseClick").click(function(){
        var businessLicense = $("#businessLicense").val();
        var mime = businessLicense.toLowerCase().substr(businessLicense.lastIndexOf("."));
        if(businessLicense == ""){
          $("#businessLicenseInfo").text("请选择营业执照!").css("color","red");
          return ;
        }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
          $("#businessLicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
          return ;
        }else{
          $("#businessLicenseInfo").text("");
        }
        $('#form-businessLicense').ajaxSubmit({success:function(data){
          if(data.success) {
            $("#picForBusinessLicense").attr("width", 600);
            $("#picForBusinessLicense").attr("height", 500);
            $("#picForBusinessLicense").attr("src", data.filePath);
            $("#businessLic").val(data.filePath);
            $("#form-businessLicense").css("display", "none");
            $("#btn_businessLicense").css("display", "inline-block");
          } else{
            $("#businessLicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
          }
        }
        });
      });
    };

    //纳税人识别号
    $scope.changeIdentificationNumber=function(){
      $("#form-identificationNumber").css('display','block');
      $("#btn_identificationNumber").css("display","none");
      $("#identificationNumberClick").click(function(){
        var identificationNumber = $("#identificationNumber").val();
        var mime = identificationNumber.toLowerCase().substr(identificationNumber.lastIndexOf("."));
        if(identificationNumber == ""){
          $("#identificationNumberInfo").text("请选择纳税人识别号!").css("color","red");
          return ;
        } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
          $("#identificationNumberInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
          return ;
        } else{
          $("#identificationNumberInfo").text("");
        }
        $('#form-identificationNumber').ajaxSubmit({success:function(data){
          if(data.success) {
            $("#picForIdentificationNumber").attr("width", 600);
            $("#picForIdentificationNumber").attr("height", 500);
            $("#picForIdentificationNumber").attr("src", data.filePath);
            $("#identityNumber").val(data.filePath);
            $("#form-identificationNumber").css("display", "none");
            $("#btn_identificationNumber").css("display", "inline-block");
          } else{
            $("#identificationNumberInfo").text("只能上传大小10M以内的图片！").css("color", "red");
          }
        }
        });
      });
    };

    //经营许可证
    $scope.changeOperatingLicense=function(){
      $("#form-operatingLicense").css('display','block');
      $("#btn_operatingLicense").css("display","none");
      $("#operatingLicenseClick").click(function(){
        var operatingLicense = $("#operatingLicense").val();
        var mime = operatingLicense.toLowerCase().substr(operatingLicense.lastIndexOf("."));
        if(operatingLicense == ""){
          $("#operatingLicenseInfo").text("请选择经营许可证!").css("color","red");
          return ;
        } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
          $("#operatingLicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
          return ;
        } else{
          $("#operatingLicenseInfo").text("");
        }
        $('#form-operatingLicense').ajaxSubmit({success:function(data){
          if(data.success) {
            $("#picForOperatingLicense").attr("width", 600);
            $("#picForOperatingLicense").attr("height", 500);
            $("#picForOperatingLicense").attr("src", data.filePath);
            $("#operationLicense").val(data.filePath);
            $("#form-operatingLicense").css("display", "none");
            $("#btn_operatingLicense").css("display", "inline-block");
          } else{
            $("#operatingLicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
          }
        }
        });
      });
    };

      //开户许可证
      $scope.changeOpeningLicense=function(){
        $("#form-openingLicense").css('display','block');
        $("#btn_openingLicense").css("display","none");
        $("#openingLicenseClick").click(function(){
          var openingLicense = $("#openingLicense").val();
          var mime = openingLicense.toLowerCase().substr(openingLicense.lastIndexOf("."));
          if(openingLicense == ""){
            $("#openingLicenseInfo").text("请选择经营许可证!").css("color","red");
            return ;
          } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
            $("#openingLicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
            return ;
          } else{
            $("#openingLicenseInfo").text("");
          }
          $('#form-openingLicense').ajaxSubmit({success:function(data){
            if(data.success) {
              $("#picForOpeningLicense").attr("width", 600);
              $("#picForOpeningLicense").attr("height", 500);
              $("#picForOpeningLicense").attr("src", data.filePath);
              $("#openLicense").val(data.filePath);
              $("#form-openingLicense").css("display", "none");
              $("#btn_openingLicense").css("display", "inline-block");
            } else{
              $("#openingLicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
            }
          }
          });
        });
      };

    //组织机构代码
    $scope.changeOrganizationCode=function(){
      $("#form-organizationCode").css('display','block');
      $("#btn_organizationCode").css("display","none");
      $("#organizationCodeClick").click(function(){
        var organizationCode = $("#organizationCode").val();
        var mime = organizationCode.toLowerCase().substr(organizationCode.lastIndexOf("."));
        if(organizationCode == ""){
          $("#organizationCodeInfo").text("请选择经营许可证!").css("color","red");
          return ;
        } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
          $("#organizationCodeInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!").css("color", "red");
          return ;
        } else{
          $("#organizationCodeInfo").text("");
        }
        $('#form-organizationCode').ajaxSubmit({success:function(data){
          if(data.success) {
            $("#picForOrganizationCode").attr("width", 600);
            $("#picForOrganizationCode").attr("height", 500);
            $("#picForOrganizationCode").attr("src", data.filePath);
            $("#orgCode").val(data.filePath);
            $("#form-organizationCode").css("display", "none");
            $("#btn_organizationCode").css("display", "inline-block");
          } else{
            $("#organizationCodeInfo").text("只能上传大小10M以内的图片！").css("color", "red");
          }
        }
        });
      });
    };

    //保存信息
    $scope.saveCompInfo=function() {
      var parttenBank = /^(- ¦\+)?\d+$/;
      var parttenZipcode=/^\d{6}$/;
      if (!parttenBank.test($scope.company.account)) {
        $("#error").css("color", "red").html("请输入合法的银行账号!");
        return;
      } else if(!parttenZipcode.test($scope.company.zipcode)){
        $("#error").css("color", "red").html("请输入合法的邮政编码!");
        return;
      } else if($("#comName").val()=="" || $("#address").val()=="" || $("#personName").val()=="" || $("#bank").val()=="" || $("#account").val()=="" || $("#invoicinginformation").val() == "" || $("#businessLic").val() == "" || $("#identityNumber").val() == "" || $("#orgCode").val() == ""){
        $("#error").text("请将信息填写完整后再执行提交操作!").css("color","red");
        return;
      }
      service({
        url:'/user/saveCompanyInfo',
        data:{name:$("#comName").val(),address:$("#address").val(),legalpersonname:$("#personName").val(),openingbank:$("#bank").val(),account:$("#account").val(),userid:$scope.user.id, invoicinginformation:$("#invoicinginformation").val(),businesslicense:$("#businessLic").val(),identificationnumber:$("#identityNumber").val(),operatinglicense:$("#operationLicense").val(), openinglicense:$("#openLicense").val(),organizationcode:$("#orgCode").val(), zipcode:$("#zipcode").val(), identificationnumword:$("#identificationnumword").val()},
        success:function(data){
          if(data){
            location.href="/#/user/wait";
          } else{
          }
        }
      })
    };

      //禁用/启用账户
    $scope.setStatus=function(){
        service({
            url:'/user/account',
            data:{securephone:$params('securephone')},
            success:function(data){
              if(data.success){
                $("#user-isactive").val(data.isactive);
                if(data.isactive){
                  $("#btn_status").text("禁用此用户");
                } else if(!data.isactive){
                  $("#btn_status").text("启用此用户");
                } else{
                }
              } else{
              }
            }
        })
     };

  //重置按钮点击事件
  $scope.resetPassword=function(){
    service({
      url:'/user/edit',
      data:{securephone:$params('securephone')},
      success:function(data){
        if(data){
          $("#resetInfo").text("重置密码成功").css("color","blue");
        } else{
          $("#resetInfo").text("重置密码失败").css("color", "red");
        }
      }
    })
  };
  //团购资格申请放弃
  service({
    url:'/groupBuy/getGiveupQualifyList',
    data:{page:$params('page',1),userid:$params('id')},
    success:function(data){
      $("#customerid").val($params('id'));
      $scope.groupbuy=data.pagerResult;
      $scope.page=data.pagerResult.page;
      $scope.$apply();
    }
  });

  $scope.pageToGroupBuy=function(){
   var customerid = $("#customerid").val();
    service({
      url:'/groupBuy/getGiveupQualifyList',
      data:{page:$scope.groupbuy.page,userid:customerid},
      success:function(data){
        $scope.groupbuy=data.pagerResult;
        $scope.page=data.pagerResult.page;
        $scope.$apply();
      }
    });
  };

  $scope.giveUp=function(qualificationcode){
    $("#qualificationcode").val(qualificationcode);
    $("#giveUpConfirmDialog").modal('show');
  };

  $scope.giveUpClick=function(){
    var customerid = $("#customerid").val();
    var qualificationcode = $("#qualificationcode").val();
    service({
      url:'/groupBuy/confirmGiveupQualify',
      data:{qualifyCode:qualificationcode},
      success:function(data){
        $("#giveUpConfirmDialog").modal('hide');
        service({
          url:'/groupBuy/getGiveupQualifyList',
          data:{page:$params('page',1),userid:customerid},
          success:function(data){
            $scope.groupbuy=data.pagerResult;
            $scope.page=data.pagerResult.page;
            $scope.$apply();
          }
        });
      }
    });
  };

 })

});



