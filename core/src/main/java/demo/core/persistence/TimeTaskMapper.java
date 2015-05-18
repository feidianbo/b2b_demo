package demo.core.persistence;

import demo.core.domain.*;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanjun on 15-1-27.
 */
public interface TimeTaskMapper {

    /* *******************系统定时任务sql ********************/

    //每天0点检查修改昨天报价截止的需求记录改为报价结束
    @Update("update demands set tradestatus='报价结束' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyTradestatusTask();

    //将超过7天的记录状态改为删除
    @Update("update demands set isdelete=1 where noshowdate = date_format(now(),'%Y-%m-%d')")
    public void modifyIsdeleteTask();

    //过了报价截止时间后,交易状态改为匹配中
    @Update("update mydemands set status='匹配中' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyStatusToMatchingTask();

    //修改我的需求状态-交易结束
    @Update("update mydemands set status='交易结束' where status='匹配中' and (date_add(deliverydate,interval 1 day)=date_format(now(),'%Y-%m-%d') or date_add(deliverydateend,interval 1 day)=date_format(now(),'%Y-%m-%d'))")
    public void modifyStatusToTradeOverTask();

    //将报价表到了报价截止日期的记录改为比价中
    @Update("update quotes set status='比价中' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyStatusToCompareQuoteTask();

    //将报价表过了提货时间截止仍没有中标的改为未中标
    @Update("update quotes set status='未中标' where status='比价中' and (date_add(deliverydate,interval 1 day)=date_format(now(),'%Y-%m-%d') or date_add(deliverydateend,interval 1 day)=date_format(now(),'%Y-%m-%d'))")
    public void modifyStatusToNotBidTask();

    /* *********************************************************** */


}
