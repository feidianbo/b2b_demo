package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fanjun on 15-2-8.
 */
public class ChartConfine implements Serializable {
    private int id;
    private String type;
    private double highlimit;
    private double lowlimit;

    public ChartConfine(){

    }

    public ChartConfine(String type, double highlimit, double lowlimit) {
        this.type = type;
        this.highlimit = highlimit;
        this.lowlimit = lowlimit;
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

    public double getHighlimit() {
        return highlimit;
    }

    public void setHighlimit(double highlimit) {
        this.highlimit = highlimit;
    }

    public double getLowlimit() {
        return lowlimit;
    }

    public void setLowlimit(double lowlimit) {
        this.lowlimit = lowlimit;
    }
}
