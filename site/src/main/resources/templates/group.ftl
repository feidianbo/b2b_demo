<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .grouphead{
            background-color: #ffffff;
        }
        .groupremind{
            text-align: center;
            font-size: 22px;
            color: #313131;
            padding-top: 20px;
            padding-bottom: 10px;
        }
        .groupremindcontent{
            font-size:14px;
            text-align: left;
            color: #313131;
            margin-left: 50px;
            margin-right: 50px;
            padding-bottom: 20px;
        }
        .groupapply{
            text-align: center;
            padding-bottom: 20px;
        }
        .groupapplybutton{
            font-size: 20px;
            background-color: #ff0000;
            color: #ffffff;
            padding:10px 20px 10px 20px;
            border: 0px;
            border-radius: 5px;
        }
        .groupcontenthead{
            margin-top: 30px;
            margin-left: 15px;
            font-size: 20px;
            text-align: left;
        }
        .groupcontentlist{
            padding-top:5px;
            padding-left: 15px;
        }
        .groupcontentleft{
            float: left;
            margin-left: 15px;
            margin-bottom: 20px;
            width: 370px;
        }
        .groupcontentright{
            float: left;
            padding-top: 30px;
            text-align: left;
        }
        .groupcontenttitle{
            font-size: 20px;
        }
        .groupcontentbody{
            text-align: left;
            padding-bottom: 20px;
            padding-top: 20px;
            border-top: solid 1px #c0c0c0;
        }
        .clear{
            clear:both;
        }
        .groupprice{
            font-size: 26px;
            padding-left: 5px;
            padding-right: 5px;
            color: #ff0000;
        }
        .groupbuybutton{
            font-size: 18px;
            background-color: #0066ff;
            color: #ffffff;
            padding:5px 20px 5px 20px;
            border: 0px;
            border-radius: 5px;
            margin-left: 20px;
        }
        .marketprice{
            text-decoration: line-through;
            padding-left: 20px;
        }
        .groupcontenttime{
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .groupredcolor{
            color:#ff0000;
            padding-left: 10px;
        }
        .grouprighttable1{
            width: 100%;
            text-align: left;
        }
        .tablebottomborder{
            border-bottom: dashed 1px #c0c0c0;
        }
        .tablepadding{
            border-bottom: dashed 1px #c0c0c0;
            line-height: 40px;
            vertical-align: middle;
        }
        .groupbuyprice{
            padding-left:15px;
        }
        .tablepaddingdetail{
            line-height: 25px;
            vertical-align: middle;
        }
        .divStyle{
            width:110px;
            height:40px;
            border:1px solid #313131;
            text-align: center;
        }
        .divFont{
            line-height:40px;
            /*padding:40px;*/
        }
        .imgStyle{
            display: inline-block;
        }
        .imgStyle img{
            margin-top: -6px;
        }
        .imgPadding {
            padding-top: 10px;
        }
    </style>
    </@block>
    <@block name="body">
    <div>
        <div class="carousel slide" style="height: 440px;" id="carousel-746761">
            <ol class="carousel-indicators" style="width: 60px; margin: 0px auto;">
                <li class="active" data-slide-to="0" href="#carousel-746761" data-slide="prev"
                    data-target="#carousel-746761">
                </li>
                <li data-slide-to="1" data-target="#carousel-746761">
                </li>
                <li data-slide-to="2" data-target="#carousel-746761">
                </li>
            </ol>
            <div class="carousel-inner">
                <div class="item active">
                    <div style="width:100%;height:440px;background:url('/images/banner.jpg') no-repeat top center;"></div>
                </div>
                <div class="item">
                    <div style="width:100%;height:440px;background:url('/images/banner.jpg') no-repeat top center;"></div>
                </div>
                <div class="item">
                    <div style="width:100%;height:440px;background:url('/images/banner.jpg') no-repeat top center;"></div>
                </div>
            </div>
            <a class="left carousel-control" href="#carousel-746761" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-746761" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
        <div class="grouphead">
            <div class="groupremind">温馨提示</div>
            <div class="container">
                <div class=" groupremindcontent">
                    <p>1.团购规则</p>
                    <p style="text-indent: 2em;">(1) 参加团购活动的用户必须具备如下资格:</p>
                        <ul>
                            <li>必须是经过XX网资质认证的会员企业;</li>
                            <li>必须缴纳一定金额的团购保证金获得团购入场资格。</li>
                        </ul>
                    <p style="text-indent: 2em;">(2) 每个用户有三次团购资格循环使用,一次团购资格只能参加一次团购活动,每一次团购资格的获取需要缴纳人民币10万元的团购保证金,方可参加团购活动;</p>
                    <p style="text-indent: 2em;">(3) 当用户参加一次团购活动时,该次团购资格将与订单绑定,直到该次团购活动结束后,团购资格才能得到释放,之后才能循环使用;</p>
                    <p style="text-indent: 2em;">(4) 当团购用户在参加团购活动期间出现违约时,XX网将扣除本次团购资格,累计违约满三次,XX网将降低该用户信用等级,关闭其团购权限。</p>
                    <p>2.团购流程</p>
                    <div style="width:914px;margin:0 auto;">
                        <div class="divStyle" style="display:inline-block;">
                            <span class="divFont">团购活动</span>
                        </div>
                        <div class="imgStyle"><img src="/images/jiantou.png"/></div>
                        <div class="divStyle" style="display:inline-block;">
                            <span class="divFont">获得团购资格</span>
                        </div>
                        <div class="imgStyle"><img src="/images/jiantou.png"/></div>
                        <div class="divStyle" style="display:inline-block;">
                            <span class="divFont">线上认购</span>
                        </div>
                        <div class="imgStyle"><img src="/images/jiantou.png"/></div>
                        <div class="divStyle" style="display:inline-block;">
                            <span class="divFont">完成付款</span>
                        </div>
                        <div class="imgStyle"><img src="/images/jiantou.png"/></div>
                        <div class="divStyle" style="display:inline-block;">
                            <span class="divFont">线下交割</span>
                        </div>
                    </div>
                    <p style="margin-top:10px;">3.本说明最终解释权归XX网所有</p>
                    <hr style="border-color: #313131;"/>
                    <p>请用户仔细阅读本说明以及所参加团购活动中出现的《团购保证金协议》,如对本说明以及协议条款有问题,请及时咨询客服。如因用户未仔细查看本说明或协议,而产生误解或纠纷,用户自行承担责任。</p>
                </div>
            </div>
            <div class="groupapply">
                <input class="groupapplybutton"  type="button" id="getGroupBuyQualification" value="获取团购资格" />
            </div>
        </div>
        <div class="container">
            <div class="groupcontenthead">
                团购商品展示区:
            </div>
            <div class="groupcontentlist">
                <#list groupBuySupplies as group>
                    <div class="groupcontentbody"   style="margin-left:-20px;">
                        <div class="groupcontentleft">
                            <div class="groupcontenttitle">【<#if group.port=='其它'>${group.deliveryplace!''}<#else>${group.port}</#if>】 ${group.NCV?c}大卡 ${group.coaltype}</div>
                            <div class="imgPadding">
                                <img src="/images/groupbuytest.png"/>
                            </div>
                        </div>
                        <div class="groupcontentright">
                            <table class="grouprighttable1">
                                <tr>
                                    <td class="groupbuyprice">团购价：</td>
                                    <td colspan="2">
                                        <span class="groupprice"><b>${group.groupbuyprice}</b></span>元/瓶
                                        <span class="marketprice">市场价：${group.marketprice}元/瓶</span>
                                    </td>
                                    <td rowspan="2" colspan="2">
                                        <input class="groupbuybutton" onclick="Show(${group.id})"  type="button" value="立即团购" />
                                    </td>
                                </tr>
                                <tr class="tablebottomborder">
                                    <td>&nbsp;</td>
                                    <td class="groupcontenttime" colspan="2">
                                        团购时间: <span class="groupredcolor">${group.groupbuybegindate} ~ ${group.groupbuyenddate}</span>
                                    </td>
                                </tr>
                                <tr class="tablepadding">
                                    <td>&nbsp;</td>
                                    <td colspan="4">
                                        发售量:<span class="groupredcolor">${group.supplyamount?c}</span>瓶
                                        |
                                        已购量:<span class="groupredcolor">${group.selledamount?c}</span>瓶
                                        |
                                        剩余量:<span class="groupredcolor" id="groupLeftAmount">${(group.supplyamount-group.selledamount)?c}</span>瓶
                                        |
                                        起订量:<span class="groupredcolor">${group.minimumamount?c}</span>瓶
                                    </td>
                                </tr>
                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>美酒种类:${group.coaltype!''}</td>
                                    <td>酒精度数:${group.NCV?c}kcal/kg</td>
                                </tr>
                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>酒类指标4:${group.TM!''}</td>
                                    <td>酒类指标5:
                                        <#if group.IM != 0 && group.IM != ''>
                                        ${group.IM}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                </tr>
                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>酒类指标1:
                                        <#if group.ADS != 0 && group.ADS != ''>
                                        ${group.ADS}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                    <td>含糖量:
                                        <#if group.RS != 0 && group.RS != ''>
                                        ${group.RS}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                </tr>
                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>酒类指标2:
                                        <#if group.ADV != 0 && group.ADV != ''>
                                        ${group.ADV}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                    <td>酒类指标3:
                                        <#if group.RV != 0 && group.RV != ''>
                                        ${group.RV}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                </tr>

                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>酒类指标7:
                                        <#if group.AFT != 0 && group.AFT != ''>
                                        ${group.AFT?c}℃
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                    <td>酒类指标6:
                                        <#if group.ASH != 0 && group.ASH != ''>
                                        ${group.ASH}%
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                </tr>

                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>酒类指标8:
                                        <#if group.HGI != 0 && group.HGI != ''>
                                            ${group.HGI}
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                    <td>堆位:
                                        <#if group.storageplace != ''>
                                        ${group.storageplace}
                                        <#else>
                                            --
                                        </#if>
                                    </td>
                                </tr>

                                <tr class="tablepaddingdetail">
                                    <td>&nbsp;</td>
                                    <td>提货时间:</td>
                                    <td colspan="3">${group.deliverydatestart} ~ ${group.deliverydateend}</td>
                                </tr>
                            </table>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <input type="hidden" id="infoId" value="${group.id?c}"/>
                </#list>
            </div>
        </div>
    </div>

    <input type="hidden" id="userName" value="${(session().user.nickname)!(session().user.securephone)}"/>
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
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary my_supplyButton"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/groupBuy.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/login.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/welcome.js')}"</script>
    </@block>
</@extend>