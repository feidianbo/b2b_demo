package demo.core.domain;

import java.time.LocalDateTime;

/**
 * Created by joe on 2/8/15.
 */
public class UploadFileByAdmin {
    public final static String tablename="uploadfilebyadmin";
    protected int id;
    protected int adminid;
    protected String filepath;
    protected LocalDateTime createtime;

    public UploadFileByAdmin() {
    }

    public UploadFileByAdmin(int adminid, String filepath) {
        this.adminid = adminid;
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

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
}
