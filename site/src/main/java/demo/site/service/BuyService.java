package demo.site.service;

import demo.core.domain.*;
import demo.core.persistence.*;
import demo.site.basic.exception.BusinessException;
import demo.site.basic.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 14/12/31.
 */
@Service
public class BuyService {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private Session session;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private PriceLadderMapper priceLadderMapper;
    @Autowired
    private Auth auth;

    //产品详细
    public void getSellInfoDetail(Integer id, Map<String, Object> model) {
        if(id == null) throw new NotFoundException();
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null || !sellInfo.getStatus().equals(EnumSellInfo.VerifyPass)) throw new NotFoundException();
        buyMapper.setPageViewTimesById(sellInfo.getViewtimes()+1, id);
        showJTJ(id, model);
        model.put("sellInfo", buyMapper.getSellInfoById(id));
    }

    //添加订单-自营
    @Transactional
    public int addZYOrder(Order order) {
        if (order == null) {
            throw new NotFoundException();
        } else {
            if(buyMapper.getSellInfoById(order.getSellinfoid()) == null) throw new NotFoundException();
            minusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            if(buyMapper.getSellInfoById(order.getSellinfoid()).getAvailquantity() < 0){
                throw new BusinessException("可用库存不足，请刷新网页，从新下单！");
            }
            orderMapper.addZYOrder(order);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "创建订单"));
            return order.getId();
        }
    }

    //添加订单-委托
    public int addWTOrder(Order order) {
        if (order == null) {
            throw new NotFoundException();
        } else {
            orderMapper.addWTOrder(order);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "创建订单"));
            return order.getId();
        }
    }

    //发布供应信息
    @Transactional
    public int addSellInfo(SellInfo sellInfo, int sid, int version, List<PriceLadder> priceLadderList, int jtjLast) {
        if (sellInfo == null) throw new NotFoundException();
        if(sid != 0) {
            if (buyMapper.getSellInfoById(sid) == null || buyMapper.setSellinfoStatus(EnumSellInfo.OutOfStack, sid, version) != 1){
                throw new BusinessException("重新发布供应信息-失败，供应信息已经被修改，请刷新后重试");
            }
        }
        buyMapper.addSellinfo(sellInfo);
        buyMapper.setParentIdById(sellInfo.getId(), sellInfo.getId());
        AddPriceLadder(priceLadderList, sellInfo.getId(), jtjLast);
        return sellInfo.getId();
    }

    //修改发布供应信息
    @Transactional
    public int addSellinfoForUpdate(SellInfo sellInfo, int sid, int version, List<PriceLadder> priceLadderList, int jtjLast) {
        if (sellInfo == null) throw new NotFoundException();
        if(buyMapper.getSellInfoById(sid) == null || buyMapper.setSellinfoStatus(EnumSellInfo.OutOfStack, sid, version) != 1){
            throw new BusinessException("更改供应信息-失败，供应信息已经被修改，请刷新后重试");
        }
        buyMapper.addSellinfoForUpdate(sellInfo);
        AddPriceLadder(priceLadderList, sellInfo.getId(), jtjLast);
        buyMapper.addSupplyVerify(new SupplyVerify(EnumSellInfo.WaitVerify, LocalDateTime.now(), sellInfo.getId(), session.getUser().getId()));
        return sellInfo.getId();
    }

    //更新供应信息
    @Transactional
    public void updateSellInfo(SellInfo sellInfo, List<PriceLadder> priceLadderList, int jtjLast) {
        if (sellInfo == null) throw new NotFoundException();
        if(buyMapper.updateSellinfo(sellInfo) != 1){
            throw new BusinessException("更改供应信息-失败，供应信息已经被修改，请刷新后重试");
        }
        priceLadderMapper.deletePriceLadderList(sellInfo.getId(), 1);
        AddPriceLadder(priceLadderList, sellInfo.getId(), jtjLast);
        buyMapper.addSupplyVerify(new SupplyVerify(EnumSellInfo.WaitVerify, LocalDateTime.now(), sellInfo.getId(), session.getUser().getId()));
    }

    public void AddPriceLadder(List<PriceLadder> priceLadderList, int id, int jtjLast){
        if(priceLadderList != null && priceLadderList.size() != 0){
            for(PriceLadder priceLadder : priceLadderList){
                priceLadder.setSellinfoid(id);
                priceLadder.setCreatetime(LocalDateTime.now());
                priceLadder.setUserid(session.getUser().getId());
                priceLadder.setUsername(session.getUser().getSecurephone());
                priceLadderMapper.addPriceLadder(priceLadder);
            }
            setJtjLastById(jtjLast, id);
        }
    }

    //删除供应信息
    public boolean deleteSellinfo(int id) {
        int rows = buyMapper.updateSellInfoStatus(EnumSellInfo.Deleted, null, id);
        return rows == 1 ? true : false;
    }

    //显示阶梯价
    public void showJTJ(int id, Map<String, Object> model) {
        List<PriceLadder> priceLadders = priceLadderMapper.getPriceLadderListBySellinfoId(id);
        switch (priceLadders.size()){
            case 0:
                break;
            case 5:
                model.put("jtj05Obj", priceLadders.get(4));
            case 4:
                model.put("jtj04Obj", priceLadders.get(3));
            case 3:
                model.put("jtj03Obj", priceLadders.get(2));
            case 2:
                model.put("jtj01Obj", priceLadders.get(0));
                model.put("jtj02Obj", priceLadders.get(1));
                break;
        }
    }


    //输出修改历史
    public void showEditHist(int id,int parentid, Map<String, Object> model) {
        List<SellInfo> list =  buyMapper.getSellInfoEditHist(id, parentid);
        for (SellInfo in : list){
            List<PriceLadder> priceLadders =  priceLadderMapper.getPriceLadderListBySellinfoId(in.getId()) ;
            if(priceLadders!=null && priceLadders.size()>0){
                in.setPricelist(priceLadders);
            }
        }
        if(list!=null && list.size()>0){
            model.put("editHistList",list);
        }
    }

    //更改sellinfo表中的阶梯价最低价
    public void setJtjLastById(int jtjlast, int id) {
        buyMapper.setJtjLastById(jtjlast, id);
    }

    //获取阶梯价
    public int getJTJByAmontId(int amount, int id) {
        List<PriceLadder> pricelist = priceLadderMapper.getPriceLadderListBySellinfoId(id);
        if (pricelist.size() == 0) throw new NotFoundException();
        int price = 0;
        for (int i = 0; i < pricelist.size(); i++) {
            if (amount <= pricelist.get(i).getAmount2() && amount > pricelist.get(i).getAmount1()) {
                price = pricelist.get(i).getPrice();
            }
        }
        if (price == 0) {
            price = pricelist.get(pricelist.size() - 1).getPrice();
        }
        return price;
    }

    //订单，修改可售库存，已售库存
    @Transactional
    public void minusSellinfoQuantity(int amount, int id) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null || amount <= 0) throw new NotFoundException();
        buyMapper.setAvailSoldAmount(sellInfo.getAvailquantity() - amount, sellInfo.getSoldquantity() + amount, id);
    }

    @Transactional
    public void plusSellinfoQuantity(int amount, int id) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null || amount <= 0) throw new NotFoundException();
        buyMapper.setAvailSoldAmount(sellInfo.getAvailquantity() + amount, sellInfo.getSoldquantity() - amount, id);
    }

    //create update Supply
    public void initCreateSupply(Map<String, Object> model) {
        List<Areaport> harbourlist = areaportMapper.getProcvincesOrPortsByParentid(5);
        harbourlist.add(new Areaport(10000, "其它"));
        List<Dictionary> inspectorgs = new ArrayList<Dictionary>();
        inspectorgs.add(new Dictionary(0, "inspectionagency", "无"));
        inspectorgs.addAll(dictionaryMapper.getAllInspectionagencys());
        inspectorgs.add(new Dictionary(100, "inspectionagency", "其它"));
        model.put("provincelist", areaportMapper.getAllProvince());
        model.put("harbourlist", harbourlist);
        model.put("deliverymodes", dictionaryMapper.getDeliverymodes());
        model.put("inspectorgs", inspectorgs);
        model.put("pnames", dictionaryMapper.getAllCoalTypes());
    }

    //create update Supply
    public void initUpdateSupply(String deliveryprovince, Map<String, Object> model) {
        List<Areaport> harbourlist = areaportMapper.getProcvincesOrPortsByParentname(deliveryprovince);
        harbourlist.add(new Areaport(10000, "其它"));
        List<Dictionary> inspectorgs = new ArrayList<Dictionary>();
        inspectorgs.add(new Dictionary(0, "inspectionagency", "无"));
        inspectorgs.addAll(dictionaryMapper.getAllInspectionagencys());
        inspectorgs.add(new Dictionary(100, "inspectionagency", "其它"));
        model.put("provincelist", areaportMapper.getAllProvince());
        model.put("harbourlist", harbourlist);
        model.put("deliverymodes", dictionaryMapper.getDeliverymodes());
        model.put("inspectorgs", inspectorgs);
        model.put("pnames", dictionaryMapper.getAllCoalTypes());
    }

    //Order 详细信息
    public void orderInfos(Order order, String reqsource, Map<String, Object> model) {
        auth.doCheckUserRight(order.getUserid(), order.getSellerid());
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if (sellInfo == null) throw new NotFoundException();
        Object check7days = 0;
        if (order.getDeliverytime1().minusDays(7).isAfter(LocalDate.now())) {
            check7days = 1;
        }
        model.put("check7days", check7days);
        model.put("orderInfo", order);
        model.put("sellInfo", sellInfo);
        model.put("reqsource", reqsource);
    }

    @Transactional
    public void confirmSellinfo(int id, int version) {
        if(buyMapper.setSellinfoStatus(EnumSellInfo.WaitVerify, id, version) != 1){
            throw new BusinessException("发布供应信息-失败，请刷新后重试");
        }
        buyMapper.addSupplyVerify(new SupplyVerify(EnumSellInfo.WaitVerify, LocalDateTime.now(), id, session.getUser().getId()));
    }
}

