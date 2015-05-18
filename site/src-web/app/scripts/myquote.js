require(['jquery','bootstrap','pagination'],function($){

  $("#cancel_btn").click(function(){
    $("#modal-confirm").modal('hide');
  })

  $("#certain_btn").click(function(){
    $.ajax({
      url:"/account/deleteMyquote",
      data:{id:$("#myquoteId").val()},
      success:function(data){
        if(data == "success"){
          $("#modal-confirm").modal('hide');
          window.location.href='/myquote';
        }
      }
    });

  })

  init();

  function init(){
    var element = $("#Pagination");
    var options = {
      bootstrapMajorVersion: 3, //版本
      currentPage: 1, //当前页数
      numberOfPages: 5, //设置显示的页码数
      totalPages:$("#totalPages").val(), //总页数
      itemTexts: function(type, page, current) {
        switch (type) {
          case "first":
            return "首页";
          case "prev":
            return "上一页";
          case "next":
            return "下一页";
          case "last":
            return "末页";
          case "page":
            return page;
        }
      },
      onPageClicked: function(event, originalEvent, type, page) {
        $.ajax({
          url: "/myQuoteTurnpage",
          data:{pageNumber:page},
          success: function(msg) {
            if (msg!=null) {
              var jsonStr ="";
              $("#table_body").html("");
              $.each(msg.quoteList,function(n,value){
                var sort = n+1;
                var id = value.id;
                var demandid = value.demandid;
                var demandcode= value.demandcode;
                var unitprice = value.unitprice;
                var supplyton = value.supplyton;
                var lowcalorificvalue = value.lowcalorificvalue;
                var quote = value.quote;
                jsonStr += "<tr><td>"+sort+"</td><td>"+demandcode+"</td><td>"+unitprice+"</td>" +
                "<td>"+supplyton+"</td><td>"+lowcalorificvalue+"</td>" +
                "<td>"+quote+"</td>"+
                "<td><a href='#' onclick='delMyQuote("+id+")'>删除</a>|<a href='#' onclick='checkMyQuote("+demandid+")'>查看</a></td></tr>";
              });
              $("#table_body").html(jsonStr);
              jsonStr="";
            }
          }
        });
      }
    };
    element.bootstrapPaginator(options);
  }


});

function delMyQuote(id){
  $("#myquoteId").val(id);
  $("#modal-confirm").modal('show');
}

function checkMyQuote(demandid){
  window.location.href="/account/viewMyQuote?demandid="+demandid+"&reqsource=myquote";
}





