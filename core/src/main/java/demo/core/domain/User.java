package demo.core.domain;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

	private int id;
	@NotNull(message = "手机号码不为空")
	private String securephone;
	private String nickname;
	@NotNull(message = "用户名密码不为空")
	private String password;
	private LocalDateTime registertime;
	private boolean isactive;
	private String verifystatus;
	private String qq;     //QQ号
	private String telephone; //固定电话

	public User(){}

	public User(String securephone, String password, LocalDateTime registertime, boolean isactive) {
		this.securephone = securephone;
		this.password = password;
		this.registertime = registertime;
		this.isactive = isactive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecurephone() {
		return securephone;
	}

	public void setSecurephone(String securephone) {
		this.securephone = securephone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegistertime() {
		return registertime;
	}

	public void setRegistertime(LocalDateTime registertime) {
		this.registertime = registertime;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getVerifystatus() {
		return verifystatus;
	}

	public void setVerifystatus(String verifystatus) {
		this.verifystatus = verifystatus;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
