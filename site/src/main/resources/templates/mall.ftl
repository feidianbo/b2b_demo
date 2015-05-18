<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        #myTable a{
            color:#000000;
            text-decoration: none;
        }
        .market-main{
            margin-top: 13px;
        }
        .announceBtn{
            margin-top:-30px;
            background-color: #317ee6;
            float:right;
            width:150px;
        }
        .paddingSpace{
            padding-left: 0px;
            padding-right: 0px;
        }
        select{width: 143px;text-align: center;}
        .inputStyle{
            height:30px;
            width:140px;
            line-height: 30px;
            text-align: center;
        }
        .noneSelectedStyle{
            border-color:red;
        }
        #filter {
            margin-top: 145px;
            border: 1px solid #edf4fd;
            background-color: #f7faff;
        }
        .fore1 {
            overflow: hidden;
            height: 35px;
            padding: 5px 8px;
            border-bottom: 1px solid #E7E3E7;
            zoom: 1;
            margin:0 auto;
        }
        #filter dl, #filter dt, #filter dd {
            float: left;
            line-height: 26px;
            zoom: 1;
            margin-left: 3px;
        }
        #filter .order .curr {
            border: 1px solid #E4393C;
            background-color: #E4393C;
            font-weight: 700;
        }
        .order dd{
            height: 24px;
            border: 1px solid #CECBCE;
            background-color: #fff;
            margin-right: 5px;
            line-height: 24px;
            overflow: hidden;
            zoom: 1;
        }
        a{
            color: #666;
            text-decoration: none;
        }
        .order .curr a:link{
            color: #fff;
        }
        .order dd a:focus{
            color: #FFF;
        }
        .order dd a:hover{
            text-decoration: underline;
            background:#E4393C;
            color: #fff;
        }
        #filter .order a{
            display: block;
            padding: 0 10px;
        }
        #filter .order .curr a:hover{
            color:#fff;
        }
        .glyphicon{
            position: static;
            height: 100%;
        }
        .f-sort{
            background-color: #e4393c;
            color: #FFF;
            border-color: #e4393c;
            font-weight: bold;
        }
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container paddingSpace">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;XX商城—供应信息列表</h5></div>
                <input type="button" id="release_supply" class="btn btn-primary announceBtn btn" value="发布需求>"/>
                <hr>
            </div>
            <form id="buy-form" action="/mall">
                <div style="margin: 0px auto; width: 100%; height: 110px;float: left;background-color: #eeeeee;text-align: center;line-height: 55px;">
                    <div style="padding-right: 150px;">
                        <label style="margin-left: 5px;">酒类:</label>
                        <select style="border-radius: 0px;" id="coalType_select" name="coaltype">
                            <#if coalList ??>
                                <#list coalList as coal>
                                    <#if coaltype == coal.id>
                                        <option selected value="${coal.id}">${coal.name}</option>
                                    <#else>
                                        <option value="${coal.id}">${coal.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                        <label style="margin-left:30px;">地区:</label>
                        <select style="border-radius: 0px;" id="region_select" name="region">
                            <#if regionlist ??>
                                <#list regionlist as reg>
                                    <#if region == reg.id>
                                        <option selected value="${reg.id}">${reg.name}</option>
                                    <#else>
                                        <option value="${reg.id}">${reg.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                        <label style="margin-left: 30px;">省份:</label>
                        <select style="border-radius: 0px;" id="province_select" name="province">
                            <#if provincelist ??>
                                <#list provincelist as prov>
                                    <#if province == prov.id>
                                        <option selected value="${prov.id}">${prov.name}</option>
                                    <#else>
                                        <option value="${prov.id}">${prov.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>

                        <label style="margin-left: 30px;">交货地:</label>
                        <select style="border-radius: 0px;" id="harbour_select" name="harbour">
                            <#if harbourlist ??>
                                <#list harbourlist as harb>
                                    <#if harbour == harb.id>
                                        <option selected value="${harb.id}">${harb.name}</option>
                                    <#else>
                                        <option value="${harb.id}">${harb.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                    </div>
                    <div>
                        <label style="margin-left: -12px;">酒精度数:</label>
                        <#if NCV01!='0'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" value="${NCV01!''}" id="smallNCV" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterNCV(event);"/>
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" id="smallNCV" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9")) onkeypress="return filterNCV(event);"/>
                        </#if>

                        <label id="bef">至</label>
                        <#if NCV02!='10000'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" id="bigNCV" value="${NCV02!''}" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterNCV(event);" />
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" id="bigNCV" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterNCV(event);"/>
                        </#if>
                        <label style="margin-left: 50px;">含糖量:</label>
                        <#if RS01!='0'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" value="${RS01!''}" id="smallRS"  style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" id="smallRS" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        </#if>

                        <label  id="bet">至</label>
                        <#if RS02!='10'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" value="${RS02!''}" id="bigRS" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" id="bigRS" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        </#if>
                        <button style="margin-left: 40px; margin-right: 10px; color:#ffffff; width: 120px; background-color:#317ee6"; class="btn" type="button" id="btn_searchMall">搜索</button>
                    </div>
                </div>
            </form>
            <div id="filter">
                <div class="fore1">
                    <dl class="order"><dt>排序：</dt>
                        <dd><a id="createTime" href="javascript:void(0);" onclick="searchByTime();">创建时间</a><b></b></dd>
                        <#--<dd><a id="ncvNum" href="javascript:void(0);" onclick="searchByNCV();">酒精度数</a><b></b></dd>-->
                        <#--<dd><a id="rsNum" href="javascript:void(0);" onclick="searchByRS();">含糖量</a><b></b></dd>-->
                        <dd ><a id="saleNum" href="javascript:void(0);" onclick="searchByAmount();">销量</a>
                            <b><img id="quantity" style="position: absolute;margin-left: 43px;margin-top: -20px; display:none;"/></b></dd>
                        <dd><a id="priceNum" href="javascript:void(0);" onclick="searchByPrice();">价格</a>
                            <b><img id="price" style="position: absolute;margin-left: 43px;margin-top: -20px; display:none;"/></b></dd>
                    </dl>
                </div>
            </div>
            <div class="market-main">
                <table class="table myTable" id="myTable">
                    <thead>
                    <tr style="border-top: 2px solid #007DE4;background-color: #f7f7f7;" id="head">
                        <th>产品编号</th>
                        <th >酒类</th>
                        <th>酒精度数</th>
                        <th>含糖量</th>
                        <th>交货地</th>
                        <th>价格</th>
                        <th>可售库存</th>
                        <th>交货时间</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if (parameterMap?size>0)>
                            <#list parameterMap as good>
                            <tr onclick="openNewPage('${good.id?c}','${good.seller}');">
                                <#if good.seller=="自营">
                                    <td style="text-align:left;padding-left: 20px;"><a href="javascript:void(0);" title="交易员:${good.dealername}" class="example" data-placement="right" data-content="联系电话:${good.dealerphone}"><span style="background-color: #007DE4;color:#ffffff;">${good.pid!''}</span>&nbsp;<img src="/images/shangc_03.png" style="margin-top: -2px;"></a></td>
                                <#else>
                                    <#if good.linktype>
                                        <td style="text-align:left;padding-left: 20px;"><a href="javascript:void(0);" title="联系人:${good.linkmanname}" class="example" data-placement="right" data-content="联系电话:${good.linkmanphone} 公司:${good.seller}"><span style="background-color: #007DE4;color:#ffffff;">${good.pid!''}</span>&nbsp;<span style="border:0px solid;margin-left: 10%;"></span></a></td>
                                    <#else>
                                        <td style="text-align:left;padding-left: 20px;"><a href="javascript:void(0);" title="交易员:${good.dealername}" class="example" data-placement="right" data-content="联系电话:${good.dealerphone} 公司:${good.seller}"><span style="background-color: #007DE4;color:#ffffff;">${good.pid!''}</span>&nbsp;<span style="border:0px solid;margin-left: 10%;"></span></a></td>
                                    </#if>
                                <#--<td style="text-align:left;padding-left: 20px;"><a href="javascript:void(0);" title="交易员:${good.dealername}" class="example" data-placement="right" data-content="联系电话:${good.dealerphone} 公司:${good.seller}"><span style="background-color: #007DE4;color:#ffffff;">${good.pid!''}</span>&nbsp;<span style="border:0px solid;margin-left: 17%;"></span></a></td>-->
                                </#if>
                                <td style="padding-left: 10px;">${good.pname}</td>
                                <td style="padding-left: 10px;">${good.NCV?c!''}</td>
                                <td style="text-align: right;padding-right: 25px;">${good.RS?string('0.00')!''}%</td>
                                <#if good.deliveryplace == "其它">
                                        <td style="text-align: center;padding-left: 35px;">${good.deliveryprovince +' '+ good.otherharbour}</td>
                                <#else>
                                        <td style="text-align: center;padding-left: 35px;">${good.deliveryprovince +' '+ good.deliveryplace}</td>
                                </#if>
                                <#if good.ykj==0>
                                    <td style="color:red;text-align: center;" class="a_btn">*最低价${good.jtjlast?c!''}</td>
                                <#else>
                                    <td style="color:red;text-align: center;" class="a_btn">${good.ykj?c!''}</td>
                                </#if>
                                <#if good.availquantity <= 0>
                                    <td style="color: #ff0000;text-align: right;padding-right: 10px;" class="a_btn">已售罄</td>
                                <#else>
                                    <td style="color: #ff0000;text-align: right;padding-right: 10px;" class="a_btn">${good.availquantity}</td>
                                </#if>
                                <td style="padding-right: 0px;">${good.deliverytime1!''} ~ ${good.deliverytime2!''}</td>
                            </tr>
                            </#list>
                        <#else>
                        <tr><td colspan='9' style=' font-size: 18px;text-align: center;'>没有搜索到您想要的产品,您可以<a href="/buy/releaseDemand" style="color: #317ee6;"> 发布需求 </a>或者<a href="/manualsell_in" style="color: #317ee6;"> 委托人工找货 </a>!</td></tr>
                        </#if>
                    </tbody>
                </table>
                <@pager path="/mall" params={"NVC01":NCV01,"NCV02":NCV02,"RS01":RS01,"RS02":RS02,"province":province,"harbour":harbour,"coaltype":coaltype,"sorttype":sorttype} page=page pagesize=pagesize count=count class="pager"/>
            </div>
        </div>
    </div>
    <input type="hidden" value="${mallType!''}" id="mallType"/>
    <input type="hidden" value="${sorttype!''}" id="sortType"/>
    <input type="hidden" value="${sequence!''}" id="sequence"/>
    <input type="hidden" value="${result!''}" id="selectedTag">
    <input type="hidden" value="${resultPrice!''}" id="resultPrice">
    <input type="hidden" value="${page!''}" id="currentPage">
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/buy.js')}"></script>
    </@block>
</@extend>
