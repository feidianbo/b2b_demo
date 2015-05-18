package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by zhangbolun on 15/1/27.
 */
public class GroupBuyOrderShow implements Serializable {
    private String groupbuyordercode;        //团购订单编号
    private int volume;                      //成交量，团购数量
    private LocalDate enddate;               //团购截止日期
    private BigDecimal groupbuyprice;        //团购价格
    private String  qualificationcode;        //团购资质编号

    public GroupBuyOrderShow(){}

    public GroupBuyOrderShow(String groupbuyordercode, int volume, LocalDate enddate, BigDecimal groupbuyprice,String  qualificationcode) {
        this.groupbuyordercode = groupbuyordercode;
        this.volume = volume;
        this.enddate = enddate;
        this.groupbuyprice = groupbuyprice;
        this.qualificationcode=qualificationcode;
    }

    public String getGroupbuyordercode() {
        return groupbuyordercode;
    }

    public void setGroupbuyordercode(String groupbuyordercode) {
        this.groupbuyordercode = groupbuyordercode;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public BigDecimal getGroupbuyprice() {
        return groupbuyprice;
    }

    public void setGroupbuyprice(BigDecimal groupbuyprice) {
        this.groupbuyprice = groupbuyprice;
    }

    public String getQualificationcode() {
        return qualificationcode;
    }

    public void setQualificationcode(String qualificationcode) {
        this.qualificationcode = qualificationcode;
    }
}
