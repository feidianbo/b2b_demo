package demo.core.domain;

import java.math.BigDecimal;

/**
 * Created by xiangyang on 15-1-10.
 */
public class CoalBaseData {
    //低位热值
    public Integer NCV;
    //空干基硫份
    public BigDecimal ADS;
    //含糖量
    public BigDecimal RS;
    //全水分
    public BigDecimal TM;
    //内水分
    public BigDecimal IM;
    //酒类指标2
    public BigDecimal ADV;
    //酒类指标3
    public BigDecimal RV;
    //酒类指标7
    public Integer AFT;
    //酒类指标6
    public BigDecimal ASH;
    //酒类指标8
    public Integer HGI;

    @Override
    public String toString() {
        return "CoalBaseData{" +
                "NCV=" + NCV +
                ", ADS=" + ADS +
                ", RS=" + RS +
                ", TM=" + TM +
                ", IM=" + IM +
                ", ADV=" + ADV +
                ", RV=" + RV +
                ", AFT=" + AFT +
                ", ASH=" + ASH +
                ", HGI=" + HGI +
                '}';
    }

    public CoalBaseData(){}

    public CoalBaseData(Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, Integer AFT, BigDecimal ASH, Integer HGI) {
        this.NCV = NCV;
        this.ADS = ADS;
        this.RS = RS;
        this.TM = TM;
        this.IM = IM;
        this.ADV = ADV;
        this.RV = RV;
        this.AFT = AFT;
        this.ASH = ASH;
        this.HGI = HGI;
    }

    public Integer getNCV() {
        return NCV;
    }

    public void setNCV(Integer NCV) {
        this.NCV = NCV;
    }

    public BigDecimal getADS() {
        return ADS;
    }

    public void setADS(BigDecimal ADS) {
        this.ADS = ADS;
    }

    public BigDecimal getRS() {
        return RS;
    }

    public void setRS(BigDecimal RS) {
        this.RS = RS;
    }

    public BigDecimal getTM() {
        return TM;
    }

    public void setTM(BigDecimal TM) {
        this.TM = TM;
    }

    public BigDecimal getIM() {
        return IM;
    }

    public void setIM(BigDecimal IM) {
        this.IM = IM;
    }

    public BigDecimal getADV() {
        return ADV;
    }

    public void setADV(BigDecimal ADV) {
        this.ADV = ADV;
    }

    public BigDecimal getRV() {
        return RV;
    }

    public void setRV(BigDecimal RV) {
        this.RV = RV;
    }

    public Integer getAFT() {
        return AFT;
    }

    public void setAFT(Integer AFT) {
        this.AFT = AFT;
    }

    public BigDecimal getASH() {
        return ASH;
    }

    public void setASH(BigDecimal ASH) {
        this.ASH = ASH;
    }

    public Integer getHGI() {
        return HGI;
    }

    public void setHGI(Integer HGI) {
        this.HGI = HGI;
    }
}
