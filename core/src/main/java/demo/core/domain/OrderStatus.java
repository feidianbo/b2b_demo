package demo.core.domain;

import java.io.Serializable;

/**
 * Created by zhangbolun on 15/1/28.
 */
public enum OrderStatus implements Serializable {
    ORDER_CREATE, //生成订单
    ORDER_ACTIVE, //团购订单进行中
    ORDER_FINISH, //团购订单结束
    ORDER_FAIL,   //团购失败
}

