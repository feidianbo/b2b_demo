package demo.core.domain;

import java.math.BigDecimal;

/**
 * Created by xiangyang on 15-1-14.
 */
public class Customer {
    private int id;
    private String nickname;
    private String companyName;
    private String contact;
    private String phone;
    //商城订单量
    private int shopOrderCount;
    //撮合订单量
    private int unShopOrderCount;
    //总发布需求量
    private int releaseDemandCount;
    //发布需求匹配瓶数
    private int demandMatchCount;
    //总报价个数
    private int quoteCount;
    //有效报价个数
    private int validQuoteCount;
    //总供应量
    private int supplyCount;
    //总匹配供应量
    private int matchSupplyCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getShopOrderCount() {
        return shopOrderCount;
    }

    public void setShopOrderCount(int shopOrderCount) {
        this.shopOrderCount = shopOrderCount;
    }

    public int getUnShopOrderCount() {
        return unShopOrderCount;
    }

    public void setUnShopOrderCount(int unShopOrderCount) {
        this.unShopOrderCount = unShopOrderCount;
    }

    public int getReleaseDemandCount() {
        return releaseDemandCount;
    }

    public void setReleaseDemandCount(int releaseDemandCount) {
        this.releaseDemandCount = releaseDemandCount;
    }

    public int getDemandMatchCount() {
        return demandMatchCount;
    }

    public void setDemandMatchCount(int demandMatchCount) {
        this.demandMatchCount = demandMatchCount;
    }

    public int getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount) {
        this.quoteCount = quoteCount;
    }

    public int getValidQuoteCount() {
        return validQuoteCount;
    }

    public void setValidQuoteCount(int validQuoteCount) {
        this.validQuoteCount = validQuoteCount;
    }

    public int getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(int supplyCount) {
        this.supplyCount = supplyCount;
    }

    public int getMatchSupplyCount() {
        return matchSupplyCount;
    }

    public void setMatchSupplyCount(int matchSupplyCount) {
        this.matchSupplyCount = matchSupplyCount;
    }
}
