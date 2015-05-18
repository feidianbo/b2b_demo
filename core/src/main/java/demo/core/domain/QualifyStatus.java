package demo.core.domain;

import java.io.Serializable;

/**
 * Created by zhangbolun on 15/1/28.
 */
public enum QualifyStatus implements Serializable {
    QUALIFY_START,     //团购资质， 开始， 最初状态
    QUALIFY_APPLY,     //申请中，审核中
    QUALIFY_NOT_ENOUGH,//审核拒绝，金额不足not enough
    QUALIFY_ACTIVE,    //已激活，可使用
    QUALIFY_INPROCESS, //已绑定，进行中的团购
    QUALIFY_FINISH,    //已结束，
    QUALIFY_CANCEL,    //资质作废,
    QUALIFY_GIVEUP,    //申请放弃资质，
    QUALIFY_GIVEUPED,  //审核通过放弃资质
}
