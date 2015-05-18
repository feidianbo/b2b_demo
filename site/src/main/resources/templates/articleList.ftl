<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .location {margin-top: 20px; margin-left: 50px;font-size: 14px;}
        .articleList {padding-right: 70px; padding-left: 50px;}
        .pageTitle {font-size: 18px; font-weight: bold; color: #393939; border-left:4px solid red; margin: 20px auto auto 20px;}
        .articleList hr {margin: 15px auto;}
        .articleDetails {padding-bottom: 10px; margin-bottom: 15px; line-height: 22px; border-bottom: dashed 1px #CCC;}
        .articleTitle {margin-bottom: 10px; font-size: 20px; font-weight: bold; color:#317116;}
        .articleContent {font-size: 14px; color: #666}
        .articleInfo {font-size: 12px; color: #666; text-indent: 2.5em; margin-top: 5px;}
    </style>
    </@block>
    <@block name="body">
        <div class="container">

            <div class="location">
                <p>您所在的位置：<a href="/" target="_blank">XX网</a> > ${parentPath!'文章列表'}</p>
            </div>

            <div class="articleList">
                <#if articleList ??>
                    <p class="pageTitle">&nbsp;&nbsp;文章列表</p>

                    <hr>

                    <#list articleList as item>
                        <div class="articleDetails">
                            <div class="articleTitle">
                                <a target="_blank" href="/getArticle?id=${item.id}");">${item.title!}</a>
                            </div>

                            <div class="articleContent">
                                <#if item.content?length lt 192>${item.content!}
                                <#else> ${item.content[0..190]}...</#if>
                            </div>

                            <div class="articleInfo">
                                <#if item.createtime??>
                                    发布时间：${item.createtime?date} &nbsp;&nbsp;&nbsp;
                                </#if>
                                <#if item.author?has_content>发布人：${item.author!} &nbsp;&nbsp;&nbsp;</#if>
                                <#if item.keywords?has_content>关键词：${item.keywords!}</#if>
                            </div>
                        </div>
                    </#list>

                    <#if (articleList?size gt 0)>
                        <@pager path="/getArticleList" params={"path":path,"page":page} page=page count=count class="pager"/>
                    </#if>
                </#if>

            </div>
        </div>
    </@block>
</@extend>
