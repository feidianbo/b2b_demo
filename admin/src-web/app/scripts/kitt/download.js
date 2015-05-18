$(function(){
    $("#aggrecontract").click(function(){
      var downloadBtn = $("#downloadDeposit");
        if($(this).is(":checked")){
          downloadBtn.attr("disabled",false);
        }else{
          downloadBtn.attr("disabled",true);
        }
    });
    $("#downloadDeposit").click(function(){
       window.location.href="/customer/download/constract";
    });
});
