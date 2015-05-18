package demo.core.util;

/**
 * Created by jack on 14/12/2.
 */
public class PageQueryParam {
    private int page =1;
    private int pageSize = 10;
    //开始检索的地方
    private int indexNum;
    public int getIndexNum() {
        this.indexNum =(this.page-1)*this.pageSize;
        return indexNum;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public PageQueryParam(){}
    public PageQueryParam(int page){
        this.page=page;
    }
}
