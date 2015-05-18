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
        </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;发布需求信息</h5></div>
                <hr>
            </div>
            <div>
                <form class="form-horizontal" url="/" id="requirement_form" role="form" method="post">
                    <#if demand??>
                        <input type="hidden" id="demandcode" name="demandcode" value="${demand.demandcode!''}">
                    </#if>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>美酒种类:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <select class="form-control" id="coaltype" name="coaltype">
                                    <#if cocaltypeList ??>
                                        <#list cocaltypeList as item>
                                            <#if demand??>
                                                <#if demand.coaltype != item.name>
                                                    <option value="${item.name}">${item.name}</option>
                                                <#else>
                                                    <option value="${demand.coaltype}" selected>${demand.coaltype}</option>
                                                </#if>
                                            <#else>
                                                <option value="${item.name}">${item.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>酒精度数:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <input class="form-control" type="text" id="NCV" name="NCV" value="${demand.NCV?c!''}">
                                <#else>
                                    <input class="form-control" type="text" placeholder="请输入3000-7000之间的值" id="NCV" name="NCV">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">kcal/kg</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标1:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.ADS == 0>
                                        <input class="form-control" type="text" placeholder="请输入0-10之间的值" id="ADS" name="ADS">
                                    <#else>
                                        <input class="form-control" type="text" id="ADS" name="ADS" value="${demand.ADS?c!''}">
                                    </#if>
                                    <#else>
                                        <input class="form-control" type="text" placeholder="请输入0-10之间的值" id="ADS" name="ADS">
                                  </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>含糖量:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <input class="form-control" type="text" id="RS" name="RS" value="${demand.RS?c!''}">
                                <#else>
                                    <input class="form-control" type="text" placeholder="请输入0-10之间的值" id="RS" name="RS">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>酒类指标4:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <input class="form-control" type="text" id="TM" name="TM" value="${demand.TM?c!''}">
                                <#else>
                                    <input class="form-control" type="text" id="TM" name="TM" placeholder="请输入0-50之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标5:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.IM == 0>
                                        <input class="form-control" type="text" id="IM" name="IM" placeholder="请输入0-50之间的值">
                                    <#else>
                                        <input class="form-control" type="text" id="IM" name="IM" value="${demand.IM!''}">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="IM" name="IM" placeholder="请输入0-50之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>酒类指标2:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <input class="form-control" type="text" id="ADV" name="ADV" value="${demand.ADV?c!''}">
                                <#else>
                                    <input class="form-control" type="text" id="ADV" name="ADV" placeholder="请输入0-50之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标3:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.RV == 0>
                                        <input class="form-control" type="text" id="RV" name="RV" placeholder="请输入0-50之间的值">
                                    <#else>
                                        <input class="form-control" type="text" id="RV" name="RV" value="${demand.RV?c!''}">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="RV" name="RV" placeholder="请输入0-50之间的值">
                                </#if>

                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标7:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.AFT == 0>
                                        <input class="form-control" type="text" id="AFT" name="AFT" placeholder="请输入900-1600之间的值">
                                    <#else >
                                        <input class="form-control" type="text" id="AFT" name="AFT" value="${demand.AFT?c!''}">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="AFT" name="AFT" placeholder="请输入900-1600之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">℃</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标6:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.ASH == 0>
                                        <input class="form-control" type="text" id="ASH" name="ASH" placeholder="请输入0-50之间的值">
                                    <#else >
                                        <input class="form-control" type="text" id="ASH" name="ASH" value="${demand.ASH!''}">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="ASH" name="ASH" placeholder="请输入0-50之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标8:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <#if demand.HGI == 0>
                                        <input class="form-control" type="text" id="HGI" name="HGI" placeholder="请输入0-100之间的值">
                                    <#else>
                                        <input class="form-control" type="text" id="HGI" name="HGI" value="${demand.HGI?c!''}">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="HGI" name="HGI" placeholder="请输入0-100之间的值">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>提货地点:</label>
                        <div class="col-xs-8 col-md-8">
                            <#--<div class="col-xs-3 col-md-3" style="display: none;">-->
                                <#--<select class="form-control" id="deliverydistrict" name="deliverydistrict">-->
                                    <#--<#if areaList ??>-->
                                        <#--<#list areaList as item>-->
                                            <#--<#if demand??>-->
                                                <#--<#if demand.deliverydistrict!=item.name>-->
                                                    <#--<option value="${item.name}">${item.name}</option>-->
                                                <#--<#else>-->
                                                    <#--<option value="${demand.deliverydistrict}" selected>${demand.deliverydistrict}</option>-->
                                                <#--</#if>-->
                                            <#--<#else>-->
                                                <#--<option value="${item.name}">${item.name}</option>-->
                                            <#--</#if>-->
                                        <#--</#list>-->
                                    <#--</#if>-->
                                <#--</select>-->
                            <#--</div>-->
                            <div class="col-xs-3 col-md-3">
                                <select class="form-control" id="deliveryprovince" name="deliveryprovince">
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
                           <#-- <div class="col-md-6">
                                <#if demand??>
                                    <input class="form-control" type="text" id="deliveryplace" name="deliveryplace" value="${demand.deliveryplace!''}">
                                <#else>
                                    <input class="form-control" type="text" id="deliveryplace" maxlength="10" name="deliveryplace" placeholder="请填写具体港口">
                                </#if>
                            </div>-->

                            <div class="col-xs-3 col-md-3">
                                <select class="form-control" id="deliveryplace" name="deliveryplace">
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

                            <div class="col-xs-6 col-md-6">
                                <#if demand ??>
                                    <#if demand.otherplace ??>
                                        <div id="otherplaceDisplay">
                                            <input type="text" class="form-control" id="otherplace" name="otherplace" maxlength="20" value="${demand.otherplace}">
                                        </div>
                                    </#if>
                                </#if>
                                <div id ="otherplaceDisplay" style="display: none;">
                                    <input type="text" class="form-control" id="otherplace" name="otherplace" maxlength="50" placeholder="请输入详细信息">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2 col-md-2">
                            <span id="otherplaceError" style="color: red; height: 34px; line-height: 34px;"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>报价截止日:</label>
                        <div class="col-xs-3 col-md-3">
                            <div class="col-xs-12 col-md-12">
                                <div class="input-group date" id="datetimepicker4">
                                    <#if demand??>
                                        <#if demand.quoteenddate??>
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="quoteenddate" name="quoteenddate" value="${demand.quoteenddate!''}">
                                        <#else >
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="quoteenddate" name="quoteenddate" placeholder="请填写截止时间">
                                        </#if>
                                    <#else>
                                        <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="quoteenddate" name="quoteenddate" placeholder="请填写截止时间">
                                    </#if>
                                    <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4 col-md-4" id="endTimeInfo" style="height: 34px; line-height: 34px;"></div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>提货方式:</label>
                        <div class="col-xs-4 col-md-4">
                            <div class="col-xs-6 col-md-6">
                                <select class="form-control" id="deliverymode" name="deliverymode">
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
                    </div>

                    <div class="form-group" id="defaultShow" style="display: none;">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>提货时间:</label>
                        <div class="col-xs-6 col-md-6 ">
                            <div class="col-xs-5 col-md-5">
                                <div class="input-group date" id="datetimepicker1">
                                    <#if demand??>
                                        <#if demand.deliverydatestart??>
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydatestart" name="deliverydatestart" value="${demand.deliverydatestart!''}">
                                        <#else >
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydatestart" name="deliverydatestart" placeholder="请填写供货时间">
                                        </#if>
                                    <#else>
                                        <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydatestart" name="deliverydatestart" placeholder="请填写供货时间">
                                    </#if>
                                    <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                                </div>
                            </div>
                            <div class="col-xs-1 col-md-1">
                                <label class="control-label">至</label>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <div class="input-group date" id="datetimepicker2">
                                    <#if demand??>
                                        <#if demand.deliverydateend??>
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydateend" name="deliverydateend" value="${demand.deliverydateend!''}">
                                        <#else >
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydateend" name="deliverydateend" placeholder="请填写供货时间">
                                        </#if>
                                    <#else>
                                        <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydateend" name="deliverydateend" placeholder="请填写供货时间">
                                    </#if>
                                    <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4 col-md-4" id="errorInfo" style="height: 34px; line-height: 34px;"></div>
                    </div>
                    <div class="form-group" id="changeShow" >
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>提货时间:</label>
                        <div class="col-xs-3 col-md-3">
                            <div class="col-xs-12 col-md-12" >
                                <div class="input-group date" id="datetimepicker3">
                                    <#if demand??>
                                        <#if demand.deliverydate??>
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydate" name="deliverydate" value="${demand.deliverydate!''}">
                                        <#else >
                                            <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydate" name="deliverydate" placeholder="请填写供货时间">
                                        </#if>
                                    <#else>
                                        <input class="form-control" type="text" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydate" name="deliverydate" placeholder="请填写供货时间">
                                    </#if>
                                    <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4 col-md-4" id="timeInfo" style="height: 34px; line-height: 34px;"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>需求数量:</label>
                        <div class="col-xs-4 col-md-4">
                            <div class="col-xs-8 col-md-8">
                                <#if demand??>
                                    <input class="form-control" maxlength="6" type="text" id="demandamount" name="demandamount" value="${demand.demandamount?c!''}">
                                <#else>
                                    <input class="form-control" type="text" maxlength="6" id="demandamount" name="demandamount" placeholder="请填写需求数量">
                                </#if>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <label class="control-label">瓶</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>检验机构:</label>
                        <div class="col-xs-8 col-md-8">
                            <div class="col-xs-6 col-md-6">
                                <select class="form-control" id="inspectionagency" name="inspectionagency">
                                    <#if inspectionagencyList ??>
                                        <#list inspectionagencyList as item>
                                            <#if demand??>
                                                <#if demand.inspectionagency!=item.name>
                                                    <option value="${item.name}">${item.name}</option>
                                                <#else>
                                                    <option value="${demand.inspectionagency}" selected>${demand.inspectionagency}</option>
                                                </#if>
                                            <#else>
                                                <option value="${item.name}">${item.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-xs-6 col-md-6">
                                <#if demand ??>
                                    <#if demand.otherorg ??>
                                        <div class="col-xs-12 col-md-12" >
                                            <div id="otherorgDisplay">
                                                <input type="text" class="form-control" id="otherorg" name="otherorg" maxlength="50" value="${demand.otherorg}">
                                            </div>
                                        </div>
                                    </#if>
                                <#else>
                                    <div class="col-xs-12 col-md-12" >
                                        <div id="otherorgDisplay">
                                            <input type="text" class="form-control" placeholder="请输入检验机构" id="otherorg" name="otherorg" maxlength="20" value="${demand.otherorg}">
                                        </div>
                                    </div>
                                </#if>
                                <div class="col-xs-12 col-md-12">
                                    <div id ="otherorgDisplay" style="display: none;">
                                        <input type="text" class="form-control" id="otherorg" name="otherorg" maxlength="20">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2 col-md-2">
                                <span id="otherorgError" style="color: red; height: 34px; line-height: 34px;"></span>
                            </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">备注:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-12 col-md-12">
                                <#if demand??>
                                    <textarea class="form-control" rows="4" id="releasecomment" name="releasecomment" maxlength="200">${demand.releasecomment}</textarea>
                                <#else>
                                    <textarea class="form-control" rows="4" id="releasecomment" name="releasecomment" placeholder="请输入备注" maxlength="200"></textarea>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 50px;">
                        <div class="col-sm-offset-3 col-md-3 col-xs-3">
                            <button type="button" id="releaseButton" class="btn btn-primary btn-block">确认发布</button>
                        </div>
                        <div class="col-md-2 col-xs-2">
                            <button type="button" id="releaseCancel" class="btn btn-block">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#--完善公司信息-->
    <div class="container">
        <div class="row clearfix">
            <div class="modal companyInfo_dialog"  role="dialog" data-backdrop="false" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog " style=" top:200px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">
                                温馨提示:
                            </h4>
                        </div>
                        <div class="modal-body">
                            <span class="companyInfo_body" style="padding-left: 30px;"></span>
                        </div>
                        <div class="modal-footer" style="text-align: center;">
                            <button type="button" class="btn btn-primary companyInfo_btn">完善公司信息</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
        <script type="text/javascript" src="${static('/scripts/releaseDemand.js')}"></script>
    </@block>
</@extend>