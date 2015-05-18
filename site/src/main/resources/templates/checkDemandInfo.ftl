<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .div-margin{
            margin-bottom: 2%;
        }
        .attr-color{
            color:#317ee6;
        }
        .unit-price{
            margin-left: 10%;
        }
        .table thead th {
            text-align: center;
        }
        .table tbody td{
            text-align: center;
        }
        .btnWidthSize{
            width: 90px;
        }

        .btnBgOk{
            background: #FF5459;
            border: none;
            color: #ffffff;
        }

        .btnBgOk:hover{
            background: red;
        }

        .btnBgCancel{
            background: #ffffff;
            color: red;
            border: 1px solid;
            margin-left: 15px;
        }
        .footerType{
            border: none; text-align: center; padding-top: 0px;
        }
        .wordColor{color: #317EE6;}
        .priceColor{color: red;}
        .table > tbody > tr > td{padding-right: 0px;padding-left: 0px;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div>
                <#if reqsource ??>
                    <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;详细需求信息</h5></div>
                <#else>
                    <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;需求发布校验</h5></div>
                </#if>
                <hr>
            </div>

            <table class="table table-bordered">
                <tbody>
                <tr><td style="text-align: left; background: #65b3e1;padding-left: 10px;"  colspan="6">美酒指标</td></tr>

                <tr>
                    <td>产品编号</td>
                    <td class=" wordColor" colspan="5">${demand.demandcode!''}</td>
                </tr>

                <tr>
                    <td>美酒种类</td>
                    <td class="wordColor">${demand.coaltype!''}</td>
                    <td>酒精度数</td>
                    <td class="wordColor">${demand.NCV?c!''}</td>
                    <td>酒类指标1</td>
                    <#if demand.ADS != 0>
                        <td class="wordColor">${demand.ADS!''}%</td>
                    <#else>
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>含糖量</td>
                    <td class="wordColor">${demand.RS!''}%</td>
                    <td>酒类指标2</td>
                    <td class="wordColor">${demand.ADV!''}%</td>
                    <td>酒类指标3</td>
                    <#if demand.RV != 0>
                        <td class="wordColor">${demand.RV!''}%</td>
                    <#else>
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>酒类指标4</td>
                    <td class="wordColor">${demand.TM!''}%</td>
                    <td>酒类指标5</td>
                    <#if demand.IM != 0>
                        <td class="wordColor">${demand.IM!''}%</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td class="">酒类指标6</td>
                    <#if demand.ASH != 0>
                        <td class="wordColor">${demand.ASH!''}%</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>酒类指标7</td>
                    <#if demand.AFT != 0>
                        <td class="wordColor">${demand.AFT?c!''}℃</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td>酒类指标8</td>
                    <#if demand.HGI != 0>
                        <td class="wordColor">${demand.HGI?c!''}</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td><td></td></td>
                </tr>

                <tr><td style="text-align: left; background: #65b3e1;padding-left: 10px;" colspan="6">需求基本信息</td></tr>
                <tr>
                    <td>提货地点</td>
                    <#if demand.deliveryplace == "其它">
                        <td  class="wordColor">${demand.deliveryprovince!''}${demand.otherplace!''}</td>
                    <#else>
                        <td class="wordColor">${demand.deliveryprovince!''}${demand.deliveryplace!''}</td>
                    </#if>
                    <td>提货方式</td>
                    <td class="wordColor">${demand.deliverymode!''}</td>
                    <td style="vertical-align:middle;" rowspan="2">需求数量</td>
                    <td style="vertical-align:middle;" rowspan="2" class="priceColor">${demand.demandamount}瓶</td>
                </tr>

                <tr>
                    <td>提货时间</td>
                    <#if demand.deliverymode == "场地自提">
                        <td colspan="3" class="priceColor">${demand.deliverydatestart!''} ~ ${demand.deliverydateend!''}</td>
                    <#else>
                        <td colspan="3" class="priceColor">${demand.deliverydate!''}</td>
                    </#if>
                </tr>

                <tr>
                    <td>检验机构</td>
                    <#if (demand.inspectionagency) == "其它">
                        <td colspan="3" class=" wordColor">${demand.otherorg!''}</td>
                    <#else>
                        <td colspan="3" class=" wordColor">${demand.inspectionagency!''}</td>
                    </#if>
                    <td style="vertical-align:middle;" rowspan="2">报价截止日</td>
                    <td style="vertical-align:middle;" rowspan="2" class="priceColor">${demand.quoteenddate!''}</td>
                </tr>

                <tr>
                    <td>交易员</td>
                    <td class="wordColor">${demand.tradername!''}</td>
                    <td>联系电话</td>
                    <td class="wordColor">${demand.traderphone!''}</td>
                </tr>
                <#if demand.releasecomment ?length gt 0>
                    <tr><td style="text-align: left; background: #65b3e1;padding-left: 10px;"  colspan="6">备注</td></tr>
                    <tr>
                        <td style="text-align: left; text-indent: 2em; word-break: break-all;" colspan="6">${demand.releasecomment}</td>
                    </tr>
                </#if>
                </tbody>
                <input type="hidden" id="code" value="${demand.demandcode!''}">
            </table>

            <#if (demand.comment)?exists>
                <#if ((demand.comment) != null) || ((demand.comment) != "")>
                    <#if reqsource??>
                    <div class=" col-xs-offset-1 col-xs-12" style="color: red; margin-top: 40px;">
                        <div class="col-xs-12 div-margin">
                            <div class="col-xs-6"></div>
                            <label style="text-align: right;" class="col-xs-2 control-label">审核反馈:</label>
                            <label class="col-xs-4 control-label">${(demand.comment)!''}</label>
                        </div>
                    </div>
                    </#if>
                </#if>
            </#if>

            <#if reqsource ??>
                <#if resume?exists>
                    <div class="col-xs-12 col-md-12">
                        <div class="col-xs-offset-9 col-xs-3">
                            <input type="button" class="btn btn-default btn-block btn-primary"  id="back-modify" value="修改">
                        </div>
                    </div>
                </#if>
            <#else >
                <div class="col-xs-12 col-md-12" style="margin-top: 40px;">
                    <div class="col-xs-offset-6 col-xs-3 col-md-offset-6 col-md-3">
                        <input type="button" class="btn btn-primary btn-block"  id="confirm-announce" value="确认发布">
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <input type="button" class="btn btn-default btn-block"  id="update-announce" value="更改">
                    </div>
                </div>
            </#if>
        </div>
    </div>
        <#--</div>-->
    <#if chooseQuote?exists>
        <div class="container clear-level">
            <table class="table" style="border: 1px solid #f5f5f5;">
                <thead style="background-color:#f5f5f5;">
                <tr>
                    <th>报价编号</th>
                    <th>申报瓶数(瓶)</th>
                    <th>申供价格(元/瓶)</th>
                    <th>客户公司名称</th>
                    <th>交易员联系方式</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if quoteList ??>
                        <#if quoteList?size gt 0>
                            <#list quoteList as item>
                            <tr style="border-bottom: 1px solid #f5f5f5;">
                                <td>${item.demandcode}</td>
                                <td>${item.supplyton?c!''}</td>
                                <td class="price">${item.quote?c!''}</td>
                                <td>${item.companyname}</td>
                                <td>${item.traderphone}</td>
                                <#if (item.status == "已中标")>
                                    <td><button class="btn btn-primary btn-block" disabled>接受报价</button></td>
                                <#else>
                                    <td><button class="btn btn-primary btn-block"  onclick="disabledBtn(this,${item.id?c});">接受报价</button></td>
                                </#if>
                            </tr>
                            </#list>
                        <#else>
                        <tr><td colspan='6'  style='color: #ff0000; font-size: 18px; text-align: center'>没有找对此报价的数据!</td></tr>
                        </#if>
                    </#if>
                </tbody>
            </table>
        </div>
        </#if>

        <div class="container">
            <div class="row clearfix">
                <div class="modal fade" id="modal-checkSuccess" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;height:180px;">
                            <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                            <br>
                            <div class="modal-body" style="text-align: center;">
                                <p style="color:#ff624f;margin-top: 15px;font-size:18px;">
                                    <img src="/images/gantanhao_03.png"/>
                                    您的需求信息已提交给平台,我们将尽快审核,</p><p style="color:#ff624f;font-size: 18px;">审核结果请关注我的需求。</p>
                                <div style="margin-top: 23px;">
                                <button type="button" class="btn" style="width: 158px;height:40px;font-size: 18px;background: #ff624f;color:#ffffff;" id="supply-close">确&nbsp;定</button>
                                </div>
                                <p></p>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <div class="modal" id="modal-selectDialog" data-backdrop="false" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 300px;top: 200px;font-size: 18px;">
            <div class="modal-content" >
                <div class="modal-body" style="text-align: center;color: red;">
                    <span>您确定接受该条报价?</span>
                </div>
                <div class="modal-footer footerType">
                    <button type="button" class="btn btnWidthSize btnBgOk btn-sm btn-primary" id="selectDialog_OK">确&nbsp;&nbsp;&nbsp;&nbsp;定</button>
                    <button type="button" class="btn btnWidthSize btnBgCancel btn-sm btn-default" data-dismiss="modal">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/releaseDemand.js')}"></script>
    </@block>
</@extend>