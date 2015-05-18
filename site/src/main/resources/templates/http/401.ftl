<@extend name="layout">
<@block name="head">
</@block>
<@block name="body">
<div class="clear-level">
    <div class="container">
        <div class="col-xs-12 col-md-12">
            需要登录后才能查看该网页，<a href="${url('/login', {'t':exception.url})}" >请登录</a>
        </div>
    </div>
</div>
</@block>
<@block name="script">
<script type="text/javascript">
    setTimeout(function(){
        window.location.href="${url('/login', {'t':exception.url})}";
    }, 5000);
</script>
</@block>
</@extend>






