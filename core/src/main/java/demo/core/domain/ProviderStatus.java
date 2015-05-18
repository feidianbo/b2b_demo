package demo.core.domain;

import java.io.Serializable;

/**
 * Created by zhangbolun on 15/1/28.
 */
public enum ProviderStatus implements Serializable {
    PROVIDER_ACTIVE, //无正在进行的团购 可以被删除
    PROVIDER_FROZEN,  //有正在进行的团购 不能被删除
}
