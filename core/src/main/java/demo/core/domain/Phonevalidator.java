package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by fanjun on 14-11-5.
 */
public class Phonevalidator implements Serializable {

    private int id;
    private LocalDateTime expiretime;
    private String phone;
    private String code;
    private int userid;
    private String contextjson;
    private LocalDateTime validatetime;
    private int validated;
    private ValidateType validatetype;
    public Phonevalidator(){

    }

    public Phonevalidator(LocalDateTime expiretime, String phone, String code, int userid, String contextjson,
                          int validated, ValidateType validatetype){

        this.expiretime = expiretime;
        this.phone= phone;
        this.code = code;
        this.userid = userid;
        this.contextjson = contextjson;
        this.validated = validated;
        this.validatetype =validatetype;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContextjson() {
        return contextjson;
    }

    public void setContextjson(String contextjson) {
        this.contextjson = contextjson;
    }

	public LocalDateTime getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(LocalDateTime expiretime) {
		this.expiretime = expiretime;
	}

	public LocalDateTime getValidatetime() {
		return validatetime;
	}

	public void setValidatetime(LocalDateTime validatetime) {
		this.validatetime = validatetime;
	}

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    public ValidateType getValidatetype() {
        return validatetype;
    }

    public void setValidatetype(ValidateType validatetype) {
        this.validatetype = validatetype;
    }
}
