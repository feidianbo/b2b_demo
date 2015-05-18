<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        /* 地区,省份,港口三级列表的样式  */
       /* #areaMenu1{
            list-style: none;
        }

        .areaMenu{
            list-style: none;
            margin-top:30px;
        }

        .areaLi{
            font-size: 22px;
            color: #000000;
            margin-left: 10px;
        }

        .provinceMenuDiv{
            margin-top: 10px;
        }

        .provinceMenuUL{
            list-style: none;
            padding-left: 15px;
        }

        .clickSpan{
            font-size: 18px;
            color:#000000;
        }

        .portsDiv{
            display: none;
        }

        .portsUL{
            list-style: none;
            padding-left: 15px;
        }

        .portsUL li{
            margin-top: 10px;
            font-size: 16px;
        }

        .provinceMarginTop{
            margin-top: 10px;
        }

        #guangxiPorts{
            margin-left: 20px;
        }

        #hainanPortsLi{
            margin-left: 20px;
        }*/



        .themeLine {
            width: 95%;
            height: 1px;
            background-color: #cccccc;
            margin-top: 10px; }

        .storageThemeDiv {
            margin-left: -50px;
        }

        .storageTheme {
            font-size: 18px;
            color:#000000;
        }

        #storageIntroduction{
            margin-left: -45px;
            margin-top:20px;
        }

        #storageIntroduction ul {
            margin:0;
            padding:0;
            float:left;
        }
        #storageIntroduction li {
            margin-left:5px;
            text-align:center;
            float:left;
            list-style:none;
            margin-bottom: 30px;
        }

        #storageIntroduction img {
            margin-right: 20px;
        }

        .wordDiv{
            width:235px;
            border: solid 1px #cccccc;
        }

        .wordDiv a{
            color:#000000;
        }

        .storageName{
            margin-top: 10px;
            text-align: left;
            padding-left: 5px;
            color:#317EE6;
        }

        .storageIntroductionWord{
            margin-top: 10px;
        }

        .wordSpan{
            padding-left:80px;
            font-size: 14px;
            padding-top: 5px;
            line-height: 25px;
        }

        #secondRow{
            margin-top: 20px;
        }

        .storagePic img{
            margin-top: 30px;
            margin-left: -40px;
        }

        .nameForPic{
            margin-top:-210px;
            margin-left: 350px;
            font-size: 18px;
        }

        .introductionForPic{
            margin-top:20px;
            margin-left: 350px;
            font-size: 18px;
        }

        .introductionForPic span{
            line-height: 25px;
        }

        .storageDetail{
            margin-top:50px;
            margin-left: -50px;
        }

        .storageDetailSpan1{
            margin-left: 120px;
            color:#317EE6;
        }

        .storageDetailSpan{
            margin-left: 20px;
            color:#317EE6;
        }

        .storageDetailDiv{
            display: none;
        }

        .storageDetailDiv p{
            font-size: 14px;
            margin-left:-40px;
            line-height:23px;
        }

        #fivePortsUL li{
            margin-bottom: 18px;
            font-size: 18px;
            margin-left: 20px;
            list-style: none;
            color:#000000;
        }

        #fivePortsUL li a{
            color:#000000;
        }

        .introductionColor{
            color:#317EE6;
        }

        .firstRowWord{
            font-size: 14px;
            line-height: 25px;
        }

        .portNameColor{
            color:#000000;
        }

        .searchGoods{
            padding-left: 580px;
        }

    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-3 col-md-3 column padding_space">

                    <div>
                        <ul id="fivePortsUL">
                        <li><a href="javascript:void(0);" onclick="menuShowDetail('guangzhou')">广州港</a></li>
                        <li><a href="javascript:void(0);" onclick="menuShowDetail('fangcheng')">防城港</a></li>
                        <li><a href="javascript:void(0);" onclick="menuShowDetail('caofeidian')">曹妃甸港</a></li>
                        <li><a href="javascript:void(0);" onclick="menuShowDetail('qinhuangdao')">秦皇岛港</a></li>
                        <li><a href="javascript:void(0);" onclick="menuShowDetail('wanzhai')">万寨港</a></li>
                        </ul>
                    </div>

                    <#--<div id="storageMenuBlock">
                        <ul id="areaMenu1">
                            <li class="areaLi">东北地区
                                <div class="provinceMenuDiv">
                                    <ul class="provinceMenuUL">
                                        <li>
                                            <span class="clickSpan" onclick="expandPorts('liaoningPorts');">辽宁省<span id="liaoningPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="liaoningPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>大连港</li>
                                                    <li>葫芦岛港</li>
                                                    <li>鲅鱼圈港</li>
                                                    <li>营口港</li>
                                                    <li>盘锦港</li>
                                                    <li>锦州港</li>
                                                    <li>丹东港</li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>

                        <ul class="areaMenu">
                            <li class="areaLi">华北地区
                                <div class="provinceMenuDiv">
                                    <ul class="provinceMenuUL">
                                        <li>
                                            <span class="clickSpan" onclick="expandPorts('hebeiPorts');">河北省<span id="hebeiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="hebeiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>秦皇岛港</li>
                                                    <li>曹妃甸港</li>
                                                    <li>京唐港</li>
                                                    <li>黄骅港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('tianjinPorts');">天津市<span id="tianjinPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="tianjinPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>天津港</li>
                                                </ul>
                                            </div>
                                        </li>

                                    </ul>
                                </div>
                            </li>
                        </ul>

                        <ul class="areaMenu">
                            <li class="areaLi">华东地区
                                <div class="provinceMenuDiv">
                                    <ul class="provinceMenuUL">
                                        <li>
                                            <span class="clickSpan" onclick="expandPorts('shandongPorts');">山东省<span id="shandongPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="shandongPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>前湾港</li>
                                                    <li>日照港</li>
                                                    <li>龙口港</li>
                                                    <li>青岛港</li>
                                                    <li>东营港</li>
                                                    <li>蓬莱港</li>
                                                    <li>潍坊港</li>
                                                    <li>烟台港</li>
                                                    <li>威海港</li>
                                                    <li>岚山港</li>
                                                    <li>海洋港</li>
                                                    <li>大湾港</li>
                                                    <li>莱州港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('jiangsuPorts');">江苏省<span id="jiangsuPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="jiangsuPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>万寨港</li>
                                                    <li>邳州港</li>
                                                    <li>南通港</li>
                                                    <li>泰州港</li>
                                                    <li>镇江港</li>
                                                    <li>太仓港</li>
                                                    <li>张家港</li>
                                                    <li>连云港</li>
                                                    <li>靖江港</li>
                                                    <li>江阴港</li>
                                                    <li>射阳港</li>
                                                    <li>大丰港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('shanghaiPorts');">上海市<span id="shanghaiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="shanghaiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>罗泾港</li>
                                                    <li>乍浦港</li>
                                                    <li>洋山港</li>
                                                    <li>外高桥港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('anhuiPorts');">安徽省<span id="anhuiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="anhuiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>马鞍山港</li>
                                                    <li>芜湖港</li>
                                                    <li>铜陵港</li>
                                                    <li>安庆港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('zhejiangPorts');">浙江省<span id="zhejiangPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="zhejiangPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>北仑港</li>
                                                    <li>温州港</li>
                                                    <li>舟山港</li>
                                                    <li>台州港</li>
                                                    <li>宁海港</li>
                                                    <li>象山港</li>
                                                    <li>石浦港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('fujianPorts');">福建省<span id="fujianPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="fujianPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>莆田港(东吴)</li>
                                                    <li>可门港</li>
                                                    <li>厦门港</li>
                                                    <li>泉州港</li>
                                                    <li>福州港</li>
                                                    <li>湄洲港</li>
                                                </ul>
                                            </div>
                                        </li>

                                    </ul>
                                </div>
                            </li>
                        </ul>

                        <ul class="areaMenu">
                            <li class="areaLi">华中地区
                                <div class="provinceMenuDiv">
                                    <ul class="provinceMenuUL">
                                        <li>
                                            <span class="clickSpan" onclick="expandPorts('jiangxiPorts');">河北省<span id="jiangxiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="jiangxiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>九江港</li>
                                                    <li>丰城港</li>
                                                    <li>南昌港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('hubeiPorts');">湖北省<span id="hubeiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="hubeiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>武汉港</li>
                                                    <li>阳逻港</li>
                                                    <li>枝城港</li>
                                                    <li>荆州港</li>
                                                    <li>鄂州港</li>
                                                    <li>黄石港</li>
                                                    <li>宜昌港</li>
                                                    <li>石首港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('hunanPorts');">湖南省<span id="hunanPortsPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="hunanPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>城陵矶港</li>
                                                    <li>岳阳港</li>
                                                </ul>
                                            </div>
                                        </li>

                                    </ul>
                                </div>
                            </li>
                        </ul>

                        <ul class="areaMenu">
                            <li class="areaLi">华南地区
                                <div class="provinceMenuDiv">
                                    <ul class="provinceMenuUL">
                                        <li>
                                            <span class="clickSpan" onclick="expandPorts('guangdongPorts');">广东省<span id="guangdongPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="guangdongPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>广州港(新沙)</li>
                                                    <li>广州港(西基)</li>
                                                    <li>广州港(黄埔)</li>
                                                    <li>广州港(海昌)</li>
                                                    <li>湛江港</li>
                                                    <li>珠海港</li>
                                                    <li>惠州港</li>
                                                    <li>阳江港</li>
                                                    <li>中山港</li>
                                                    <li>江门港</li>
                                                    <li>汕头港</li>
                                                    <li>佛山港</li>
                                                    <li>海门港</li>
                                                    <li>茂名港</li>
                                                </ul>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('guangxiPorts');">广西自治区<span id="guangxiPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="guangxiPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>防城港</li>
                                                    <li>钦州港</li>
                                                    <li>北海港</li>
                                                    <li>贵港</li>
                                                    <li>南宁港</li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li id="hainanPortsLi" class="provinceMarginTop">
                                            <span class="clickSpan" onclick="expandPorts('hainanPorts');">海南省<span id="hainanPortsPortsIcon" class="glyphicon glyphicon-chevron-up" style="font-size: 11px;"></span></span>
                                            <div id="hainanPorts" class="portsDiv">
                                                <ul class="portsUL">
                                                    <li>海口港</li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                    </div>-->
                </div>
                <div class="col-xs-9 col-md-9 column" id="storageDiv">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">仓储列表</div>
                        <div class="themeLine"></div>
                    </div>

                    <div id="storageIntroduction">
                        <div>
                        <ul>
                            <li><img src="/images/guangzhou.jpg" onclick="showDetail('guangzhou')"/>
                                <div class="wordDiv" onclick="showDetail('guangzhou')"><a href="javascript:void(0);">
                                    <div class="storageName">仓储名称<span class="portNameColor">&nbsp;:&nbsp;广州港</span></div>
                                    <div class="storageIntroductionWord">
                                        <span class="introductionColor">仓储简介</span>&nbsp;:&nbsp;
                                        <span class="firstRowWord">广州港股份有限公司&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <span class="wordSpan">广州港是我国沿海的主</span>
                                        <span class="wordSpan">枢纽港，位于珠江三角</span>
                                        <span class="wordSpan">洲的中心，沿广州两....</span>
                                    </div>
                                </a>
                                </div>
                            </li>
                            <li><img src= "/images/fangcheng.jpg" onclick="showDetail('fangcheng')"/>
                                <div class="wordDiv" onclick="showDetail('fangcheng')"><a href="javascript:void(0);">
                                    <div class="storageName">仓储名称<span class="portNameColor">&nbsp;:&nbsp;防城港</span></div>
                                    <div class="storageIntroductionWord">
                                        <span class="introductionColor">仓储简介</span>&nbsp;:&nbsp;
                                        <span class="firstRowWord">防城港务集团有限公司</span>
                                        <span class="wordSpan">防城港位于广西南部北</span>
                                        <span class="wordSpan">部湾北岸西端，是中国</span>
                                        <span class="wordSpan">沿海24个主要港口之...</span>
                                    </div>
                                </a>
                                </div>
                            </li>
                            <li> <img src= "/images/caofeidian.jpg" onclick="showDetail('caofeidian')"/>
                                <div class="wordDiv" onclick="showDetail('caofeidian')"><a href="javascript:void(0);">
                                    <div class="storageName">仓储名称<span class="portNameColor">&nbsp;:&nbsp;曹妃甸港</span></div>
                                    <div class="storageIntroductionWord">
                                        <span class="introductionColor">仓储简介</span>&nbsp;:&nbsp;
                                        <span class="firstRowWord">曹妃甸实业港务有限公</span>
                                        <span class="wordSpan">司（矿石码头）矿石码</span>
                                        <span class="wordSpan">是由唐山唐山曹妃甸实</span>
                                        <span class="wordSpan">业港务有限公司投资....</span>
                                    </div>
                                </a>
                                </div>
                            </li>

                        </ul>
                        </div>
                        <div>
                            <ul id="secondRow">
                                <li> <img src= "/images/qinhuangdao.jpg" onclick="showDetail('qinhuangdao')"/>
                                    <div class="wordDiv" onclick="showDetail('qinhuangdao')"><a href="javascript:void(0);">
                                        <div class="storageName">仓储名称<span class="portNameColor">&nbsp;:&nbsp;秦皇岛港</span></div>
                                        <div class="storageIntroductionWord">
                                            <span class="introductionColor">仓储简介</span>&nbsp;:&nbsp;
                                            <span class="firstRowWord">秦皇岛港股份有限公司</span>
                                            <span class="wordSpan">为全球最大的公众大宗</span>
                                            <span class="wordSpan">干散货码头运营商，同</span>
                                            <span class="wordSpan">时集团经营的秦皇岛...</span>
                                        </div>
                                    </a>
                                    </div>
                                </li>
                                <li><img src= "/images/wanzhai.jpg" onclick="showDetail('wanzhai')"/>
                                    <div class="wordDiv" onclick="showDetail('wanzhai')"><a href="javascript:void(0);">
                                        <div class="storageName">仓储名称<span class="portNameColor">&nbsp;:&nbsp;万寨港</span></div>
                                        <div class="storageIntroductionWord">
                                            <span class="introductionColor">仓储简介</span>&nbsp;:&nbsp;
                                            <span class="firstRowWord">万寨港是江苏徐州港务</span>
                                            <span class="wordSpan">(集团)有限公司所属,京</span>
                                            <span class="wordSpan">杭运河上规模最大的煤</span>
                                            <span class="wordSpan">炭专业化港口,被誉为...</span>
                                        </div>
                                    </a>
                                    </div>
                                </li>

                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-xs-9 col-md-9 column storageDetailDiv" id="guangzhouDetail">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">
                            <span>广州港</span>
                            <span class="searchGoods"><p class="glyphicon glyphicon-hand-right" style="color:red;"></p><a href="/buy?region=4&province=16&harbour=87&page=1">&nbsp;&nbsp;&nbsp;查找商品</a></span>
                        </div>
                        <div class="themeLine"></div>
                    </div>

                    <div class="storagePic">
                        <img src="/images/guangzhou2.jpg">
                        <div class="nameForPic">仓储名称&nbsp;:&nbsp;广州港</div>
                        <div class="introductionForPic">仓储简介&nbsp;:&nbsp;
                            <span style="font-size: 14px;">广州港是我国沿海的主枢纽港，位于珠江三角洲的中心，沿广州珠江两岸至出海口依次分布着内港、黄埔、新沙、南沙四大港区。
            截至2013年12月31日，广州港股份有限公司共有运营泊位64个（另有配套驳船泊位54个），其中集装箱专用泊位19个、煤炭专用泊位4个，
            油品及液体化工专用泊位7个，其他散杂货泊位34个，为珠三角、广东乃至华南地区的经济发展提供高效完善的港口物流服务。广州港股份有限公司与马士基、
            地中海、法国达飞、中国远洋、中国海运等全球知名航运企业建立了
                            </span>
                        </div>
                    </div>
                    <#--下半部分文字介绍-->
                    <p>良好的合作关系，航线通达全球80多个国家和地区的350多个港口，成为全球物流链中重要的一环。
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;近年来，广州港股份有限公司积极实施“走出去”战略，在潮州成立合资公司，合作建设经营码头，并先后在长沙、昆明、衡阳等内陆城市建设无水港。同时，公司不断加强港口能力建设资金的投入，
                    到2016年，实现货物吞吐量超过4亿瓶，集装箱吞吐量超过1,600万TEU；到2020年，实现货物吞吐量超过4.9亿瓶，集装箱吞吐量2,000万TEU。增强港口物流功能和内陆货源开拓能力，提高服务质量和环境质量，
                    成为具有较强国际竞争力和发展活力的港口龙头企业及现代物流服务企业。
                    </p>

                    <div class="storageDetail">
                        <div>
                            <span class="storageTheme">当前仓储地区&nbsp;:</span>
                            <span class="storageDetailSpan1">堆位明细</span>
                            <span class="storageDetailSpan">费用明细</span>
                            <span class="storageDetailSpan">码头明细</span>
                            <span class="storageDetailSpan">装卸平台明细</span>
                            <span class="storageDetailSpan">联系方式</span>
                        </div>
                        <div class="themeLine"></div>
                    </div>
                </div>

                <div class="col-xs-9 col-md-9 column storageDetailDiv" id="fangchengDetail">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">
                            <span>防城港</span>
                            <span class="searchGoods"><p class="glyphicon glyphicon-hand-right" style="color:red;"></p><a href="/buy?region=4&province=17&harbour=102&page=1">&nbsp;&nbsp;&nbsp;查找商品</a></span>
                        </div>
                        <div class="themeLine"></div>
                    </div>

                    <div class="storagePic">
                        <img src="/images/fangcheng2.jpg">
                        <div class="nameForPic">仓储名称&nbsp;:&nbsp;防城港</div>
                        <div class="introductionForPic">仓储简介&nbsp;:&nbsp;
                            <span style="font-size: 14px;">防城港位于广西南部北部湾北岸西端，是中国沿海24个主要港口之一。
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;防城港北靠云、贵、川，东邻粤、琼、港、澳，西接越南，南濒北部湾，地处泛珠三角经济圈、西南经济圈与东盟经济圈的结合部，是中国内陆腹地进入中南半岛东盟国家最便捷的出海门户，也是中国通往西亚、欧洲、非洲、大洋洲海上运距最短的港口。
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;防城港港湾水深浪静，三面环山犹如内陆湖泊，航道短且不
                            </span>
                        </div>
                    </div>
                    <#--下半部分文字介绍-->
                <p>淤积，水域、陆域宽阔，可利用岸线长。港口交通便利，陆路交通有高速公路和铁路与全国干线连网，海路与100多个国家和地区的250多个港口通航。
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;港口始建于1968年3月22日， 1983年7月国务院批准对外开放，1986年完成一期工程建设，1987年全面投入营运。
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;防城港现拥有泊位41个，其中生产性泊位37个，万瓶级以上深水泊位26个，泊位最大靠泊能力为20万瓶级。靠泊的最大船舶是2010年2月到港的“河北宏图”号货轮，载重瓶位为28.1万瓶。码头库场面积超400万平方米，年实际通过能力超过8000万瓶，其中集装箱通过能力55万TEU。防城港是全国沿海港口装卸货种最齐全的港口之一，交通运输部列入统计口径的16类货种在防城港都有作业。拥有4个15万瓶级深水泊位和1个20万瓶级深水泊位，是目前华南沿海惟一可同时接卸5艘满载的好望角型船舶的港口。
                    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;防城港开发前景十分广阔，规划的建港岸线长50.3公里，可规划建设深水泊位200多个。防城港目前正在同时建设5～20万瓶级等各类泊位10个，建成后港口通过能力将达1亿瓶，形成集装箱、铁矿石、煤炭、有色金属矿、硫磺、粮食、液体化工产品、磷肥、非金属矿等九大专业化物流系统。
                </p>
                    <div class="storageDetail">
                        <div>
                            <span class="storageTheme">当前仓储地区&nbsp;:</span>
                            <span class="storageDetailSpan1">堆位明细</span>
                            <span class="storageDetailSpan">费用明细</span>
                            <span class="storageDetailSpan">码头明细</span>
                            <span class="storageDetailSpan">装卸平台明细</span>
                            <span class="storageDetailSpan">联系方式</span>
                        </div>
                        <div class="themeLine"></div>
                    </div>
                </div>

                <div class="col-xs-9 col-md-9 column storageDetailDiv" id="caofeidianDetail">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">
                            <span>曹妃甸港</span>
                            <span class="searchGoods"><p class="glyphicon glyphicon-hand-right" style="color:red;"></p><a href="/buy?region=1&province=5&harbour=20&page=1">&nbsp;&nbsp;&nbsp;查找商品</a></span>
                        </div>
                        <div class="themeLine"></div>
                    </div>

                    <div class="storagePic">
                        <img src="/images/caofeidian2.jpg">
                        <div class="nameForPic">仓储名称&nbsp;:&nbsp;曹妃甸港</div>
                        <div class="introductionForPic">仓储简介&nbsp;:&nbsp;
                            <span style="font-size: 14px;">
            矿石码头是由唐山唐山曹妃甸实业港务有限公司投资建设。 矿石码头位于曹妃甸港区，紧贴渤海湾深槽，港址西距天津新港38海里，
            东北距秦皇岛港92海里，距京唐港33海里。建成后的码头前沿不需要挖泥自然水深就达-25米，可以停靠25万瓶级的远洋巨轮，
            是整个环渤海1000多公里海岸线少有的天然深水港址。 码头及配套设施建设从2004年4月25日起在海上吹填造陆，历时19个月，
            于2005年12月1日完工，提前一个月实现了省委、省政府确定的总体工期目标。其中，用13个月建成2座25万瓶
                            </span>
                        </div>
                    </div>
                <#--下半部分文字介绍-->
                    <p>级矿石泊位；用18个月完成全部设备系统的制造、安装和调试；用4个月建成一座110kv变电站和14km跨海的110kv高压输电线路，并实现并网送电。 矿石码头从2005年12月1日开始重载试车，12月16日正式开航，由建设期转入试运营。经港航管理部门批准，公司试运营期到2006年5月31日止。试运营期间船舶靠离码头只能在白天进行。 公司矿石码头采用高桩梁板式结构，总长808米，其中靠泊岸线长度735米，码头前沿停泊水域底标高-25米。码头堆场区有效长度1481米，有效宽度399米，
                        有效堆存面积58万平方米，共布置6条堆存场地，堆存能力 500万瓶，年可接卸矿石3000万瓶。 码头装卸系统配置6台桥式卸船机，单击卸船能力2500瓶/小时；码头至堆场布置两条卸船线，每条线最大运输能力为7500瓶/小时。堆场共布置5条皮带机输送线；装备1台堆料机、2台取料机、2台堆取料机，可同时进行4条取料线作业。堆场内布设有汽车装车线两条，装车能力2500瓶/小时，火车装车线2条，通往精品钢铁基地皮带机输送线一条。
                        5-10万瓶级散杂货码头，于2007年8月8日投入运营，位于矿石码头西北方向。码头总长525米，水深14米，设计年吞吐量350万瓶，堆场面积26万平方米，装卸系统配备8台40-38米门机。 首钢搬迁曹妃甸后，依托曹妃甸这个深水良港，25万瓶矿石巨轮可自由出入，不用卸载，矿石可通过传送带直接炼铁，每瓶钢的成本下降了200元。而且矿石码头的建设每年可为国家节省运输费用5-10亿元，对充分利用国外资源，促进我国钢铁工业的发展，具有非常重要的现实意义和长远的发展意义。
                    </p>

                    <div class="storageDetail">
                        <div>
                            <span class="storageTheme">当前仓储地区&nbsp;:</span>
                            <span class="storageDetailSpan1">堆位明细</span>
                            <span class="storageDetailSpan">费用明细</span>
                            <span class="storageDetailSpan">码头明细</span>
                            <span class="storageDetailSpan">装卸平台明细</span>
                            <span class="storageDetailSpan">联系方式</span>
                        </div>
                        <div class="themeLine"></div>
                    </div>
                </div>

                <div class="col-xs-9 col-md-9 column storageDetailDiv" id="qinhuangdaoDetail">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">
                            <span>秦皇岛港</span>
                            <span class="searchGoods"><p class="glyphicon glyphicon-hand-right" style="color:red;"></p><a href="/buy?region=1&province=5&harbour=19&page=1">&nbsp;&nbsp;&nbsp;查找商品</a></span>
                        </div>
                        <div class="themeLine"></div>
                    </div>

                    <div class="storagePic">
                        <img src="/images/qinhuangdao2.jpg">
                        <div class="nameForPic">仓储名称&nbsp;:&nbsp;秦皇岛港</div>
                        <div class="introductionForPic">仓储简介&nbsp;:&nbsp;
                            <span style="font-size: 14px;">
        为全球最大的公众大宗干散货码头运营商，同时集团经营的秦皇岛港是全球最大的煤炭港，占2012年中国沿海主要港口煤炭下水总量约31.9%。集团同时是环渤海地区最重要的矿石码头运营商之一。
        集团通过加大硬件设施投入不断提高港口通过能力，为客户提供优质高效的综合港口服务，包括货物装卸、堆存、仓储、运输及物流等业务，经营货种包括煤炭、金属矿石、油品及液体化工、集装箱及其他杂货。
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;集团及联营公司运营的秦皇岛港、曹妃甸港码头以及黄骅港
                            </span>
                        </div>
                    </div>
                <#--下半部分文字介绍-->
                    <p>码头，均位于中国北方的环渤海地区。其中，秦皇岛港是具有百年经营历史的大港；曹妃甸港及黄骅港是环渤海地区的新兴港口，正处于快速发展阶段，扩大了集团的腹地覆盖范围，有望成为集团未来业务增长的主要动力。集团及联营公司现运营62个泊位。依托丰富的港口运营经验以及环渤海地区港口资源的成功整合，相信集团将持续受益于中国经济，特别是环渤海地区经济的高速增长。
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;秦皇岛港是中国内贸煤炭运输大通道的最重要枢纽港，在中国煤炭运输体系中具有举足轻重的地位。中国的煤炭资源主要蕴藏于内蒙古、山西及陕西等西北地区，而国内煤炭消费需求主要来自中国东南沿海发达地区。西北地区生产的煤炭首先经铁路「西煤东运」，从产地运至东部沿海港口，然后经海运「北煤南运」，到达南方地区。大秦线是「西煤东运」运输大通道中最重要的铁路线。秦皇岛港位于大秦线东端，铁路线直达港口，占据战略性地理位置，是大秦线运输煤炭的主要中转港。此外，秦皇岛港的港口自然和地理条件优越，为天然深水良港，不冻不淤，并在陆上与多条国道相连。
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;集团为客户提供一体化的综合码头服务，有效地满足客户的各种运输需求。通过提供配煤服务和海运煤炭交易平台等增值服务，进一步提高煤炭运输的一体化综合服务能力。秦皇岛煤炭市场建立了一个以煤炭交易服务为核心，集煤炭信息服务及物流服务为一体的功能完善的综合性交易服务平台和煤炭物流链管理服务系统。德鲁里的统计数据显示，秦皇岛煤炭市场发布的环渤海动力煤价格指数，已经成为国内煤炭重点订货和煤炭贸易的定价依据，正在成为国际煤炭贸易的重要参考依据。秦皇岛煤炭市场开发的煤炭现货交易服务平台是国内沿海港口下水煤炭最重要的交易平台，为未来实现煤炭现货交易与中远期交易及相关衍生品交易的有机整合，实现期现结合的市场模式奠定了坚实基础。依托丰富的港口运营经验以及环渤海地区港口资源的成功整合，集团相信将持续受益于中国经济，特别是环渤海地区经济的高速增长。

                    </p>

                    <div class="storageDetail">
                        <div>
                            <span class="storageTheme">当前仓储地区&nbsp;:</span>
                            <span class="storageDetailSpan1">堆位明细</span>
                            <span class="storageDetailSpan">费用明细</span>
                            <span class="storageDetailSpan">码头明细</span>
                            <span class="storageDetailSpan">装卸平台明细</span>
                            <span class="storageDetailSpan">联系方式</span>
                        </div>
                        <div class="themeLine"></div>
                    </div>
                </div>

                <div class="col-xs-9 col-md-9 column storageDetailDiv" id="wanzhaiDetail">
                    <div class="storageThemeDiv">
                        <div class="storageTheme">
                            <span>万寨港</span>
                            <span class="searchGoods"><p class="glyphicon glyphicon-hand-right" style="color:red;"></p><a href="/buy?region=2&province=8&harbour=37&page=1">&nbsp;&nbsp;&nbsp;查找商品</a></span>
                        </div>
                        <div class="themeLine"></div>
                    </div>

                    <div class="storagePic">
                        <img src="/images/wanzhai2.jpg">
                        <div class="nameForPic">仓储名称&nbsp;:&nbsp;万寨港</div>
                        <div class="introductionForPic">仓储简介&nbsp;:&nbsp;
                            <span style="font-size: 14px;">万寨港是江苏徐州港务（集团）有限公司所属、京杭运河上规模最大的煤炭专业化港口，被誉为“京杭运河第一港”，被交通部列为全国内河最大的煤炭输出港。
                                港口位于淮海经济区中心城市徐州市，经济腹地宽广，枢纽地位突出。港口主要承担国家煤炭应急储备、西煤东输、北煤南运重任，承接来自晋、陕、豫、皖、鲁、冀、甘、宁、青、蒙等省份的煤炭，其中包括电煤、烟煤、无烟煤、块煤、精煤、焦煤等多种品类煤炭，经此中转至江、浙、沪、皖等地区。
                    <br>
                            </span>
                        </div>
                    </div>

                <#--下半部分文字介绍-->
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;万寨港于公元1990年一月建成并投入使用，年吞吐能力达到1500万瓶。港口目前拥有铁路专用线22.9公里，装卸作业线 6.65 公里，铁路日接卸量可达500余节。港口总占地面积96.8万平方米，其中陆域面积81万平方米，水域面积 15.8 万平方米，码头岸线长 1360米，拥有码头泊位10座，2000瓶级、1000瓶级各为5座。港口目前生产运行基本实现了自动化和机械化控制，综合运输成本较低，全面实现铁路、水路、公路的进发衔接、多式联运。
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;万寨港在多年发展过程中，不断加强与煤炭、铁路、电力、冶金、建材、化工和航运等行业所属企业的业务联系，成为以产、运、销为核心的煤炭物流产业链的重要节点，发展为华东地区最重要的集中转、配送、交易、信息、代购代销、代储代运为一体的大型现代化煤炭集散地，并且形成了独具港口特色的“十大优势”。
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;近年来，随着改革开放的进一步发展，港口扩大了经营范畴，先后于2010年上半年开辟了火车发运、来港卸船的新业务，有效满足了客户的需要，使港口在现代化港口发展进程中又上了一个新的台阶。在抓好生产经营工作的同时，港口还进一步加强对外合作和招商引资步伐，先后建立了多家股份制煤炭贸易公司、煤炭洗选加工有限公司，积极引进物流金融业务，构建了港口煤炭物流一体化服务平台，从而推动了港口向着华东地区最大的煤炭物流基地、储备基地和现代化集散分拨中心港发展前进。
                    </p>
                    <div class="storageDetail">
                        <div>
                            <span class="storageTheme">当前仓储地区&nbsp;:</span>
                            <span class="storageDetailSpan1">堆位明细</span>
                            <span class="storageDetailSpan">费用明细</span>
                            <span class="storageDetailSpan">码头明细</span>
                            <span class="storageDetailSpan">装卸平台明细</span>
                            <span class="storageDetailSpan">联系方式</span>
                        </div>
                        <div class="themeLine"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </@block>

    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/storage.js')}"></script>
    </@block>
</@extend>