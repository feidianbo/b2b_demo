<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .announceBtn{
            margin-top:-30px;
            background-color: #317ee6;
            float:right;
            width:150px;
        }
        .market-main{
            margin-top: 20px;
        }
        .paddingSpace{
            padding-left: 0px;
            padding-right: 0px;
        }
        .marginSpace{margin-left: 0px;margin-right: 0px;}
        select{width: 143px;}
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
        .f-sort{
            background-color: #e4393c;
            color: #FFF;
            border-color: #e4393c;
            font-weight: bold;
        }
        .img_back{background-image:url("/images/timeOver.png"); background-repeat: no-repeat; background-size: contain; background-position-x: 8px;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container paddingSpace">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;我要卖—需求信息列表</h5></div>
                <a><button type="button" id="release_supply" class="btn btn-primary announceBtn" value="">发布供应></button></a>
                <hr>
            </div>
            <div class="row marginSpace">
                <div style="margin: 0px auto; width: 100%; height: 110px;float: left;background-color: #eeeeee;text-align: center;line-height: 55px;">
                    <div style="padding-right: 80px;">
                        <label>酒类:</label>
                        <select style="border-radius: 0px;" id="coalType_select">
                            <option value="0">全部</option>
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

                        <label style="padding-left: 50px;">地区:</label>
                        <select style="border-radius: 0px;" id="area_select">
                            <option value="0">全部</option>
                            <#if areatList ??>
                                <#list areatList as cate>
                                    <#if area == cate.id >
                                        <option selected value="${cate.id}">${cate.name}</option>
                                    <#else>
                                        <option value="${cate.id}">${cate.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                        <label style="margin-left: 50px;">省份:</label>
                        <select style="border-radius: 0px;" id="province_select">
                            <option value="0">全部</option>
                            <#if provincesList ??>
                                <#list provincesList as cate>
                                    <#if province == cate.id >
                                        <option selected value="${cate.id}">${cate.name}</option>
                                    <#else>
                                        <option value="${cate.id}">${cate.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>

                        <label style="margin-left: 50px;">提货地:</label>
                        <select style="border-radius: 0px;" id="port_select">
                            <option value= "0">全部</option>
                            <#if portsList ??>
                                <#list portsList as cate>
                                    <#if port == cate.id >
                                        <option selected value="${cate.id}">${cate.name}</option>
                                    <#else>
                                        <option value="${cate.id}">${cate.name}</option>
                                    </#if>
                                </#list>
                            <#else>
                            </#if>
                        </select>
                    </div>
                    <div>
                        <label >酒精度数:</label>
                        <#if lowHotValue!='0'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" value="${lowHotValue!''}" id="smallNCV" style="ime-mode:disabled"  maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9")) onkeypress="return filterNCV(event);"/>
                        <#else>
                            <input type="text" data-container="body" data-content="0< x ≤9999" data-placement="bottom" data-trigger="manual" class="inputStyle" id="smallNCV" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9")) onkeypress="return filterNCV(event);"/>
                        </#if>
                        <label id="label_heat">至</label>
                        <#if highHotValue!='10000'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" id="bigNCV" value="${highHotValue!''}" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterNCV(event);" />
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x ≤9999" data-trigger="manual" class="inputStyle" id="bigNCV" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterNCV(event);" />
                        </#if>

                        <label style="margin-left: 50px;">含糖量:</label>
                        <#if lowSulfurContent!='0'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" value="${lowSulfurContent!''}" id="smallRS"  style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        <#else>
                            <input type="text"  data-placement="bottom"  data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" id="smallRS"  style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        </#if>

                        <label id="label_sulfur">至</label>
                        <#if highSulfurContent!='10'>
                            <input type="text" data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" id="bigRS" value="${highSulfurContent!''}" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        <#else>
                            <input type="text"  data-placement="bottom" data-container="body" data-content="0< x <10" data-trigger="manual" class="inputStyle" id="bigRS" style="ime-mode:disabled" maxlength="4" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="return filterRS(event);"/>
                        </#if>
                        <button style="margin-left: 40px; margin-right: 10px; color:#ffffff; width: 120px; background-color:#317ee6"; class="btn" type="button" id="btnSearch">搜索</button>
                    </div>
                </div>
            </div>
        <#--<div id="filter">-->
        <#--<div class="fore1">-->
        <#--<dl class="order"><dt>排序：</dt>-->
        <#--<dd><a id="createTime" href="javascript:void(0);" onclick="searchByOrderBy(1);">创建时间</a><b></b></dd>-->
        <#--<dd><a id="ncvNum" href="javascript:void(0);" onclick="searchByOrderBy(2);">酒精度数</a><b></b></dd>-->
        <#--<dd><a id="rsNum" href="javascript:void(0);" onclick="searchByOrderBy(3);">含糖量</a><b></b></dd>-->
        <#--</dl>-->
        <#--<input type="hidden" id="orderByColumn" value="${orderByColumn}">-->
        <#--</div>-->
        <#--</div>-->
            <div class="row marginSpace">
                <div class="market-main">
                    <table class="table myTable">
                        <thead>
                        <tr style="border-top: 2px solid #007DE4;">
                            <th>需求编号</th>
                            <th>酒类</th>
                            <th>酒精度数</th>
                            <th>含糖量</th>
                            <th>提货地</th>
                            <th>提货时间</th>
                            <th>发布时间</th>
                            <th>报价截止日</th>
                            <th>交易状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if demandList ??>
                                <#list demandList as demand>
                                    <#assign deliveryprovince = demand.deliveryprovince/>
                                    <#assign deliveryplace = demand.deliveryplace/>
                                    <#assign otherplace = demand.otherplace/>

                                <tr onclick='showId(${demand.id?c})'>
                                    <#if (demand.tradername)?length gt 0>
                                        <td ><a href="javascript:void(0);" title="交易员:${demand.tradername}" class="example" data-placement="right" data-content="联系电话:${demand.traderphone}"><span style="background-color: #007DE4;color: #ffffff;">${demand.demandcode !''}</span></a></td>
                                    <#else>
                                        <td ><a href="javascript:void(0);"><span style="background-color: #007DE4;color: #ffffff;">${demand.demandcode !''}</span></a></td>
                                    </#if>
                                    <td>${demand.coaltype}</td>
                                    <td>${demand.NCV?c!''}</td>
                                    <td>${demand.RS?string('0.00')!''}%</td>
                                    <#if demand.deliveryplace == "其它">
                                        <td>${deliveryprovince +' '+ otherplace}</td>
                                    <#else>
                                        <td>${deliveryprovince +' '+ deliveryplace}</td>
                                    </#if>
                                    <#if demand.deliverymode == "场地自提">
                                        <td>${demand.deliverydatestart !''} ~ ${demand.deliverydateend !''}</td>
                                    <#else>
                                        <td>${demand.deliverydate}</td>
                                    </#if>
                                    <td><@period value=demand.releasetime/></td>
                                    <#if (demand.tradestatus) == "报价结束">
                                        <td class="img_back">${demand.quoteenddate}</td>
                                    <#else>
                                        <td>${demand.quoteenddate}</td>
                                    </#if>
                                    <td>已有<span style="color:red;font-weight: bold;">${demand.quotenum}</span>家报价</td>
                                    <#if (demand.tradestatus) == "报价结束">
                                        <td class="tablebtn"><a class="disable" style="background-color:lightgray;" href="javascript:void(0);">报价</a></td>
                                    <#else>
                                        <td class="tablebtn"><a href="javascript:void(0);")'>报价</a></td>
                                    </#if>
                                </tr>
                                </#list>
                            <#else >
                            <tr><td colspan='9' style=' font-size: 18px;text-align: center;'>没有搜索到您想要的信息,您可以<a style="color: #317ee6;" href="/sell/createSupply" style="color: #317ee6;"> 发布供应 </a>或者<a style="color: #317ee6;" href="/manualsell_out" style="color: #317ee6;"> 委托人工销售 </a>!</td></tr>
                            </#if>
                        </tbody>
                    </table>
                    <input type="hidden" value="" id="demandId">
                    <@pager path="/sell" params={"coaltype":coaltype,"area":area,"province":province,"port":port,"lowHotValue":lowHotValue,"highHotValue":highHotValue,"lowSulfurContent":lowSulfurContent,"highSulfurContent":highSulfurContent}  page=pageNumber pagesize=pagesize  count=totalCount  class="pager"/>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="modal-companyInfo-quote" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
        <div class="modal-dialog" style="width: 300px;height: 300px;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">
                        温馨提示:
                    </h4>
                </div>
                <div class="modal-body">
                    <span>您的公司信息不完整或者未通过审核，不能报价!</span>
                </div>
                <div class="modal-footer">
                    <button id="close_companyInfo_quote" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    </@block>

    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/sell.js')}"></script>
    </@block>
</@extend>