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
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;修改供应信息</h5></div>
                <hr>
            </div>
            <div>
                <form class="form-horizontal" id="supply-form" action="/editSellinfo">
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span >*</span>产品编号:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if sellInfo??>
                                    ${sellInfo.pid!''}
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>供货数量:</label>
                        <div class="col-xs-3 col-md-3">
                            <div class="col-xs-10 col-md-10">
                                <#if sellInfo??>
                                    <input class="form-control" type="text" id="supplyNumber1" name="supplyquantity" value="${sellInfo.supplyquantity?c!''}">
                                <#else>
                                    <input class="form-control" type="text" id="supplyNumber1" name="supplyquantity" placeholder="请填写供货瓶数">
                                </#if>
                            </div>
                            <label class="col-xs-2 col-md-2 control-label">瓶</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>交货时间:</label>
                        <div class="col-xs-6 col-md-6 ">
                            <div class="col-xs-5 col-md-5"  id="datetimepicker1" >
                                <div class="input-group date">
                                    <#if sellInfo??>
                                        <input type="text" class="form-control" data-date-format="YYYY-MM-DD" value="${sellInfo.deliverytime1!''}" readonly id="deliverydate" name="deliverytime1">
                                    <#else>
                                        <input type="text" class="form-control" data-date-format="YYYY-MM-DD" name="deliverytime1" id="deliverydate" readonly placeholder="请填写供货时间">
                                    </#if>
                                    <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                    </span>
                                </div>
                            </div>
                            <label class="col-xs-2 col-md-2" style="height:34px;line-height: 34px;">至</label>
                            <div class="col-xs-5 col-md-5"  id="datetimepicker2" style="margin-left: -30px;">
                                <div class="input-group date">
                                    <#if sellInfo??>
                                        <input type="text" class="form-control" data-date-format="YYYY-MM-DD" value="${sellInfo.deliverytime2!''}" readonly="readonly" id="deliverydate2" name="deliverytime2">
                                    <#else>
                                        <input type="text" class="form-control" data-date-format="YYYY-MM-DD" readonly="readonly" id="deliverydate2" name="deliverytime2" placeholder="请填写供货时间">
                                    </#if>
                                    <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <span id="errorInfo" style="height:34px;line-height: 34px;"></span>
                    </div>

                <!--付款方式 开始-->
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>付款方式:</label>
                        <div class="col-xs-10 col-md-10">
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="paymode" id="paymode">
                                    <#if sellInfo ??>
                                        <#if sellInfo.paymode == 1>
                                            <option value="1" selected>现汇</option>
                                        <#else>
                                            <option value="1">现汇</option>
                                        </#if>
                                        <#if sellInfo.paymode == 2>
                                            <option value="2" selected>账期</option>
                                        <#else>
                                            <option value="2">账期</option>
                                        </#if>
                                        <#if sellInfo.paymode == 3>
                                            <option value="3" selected>银行承兑汇票</option>
                                        <#else>
                                            <option value="3">银行承兑汇票</option>
                                        </#if>
                                        <#if sellInfo.paymode == 4>
                                            <option value="4" selected>现汇+银行承兑汇票</option>
                                        <#else>
                                            <option value="4">现汇+银行承兑汇票</option>
                                        </#if>
                                    <#else>
                                        <option value="1">现汇</option>
                                        <option value="2">账期</option>
                                        <option value="3">银行承兑汇票</option>
                                        <option value="4">现汇+银行承兑汇票</option>
                                    </#if>
                                </select>
                            </div>

                            <#if sellInfo ??>
                                <#if sellInfo.paymode == 2>
                                    <div class="col-xs-4 col-md-4"  id="payperiodDisplay2">
                                        <div class="col-xs-6 col-md-6">
                                            <select class="form-control"  id="payperiod2" name="payperiod">
                                                <#if sellInfo.payperiod == 0.5>
                                                    <option value="0.5" selected>0.5</option>
                                                <#else>
                                                    <option value="0.5">0.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 1>
                                                    <option value="1" selected>1</option>
                                                <#else>
                                                    <option value="1">1</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 1.5>
                                                    <option value="1.5" selected>1.5</option>
                                                <#else>
                                                    <option value="1.5">1.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 2>
                                                    <option value="2" selected>2</option>
                                                <#else>
                                                    <option value="2">2</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 2.5>
                                                    <option value="2.5" selected>2.5</option>
                                                <#else>
                                                    <option value="2.5">2.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 3>
                                                    <option value="3" selected>3</option>
                                                <#else>
                                                    <option value="3">3</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 3.5>
                                                    <option value="3.5" selected>3.5</option>
                                                <#else>
                                                    <option value="3.5">3.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 4>
                                                    <option value="4" selected>4</option>
                                                <#else>
                                                    <option value="4">4</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 4.5>
                                                    <option value="4.5" selected>4.5</option>
                                                <#else>
                                                    <option value="4.5">4.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 5>
                                                    <option value="5" selected>5</option>
                                                <#else>
                                                    <option value="5">5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 5.5>
                                                    <option value="5.5" selected>5.5</option>
                                                <#else>
                                                    <option value="5.5">5.5</option>
                                                </#if>
                                                <#if sellInfo.payperiod == 6>
                                                    <option value="6" selected>6</option>
                                                <#else>
                                                    <option value="6">6</option>
                                                </#if>

                                            </select>
                                        </div>
                                        <label class="col-xs-3 col-md-3 control-label">个月</label>
                                    </div>
                                </#if>
                            </#if>
                            <div class="col-xs-4 col-md-4"  style="display: none;" id="payperiodDisplay">
                                <div class="col-xs-6 col-md-6">
                                    <select class="form-control"  id="payperiod" name="payperiod">
                                        <option value="0.5">0.5</option>
                                        <option value="1">1</option>
                                        <option value="1.5">1.5</option>
                                        <option value="2">2</option>
                                        <option value="2.5">2.5</option>
                                        <option value="3">3</option>
                                        <option value="3.5">3.5</option>
                                        <option value="4">4</option>
                                        <option value="4.5">4.5</option>
                                        <option value="5">5</option>
                                        <option value="5.5">5.5</option>
                                        <option value="6">6</option>
                                    </select>
                                </div>
                                <label class="col-xs-3 col-md-3 control-label">个月</label>
                            </div>
                        </div>
                    </div>
                <!--付款方式 结束-->

                <!-- 定价方式 开始 -->
                    <div class="form-group" id="fixDiv">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>定价方式:</label>
                        <div class="col-xs-7 col-md-7">
                            <label class="checkbox-inline col-xs-3 col-md-3">
                                <input type="radio" name="optionsRadiosinline" class="radioItem" id="optionsRadiosFixedPrice" value="fixed" checked> 一口价:
                            </label>
                            <div class="col-xs-5 col-md-5">
                                <div class="col-xs-7 col-md-7">
                                    <#if sellInfo??>
                                        <#if sellInfo.ykj!=0>
                                            <input class="form-control"  style="margin-left: -28%;" type="text" name="ykj" value="${sellInfo.ykj?c!''}" id="ykj2">
                                        <#else>
                                            <input class="form-control"  style="margin-left: -28%;" type="text" name="ykj" id="ykj2">
                                        </#if>
                                    <#else>
                                        <input class="form-control"  style="margin-left: -28%;" type="text" name="ykj" placeholder="填写价格" id="ykj2">
                                    </#if>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <label class="control-label"  style="margin-left: -28px;">元/瓶</label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="stepDiv">
                        <div class="col-md-offset-2 col-xs-2 col-md-2">
                            <#if sellInfo??>
                                <#if sellInfo.ykj==0>
                                    <label class="checkbox-inline col-xs-12 col-md-12">
                                        <input type="radio" name="optionsRadiosinline" checked class="radioItem" id="optionsRadiosStepPrice" value="step"> 阶梯价:
                                    </label>
                                <#else>
                                    <label class="checkbox-inline col-xs-12 col-md-12">
                                        <input type="radio" name="optionsRadiosinline" class="radioItem" id="optionsRadiosStepPrice" value="step"> 阶梯价:
                                    </label>
                                </#if>
                            <#else>
                                <label class="checkbox-inline col-xs-12 col-md-12">
                                    <input type="radio" name="optionsRadiosinline" class="radioItem" id="optionsRadiosStepPrice" value="step"> 阶梯价:
                                </label>
                            </#if>
                        </div>
                        <div class="col-xs-6 col-md-6" id="stepDiv1" style="margin-left: -59px;">
                            <div class="col-xs-7 col-md-7">
                                <div class="col-xs-5 col-md-5">
                                    <input type="text" readonly class="form-control" name="amount11" value="50" id="smallest">
                                </div>
                                <div>
                                    <#assign first ="≤M≤" />
                                    <#assign t ="<M≤" />
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px; margin-right: 10px;">${first}</label>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj01Obj ??>
                                        <#if jtj01Obj.amount2 != 0>
                                            <input type="text" class="form-control" value="${jtj01Obj.amount2?c!''}" id="small" name="amount12" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        <#else>
                                            <input type="text" class="form-control" value="${jtj01Obj.amount2?c!''}" id="small" name="amount12" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="amount12" id="small" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px;">瓶</label>
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if jtj01Obj??>
                                    <#if jtj01Obj.price==0>
                                        <input class="form-control" type="text" name="jtj01"  id="jtj012">
                                    <#else>
                                        <input class="form-control" type="text" name="jtj01" value="${jtj01Obj.price?c!''}" id="jtj012">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="jtj01" placeholder="填写价格" id="jtj012">
                                </#if>
                            </div>
                            <div>
                                <label class="control-label">元/瓶</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="stepDiv2">
                        <div class="col-md-offset-2 col-xs-2 col-md-2"></div>
                        <div class="col-xs-6 col-md-6" id="stepDiv1" style="margin-left: -59px;">
                            <div class="col-xs-7 col-md-7">
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj02Obj ??>
                                        <#if jtj02Obj.amount1 != 0>
                                            <input type="text" readonly class="form-control" id="more" value="${jtj02Obj.amount1?c!''}" name="amount21">
                                        <#else>
                                            <input type="text" readonly class="form-control" name="amount21" id="more">
                                        </#if>
                                    <#else>
                                        <input type="text" readonly class="form-control" name="amount21" id="more">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px; margin-right: 10px;">${t}</label>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj02Obj ??>
                                        <#if jtj02Obj.amount2 != 0>
                                            <input type="text" class="form-control" id="middle" name="amount22" value="${jtj02Obj.amount2?c!''}" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        <#else>
                                            <input type="text" class="form-control" name="amount22" id="middle" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="amount22" id="middle" maxlength="6"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px;">瓶</label>
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if jtj02Obj??>
                                    <#if jtj02Obj.price==0>
                                        <input class="form-control" type="text" name="jtj02"  id="jtj022">
                                    <#else>
                                        <input class="form-control" type="text" name="jtj02" value="${jtj02Obj.price?c!''}" id="jtj022">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="jtj02" placeholder="填写价格" id="jtj022">
                                </#if>
                            </div>
                            <div>
                                <label class="control-label">元/瓶</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="stepDiv3" style="display: none;">
                        <div class="col-md-offset-2 col-xs-2 col-md-2"></div>
                        <div class="col-xs-6 col-md-6" id="stepDiv1" style="margin-left: -59px;">
                            <div class="col-xs-7 col-md-7">
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj03Obj ??>
                                        <#if jtj03Obj.amount1 != 0>
                                            <input type="text" readonly class="form-control" id="large" value="${jtj03Obj.amount1?c!''}" name="amount31">
                                        <#else>
                                            <input type="text" readonly class="form-control" name="amount31" id="large">
                                        </#if>
                                    <#else>
                                        <input type="text" readonly class="form-control" name="amount31" id="large">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px; margin-right: 10px;">${t}</label>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj03Obj ??>
                                        <#if jtj03Obj.amount2 != 0>
                                            <input type="text" class="form-control" name="amount32" id="larger" value="${jtj03Obj.amount2?c!''}" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        <#else>
                                            <input type="text" class="form-control" name="amount32" id="larger" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="amount32" id="larger" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px;">瓶</label>
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if jtj03Obj??>
                                    <#if jtj03Obj.price==0>
                                        <input class="form-control" type="text" name="jtj03"  id="jtj032">
                                    <#else>
                                        <input class="form-control" type="text" name="jtj03" value="${jtj03Obj.price?c!''}" id="jtj032">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="jtj03" placeholder="填写价格" id="jtj032">
                                </#if>
                            </div>
                            <div>
                                <label class="control-label">元/瓶<img src="/images/jian_03.png" id="reduceZero"/></label>
                            </div>
                        </div>
                    </div>


                    <div class="form-group" id="stepDiv4" style="display: none;">
                        <div class="col-md-offset-2 col-xs-2 col-md-2"></div>
                        <div class="col-xs-6 col-md-6" style="margin-left: -59px;">
                            <div class="col-xs-7 col-md-7">
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj04Obj ??>
                                        <#if jtj04Obj.amount1 != 0>
                                            <input type="text" readonly class="form-control" name="amount41" id="big" value="${jtj04Obj.amount1?c!''}">
                                        <#else>
                                            <input type="text" readonly class="form-control" name="amount41" id="big">
                                        </#if>
                                    <#else>
                                        <input type="text" readonly class="form-control" name="amount41" id="big">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px; margin-right: 10px;">${t}</label>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj04Obj ??>
                                        <#if jtj04Obj.amount2 != 0>
                                            <input type="text" class="form-control" name="amount42" id="bigger" value="${jtj04Obj.amount2?c!''}" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        <#else>
                                            <input type="text" class="form-control" name="amount42" id="bigger" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="amount42" id="bigger" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px;">瓶</label>
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if jtj04Obj??>
                                    <#if jtj04Obj.price==0>
                                        <input class="form-control" type="text" name="jtj04"  id="jtj042">
                                    <#else>
                                        <input class="form-control" type="text" name="jtj04" value="${jtj04Obj.price?c!''}" id="jtj042">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="jtj04" placeholder="填写价格" id="jtj042">
                                </#if>
                            </div>
                            <div>
                                <label class="control-label">元/瓶<img src="/images/jian_03.png" id="reduceFirst"/></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="stepDiv5" style="display: none;">
                        <div class="col-md-offset-2 col-xs-2 col-md-2"></div>
                        <div class="col-xs-6 col-md-6" style="margin-left: -59px;">
                            <div class="col-xs-7 col-md-7">
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj05Obj ??>
                                        <#if jtj05Obj.amount1 != 0>
                                            <input type="text" readonly class="form-control" id="littleBig" name="amount51" value="${jtj05Obj.amount1?c!''}">
                                        <#else>
                                            <input type="text" readonly class="form-control" name="amount51" id="littleBig">
                                        </#if>
                                    <#else>
                                        <input type="text" readonly class="form-control" name="amount51" id="littleBig">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px; margin-right: 10px;">${t}</label>
                                </div>
                                <div class="col-xs-5 col-md-5">
                                    <#if jtj05Obj ??>
                                        <#if jtj05Obj.amount2 != 0>
                                            <input type="text" class="form-control" name="amount52" id="biggest" value="${jtj05Obj.amount2?c!''}" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        <#else>
                                            <input type="text" class="form-control" name="amount52" id="biggest" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="amount52" id="biggest" maxlength="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                    </#if>
                                </div>
                                <div>
                                    <label class="control-label col-xs-1 col-md-1" style="margin-left: -20px;">瓶</label>
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if jtj05Obj??>
                                    <#if jtj05Obj.price==0>
                                        <input class="form-control" type="text" name="jtj05"  id="jtj052">
                                    <#else>
                                        <input class="form-control" type="text" name="jtj05" value="${jtj05Obj.price?c!''}" id="jtj052">
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="jtj05" placeholder="填写价格" id="jtj052">
                                </#if>
                            </div>
                            <div>
                                <label class="control-label">元/瓶<img style="padding-left:5px;" src="/images/jian_03.png" id="reduceSecond"/></label>
                            </div>
                        </div>
                    </div>

                    <!--添加阶梯价按钮-->
                    <div class="col-xs-offset-10 col-md-offset-10" id="addDiv">
                        <img src="/images/jia_06.png" id="add" />
                    </div>

                    <!--错误提示信息-->
                    <div class="col-xs-offset-4 col-md-offset-4" style="padding-bottom: 10px;">
                        <span id="error"></span>
                    </div>
                <!-- 定价方式 结束 -->

                    <!-- 联系方式 -->
                    <input type="hidden" id="linkType" value="${sellInfo.linktype}"/>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>联系方式:</label>
                        <div class="col-xs-10 col-md-10">
                            <div class="col-xs-10 col-md-10">
                                <#if sellInfo ??>
                                    <#if sellInfo.linktype>
                                        <input type="radio" class="linkItem"  name="linktype" value="0" />&nbsp;委托XX网快速寻找买家&nbsp;&nbsp;&nbsp;
                                        <input type="radio" class="linkItem" name="linktype" value="1" checked="checked" />&nbsp;让买家联系我公司客服
                                    <#else>
                                        <input type="radio" class="linkItem" name="linktype" value="0" checked="checked" />&nbsp;委托XX网快速寻找买家&nbsp;&nbsp;&nbsp;
                                        <input type="radio" class="linkItem" name="linktype" value="1" />&nbsp;让买家联系我公司客服
                                    </#if>
                                <#else>
                                    <input type="radio" class="linkItem" name="linktype" value="0" checked="checked" />&nbsp;委托XX网快速寻找买家&nbsp;&nbsp;&nbsp;
                                    <input type="radio" class="linkItem" name="linktype" value="1" />&nbsp;让买家联系我公司客服
                                </#if>
                            </div>
                        </div>
                    </div>

                    <div id="linkInfoDiv" style="display: none;">
                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>姓名</label>
                            <div class="col-xs-8 col-md-8">
                                <div class="col-xs-4 col-md-4">
                                    <#if sellInfo??>
                                        <#if sellInfo.linkmanname??>
                                            <input type="text" class="form-control" value="${sellInfo.linkmanname!''}" id="linkmanname" name="linkmanname">
                                        <#else>
                                            <input type="text" class="form-control" name="linkmanname" id="linkmanname" placeholder="请填写联系人姓名">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="linkmanname" id="linkmanname" placeholder="请填写联系人姓名">
                                    </#if>
                                </div>
                                <div class="col-xs-4 col-md-4">
                                    <span id="linkmanname_error" style="height: 34px;line-height: 34px;"></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>手机号</label>
                            <div class="col-xs-8 col-md-8">
                                <div class="col-xs-4 col-md-4">
                                    <#if sellInfo??>
                                        <#if sellInfo.linkmanphone??>
                                            <input type="text" class="form-control" value="${sellInfo.linkmanphone!''}" id="linkmanphone" name="linkmanphone">
                                        <#else>
                                            <input type="text" class="form-control" name="linkmanphone" id="linkmanphone" placeholder="请填写联系人电话">
                                        </#if>
                                    <#else>
                                        <input type="text" class="form-control" name="linkmanphone" id="linkmanphone" placeholder="请填写联系人电话">
                                    </#if>
                                </div>
                                <div class="col-xs-4 col-md-4">
                                    <span id="linkmanphone_error" style="height: 34px;line-height: 34px;"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 联系方式 结束-->
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">备注:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-12 col-md-12">
                                <#if sellInfo??>
                                    <textarea class="form-control" rows="4" id="releaseremarks" name="releaseremarks" maxlength="200">${sellInfo.releaseremarks}</textarea>
                                <#else>
                                    <textarea class="form-control" rows="4" id="releaseremarks" name="releaseremarks" placeholder="请输入备注" maxlength="200"></textarea>
                                </#if>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-offset-3 col-md-offset-3">
                        <span id="commonError"></span>
                    </div>

                    <div class="form-group" style="margin-top: 5%;">
                        <div class="col-xs-2 col-sm-offset-4 col-md-2">
                            <button type="button" id="btn-confirmSupply" class="btn btn-primary btn-block">确认更改</button>
                        </div>
                        <div class="col-xs-2 col-md-2">
                            <button type="button" id="btn-cancelSupply" class="btn btn-block">取消</button>
                        </div>
                    </div>

                    <hr>

                    <div style="border-left:4px solid red;">
                        <h5>&nbsp;&nbsp;其他信息(不可修改):</h5>
                    </div>
                    <br>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>美酒种类:</label>
                        <div class="col-xs-10 col-md-10">
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="pname" id="pname" disabled>
                                <#if pnames ??>
                                    <#list pnames as product>
                                <#if sellInfo??>
                                <#if sellInfo.pname!=product.name>
                                    <option value="${product.name}">${product.name}</option>
                                <#else>
                                    <option value="${sellInfo.pname}" selected>${sellInfo.pname}</option>
                                </#if>
                                <#else>
                                    <option value="${product.name}">${product.name}</option>
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
                            <div class="col-xs-7 col-md-7">
                                <#if sellInfo??>
                                    <input class="form-control" type="text" id="dwrz" name="NCV" value="${sellInfo.NCV?c!''}" disabled>
                                <#else>
                                    <input class="form-control" type="text" id="dwrz" name="NCV" placeholder="请输入3000-7000之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">kcal/kg</label>
                            </div>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>产地:</label>
                    <div class="col-xs-6 col-md-6">
                        <div class="col-xs-7 col-md-7">
                            <#if sellInfo??>
                                <input class="form-control" type="text" id="originplace" name="originplace" value="${sellInfo.originplace!''}" maxlength="100" disabled>
                            <#else>
                                <input class="form-control" type="text" id="originplace" name="originplace" placeholder="请输入产地" maxlength="100" disabled>
                            </#if>
                        </div>
                        <div class="col-xs-5 col-md-5">
                            <label class="control-label"></label>
                        </div>
                    </div>
                </div>
            <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标1:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if sellInfo??>
                                    <#if sellInfo.ADS != 0>
                                        <input class="form-control" type="text" name="ADS" id="ADS" disabled value="${sellInfo.ADS!''}" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="ADS"  id="ADS" disabled placeholder="请输入0-10之间的值" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="ADS" id="ADS" disabled placeholder="请输入0-10之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                        <span id="ADSError"></span>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>含糖量:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if sellInfo??>
                                    <input class="form-control" type="text" name="RS" id="RS" value="${sellInfo.RS!''}" disabled>
                                <#else>
                                    <input class="form-control" type="text" name="RS" id="RS" placeholder="请输入0-10之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>酒类指标4:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <input class="form-control" type="text" id="TM"  name="TM" value="${sellInfo.TM!''}" disabled>
                                <#else>
                                    <input class="form-control" type="text" name="TM"  id="TM" placeholder="请输入0-50之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                        <span id="TMError"></span>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标5:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <#if sellInfo.IM==0>
                                        <input class="form-control" type="text" id="TM" name="IM" value="" placeholder="请输入0-50之间的值" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="IM" value="${sellInfo.IM!''}" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" id="TM" name="IM" placeholder="请输入0-50之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>酒类指标2:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <#if sellInfo.ADV != 0>
                                        <input class="form-control" type="text" name="ADV" value="${sellInfo.ADV!''}" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="ADV" placeholder="请输入0-50之间的值" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="ADV" placeholder="请输入0-50之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标3:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <#if sellInfo.RV != 0>
                                        <input class="form-control" type="text" name="RV" value="${sellInfo.RV!''}" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="RV" placeholder="请输入0-50之间的值" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="RV" placeholder="请输入0-50之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标7:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <#if sellInfo.AFT==0>
                                        <input class="form-control" type="text" name="AFT" placeholder="请输入900-1600之间的值" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="AFT" value="${sellInfo.AFT?c!''}" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="AFT" placeholder="请输入900-1600之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">℃</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标6:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if  sellInfo??>
                                    <#if sellInfo.ASH==0>
                                        <input class="form-control" type="text" name="ASH" placeholder="请输入0-50之间的值" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="ASH" value="${sellInfo.ASH!''}" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="ASH" placeholder="请输入0-50之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label">%</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label">酒类指标8:</label>
                        <div class="col-xs-6 col-md-6">
                            <div class="col-xs-7 col-md-7">
                                <#if sellInfo??>
                                    <#if sellInfo.HGI==0>
                                        <input class="form-control" type="text" name="HGI" placeholder="请输入0-100之间的值" disabled>
                                    <#else>
                                        <input class="form-control" type="text" name="HGI" value="${sellInfo.HGI!''}" disabled>
                                    </#if>
                                <#else>
                                    <input class="form-control" type="text" name="HGI" placeholder="请输入0-100之间的值" disabled>
                                </#if>
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <label class="control-label"></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>交货方式:</label>
                        <div class="col-xs-10 col-md-10">
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="deliverymode" id="deliveryWay" disabled>
                                    <#if deliverymodes ??>
                                        <#list deliverymodes as mode>
                                            <#if sellInfo??>
                                                <#if sellInfo.deliverymode!=mode.name>
                                                    <option value="${mode.name}">${mode.name}</option>
                                                <#else>
                                                    <option value="${sellInfo.deliverymode}" selected>${sellInfo.deliverymode}</option>
                                                </#if>
                                            <#else>
                                                <option value="${mode.name}">${mode.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>交货地点:</label>
                        <div class="col-xs-7 col-md-7" id="way">
                            <div class="col-xs-3 col-md-3">
                                <select class="form-control" name="deliveryprovince" id="deliveryprovince" disabled>
                                    <#if provincelist ??>
                                        <#list provincelist as province>
                                            <#if sellInfo??>
                                                <#if sellInfo.deliveryprovince ??>
                                                    <#if sellInfo.deliveryprovince!=province.name>
                                                    <option value="${province.id}">${province.name}</option>
                                                    <#else>
                                                    <option value="${province.id}" selected>${sellInfo.deliveryprovince}</option>
                                                    </#if>
                                                </#if>
                                            <#else>
                                                <option value="${province.id}">${province.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <select class="form-control" name="deliveryplace" id="deliveryplace" disabled>
                                    <#if harbourlist ??>
                                        <#list harbourlist as place>
                                            <#if sellInfo??>
                                                <#if sellInfo.deliveryplace != place.name>
                                                <option value="${place.id}">${place.name}</option>
                                                <#else>
                                                <option value="${place.id}" selected>${sellInfo.deliveryplace}</option>
                                                </#if>
                                            <#else>
                                                <option value="${place.id}">${place.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <#if sellInfo ??>
                                    <#if sellInfo.deliveryplace=="其它">
                                    <#if sellInfo.otherharbour ??>
                                        <div id="otherplaceDisplay">
                                            <input type="text" class="form-control" id="otherharbour" name="otherharbour1" maxlength="20" value="${sellInfo.otherharbour!''}" disabled>
                                        </div>
                                    </#if>
                                    </#if>
                                </#if>
                                <div id ="otherplaceDisplay" style="display: none;">
                                    <input type="text" class="form-control" id="otherharbour" name="otherharbour" maxlength="20" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2 col-md-2">
                            <span id="otherplaceError" style="color: red; height: 34px; line-height: 34px;"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">*</span>检验机构:</label>
                        <div class="col-xs-10 col-md-10">
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="inspectorg" id="orgSelect" disabled>
                                    <#if inspectorgs??>
                                        <#list inspectorgs as org>
                                            <#if sellInfo??>
                                                <#if sellInfo.inspectorg!=org.name>
                                                    <option value="${org.name}">${org.name}</option>
                                                <#else>
                                                    <option value="${sellInfo.inspectorg}" selected>${sellInfo.inspectorg}</option>
                                                </#if>
                                            <#else>
                                                <option value="${org.name}">${org.name}</option>
                                            </#if>
                                        </#list>
                                    </#if>
                                </select>
                            </div>

                            <div class="col-xs-3 col-md-3">
                                <#if sellInfo ??>
                                    <#if sellInfo.inspectorg=="其它">
                                        <#if sellInfo.otherinspectorg ??>
                                            <div id="otherorgDisplay">
                                                <input type="text" class="form-control" id="otherinspectorg" name="otherinspectorg1" maxlength="20" value="${sellInfo.otherinspectorg!''}" disabled>
                                            </div>
                                        </#if>
                                    </#if>
                                </#if>
                                <div class="input-group date" style="display: none;" id="otherorgDisplay">
                                    <input type="text" id="otherinspectorg" class="form-control" name="otherinspectorg" maxlength="20" disabled>
                                </div>
                            </div>

                            <div class="col-xs-4 col-md-4">
                                <span id="otherorgError" style="color: red; height: 34px; line-height: 34px;"></span>
                            </div>
                        </div>
                    </div>
        </div>

    <#if sellInfo??>
        <input type="hidden" value="${sellInfo.id?c!''}" name="id"/>
        <input type="hidden" value="${sellInfo.version!''}" name="version"/>
    <#else>
        <input type="hidden" value="" name="id"/>
    </#if>

        <input type="hidden" value="${type}" name="type"/>
        <input type="hidden" value="${verifiedOnce}" name="verifiedOnce" id="verifiedOnce"/>

    </form>

    </div>
    </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal fade my_supply" id="releaseSupply-Confirm" data-backdrop="false" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="top:200px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel">
                                    温馨提示:
                                </h4>
                            </div>
                            <div class="modal-body">
                                <span class="my_supplyBody" style="padding-left: 30px;"></span>
                            </div>
                            <div class="modal-footer" style="text-align: center;">
                                <button type="button" class="btn btn-primary my_supplyButton">完善公司信息</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/supply.js')}"></script>
    </@block>
</@extend>