package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by fanjun on 15-2-7.
 */
public class Chart implements Serializable {

    //图表实体类
    private int id;
    private String type;             //图表类型
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate tradedate;        //交易日
    private double averageprice;    //平均价格
    private LocalDateTime createtime;   //创建时间

    public Chart(){

    }

    public Chart(String type, LocalDate tradedate, double averageprice, LocalDateTime createtime) {
        this.type = type;
        this.tradedate = tradedate;
        this.averageprice = averageprice;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getTradedate() {
        return tradedate;
    }

    public void setTradedate(LocalDate tradedate) {
        this.tradedate = tradedate;
    }

    public double getAverageprice() {
        return averageprice;
    }

    public void setAverageprice(double averageprice) {
        this.averageprice = averageprice;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
}
