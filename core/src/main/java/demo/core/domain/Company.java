package demo.core.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by fanjun on 14-11-12.
 */
public class Company implements Serializable {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    private String fax;
   /* private String contactphone;          //联系人手机*/
    @NotNull
    private String legalperson;           //公司法人
    @NotNull
    private String businesslicense;       //营业执照
    @NotNull
    private String identificationnumber;  //纳税人识别号
    @NotNull
    private String organizationcode;      //机构代码
    private String operatinglicense;      //经营许可证
    @NotNull
	private int userid;
    private String verifystatus;          //公司信息审核状态
    private String remarks;               //审核未通过理由
    private String traderphone;           //交易员手机
    @NotNull
    private String legalpersonname;       //公司法人姓名
    @NotNull
    private String account;               //银行账号
    @NotNull
    private String openingbank;           //开户银行
    @NotNull
    private String identificationnumword; //纳税人识别号(文字)
    @NotNull
    private String zipcode;               //邮编
    @NotNull
    private String openinglicense;        //开户许可证
    private String invoicinginformation;  //企业开票信息

    public Company(){

    }

    public Company(String name,String address,String fax,
                   String legalperson,String businesslicense,String identificationnumber,
                   String organizationcode,String operatinglicense,int userid,String legalpersonname,String account,String openingbank, String traderphone){
        this.name = name;
        this.address = address;
        this.fax = fax;
        this.legalperson = legalperson;
        this.businesslicense = businesslicense;
        this.identificationnumber = identificationnumber;
        this.organizationcode = organizationcode;
        this.operatinglicense = operatinglicense;
        this.userid = userid;
        this.legalpersonname = legalpersonname;
        this.account = account;
        this.openingbank = openingbank;
        this.traderphone = traderphone;
    }

    public Company(String name, String address, String phone, String fax,
                   String legalperson, String businesslicense, String identificationnumber,
                   String organizationcode, String operatinglicense, int userid, String legalpersonname, String account, String openingbank){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.legalperson = legalperson;
        this.businesslicense = businesslicense;
        this.identificationnumber = identificationnumber;
        this.organizationcode = organizationcode;
        this.operatinglicense = operatinglicense;
        this.userid = userid;
        this.legalpersonname = legalpersonname;
        this.account = account;
        this.openingbank = openingbank;
    }

    public Company(String name, String address, String legalperson, String businesslicense,
                   String identificationnumber, String organizationcode, String operatinglicense,
                   int userid, String legalpersonname, String account, String openingbank,
                   String traderphone, String identificationnumword, String zipcode){
        this.name = name;
        this.address = address;
        this.legalperson = legalperson;
        this.businesslicense = businesslicense;
        this.identificationnumber = identificationnumber;
        this.organizationcode = organizationcode;
        this.operatinglicense = operatinglicense;
        this.userid = userid;
        this.legalpersonname = legalpersonname;
        this.account = account;
        this.openingbank = openingbank;
        this.traderphone = traderphone;
        this.identificationnumword = identificationnumword;
        this.zipcode = zipcode;
    }

    public Company(String name, String address, String phone, String fax, String invoicinginformation,
                   String businesslicense, String identificationnumber, String organizationcode,
                   String operatinglicense, String openinglicense, String legalpersonname,  String account,
                   String openingbank, int userid, String identificationnumword,
                   String zipcode) {
        this.account = account;
        this.address = address;
        this.businesslicense = businesslicense;
        this.phone = phone;
        this.fax = fax;
        this.identificationnumber = identificationnumber;
        this.invoicinginformation = invoicinginformation;
        this.legalpersonname = legalpersonname;
        this.name = name;
        this.openingbank = openingbank;
        this.operatinglicense = operatinglicense;
        this.openinglicense = openinglicense;
        this.organizationcode = organizationcode;
        this.userid = userid;
        this.identificationnumword = identificationnumword;
        this.zipcode = zipcode;
    }

    public Company(String name, String address, String account, String businesslicense, String fax, String identificationnumber, String legalperson, String legalpersonname, String openingbank, String operatinglicense, String organizationcode, String phone, String remarks, String traderphone, int userid, String verifystatus) {
        this.account = account;
        this.address = address;
        this.businesslicense = businesslicense;
        this.fax = fax;
        this.identificationnumber = identificationnumber;
        this.legalperson = legalperson;
        this.legalpersonname = legalpersonname;
        this.name = name;
        this.openingbank = openingbank;
        this.operatinglicense = operatinglicense;
        this.organizationcode = organizationcode;
        this.phone = phone;
        this.remarks = remarks;
        this.traderphone = traderphone;
        this.userid = userid;
        this.verifystatus = verifystatus;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
    }

    public String getIdentificationnumber() {
        return identificationnumber;
    }

    public void setIdentificationnumber(String identificationnumber) {
        this.identificationnumber = identificationnumber;
    }

    public String getOrganizationcode() {
        return organizationcode;
    }

    public void setOrganizationcode(String organizationcode) {
        this.organizationcode = organizationcode;
    }

    public String getOperatinglicense() {
        return operatinglicense;
    }

    public void setOperatinglicense(String operatinglicense) {
        this.operatinglicense = operatinglicense;
    }

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

    public String getVerifystatus() {
        return verifystatus;
    }

    public void setVerifystatus(String verifystatus) {
        this.verifystatus = verifystatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTraderphone() {
        return traderphone;
    }

    public void setTraderphone(String traderphone) {
        this.traderphone = traderphone;
    }

    public String getLegalpersonname() {
        return legalpersonname;
    }

    public void setLegalpersonname(String legalpersonname) {
        this.legalpersonname = legalpersonname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOpeningbank() {
        return openingbank;
    }

    public void setOpeningbank(String openingbank) {
        this.openingbank = openingbank;
    }

    public String getIdentificationnumword() {
        return identificationnumword;
    }

    public void setIdentificationnumword(String identificationnumword) {
        this.identificationnumword = identificationnumword;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getOpeninglicense() {
        return openinglicense;
    }

    public void setOpeninglicense(String openinglicense) {
        this.openinglicense = openinglicense;
    }

    public String getInvoicinginformation() {
        return invoicinginformation;
    }

    public void setInvoicinginformation(String invoicinginformation) {
        this.invoicinginformation = invoicinginformation;
    }
}
