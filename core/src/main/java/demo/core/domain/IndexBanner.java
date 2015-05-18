package demo.core.domain;

import java.time.LocalDateTime;

/**
 * Created by fanjun on 15-3-23.
 */
public class IndexBanner {
    //首页图片
    private int id;
    private String name;
    private String path;
    private int sequence;
    private int limitnum;
    private LocalDateTime createtime;

    public IndexBanner(){

    }

    public IndexBanner(String name, String path, int sequence,int limitnum, LocalDateTime createtime) {
        this.name = name;
        this.path = path;
        this.sequence = sequence;
        this.limitnum = limitnum;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public int getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }
}
