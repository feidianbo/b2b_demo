<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        dl dd{
            text-indent: 2em;
            line-height: 36px;
        }
        dl{
            padding-left: 50px;
            padding-right: 50px;
        }
        dl dt{
            margin-left: -15px;
            line-height: 36px;
        }
        dl img{
            margin-top: 20px;
            margin-bottom: 30px;
            border:1px solid #eeeeee;
        }

    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <dl>
                        <div id="first">
                            <dt><h4>1. 金融服务</h4></dt>
                            <dd>
                                目前，XX网主要有两种金融服务产品：<a href="/finance/loan">煤易贷</a>
                                和&nbsp;&nbsp;<a href="/finance/financing">快融通</a>。
                                <#--<a href="/finance/loan">煤易贷:</a>煤炭供给方通过XX网平台进行的保值代销业务中，将煤炭运至XX网指定的仓储地后，可将货权转移给XX网，XX网对其提供质押融资服务，融资比例为煤炭价值的60%—90% 。-->
                            </dd>
                            <dd>（1）“煤易贷”是XX网平台针对广大煤炭行业中上游企业货权融资而推出的高效金融服务产品。</dd>
                            <#--<dd style="text-align: center;">模式与流程</dd>-->
                            <dd><img width="820px" src="/images/footer/aboutUs/16.png"></dd>
                            <#--<dd style="text-align: center;">模式与流程</dd>-->
                            <dd>（2）“快融通”是为了满足广大客户在煤炭购销中提高资金使用效率的需求，通过XX网平台所提供的金 融工具及手段，保障客户资金回流稳定、快速。</dd>
                            <dd><img width="820px" src="/images/footer/aboutUs/17.png"></dd>
                        </div>

                        <div id="sec">
                            <dt><h4>2. XX团购</h4></dt>
                            <dd><a href="/group">XX团购</a>业务主要分两类：一类是XX网针对某一或某几个区域的客户群体需求，进行分析，形成有强代表性的客户需求，针对某一特定酒类，定期组织有针对性的团购活动；二是煤炭供给方有货物亟需销售，提出销售需求，由XX网组织用户进行团购活动。</dd>
                            <dd><img src="/images/footer/aboutUs/tg.png"></dd>
                            <dd>XX网提供团购折扣和单独购买得不到的价格优惠，最大程度让利于广大中小客户。</dd>
                        </div>

                        <div id="third">
                            <dt><h4>3. XX仓储</h4></dt>
                            <dd><a href="/storage">XX仓储</a>是XX网为客户推出的煤炭中转仓储服务，一方面为客户提供国内主要港口的实际仓储信息，客户可根据自身需求进行仓储选择；一方面XX网有自己的仓储基地，为客户提供仓储服务，客户可自行选择。</dd>
                        </div>
                    </dl>
                </div>
                <input type="hidden" id="scrollTop" name="scrollTop" value="${pos!'first'}">
            </div>
        </div>
    </div>
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/footerScroll.js')}"></script>
    </@block>
</@extend>