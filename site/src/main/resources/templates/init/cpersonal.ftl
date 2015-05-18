<#function cpersonal path>
    <#return request.requestURI==path ||
    request.requestURI?starts_with(path?ensure_ends_with("/")) ||
    (request.requestURI=='/account/individualCenter' && path='/account/accountInfo')
    ||(request.requestURI=='/account/getMyInterestDemand' && path='/account/getMyInterest')
    ||(request.requestURI=='/account/getMyReturnedOrders' && path='/account/getMyOrders')
    ||(request.requestURI=='/account/getMyFinishedOrders' && path='/account/getMyOrders')
    ||(request.requestURI=='/account/payGetMyOrders' && path='/account/getMyOrders')
    ||(request.requestURI=='/account/getMyCanceledOrders' && path='/account/getMyOrders')
    ||(request.requestURI=='/account/getMySellFinishedOrders' && path='/account/getMySellOrders')
    ||(request.requestURI=='/account/getMySellCanceledOrders' && path='/account/getMySellOrders')
    ||(request.requestURI=='/account/getMyQuoteBid' && path='/account/getMyQuote')
    ||(request.requestURI=='/account/getMyQuoteNotBid' && path='/account/getMyQuote')
    ||(request.requestURI=='/manualsell/list_manualsel_out' && path='/getmanualsellOut')
    ||(request.requestURI=='/manualsell/list_manualsel_in' && path='/getmanualsellIn')
    ||(request.requestURI=='/account/getMyOrderFinish' && path='/account/getMyOrderActive')
    />
</#function>
<#function personal path>
    <#return cpersonal(path)?string("background:#337ab7;border:0px", "")/>
</#function>
<#global cpersonal=cpersonal/>
<#global personal=personal/>