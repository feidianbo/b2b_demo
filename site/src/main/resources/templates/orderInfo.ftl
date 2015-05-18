<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
         .col-md-2, .col-md-4, .col-md-8, .col-md-12 {
            padding-left: 0px;
        }
        .delegate-hover input:hover{
            background-color: #ea3c27;
            box-shadow: 0px 0px 4px 4px rgba(192, 192, 192, 0.11);
        }
        .table thead{
            background-color:#f5f5f5;
        }

        .table tbody tr{
            border-bottom: 1px solid #f5f5f5;
        }

        .table thead th {
            text-align: center;
        }

        .table tbody td{
            text-align: center;
        }
        .tablebtn input{
            display: block;
            padding: 3px 15px;
            border-radius: 20px;
            border: solid 1px #FF5441;
            color: #FF5441;
            text-align: center;
        }
        .btn-delegate{
            background-color: #ff624f;
            color: #fff;
        }
        #gotoContract:hover{
            background-color: #ea3c27;
        }
        .wordColor{color: #317EE6;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                    <div id="body-head">
                    <#if reqsource??>
                        <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;订单详细信息</h5></div>
                    <#else>
                        <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;产品详细信息</h5></div>
                    </#if>
                    <hr>
                    </div>
                    <h5 style="color:red;margin-bottom: 2%;">请仔细核对以下信息:</h5>
                <#if sellInfo??>
                    <table class="table table-bordered" style="font-size:14px;">
                        <#assign t ="<M≤"/>
                        <tbody>
                        <#if reqsource??>
                        <tr><td style="text-align: left; background: #65b3e1; color: #ffffff;" colspan="8">订单基本信息</td></tr>
                        <#else>
                        <tr><td style="text-align: left; background: #65b3e1; color: #ffffff;" colspan="8">产品基本信息</td></tr>
                        </#if>
                        <tr>
                            <td>订单编号</td>
                            <td class="wordColor" colspan="4">${orderInfo.orderid!''}</td>
                            <td>付款方式</td>
                            <#if orderInfo.paytype=='PayTheWhole'>
                            <td colspan="2">付全款</td>
                            <#elseif orderInfo.paytype=='PayCashDeposit'>
                            <td colspan="2">付总货款10%的保证金</td>
                            <#else>
                            <td colspan="2">无</td>
                            </#if>
                        </tr>
                        <tr>
                            <td>提货地点</td>
                            <#if orderInfo.deliveryplace=="其它">
                            <td class="wordColor">${sellInfo.deliveryprovince!''}${sellInfo.otherharbour!''}</td>
                            <#else>
                                <td class="wordColor">${sellInfo.deliveryprovince!''}${sellInfo.deliveryplace!''}</td>
                            </#if>
                            <td>检验机构</td>
                            <#if sellInfo??>
                                <#if sellInfo.inspectorg=='无'>
                                    <td colspan="2" style="width: 200px;" class=" wordColor">无</td>
                                <#else>
                                    <#if sellInfo.inspectorg=='其它'>
                                        <td colspan="2" style="width: 200px;" class=" wordColor">${sellInfo.otherinspectorg!''}</td>
                                    <#else>
                                        <td colspan="2" style="width: 200px;" class=" wordColor">${sellInfo.inspectorg!''}</td>
                                    </#if>
                                </#if>
                            </#if>
                            <td>价格</td>
                            <td colspan="2" style="color:#ff0000;font-weight: bold;">${orderInfo.price?c!''}元/瓶</td>
                        </tr>
                        <tr>
                            <td>提货方式</td>
                            <td class="wordColor">${orderInfo.deliverymode!''}</td>
                            <td>提货时间</td>
                            <#if orderInfo.deliverymode=='场地自提'>
                            <td colspan="2" class="wordColor">${orderInfo.deliverytime1?date!''}~${orderInfo.deliverytime2?date!''}</td>
                            <#else>
                            <td colspan="2" class="wordColor">${orderInfo.deliverytime1?date!''}</td>
                            </div>
                            </#if>
                            <td>数量</td>
                            <td colspan="2" style="color:#ff0000;font-weight: bold;">${orderInfo.amount!''}瓶</td>
                        </tr>
                        <tr>
                            <td>交易员</td>
                            <td class="wordColor">${orderInfo.dealername!''}</td>
                            <td>联系电话</td>
                            <td colspan="2" class="wordColor">${orderInfo.dealerphone!''}</td>
                            <td>金额</td>
                            <td colspan="2" style="font-weight: bold;color:#ff0000;">${orderInfo.totalmoney!''}元</td>
                        </tr>
                        <tr><td style="text-align: left; background: #65b3e1; color: #ffffff;"  colspan="8">产品指标</td></tr>
                        <tr>
                            <td>美酒种类</td>
                            <td>${sellInfo.pname!''}</td>
                            <td>产地</td>
                            <td colspan="1">${sellInfo.originplace!''}</td>
                            <td>酒精度数</td>
                            <td class="wordColor">${sellInfo.NCV?c!''}kcal/kg</td>
                            <td>酒类指标4</td>
                            <td class="wordColor">${sellInfo.TM!''}%</td>
                        </tr>
                        <tr>
                            <td>酒类指标5</td>
                            <#if sellInfo.IM != 0>
                                <td>${sellInfo.IM!''}%</td>
                            <#else>
                                <td>--</td>
                            </#if>
                            <td>酒类指标1</td>
                            <#if sellInfo.ADS ??>
                                <#if sellInfo.ADS != 0>
                                    <td class="wordColor">${sellInfo.ADS!''}%</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                            <td style="width:100px;">含糖量</td>
                            <#if sellInfo.RS ??>
                                <#if sellInfo.RS != 0>
                                    <td class="wordColor">${sellInfo.RS!''}%</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                            <td style="width:115px;">酒类指标2</td>
                            <#if sellInfo.ADV ??>
                                <#if sellInfo.ADV != 0>
                                    <td class="wordColor">${sellInfo.ADV!''}%</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                        </tr>
                        <tr>
                            <td style="width: 115px;">酒类指标3</td>
                            <#if sellInfo.RV ??>
                                <#if sellInfo.RV != 0>
                                    <td class="wordColor">${sellInfo.RV!''}%</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                            <td class="">酒类指标6</td>
                            <#if sellInfo.ASH ??>
                                <#if sellInfo.ASH != 0>
                                    <td class="wordColor">${sellInfo.ASH!''}%</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                            <td>酒类指标7</td>
                            <#if sellInfo.AFT ??>
                                <#if sellInfo.AFT != 0>
                                    <td class="wordColor">${sellInfo.AFT?c!''}℃</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                            <td>酒类指标8</td>
                            <#if sellInfo.HGI ??>
                                <#if sellInfo.HGI != 0>
                                    <td class="wordColor">${sellInfo.HGI!''}</td>
                                <#else>
                                    <td class="wordColor">--</td>
                                </#if>
                            </#if>
                        </tr>
                        <#if sellInfo.releaseremarks ?length gt 0>
                            <tr><td class="tableTitle"  style="text-align: left; background: #65b3e1;color: #ffffff;"  colspan="8">备注</td></tr>
                            <tr><td style="text-align: left; text-indent: 3em;" colspan="8">${sellInfo.releaseremarks!''}</td></tr>
                        <#else>
                            <tr><td class="tableTitle"  style="text-align: left; background: #65b3e1;color: #ffffff;"  colspan="8">备注</td></tr>
                            <tr><td style="text-align: left; text-indent: 3em;" colspan="8"></td></tr>
                        </#if>
                        </tbody>
                    </table>
                </#if>
        <input type="hidden" value="${orderInfo.id?c}" id="idText"/>
        </div>
        <#if reqsource ??>
            <div class="col-xs-2 col-md-offset-2 col-md-2" style="padding-top: 30px;">
                <#if sellInfo.seller == '自营'>
                    <input type="button" class="btn btn-block btn-primary" value="查看合同" id="showContract">
                </#if>
            </div>
            <div class="col-xs-1 col-md-offset-6 col-md-1" style="padding-top: 30px;">
                <input type="button" class="btn btn-block btn-cancel" value="返回" id="cancelOrder">
            </div>
        <#else>
            <div class="col-xs-12 col-md-12">
                    <div class="col-xs-4 col-md-4 col-md-offset-9" style="padding-top: 20px;">
                        <label>认购瓶数:<span style="color:red;">${orderInfo.amount?c!''}</span>瓶</label>
                        &nbsp;&nbsp;&nbsp;
                        <#if orderInfo??>
                            <#if orderInfo.paytype=='PayTheWhole'>
                                <label>总货款:<span style="color:red;">${orderInfo.totalmoney!''}</span>元</label>
                            <#else>
                                <label>履约金:<span style="color:red;">${orderInfo.totalmoney*0.1!''}</span>元</label>
                            </#if>
                        </#if>
                    </div>
                    <div class="col-xs-offset-8 col-md-offset-8" style="margin-top: 5%;">
                        <div class="col-xs-8 col-md-8">
                            <input type="button" class="btn btn-block btn-delegate" value="确定认购" id="gotoContract">
                        </div>
                        <div class="col-xs-3 col-md-3">
                            <input  style="width:100px;" type="button" class="btn btn-cancel btn-block"  id="gotoUpdateOrder" value="修改">
                        </div>
                    </div>
            </div>
        </#if>
        <input type="hidden" value="${orderInfo.id?c}" id="orderId"/>
        <input type="hidden" value="${orderInfo.contractno}" id="contractNo"/>
        <input type="hidden" value="${orderInfo.paytype}" id="orderPayType"/>
        <input type="hidden" value="${reqsource}" id="reqsource"/>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-7 column">
                    <div class="modal fade" id="modal-login" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" margin:0px auto;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        用户登录
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" action="/login" role="form" id="login_form" method="post">
                                        <div class="form-group">
                                            <div class="loginInfoError">
                                                <span id="loginInfo"></span>
                                            </div>
                                            <div class="form-group">
                                                <label for="securephone" class="col-xs-2 col-sm-2 control-label">用户名:</label>
                                                <div class="col-xs-6 col-sm-6">
                                                    <input type="text" class="form-control" id="securephone" name="securephone" placeholder="请输入手机号或邮箱">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="password" class="col-xs-2 col-sm-2 control-label">登陆密码:</label>
                                                <div class="col-xs-6 col-sm-6">
                                                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入6-16个字符">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-6 col-sm-offset-2 col-sm-6">
                                                    <a href="/reset-password" class="resetPwd">忘记密码</a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-6 col-sm-offset-2 col-sm-6">
                                                    <button type="button" class="btn btn-lg btn-primary btn-block" id="login-btn">登录</button>
                                                </div>
                                            </div>
                                            <div style="margin-left:18%;">
                                                没有账号，
                                                <a href="/register">现在注册 》</a>&nbsp;或者<a href="#">委托人工找货</a>
                                            </div>
                                            <hr>
                                            <div style="margin-left:13%;">
                                                <label style="color:#ff0000;">使用合作网站账号登陆:</label><img style="margin-left: 2%;" src="/images/alipay.png">&nbsp;<img src="/images/qq.png">&nbsp;<img src="/images/weibo.png">
                                            </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
</div>
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/myRelease.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/myOrder.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/index.js')}"></script>
    </@block>
</@extend>


