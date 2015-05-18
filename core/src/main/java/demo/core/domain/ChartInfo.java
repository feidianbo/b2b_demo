package demo.core.domain;

import java.io.Serializable;

/**
 * Created by Enoy on 15/3/31.
 */
public class ChartInfo implements Serializable{

    /*** The size of chart data. */
    private int size;

    /*** All the date for this chart */
    private String dateAll;

    /*** All the price for this chart */
    private String priceAll;

    /*** Max Price */
    private double max;

    /*** Min Price */
    private double min;

    /**
     * Chart Confine Offset
     * Offset:(max-min)/10
     */
    private double offset;

    public ChartInfo() {

    }

    public ChartInfo(int size, String dateAll, String priceAll, double max, double min, double offset) {
        this.size = size;
        this.dateAll = dateAll;
        this.priceAll = priceAll;
        this.max = max;
        this.min = min;
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDateAll() {
        return dateAll;
    }

    public void setDateAll(String dateAll) {
        this.dateAll = dateAll;
    }

    public String getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(String priceAll) {
        this.priceAll = priceAll;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }
}
