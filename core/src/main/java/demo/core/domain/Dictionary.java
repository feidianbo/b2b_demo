package demo.core.domain;

import java.io.Serializable;

/**
 * Created by fanjun on 14-11-25.
 */
public class Dictionary implements Serializable {
    private int id;
    private String code;
    private String name;

    public Dictionary(){

    }

    public Dictionary(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Dictionary(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
