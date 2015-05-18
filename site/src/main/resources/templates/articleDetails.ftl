<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .space{padding-right: 85px; padding-left: 85px;}
        .location {margin-top: -10px; font-size: 14px;}
        .articleInfo {font-size: 14px; text-align: center}
        .articleTitle {font-size:22px; color:#013f88; font-weight:bold; text-align: center; margin-top: 20px;}
        .articleSummary {border: 1px solid #ccc; border-radius: 4px; background-color: #f5f5f5; font-size: 14px; line-height: 1.5em; color: #333; padding: 12px; margin: 15px; }
        .articleContent {margin-top: 15px; font-size:16px;}
        .articleContent p {text-indent: 2em; line-height: 2em;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container space" >

            <#if article ??>
                <p class="location">您所在的位置：<a href="/" target="_blank"> XX网</a> >
                <#if parentPath??>
                    <a href="/getArticleList?path=/${parentPath!}" target="_blank">${parentPath!}</a>
                </#if>
                    > 正文</p>

                <div class="article">
                    <p align="center" class='articleTitle'>${article.title!}</p>
                    <hr>
                    <p class="articleInfo">
                        <#if article.createtime?has_content>
                            <span>时间：${article.createtime?date}  &nbsp;&nbsp;</span>
                        </#if>
                        <#if article.source?has_content>
                            <span>来源：${article.source!}  &nbsp;&nbsp;</span>
                        </#if>
                        <#if article.author?has_content>
                            <span>发布人：${article.author!}  &nbsp;&nbsp;</span>
                        </#if>
                    </p>

                    <#if article.summary?has_content>
                        <#if article.summary?length gt 0>
                            <div class="articleSummary">
                                    ${article.summary!}
                            </div>
                        </#if>
                    </#if>
                    <div class='articleContent'>
                        <@markdown value=article.content/>
                    </div>
                </div>
            </#if>

        </div>
    </div>
    </@block>
</@extend>