package demo.core.domain;

import java.util.List;
import java.util.Set;

/**
 * Created by xiangyang on 15-3-16.
 *
 */
public class SellDistribute {

    private String sell;
    private Set<String> deliverys;
    private Set<Integer> ncvs;

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public Set<String> getDeliverys() {
        return deliverys;
    }

    public void setDeliverys(Set<String> deliverys) {
        this.deliverys = deliverys;
    }


    public Set<Integer> getNcvs() {
        return ncvs;
    }

    public void setNcvs(Set<Integer> ncvs) {
        this.ncvs = ncvs;
    }

    public SellDistribute() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellDistribute that = (SellDistribute) o;

        if (deliverys != null ? !deliverys.equals(that.deliverys) : that.deliverys != null) return false;
        if (ncvs != null ? !ncvs.equals(that.ncvs) : that.ncvs != null) return false;
        if (sell != null ? !sell.equals(that.sell) : that.sell != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sell != null ? sell.hashCode() : 0;
        result = 31 * result + (deliverys != null ? deliverys.hashCode() : 0);
        result = 31 * result + (ncvs != null ? ncvs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SellDistribute{" +
                "sell='" + sell + '\'' +
                ", deliverys=" + deliverys +
                ", ncvs=" + ncvs +
                '}';
    }
}
