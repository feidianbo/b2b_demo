<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .div-margin{
            margin-bottom: 2%;
        }
        .attr-color{
            color:#317ee6;
        }
        .x{
            padding-left: 0px;
            padding-right: 0px;
        }
        .y{
            margin-left: -1px;
        }
        .wordColor{color: #317EE6;}
        .priceColor{color: red;}
        .padClear{padding-left: 0px;padding-right: 0px;}
        .padClearR{padding-left: 0px;padding-right: 0px;text-align: right;}
        .padClearC{padding-left: 0px;padding-right: 0px;text-align: center;}
        .padClearL{padding-left: 0px;padding-right: 0px;text-align: left;}
        .table>tbody>tr>td{padding-left: 0px;padding-right: 0px;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div>
                <#if reqsource??>
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;供应信息详情</h5></div>
                <#else>
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;供应发布校验</h5></div>
                </#if>
                <hr>
            </div>

            <#--add by xj begin-->
            <table class="table table-bordered" style="font-size: 14px;">
                <#assign t ="<M≤"/>
                <#assign pay = (supplyInfo.paymode)/>
                <tbody>
                <tr><td style="padding-left:10px;text-align: left; background: #65b3e1; color: #ffffff;" colspan="8">产品指标</td></tr>
                <tr>
                    <td>产品编号</td>
                    <td colspan="7" class="wordColor" colspan="3">${supplyInfo.pid!''}</td>
                </tr>
                <tr>
                    <td style="width: 110px;">美酒种类</td>
                    <td class="wordColor">${supplyInfo.pname!''}</td>
                    <td style="width:90px;">产地</td>
                    <td class="wordColor">${supplyInfo.originplace!''}</td>
                    <td style="width:90px;">酒精度数</td>
                    <td class="wordColor">${supplyInfo.NCV?c!''}</td>
                    <td style="width:110px;">酒类指标4</td>
                    <td class="wordColor">${supplyInfo.TM!''}%</td>
                </tr>
                <tr>
                    <td>酒类指标5</td>
                    <#if supplyInfo.IM ??>
                        <#if supplyInfo.IM != 0>
                            <td class="wordColor">${supplyInfo.IM!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标1</td>
                    <#if supplyInfo.ADS ??>
                        <#if supplyInfo.ADS != 0>
                            <td class="wordColor">${supplyInfo.ADS!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>含糖量</td>
                    <#if supplyInfo.RS ??>
                        <#if supplyInfo.RS != 0>
                            <td class="wordColor">${supplyInfo.RS!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标2</td>
                    <#if supplyInfo.ADV ??>
                        <#if supplyInfo.ADV != 0>
                            <td class="wordColor">${supplyInfo.ADV!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                </tr>
                <tr>
                    <td>酒类指标3</td>
                    <#if supplyInfo.RV ??>
                        <#if supplyInfo.RV != 0>
                            <td class="wordColor">${supplyInfo.RV!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td class="">酒类指标6</td>
                    <#if supplyInfo.ASH ??>
                        <#if supplyInfo.ASH != 0>
                            <td class="wordColor">${supplyInfo.ASH!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标7</td>
                    <#if supplyInfo.AFT ??>
                        <#if supplyInfo.AFT != 0>
                            <td class="wordColor">${supplyInfo.AFT?c!''}℃</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标8</td>
                    <#if supplyInfo.HGI ??>
                        <#if supplyInfo.HGI != 0>
                            <td class="wordColor">${supplyInfo.HGI!''}</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                </tr>
                <tr><td style="padding-left:10px;text-align: left; background: #65b3e1; color: #ffffff;" colspan="8">产品基本信息</td></tr>
                <tr>
                    <#if supplyInfo.seller == '自营'>
                        <td>交货地点</td>
                        <td class="wordColor">${supplyInfo.deliveryprovince!''}${supplyInfo.deliveryplace!''}</td>
                        <td>交货方式</td>
                        <td class="wordColor">${supplyInfo.deliverymode!''}</td>
                    <#else>
                        <td>付款方式</td>
                        <#if pay == 1>
                            <td class="wordColor">现汇</td>
                        <#elseif pay == 2>
                            <td class="wordColor">账期${supplyInfo.payperiod}个月</td>
                        <#elseif pay == 3>
                            <td class="wordColor">银行承兑汇票</td>
                        <#else >
                            <td class="wordColor">现汇+银行承兑汇票</td>
                        </#if>
                        <td>交货地点</td>
                        <td class="wordColor">${supplyInfo.deliveryprovince!''}


                        <#if supplyInfo.deliveryplace?exists>
                            <#if supplyInfo.deliveryplace == "其它">
                                ${supplyInfo.otherharbour!''}
                            <#else>
                                ${supplyInfo.deliveryplace!''}
                            </#if>
                        </#if>

                        </td>

                    </#if>
                    <td rowspan="2" style="vertical-align: middle;">供应数量</td>
                    <td style="vertical-align: middle;" class="priceColor" rowspan="2">${supplyInfo.supplyquantity!''}瓶</td>
                    <td style="vertical-align: middle;" rowspan="4"><span style="font-size: 30px;">价格</span><br>(含税)</td>
                    <#if supplyInfo.ykj==0>
                        <td class=" wordColor" rowspan="4" style="width:255px;color:#ff0000;text-align: -webkit-left; vertical-align: middle;line-height: 25px;padding-left: 0px; padding-right: 0px; ">
                            <#if jtj01Obj ??>
                                <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                    <span class="padClearR col-xs-3">0</span>
                                    <span class="padClearC col-xs-2">${t}</span>
                                    <span class="padClearL col-xs-4">${jtj01Obj.amount2?c!''}瓶</span>
                                    <span class="padClearL col-xs-3">${jtj01Obj.price?c!''}元/瓶</span>
                                </div>
                            </#if>
                            <#if jtj02Obj ??>
                                <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                    <span class="padClearR col-xs-3">${jtj02Obj.amount1?c!''}</span>
                                    <span class="padClearC col-xs-2">${t}</span>
                                    <span class="padClearL col-xs-4">${jtj02Obj.amount2?c!''}瓶</span>
                                    <span class="padClearL col-xs-3">${jtj02Obj.price?c!''}元/瓶</span>
                                </div>
                            </#if>
                            <#if jtj03Obj ??>
                                <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                    <span class="padClearR col-xs-3">${jtj03Obj.amount1?c!''} </span>
                                    <span class="padClearC col-xs-2">${t}</span>
                                    <span class="padClearL col-xs-4">${jtj03Obj.amount2?c!''}瓶</span>
                                    <span class="padClearL col-xs-3">${jtj03Obj.price?c!''}元/瓶</span>
                                </div>
                            </#if>
                            <#if jtj04Obj ??>
                                <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                    <span class="padClearR col-xs-3">${jtj04Obj.amount1?c!''} </span>
                                    <span class="padClearC col-xs-2">${t}</span>
                                    <span class="padClearL col-xs-4">${jtj04Obj.amount2?c!''}瓶</span>
                                    <span class="padClearL col-xs-3">${jtj04Obj.price?c!''}元/瓶</span>
                                </div>
                            </#if>
                            <#if jtj05Obj ??>
                                <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                                    <span class="padClearR col-xs-3">${jtj05Obj.amount1?c!''} </span>
                                    <span class="padClearC col-xs-2">${t}</span>
                                    <span class="padClearL col-xs-4">${jtj05Obj.amount2?c!''}瓶</span>
                                    <span class="padClearL col-xs-3">${jtj05Obj.price?c!''}元/瓶</span>
                                </div>
                            </#if>
                        </td>
                    <#else>
                        <td rowspan="4" style="text-align: center;vertical-align:middle;color:#ff0000;">${supplyInfo.ykj!''}元/瓶</td>
                    </#if>
                </tr>
                <tr>
                    <#if supplyInfo.seller == '自营'>
                        <td>交货时间</td>
                        <td colspan="3" class="wordColor">${supplyInfo.deliverytime1!''} ~ ${supplyInfo.deliverytime2!''}</td>
                    <#else>
                        <td>交货方式</td>
                        <td class="wordColor">${supplyInfo.deliverymode!''}</td>
                        <td>交货时间</td>
                        <td class="wordColor">${supplyInfo.deliverytime1!''} ~ ${supplyInfo.deliverytime2!''}</td>
                    </#if>
                </tr>
                <tr>
                    <td>检验机构</td>
                    <#if supplyInfo??>
                        <#if supplyInfo.inspectorg=='无'>
                            <td colspan="3"  class=" wordColor">无</td>
                        <#else>
                            <#if supplyInfo.inspectorg=='其它'>
                                <td colspan="3"  class=" wordColor">${supplyInfo.otherinspectorg!''}</td>
                            <#else>
                                <td colspan="3"  class=" wordColor">${supplyInfo.inspectorg!''}</td>
                            </#if>
                        </#if>
                    </#if>
                    <td>已销售数量</td>
                    <#if supplyInfo.soldquantity ??>
                        <#if supplyInfo.soldquantity!=0>
                            <td class="priceColor">${supplyInfo.soldquantity!''}瓶</td>
                        <#else>
                            <td class="priceColor">0瓶</td>
                        </#if>
                    </#if>
                </tr>
                <tr>
                    <#if supplyInfo.seller == '自营'>
                        <td>交易员</td>
                    <#else>
                        <td>联系人</td>
                    </#if>
                    <#if supplyInfo.linktype>
                        <td class="wordColor">${supplyInfo.linkmanname!''}</td>
                    <#else>
                        <td class="wordColor">${supplyInfo.dealername!''}</td>
                    </#if>
                    <td>联系方式</td>
                    <#if supplyInfo.linktype>
                        <td class="wordColor">${supplyInfo.linkmanphone!''}</td>
                    <#else>
                        <td class="wordColor">${supplyInfo.dealerphone!''}</td>
                    </#if>
                    <#--<td>交易员</td>-->
                    <#--<td class="wordColor"></td>-->
                    <#--<td>联系方式</td>-->
                    <#--<td class="wordColor"></td>-->
                    <td>剩余库存量</td>
                    <#if supplyInfo.availquantity ??>
                        <#if supplyInfo.availquantity!=0>
                            <td class="priceColor">${supplyInfo.availquantity!''}瓶</td>
                        <#else>
                            <td class="priceColor">0瓶</td>
                        </#if>
                    </#if>
                </tr>
                <#if supplyInfo.releaseremarks?length gt 0>
                <tr><td style="padding-left:10px;text-align: left; background: #65b3e1; color: #ffffff;"  colspan="8">备注</td></tr>
                <#--<tr><td style="text-align: left;text-indent: 2em;" colspan="8">${supplyInfo.releaseremarks!''}</td></tr>-->
                <#else>
                </#if>
                </tbody>
            </table>
            <#if supplyInfo.releaseremarks?length gt 0>
                    <div style="width: 100%;text-indent: 2em;word-break: break-all;margin-bottom: 50px;border: 1px solid #ddd;border-top: 0px;">
                        <p>${supplyInfo.releaseremarks!''}</p></div>
            <#else>
                <div style="margin-bottom: 50px;"></div>
            </#if>
            <#--add by xj end-->

            <!-- 历史记录开始 -->
            <#if editHistList?exists>
            <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;历史记录 (按修改由近及远排序)</h5></div>
            <hr>
            <table class="table table-bordered" style="font-size: 12px; margin-bottom: 50px;">
                <tbody>
                    <tr style="text-align: left; background: #65b3e1; color: #ffffff; font-size: 14px;">
                        <td>修改次序</td>
                        <td>供应数量</td>
                        <td>付款方式</td>
                        <td>定价方式</td>
                        <td>交货时间</td>
                        <td>修改记录时间</td>
                        <td>备注</td>
                    </tr>

                    <#list editHistList as record>
                    <tr>
                        <td style="vertical-align:middle;">${record.editnum!}</td>
                        <td style="vertical-align:middle;">${record.supplyquantity!}瓶</td>

                        <!-- 付款方式开始 -->
                        <#if record.paymode == 1>
                            <td style="vertical-align:middle;">现汇</td>
                        <#elseif record.paymode == 2>
                            <td style="vertical-align:middle;">账期${record.payperiod!}个月</td>
                        <#elseif record.paymode == 3>
                            <td style="vertical-align:middle;">银行承兑汇票</td>
                        <#else >
                            <td style="vertical-align:middle;">现汇+银行承兑汇票</td>
                        </#if>
                        <!-- 付款方式结束 -->

                        <!-- 价格开始 -->
                        <#if record.ykj!=0>
                            <td style="vertical-align:middle;">${record.ykj!}</td>
                        <#else>
                        <#--阶梯价开始-->
                            <td style="vertical-align:middle; width: 250px; padding-left: 0px;padding-right: 0px;">
                                <#if record.pricelist?exists>
                                    <#list record.pricelist as price>
                                    <#--<div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">-->
                                        <div style="padding-left: 0px;padding-right: 0px;">
                                            <span class="padClearR col-xs-3" style="">${price.amount1!}</span>
                                            <span class="padClearC col-xs-2"> &lt; M ≤ </span>
                                            <span class="padClearL col-xs-3">${price.amount2!}瓶</span>
                                            <span class="padClearL col-xs-4" style="">${price.price!}元/瓶</span>
                                        </div>
                                    <#--</div>-->
                                    </#list>
                                </#if>
                            </td>
                        <#--阶梯价结束-->
                        </#if>
                        <td style="vertical-align:middle;">${record.deliverytime1!} ~ ${record.deliverytime2!}</td>
                        <td style="vertical-align:middle;">${record.lastupdatetime!}</td>
                        <td style="">
                            <span class="col-xs-12" style="width: 200px;word-wrap:break-word;">${record.releaseremarks!}</span>
                        </td>
                        <!-- 价格结束 -->
                    </tr>

                    </#list>
                <!-- 历史记录开始 -->

                </tbody>
            </table>
            </#if>

            <#--原页面代码begin-->
            <#--<h5 style="color:red;margin-bottom: 2%;">请仔细核对以下信息:</h5>-->
            <#--<div class="col-xs-6 col-md-6 div-margin">-->
                <#--<label class="col-xs-4 col-md-4 control-label">产品编号:</label>-->
                <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.pid!''}</span>-->
            <#--</div>-->
            <#--<div class="col-xs-12 col-md-12">-->
                <#--<h5 style="color:red;margin-bottom: 2%;margin-left: -15px;">主要信息如下:</h5>-->
            <#--</div>-->
            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">美酒种类:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.pname!''}</span>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒精度数:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.NCV?c!''}kcal/kg</span>-->
                <#--</div>-->

            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标4:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.TM!''}%</span>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标5:</label>-->
                    <#--<#if supplyInfo.IM != 0>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.IM!''}%</span>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->

                <#--</div>-->
            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标1:</label>-->
                    <#--<#if supplyInfo ??>-->
                        <#--<#if supplyInfo.ADS != 0>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.ADS!''}%</span>-->
                        <#--<#else>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                        <#--</#if>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">含糖量:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.RS!''}%</span>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标2:</label>-->
                    <#--<#if supplyInfo ??>-->
                        <#--<#if supplyInfo.ADV != 0>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.ADV!''}%</span>-->
                        <#--<#else>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                        <#--</#if>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标3:</label>-->
                    <#--<#if supplyInfo ??>-->
                        <#--<#if supplyInfo.RV != 0>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.RV!'0'}%</span>-->
                        <#--<#else>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                        <#--</#if>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标7:</label>-->
                    <#--<#if supplyInfo.AFT != 0>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.AFT?c!''}℃</span>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标6:</label>-->
                    <#--<#if supplyInfo.ASH != 0>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.ASH!'0'}%</span>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">酒类指标8:</label>-->
                    <#--<#if supplyInfo.HGI != 0>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.HGI!''}</span>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">--</span>-->
                    <#--</#if>-->
                <#--</div>-->
                <#--<div class="col-xs-12 col-md-12">-->
                    <#--<hr style="margin-top: 0px; margin-bottom: 20px;">-->
                <#--</div>-->
             <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">交货地点:</label>-->
                    <#--<#if supplyInfo.deliveryplace=="其它">-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.deliveryprovince!''}${supplyInfo.otherharbour!''}</span>-->
                    <#--<#else>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.deliveryprovince!''}${supplyInfo.deliveryplace!''}</span>-->
                    <#--</#if>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">交货方式:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.deliverymode!''}</span>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">供应数量:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.supplyquantity!''}瓶</span>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">交货时间:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.deliverytime1!''} ~ ${supplyInfo.deliverytime2!''}</span>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">已销售量:</label>-->
                    <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.soldquantity!''}瓶</span>-->
                <#--</div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">检验机构:</label>-->
                    <#--<#if supplyInfo??>-->
                        <#--<#if supplyInfo.inspectorg=='无'>-->
                            <#--<span class="col-xs-8 col-md-8 control-label attr-color">无</span>-->
                        <#--<#else>-->
                            <#--<#if supplyInfo.inspectorg=='其它'>-->
                                <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.inspectorg!''}${supplyInfo.otherinspectorg!''}</span>-->
                            <#--<#else>-->
                                <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.inspectorg!''}</span>-->
                            <#--</#if>-->
                        <#--</#if>-->
                    <#--</#if>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div>-->
                <#--<div class="col-xs-6 col-md-6 div-margin">-->
                    <#--<label class="col-xs-4 col-md-4 control-label">可销售库存:</label>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.availquantity!''}瓶</span>-->
                <#--</div>-->
                <#--<#if supplyInfo.ykj!=0>-->
                    <#--<div class="col-xs-6 col-md-6 div-margin">-->
                        <#--<label class="col-xs-4 col-md-4 control-label">一口价:</label>-->
                        <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.ykj?c!''}元/瓶</span>-->
                    <#--</div>-->
                    <#--<div class="col-xs-12 col-md-12" style="height:90px;"></div>-->
                <#--<#else>-->
                    <#--<#assign t ="<M≤" />-->
                    <#--<div class="col-xs-6 col-md-6 div-margin">-->
                        <#--<#if jtj01Obj ??>-->
                            <#--<div class="col-xs-12 col-md-12">-->
                                <#--<label class="col-xs-4 col-md-4 control-label" style="margin-left: -15px;">阶梯定价:</label>-->
                                <#--<div class="col-xs-8 col-md-8" class="x">-->
                                    <#--<div class="col-xs-8 col-md-8 x">-->
                                        <#--<label style="margin-left:5px;"><span>50${t}${jtj01Obj.amount2?c!''}瓶</span></label>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-4 x">-->
                                        <#--<label class="attr-color"><span class="y">${jtj01Obj.price?c!''}元/瓶</span></label>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#if>-->
                    <#--</div>-->
                    <#--<div class="col-xs-12 col-md-12 div-margin">-->
                        <#--<#if jtj02Obj ??>-->
                            <#--<div class="col-xs-6 col-md-offset-6 col-md-6">-->
                                <#--<label class="col-xs-4 col-md-4 control-label"></label>-->
                                <#--<div class="col-xs-8 col-md-8" class="x">-->
                                    <#--<div class="col-xs-8 col-md-8 x">-->
                                        <#--<label>${jtj02Obj.amount1?c!''}${t}${jtj02Obj.amount2?c!''}瓶</label>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-4 x">-->
                                        <#--<label class=" attr-color">${jtj02Obj.price?c!''}元/瓶</label>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#if>-->
                    <#--</div>-->
                    <#--<div class="col-xs-12 col-md-12 div-margin">-->
                        <#--<#if jtj03Obj ??>-->
                            <#--<div class="col-xs-6 col-md-offset-6 col-md-6">-->
                                <#--<label class="col-xs-4 col-md-4 control-label"></label>-->
                                <#--<div class="col-xs-8 col-md-8" class="x">-->
                                    <#--<div class="col-xs-8 col-md-8 x" >-->
                                        <#--<label>${jtj03Obj.amount1?c!''}${t}${jtj03Obj.amount2?c!''}瓶</label>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-4 x" >-->
                                        <#--<label class="attr-color">${jtj03Obj.price?c!''}元/瓶</label>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#if>-->
                    <#--</div>-->
                    <#--<div class="col-xs-12 col-md-12 div-margin">-->
                        <#--<#if jtj04Obj ??>-->
                            <#--<div class="col-xs-6 col-md-offset-6 col-md-6">-->
                                <#--<label class="col-xs-4 col-md-4 control-label"></label>-->
                                <#--<div class="col-xs-8 col-md-8" class="x">-->
                                    <#--<div class="col-xs-8 col-md-8 x" >-->
                                        <#--<label>${jtj04Obj.amount1?c!''}${t}${jtj04Obj.amount2?c!''}瓶</label>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-4 x" >-->
                                        <#--<label class="attr-color">${jtj04Obj.price?c!''}元/瓶</label>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#if>-->
                    <#--</div>-->
                    <#--<div class="col-xs-12 col-md-12 div-margin">-->
                        <#--<#if jtj05Obj ??>-->
                            <#--<div class="col-xs-6 col-md-offset-6 col-md-6">-->
                                <#--<label class="col-xs-4 col-md-4 control-label"></label>-->
                                <#--<div class="col-xs-8 col-md-8" class="x">-->
                                    <#--<div class="col-xs-8 col-md-8 x" >-->
                                        <#--<label>${jtj05Obj.amount1?c!''}${t}${jtj05Obj.amount2?c!''}瓶</label>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-4 x" >-->
                                        <#--<label class=" attr-color">${jtj05Obj.price?c!''}元/瓶</label>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</#if>-->
                    <#--</div>-->
                <#--</#if>-->
            <#--</div>-->

            <#--<div>-->
                <#--<#if supplyInfo.status ??>-->
                    <#--<#if supplyInfo.status == 'VerifyNotPass'>-->
                        <#--<div>-->
                            <#--<div class="col-xs-6 col-md-6 div-margin">-->
                                <#--<label class="col-xs-4 col-md-4 control-label">审核备注:</label>-->
                                <#--<span class="col-xs-8 col-md-8 control-label attr-color">${supplyInfo.remarks!''}</span>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</#if>-->
                <#--</#if>-->
            <#--</div>-->
        <#--原页面代码end-->
        <input type="hidden" name="supplyId" id="supplyId" value="${supplyInfo.id?c}"/>
        <input type="hidden" name="version" id="version" value="${supplyInfo.version?c}" />
        <input type="hidden" name="editnum" id="editnum" value="${supplyInfo.editnum!''}">
        <#if reqsource ??>
            <#if supplyInfo.editnum !=0 >
                <div class="col-xs-12 col-md-12">
                    <div class="col-xs-2 col-md-offset-5 col-md-2">
                        <input type="button" class="btn btn-default btn-block btn-primary"  id="btn-supplyUpdate-VerifyPass" onclick="updateMySupply('${supplyInfo.id?c}');" value="修改信息">
                    </div>
                </div>
            </#if>

            <#if supplyInfo.status=='WaitVerify' && supplyInfo.editnum==0 >
            <div class="col-xs-12 col-md-12">
                <div class="col-xs-2 col-md-offset-4 col-md-2">
                    <input type="button" class="btn btn-default btn-block btn-primary"  id="btn-supplyUpdate" value="修改">
                </div>
            </div>
            <#elseif supplyInfo.status=='VerifyPass' && supplyInfo.editnum==0 >
                <div class="col-xs-12 col-md-12">
                    <div class="col-xs-2 col-md-offset-5 col-md-2">
                        <input type="button" class="btn btn-default btn-block btn-primary" id="btn-supplyUpdate-VerifyPass" onclick="updateMySupply('${supplyInfo.id?c}', '${supplyInfo.status}');" value="修改信息">

                    </div>
                </div>
            </#if>
        <#else>
            <div class="col-xs-12 col-md-12">
                <div class="col-xs-3 col-md-3" style="margin-left: 37%;">
                    <input type="button" class="btn btn-primary btn-block"  id="confirm-announce" value="确认发布">
                </div>
                <div class="col-xs-3 col-md-3">
                    <input type="button" class="btn btn-default"  id="update-announce" value="更改">
                </div>
            </div>
        </#if>
    </div>
</div>
    <div class="container">
        <div class="row clearfix">
            <div class="modal fade" id="modal-checkSuccess" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content" style="top:200px;height:180px;">
                        <br>
                        <div class="modal-body" style="text-align: center;">
                            <p style="color:#ff624f;margin-top: 15px;font-size:18px;">
                            <img src="/images/gantanhao_03.png"/>
                            您的供应信息已提交给平台,我们将尽快审核,</p><p style="color:#ff624f;font-size: 18px;">审核结果请关注我的供应。</p>
                            <div class="col-md-offset-4" style='margin-top:20px;'>
                                <button type="button" class="btn btn-default col-md-6 mybtn" id="supply-close" style="background-color: #ff624f;color:white;">确&nbsp;定</button>
                            </div>
                            <p></p>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="supplyUpdeteComfirm" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="mySmallModalLabel">温馨提示：</h4>
                </div>
                <div class="modal-body" style="text-align: center;">
                    <span class="supplyUpdeteComfirmBody" style="color: red;">当前供应信息已上架，修改会导致此商品下架，并进入审核流程，是否继续？</span>
                </div>
                <div class="modal-footer footerType">
                    <button type="button" class="btn btn-primary" id="supplyUpdeteComfirmBtn">继续更改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/supply.js')}"></script>
    </@block>
</@extend>