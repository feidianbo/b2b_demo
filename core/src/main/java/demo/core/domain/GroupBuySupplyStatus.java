package demo.core.domain;

import java.io.Serializable;

/**
 * Created by zhangbolun on 15/1/28.
 */
public enum GroupBuySupplyStatus implements Serializable {
    GROUP_BUY_SUPPLY_RELEASE,    //团购信息发布
    GROUP_BUY_SUPPLY_INPROGRESS, //团购进行中
    GROUP_BUY_SUPPLY_FINISH,     //团购结束
    GROUP_BUY_SUPPLY_FAIL,       //团购失败
    GROUP_BUY_SUPPLY_GIVEUP,     //放弃团购，下架
}
