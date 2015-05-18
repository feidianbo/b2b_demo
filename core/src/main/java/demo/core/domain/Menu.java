package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/3/4.
 */
public class Menu implements Serializable {
    private Integer id;
    private String menuname;
    private String url;
    private int parentid;
    private Integer sequence;

    public Menu(){}

    public Menu(String menuname, String url) {
        this.menuname = menuname;
        this.url = url;
    }

    public Menu(String menuname, String url, int parentid, int sequence) {
        this.menuname = menuname;
        this.url = url;
        this.parentid = parentid;
        this.sequence = sequence;
    }

    public Menu(int id, String menuname, String url, int parentid, int sequence) {
        this.id = id;
        this.menuname = menuname;
        this.url = url;
        this.parentid = parentid;
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
