package demo.core.domain;

import java.time.LocalDateTime;

/**
 * Created by joe on 2/8/15.
 */
public class UploadFileByUser {
    public final static String tablename="uploadfilebyuser";
    protected int id;
    protected int userid;
    protected String filepath;
    protected LocalDateTime createtime;

    public UploadFileByUser() {
    }

    public UploadFileByUser(int userid, String filepath) {
        this.userid = userid;
        this.filepath = filepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
}
