<#function cmenu path>
    <#return request.requestURI==path ||
            request.requestURI?starts_with(path?ensure_ends_with("/")) ||
            (request.requestURI=='/' && path='/index')/>
</#function>
<#function menuclass path>
    <#return cmenu(path)?string("active", "")/>
</#function>
<#global cmenu=cmenu/>
<#global menuclass=menuclass/>