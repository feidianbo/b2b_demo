<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .theme{
            border-left:4px solid red;
            font-size: 16px;
        }
        .tabcolor{
            color: #007DE4;
        }
        .tablepass{
            color: #d58512;
        }
        .tablein{
            color:green;
        }
        .tablenopass{
            color:#ff0000;
        }
        .tablecontinue{
            color:#3dc240;
        }
        .tableMatching{
            color: #78FA89;
        }
        .tableQoute{
            color: darkorchid;
        }
        .tableEnd{
            color: #770000;
        }
        .table{
            border: 1px solid #f5f5f5;
        }
        .table thead{
            background-color:#f5f5f5;
        }

        .table tbody tr{
            border-bottom: 1px solid #f5f5f5;
        }
        .padding_space{
            padding-left: 0px;
            padding-right: 0px;
        }
        .tablebtn input{
            display: block;
            padding: 3px 15px;
            border-radius: 20px;
            border: solid 1px #FF5441;
            color: #FF5441;
            text-align: center;
        }
        .span_property{
            line-height: 34px;
            height: 34px;
        }
        .btn{
            border-radius: 0px;
        }

        .btn-default{
            background-color: #eee;
        }

        a{
            color: #000000;
        }

        .themeAdd{
            margin: 10px 3px;
        }

        .title_style{
            border-bottom: 1px solid #eee;
            color: #337ab7;
            margin-bottom: 20px;
            padding-bottom: 10px;;
        }

        .btn_fontSize{
            font-size: 16px;;
        }

        .form-group{
            margin-bottom: 15px;
        }
        .announceBtn{
            background-color: #317ee6;
            width:150px;
        }
        .activeClass{
            color: #317ee6;
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
        .rowSpan{margin-left: 0px;margin-right: 0px;}
        .noPassTip{
            color: red ;
            padding-left:63px; }
    </style>
    </@block>
    <@block name="body">
    <script language="javascript">
        <!--
        var where = new Array(35);
        function comefrom(province,city) { this.province = province; this.city = city; }
        where[0]= new comefrom("请选择省份名","请选择城市名");
        where[1] = new comefrom("北京","|东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
        where[2] = new comefrom("上海","|黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
        where[3] = new comefrom("天津","|和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
        where[4] = new comefrom("重庆","|万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
        where[5] = new comefrom("河北","|石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
        where[6] = new comefrom("山西","|太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
        where[7] = new comefrom("内蒙古","|呼和浩特|包头|乌海|赤峰|呼伦贝尔盟|阿拉善盟|哲里木盟|兴安盟|乌兰察布盟|锡林郭勒盟|巴彦淖尔盟|伊克昭盟");
        where[8] = new comefrom("辽宁","|沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
        where[9] = new comefrom("吉林","|长春|吉林|四平|辽源|通化|白山|松原|白城|延边");
        where[10] = new comefrom("黑龙江","|哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河|大兴安岭");
        where[11] = new comefrom("江苏","|南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
        where[12] = new comefrom("浙江","|杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
        where[13] = new comefrom("安徽","|合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
        where[14] = new comefrom("福建","|福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
        where[15] = new comefrom("江西","|南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
        where[16] = new comefrom("山东","|济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
        where[17] = new comefrom("河南","|郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
        where[18] = new comefrom("湖北","|武汉|宜昌|荆州|襄樊|黄石|荆门|黄冈|十堰|恩施|潜江|天门|仙桃|随州|咸宁|孝感|鄂州");
        where[19] = new comefrom("湖南","|长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|湘西|张家界");
        where[20] = new comefrom("广东","|广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
        where[21] = new comefrom("广西","|南宁|柳州|桂林|梧州|北海|防城港|钦州|贵港|玉林|南宁地区|柳州地区|贺州|百色|河池");
        where[22] = new comefrom("海南","|海口|三亚");
        where[23] = new comefrom("四川","|成都|绵阳|德阳|自贡|攀枝花|广元|内江|乐山|南充|宜宾|广安|达川|雅安|眉山|甘孜|凉山|泸州");
        where[24] = new comefrom("贵州","|贵阳|六盘水|遵义|安顺|铜仁|黔西南|毕节|黔东南|黔南");
        where[25] = new comefrom("云南","|昆明|大理|曲靖|玉溪|昭通|楚雄|红河|文山|思茅|西双版纳|保山|德宏|丽江|怒江|迪庆|临沧");
        where[26] = new comefrom("西藏","|拉萨|日喀则|山南|林芝|昌都|阿里|那曲");
        where[27] = new comefrom("陕西","|西安|宝鸡|咸阳|铜川|渭南|延安|榆林|汉中|安康|商洛");
        where[28] = new comefrom("甘肃","|兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳|临夏|甘南");
        where[29] = new comefrom("宁夏","|银川|石嘴山|吴忠|固原");
        where[30] = new comefrom("青海","|西宁|海东|海南|海北|黄南|玉树|果洛|海西");
        where[31] = new comefrom("新疆","|乌鲁木齐|石河子|克拉玛依|伊犁|巴音郭勒|昌吉|克孜勒苏柯尔克孜|博尔塔拉|吐鲁番|哈密|喀什|和田|阿克苏");
        where[32] = new comefrom("香港","");
        where[33] = new comefrom("澳门","");
        where[34] = new comefrom("台湾","|台北|高雄|台中|台南|屏东|南投|云林|新竹|彰化|苗栗|嘉义|花莲|桃园|宜兰|基隆|台东|金门|马祖|澎湖");
        where[35] = new comefrom("其它","|北美洲|南美洲|亚洲|非洲|欧洲|大洋洲");
        function select() {
            with(document.all['province']) {
                var province = options[selectedIndex].value;
            }
            for(var i = 0;i < where.length;i ++) {
                if (where[i].province == province) {
                    province = (where[i].city).split("|");
                    for(j = 0;j < province.length;j++) { with(document.all['city']) { length = province.length; options[j].text = province[j]; options[j].value = province[j]; var province=options[selectedIndex].value;}}
                    break;
                }}
        }
        function init() {
            with(document.all['province']) {
                length = where.length;
                for(k=0;k<where.length;k++) { options[k].text = where[k].province; options[k].value = where[k].province; }
                options[selectedIndex].text = where[0].province; options[selectedIndex].value = where[0].province;
            }
            with(document.all['city']) {
                province3 = (where[0].city).split("|");
                length = province3.length;
                for(var l=0;l<length;l++) { options[l].text = province3[l]; options[l].value = province3[l]; }
                options[selectedIndex].text = province3[0]; options[selectedIndex].value = province3[0];
            }
        }
        function setCity(loca, locacity) {
            document.all['province'].value = loca;
            for (var i= 0; i < where.length; i++) {
                if (where[i].province == loca) {
                    loca3 = (where[i].city).split("|");
                    for (j = 0; j < loca3.length; j++) {
                        with(document.all['city']) {
                            length = loca3.length;
                            options[j].text = loca3[j];
                            options[j].value = loca3[j];
                            if (loca3[j] == locacity) {
                                document.all['city'].value = locacity;
                            }
                        }
                    }
                }
            }
        }
        -->
    </script>
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-1 col-md-1 column padding_space ">
                    <div class="theme themeAdd">&nbsp;&nbsp;账户中心</div>
                    <div class="btn-group btn-group-md  btn-group-vertical">
                        <span style="${personal('/account/accountInfo')}"  class="btn btn-default btn_fontSize" id="accountInfo"><a  href="/account/accountInfo">账号信息</a></span>
                        <span style="${personal('/account/accountSecurity')}"  class="btn btn-default btn_fontSize" id="accountSecurity"><a  href="/account/accountSecurity">账号安全</a></span>
                        <span style="${personal('/account/getMyQualification')}"  class="btn btn-default btn_fontSize" id="accountQualify"><a  href="/account/getMyQualification">团购资格</a></span>
                    </div>
                    <div class="theme themeAdd" style="margin-top: 40px;">&nbsp;&nbsp;我的交易</div>
                    <div class="btn-group btn-group-vertical ">
                        <span style="${personal('/account/getMyOrders')}" id="my_buyOrder" class="btn btn-default btn_fontSize" ><a href="/account/getMyOrders">买货订单</a></span>
                        <span style="${personal('/account/getMySellOrders')}" id="my_sellOrder" class="btn btn-default btn_fontSize" ><a href="/account/getMySellOrders">卖货订单</a></span>
                        <span style="${personal('/account/getMyInterest')}" id="my_attention" class="btn btn-default btn_fontSize" ><a href="/account/getMyInterest">我的关注</a></span>
                        <span style="${personal('/account/getMyDemand')}" id="my_release" class="btn btn-default btn_fontSize" ><a href="/account/getMyDemand">我的需求</a></span>
                        <span style="${personal('/account/getMySupply')}" id="my_supply" class="btn btn-default btn_fontSize" ><a href="/account/getMySupply">我的供应</a></span>
                        <span style="${personal('/account/getMyQuote')}" id="my_offer" class="btn btn-default btn_fontSize" ><a href="/account/getMyQuote">我的报价</a></span>
                        <span style="${personal('/account/getMyOrderActive')}" id="my_group" class="btn btn-default btn_fontSize" ><a href="/account/getMyOrderActive">团购订单</a></span>
                        <span style="${personal('/getmanualsellOut')}" id="my_delegate" class="btn btn-default btn_fontSize" ><a href="/manualsell/list_manualsel_out">人工销售</a></span>
                        <span style="${personal('/getmanualsellIn')}" id="my_delegate" class="btn btn-default btn_fontSize" ><a href="/manualsell/list_manualsel_in">人工找货</a></span>
                    </div>
                </div>

                <div class="col-xs-11 col-md-11 column" >
                    <#--账号信息-->
                    <#if personPhone ??>
                    <div id="div_account" style="margin-top: 50px;">
                        <div>
                            <div class="title_style">个人中心</div>
                        </div>
                        <form action="/" method="post" class="form-horizontal" role="form" id="change-form" >
                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label">用户名:</label>
                            <div class="col-xs-3 col-md-3">
                                <span class="span_property" id="telPhone">${(users.nickname)!(users.securephone) }</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label">公司:</label>
                            <#if company??>
                                <#if company.name??>
                                    <div class="col-xs-4 col-md-4">
                                        <span class="span_property">${company.name}</span>
                                    </div>
                                </#if>
                            </#if>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label">手机:</label>
                            <div class="col-xs-2 col-md-2">
                                <span class="span_property" id="securephone">${users.securephone}</span>
                            </div>
                            <#--修改手机号的不需要(修改)-->
                            <#--<div class="col-md-2">-->
                                <#--<input type="button" id="modifySecurephone" class="btn btn-primary" value="修改手机号">-->
                                <#--<input type="hidden" id="isFrom">-->
                            <#--</div>-->
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label">固话:</label>
                            <div class="col-xs-3 col-md-3">
                                <input type="text" name="telephone" placeholder="如 021-88888888" value="${users.telephone!''}" class="form-control" id="telephone">
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <span id="telephoneError" class="index-color span_property"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-2 col-md-2 control-label">QQ:</label>
                            <div class="col-xs-3 col-md-3">
                                <input type="text" maxlength="15" placeholder="请输入QQ号码" name="qq" value="${users.qq!''}" class="form-control" id="qq">
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <span id="qqError" class="index-color span_property"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-3 col-md-offset-2 col-md-3">
                                <input type="button" id="userSave" class="btn btn-primary btn-block" value="保存">
                            </div>
                        </div>
                    </form>

                        <form class="form-horizontal" role="form" style="margin-top: 80px;">
                        <div class="form-group">
                            <label class="col-xs-3 col-md-3 control-label">公司认证:</label>
                            <#if users.verifystatus == "待完善信息">
                                <span class="col-xs-3 col-md-3 span_property" id="companyStatus"><strong>未通过任何公司信息认证!</strong></span>
                            <#elseif users.verifystatus == "待审核">
                                <span class="col-xs-3 col-md-3 span_property" id="companyStatus"><strong>公司信息正在审核中!</strong></span>
                            <#elseif users.verifystatus == "审核未通过">
                                <span class="col-xs-3 col-md-3 index-color span_property" id="companyStatus"><strong>公司信息审核未通过!</strong></span>
                            <#else>
                                <span class="col-xs-3 col-md-3 span_property" id="companyStatus"><strong>公司信息审核已通过!</strong></span>
                            </#if>
                            <input type="button" id="approve" value="修改" class="col-xs-1 col-md-1 btn btn-primary">
                        </div>
                    </form>
                        <#if users.verifystatus == "审核未通过">
                        <label class="col-xs-offset-1 col-xs-12 noPassTip">提示:${company.remarks!''}</label>
                        </#if>
                    </div>
                    </#if>

                    <#--认证公司信息-->
                    <#if certification??>
                        <div class="title_style" style="margin-top: 50px;">认证公司信息</div>
                        <div class="form-horizontal" id="company-form">
                            <div class="form-group">
                                <div class="col-xs-5 col-md-5">
                                    <span class="index-color span_property" id="remarks"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-xs-3 col-md-3 control-label">公司名称</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="20" class="form-control" id="name" name="name" placeholder="请输入公司名称">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="comNameInfo" class="index-color"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="col-xs-3 col-md-3 control-label">公司办公地址</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="50" class="form-control" id="address" name="address" placeholder="请输入公司地址">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="addressInfo" class="index-color"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="col-xs-3 col-md-3 control-label">公司办公电话</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="如 021-88888888">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="phoneInfo" class="index-color" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fax" class="col-xs-3 col-md-3 control-label">公司传真</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" class="form-control" id="fax" name="fax" placeholder="如 021-88888888">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="faxInfo" class="index-color"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contact" class="col-xs-3 col-md-3 control-label">法人</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="20" class="form-control" id="legalpersonname" name="legalpersonname" placeholder="请输入法人">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="legalpersonnameInfo" class="index-color" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">公司开户银行</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="20" class="form-control" id="openingbank" name="openingbank" placeholder="请输入公司开户银行">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="openingbankInfo" class="index-color" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">公司银行账号</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="30" class="form-control" id="account" name="account" placeholder="请输入公司银行账号">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="accountError" class="index-color" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">纳税人识别号</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="20" class="form-control" id="identificationnumword" name="identificationnumword" placeholder="请输入纳税人识别号">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="identificationnumwordError" class="index-color" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">邮政编码</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="6" class="form-control" id="zipcode" name="zipcode" placeholder="请输入公司邮政编码">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="zipcodeError" class="index-color" ></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">公司所在省份</label>
                                <div class="col-xs-3 col-md-3">
                                    <#--<input type="text" maxlength="10" class="form-control" id="province" name="province" placeholder="请输入公司所在省份">-->
                                    <select class="form-control" name="province" id="province" onChange="select()">
                                    </select>
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="provinceError" class="index-color" ></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">公司所在城市</label>
                                <div class="col-xs-3 col-md-3">
                                    <#--<input type="text" maxlength="10" class="form-control" id="city" name="city" placeholder="请输入公司所在城市">-->
                                        <select class="form-control" name="city" id="city">
                                        </select>
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="cityError" class="index-color" ></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contactphone" class="col-xs-3 col-md-3 control-label">主营品种</label>
                                <div class="col-xs-3 col-md-3">
                                    <input type="text" maxlength="100" class="form-control" id="mainvariety" name="mainvariety" placeholder="请输入主营品种">
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <span id="mainvarietyError" class="index-color" ></span>
                                </div>
                            </div>

                            <div class="form-group" style="padding-top: 15px;">
                                <div class="col-xs-12 col-md-12">
                                    <span class="col-xs-offset-3 col-md-offset-3" style="color:#555555;font-size:14px;">温馨提示:请选择jpg,bmp,png,jpeg,pdf格式的照片上传,图片容量最大为10M</span>
                                </div>
                            </div>
                            <#--<div class="form-group">-->
                                <#--<label for="legalperson" class="col-xs-3 col-md-3 control-label">企业法人代表身份证</label>-->
                                <#--<div class="col-xs-6 col-md-6">-->
                                    <#--<input type="hidden" id="hide-legalperson" name="hide-legalperson">-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<form  id="form-legalperson" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">-->
                                            <#--<input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="legalperson" name="file"  >-->
                                            <#--<div class="col-xs-5">-->
                                                <#--<input type="button" class="btn btn-primary" id="legalpersonClick" name="legalpersonClick" value="上传图片">-->
                                            <#--</div>-->
                                        <#--</form>-->
                                    <#--</div>-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<input type="button" class="btn btn-primary" id="btn_legalperson" name="btn_legalperson" value="更换图片">-->
                                        <#--<span id="legalpersonInfo" class="index-color" style="display: inline-block;" ></span>-->
                                        <#--<iframe  id="picForLegalperson" src="" width="600px;" height="500px;" ></iframe>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <div class="form-group">
                                <label for="businesslicense" class="col-xs-3 col-md-3 control-label">营业执照</label>
                                <div class="col-xs-7 col-md-7">
                                    <input type="hidden" id="hide-license" name="hide-license">
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <form  id="form-businesslicense" target="form-businesslicense" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">
                                            <input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="businesslicense" name="file"  >
                                            <div class="col-xs-5">
                                                <input type="button" class="btn btn-primary" id="businesslicenseClick" name="businesslicenseClick" value="上传图片">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <input type="button" class="btn btn-primary" id="btn_license" name="btn_license" value="更换图片">
                                        <span id="businesslicenseInfo" class="index-color" style="display: inline-block;" ></span>
                                        <iframe  id="picForLicense" name="form-businesslicense" src="" width="600px;" height="200px;" ></iframe>
                                    </div>
                                </div>
                            </div>
                            <#--税务登记证-->
                            <#--<div class="form-group">-->
                                <#--<label for="identificationnumber" class="col-xs-3 col-md-3 control-label">税务登记证</label>-->
                                <#--<div class="col-xs-7 col-md-7">-->
                                    <#--<input type="hidden" id="hide-number" name="hide-number">-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<form id="form-identificationnumber" target="form-identificationnumber" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">-->
                                            <#--<input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="identificationnumber" name="file"  >-->
                                            <#--<div class="col-xs-5">-->
                                                <#--<input type="button" class="btn btn-primary" id="identificationnumberClick" name="identificationnumberClick" value="上传图片">-->
                                            <#--</div>-->
                                        <#--</form>-->
                                    <#--</div>-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<input type="button" class="btn btn-primary" id="btn_number" name="btn_number" value="更换图片">-->
                                        <#--<span id="identificationnumberInfo" class="index-color" style="display: inline-block;" ></span>-->
                                        <#--<iframe  id="picForNumber" name="form-identificationnumber" src="" width="600px;" height="500px;" ></iframe>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--组织机构代码-->
                            <#--<div class="form-group">-->
                                <#--<label for="organizationcode" class="col-xs-3 col-md-3 control-label">组织机构代码</label>-->
                                <#--<div class="col-xs-7 col-md-7">-->
                                    <#--<input type="hidden" id="hide-code" name="hide-code">-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<form id="form-organizationcode" target="form-organizationcode" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">-->
                                            <#--<input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="organizationcode" name="file"  >-->
                                            <#--<div class="col-xs-5">-->
                                                <#--<input type="button" class="btn btn-primary" id="organizationcodeClick" name="organizationcodeClick" value="上传图片">-->
                                            <#--</div>-->
                                        <#--</form>-->
                                    <#--</div>-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<input type="button" class="btn btn-primary" id="btn_code" name="btn_code" value="更换图片">-->
                                        <#--<span id="organizationcodeInfo" class="index-color" style="display: inline-block;" ></span>-->
                                        <#--<iframe id="picForCode" name="form-organizationcode" src="" width="600px;" height="500px;" ></iframe>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--开户许可证-->
                            <#--<div class="form-group">-->
                                <#--<label for="openinglicense" class="col-xs-3 col-md-3 control-label">开户许可证</label>-->
                                <#--<div class="col-xs-7 col-md-7">-->
                                    <#--<input type="hidden" id="hide-openinglicense" name="hide-openinglicense">-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<form id="form-openinglicense" target="form-openinglicense" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">-->
                                            <#--<input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="openinglicense" name="file"  >-->
                                            <#--<div class="col-xs-5">-->
                                                <#--<input type="button" class="btn btn-primary" id="openinglicenseClick" name="openinglicenseClick" value="上传图片">-->
                                            <#--</div>-->
                                        <#--</form>-->
                                    <#--</div>-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<input type="button" class="btn btn-primary" id="btn_openinglicense" name="btn_openinglicense" value="更换图片">-->
                                        <#--<span id="openinglicenseInfo" class="index-color" style="display:inline-block;" ></span>-->
                                        <#--<iframe id="picForOpeningLicense" name="form-openinglicense" src="" width="600px;" height="500px;"></iframe>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->

                            <#--企业开票信息-->
                            <!--<div class="form-group">
                                <label for="invoicinginformation" class="col-xs-3 col-md-3 control-label">企业开票信息</label>
                                <div class="col-xs-6 col-md-6">
                                    <input type="hidden" id="hide-invoicinginformation" name="hide-invoicinginformation">
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <form  id="form-invoicinginformation" target="form-invoicinginformation" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">
                                            <input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="invoicinginformation" name="file"  >
                                            <div class="col-xs-5">
                                                <input type="button" class="btn btn-primary" id="invoicinginformationClick" name="invoicinginformationClick" value="上传图片">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <input type="button" class="btn btn-primary" id="btn_invoicinginformation" name="btn_invoicinginformation" value="更换图片">
                                        <span id="invoicinginformationInfo" class="index-color" style="display: inline-block;" ></span>
                                        <iframe  id="picForinvoicinginformation" name="form-invoicinginformation" src="" width="600px;" height="500px;" ></iframe>
                                    </div>
                                    <#--<input type="hidden" id="hide-legalperson" name="hide-legalperson">-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<form  id="form-legalperson" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">-->
                                            <#--<input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="legalperson" name="file"  >-->
                                            <#--<div class="col-xs-5">-->
                                                <#--<input type="button" class="btn btn-primary" id="legalpersonClick" name="legalpersonClick" value="上传图片">-->
                                            <#--</div>-->
                                        <#--</form>-->
                                    <#--</div>-->
                                    <#--<div class="row" style="margin-left: 0px; margin-right: 0px;">-->
                                        <#--<input type="button" class="btn btn-primary" id="btn_legalperson" name="btn_legalperson" value="更换图片">-->
                                        <#--<span id="legalpersonInfo" class="index-color" style="display: inline-block;" ></span>-->
                                        <#--<iframe  id="picForLegalperson" src="" width="600px;" height="500px;" ></iframe>-->
                                    <#--</div>-->
                                </div>
                            </div>-->

                            <#--美酒经营许可证-->
                            <div class="form-group">
                                <label for="operatinglicense" style="padding-left: 0px;" class="col-xs-3 col-md-3 control-label">美酒经营许可证</label>
                                <div class="col-xs-7 col-md-7">
                                    <input type="hidden" id="hide-operating" name="hide-operating">
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <form id="form-operatinglicense" target="form-operatinglicense" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">
                                            <input class="col-xs-6" style="height: 34px; padding-left: 0px;" type="file" id="operatinglicense" name="file"  >
                                            <div class="col-xs-5">
                                                <input type="button" class="btn btn-primary" id="operatinglicenseClick" name="operatinglicenseClick" value="上传图片">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row" style="margin-left: 0px; margin-right: 0px;">
                                        <input type="button" class="btn btn-primary" id="btn_operater" name="btn_operater" value="更换图片">
                                        <span id="operatinglicenseInfo" class="index-color" style="display: inline-block;" ></span>
                                        <iframe id="picForOperate" name="form-operatinglicense" src="" width="600px" height="200px;"></iframe>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs--offset-2 col-xs-7 col-md-offset-2 col-md-7">
                                    <span class="index-color">(交/提货地在山西,内蒙古区域内的要添加煤炭经营许可,其余可不添加此项)</span>
                                </div>
                            </div >
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-3 col-md-offset-2 col-md-3">
                                    <button type="button" class="btn btn-primary btn-block" id="changeComBtn">保存信息</button>
                                </div>
                                <div class="col-xs-2 col-md-2">
                                    <button type="button" class="btn btn-default btn-block" id="cancelComBtn">取消</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-3 col-md-offset-2 col-md-3">
                                    <span style="color: red; font-size: 14px;">(一旦修改信息,系统将重新审核)</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-7 col-md-offset-2 col-md-7">
                                    <span id="companyInfo" style="color:blue;"></span>
                                </div>
                            </div>
                        </div>
                    </#if>

                    <#--账号安全-->
                    <#if accountSecurity??>
                        <div class="row" style="margin-top: 40px;" >
                            <label class="col-xs-offset-1 col-xs-2 col-md-offset-1 col-md-2 control-label">修改密码</label>
                            <label class="col-xs-4 col-md-4 control-label">为确保账号安全,建议经常修改密码</label>
                            <div class="col-xs-2 col-md-2 ">
                                <input type="button" id="modify_pwd" class="btn btn-primary btn-block" style="margin-top: -8px;" value="修改">
                            </div>
                        </div>
                        <div class="row" style="margin-top: 25px;">
                            <label class="col-xs-offste-1 col-md-offset-1 col-xs-2 col-md-2 control-label">修改手机号</label>
                            <label class="col-xs-4 col-md-4 control-label">验证通过</label>
                            <div class="col-xs-2 col-md-2">
                                <input type="button" id="modify_securephone" class="btn btn-primary btn-block" style="margin-top: -8px;" value="修改手机号">
                            </div>
                        </div>
                    </#if>

                    <!--团购资格-->
                    <#if groupBuyQualificationList ??>
                        <div class="market-main">
                            <a href="javascript:void(0);" id="getGroupCert" type="button" style="margin-left: 776px;background-color: #ff624f;color: #fff;width:150px;" class="btn">获取团购资格</a>
                            <div style=" margin-top: 8px;">
                            <table class="table" style="border: 1px solid #f5f5f5;">
                                <thead style="background-color:#f5f5f5;">
                                <tr>
                                    <th>团购资格编号</th>
                                    <th>状态</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if groupBuyQualificationList ??>
                                        <#if groupBuyQualificationList?size gt 0>
                                        </#if>
                                        <#list groupBuyQualificationList as qualification>
                                        <tr>
                                            <td style="color:#317ee6;">${qualification.qualificationcode!''}</td>
                                                <#if qualification.status=='QUALIFY_ACTIVE'>
                                                <td style="color:#3dc240">审核通过</td>
                                                <#elseif qualification.status=='QUALIFY_APPLY'>
                                                <td style="color:#3dc240">审核中</td>
                                                <#elseif qualification.status=='QUALIFY_NOT_ENOUGH'>
                                                <td>审核失败</td>
                                                <#elseif qualification.status=='QUALIFY_INPROCESS'>
                                                <td>已使用</td>
                                                <#elseif qualification.status=='QUALIFY_FINISH'>
                                                <td>已结束</td>
                                                <#elseif qualification.status=='QUALIFY_CANCEL'>
                                                <td>资质作废</td>
                                                <#elseif qualification.status=='QUALIFY_GIVEUP'>
                                                <td style="color:#3dc240">放弃中</td>
                                                <#else>
                                                <td>已放弃</td>
                                                </#if>
                                            <#if qualification.status=='QUALIFY_ACTIVE' >
                                                <td class="tablebtn" style="width:140px;"><a style="padding: 0px;"  href="/account/showDepositContract?type=deposit&root=account&id=${qualification.id}" >查看保证金合约</a></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="giveUpQualification('${qualification.qualificationcode}')">放弃资格</a></td>
                                            <#elseif qualification.status=='QUALIFY_APPLY' || qualification.status=='QUALIFY_NOT_ENOUGH' || qualification.status=='QUALIFY_INPROCESS'>
                                                <td class="tablebtn" style="width:140px;"><a style="padding: 0px;" href="/account/showDepositContract?type=deposit&root=account&id=${qualification.id}" >查看保证金合约</a></td>
                                                <td style="width:140px;"></td>
                                            <#elseif qualification.status=='QUALIFY_GIVEUP' || qualification.status=='QUALIFY_GIVEUP'>
                                                <td style="width:140px;"></td>
                                                <td style="width:140px;"></td>
                                            <#else>
                                                <td style="width:140px;"></td>
                                                <td style="width:140px;"></td>
                                            </#if>
                                            </td>
                                        </tr>
                                        </#list>
                                    <#else >
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有团购资格相关的数据!</td></tr>
                                    </#if>
                                </tbody>
                            </table>
                            <#if groupBuyQualificationList?size gt 0>
                                <@pager path="/account/getMyQualification" params={} page=qualifyPageNumber  count=qualifyCount pagesize=pagesizeQualify class="pager"/>
                            <#else>
                                <center><span style='color: #ff0000; font-size: 18px;'>没有团购资格相关的数据!</span></center>
                            </#if>
                        </div>
                    </div>
                </#if>
                    <#--买货订单-->
                    <#if orderBuyGoList?exists||orderCompList?exists||orderCancelList?exists||orderReturnList?exists>
                        <div class="btn-group btn-group-md">
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyOrders')}" href="/account/getMyOrders">进行中</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyReturnedOrders')}" href="/account/getMyReturnedOrders">退货中</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyCanceledOrders')}" href="/account/getMyCanceledOrders">已取消</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyFinishedOrders')}" href="/account/getMyFinishedOrders">已完成</a></span>
                        </div>
                        <div>
                        <#if orderBuyGoList?exists>
                            <table class="table">
                                <thead>
                                <tr style="border-top: 2px solid #007DE4;">
                                    <th>订单编号</th>
                                    <th>单价(元/瓶)</th>
                                    <th>数量(瓶)</th>
                                    <th>总货款(元)</th>
                                    <th>状态</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if orderBuyGoList?exists>
                                        <#list orderBuyGoList as continuedOrder>
                                        <tr>
                                            <#if continuedOrder.ordertype=='MallOrder'>
                                            <td><a href='/account/OrderInfo?id=${continuedOrder.id}&reqsource=buyContinueOrder' style="color:red;">${continuedOrder.orderid!''}</a></td>
                                            <#else>
                                            <td><a href='/account/OrderInfo?id=${continuedOrder.id}&reqsource=buyContinueOrder' style="color:#317ee6;">${continuedOrder.orderid!''}</a></td>
                                            </#if>
                                            <td style="color:red;">${continuedOrder.price?c!''}</td>
                                            <td>${continuedOrder.amount?c!''}</td>
                                            <td>${continuedOrder.totalmoney!''}</td>
                                            <#if continuedOrder.status=="WaitSignContract">
                                                <td class="tablenopass">待签合同</td>
                                                <#elseif continuedOrder.status=="WaitPayment">
                                                <td class="tablenopass">待付款</td>
                                                <#elseif continuedOrder.status=="WaitBalancePayment">
                                                <td class="tablenopass">待付尾款</td>
                                                <#elseif continuedOrder.status=="WaitVerify">
                                                <td class="tablenopass">审核中</td>
                                                <#elseif continuedOrder.status=="VerifyPass">
                                                <td class="tablecontinue">交割中</td>
                                                <#elseif continuedOrder.status=="VerifyNotPass">
                                                <td class="tablepass">审核未通过</td>
                                                <#elseif continuedOrder.status=="MakeMatch">
                                                <td style="color:#3dc240">撮合中</td>
                                                </#if>
                                            <#if continuedOrder.status=='WaitSignContract'>
                                                <td class="tablebtn"><a style="padding: 0px;width:90px;" href="/mall/contract?id=${continuedOrder.id}">去签合同</a></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="CancelMyContinuedOrder('${continuedOrder.id}','buy')">取消订单</a></td>
                                                <#elseif continuedOrder.status=='WaitPayment'>
                                                    <td class="tablebtn"><a style="padding: 0px;width:90px;" href="/mall/payment?id=${continuedOrder.id}">去付款</a></td>
                                                    <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="CancelMyContinuedOrder('${continuedOrder.id}','buy')">取消订单</a></td>
                                                <#elseif continuedOrder.status=='WaitBalancePayment'>
                                                <td class="tablebtn"><a style="padding: 0px;width:90px;" href="/mall/payment?id=${continuedOrder.id}">去付尾款</a></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="ApplyReturn('${continuedOrder.id}');">申请退货</a></td>
                                                <#elseif continuedOrder.status=='WaitVerify'>
                                                <td></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="ApplyReturn('${continuedOrder.id}');">申请退货</a></td>
                                                <#elseif continuedOrder.status=="VerifyPass">
                                                <td></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="ApplyReturn('${continuedOrder.id}');">申请退货</a></td>
                                                <#elseif continuedOrder.status=='VerifyNotPass'>
                                                <td class="tablebtn"><a style="padding: 0px;width:90px;" href="/mall/payment?id=${continuedOrder.id}">去付款</a></td>
                                                <td></td>
                                                <#elseif continuedOrder.status=='MakeMatch'>
                                                <td></td>
                                                <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="CancelMyContinuedOrder('${continuedOrder.id}','buy')">取消订单</a></td>
                                            </#if>
                                        </tr>
                                        </#list>
                                    <#else>
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                    </#if>
                                </tbody>
                            </table>
                            <#if orderBuyGoList?size gt 0>
                                <@pager path="/account/getMyOrders" params={} page=pageNumBuyGo pagesize=pagesizeBuyGo count=countBuyGo class="pager"/>
                            <#else>
                                <center><span style='color: #ff0000; font-size: 18px;text-align: center;'>没有搜索到您想要的数据!</span></center>
                            </#if>
                        <#elseif orderReturnList?exists>
                            <table class="table">
                                <thead>
                                <tr style="border-top: 2px solid #007DE4;">
                                    <th>订单编号</th>
                                    <th>单价(元/瓶)</th>
                                    <th>数量(瓶)</th>
                                    <th>总货款(元)</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if orderReturnList?exists>
                                        <#list orderReturnList as returnedOrder>
                                        <tr>
                                            <#if returnedOrder.ordertype=='MallOrder'>
                                            <td><a href='/account/OrderInfo?id=${returnedOrder.id}&reqsource=buyReturnOrder' style="color:red;">${returnedOrder.orderid!''}</a></td>
                                            <#else>
                                            <td><a href='/account/OrderInfo?id=${returnedOrder.id}&reqsource=buyReturnOrder' style="color:#317ee6;">${returnedOrder.orderid!''}</a></td>
                                            </#if>
                                            <td style="color:red;">${returnedOrder.price?c!''}</td>
                                            <td>${returnedOrder.amount?c!''}</td>
                                            <td>${returnedOrder.totalmoney!''}</td>
                                            <td>退货中</td>
                                            <td><a style="padding: 0px;color:#317ee6;" href="#" onclick="CancelMyReturnedOrder(${returnedOrder.id})">取消退货</a></td>
                                        </tr>
                                        </#list>
                                    <#else>
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                    </#if>
                                </tbody>
                            </table>
                            <#if orderReturnList?size gt 0>
                                <@pager path="/account/getMyReturnedOrders" params={} page=pageNumReturn pagesize=pagesizeReturn count=countReturn class="pager"/>
                            <#else>
                                <center><span style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</span></center>
                            </#if>
                        <#elseif orderCancelList?exists>
                            <table class="table">
                                <thead>
                                <tr style="border-top: 2px solid #007DE4;">
                                    <th>订单编号</th>
                                    <th>单价(元/瓶)</th>
                                    <th>数量(瓶)</th>
                                    <th>总货款(元)</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if orderCancelList?exists>
                                        <#list orderCancelList as canceledOrder>
                                        <tr>
                                            <#if canceledOrder.ordertype=='MallOrder'>
                                            <td><a href='/account/OrderInfo?id=${canceledOrder.id}&reqsource=buyCancelOrder' style="color:red;">${canceledOrder.orderid!''}</a></td>
                                            <#else>
                                            <td><a href='/account/OrderInfo?id=${canceledOrder.id}&reqsource=buyCancelOrder' style="color:#317ee6;">${canceledOrder.orderid!''}</a></td>
                                            </#if>
                                            <td style="color:red;">${canceledOrder.price?c!''}</td>
                                            <td>${canceledOrder.amount?c!''}</td>
                                            <td>${canceledOrder.totalmoney!''}</td>
                                            <td>已取消</td>
                                            <td><a style="padding: 0px;color:#317ee6;" href="#" onclick="delMyCanceledOrder(${canceledOrder.id})">删除</a></td>
                                        </tr>
                                        </#list>
                                    <#else>
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                    </#if>
                                </tbody>
                            </table>
                            <#if orderCancelList?size gt 0>
                                <@pager path="/account/getMyCanceledOrders" params={} page=pageNumCancel pagesize=pagesizeCancel count=countCancel class="pager"/>
                            <#else>
                                <center><span style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</span></center>
                            </#if>
                        <#else>
                            <table class="table">
                                <thead>
                                <tr style="border-top: 2px solid #007DE4;">
                                    <th>订单编号</th>
                                    <th>单价(元/瓶)</th>
                                    <th>数量(瓶)</th>
                                    <th>总货款(元)</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if orderCompList?exists>
                                        <#list orderCompList as finishedOrder>
                                        <tr>
                                            <#if finishedOrder.ordertype=='MallOrder'>
                                                <td><a href='/account/OrderInfo?id=${finishedOrder.id}&reqsource=buyCompletedOrder' style="color:red;">${finishedOrder.orderid!''}</a></td>
                                            <#else>
                                                <td><a href='/account/OrderInfo?id=${finishedOrder.id}&reqsource=buyCompletedOrder' style="color:#317ee6;">${finishedOrder.orderid!''}</a></td>
                                            </#if>
                                            <td style="color:red;">${finishedOrder.price?c!''}</td>
                                            <td>${finishedOrder.amount?c!''}</td>
                                            <td>${finishedOrder.totalmoney!''}</td>
                                            <td>
                                                <#if finishedOrder.status == 'Completed'>
                                                    已完成
                                                    <#else>
                                                    退货完成
                                                </#if>
                                            </td>
                                            <td><a style="padding: 0px;color:#317ee6;" href="#" onclick="delMyFinishedOrder(${finishedOrder.id})">删除</a></td>
                                        </tr>
                                        </#list>
                                    <#else>
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                    </#if>
                                </tbody>
                            </table>
                            <#if orderCompList?size gt 0>
                                <@pager path="/account/getMyFinishedOrders" params={} page=pageNumComp pagesize=pagesizeComp count=countComp class="pager"/>
                            <#else>
                                <center><span style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</span></center>
                            </#if>
                        </#if>
                        </div>
                    </#if>

                    <#--卖货订单-->
                    <#if orderSellGoList?exists||orderSellCompList?exists||orderSellCancelList?exists>
                    <div class="btn-group btn-group-md">
                        <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMySellOrders')}" href="/account/getMySellOrders">进行中</a></span>
                        <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMySellFinishedOrders')}" href="/account/getMySellFinishedOrders">已完成</a></span>
                        <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMySellCanceledOrders')}" href="/account/getMySellCanceledOrders">已取消</a></span>
                    </div>
                    <div>
                    <#if orderSellGoList?exists>
                        <table class="table">
                            <thead>
                            <tr style="border-top: 2px solid #007DE4;">
                                <th>订单编号</th>
                                <th>单价(元/瓶)</th>
                                <th>数量(瓶)</th>
                                <th>总货款(元)</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if orderSellGoList?exists>
                                    <#list orderSellGoList as continuedOrder>
                                    <tr>
                                        <td><a href='/account/OrderInfo?id=${continuedOrder.id}&reqsource=sellContinuedOrder' style="color:#317ee6;">${continuedOrder.orderid!''}</a></td>
                                        <td style="color:red;">${continuedOrder.price?c!''}</td>
                                        <td>${continuedOrder.amount?c!''}</td>
                                        <td>${continuedOrder.totalmoney!''}</td>
                                        <td>撮合中</td>
                                    </tr>
                                    </#list>
                                <#else>
                                <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                </#if>
                            </tbody>
                        </table>
                        <#if orderSellGoList?size gt 0>
                            <@pager path="/account/getMySellOrders" params={} page=pageNumSellGo pagesize=pagesizeSellGo count=countSellGo class="pager"/>
                        <#else>
                            <center><span style='color: #ff0000; font-size: 18px;text-align: center;'>没有搜索到您想要的数据!</span></center>
                        </#if>
                    <#elseif orderSellCompList?exists>
                        <table class="table">
                            <thead>
                            <tr style="border-top: 2px solid #007DE4;">
                                <th>订单编号</th>
                                <th>单价(元/瓶)</th>
                                <th>数量(瓶)</th>
                                <th>总货款(元)</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if orderSellCompList?exists>
                                    <#list orderSellCompList as finishedOrder>
                                    <tr>
                                        <td><a href='/account/OrderInfo?id=${finishedOrder.id}&reqsource=sellCompletedOrder' style="color:#317ee6;">${finishedOrder.orderid!''}</a></td>
                                        <td style="color:red;">${finishedOrder.price?c!''}</td>
                                        <td>${finishedOrder.amount?c!''}</td>
                                        <td>${finishedOrder.totalmoney!''}</td>
                                        <td>已完成</td>
                                        <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="delMySellOrder('${finishedOrder.id}','finishedOrder')">删除</a></td>
                                    </tr>
                                    </#list>
                                <#else>
                                <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                </#if>
                            </tbody>
                        </table>
                        <#if orderSellCompList?size gt 0>
                            <@pager path="/account/getMySellFinishedOrders" params={} page=pageNumSellComp pagesize=pagesizeSellComp count=countSellComp class="pager"/>
                        <#else>
                            <center><span style='color: #ff0000; font-size: 18px;text-align: center;'>没有搜索到您想要的数据!</span></center>
                        </#if>
                    <#else>
                        <table class="table">
                            <thead>
                            <tr style="border-top: 2px solid #007DE4;">
                                <th>订单编号</th>
                                <th>单价(元/瓶)</th>
                                <th>数量(瓶)</th>
                                <th>总货款(元)</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if orderSellCancelList?exists>
                                    <#list orderSellCancelList as canceledOrder>
                                    <tr>
                                        <td><a href='/account/OrderInfo?id=${canceledOrder.id}&reqsource=sellCancelOrder' style="color:#317ee6;">${canceledOrder.orderid!''}</a></td>
                                        <td style="color:red;">${canceledOrder.price?c!''}</td>
                                        <td>${canceledOrder.amount?c!''}</td>
                                        <td>${canceledOrder.totalmoney!''}</td>
                                        <td>已取消</td>
                                        <td><a href="javascript:void(0);" style="color:#317ee6;" onclick="delMySellOrder('${canceledOrder.id}','canceledOrder')">删除</a></td>
                                    </tr>
                                    </#list>
                                <#else>
                                <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有搜索到您想要的数据!</td></tr>
                                </#if>
                            </tbody>
                        </table>
                        <#if orderSellCancelList?size gt 0>
                            <@pager path="/account/getMyCanceledOrders" params={} page=pageNumSellCancel pagesize=pagesizeSellCancel count=countSellCancel class="pager"/>
                        <#else>
                            <center><span style='color: #ff0000; font-size: 18px;text-align: center;'>没有搜索到您想要的数据!</span></center>
                        </#if>
                    </#if>
                </div>
            </#if>


                    <!--团购订单-->
                    <#if groupBuyOrdersActiveList?exists || groupBuyOrdersFinishes?exists>
                        <div class="btn-group btn-group-md">
                            <span class="btn btn_fontSize"><a class="activeClass" style="${changeStyle('/account/getMyOrderActive')}" href="/account/getMyOrderActive">团购订单</a></span>
                        </div>
                        <div style="border:1px solid #bfbfbf;"></div>
                        <div class="btn-group btn-group-md">
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyOrderActive')}" href="/account/getMyOrderActive">进行中</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyOrderFinish')}" href="/account/getMyOrderFinish">已结束</a></span>
                        </div>
                        <#if groupBuyOrdersActiveList?exists>
                        <table class="table">
                                <thead>
                                    <tr>
                                        <th>团购编号</th>
                                        <th>单价(元/瓶)</th>
                                        <th>数量(瓶)</th>
                                        <th>截止日期</th>
                                        <th>团购资格编号</th>
                                    </tr>
                                </thead>
                            <tbody>
                                <#if groupBuyOrdersActiveList?size gt 0>
                                    <#list groupBuyOrdersActiveList as groupOrder>
                                        <tr>
                                            <td><a href="/account/selectOrderDetail?groupbuyordercode=${groupOrder.groupbuyordercode}&type=activeGroupOrder" style="color:#317ee6;">${groupOrder.groupbuyordercode!''}</a></td>
                                            <td class="price">${groupOrder.groupbuyprice!''}</td>
                                            <td>${groupOrder.volume!''}</td>
                                            <td>${groupOrder.groupbuyenddate!''}</td>
                                            <td>${groupOrder.qualificationcode!''}</td>
                                        </tr>
                                    </#list>
                                <#else>
                                        <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有进行中的团购相关的订单数据!</td></tr>
                                </#if>
                            </tbody>
                        </table>
                        <#if groupBuyOrdersActiveList?size gt 0>
                            <@pager path="/account/getMyOrderActive" params={} page=activePageNumber count=activeSupplyCount pagesize=pagesizeGroupActive class="pager"/>
                        </#if>
                        <#else>
                        <table class="table">
                                <thead>
                                    <tr>
                                        <th>团购编号</th>
                                        <th>单价(元/瓶)</th>
                                        <th>数量(瓶)</th>
                                        <th>截止日期</th>
                                        <th>团购资格编号</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#if groupBuyOrdersFinishes?exists>
                                        <#if groupBuyOrdersFinishes?size gt 0>
                                            <#list groupBuyOrdersFinishes as finishOrder>
                                            <tr>
                                                <td><a href="/account/selectOrderDetail?groupbuyordercode=${finishOrder.groupbuyordercode}&type=finishedGroupOrder" style="color:#317ee6;">${finishOrder.groupbuyordercode!''}</td>
                                                <td class="price">${finishOrder.groupbuyprice!''}</td>
                                                <td>${finishOrder.volume!''}</td>
                                                <td>${finishOrder.groupbuyenddate!''}</td>
                                                <td>${finishOrder.qualificationcode!''}</td>
                                                <td class="tablebtn"><a href="javascript:void(0);" onclick="delFinishedGroupOrder('${finishOrder.groupbuyordercode}')">删除</a></td>
                                            </tr>
                                            </#list>
                                        <#else>
                                            <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有已结束的团购相关的订单数据!</td></tr>
                                        </#if>
                                    </#if>
                                </tbody>
                        </table>
                                <#if groupBuyOrdersFinishes?size gt 0>
                                    <@pager path="/account/getMyOrderFinish" params={} page=finishedPageNumber count=finishedSupplyCount pagesize=pagesizeGroupFinish class="pager"/>
                                </#if>
                        </#if>
                    </#if>

                    <#--我的关注-->
                    <#if supplyMap?exists || demandMap?exists>
                        <div class="btn-group btn-group-md">
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyInterest')}"  href="/account/getMyInterest">我关注的产品</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyInterestDemand')}"  href="/account/getMyInterestDemand">我关注的需求</a></span>
                        </div>
                        <div>
                            <#if supplyMap?exists>
                                <table class="table">
                                    <thead>
                                    <tr style="border-top: 2px solid #007DE4;">
                                        <th>产品编号</th>
                                        <th>价格(元)</th>
                                        <th>数量(瓶)</th>
                                        <th>热值(kcal/kg)</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if supplyMap?exists>
                                        <#if supplyMap?size gt 0>
                                            <#list supplyMap as interest>
                                            <tr>
                                                <td><a href='/mall/info?id=${interest.sid?c}'  style="color:#317ee6;" target="_blank">${interest.pid!''}</a></td>
                                                <td>${interest.price?c!''}</td>
                                                <td>${interest.amount?c!''}</td>
                                                <td>${interest.NCV?c!''}</td>
                                                <td class="tablebtn" ><a style="padding: 0px;" href="#" onclick="delMyInterest(${interest.id})">删除</a></td>
                                            </tr>
                                            </#list>
                                        <#else>
                                            <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有您关注的数据!</td></tr>
                                        </#if>
                                        </#if>
                                    </tbody>
                                </table>
                                <#if supplyMap?size gt 0>
                                    <@pager path="/account/getMyInterest" params={} page=pageNumber count=supplyCount pagesize=pagesizeSupply class="pager"/>
                                </#if>
                            <#else >
                                <table class="table">
                                    <thead>
                                    <tr style="border-top: 2px solid #007DE4;">
                                        <th>需求编号</th>
                                        <th>数量(瓶)</th>
                                        <th>热值(kcal/kg)</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if demandMap?exists>
                                            <#if demandMap?size gt 0>
                                                <#list demandMap as interest>
                                                <tr>
                                                    <td><a target="_blank"  style="color:#317ee6;" href='/sell/gotoQuote?id=${interest.sid?c}&reqsource=quote'>${interest.pid!''}</a></td>
                                                    <td>${interest.amount?c!''}</td>
                                                    <td>${interest.NCV?c!''}</td>
                                                    <td class="tablebtn" ><a style="padding: 0px;" onclick="delMyInerestedDeamnd(${interest.id})">删除</a></td>
                                                </tr>
                                                </#list>
                                            <#else>
                                                <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>没有您需求的数据!</td></tr>
                                            </#if>
                                        </#if>
                                    </tbody>
                                </table>
                                <#if demandMap?size gt 0>
                                    <@pager path="/account/getMyInterestDemand" params={} page=pageNumber count=demandCount pagesize=pagesizeDemand class="pager"/>
                                </#if>
                            </#if>
                        </div>
                    </#if>


                    <#--我的发布页面--已发布的需求-->
                    <#if demandList ??>
                    <div  style="margin-bottom:-30px; text-align: right;">
                        <a href="/buy/releaseDemand" type="button" class="btn btn-primary announceBtn">发布需求</a>
                        <a href="/sell/createSupply" type="button" style="margin-left:20px;" class="btn btn-primary announceBtn">发布供应</a>
                    </div>
                    <div class="market-main" id="myRelease">
                        <div style=" margin-top: 40px;">
                            <table class="table" style="border: 1px solid #f5f5f5;">
                            <thead style="background-color:#f5f5f5;">
                            <tr>
                                <th>需求编号</th>
                                <th>发布时间</th>
                                <th>截止时间</th>
                                <th>需求瓶数(瓶)</th>
                                <th>报价情况</th>
                                <th>匹配进度</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="myReleaseBody">
                            <#if demandList ??>
                                <#if demandList?size gt 0>
                                        <#list demandList as demand>
                                        <tr style="border-bottom: 1px solid #f5f5f5;">
                                            <#if (demand.status) == "匹配中">
                                                <td><a class="tabcolor" target="_blank" href="/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease&chooseQuote=chooseQuote">${demand.demandcode !''}</a></td>
                                            <#elseif (demand.status)  == "报价中">
                                                <td><a class="tabcolor" target="_blank" href='/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease'>${demand.demandcode !''}</a></td>
                                            <#elseif (demand.status) == "交易结束">
                                                <td><a class="tabcolor" target="_blank" href='/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease'>${demand.demandcode !''}</a></td>
                                            <#elseif (demand.status)  == "审核中">
                                                <td><a class="tabcolor" target="_blank" href='/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease'>${demand.demandcode !''}</a></td>
                                            <#else>
                                                <td><a class="tabcolor" target="_blank" href="/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease&resume=resume">${demand.demandcode !''}</a>
                                                <#--if (demand.status)  == "审核未通过"-->
                                            <#--<#else>-->
                                                <#--<td><a class="tabcolor" target="_blank" href='/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease'>${demand.demandcode !''}</a></td>-->
                                            </#if>
                                            <td>${demand.releasetime ?date!''}</td>
                                            <td>${demand.quoteenddate!''}</td>
                                            <td>${demand.demandamount?c!''}</td>
                                            <td>已有${demand.quotenum?c!''}家报价</td>
                                            <td>已匹配${demand.purchasednum?c !''}瓶</td>
                                            <#if (demand.status) == "匹配中">
                                                <td>${demand.status }</td>
                                                <td class="tablebtn" style="width: auto;"><a class="tableMatching" target="_blank" href="/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease&chooseQuote=chooseQuote">选择报价</a></td>
                                            <#elseif (demand.status)  == "报价中">
                                                <td>${demand.status }</td>
                                                <#--<td class="tablebtn" style="width: auto;"><a class="tableQoute" href="javascript:void(0);" onclick="stopMyrelease('${demand.demandcode}')">取消发布</a></td>-->
                                            <#elseif (demand.status) == "交易结束">
                                                <td>${demand.status }</td>
                                                <td class="tablebtn" style="width: auto;"><a class="tableEnd" href="javascript:void(0);" onclick="deleteMyrelease('${demand.demandcode}')">删除</a></td>
                                            <#elseif (demand.status)  == "审核中">
                                                <td>${demand.status }</td>
                                                <td class="tablebtn" style="width: auto;"><a class="tablein" href="javascript:void(0);" onclick="stopMyrelease('${demand.demandcode}')">取消发布</a></td>
                                            <#else>
                                                <td>${demand.status }</td>
                                                <td class="tablebtn" style="width: auto;"><a class="tablenopass" target="_blank" href="/account/viewMyDemand?demandcode=${demand.demandcode}&reqsource=myrelease&resume=resume">重新发布</a>
                                            </#if>
                                        </tr>
                                    </#list>
                                    <#else>
                                        <tr><td colspan='8' style='color: #ff0000; font-size: 18px; text-align: center;'>没有您的发布需求!</td></tr>
                                </#if>
                            </#if>
                            </tbody>
                        </table>
                            <#if demandList?size gt 0>
                                <@pager path="/account/getMyDemand" params={} page=pageNumber pagesize=demandPagesize count=demandTotalCount class="pager"/>
                            </#if>
                        </div>
                    </div>
                    </#if>


                    <#--我的发布页面--已发布的供应-->
                    <#if supplyList ??>
                    <div  style="margin-bottom:-30px; text-align: right;">
                        <a href="/buy/releaseDemand" type="button" class="btn btn-primary announceBtn">发布需求</a>
                        <a href="/sell/createSupply" type="button" style="margin-left:20px;" class="btn btn-primary announceBtn">发布供应</a>
                    </div>
                    <div class="market-main">
                        <div style=" margin-top: 40px;">
                            <table class="table" style="border: 1px solid #f5f5f5;">
                                <thead style="background-color:#f5f5f5;">
                                <tr >
                                    <th>产品编号</th>
                                    <th>发布时间</th>
                                    <th>酒精度数(kcal/kg)</th>
                                    <th>单价(元/瓶)</th>
                                    <th>供应数量(瓶)</th>
                                    <th>审核进度</th>
                                    <th colspan='2'>操作</th>
                                </tr>
                                </thead>
                                <tbody id="mySupplyBody">
                                    <#if supplyList ??>
                                        <#if supplyList?size gt 0>
                                        </#if>
                                        <#list supplyList as supply>
                                        <tr style="border-bottom: 1px solid #f5f5f5;">
                                            <td class="tabcolor"><a style="color:#317ee6;" href="/account/getSupplyDetail?id=${supply.id?c}&reqsource=mysupply" target="_blank">${supply.pid !''}</a></td>
                                            <td>${supply.createdate!''}</td>
                                            <td>${supply.NCV?c !''}</td>
                                            <#if supply.ykj == 0>
                                                <td class="price">*最低价${supply.jtjlast?c !''}</td>
                                            <#else>
                                                <td class="price">${supply.ykj?c!''}</td>
                                            </#if>
                                            <td>${supply.supplyquantity?c!''}</td>
                                            <#if supply.status =="WaitVerify">
                                                <td class="tablein">审核中</td>
                                            <#elseif supply.status == "VerifyPass">
                                                <td class="tablepass">审核通过</td>
                                            <#elseif supply.status == "VerifyNotPass" >
                                                <td class="tablenopass">审核未通过</td>
                                            <#elseif supply.status == "Canceled">
                                                <td class="tablenopass">已取消</td>
                                            </#if>
                                            <#if supply.status=='Completed'>
                                            <td class="tablebtn" style="width:200px;">
                                                <a href="#" onclick="delMySupply(${supply.id?c})" style="display:inline">删除</a></td>
                                            <#elseif supply.status =="Canceled">
                                            <td class="tablebtn" style="width:200px;">
                                                <a href="#" onclick="delMySupply(${supply.id?c})" style="display:inline">删除</a></td>
                                            <#elseif supply.status=="VerifyNotPass">
                                            <td class="tablebtn" style="width:200px;">
                                                <a href="/account/updateSupply?type=newone&id=${supply.id?c}" style="display:inline">重新发布</a></td>
                                            <#elseif supply.status=="VerifyPass" ||  supply.status=='WaitVerify' >
                                                <td class="tablebtn" style="width:200px;">
                                                    <a href="#" onclick="updateMySupply('${supply.id?c}', '${supply.status}')" style="display:inline">更改</a>
                                                    <a href="#" onclick="cancelMySupply(${supply.id?c})" style="display:inline">取消供应</a></td>
                                            <#else>
                                            <td class="tablebtn" style="width:200px;">
                                                <a href="#" onclick="cancelMySupply(${supply.id?c})" style="display:inline">取消供应</a></td>
                                            </#if>

                                        </tr>
                                    </#list>
                                    <#else >
                                    <tr><td colspan='9' style='color: #ff0000; font-size: 18px;'>您还没有发布供应信息!</td></tr>
                                    </#if>
                                </tbody>
                        </table>
                        <input type="hidden" id="supplyId">
                        <#if supplyList?size gt 0>
                            <@pager path="/account/getMySupply" params={} page=pageNumber  count=count pagesize=pagesizeMySupply class="pager"/>
                        <#else>
                            <center><span style='color: #ff0000; font-size: 18px;'>您还没有发布供应信息!</span></center>
                        </#if>
                        </div>
                    </div>
                    </#if>


                    <#--我的报价-->
                    <#if quoteUnderwayList?exists || quoteBidList?exists || quoteNotBidList?exists>
                        <div class="btn-group btn-group-md">
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyQuote')}"  href="/account/getMyQuote">进行中</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyQuoteBid')}"  href="/account/getMyQuoteBid">已中标</a></span>
                            <span class="btn btn_fontSize"><a style="${changeStyle('/account/getMyQuoteNotBid')}"  href="/account/getMyQuoteNotBid">未中标</a></span>
                        </div>
                        <div>
                            <#if quoteUnderwayList ?exists >
                                <table class="table" style="border: 1px solid #f5f5f5;">
                                    <thead style="background-color:#f5f5f5;">
                                    <tr>
                                        <th>需求编号</th>
                                        <th>申供瓶数(瓶)</th>
                                        <th>申供价格(元/瓶)</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if quoteUnderwayList ??>
                                            <#if quoteUnderwayList?size gt 0>
                                                <#list quoteUnderwayList as quote>
                                                <tr style="border-bottom: 1px solid #f5f5f5;">
                                                    <td class="tabcolor"><a target="_blank" href='/account/viewMyQuote?demandid=${quote.demandid?c}&reqsource=myquote'>${quote.demandcode !''}</a></td>
                                                    <td>${quote.supplyton?c!''}</td>
                                                    <td class="price">${quote.quote?c!''}</td>
                                                    <td>${quote.status!''}</td>
                                                    <#if (quote.status) != "比价中">
                                                        <td class="tablebtn"><a href="/account/viewMyQuote?demandid=${quote.demandid?c}&reqsource=quote" style="padding: 3px;" target="_blank">更改报价</a></td>
                                                    </#if>
                                                </tr>
                                                </#list>
                                            <#else>
                                            <tr><td colspan='4'  style='color: #ff0000; font-size: 18px; text-align: center'>没有找到您想要的数据!</td></tr>
                                            </#if>
                                        </#if>
                                    </tbody>
                                </table>
                                <#if quoteUnderwayList?size gt 0>
                                    <@pager path="/account/getMyQuote" params={} page=pageNumber  count=underwayTotalCount pagesize=pagesizeQuote class="pager"/>
                                </#if>
                            <#elseif quoteBidList?exists>
                                    <table class="table" style="border: 1px solid #f5f5f5;">
                                        <thead style="background-color:#f5f5f5;">
                                        <tr>
                                            <th>需求编号</th>
                                            <th>申供瓶数(瓶)</th>
                                            <th>申供价格(元/瓶)</th>
                                            <th>状态</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <#if quoteBidList ??>
                                                <#if quoteBidList?size gt 0>
                                                    <#list quoteBidList as quote>
                                                    <tr style="border-bottom: 1px solid #f5f5f5;">
                                                        <td class="tabcolor"><a target="_blank" href='/account/viewMyQuote?demandid=${quote.demandid?c}&reqsource=myquote'>${quote.demandcode !''}</a></td>
                                                        <td>${quote.supplyton?c!''}</td>
                                                        <td class="price">${quote.quote?c!''}</td>
                                                        <td>${quote.status!''}</td>
                                                    </tr>
                                                    </#list>
                                                <#else>
                                                <tr><td colspan='6'  style='color: #ff0000; font-size: 18px; text-align: center'>没有找到您想要的数据!</td></tr>
                                                </#if>
                                            </#if>
                                        </tbody>
                                    </table>
                                    <#if quoteBidList?size gt 0>
                                        <@pager path="/account/getMyQuoteBid" params={} page=pageNumber pagesize=pagesizeBid count=bidTotalCount class="pager"/>
                                    </#if>
                            <#else>
                                <table class="table" style="border: 1px solid #f5f5f5;">
                                    <thead style="background-color:#f5f5f5;">
                                    <tr>
                                        <th>需求编号</th>
                                        <th>申供瓶数(瓶)</th>
                                        <th>申供价格(元/瓶)</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if quoteNotBidList ??>
                                            <#if quoteNotBidList?size gt 0>
                                                <#list quoteNotBidList as quote>
                                                <tr style="border-bottom: 1px solid #f5f5f5;">
                                                    <td class="tabcolor"><a target="_blank" href='/account/viewMyQuote?demandid=${quote.demandid?c}&reqsource=myquote'>${quote.demandcode !''}</a></td>
                                                    <td>${quote.supplyton?c!''}</td>
                                                    <td class="price">${quote.quote?c!''}</td>
                                                    <td>${quote.status!''}</td>
                                                    <td class="tablebtn"><a href="javascript:void(0);" onclick="deleteMyquote(${quote.id?c})">删除</a></td>
                                                    <#--<td ><a href="javascript:void(0);" onclick="cancelMyquote(${quote.id})">取消报价</a></td>-->
                                                </tr>
                                                </#list>
                                            <#else>
                                            <tr><td colspan='5'  style='color: #ff0000; font-size: 18px; text-align: center'>没有找到您想要的数据!</td></tr>
                                            </#if>
                                        </#if>
                                    </tbody>
                                </table>
                                <#if quoteNotBidList?size gt 0>
                                    <@pager path="/account/getMyQuoteNotBid" params={} page=pageNumber count=notBidTotalCount pagesize=pagesizeNotBid class="pager"/>
                                </#if>
                            </#if>
                        </div>
                    </#if>

                    <#--委托人工买卖信息展示-->
                            <#if manualsellList ??>
                            <div class="market-main">
                            <div>
                               <#assign rangeKeys=rangeTypes?keys>
                                <#list rangeKeys as key>
                                        <#if manualsellType==true>
                                            <#if dateRange==key>
                                                <a  href="/manualsell/list_manualsel_in?dateRange=${key}" style="color:red;">${rangeTypes[key]}</a>
                                                    <#else>
                                                <a href="/manualsell/list_manualsel_in?dateRange=${key}">${rangeTypes[key]}</a>
                                            </#if>
                                        <#else>
                                            <#if dateRange==key>
                                                <a href="/manualsell/list_manualsel_out?dateRange=${key}" style="color:red;">${rangeTypes[key]}</a>
                                            <#else>
                                                <a href="/manualsell/list_manualsel_out?dateRange=${key}">${rangeTypes[key]}</a>
                                            </#if>
                                        </#if>
                                </#list>
                                <table class="table" style="border: 1px solid #f5f5f5;">
                                <thead style="background-color:#f5f5f5;">
                                <tr>
                                    <#if manualsellType==true>
                                        <th>人工找货编号</th>
                                        <th>提货地点</th>
                                        <th>需求数量(瓶)</th>
                                        <th>提货时间</th>
                                    <#else>
                                        <th>人工销售编号</th>
                                        <th>交货地点</th>
                                        <th>供货数量(瓶)</th>
                                        <th>交货时间</th>
                                    </#if>
                                    <#--<th>状态</th>-->
                                    <#--<th colspan='2'>操作</th>-->
                                </tr>
                                </thead>
                            <tbody>
                                <#if manualsellList?size gt 0>
                                    <#assign flag=manualsellType?string/>
                                <#list manualsellList as sell>
                                <tr style="border-bottom: 1px solid #f5f5f5;">
                                    <td class="tabcolor"><a href='/manualsell/load/${sell.manualsellId}' style="color:#317ee6">${sell.manualsellId}</a></td>
                                    <td>
                                        <#if sell.deliveryOtherPlace?has_content>
                                         ${sell.deliveryProvince}${sell.deliveryOtherPlace}
                                            <#else>
                                        ${sell.deliveryProvince}${sell.deliveryAddr}
                                        </#if>
                                    </td>
                                    <td>${sell.demandAmount?c!''}</td>
                                    <td>${sell.deliveryStartDate}
                                        <#if sell.deliveryEndDate??>
                                            至 ${sell.deliveryEndDate}
                                        </#if>
                                    </td>
                                    <#--<td>匹配中</td>-->
                                    <#--<td class="tablebtn"><a href="javascript:void(0);" onclick="deleteManualInfo(${sell.id},${flag})" >删除</a></td>-->
                                </tr>
                                </#list>
                            <#else>
                            <tr><td colspan='6'  style='color: #ff0000; font-size: 18px; text-align: center'>没有找到您想要委托人工的数据!</td></tr>
                            </tbody>
                            </#if>
                            </table>
                                <#if manualsellList?size gt 0>
                                    <#if flag==true>
                                    <@pager path="/manualsell/list_manualsel_in" params={"dateRange":dateRange,"manualsellType":manualsellType?string('true','false')} page=pageNumber pagesize=manualsellPagesize count=manualsellCount class="pager"/>
                                    <#else>
                                        <@pager path="/manualsell/list_manualsel_out" params={"dateRange":dateRange,"manualsellType":manualsellType?string('true','false')} page=pageNumber pagesize=manualsellPagesize count=manualsellCount class="pager"/>
                                    </#if>
                                </#if>
                            </div>
                            </div>
                            </#if>
                    <#if manualsell??>
                        <div style="margin-left: 50px;">
                        <#if manualsell.type==true>
                            <h4>委托找货详细信息</h4>
                        <#else>
                            <h4>委托销售详细信息</h4>
                        </#if>
                            <hr/>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                        <div class="col-xs-4 col-md-4">美酒种类:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.coalType}</div>
                                    </div>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                       <div class="col-xs-4 col-md-4">酒精度数:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.lowcalorificvalue?c!''}kcal/kg</div>
                                    </div>
                            <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                <div class="col-xs-4 col-md-4">含糖量:</div>
                                <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.receivebasissulfur}%</div>
                            </div>
                            <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                <div class="col-xs-4 col-md-4"> 酒类指标2:</div>
                                <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.airdrybasisvolatile}%</div>
                            </div>
                                <#if manualsell.type==true>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                        <div class="col-xs-4 col-md-4">提货地点:</div>
                                    <div class="col-xs-8 col-md-8" style="color:#317ee6;">
                                        <#if manualsell.deliveryOtherPlace?has_content>${manualsell.deliveryProvince}${manualsell.deliveryOtherPlace}
                                    <#else>
                                    ${manualsell.deliveryProvince}${manualsell.deliveryAddr}
                                    </#if>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                        <div class="col-xs-4 col-md-4">需求数量:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.demandAmount?c!''}瓶</div>
                                    </div>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                        <div class="col-xs-4 col-md-4">提货方式:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.deliveryMode}</div>
                                    </div>
                                    <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                        <div class="col-xs-4 col-md-4">提货时间:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.deliveryStartDate}</div>
                                    </div>
                                    <#else>
                                        <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                            <div class="col-xs-4 col-md-4">交货地点:</div>
                                        <div class="col-xs-8 col-md-8" style="color:#317ee6;">
                                            <#if manualsell.deliveryOtherPlace?has_content>${manualsell.deliveryProvince}${manualsell.deliveryOtherPlace}
                                            <#else>
                                            ${manualsell.deliveryProvince}${manualsell.deliveryAddr}
                                            </#if>
                                        </div>
                                        </div>
                                        <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                            <div class="col-xs-4 col-md-4">供货数量:</div>
                                            <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.demandAmount?c!''}瓶</div>
                                        </div>
                                        <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                            <div class="col-xs-4 col-md-4">交货方式:</div>
                                            <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.deliveryMode}</div>
                                        </div>
                                        <div class="col-xs-6 col-md-6" style="line-height:50px;">
                                            <div class="col-xs-4 col-md-4">交货时间:</div>
                                            <div class="col-xs-8 col-md-8" style="color:#317ee6;">${manualsell.deliveryStartDate}</div>
                                        </div>
                                </#if>

                            <div class="col-xs-offset-10 col-md-offset-10 col-xs-2 col-md-2">
                            <input type="button" onclick="javascript:history.go(-1)" class="btn btn-block" value="返回">
                            </div>
                        </div>
                    </#if>

                </div>
            </div>
        </div>
    </div>
</div>
    <input type="hidden" id="myOrderId" value=""/>
    <input type="hidden" id="myOrderAmount" value=""/>
    <input type="hidden" id="myInterestId" value=""/>
    <input type="hidden" id="mySupplyId" value=""/>
    <input type="hidden" id="mySellInfoid" value=""/>
    <input type="hidden" id="attendType" value=""/>
    <input type="hidden" id="selectModifyPwd" value="">
    <input type="hidden" id="delType" value="">
    <input type="hidden" id="cancelType" value="">
    <input type="hidden" id="phoneNum" name="phoneNum" value='${(session().user.securephone)!''}'>

    <div class="modal fade" id="confirm_deletemanual_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">温馨提示:</h4>
                </div>
                <div class="modal-body">
                    <p>确定要删除该委托信息吗?</p>
                </div>
                <div class="modal-footer">
                    <button id="manualsell_confirm" type="button" class="btn btn-primary">确定</button>
                    <button type="button" id="manualsell_cancel" class="btn btn-primary">关闭</button>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal fade" id="modal-confirm" data-backdrop="false" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin: 0px auto;width: 600px;height: 250px;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                <span>确定要删除该报价?</span>
                            </div>
                            <div class="modal-footer">
                                <button id="certain_btn" type="button" class="btn btn-primary">确定</button>
                                <button id="cancel_btn" type="button" class="btn btn-default">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal fade" id="modal-sellOrderCancelConfirm" data-backdrop="false" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin: 0px auto;width: 600px;height: 250px;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                <span>该订单正在撮合中,您确定要取消吗?</span>
                            </div>
                            <div class="modal-footer">
                                <button id="sellOrderCancel_btn" type="button" class="btn btn-primary">确定</button>
                                <button data-dismiss="modal" type="button" class="btn btn-default">取消</button>
                            </div>
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
                    <button type="button" class="btn  btn-primary" id="supplyUpdeteComfirmBtn">继续更改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade my_demand" id="myRelease-deleteConfirm" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 300px;top: 200px;font-size: 18px;">
            <div class="modal-content">
                <div class="modal-body" style="text-align: center;">
                    <span class="my_demandBody" style="color: red;"></span>
                </div>
                <div class="modal-footer footerType">
                    <button type="button" class="btn btnWidthSize btnBgOk btn-sm btn-primary my_demandButton">确&nbsp;&nbsp;&nbsp;&nbsp;定</button>
                    <button type="button" class="btn btnWidthSize btnBgCancel btn-sm btn-default my_cancelButton" data-dismiss="modal">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="modal" id="myQuote-cancelConfirm" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <span>您确定取消该项报价吗?</span>
                    </div>v
                    <div class="modal-footer">
                        <button id="myQuote_cancelConfirm" type="button" class="btn btn-primary">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--修改公司的账号信息-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal" id="modal-companyDialog" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="top:200px; width: 420px;">
                        <div class="modal-content" style="text-align: center; color: red;">
                            <div class="modal-body">
                                <span>您已修改公司的账号信息，本平台将重新进行审核。</span>
                            </div>
                            <div class="modal-footer footerType">
                                <button id="btn_success" type="button" class="btn btnBgOk btnWidthSize btn-sm btn-default">关&nbsp;&nbsp;&nbsp;&nbsp;闭</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--后台审核,前台页面还没有刷新-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal" id="modal-reLoad" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="top:200px; width: 420px;">
                        <div class="modal-content" style="text-align: center; color: red;">
                            <div class="modal-body">
                                <span>该条信息已被审核通过，请重新刷新页面!</span>
                            </div>
                            <div class="modal-footer footerType">
                                <button id="btn_reLoad" type="button" class="btn btnBgOk btnWidthSize btn-sm btn-default">刷&nbsp;&nbsp;&nbsp;&nbsp;新</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--修改手机号-->
    <div class="modal" id="changePhoneDialog" data-backdrop="static" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="top:180px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" id="modifyPhone_close" class="close"  aria-hidden="true">&times;</button>
                    <h4 class="modal-title">修改手机号</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" action="/validcode" role="form" id="phone_form" method="post">
                        <div class="form-group">
                            <label for="securephone" class="col-xs-2 col-md-2 control-label">手机号码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="text" class="form-control" id="securephone" name="securephone" value="${(session().user.securephone)!'null'}" readonly="readonly">
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <input type="button" style="height:34px;" id="verfyCode" value="获取验证码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-md-offset-2 col-xs-5 col-md-5">
                                <span class="tip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="code" class="col-xs-2 col-md-2 control-label">验证码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="text" class="form-control" id="code" name="code">
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <span id="codeInfo" class="index-color span_property"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-md-offset-2 col-xs-5 col-md-5">
                                <button type="button" class="btn btn-lg btn-primary btn-block" id="vCode-btn">下一步</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-md-offset-2 col-xs-7 col-md-7">
                                <span id="phoneSaveInfo" style="color:blue;"></span>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/" role="form" id="newPhone_form" method="post" style="display:none;">
                        <div class="form-group">
                            <label for="newSecurephone" class="col-xs-3 col-md-3 control-label">新手机号码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="text" class="form-control" id="newSecurephone" name="newSecurephone">
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <input type="button" style="height:34px;"  id="newVerfyCode" value="获取验证码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-md-offset-3 col-xs-7 col-md-7">
                                <span class="SecTip"></span>
                                <span id="newSecurephoneInfo" class="index-color span_property"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="code" class="col-xs-3 col-md-3 control-label">验证码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="text" class="form-control" id="newCode" name="newCode">
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <span id="newCodeInfo" class="index-color span_property"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-md-offset-3 col-xs-5 col-md-5">
                                <button type="button" class="btn btn-lg btn-primary btn-block" id="OKBtn">确认修改</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-md-offset-3 col-xs-7 col-md-7">
                                <span id="newpPhoneSaveInfo" style="color:blue;"></span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

     <#--修改密码-->
    <div class="modal" id="changePasswordDialog" data-backdrop="static" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="top:180px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button"  class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form"  method="post" id="pwd-form">
                        <div class="form-group">
                            <label for="orgPassword" class="col-xs-2 col-md-2 control-label">原密码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="password" class="form-control" id="orgPassword" name="orgPassword" placeholder="请输入原始密码">
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <span id="orgPasswordInfo" class="index-color span_property"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-xs-2 col-md-2 control-label">新密码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="password" class="form-control" id="password" name="password" placeholder="请输入新密码">
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <span id="passwordInfo" class="index-color span_property" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmPwd" class="col-xs-2 col-md-2 control-label">确认密码</label>
                            <div class="col-xs-5 col-md-5">
                                <input type="password" class="form-control" id="confirmPwd" name="confirmPwd" placeholder="请再次输入新密码">
                            </div>
                            <div class="col-xs-5 col-md-5">
                                <span id="confrimPwdInfo" class="index-color span_property" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-md-offset-2 col-xs-5 col-md-5">
                                <button type="button" class="btn btn-primary btn-block" id="changePwdBtn">修改密码</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-md-offset-2 col-xs-7 col-md-7">
                                <span id="changePwdSaveInfo" style="color:blue;"></span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#--修改密码成功-->
    <div class="modal" id="successPasswordDialog" data-backdrop="static" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="top:200px; width: 320px;">
            <div class="modal-content">
                <div class="modal-body" style="text-align: center; color: red;">
                    <span>修改密码成功!</span>
                </div>
                <div class="modal-footer footerType">
                    <button id="close_successPasswordDialog" type="button" class="btn btnBgOk btnWidthSize btn-sm btn-default" data-dismiss="modal">关&nbsp;&nbsp;&nbsp;&nbsp;闭</button>
                </div>
            </div>
        </div>
    </div>

    <#--用户修改信息保存-->
    <div class="modal" id="userInfo_Dialog" data-backdrop="static" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="top:200px; width: 320px;">
            <div class="modal-content" >
                <div class="modal-body" style="text-align: center; color: red;">
                    <span>信息保存成功!</span>
                </div>
                <div class="modal-footer footerType">
                    <button id="close_userInfoDialog" type="button" class="btn btnBgOk btnWidthSize btn-sm btn-default" data-dismiss="modal">关&nbsp;&nbsp;&nbsp;&nbsp;闭</button>
                </div>
            </div>
        </div>
    </div>

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
                    <!--<button type="button" class="btn btn-primary my_supplyButton"></button>-->
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/myRelease.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/myOrder.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/supply.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/groupBuy.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/index.js')}"></script>
    <script>init()</script>
    </@block>
</@extend>



