<@extend name="layout">
    <@block name="cs">
        <style type="text/css">
            .topNews {padding-left: 10px;padding-right: 10px;}
            .topNews a:hover {color:#317ee6;}
            .newsLink {color: #808080;}
            .articleTitle {font-weight: 600; font-size: 18px; color: #000;}
            .space4article { margin-bottom: 25px;}
            .articleContent {margin-top: 10px; font-size: 14px; line-height: 1.5em}
            .page-header h3 {display: inline;}
            .moreArticle {font-size:14px; float:right;margin-right:20px; margin-top:15px;}
            .list_lh{ height: 400px; overflow: hidden; color: #000000; font-size: 12px;}
            .list_lh tr td {height: 38px;}
            .betaInfo {margin-top: 20px;}
            .betaDay {background-color: #317ee6; color: #ffffff; text-align: center; padding: 10px; margin: 0px;}
            .betaDay h3 {font-size:20px; margin: 0px;}
            .betaDay span {font-size: 30px;}
            .totalAmount{border: #DDD solid 1px; margin: 0px; padding-left: 20px;}
            .totalAmount span {color: #F00 ; font-size: 20px;}
        </style>
    </@block>
    <@block name="body">

    <div id="menu1">
        <a href="/getArticleList?path=/区域行情" target="_blank"><p style="background:url('/images/hangqing.png') no-repeat 50px;"><span>区域行情</span></p></a>
        <ul>
        <#if regionalNewsList ??>
            <#list regionalNewsList as article>
            <li class="topNews">
                <a href="/getArticle?id=${article.id}" target="_blank">
                    <#if article.title?length lt 26> ${article.title!"无"?html}
                    <#else> ${article.title[0..24]!"无"?html}...</#if>
                </a>
            </li>
            </#list>
        </#if>
        </ul>
    </div>

    <#--图片轮播begin-->
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list indexBannerList as indexbanner>
                <#if indexbanner_index == 0>
                    <li data-target="#carousel-example-generic" data-slide-to="${indexbanner_index}" class="active"></li>
                <#else>
                    <li data-target="#carousel-example-generic" data-slide-to="${indexbanner_index}"></li>
                </#if>
            </#list>
        </ol>

        <div class="carousel-inner" role="listbox">
            <#if indexBannerList?exists>
                <#list indexBannerList as indexbanner>
                    <#if indexbanner_index == 0>
                        <div class="item active">
                            <div style="width:100%;height:440px;background:url('${indexbanner.path}') no-repeat top center;"></div>
                        </div>
                    <#else>
                        <div class="item">
                            <div style="width:100%;height:440px;background:url('${indexbanner.path}') no-repeat top center;"></div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <div class="item active">
                    <div style="width:100%;height:440px;"></div>
                </div>
            </#if>

        </div>

        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <#--图片轮播end-->
    <div class="container">
        <div class="row">
            <div class="col-xs-9 col-md-9">
                <div class="table-box">
                    <div class="table-t">
                        <h3><span class="icon reco"></span>推荐产品
                            <small>Recommend Products</small>
                        </h3>
                        <#--<span class="more"><a href="#">查看更多 <span class="caret"></span></a></span>-->
                    </div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>产品编号</th>
                            <th>热值</th>
                            <th>硫份</th>
                            <th>交货地</th>
                            <th>价格</th>
                            <th>交货时间</th>
                            <th>购买</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list sellInfoList as good>
                            <tr onclick="openNewPage('${good.id}','${good.seller}');">
                                <td style="text-align:left;padding-left: 25px;"><a href="javascript:void(0);" title="交易员:${good.dealername}" class="example" data-placement="right" data-content="联系电话:${good.dealerphone}"><span style="background-color: #007DE4;color:#ffffff;">${good.pid!''}</span></a></td>
                                <td>${good.NCV?c!''}</td>
                                <td>${good.RS?string(",##0.0#")!''}%</td>
                                <#if good.deliveryplace=="其它">
                                    <td>${good.otherharbour!''}</td>
                                <#else>
                                    <td>${good.deliveryplace!''}</td>
                                </#if>
                                <#if good.ykj==0>
                                    <td style="color:red;" class="a_btn">${good.jtjlast?c!''}</td>
                                <#else>
                                    <td style="color:red;" class="a_btn">${good.ykj?c!''}</td>
                                </#if>
                                <td>${good.deliverytime1!''}—${good.deliverytime2!''}</td>
                                <td class="tablebtn"><a href="javascript:void(0);" class="example">购买</a></td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

                <div class="news">
                    <div class="page-header">
                        <h3>环渤海动力煤价格参考</h3>
                        <span class="moreArticle"><a href="/getArticleList?path=/BSPI" target="_blank">查看更多</a></span>
                    </div>

                    <!--chart1 BSPI指数-->
                    <div class="chart-main1 col-xs-5 col-md-5">
                        <div id="chartContainer1" style="min-width: 250px; width:280px; height: 150px; margin: 0 auto"></div>
                    </div>

                    <div class="desc col-xs-7 col-md-7">

                        <!-- list articles-->
                        <#if firstArticleList ??>
                            <#list firstArticleList as article>
                                <div class="articles2 space4article">
                                    <#if article.id ??>
                                        <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                    <#else>
                                        <a href="#" class="newsLink">
                                    </#if>
                                            <span class="articleTitle">
                                            <#if article.title?length lt 23>
                                                ${article.title!"无"?html}
                                            <#else>
                                                ${article.title[0..21]!"无"?html}...
                                            </#if>
                                            </span>
                                        </a>
                                    <div class="articleContent">
                                        <#if article.id ??>
                                            <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                        <#else>
                                            <a href="#" class="newsLink">
                                        </#if>
                                                <#if article.content?length lt 60>
                                                    ${article.content!}
                                                <#else>
                                                    ${article.content[0..57]}...
                                                </#if>
                                            </a>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>
                <div class="news">
                    <div class="page-header">
                        <h3>动力煤国内到岸价格参考</h3>
                        <span class="moreArticle"><a href="/getArticleList?path=/API8" target="_blank">查看更多</a></span>
                    </div>
                    
                    <!--chart2 API8指数-->
                    <div class="chart-main1 col-xs-5 col-md-5">
                        <div id="chartContainer2" style="min-width: 250px; width:280px; height: 150px; margin: 0 auto"></div>
                    </div>
                    <div class="desc col-xs-7 col-md-7">
                        <!-- list articles-->
                        <#if secondArticleList ??>
                            <#list secondArticleList as article>
                                <div class="articles2 space4article">
                                    <#if article.id ??>
                                        <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                    <#else>
                                        <a href="#" class="newsLink">
                                    </#if>
                                    <span class="articleTitle">
                                        <#if article.title?length lt 23>
                                            ${article.title!"无"?html}
                                        <#else>
                                            ${article.title[0..21]!"无"?html}...
                                        </#if>
                                    </span>
                                </a>
                                    <div class="articleContent">
                                        <#if article.id ??>
                                            <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                        <#else>
                                            <a href="#" class="newsLink">
                                        </#if>
                                            <#if article.content?length lt 60>
                                                ${article.content!}
                                            <#else>
                                                ${article.content?trim[0..57]}...
                                            </#if>
                                            </a>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                </div>
                <div class="news">
                    <div class="page-header">
                        <h3>TC主力合约</h3>
                        <span class="moreArticle"><a href="/getArticleList?path=/TC主力合约" target="_blank">查看更多</a></span>
                    </div>
                    
                    <!--chart3 TC1505指数-->
                    <div class="chart-main1 col-xs-5 col-md-5">
                        <div id="chartContainer3" style="min-width: 250px; width:280px; height: 150px; margin: 0 auto"></div>
                    </div>
                    <div class="desc col-xs-7 col-md-7">
                        <!-- list articles-->
                        <#if thirdArticleList ??>
                            <#list thirdArticleList as article>
                                <div class="articles2 space4article">
                                    <#if article.id ??>
                                    <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                    <#else>
                                    <a href="#" class="newsLink">
                                    </#if>
                                    <span class="articleTitle">${article.title!}</span>
                                </a>
                                    <div class="articleContent">
                                        <#if article.id ??>
                                        <a href="/getArticle?id=${article.id!}" target="_blank" class="newsLink">
                                        <#else>
                                        <a href="#" class="newsLink">
                                        </#if>
                                        <#if article.content?length lt 60>
                                            <@markdown value=article.content/>
                                        <#else>
                                            <@markdown value=article.content[0..57]+'...'/>
                                        </#if>
                                    </a>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                </div>
            </div>

            <div class="col-xs-3 col-md-3" style="margin-top: 50px;">


                <div class="buy">
                    <a href="/manualsell_in"><span class="glyphicon glyphicon-user"></span>委托人工买卖 &gt;</a>
                </div>

                <div class="betaInfo">
                    <div class="betaDay">
                        <h3>公测 &nbsp;<span>${onlineDays!}</span>&nbsp; 日</h3>
                    </div>
                    <div class="totalAmount">
                        <h6>累计成交：<span>${totalAmount!}</span> 瓶</h6>
                        <h6>交易额：<span>${totalMoney!}</span> 元</h6>
                    </div>
                </div>

                <div class="notes">
                    <h4>成交记录<br>
                        <small>Transaction Records</small>
                    </h4>
                    <table class="table" style="font-size: 12px;">
                        <thead>
                            <tr>
                                <th>产品</th>
                                <th style="padding-left: 20px;">数量</th>
                                <th>交货地</th>
                                <th>时间</th>
                            </tr>
                        </thead>
                    </table>
                    <div class="list_lh">
                        <table class="table" style="font-size: 12px;">
                            <tbody>
                                <#list orderTransactionList as order>
                                    <tr onclick="openNewPage('${order.sellinfoid}','${order.seller}');">
                                        <td>${order.pname}</td>
                                        <td>${order.amount?c}</td>
                                        <td>${order.harbour}</td>
                                        <td>${order.time}</td>
                                    </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                <div class="desire" style="margin-top: 33px;">
                    <hr/>
                    <div class="title">郑重承诺</div>
                    <hr/>
                    <div class="clear"></div>
                    <div class="desire-c"><img src="images/pic_8.jpg"> 100%资金安全</div>
                    <div class="desire-c"><img src="images/pic_9.jpg"> 货物质量有保障</div>
                    <div class="desire-c"><img src="images/pic_10.jpg"> 最专业的行业资讯</div>
                </div>
            </div>
            </div>
        </div>
        <div class="page-header">
            <h3><span class="glyphicon glyphicon-user"></span>合作伙伴</h3>
        <#--<span class="more"><a href="#">查看更多 <span class="caret"></span></a></span>-->
        </div>

        <div class="row">
            <div class="col-xs-3">
                <a href="http://www.ccsoln.com/" target="_blank"><img style="border:1px solid #eee;" src="/images/bankofRMT.png" alt="瑞茂通"></a>
            </div>
            <div class="col-xs-3">
                <a href="http://www.chd.com.cn/" target="_blank"><img style="border:1px solid #eee;" src="/images/bankofHD.png" alt="华电集团"></a>
            </div>
            <div class="col-xs-3">
                <a href="http://www.gzpgroup.com/" target="_blank"><img style="border:1px solid #eee;" src="/images/bankofGZ.png" alt="广州港集团"></a>
            </div>
            <div class="col-xs-3">
                <a href="http://www.railip.com/" target="_blank"><img style="border:1px solid #eee;" src="/images/zryq.jpg" alt="中瑞园区(集团)"></a>
            </div>
        </div>
    </div>
    </@block>
    <@block name="script">
    <script type="text/javascript">
        var BSPIxAxisData1 = ${BSPIdate!0};
        var BSPIseriesData1 = ${BSPInum!0};
        var API8xAxisData2 = ${API8date!0};
        var API8seriesData2 = ${API8num!0};
        var TC1505xAxisData3 = ${TC1505date!0};
        var TC1505seriesData3 = ${TC1505num!0};
        var minMaxLimited = ${chartConfine!0};
    </script>
    <script type="text/javascript" src="${static('/scripts/welcome.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/buy.js')}"></script>
    </@block>
</@extend>





































