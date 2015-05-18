package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.core.domain.*;
import demo.core.persistence.GroupBuyOrderMapper;
import demo.core.persistence.GroupBuyQualificationMapper;
import demo.core.persistence.GroupBuySupplyMapper;
import demo.core.persistence.TimeTaskMapper;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanjun on 15-1-27.
 */
@Controller
public class TimeTaskController {

    @Autowired
    protected TimeTaskMapper timeTaskMapper;
    @Autowired
    private GroupBuyQualificationMapper groupBuyQualifyMapper;
    @Autowired
    private GroupBuySupplyMapper groupBuySupplyMapper;
    @Autowired
    private GroupBuyOrderMapper groupBuyOrderMapper;

    //手动触发定时任务
    @RequestMapping("/startTimeTasks")
    @VerifyAuthentication(Operation = true,Admin = true)
    @ResponseBody
    public Object startTimeTasks(){
        timeTaskMapper.modifyTradestatusTask();
        timeTaskMapper.modifyIsdeleteTask();
        timeTaskMapper.modifyStatusToMatchingTask();
        timeTaskMapper.modifyStatusToTradeOverTask();
        timeTaskMapper.modifyStatusToCompareQuoteTask();
        timeTaskMapper.modifyStatusToNotBidTask();
        updateGroupBuyStatus();
        boolean success = true;
        return JSON.toString(success);
    }


    public void updateGroupBuyStatus(){
        List<GroupBuySupply> groupBuySupplies= groupBuySupplyMapper.getGroupBuySuppliesByStatus(GroupBuySupplyStatus.GROUP_BUY_SUPPLY_INPROGRESS.toString());
        List<GroupBuySupply> deadlineList=new ArrayList<GroupBuySupply>();
        for(GroupBuySupply gbs:groupBuySupplies){
            if(gbs.getGroupbuyenddate().isBefore(LocalDateTime.now())){
                deadlineList.add(gbs);
            }
        }
        if(deadlineList.size()>0){
            for(GroupBuySupply gs:deadlineList) {
                if(gs.getSupplyamount()==gs.getSelledamount()&&gs.getSurplusamount()==0) {
                    groupBuySupplyMapper.updateStatusById(gs.getId(), GroupBuySupplyStatus.GROUP_BUY_SUPPLY_FINISH.toString());
                    List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getOrdersBySupplyId(gs.getId());
                    if (groupBuyOrders != null) {
                        for (GroupBuyOrder bgo : groupBuyOrders) {
                            groupBuyOrderMapper.updateStatusById(bgo.getId(), OrderStatus.ORDER_FINISH.toString());
                            groupBuyQualifyMapper.updateStatusByCode(bgo.getQualificationcode(), QualifyStatus.QUALIFY_FINISH.toString());
                        }
                    }
                }else {
                    groupBuySupplyMapper.updateStatusById(gs.getId(), GroupBuySupplyStatus.GROUP_BUY_SUPPLY_FAIL.toString());
                    List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getOrdersBySupplyId(gs.getId());
                    if (groupBuyOrders != null) {
                        for (GroupBuyOrder bgo : groupBuyOrders) {
                            groupBuyOrderMapper.updateStatusById(bgo.getId(), OrderStatus.ORDER_FAIL.toString());
                            groupBuyQualifyMapper.updateStatusByCode(bgo.getQualificationcode(), QualifyStatus.QUALIFY_ACTIVE.toString());
                        }
                    }
                }
            }
        }

    }
}
