require(['jquery','jquery.validation','jquery-form'],function($,jquery_validation){

$(function(){
        //加载公司信息
        $.ajax({
             url:"/account/getCompany",
             dataType: "json",
             success:function(data){
                setVal(data);
             },
             error:function(data){
                setHidden();
             }
        });
 /*       if($("#picForinvoicinginformation").src == null ){
          $("#picForinvoicinginformation").hide();
        }
        if($("#picForLicense").src == null){
          $("#picForLicense").hide();
        }
        if($("#picForNumber").src == null ){
          $("#picForNumber").hide();
        }
        if($("#picForCode").src == null){
          $("#picForCode").hide();
        }
        if($("#picForOperate").src == null){
          $("#picForOperate").hide();
        }
        if($("#picForOpeningLicense").src == null){
          $("#picForOpeningLicense").hide();
        }
*/
        //第一次公司没有信息时，不显示更换图片
        function setHidden(){
          $("#btn_license").css("display","none");
          $("#btn_number").css("display","none");
          $("#btn_code").css("display","none");
          $("#btn_openinglicense").css("display", "none");
          $("#btn_operater").css("display","none");
          //$("#btn_legalperson").css("display","none");
          $("#btn_invoicinginformation").css("display","none");
        }

        //已经有了公司信息时，显示更换图片，隐藏相关的form表单，同时给相关控件赋值
        function setVal(obj){
          $("#form-invoicinginformation").css("display","none");
          $("#form-businesslicense").css("display","none");
          $("#form-identificationnumber").css("display","none");
          $("#form-organizationcode").css("display","none");
          $("#form-openinglicense").css("display", "none");
          $("#form-operatinglicense").css("display", "none");
          $("#name").val(obj['name']);
          $("#address").val(obj['address']);
          $("#phone").val(obj['phone']);
          $("#fax").val(obj['fax']);
          $("#zipcode").val(obj['zipcode']);
          $("#legalpersonname").val(obj['legalpersonname']);
          $("#openingbank").val(obj['openingbank']);
          $("#account").val(obj['account']);
          $("#identificationnumword").val(obj['identificationnumword']);

          //审核通过和审核中不标注备注，审核不通过需要备注其原因(现在提示错误已换位置)
          //$("#verifystatus").val(obj['verifystatus']);
          //if(obj['verifystatus'] == "审核未通过"){
          //  $("#remarks").val(obj['remarks']);
          //}
          //修改企业法人
          $("#picForinvoicinginformation").attr("src",obj['invoicinginformation']);
          $("#picForinvoicinginformation").css("width",600);
          $("#picForinvoicinginformation").css("overflow",'hidden');
          $("#picForinvoicinginformation").css("height", 500);
          //修改企业法人

          $("#picForLicense").attr("src",obj['businesslicense']);
          $("#picForLicense").css("width",600);
          $("#picForLicense").css("overflow",'hidden');
          $("#picForLicense").attr("height", 500);

          $("#picForNumber").attr("src",obj['identificationnumber']);
          $("#picForNumber").attr("width",600);
          $("#picForNumber").css("overflow",'hidden');
          $("#picForNumber").attr("height", 500);

          $("#picForCode").attr("src",obj['organizationcode']);
          $("#picForCode").attr("width",600);
          $("#picForCode").css("overflow",'hidden');
          $("#picForCode").attr("height",500);

          $("#picForOpeningLicense").attr("src", obj['openinglicense']);
          $("#picForOpeningLicense").attr("width", 600);
          $("#picForOpeningLicense").css("overflow", 'hidden');
          $("#picForOpeningLicense").css("height", 500);

          if(obj['operatinglicense'] != "" &&  obj['operatinglicense'] != null){
            $("#picForOperate").attr("src",obj['operatinglicense']);
            $("#picForOperate").attr("width",600);
            $("#picForOperate").attr("height",500);
            $("#picForOperate").css("overflow",'hidden');
            $("#form-operatinglicense").css("display","none");
            $("#btn_operater").show();
          } else{
            $("#form-operatinglicense").css("display","block");
            $("#btn_operater").css("display","none");
          }

          if(obj['invoicinginformation'] != "" &&  obj['invoicinginformation'] != null){
            $("#picForinvoicinginformation").attr("src",obj['invoicinginformation']);
            $("#picForinvoicinginformation").attr("width",600);
            $("#picForinvoicinginformation").attr("height",500);
            $("#picForinvoicinginformation").css("overflow",'hidden');
            $("#form-invoicinginformation").css("display","none");
            $("#btn_invoicinginformation").show();
          } else{
            $("#form-invoicinginformation").css("display","block");
            $("#btn_invoicinginformation").css("display","none");
          }

          $("#hide-invoicinginformation").val(obj['invoicinginformation']);
          $("#hide-license").val(obj['businesslicense']);
          $("#hide-number").val(obj['identificationnumber']);
          $("#hide-code").val(obj['organizationcode']);
          $("#hide-operating").val(obj['operatinglicense']);
          $("#hide-openinglicense").val(obj['openinglicense']);
        }

        //点击更换图片时，让对应的form显示
        $("#btn_license").click(function(){
            $("#form-businesslicense").css("display","block");
            $("#btn_license").css("display","none");
        });
        $("#btn_number").click(function(){
            $("#form-identificationnumber").css("display","block");
            $("#btn_number").css("display","none");
        });
        $("#btn_code").click(function(){
            $("#form-organizationcode").css("display","block");
            $("#btn_code").css("display","none");
        });
        $("#btn_operater").click(function(){
            $("#form-operatinglicense").css("display","block");
            $("#btn_operater").css("display","none");
        });
        $("#btn_invoicinginformation").click(function(){
          $("#form-invoicinginformation").css("display","block");
          $("#btn_invoicinginformation").css("display","none");
        });
        $("#btn_openinglicense").click(function(){
          $("#form-openinglicense").css("display", "block");
          $("#btn_openinglicense").css("display", "none");
        })

        //点击对应的上传按钮上传对应的图片
        $("#invoicinginformationClick").click(function(){
          var license = $("#invoicinginformation").val();
          var mime = license.toLowerCase().substr(license.lastIndexOf("."));
          if(license == ""){
            $("#invoicinginformationInfo").text("请选择企业开票信息!");
            return ;
          }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
            $("#invoicinginformationInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!");
            return ;
          }else{
            $("#invoicinginformationInfo").text("");
          }
          $('#form-invoicinginformation').ajaxSubmit({success:function(data){
            if(data.success) {
              $("#hide-invoicinginformation").val(data.filePath);
              $("#picForinvoicinginformation").css("width", 600);
              $("#picForinvoicinginformation").attr("height", 500);
              $("#picForinvoicinginformation").css("overflow", 'hidden');
              $("#picForinvoicinginformation").attr("src", data.filePath);
              $("#form-invoicinginformation").css("display", "none");
              $("#btn_invoicinginformation").css("display", "inline-block");
              $("#picForinvoicinginformation").show();
            } else{
              $("#invoicinginformationInfo").text("只能上传大小10M以内的图片！").css("color", "red");
            }
          },
            error:(function(data){
            })
          });
        });

        $("#businesslicenseClick").click(function(){
            var license = $("#businesslicense").val();
            var mime = license.toLowerCase().substr(license.lastIndexOf("."));
            if(license == ""){
                $("#businesslicenseInfo").text("请选择营业执照!");
                return ;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#businesslicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!");
                return ;
            }else{
                $("#businesslicenseInfo").text("");
            }
            $('#form-businesslicense').ajaxSubmit({success:function(data){
              if(data.success) {
                $("#hide-license").val(data.filePath);
                $("#picForLicense").css("width", 600);
                $("#picForLicense").css("overflow", 'hidden');
                $("#picForLicense").attr("src", data.filePath);
                $("#picForLicense").attr("height", 500);
                $("#form-businesslicense").css("display", "none");
                $("#btn_license").css("display", "inline-block");
                $("#picForLicense").show();
              } else{
                $("#businesslicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
              }
            },
            error:(function(data){
            })
        });
        });

        $("#identificationnumberClick").click(function(){
            var cationNumber = $("#identificationnumber").val();
            var mime = cationNumber.toLowerCase().substr(cationNumber.lastIndexOf("."));
            if(cationNumber == ""){
                $("#identificationnumberInfo").text("请选择纳税人识别号!");
                return ;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#identificationnumberInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!");
                return ;
            }else{
                $("#identificationnumberInfo").text("");
            }
            $('#form-identificationnumber').ajaxSubmit({success:function(data){
              if(data.success) {
                $("#hide-number").val(data.filePath);
                $("#picForNumber").attr("height", 500);
                $("#picForNumber").css("width", 600);
                $("#picForNumber").css("overflow", 'hidden');
                $("#picForNumber").attr("src", data.filePath);
                $("#form-identificationnumber").css("display", "none");
                $("#btn_number").css("display", "inline-block");
                $("#picForNumber").show();
              } else{
                $("#identificationnumberInfo").text("只能上传大小10M以内的图片！").css("color", "red");
              }
            },
            error:(function(data){
            })
            });
        });

        $("#organizationcodeClick").click(function(){
            var organization = $("#organizationcode").val();
            var mime = organization.toLowerCase().substr(organization.lastIndexOf("."));
            if(organization == ""){
                $("#organizationcodeInfo").text("请选择组织机构代码!");
                return ;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#organizationcodeInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!");
                return ;
            }else{
                $("#organizationcodeInfo").text("");
            }
            $('#form-organizationcode').ajaxSubmit({success:function(data){
              if(data.success) {
                $("#hide-code").val(data.filePath);
                $("#picForCode").css("width", 600);
                $("#picForCode").css("overflow", 'hidden');
                $("#picForCode").attr("src", data.filePath);
                $("#picForCode").attr("height", 500);
                $("#form-organizationcode").css("display", "none");
                $("#btn_code").css("display", "inline-block");
                $("#picForCode").show();
              } else{
                $("#organizationcodeInfo").text("只能上传大小10M以内的图片！").css("color", "red");
              }
            },
            error:(function(data){
            })
          });
        });

        $("#operatinglicenseClick").click(function(){
            var operate = $("#operatinglicense").val();
            var mime = operate.toLowerCase().substr(operate.lastIndexOf("."));
          if(operate == ""){
            $("#operatinglicenseInfo").text("请选择美酒经营许可证!");
            return;
          } else{
            if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
              $("#operatinglicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!").css("color", "red");
              return ;
            }else{
              $("#operatinglicenseInfo").text("");
            }
            $('#form-operatinglicense').ajaxSubmit({success:function(data){
              if(data.success) {
                $("#hide-operating").val(data.filePath);
                $("#picForOperate").css("width", 600);
                $("#picForOperate").css("overflow", 'hidden');
                $("#picForOperate").attr("src", data.filePath);
                $("#picForOperate").attr("height", 500);
                $("#form-operatinglicense").css("display", "none");
                $("#btn_operater").css("display", "inline-block");
                $("#picForOperate").show();
              } else{
                $("#operatinglicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
              }
            },
              error:(function(data){
              })
            });
          }
        });

        $("#openinglicenseClick").click(function(){
          var license = $("#openinglicense").val();
          var mime = license.toLowerCase().substr(license.lastIndexOf("."));
          if(license == ""){
            $("#operatinglicenseInfo").text("请选择开户许可证！");
          } else{
            if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
              $("#openinglicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的图片上传!").css("color", "red");
              return ;
            }else{
              $("#openinglicenseInfo").text("");
            }
            $('#form-openinglicense').ajaxSubmit({success:function(data){
              if(data.success) {
                $("#hide-openinglicense").val(data.filePath);
                $("#picForOpeningLicense").css("width", 600);
                $("#picForOpeningLicense").css("overflow", 'hidden');
                $("#picForOpeningLicense").attr("src", data.filePath);
                $("#picForOpeningLicense").attr("height", 500);
                $("#form-openinglicense").css("display", "none");
                $("#btn_openinglicense").css("display", "inline-block");
                $("#picForOpeningLicense").show();
              } else{
                $("#openinglicenseInfo").text("只能上传大小10M以内的图片！").css("color", "red");
              }
            },
              error:(function(data){
              })
            });
          }
        });

        //头部信息的颜色切换，需要引用bootstrap.js
        $("#nav>li").mouseover(function(){
            $("#nav>li").each(function(i){
                $(this).removeClass("active");});
                $(this).addClass("active");
            }).mouseout(function(){
                $(this).removeClass("active");
            });
        });


        //失去光标判断控件的值是否正确
        $("#name").blur(function(){
            checkName();
        });
        $("#address").blur(function(){
          checkAddress();
        });
        $("#phone").blur(function(){
          checkPhone();
        });
        $("#fax").blur(function(){
          checkFax();
        });
        $("#legalpersonname").blur(function(){
          checkContact();
        });
        $("#openingbank").blur(function(){
          checkOpeningbank();
        })
        $("#account").blur(function(){
          checkAccount();
        })
        $("#identificationnumword").blur(function(){
          checkIdentificationnumword();
        })
        $("#zipcode").blur(function(){
          checkZipcode();
        })
        //修改或者添加公司信息时对相应的控件check
        $("#changeComBtn").click(function(){
            checkPhone();
            checkFax();
            checkName();
            checkAddress();
            checkContact();
            //checkLegalperson();
            checkBusinesslicense();
            checkOrganizationcode();
            checkOpeningLicense();
            checkIdentificationnumber();
            checkOpeningbank();
            checkAccount();
            checkIdentificationnumword();
            checkZipcode();
        if(checkFax() && checkName() && checkAddress() && checkContact()
          //&& checkLegalperson()
          &&  checkBusinesslicense() &&  checkOrganizationcode()
          && checkIdentificationnumber() && checkOpeningLicense() &&
             checkPhone() && checkAccount() && checkOpeningbank() && checkIdentificationnumword() && checkZipcode()){
                $.ajax({
                     url:"/account/saveCompany",
                     data:{name:$("#name").val(),address:$("#address").val(),phone:$("#phone").val(),fax:$("#fax").val(),invoicinginformation:$("#hide-invoicinginformation").val(),businesslicense:$("#hide-license").val(),
                       identificationnumber:$("#hide-number").val(),organizationcode:$("#hide-code").val(),operatinglicense:$("#hide-operating").val(),zipcode:$("#zipcode").val(),
                       legalpersonname:$("#legalpersonname").val(),account:$("#account").val(),openingbank:$("#openingbank").val(),identificationnumword:$("#identificationnumword").val(),
                       openinglicense:$("#hide-openinglicense").val()
                     },
                      success:function(data){
                        $("#verifystatus").val("待审核");
                        $("#modal-companyDialog").modal('show');
                     }
                });
            }
        });


        $("#btn_success").bind("click", function(){
          location.href="/account/accountInfo";
        });

        function checkName(){
            if($("#name").val()== ""){
                $("#comNameInfo").text("请输入公司名称!");
                return false;
            } else if(!IsCompanyName()){
                $("#comNameInfo").text("公司名称已存在!");
                return false;
            } else{
                $("#comNameInfo").text("");
                return true;
            }
        }

        function checkAddress(){
                if($("#address").val()== ""){
                    $("#addressInfo").text("请输入公司地址!");
                    return false;
                } else{
                    $("#addressInfo").text("");
                    return true;
                }
        }

        function checkPhone(){
          var result = true;
          var pattern = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
          if($("#phone").val() != ""){
            if(!pattern.test($("#phone").val())){
              $("#phoneInfo").text("请正确填写公司电话!");
              result = false;
            }else{
              $("#phoneInfo").text("");
            }
          }else{
            $("#phoneInfo").text("");
          }
          return result;
        }

        function checkFax(){
          var result = true;
          var pattern = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
          if($("#fax").val() != ""){
            if(!pattern.test($("#fax").val())){
              $("#faxInfo").text("请正确填写公司电话!");
              result = false;
            }else{
              $("#faxInfo").text("");
            }
          }else{
            $("#faxInfo").text("");
          }
          return result;
        }

        function checkAccount(){
          var pattern = /^\d{10}|\d{30}$/;
          if($("#account").val()== ""){
            $("#accountError").text("请填写公司银行账号!");
            return false;
          } else if(!pattern.test($("#account").val())){
            $("#accountError").text("请正确填写公司银行账号!");
            return false;
          } else{
            $("#accountError").text("");
            return true;
          }
        }

        function checkContact(){
            if($("#legalpersonname").val()== ""){
                $("#legalpersonnameInfo").text("请输入法人!");
                return false;
            }else{
                $("#legalpersonnameInfo").text("");
                return true;
            }
        }

        function checkOpeningbank(){
          var regular = /[^\u4E00-\u9FA5]/;
          if($("#openingbank").val() == ""){
            $("#openingbankInfo").text("请输入公司开户银行!");
            return false;
          }
          else if(regular.test($("#openingbank").val())){
            $("#openingbankInfo").text("公司开户银行只能输入汉字!");
            return false;
          }else{
            $("#openingbankInfo").text("");
            return true;
          }
        }

      function checkIdentificationnumword(){
        var regular = /[0-9a-zA-Z]/;
        if($("#identificationnumword").val() == ""){
          $("#identificationnumwordError").text("请输入纳税人识别号!");
          return false;
        }
        else if(!regular.test($("#identificationnumword").val())){
          $("#identificationnumwordError").text("请输入正确的纳税人识别号!");
          return false;
        }
        else{
          $("#identificationnumwordError").text("");
          return true;
        }
      }

      function checkZipcode(){
        var regular = /^[0-9]{6}$/;
        if($("#zipcode").val() == ""){
          $("#zipcodeError").text("请输入公司邮政编码!");
          return false;
        }else if(!regular.test($("#zipcode").val())){
          $("#zipcodeError").text("请输入正确的公司邮政编码!");
          return false;
        }
        else{
          $("#zipcodeError").text("");
          return true;
        }
      }

        //function checkLegalperson(){
        //  var license = document.getElementById("picForinvoicinginformation").src;
        //  var mime =license.toLowerCase().substr(license.lastIndexOf("."));
        //  if(license == "" || license == null){
        //    $("#invoicinginformationInfo").text("请选择企业法人!");
        //    return false;
        //  }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
        //    $("#invoicinginformationInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!");
        //    return false;
        //  }else{
        //    $("#invoicinginformationInfo").text("");
        //    return true;
        //  }
        //}

        function checkBusinesslicense(){
            var license = document.getElementById("picForLicense").src;
            var mime =license.toLowerCase().substr(license.lastIndexOf("."));
            if(license == "" || license == null){
                $("#businesslicenseInfo").text("请选择营业执照!");
                return false;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#businesslicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!");
                return false;
            }else{
                $("#businesslicenseInfo").text("");
                return true;
            }
        }

        function checkIdentificationnumber(){
            var cationNumber = document.getElementById("picForNumber").src;
            var mime =cationNumber.toLowerCase().substr(cationNumber.lastIndexOf("."));
            if(cationNumber == "" || cationNumber == null){
                $("#identificationnumberInfo").text("请选择纳税人识别号!");
                return false;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#identificationnumberInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!");
                return false;
            }else{
                $("#identificationnumberInfo").text("");
                return true;
            }
        }

        function checkOrganizationcode(){
            var organization = document.getElementById("picForCode").src;
            var mime =organization.toLowerCase().substr(organization.lastIndexOf("."));
            if(organization == "" || organization == null){
                $("#organizationcodeInfo").text("请选择组织机构代码!");
                return false;
            }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
                $("#organizationcodeInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!");
                return false;
            }else{
                $("#organizationcodeInfo").text("");
                return true;
            }
        }

        function checkOpeningLicense(){
          var openinglicense = document.getElementById("picForOpeningLicense").src;
          var mime = openinglicense.toLowerCase().substr(openinglicense.lastIndexOf("."));
          if(openinglicense == "" || openinglicense == null){
            $("#openinglicenseInfo").text("请选择开户许可证!");
            return false;
          } else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg" || mime == ".pdf")){
            $("#openinglicenseInfo").text("请选择jpg,bmp,png,jpeg,pdf格式的照片上传!");
            return false;
          } else{
            $("#openinglicenseInfo").text("");
            return true;
          }
        }

        //function checkOperatinglicense(){
        //    var operate = document.getElementById("picForOperate").src;
        //    var mime =operate.toLowerCase().substr(operate.lastIndexOf("."));
        //    if(operate == "" || operate == null){
        //        $("#operatinglicenseInfo").text("请选择煤炭经营许可证!");
        //        return false;
        //    }else if(!(mime == ".jpeg" || mime == ".bmp" ||mime == ".png" || mime == ".jpg")){
        //        $("#operatinglicenseInfo").text("请选择jpg,bmp,png,jpeg格式的照片上传!");
        //        return false;
        //    }else{
        //        $("#operatinglicenseInfo").text("");
        //        return true;
        //    }
        //}

        //昵称信息修改
        $("#btnSet").click(function(){
            $("#nickNameSaveInfo").text("");

            if($("#change-form").is(":visible")){
                $("#change-form").css("display", "none");
                $("#btnSet").css("color","#428bca");
                $("#btnSet").text("设置");
            }else{
                $("#change-form").css("display","block");
                $("#btnSet").css("color","red");
                $("#btnSet").text("取消设置");
            }
        });

        //登录密码修改
        $("#changePwd").click(function(){
            $("#changePwdSaveInfo").text("");

            if($("#pwd-form").is(":visible")){
                $("#pwd-form").css("display", "none");
                $("#changePwd").text("修改");
                $("#changePwd").css("color","#428bca");
            }else{
                $("#pwd-form").css("display","block");
                $("#changePwd").text("取消修改");
                $("#changePwd").css("color","red");
            }
        });

        //企业信息修改
        $("#companyModify").click(function(){
            $("#companyInfo").text("");

            if($("#company-form").is(":visible")){
                $("#company-form").css("display", "none");
                $("#companyModify").text("修改");
                $("#companyModify").css("color","#428bca");
            }else{
                $("#company-form").css("display","block");
                $("#companyModify").text("取消修改");
                $("#companyModify").css("color","red");
            }
        });

        //手机号修改
        $("#phoneBtn").click(function(){
            $("#newpPhoneSaveInfo").text("");

            if(!($("#newPhone_form").is(":visible"))){
                if($("#phone_form").is(":visible")){
                    $("#phone_form").css("display", "none");
                    $("#phoneBtn").text("修改");
                    $("#phoneBtn").css("color","#428bca");
                }else{
                    $("#phone_form").css("display","block");
                    $("#phoneBtn").text("取消修改");
                    $("#phoneBtn").css("color","red");
                }
            }else{
                    $("#newPhone_form").css("display","none");
            }
        });

        //修改昵称
        $("#btnNickname").click(function(){
            if($("#nickname").val() == null || $("#nickname").val() == ""){
                $("#nickNameInfo").text("昵称不能为空,请重新输入!");
                return;
            }else if($("#nickname").val().indexOf(" ") >= 0){
                $("#nickNameInfo").text("不能包含空格,请重新输入!");
                return;
            }else{
                $("#nickNameInfo").text("");
            }
            $.ajax({
                 url:"/modifynickname",
                 data:{nickname:$("#nickname").val(),securephone:$("#phoneNum").val()},
                 success:function(data){
                     if(data.success){
                        $("#userName").text($("#nickname").val());
                        $("#nickNameSaveInfo").text("保存成功!");

                     }
                     else{
                        $("#nickNameSaveInfo").text("保存失败!");
                     }
                 }
            });
        })

        //手机第一次获取验证码
        $("#verfyCode").click(function(){
            $(".tip").css("color","red").text("验证码已经发送到您的手机上!");
            sendSmsCode($(this));
            $.ajax({
                 url:"/getValidCode",
                 data:{securephone:$("#phoneNum").val()},
                 success:function(data){
                     if(data.success){
                     }
                     else{
                        $("#phoneSaveInfo").text("保存失败!");
                     }
                 }
            });
        })

        //手机第二次获取验证码
        $("#newVerfyCode").click(function(){
            var mobile = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
            if($("#newSecurephone").val() == null || $("#newSecurephone").val() == ""){
                $("#newSecurephoneInfo").text("新手机号码不能为空!");
                return;
            }else if(!mobile.test($("#newSecurephone").val())){
                $("#newSecurephoneInfo").text("请输入正确的手机号码!");
                return;
            }
            else{
                $("#newSecurephoneInfo").text("");
            }


            $.ajax({
              url:"/checkNewSecurephone",
              data:{newSecurephone:$("#newSecurephone").val()},
              success:function(data){
                if(data != "success"){
                  $("#newSecurephoneInfo").text("您输入的新手机号码已经存在!");
                  return;
                }
                else{
                  $("#newSecurephoneInfo").text("");
                  $(".SecTip").css("color","red").text("验证码已经发送到您的手机上!");
                  sendSmsCode($(this));
                  $.ajax({
                    url:"/getValidCode",
                    data:{securephone:$("#newSecurephone").val()},
                    success:function(data){
                      if(data.success){
                      }
                      else{
                        $("#newpPhoneSaveInfo").text("获取失败!");
                      }
                    }
                  });
                }
              }
            });
        });

        //function checkNewSecurephone(){
        //  $.ajax({
        //    url:"/checkNewSecurephone",
        //    data:{newSecurephone:$("#newSecurephone").val()},
        //    success:function(data){
        //      if(data == "success"){
        //        return true;
        //      }
        //      else{
        //        return false;
        //      }
        //    }
        //  });
        //}

        var wait=60;
        function sendSmsCode(val) {
            if (wait == 0) {
                val.val("获取验证码");
                val.removeAttr("disabled");
                wait = 60;
            } else {
                timeLoop(val, wait);
            }
        }

        function timeLoop(val, wait){
            val.attr({"disabled":"disabled"});
                setTimeout(function () {
                    val.val("重新发送("+ wait +")");
                    wait--;
                    if(wait < 0){
                        val.val("获取验证码");
                        val.removeAttr("disabled");
                         $(".tip").text("");
                         $(".SecTip").text("");
                        return;
                    }
                    timeLoop(val, wait);
                },1000)
        }

        //修改手机下一步的操作
        $("#vCode-btn").click(function(){
            if($("#code").val() == null || $("#code").val() == ""){
                     $("#codeInfo").text("验证码不能为空!");
                     return;
                 }else if($("#code").val().length != 6){
                     $("#codeInfo").text("验证码长度为6位!");
                     return;
                 }else{
                     $("#codeInfo").text("");
                 }

            $.ajax({
                 url:"/validOrginalPhone",
                 data:{securephone:$("#phoneNum").val(),code:$("#code").val()},
                 success:function(data){
                     if(data.success){
                        $("#newPhone_form").css("display","block");
                        $("#phone_form").css("display","none");
                     }
                     else{
                        $("#codeInfo").text("验证码不正确!");
                     }
                 }
            });
        })

        //修改手机完成的操作
        $("#OKBtn").click(function(){
           var mobile = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/;
            if($("#newSecurephone").val() == null || $("#newSecurephone").val() == ""){
                $("#newSecurephoneInfo").text("新手机号码不能为空!");
                return;
            }else if(!mobile.test($("#newSecurephone").val())){
                $("#newSecurephoneInfo").text("请输入正确的手机号码!");
                return;
            }else{
                $("#newSecurephoneInfo").text("");
            }

            if($("#newCode").val() == null || $("#newCode").val() == ""){
                $("#newCodeInfo").text("验证码不能为空!");
                return;
            }else if($("#newCode").val().length != 6){
                 $("#newCodeInfo").text("验证码长度为6位!");
                 return;
            }else{
                $("#newCodeInfo").text("");
            }

            $.ajax({
                 url:"/modifyphone",
                 data:{securephone:$("#newSecurephone").val(),code:$("#newCode").val()},
                 success:function(data){
                     if(data.success){
                       //判断是从账号安全的手机修改还是账号信息中的修改手机
                       if($("#selectModifyPwd").val() == "Security")
                       {
                         location.href="/account/accountSecurity";
                       }else{
                         location.href="/account/accountInfo";
                       }
                     }
                     else{
                        $("#newCodeInfo").text("验证码错误!");
                     }
                 }
            });
        })

        //修改密码
        $("#changePwdBtn").click(function(){
            if($("#orgPassword").val() == null || $("#orgPassword").val() == ""){
                $("#orgPasswordInfo").text("请输入原密码!")
                return;
            }else if($("#orgPassword").val().length < 6){
                $("#orgPasswordInfo").text("原密码的长度不能少于6位!");
                return;
            }else{
                $("#orgPasswordInfo").text("");
            }

            if($("#password").val() == null || $("#password").val() == ""){
                $("#passwordInfo").text("请输入新密码!")
                return;
            }else if($("#password").val().length < 6){
                $("#passwordInfo").text("新密码的长度不能少于6位!");
                return;
            }else if($("#password").val().indexOf(" ") != -1){
                $("#passwordInfo").text("新密码中不能包含空格!");
                return;
            }
            else{
                $("#passwordInfo").text("");
            }

            if($("#confirmPwd").val() == null || $("#confirmPwd").val() == ""){
                $("#confrimPwdInfo").text("请再次输入新密码!")
                return;
            }else if($("#confirmPwd").val().length < 6){
                $("#confrimPwdInfo").text("确认密码的长度不能少于6位!");
                return;
            }else if($("#confirmPwd").val().indexOf(" ") != -1){
                $("#confrimPwdInfo").text("确认密码中不能包含空格!");
                return;
            }
            else if($("#confirmPwd").val() != $("#password").val()){
                $("#confrimPwdInfo").text("新密码和确认密码不相同!");
                return;
            }else{
                $("#confrimPwdInfo").text("");
            }

            $.ajax({
                 url:"/modifyAccountPasswd",
                 data:{password:$("#orgPassword").val(),newpassword:$("#confirmPwd").val()},
                 success:function(data){
                     if(data.success){
                       $("#changePasswordDialog").modal('hide');
                       $("#successPasswordDialog").modal('show');
                     }
                     else{
                        $("#orgPasswordInfo").text("原密码不正确,请重新输入!");
                     }
                 }
                });
            })

        //判断公司名称是否存在
        function IsCompanyName(){
          var result = null;
          $.ajax({
            async:false,
            url:"/account/checkCompanyname",
            data:{name:$("#name").val()},
            success:function(data){
              if(data == "true"){
                result = true;
              }
              else{
                result =  false;
              }
            }
          });
          return result;
        }
})




