<@extend name="layout">
    <@block name="head">
    <link rel="stylesheet" type="text/css" href="/bower_components/bootstrap3-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>
    </@block>
    <@block name="cs">
    <style type="text/css">
        .required{
            color: #ff0000;
            margin-right: 6px;
            vertical-align: middle;
            font-size: large;
        }
        a:link,a:visited,a:hover,a:active {
            text-decoration: none;
            cursor:hand;
        }

    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <!--类型-->
        <input type="hidden" id="delegationType"  value="${delegationType}">
    <div class="container">
        <div id="body-head">
            <div style="border-left:4px solid red;"><span style="font-size:24px;">&nbsp;&nbsp;委托人工买卖</span></div>
            <hr>
        </div>
        <div class="radio" style="margin-left:120px; margin-top: 51px; margin-bottom:43px;">
            <label>
                <input type="radio" name="optionsRadios" value="1"  id="lookupRadio" style="font-size:16px;">委托人工找货
            </label>
            <label style="margin-left:50px;">
                <input type="radio" name="optionsRadios" value="0" id="sellRadio" style="font-size:16px;">委托人工销售
            </label>
        </div>
        <div id="lookupPanel" style="margin-top: 40px;">
            <form   id="lookupForm" class="form-horizontal" role="form">
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span>酒类:</label>
                    <div class="col-md-2">
                        <select class="form-control input-sm" id="coaltype" name="coalType">
                            <#if coalTypeList ??>
                                <#list coalTypeList as item>
                                            <option value="${item.name}">${item.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span>酒精度数:</label>
                    <div class="col-md-3">
                        <div class="col-md-9">
                        <input type="text" style="margin-left:-15px;" class="form-control input-sm"  name="lowcalorificvalue" placeholder="请输入酒精度数">
                        </div>
                        <div class="col-md-1">
                            <label   class="control-label" style="margin-left:-25px;">kcal/kg</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span>含糖量:</label>
                    <div class="col-md-3">
                        <div class="col-md-9">
                        <input type="text" style="margin-left:-15px;" class="form-control input-sm" name="receivebasissulfur" placeholder="请输入含糖量">
                        </div>
                        <div class="col-md-1">
                            <label   class="control-label" style="margin-left:-25px;">%</label>
                        </div>
                    </div>
                </div>
                <#--<div class="form-group">-->
                    <#--<label  class="col-md-2 control-label"><span class="required">*</span>酒类指标1:</label>-->
                    <#--<div class="col-md-2">-->
                        <#--<input type="text" class="form-control input-sm" name="airdrybasissulfur" placeholder="请输入酒类指标1">-->
                    <#--</div>-->
                <#--</div>-->
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span>酒类指标2:</label>
                    <div class="col-md-3">
                        <div class="col-md-9">
                        <input type="text" style="margin-left:-15px;" class="form-control input-sm" name="airdrybasisvolatile" placeholder="请输入酒类指标2">
                        </div>
                        <div class="col-md-1">
                            <label   class="control-label" style="margin-left:-25px;">%</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"><span class="required">*</span><span id="dealAddress">提货地点:</span></label>
                    <div class="col-md-2">
                        <select class="form-control input-sm" name="deliveryDistrict" id="deliveryDistrict">
                            <#if areaList ??>
                                <#list areaList as item>
                                    <#if demand??>
                                        <#if demand.deliverydistrict!=item.name>
                                            <option value="${item.name}">${item.name}</option>
                                        <#else>
                                            <option value="${demand.deliverydistrict}" selected>${demand.deliverydistrict}</option>
                                        </#if>
                                    <#else>
                                        <option value="${item.name}">${item.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <select class="form-control input-sm" name="deliveryProvince" id="deliveryProvince">
                            <#if provincesList ??>
                                <#list provincesList as item>
                                    <#if demand??>
                                        <#if demand.deliveryprovince!=item.name>
                                            <option value="${item.name}">${item.name}</option>
                                        <#else>
                                            <option value="${demand.deliveryprovince}" selected>${demand.deliveryprovince}</option>
                                        </#if>
                                    <#else>
                                        <option value="${item.name}">${item.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <select class="form-control input-sm" name="deliveryAddr" id="deliveryAddr">
                            <#if portsList ??>
                                <#list portsList as item>
                                    <#if demand??>
                                        <#if demand.deliveryplace!=item.name>
                                            <option value="${item.name}">${item.name}</option>
                                        <#else>
                                            <option value="${demand.deliveryplace}" selected>${demand.deliveryplace}</option>
                                        </#if>
                                    <#else>
                                        <option value="${item.name}">${item.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <div id ="otherplaceDisplay" style="display: none;">
                            <input type="text" class="form-control input-sm" style="width:200px;" id="otherplace" name="deliveryOtherPlace" maxlength="20" placeholder="请填写详细地址">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span><span id="dealCount">需求数量:</span></label>
                    <div class="col-md-3">
                        <div class="col-md-9">
                            <input type="text" style="margin-left:-15px;" class="form-control input-sm" id="demandamount" name="demandAmount" placeholder="请输入数量">
                        </div>
                        <div class="col-md-1">
                            <label  id="dealCount" class="control-label" style="margin-left:-30px;">瓶</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span><span id="dealMode">提货方式:</span></label>
                    <div class="col-sm-2">
                        <select class="form-control input-sm" name="deliveryMode" id="deliverymode">
                            <#if deliverymodeList ??>
                                <#list deliverymodeList as item>
                                    <#if demand??>
                                        <#if demand.deliverymode!=item.name>
                                            <option value="${item.name}">${item.name}</option>
                                        <#else>
                                            <option value="${demand.deliverymode}" selected>${demand.deliverymode}</option>
                                        </#if>
                                    <#else>
                                        <option value="${item.name}">${item.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label  class="col-md-3 control-label"><span class="required">*</span><span id="dealDate">提货时间:</span></label>
                    <div class="col-md-2" id="datetimepicker1">
                        <div class="input-group date">
                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydatestart" name="deliveryStartDate" placeholder="选择时间">
                        <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                    <div class="col-md-2" id="datetimepicker2" style="display:none;">
                        <div class="input-group date">
                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliveryEndDate" name="deliveryEndDate" placeholder="选择时间">
                        <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>


                    <div class="col-md-4" id="timeInfo" style="height: 34px; line-height: 34px;"></div>
                </div>

                <div id="contactInfo">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required">*</span>联系人:</label>
                        <div class="col-md-2">
                            <input type="text" class="form-control input-sm" id="contactName" name="contactName"  placeholder="请输入联系人" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required">*</span>公司名称:</label>
                        <div class="col-md-2">
                            <input type="text" class="form-control input-sm" id="companyName" value="${company.name}"  name="companyName" placeholder="请输入公司名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span class="required">*</span>手机号:</label>
                        <div class="col-md-2">
                            <input type="text" class="form-control input-sm" id="securephone" name="phone" value="${userPhone}" placeholder="请输入手机号码">
                        </div>
                    </div>

                    <#if userPhone??>
                    <#--存放用户手机号-->
                        <input type="hidden" value="${userPhone}" id="oldPhone"/>
                        <#assign display="none">
                    <#else>
                        <#assign display="block">
                    </#if>

                    <div class="form-group" id="verifyCodegroup" style="display:${display}">
                        <label for="smsCode" class="col-md-3 control-label"><span class="required">*</span>验证码:</label>
                        <div class="col-md-2">
                            <input type="text" id="verifyCode" name="verifyCode" class="form-control input-sm" style="height:30px; border-radius: 0px; font-size:12px;" placeholder="请输入验证码"/>&nbsp;&nbsp;
                        </div>
                        <div class="col-md-2" style="margin-top:7px;">
                            <a href="javascript:void(0)"  id="sendCode" class="control-label" style="margin-left:-25px;  cursor:hand; color:#317ee6;font-size:14px; font-weight: normal">获取验证码</a>
                        </div>
                        <div class="col-md-3">
                            <label id="code" class="control-label" style="font-weight: normal;"></label>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 40px;">
                        <div class="col-sm-offset-2 col-md-3">
                            <button id="submit"  type="button" class="btn btn-primary btn-block" style="background-color: #317EE6">确认委托</button>
                        </div>
                        <div class="col-md-2">
                            <button id="button" onclick="javascript:history.go(-1)" type="button" class="btn   btn-block" style="background-color: #E5E5E5">取消</button>&nbsp;&nbsp;&nbsp;
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
       </div>
    <div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog">
        <div class="modal-content" style="top:200px;height:150px;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <br>
            <div class="modal-body" style="text-align: center;">
                <div>
                    <img src="/images/gantanhao_03.png"/>
                    <label style="color:#ff624f;">您的委托已成功提交，我们会尽快与您联系并确认。</label>
                </div>
                <div class="col-md-offset-4" style='margin-top:20px;'>
                    <button type="button" class="btn btn-default col-md-6 mybtn" id="close_confirm_button" style="background-color: #ff624f;color:white;">关闭</button>
                </div>
                <p></p>
                <p></p>
            </div>
        </div>
    </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/manualsell.js')}"></script>
    </@block>
</@extend>
