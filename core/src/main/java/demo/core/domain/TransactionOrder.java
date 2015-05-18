package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 成交记录
 * Created by zhangbolun on 15/1/27.
 */
public class TransactionOrder implements Serializable {
    private String coaltype;                 //煤种
    private String port;                     //港口
    private BigDecimal marketprice;          //市场价格
    private BigDecimal groupbuyprice;        //团购价格
    private LocalDateTime deliverytime;          //交货时间,提货时间
    private int volume;                      //成交量，团购数量

    public TransactionOrder(){}

    public TransactionOrder(String coaltype, String port, BigDecimal marketprice, BigDecimal groupbuyprice, LocalDateTime deliverytime, int volume) {
        this.coaltype = coaltype;
        this.port = port;
        this.marketprice = marketprice;
        this.groupbuyprice = groupbuyprice;
        this.deliverytime = deliverytime;
        this.volume = volume;
    }

    public String getCoaltype() {
        return coaltype;
    }

    public void setCoaltype(String coaltype) {
        this.coaltype = coaltype;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public BigDecimal getGroupbuyprice() {
        return groupbuyprice;
    }

    public void setGroupbuyprice(BigDecimal groupbuyprice) {
        this.groupbuyprice = groupbuyprice;
    }

    public LocalDateTime getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(LocalDateTime deliverytime) {
        this.deliverytime = deliverytime;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
