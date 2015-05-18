<#function buyorder path>
    <#return request.requestURI==path ||
    request.requestURI?starts_with(path?ensure_ends_with("/"))/>
</#function>
<#function changeStyle path>
    <#return buyorder(path)?string("color:#317ee6", "")/>
</#function>
<#global buyorder=buyorder/>
<#global changeStyle=changeStyle/>
