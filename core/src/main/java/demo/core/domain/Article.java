package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.String;
import java.time.LocalDateTime;

/**
 * Created by lxj on 14/11/12.
 */
public class Article implements Serializable {
    private Integer id;
    private String title;                   //文章标题
    private String summary;                 //文章摘要
    private String keywords;                //关键字
    private String content;                 //文章内容
    private String author;                  //作者 编辑
    private String source;                  //文章来源
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;       //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastupdatetime;   //最后一次修改时间
    private boolean isdelete;               //是否删除
    private Integer parentid;                   //父id
    private boolean haschild;               //是否有child
    private String path;                    //路径
    private String lastupdateman;           //最后一次更新的操作人
    private int sequence;                   //文章次序

    public Article() {
    }

    public Article(String title, String summary, String keywords, String content,
                   String author, String source, Integer parentid, String lastupdateman) {
        this.title = title;
        this.summary = summary;
        this.keywords = keywords;
        this.content = content;
        this.author = author;
        this.source = source;
        this.parentid = parentid;
        this.lastupdateman = lastupdateman;
    }

    public Article(Integer id, String title, String summary, String keywords, String content,
                   String author, String source, String lastupdateman) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.keywords = keywords;
        this.content = content;
        this.author = author;
        this.source = source;
        this.lastupdateman = lastupdateman;
    }

    public Article(Integer id, String title, String summary, String keywords,
                   String content, String author, String lastupdateman, Integer parentid,
                   String path, int sequence, String source) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.keywords = keywords;
        this.lastupdateman = lastupdateman;
        this.parentid = parentid;
        this.path = path;
        this.sequence = sequence;
        this.source = source;
        this.summary = summary;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public boolean isHaschild() {
        return haschild;
    }

    public void setHaschild(boolean haschild) {
        this.haschild = haschild;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLastupdateman() {
        return lastupdateman;
    }

    public void setLastupdateman(String lastupdateman) {
        this.lastupdateman = lastupdateman;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
