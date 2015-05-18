<#macro pager path params page pagesize count class="" id="">
    <head>
        <style type="text/css">
            .selectStyle{background-color: #dedede;}
            .fontStyle{color:black;font-weight: bold;}
            .class{color: red;font-weight: bold;}
        </style>
    </head>
    <#local page=page/>
    <#local pagesize=pagesize/>
    <#local pageNumber=pagesize />
    <#local maxpage=(count/pagesize)?ceiling/>
    <#if maxpage lt 1>
        <#local maxpage=1/>
    </#if>
    <#if page lt 1>
        <#local page=1/>
    </#if>
    <#if page gt maxpage>
        <#local maxpage=page/>
    </#if>
    <#local begin=page-2/>
    <#if begin lt 1>
        <#local begin=1/>
    </#if>
    <#local end=begin+4/>
    <#if end gt maxpage>
        <#local end=maxpage/>
        <#local begin=end-5/>
        <#if begin lt 1>
            <#local begin=1/>
        </#if>
    </#if>
    <input type="hidden" id="count" value="${count!'0'}">
    <input type="hidden" id="currentPage" value="${page!'0'}">
<div<#if id!=''> id="${id}"</#if><#if class!=''> class="${class}"</#if>>
<ul>
    <span class="dropdown" style="max-width: 100px;margin-left: -170px;">
        <a auth href="" data-toggle="dropdown" style="padding-right: 50px;">每页<span id="pageSize">${pageNumber!'10'}</span>条<b class="caret"></b></a>
        <ul class="dropdown-menu" style="max-width: 80px; min-width: 80px; font-size: 16px; padding-top: 0px; padding-bottom: 0px;">
            <div style="width: 80px; text-align: center;">
                <table style="width: 80px; text-align: center;">
                    <tr style="line-height: 20px;">
                        <td>
                            <#local pageNumber=10 />
                            <a style="display: inline-block;width:80px;" class="class" href="${url(path, params+{"page":page, "pagesize": 10})}">10</a>
                        </td>
                    </tr>
                    <tr style="line-height: 20px;">
                        <td>
                            <#local pageNumber=20 />
                            <a style="display: inline-block;width:80px;" class="class" href="${url(path, params+{"page":page, "pagesize": 20})}">20</a>
                        </td>
                    </tr>
                    <tr style="line-height: 20px;">
                        <td>
                            <#local pageNumber=50 />
                            <a style="display: inline-block;width:80px;" class="class" href="${url(path, params+{"page":page, "pagesize": 50})}">50</a>
                        </td>
                    </tr>
                    <tr style="line-height: 20px;">
                        <td>
                            <#local pageNumber=100 />
                            <a style="display: inline-block;width:80px;" class="class" href="${url(path, params+{"page":page, "pagesize": 100})}">100</a>
                        </td>
                    </tr>
                </table>
            </div>
        </ul>
    </span>
        <#if maxpage gt 1>
            <#if maxpage gt 1>
                <#if page gt 1>
                    <li>
                        <a href="${url(path, params+{"page":page-1, "pagesize": pagesize})}">上一页</a>
                    </li>
                </#if>
            </#if>
            <#if page gt 1>
                <#list begin .. (page-1) as index>
                    <li>
                        <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                    </li>
                </#list>
            </#if>
            <span style="padding-left: 10px;">${page}</span>
            <#if page lt end>
                <#list (page+1) .. end as index>
                    <li>
                        <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                    </li>
                </#list>
            </#if>
            <#if maxpage gt 1>
                <#if page lt maxpage>
                    <li>
                        <a href="${url(path, params+{"page":page+1, "pagesize": pagesize})}">下一页</a>
                    </li>
                </#if>
            </#if>
        </#if>
        <li style="padding-left: 30px;">
            共${maxpage}页
        </li>
        <li style="padding-left: 20px;">
            共${count}条数据
        </li>
    </ul>
</div>

</#macro>
<#global pager=pager/>

