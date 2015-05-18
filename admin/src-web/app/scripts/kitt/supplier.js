
define(['angular','kitt','jquery.validation','modal','CN'],function(angular, kitt){
  return angular.module('kitt.supplier', ['kitt'])
    .config(function($routeProvider){
    $routeProvider
      .when("/supplier",{
      templateUrl:'views/supplier/list.html',
      controller:'GroupBuyCtrl.supplier'
    })

      .when("/groupBuy/getGroupBuySupplyList",{
        templateUrl:'views/GroupBuy/GroupBuy.html',
        controller:'Groupbuy.List'
      })
      .when("/groupBuy/getGroupBuySupplyOver",{
        templateUrl:'views/GroupBuy/groupbuyOver.html',
        controller:'Groupbuy.ListOver'
      })

      .when("/addGroupBuy",{
        templateUrl:'views/GroupBuy/releaseGroupBuy.html',
        controller:'Groupbuy.Add'
      })

      .when("/viewGroupBuy",{
        templateUrl:'views/GroupBuy/detail.html',
        controller:'Groupbuy.Detail'
      })

      .when("/viewGroupBuyOver",{
        templateUrl:'views/GroupBuy/detailover.html',
        controller:'Groupbuy.DetailOver'
      })

      .when("/checkGroupBuy",{
        templateUrl:'views/GroupBuy/releaseCheck.html',
        controller:'Groupbuy.Check'
      })
  })

    .controller('GroupBuyCtrl.supplier',function($scope,$params) {
      check();

    $scope.content = $params('content', null);
    $scope.pageTo=function(){
      goto('/supplier', {page:$scope.supplier.page});
    };
    service({
      url:'/groupBuy/supplierslist',
      data:{page:$params('page',1)},
      success:function(data){
        $scope.supplier=data.supplier;
        $scope.page=data.supplier.page;
        $scope.$apply();
        window.initAuth();
      }
    });

      //添加供应商,浮层
      $scope.addSupplier=function(){
        $("#AddSupplierDialog").modal('show');
      }

      $scope.addSupplierClick=function(){
        if(!check().form()) {
          return;
        }else {
          service({
            url: '/groupBuy/addProviderInfo',
            data:  $scope.ProviderInfo,
            success: function (data) {
              if(data.message == "succeed") {
                $("#AddSupplierDialog").modal('hide');
                window.location.href = "/#/supplier";
                location.reload();
              }else{
                alert(data.message);
              }
            }
          });
        }
      };

      //删除供应商
      $scope.deleteSupplierClick=function(id){
         $('#providerinfoid').val(id);
        $("#deleteSupplierConfirmDialog").modal('show');
    };

      $scope.deleteSupplier=function(){
        var providerinfoid = $('#providerinfoid').val();
        service({
          url: '/groupBuy/deleteProviderInfo',
          data:  {providerinfoid:providerinfoid},
          success: function (data) {
            if(data.message == "succeed") {
              $("#deleteSupplierConfirmDialog").modal('hide');
              window.location.href = "/#/supplier";
              location.reload();
            }else{
              alert(data.message);
            }
          }
        });
      };

})

    //查看团购列表
    .controller('Groupbuy.List',function($scope,$params) {
      $scope.addGroupBuy=function(){
      //  window.location.href="/#/addGroupBuy?providerinfoid="+$params('id');
        var providerinfoid = $("#providerinfoid").val();
        window.location.href="/#/addGroupBuy?providerinfoid="+providerinfoid;
      };

      $scope.pageTo=function(){
        var providerinfoid = $("#providerinfoid").val();

       // goto('/groupBuy/getGroupBuySupplyList', {providerinfoid:$params('id'),page:$scope.groupbuy.page});
      //  goto('/groupBuy/getGroupBuySupplyList', {providerinfoid:providerinfoid,page:$scope.groupbuy.page});
        service({
          url:'/groupBuy/getGroupBuyInProcess',
          data:{page:$scope.groupbuy.page,providerinfoid:providerinfoid},
          success:function(data){
            $('#providerinfoid').val(providerinfoid);
            $scope.groupbuy=data.groupbuy;
            $scope.page=data.groupbuy.page;
            $scope.$apply();
          }
        });
      };
      service({
        url:'/groupBuy/getGroupBuyInProcess',
        data:{page:$params('page',1),providerinfoid:$params('id')},
        success:function(data){
          $('#providerinfoid').val($params('id'));
          $scope.groupbuy=data.groupbuy;
          $scope.page=data.groupbuy.page;
          $scope.$apply();
        }
      });

      //下架团购活动
      $scope.offShelfClick=function(id,providerinfoid){
        $('#providerinfoid').val(providerinfoid);
        $('#groupbuyid').val(id);
        $("#offShelfConfirmDialog").modal('show');
      };
      $scope.offShelf=function(){
        var providerinfoid = $("#providerinfoid").val();
        var groupbuysupplyid = $("#groupbuyid").val();
          service({
            url: '/groupBuy/cancelGroupBuySupply',
            data: {groupbuysupplyid:groupbuysupplyid},
            success: function (data) {
              if(data.message == "succeed") {
                $("#offShelfConfirmDialog").modal('hide');
                window.location.href = "/#/groupBuy/getGroupBuySupplyList?id=" + providerinfoid;
                location.reload();
              }else{
                alert(data.message);
              }
            }
          });
      };

      //已结束
      $scope.groupbuyOver=function(){
         var providerinfoid = $("#providerinfoid").val();
        window.location.href="/#/groupBuy/getGroupBuySupplyOver?providerinfoid="+providerinfoid;
      };

    })

    .controller('Groupbuy.ListOver',function($scope,$params) {
      $scope.addGroupBuy=function(){
        window.location.href="/#/addGroupBuy?providerinfoid="+$("#providerinfoid").val();
      };

      $scope.pageTo=function(){
       // goto('/groupBuy/getGroupBuySupplyOver', {providerinfoid:$("#providerinfoid").val(),page:$scope.groupbuy.page});
        var providerinfoid = $("#providerinfoid").val();
        service({
          url:'/groupBuy/getGroupBuyFinish',
          data:{page:$scope.groupbuy.page,providerinfoid:providerinfoid},
          success:function(data){
            $("#providerinfoid").val(providerinfoid);
            $scope.groupbuy=data.groupbuy;
            $scope.page=data.groupbuy.page;
            $scope.$apply();
          }
        });

      };
      service({
        url:'/groupBuy/getGroupBuyFinish',
        data:{page:$params('page',1),providerinfoid:$params('providerinfoid')},
        success:function(data){
          $("#providerinfoid").val($params('providerinfoid'));
          $scope.groupbuy=data.groupbuy;
          $scope.page=data.groupbuy.page;
          $scope.$apply();
        }
      });

      $scope.groupbuying=function(){
        var providerinfoid = $("#providerinfoid").val();
        window.location.href="/#/groupBuy/getGroupBuySupplyList?id="+providerinfoid;
      };

    })

    //添加团购
    .controller('Groupbuy.Add',function($scope,$params) {
      $("#providerinfoid").val($params('providerinfoid'));
      //获取下啦列表数据
        service({
          url:'/supplyInfo',
          success:function(data){
            //  $scope.deliveryproviences = data.deliveryproviences;
              $scope.deliveryregions = data.deliveryregions;
              $scope.deliveryplaces = data.deliveryplaces;
            $scope.inspectorgs = data.inspectorgs;
            $scope.$apply();
          }
        });

    /*  service({
        url:'/groupBuy/getGroupBuyForCheck',
        data: {groupbuysupplycode:$params('groupbuysupplycode')},
        success:function(data){
          $scope.GroupBuySupply = data.groupbuy;
          $scope.$apply();
        }
      });*/

      //初始化
      initDate();
     // initInput();
      regionChange();
      provinceChange();
      harbourChange();
      orgChange();

      //取消发布团购
      $scope.cancelGroupBuy=function(){
        var providerinfoid = $("#providerinfoid").val();
        goto("/groupBuy/getGroupBuySupplyList?id="+providerinfoid);
      };

      //上传团购图片
      $scope.uploadPic=function(){
        uploadGroupBuyPic();
      };

      //更换图片
      $scope.changePic=function(){
        $("#changeClick").css("display","none");
        $("#form-groupbuyPic").css("display","block");
        $("#uploadClick").css("display","block");
      };


      //发布团购
      $scope.confirmGroupBuy=function(){
        if(!checkGroupBuy().form()) {
          return;
        }

        if($("#deliverydatestart").val() == "" ||$("#deliverydateend").val()== ""){
          $("#errorInfo").html("请填写交货时间").css("color","red");
          return;
        } else if(dateCompare($("#deliverydatestart").val(),($("#deliverydateend").val()))==false){
          $("#errorInfo").html("结束时间不能大于开始时间").css("color","red");
          return;
        } else{
          $("#errorInfo").html("");
        }


        if($("#groupbuybegindate").val() == "" ||$("#groupbuyenddate").val()== ""){
          $("#groupbuyErrorInfo").html("请填写团购时间").css("color","red");
          return;
        } else if(dateCompare($("#groupbuybegindate").val(),($("#groupbuyenddate").val()))==false){

          $("#groupbuyErrorInfo").html("结束时间不能大于开始时间").css("color","red");
          return;
        } else{
          $("#groupbuyErrorInfo").html("");
        }

        if(dateCompare($("#groupbuyenddate").val(),($("#deliverydatestart").val()))==false){
          $("#groupbuyErrorInfo").html("团购结束时间不能大于提货开始时间").css("color","red");
          return;
        }else{
          $("#groupbuyErrorInfo").html("");
        }

        if($("#port").val()=="其它" && $("#deliveryplace").val().length==0){
          $("#otherplaceError").text("请输入交货地点").css("color","red");
          $("#deliveryplace").blur(function(){
            $("#otherplaceError").text("");
          });
          return;
        }

        if($("#inspectionagency").val()=="其它" && $("#otherorg").val().length==0) {
          $("#otherorgError").text("请输入检验机构").css("color", "red");
          $("#otherorg").blur(function () {
            $("#otherorgError").text("");
          });
          return;
        }

        if(eval($("#minimumamount").val())>eval($("#supplyamount").val())){
          $("#minError").text("起订量不能大于供应量").css("color", "red");
          return;
        }else{
          $("#minError").text("");
        }

        if($("#photopath").val() == null || $("#photopath").val() == ""){
          $("#picFilePathInfo").text("请选择图片上传").css("color", "red");
          return;
        }else{
          $("#picFilePathInfo").text("")
        }

        // $scope.GroupBuySupply
        var providerinfoid = $("#providerinfoid").val();
          service({
            url: '/groupBuy/releaseGroupBuySupply',
            data:  $("#groupbuy_form").serialize(),
            success: function (data) {
              window.location.href="/#/groupBuy/getGroupBuySupplyList?id="+providerinfoid;
               location.reload();

             /* var groupbuysupplycode = data.groupbuysupplycode;
              window.location.href="/#/checkGroupBuy?groupbuysupplycode="+groupbuysupplycode+"&providerinfoid="+providerinfoid;*/
            }
          });
      };

    })


    //查看团购详细-进行中
    .controller('Groupbuy.Detail',function($scope,$params) {

      service({
        url: '/groupBuy/getGroupBuySupplyDetail',
        data: {groupbuyid: $params('id'),page:$params('page',1)},
        success: function (data) {
          $("#providerinfoid").val($params('providerinfoid'));
          $("#groupbuyid").val($params('id'));
          $scope.groupbuy = data.groupbuy;
          $scope.groupbuyorder = data.groupbuyorder;
          $scope.totalamount = data.totalamount;
          $scope.surplusamount = data.surplusamount;
          $scope.$apply();
        }, error: function () {
        }
      });

      $scope.back=function(){
        var providerinfoid = $("#providerinfoid").val();
        window.location.href="/#/groupBuy/getGroupBuySupplyList?id="+providerinfoid;
      };

      //下载图片
      $scope.downloadPic=function(picPath){
        window.location.href='/certificate/downloadCertification?url=' + picPath;
      };

      //团购订单翻页
      $scope.pageTo=function(){
        //var providerinfoid = $("#providerinfoid").val();
        //goto('/viewGroupBuy', {groupbuyid:$params('id'),providerinfoid:providerinfoid,page:$scope.groupbuyorder.page});

        service({
          url: '/groupBuy/getGroupBuySupplyDetail',
          data: {groupbuyid: $params('id'),page:$scope.groupbuyorder.page},
          success: function (data) {
            $("#providerinfoid").val($params('providerinfoid'));
            $scope.groupbuy = data.groupbuy;
            $scope.groupbuyorder = data.groupbuyorder;
            $scope.totalamount = data.totalamount;
            $scope.surplusamount = data.surplusamount;
            $scope.$apply();
          }, error: function () {
          }
        });
       /*  ******/
      };
    })


    //查看团购详细-已结束
    .controller('Groupbuy.DetailOver',function($scope,$params) {

      service({
        url: '/groupBuy/getGroupBuySupplyDetail',
        data: {groupbuyid: $params('id'),page:$params('page',1)},
        success: function (data) {
          $("#providerinfoid").val($params('providerinfoid'));
          $("#groupbuyid").val($params('id'));
          $scope.groupbuy = data.groupbuy;
          $scope.groupbuyorder = data.groupbuyorder;
          $scope.totalamount = data.totalamount;
          $scope.surplusamount = data.surplusamount;
          $scope.$apply();
        }, error: function () {
        }
      });

      $scope.back=function(){
        var groupbuyid = $("#groupbuyid").val();
        var providerinfoid = $("#providerinfoid").val();
        window.location.href="/#/groupBuy/getGroupBuySupplyOver?id="+groupbuyid+"&providerinfoid="+providerinfoid;
      };

      //下载图片
      $scope.downloadPic=function(picPath){
        window.location.href='/certificate/downloadCertification?url=' + picPath;
      };

      $scope.canUse=function(groupbuyordercode,qualificationcode){

        var providerinfoid = $("#providerinfoid").val();
        var groupbuyid  = $("#groupbuyid").val();
        service({
          url: '/groupBuy/resetQualifyStatus',
          data: {isOk:"true",ordercode: groupbuyordercode,qualificationcode:qualificationcode},
          success: function (data) {
            location.reload();
            alert("操作成功");
          }, error: function () {
          }
        });
      };

      $scope.cannotUse=function(groupbuyordercode,qualificationcode){
        var providerinfoid = $("#providerinfoid").val();
        var groupbuyid  = $("#groupbuyid").val();
        service({
          url: '/groupBuy/resetQualifyStatus',
          data: {isOk:"false",ordercode: groupbuyordercode,qualificationcode:qualificationcode},
          success: function (data) {
            location.reload();
            alert("操作成功");
          }, error: function () {
          }
        });
      };

      //团购订单翻页
      $scope.pageTo=function(){
        //var providerinfoid = $("#providerinfoid").val();
        //goto('/viewGroupBuy', {groupbuyid:$params('id'),providerinfoid:providerinfoid,page:$scope.groupbuyorder.page});
        //重发ajax请求获取数据并匹配
        service({
          url: '/groupBuy/getGroupBuySupplyDetail',
          data: {groupbuyid: $params('id'),page:$scope.groupbuyorder.page},
          success: function (data) {
            $("#providerinfoid").val($params('providerinfoid'));
            $scope.groupbuy = data.groupbuy;
            $scope.groupbuyorder = data.groupbuyorder;
            $scope.totalamount = data.totalamount;
            $scope.surplusamount = data.surplusamount;
            $scope.$apply();
          }, error: function () {
          }
        });
         /* ***** */
      };
    })


    //检查发布的团购信息
    .controller('Groupbuy.Check',function($scope,$params) {

      service({
        url: '/groupBuy/getGroupBuyForCheck',
        data: { groupbuysupplycode: $params('groupbuysupplycode')},
        success: function (data) {
          $scope.groupbuy = data.groupbuy;
          $scope.$apply();
        }
      });
      $scope.confirmRelease = function () {
        //alert("发布成功");
        window.location.href = "/#/groupBuy/getGroupBuySupplyList?id=" + $params('providerinfoid');
      };

      $scope.change = function () {
        window.location.href = "/#/addGroupBuy?groupbuysupplycode=" + $params('groupbuysupplycode');
      };

    })

//对发布的团购信息进行校验
  function checkGroupBuy(){
    jQuery.validator.addMethod("floatCheck", function(value, element) {
      return this.optional(element) || /^(\d+(\.\d)?)$/.test(value);
    }, "只能输入数值");
    jQuery.validator.addMethod("numberCheck", function(value, element) {
      var firstChar = value.substr(0,1);
      return this.optional(element) || firstChar != 0;
    }, "请输入合法数字");
    jQuery.validator.addMethod("multiple", function(value, element) {
      return this.optional(element) || (value % 50) == 0;
    }, " 输入的瓶数必须是50的倍数!");
    jQuery.validator.addMethod("hundredCheck", function(value, element) {
      return this.optional(element) || (value % 100) == 0;
    }, " 输入的数字必须是100的倍数!");
    return  $("#groupbuy_form").validate({
      onsubmit:false,
      onkeyup:false,
      focusInvalid:false,
      errorElement: 'span',
      errorClass: 'help-block',
      rules: {
        coaltype: {
          required: true
        },
        NCV: {
          required: true,
          digits:true,
          numberCheck:true,
          hundredCheck:true,
          range:[3000,7000]
        },
        ADS: {
          range:[0.1,10],
          floatCheck:true
        },
        RS: {
          required: true,
          floatCheck:true,
          range:[0.1,10]
        },
        TM: {
          required: true,
          floatCheck:true,
          range:[0.1,50]
        },
        IM: {
          floatCheck:true,
          range:[0.1,50]
        },
        ADV: {
          required: true,
          numberCheck:true,
          floatCheck:true,
          range:[0.1,50]
        },
        RV: {
          numberCheck:true,
          floatCheck:true,
          range:[0.1,50]
        },
        AFT: {
          numberCheck:true,
          range:[900,1600],
          multiple:true
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
        deliverydistrict: {
          required: true
        },
        deliverydatestart: {
          required: true
        },
        deliverydateend: {
          required: true
        },
        supplyamount: {
          required: true,
          digits:true,
          maxlength:6,
          numberCheck:true
        },

        storageplace: {
          required: true
        },

        groupbuyprice: {
          required: true,
          digits:true,
          maxlength:6,
          numberCheck:true
        },

        groupbuybegindate: {
          required: true
        },

        groupbuyenddate: {
          required: true
        },

        marketprice: {
          required: true,
          digits:true,
          maxlength:6,
          numberCheck:true
        },

        minimumamount: {
          required: true,
          digits:true,
          maxlength:6,
          numberCheck:true
        },
        comment: {
          required: true
        }

      },


      messages: {
        coaltype:{
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
        deliverydistrict: {
          required: "请选择交货地点"
        },
        deliverytime1:{
          required: "请输入交货时间"
        },
        deliverytime2:{
          required: "请输入交货时间"
        },
        deliverytime4:{
          required: "请输入团购时间"
        },
        deliverytime5:{
          required: "请输入团购时间"
        },
        supplyamount:{
          required: "请输入供货数量",
          digits:"请输入整数",
          maxlength:"供货数量只能输入6位数"
        },

        storageplace:{
          required: "请输入堆位"
        },
        groupbuyprice:{
          required: "请输入团购价",
          digits:"请输入整数",
          maxlength:"团购价只能输入6位数"
        },
        groupbuyprice:{
          required: "请输入团购价",
          digits:"请输入整数",
          maxlength:"团购价只能输入6位数"
        },
        marketprice:{
          required: "请输入市场价",
          digits:"请输入整数",
          maxlength:"市场价只能输入6位数"
        },
        minimumamount:{
          required: "请输入起订量",
          digits:"请输入整数",
          maxlength:"起订量只能输入6位数"
        },
        comment:{
          required: "请输入团购规则"
        }


       /* org:{
          required:"请输入检验机构"
        }*/
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


//检查发布供应商
  function check(){
    jQuery.validator.addMethod("isMobile", function(value, element) {

      var length = value.length;
      var mobile = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
      var telephonePattern = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
      return this.optional(element) || (telephonePattern.test(value) || mobile.test(value));
    }, "请正确输入联系电话!");

    return  $("#supplier_form").validate({
      onsubmit:false,
      onkeyup:false,
      focusInvalid:false,
      errorElement: 'span',
      errorClass: 'help-block',
      rules: {
        providername: {
          required: true
        },
        contacter: {
          required: true
        },
        enterpriseaddress: {
          required: true
        },
        registeredcapital: {
          required: true,
          digits:true
        },
        enterpriseproperty: {
          required: true
        },
        contactphone: {
          required: true,
          isMobile:true
        }
      },

      messages: {
        providername: {
          required: "请输入企业名称!"
        },
        contacter:{
          required: "请输入联系人!"
        },
        enterpriseaddress:{
          required: "请输入企业地址!"
        },
        registeredcapital:{
          required: "请输入注册资本!",
          digits: $.validator.format("请输入整数!")
        },
        enterpriseproperty:{
          required: $.validator.format("请输入企业性质!")
        },
        contactphone:{
          required: "请输入联系电话!"
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

    $('#datetimepicker4').datetimepicker({
      todayBtn: true,
      language:'zh-cn',
      format:'YYYY-MM-DD HH:mm'
    });
    $('#datetimepicker5').datetimepicker({
      todayBtn: true,
      language:'zh-cn',
      format:'YYYY-MM-DD HH:mm'
    });

    $('#datetimepicker1').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker2').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker4').data("DateTimePicker").setMinDate(new Date());
    $('#datetimepicker5').data("DateTimePicker").setMinDate(new Date());
  }

  function regionChange(){
    //根据区域查询省份
    $("#deliverydistrict").change(function(){
    //  $("#wrongInfo").text("");
      if($("#deliverydistrict").val() == "" || $("#deliverydistrict").val() == null ){
        $("#deliveryprovince").prepend("<option selected value='0'>--请选择--</option>");
        $("#port").prepend("<option selected value='0'>--请选择--</option>");
      //  $("#wrongInfo").text("请选择交货地点").css("color","red");
      //  return;
      } else{
        service({
          url:'/getProviences',
          data:{name:$("#deliverydistrict").val()},
          success:function(data){
            $("#deliveryprovince").empty();
            var jsonStr="";
            $.each(data, function (i, item) {
              jsonStr += "<option value="+item.name+">" + item.name + "</option>";
            });
            $("#deliveryprovince").html(jsonStr);
            $.ajax({
              url:'/getBuyPorts',
              data:{name:data[0].name},
              success:function(data){
                $("#port").empty();
                var jsonStr="";
                $.each(data.deliveryplaces, function (i, item) {
                  if( i != data.length - 1){
                    jsonStr += '<option value='+item.name+'>' + item.name + '</option>';
                  }
                });
                $("#port").html(jsonStr);
              }
            });
          },
          error:function(){
          }
        });
      }
    });
  }

  function provinceChange(){
    //根据省份查询港口
    $('#deliveryprovince').change(function(){
     // $("#wrongInfo").text("");
      service({
        url:'/getBuyPorts',
        data:{name:$('#deliveryprovince').val()},
        success:function(data){
          $("#port").empty();
          var jsonStr="";
          $.each(data.deliveryplaces, function (i, item) {
            if( i != data.length - 1){
              jsonStr += '<option value='+item.name+'>' + item.name + '</option>';
            }
          });
          if(data.deliveryplaces[0].name=="其它"){
            $("#otherplaceDisplay").slideDown();
          } else{
            $("#otherplaceDisplay").slideUp();
          }
          $("#port").html(jsonStr);
        },error:function(){
        }
      });
    });
  }

  function harbourChange(){
    $('#port').change(function(){
      if($("#port").val()=="其它"){
       // $("#wrongInfo").text("");
        $("#otherplaceDisplay").slideDown();
      } else{
        $("#otherplaceDisplay").slideUp();
      }
    });
  }

  function orgChange(){
    $('#inspectionagency').change(function(){
      if($("#inspectionagency").val()=="其它"){
        $("#otherorgDisplay").slideDown();

      } else{
        $("#otherorgDisplay").slideUp();
      }
    });
  }

  //日期比较
  function dateCompare(startdate,enddate){
    var startTime = new Date(startdate).getTime();
    var endTime = new Date(enddate).getTime();
    if(startTime >= endTime) {
      return false;
    }
    else{
      return true;
    }
  }
 /* function dateCompare(startdate,enddate){
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
  }*/

  function uploadGroupBuyPic(){
    var picFilePath = $("#picFilePath").val();
    var mime = picFilePath.toLowerCase().substr(picFilePath.lastIndexOf("."));
    if(picFilePath == ""){
      $("#picFilePathInfo").text("请选择团购图片!").css("color","red");
      return ;
    }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#picFilePathInfo").text("请选择jpg,bmp,png,jpeg格式的照片上传!").css("color","red");
      return ;
    }else{
      $("#picFilePathInfo").text("");
    }
    $('#form-groupbuyPic').ajaxSubmit({success:function(data){
      $("#picSavePath").attr("width",200);
      //$("#picSavePath").attr("height",100);
      $("#picSavePath").attr("src",data);
      $("#photopath").val(data);
      $("#photopathVal").val(data);
      $("#form-groupbuyPic").css("display","none");
      $("#uploadClick").css("display","none");
      $("#changeClick").css("display","block");
    }
    });

  }

});

