package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/1/25.
 */
public enum EnumOrder implements Serializable {
    WaitConfirmed,           //订单状态：待确认
    WaitSignContract,        //订单状态：待签合同 -- 进行中
    WaitPayment,             //订单状态：待付款 -- 进行中
    WaitVerify,              //订单状态：待审核（审核中） -- 进行中
    VerifyPass,              //订单状态：审核通过（交割中） -- 进行中
    VerifyNotPass,           //订单状态：审核未通过 -- 进行中
    WaitBalancePayment,      //订单状态：待付尾款 -- 进行中
    ReturnGoods,             //订单状态：退货中 -- 退货中
    MakeMatch,               //订单状态：撮合中 -- 进行中
    Canceled,                //订单状态：已取消 -- 已取消
    Deleted,                 //订单状态：已删除
    Completed,               //订单状态：已完成 -- 已完成
    ReturnCompleted,         //订单状态：退货完成 -- 已完成

    NULL,                    //订单状态：空，

    PayTheWhole,             //付款类型：付全款
    PayCashDeposit,          //付款类型：付10%保证金

    MallOrder,               //订单类型：自营单，商城订单
    OtherOrder,              //订单类型：委托单，其它卖家订单
}
