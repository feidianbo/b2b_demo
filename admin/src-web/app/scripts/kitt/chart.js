define(['angular','kitt','modal'],function(angular, kitt){
  return angular.module('kitt.chart', ['kitt']).config(function($routeProvider){
    $routeProvider
      .when('/chart/BSPI',{
        templateUrl:'views/chart/list.html',
        controller:'chart.BSPI'
      })
      .when('/chart/API8',{
        templateUrl:'views/chart/list.html',
        controller:'chart.API8'
      })
      .when('/chart/TC1505',{
        templateUrl:'views/chart/list.html',
        controller:'chart.TC1505'
      })

    .when('/indexpic',{
      templateUrl:'views/chart/indexPic.html',
      controller:'IndexPic'
    });
  })
    .controller('chart.BSPI',function($scope,$params) {
      initDate();
      service({
        url:'/chart/BSPIList',
        data:{page:$params('page',1)},
        success:function(data){
          $("#theme").text("-BSPI");
          $scope.BSPI=data.BSPI;
          $scope.page=data.BSPI.page;
          $scope.$apply();
        }
      });

      $scope.pageToBSPI=function(){
        goto('/chart/BSPI', {page:$scope.BSPI.page});
      };

      $scope.addChart=function(){
        $("#AddChartDialog").modal('show');
      };

      $scope.addChartClick=function(){
        $("#type").val("BSPI");
        if(checkForm()){
          // 验证交易日期唯一性
          var tradedate = $("#tradedate").val();
          service({
            url: '/chart/checkTradedateSole',
            data: {tradedate:tradedate,type:'BSPI'},
            success: function (data) {
              if(data == 1){
                $("#tradedateError").text("日期已存在").css("color","red");
                return;
              }else{
                $("#tradedateError").text("");
                //通过后执行保存
                service({
                  url: '/chart/saveChart',
                  // data:$scope.Chart,参数传不了
                  data: $("#chart_form").serialize(),
                  success: function (data) {
                    $("#AddChartDialog").modal('hide');
                    location.reload();
                  }
                });

              }
            }
          });

        }
      };

      $scope.deleteOne=function(id){
        $("#chartid").val(id);
        $("#deleteOneConfirmDialog").modal('show');
      };

      $scope.deleteOneClick=function(){
        var id = $("#chartid").val();
        service({
          url:'/chart/deleteOne',
          data:{id:id},
          success:function(data){
            $("#deleteOneConfirmDialog").modal('hide');
            location.reload();
          }
        });
      };

      $scope.modifyChartConfine=function(){
        $("#modifyLimitDialog").modal('show');
        service({
          url:'/chart/getChartConfine',
          data:{type:'BSPI'},
          success:function(data){
            $("#highlimit").val(data.chartconfine.highlimit);
            $("#lowlimit").val(data.chartconfine.lowlimit);
          }
        });

      };

      $scope.modifyLimitClick=function(){
        $("#limitType").val("BSPI");
        if(checkLimitForm()) {
          service({
            url: '/chart/modifyChartConfine',
            data: $("#chartConfine_form").serialize(),
            success: function (data) {
              $("#modifyLimitDialog").modal('hide');
            }
          });
        }
      };

      $scope.batchAdd=function(){
        $("#type_0").val("BSPI");
        $("#batchAddDialog").modal('show');
        resetTrNum("chart_tbody");
      };

      $scope.addRow=function(){
        var type = $("#type_0").val();
        var content = "";
        //var row = $("#batch_add_template").clone();
        content += "<tr>";
        content += "<input type=\"hidden\" id=\"type_#index#\" name=\"chartList[#index#].type\" value=\"BSPI\">";
        content += "<td style=\"padding-left: 60px;padding-top: 10px;\">";
        content += "<span class=\"input-group date\" id=\"datetimepicker_#index#\">";
        content += "<input class=\"form-control\" type=\"text\" data-date-format=\"YYYY-MM-DD\" readonly=\"readonly\" id=\"tradedate_#index#\" name=\"chartList[#index#].tradedate\">";
        content += "<span class=\"input-group-addon\">";
        content += "<i class=\"glyphicon glyphicon-calendar\"></i>";
        content += "</span></span>";
        content += "</td>";
        content += "<td style=\"padding-left: 30px;padding-top: 10px;\"><input style=\"width:200px;\" class=\"form-control\" maxlength=\"7\" style=\"width:180px;\" type=\"text\" id=\"averageprice_#index#\" name=\"chartList[#index#].averageprice\"></td>";
        content += "<td><span id=\"error_#index#\" style=\"margin-left:20px;line-height: 40px;\"></span></td>";
        content += "</tr>";
        $("#chart_tbody").append(content);
      /*  if($("#chart_tbody tr").length >7){
        }*/
        resetTrNum("chart_tbody");
      };

      $scope.batchAddClick=function(){
        if(checkBatchForm("chart_tbody")){
          service({
            url:'/chart/batchAdd',
            data:$("#batch_form").serialize(),
            success:function(data){
              $("#batchAddDialog").modal('hide');
              location.reload();
            }
          });
        }

      };

      //去掉行
      $scope.cutRow=function(){
        $("#chart_tbody tr:last").remove();
      };

    })



    .controller('chart.API8',function($scope,$params) {
      initDate();
      service({
        url:'/chart/API8List',
        data:{page:$params('page',1)},
        success:function(data){
          $("#theme").text("-API8");
          $scope.API8=data.API8;
          $scope.page=data.API8.page;
          $scope.$apply();
        }
      });

      $scope.pageToAPI8=function(){
        goto('/chart/API8', {page:$scope.API8.page});
      };

      $scope.addChart=function(){
        $("#AddChartDialog").modal('show');
      };

      $scope.addChartClick=function(){
        $("#type").val("API8");
        if(checkForm()) {
          // 验证交易日期唯一性
          var tradedate = $("#tradedate").val();
          service({
            url: '/chart/checkTradedateSole',
            data: {tradedate:tradedate,type:'API8'},
            success: function (data) {
              if(data == 1){
                $("#tradedateError").text("日期已存在").css("color","red");
                return;
              }else{
                $("#tradedateError").text("");
                //通过后执行保存
                service({
                  url: '/chart/saveChart',
                  data: $("#chart_form").serialize(),
                  success: function (data) {
                    $("#AddChartDialog").modal('hide');
                    location.reload();
                  }
                });

              }
            }
          });
        }
      };

      $scope.deleteOne=function(id){
        $("#chartid").val(id);
        $("#deleteOneConfirmDialog").modal('show');
      };

      $scope.deleteOneClick=function(){
        var id = $("#chartid").val();
        service({
          url:'/chart/deleteOne',
          data:{id:id},
          success:function(data){
            $("#deleteOneConfirmDialog").modal('hide');
            location.reload();
          }
        });
      };

      $scope.modifyChartConfine=function(){
        $("#modifyLimitDialog").modal('show');
        service({
          url:'/chart/getChartConfine',
          data:{type:'API8'},
          success:function(data){
            $("#highlimit").val(data.chartconfine.highlimit);
            $("#lowlimit").val(data.chartconfine.lowlimit);
          }
        });

      };

      $scope.modifyLimitClick=function(){
        $("#limitType").val("API8");
        if(checkLimitForm()){
          service({
            url: '/chart/modifyChartConfine',
            data: $("#chartConfine_form").serialize(),
            success: function (data) {
              $("#modifyLimitDialog").modal('hide');
            }
          });
        }
      };

      $scope.batchAdd=function(){
        $("#type_0").val("API8");
        $("#batchAddDialog").modal('show');
        resetTrNum("chart_tbody");
      };

      $scope.addRow=function(){
        var type = $("#type_0").val();
        var content = "";
        content += "<tr>";
        content += "<input type=\"hidden\" id=\"type_#index#\" name=\"chartList[#index#].type\" value=\"API8\">";
        content += "<td style=\"padding-left: 60px;padding-top: 10px;\">";
        content += "<span class=\"input-group date\" id=\"datetimepicker_#index#\">";
        content += "<input class=\"form-control\" type=\"text\" data-date-format=\"YYYY-MM-DD\" readonly=\"readonly\" id=\"tradedate_#index#\" name=\"chartList[#index#].tradedate\">";
        content += "<span class=\"input-group-addon\">";
        content += "<i class=\"glyphicon glyphicon-calendar\"></i>";
        content += "</span></span>";
        content += "</td>";
        content += "<td style=\"padding-left: 30px;padding-top: 10px;\"><input style=\"width:200px;\" class=\"form-control\" maxlength=\"7\" style=\"width:180px;\" type=\"text\" id=\"averageprice_#index#\" name=\"chartList[#index#].averageprice\"></td>";
        content += "<td><span id=\"error_#index#\" style=\"margin-left:20px; line-height: 40px;\"></span></td>";
        content += "</tr>";
        $("#chart_tbody").append(content);
        resetTrNum("chart_tbody");
      };

      $scope.batchAddClick=function(){
        if(checkBatchForm("chart_tbody")){
          service({
            url:'/chart/batchAdd',
            data:$("#batch_form").serialize(),
            success:function(data){
              $("#batchAddDialog").modal('hide');
              location.reload();
            }
          });
        }
      };

      //去掉行
      $scope.cutRow=function(){
        $("#chart_tbody tr:last").remove();
      };


    })

    .controller('chart.TC1505',function($scope,$params) {
      initDate();
      service({
        url:'/chart/TC1505List',
        data:{page:$params('page',1)},
        success:function(data){
          $("#theme").text("-TC1505");
          $scope.TC1505=data.TC1505;
          $scope.page=data.TC1505.page;
          $scope.$apply();
        }
      });

      $scope.pageToTC1505=function(){
        goto('/chart/TC1505', {page:$scope.TC1505.page});
      };

      $scope.addChart=function(){
        $("#AddChartDialog").modal('show');
      };

      $scope.addChartClick=function(){
        $("#type").val("TC1505");
        if(checkForm()) {
          // 验证交易日期唯一性
          var tradedate = $("#tradedate").val();
          service({
            url: '/chart/checkTradedateSole',
            data: {tradedate:tradedate,type:'TC1505'},
            success: function (data) {
              if(data == 1){
                $("#tradedateError").text("日期已存在").css("color","red");
                return;
              }else{
                $("#tradedateError").text("");
                //通过后执行保存
                service({
                  url: '/chart/saveChart',
                  data: $("#chart_form").serialize(),
                  success: function (data) {
                    $("#AddChartDialog").modal('hide');
                    location.reload();
                  }
                });

              }
            }
          });
        }
      };

      $scope.deleteOne=function(id){
        $("#chartid").val(id);
        $("#deleteOneConfirmDialog").modal('show');
      };

      $scope.deleteOneClick=function(){
        var id = $("#chartid").val();
        service({
          url:'/chart/deleteOne',
          data:{id:id},
          success:function(data){
            $("#deleteOneConfirmDialog").modal('hide');
            location.reload();
          }
        });
      };

      $scope.modifyChartConfine=function(){
        $("#modifyLimitDialog").modal('show');
        service({
          url:'/chart/getChartConfine',
          data:{type:'TC1505'},
          success:function(data){
            $("#highlimit").val(data.chartconfine.highlimit);
            $("#lowlimit").val(data.chartconfine.lowlimit);
          }
        });

      };

      $scope.modifyLimitClick=function(){
        $("#limitType").val("TC1505");
        if(checkLimitForm()) {
          service({
            url: '/chart/modifyChartConfine',
            data: $("#chartConfine_form").serialize(),
            success: function (data) {
              $("#modifyLimitDialog").modal('hide');
            }
          });
        }
      };

      $scope.batchAdd=function(){
        $("#type_0").val("TC1505");
        $("#batchAddDialog").modal('show');
        resetTrNum("chart_tbody");
      };

      $scope.addRow=function(){
        var type = $("#type_0").val();
        var content = "";
        content += "<tr>";
        content += "<input type=\"hidden\" id=\"type_#index#\" name=\"chartList[#index#].type\" value=\"TC1505\">";
        content += "<td style=\"padding-left: 60px;padding-top: 10px;\">";
        content += "<span class=\"input-group date\" id=\"datetimepicker_#index#\">";
        content += "<input class=\"form-control\" type=\"text\" data-date-format=\"YYYY-MM-DD\" readonly=\"readonly\" id=\"tradedate_#index#\" name=\"chartList[#index#].tradedate\">";
        content += "<span class=\"input-group-addon\">";
        content += "<i class=\"glyphicon glyphicon-calendar\"></i>";
        content += "</span></span>";
        content += "</td>";
        content += "<td style=\"padding-left: 30px;padding-top: 10px;\"><input style=\"width:200px;\" class=\"form-control\" maxlength=\"7\" style=\"width:180px;\" type=\"text\" id=\"averageprice_#index#\" name=\"chartList[#index#].averageprice\"></td>";
        content += "<td><span id=\"error_#index#\" style=\"margin-left:20px;line-height: 40px;\"></span></td>";
        content += "</tr>";
        $("#chart_tbody").append(content);
        resetTrNum("chart_tbody");
      };

      $scope.batchAddClick=function(){
        if(checkBatchForm("chart_tbody")){
          service({
            url:'/chart/batchAdd',
            data:$("#batch_form").serialize(),
            success:function(data){
              $("#batchAddDialog").modal('hide');
              location.reload();
            }
          });
        }
      };

      //去掉行
      $scope.cutRow=function(){
        $("#chart_tbody tr:last").remove();
      };

    })

    //更换首页图片
  .controller('IndexPic',function($scope,$params) {

      //读取数据,给页面赋值和展示图片
      service({
        url:'/chart/getAllIndexBanners',
        success:function(data){
          var bannerList = data.bannerList;
          if(bannerList != null) {
            for (var i = 0; i < bannerList.length; i++) {
              var bannerName = bannerList[i].name;
              var bannerPath = bannerList[i].path;
              var bannerSequence = bannerList[i].sequence;
              var bannerLimitNum = bannerList[0].limitnum;
              $("#limitnum-input").val(bannerLimitNum);
              if (bannerName == "banner1") {
                //页面上赋值
                $("#picSavePath_1").attr("src",bannerPath);
                $("#sequence-input_1").val(bannerSequence);
                //隐藏域赋值
                $("#path-banner_1").val(bannerPath);
              }

              if (bannerName == "banner2") {
                //页面上赋值
                $("#picSavePath_2").attr("src",bannerPath);
                $("#sequence-input_2").val(bannerSequence);
                //隐藏域赋值
                $("#path-banner_2").val(bannerPath);

              }

              if (bannerName == "banner3") {
                //页面上赋值
                $("#picSavePath_3").attr("src",bannerPath);
                $("#sequence-input_3").val(bannerSequence);
                //隐藏域赋值
                $("#path-banner_3").val(bannerPath);
              }

              if (bannerName == "banner4") {
                //页面上赋值
                $("#picSavePath_4").attr("src",bannerPath);
                $("#sequence-input_4").val(bannerSequence);
                //隐藏域赋值
                $("#path-banner_4").val(bannerPath);
              }

              if (bannerName == "banner5") {
                //页面上赋值
                $("#picSavePath_5").attr("src",bannerPath);
                $("#sequence-input_5").val(bannerSequence);
                //隐藏域赋值
                $("#path-banner_5").val(bannerPath);
              }

            }
          }

        }
      });

      $scope.changePic=function(i){
        $("#changeAndSequence_"+i).hide();
        $("#form-banner_"+i).show();
      };

      $scope.uploadPic=function(i){
        uploadIndexPic(i);
      };

      $scope.saveIndexPic=function(){
        //判断五张图片是否已上传
        for(var i=1;i<6;i++) {
          var bannerPath = $("#path-banner_" + i).val();
          if(bannerPath != null && bannerPath != ""){
            $("#sequenceError_"+i).text("")
          }else{
            $("#sequenceError_"+i).text("请上传banner"+i+"图片!").css("color","red");
            return;
          }
        }

        //判断顺序是否为空,是否为正整数,满足条件放入数组内
        var sequenceArr = [];
        var regular =/^[1-9]\d*$/;
        for(var i=1;i<6;i++){
          var sequence = $("#sequence-input_"+i).val();
          if(sequence != null && sequence != ""){
            $("#sequenceError_"+i).text("");

            if(regular.test(sequence)){
              sequenceArr[i] = sequence;
              $("#sequenceError_"+i).text("");
            }else{
              $("#sequenceError_"+i).text("请输入正整数").css("color","red");
              return;
            }
          }else{
            $("#sequenceError_"+i).text("顺序不能为空").css("color","red");
            return;
          }
        }

        //判断数组内是否有重复的元素
        var isRepeat = false;
        var sortSequenceArr = sequenceArr.sort();
        for(var j=0;j<sortSequenceArr.length;j++){
          if (sortSequenceArr[j] == sortSequenceArr[j+1]){
           // alert("数组重复内容："+sortSequenceArr[j]);
            if(sortSequenceArr[j] != undefined) {
              isRepeat = true;
              break;
            }
          }
        }

        //如果有重复的,给出提示
        if(isRepeat){
          $("#sequenceAndLimitnumError").text("顺序有重复,请检查!").css("color","red");
          return;
        }else{
          $("#sequenceAndLimitnumError").text("");
          //全部正常,将值赋给隐藏域的表单
          for(var i=1;i<6;i++) {
            $("#sequence_"+i).val($("#sequence-input_" + i).val());
          }
        }

        //检查显示数量是否为空,是否为正整数
        var limitnum = $("#limitnum-input").val();
        if(limitnum != null && limitnum != ""){
          if(regular.test(limitnum)){
            $("#sequenceAndLimitnumError").text("");
            //符合条件.将值付给隐藏域表单
            for(var i=1;i<6;i++) {
              $("#limitnum_"+i).val($("#limitnum-input").val());
            }
          }else{
            $("#sequenceAndLimitnumError").text("显示数量为正整数").css("color","red");
            return;
          }
        }else{
          $("#sequenceAndLimitnumError").text("显示数量不能为空").css("color","red");
          return;
        }

        //全部条件满足后,提交隐藏域表单
        service({
          url:'/chart/saveIndexPic',
          data:$("#form-picPath").serialize(),
          success:function(data){
            location.reload();
          }
        });

      };

  });

  function initDate(){
    $('#datetimepicker').datetimepicker({
      pickTime: false,
      todayBtn: true,
      language:'zh-cn'
    });

  }

  function checkForm(){
    var pass = true;
    var tradedate = $("#tradedate").val();
    var averageprice = $("#averageprice").val();
    if(tradedate == null || tradedate == ""){
      $("#tradedateError").text("日期不能为空").css("color","red");
      pass = false;
    }else{
      $("#tradedateError").text("");
    }

    if(averageprice == null || averageprice == ""){
      $("#averagepriceError").text("价格不能为空").css("color","red");
      pass = false;
    }else{
      $("#averagepriceError").text("");
    }

    var regular = /^(\d{1,4}(\.\d{1,2})?)$/;
    if(!regular.test(averageprice)){
      $("#averagepriceError").text("请正确输入价格").css("color","red");
      pass = false;
    }else{
      $("#averagepriceError").text("");
    }

    return pass;

  }

  function checkLimitForm(){
    var pass = true;
    var regular = /^(\d{1,4}(\.\d)?)$/;
    var highlimit = $("#highlimit").val();
    var lowlimit = $("#lowlimit").val();

    if(highlimit == null || highlimit == ""){
      $("#highlimitError").text("请输入上限").css("color","red");
      pass = false;
    }else{
      $("#highlimitError").text("")
    }


    if(!regular.test(highlimit)){
      $("#highlimitError").text("请正确输入上限,保留1位小数").css("color","red");
      pass = false;
    }else{
      $("#highlimitError").text("")
    }

    if(lowlimit == null || lowlimit == ""){
      $("#lowlimitError").text("请输入下限").css("color","red");
      pass = false;
    }else{
      $("#lowlimitError").text("")
    }

    if(!regular.test(lowlimit)){
      $("#lowlimitError").text("请正确输入下限,保留1位小数").css("color","red");
      pass = false;
    }else{
      $("#lowlimitError").text("")
    }

    if(eval(highlimit)<eval(lowlimit)){
      $("#lowlimitError").text("下限必须小于上限").css("color","red");
      pass = false;
    }else{
      $("#lowlimitError").text("")
    }

    return pass;

  }

 /*初始化下标*/
  function resetTrNum(tableId) {
    $tbody = $("#" + tableId + "");
    $tbody.find('>tr').each(function(i){
      $(':input, span', this).each(function() {
        var $this = $(this), name = $this.attr('name'), val = $this.val();
        if (name != null) {
          if (name.indexOf("#index#") >= 0) {
            $this.attr("name",name.replace('#index#',i));
          }else{
            var s = name.indexOf("[");
            var e = name.indexOf("]");
            var new_name = name.substring(s+1,e);
            $this.attr("name",name.replace(new_name,i));
          }
        }
        var id = $this.attr('id'), val = $this.val();
        if (id != null) {
          if (id.indexOf("#index#") >= 0) {
            $this.attr("id",id.replace('#index#',i));
          }else{
            var a = id.indexOf("_");
            var new_id = id.substring(a+1, id.length);
            $this.attr("id",id.replace(new_id,i));
          }
        }

        $('#datetimepicker_'+i).datetimepicker({
          pickTime: false,
          todayBtn: true,
          language:'zh-cn'
        });
      });
    });
  }

  function checkBatchForm(tableId){
    var pass = true;
    $tbody = $("#" + tableId + "");
    $tbody.find('>tr').each(function(i){
      $(':input, span', this).each(function() {
        var tradedate = $("#tradedate_"+i).val();
        var averageprice = $("#averageprice_"+i).val();
        if(tradedate == null || tradedate == ""){
          $("#error_"+i).text("日期不能为空").css("color","red");
         // return false;
          pass = false;
        }else{
          $("#error_"+i).text("")
        }

        if(averageprice == null || averageprice == ""){
          $("#error_"+i).text("价格不能为空").css("color","red");
          pass = false;
        }else{
          $("#error_"+i).text("")
        }

        var regular = /^(\d{1,4}(\.\d{1,2})?)$/;
        if(!regular.test(averageprice)){
          $("#error_"+i).text("请正确输入价格").css("color","red");
          pass = false;
        }else{
          $("#error_"+i).text("");
        }

      });
    });

    return pass;
  }

  //上传首页图片
  function uploadIndexPic(i){
    var picFilePath = $("#picFilePath_"+i).val();
    var mime = picFilePath.toLowerCase().substr(picFilePath.lastIndexOf("."));
    if(picFilePath == ""){
      $("#typeError_"+i).text("请选择图片!").css("color","red");
      return ;
    }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
      $("#typeError_"+i).text("请选择jpg,bmp,png,jpeg格式的图片上传!").css("color","red");
      return ;
    }else{
      $("#typeError_"+i).text("");
    }
    $('#form-banner_'+i).ajaxSubmit({success:function(data){
     // $("#path-banner_"+i).attr("width",200);
      //$("#"#path-banner_"+i").attr("height",100);
      $("#picSavePath_"+i).attr("src",data.filePath);
      $("#path-banner_"+i).val(data.filePath);
      $("#changeAndSequence_"+i).show();
      $("#form-banner_"+i).hide();
      }
    });

  }

});
