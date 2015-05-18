package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/1/9.
 */
public class DealerBackup extends Dealer implements Serializable {
    private boolean iswork;

    public DealerBackup(String dealerid, String dealername, String dealerphone, String deliveryregion, String deliveryprovince, String deliveryplace, int lastupdatemanid, String lastupdatemanname, boolean iswork) {
        super(dealerid, dealername, dealerphone, deliveryregion, deliveryprovince, deliveryplace, lastupdatemanid, lastupdatemanname);
        this.iswork = iswork;
    }

    public boolean isIswork() {
        return iswork;
    }

    public void setIswork(boolean iswork) {
        this.iswork = iswork;
    }
}
