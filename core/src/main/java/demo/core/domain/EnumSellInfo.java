package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/1/26.
 */
public enum  EnumSellInfo implements Serializable {
    WaitConfirmed,               //待确认
    WaitVerify,                  //待审核
    VerifyPass,                  //审核通过
    VerifyNotPass,               //审核未通过
    Canceled,                    //已取消
    Deleted,                     //已删除
    SellOut,                     //匹配完成
    OutOfDate,                   //已过期
    OutOfStack,                  //已下架

    Recommend,                   //产品类型  producttype 推荐
    Common,                      //产品类型  正常
}
