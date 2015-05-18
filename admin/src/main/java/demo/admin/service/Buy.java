package demo.admin.service;

import demo.admin.basic.exception.BusinessException;
import demo.admin.basic.exception.NotFoundException;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.service.CODE;
import demo.core.service.SMS;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jack on 15/1/9.
 */
@Service
public class Buy {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private PriceLadderMapper priceLadderMapper;
    @Autowired
    private Session session;
    @Autowired
    private DealerMapper dealerMapper;
    @Autowired
    private SMS sms;
    @Autowired
    private CODE code;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;


    //发布供应信息
    @Transactional
    public int addSellInfo(SellInfo sellInfo, List<PriceLadder> priceLadderList){
        if(sellInfo == null) throw new NotFoundException();
        buyMapper.addSellinfo(sellInfo);
        AddPriceLadder(priceLadderList, sellInfo.getId());
        return sellInfo.getId();
    }

    public void AddPriceLadder(List<PriceLadder> priceLadderList, int id){
        if(priceLadderList != null && priceLadderList.size() != 0){
            for(PriceLadder priceLadder : priceLadderList){
                priceLadder.setSellinfoid(id);
                priceLadder.setCreatetime(LocalDateTime.now());
                priceLadder.setUserid(session.getAdmin().getId());
                priceLadder.setUsername(session.getAdmin().getUsername());
                priceLadderMapper.addPriceLadder(priceLadder);
            }
        }
    }

    //更新供应信息
    @Transactional
    public void updateSellInfo(SellInfo sellInfo, int sid, int version, List<PriceLadder> priceLadderList){
        if(sellInfo == null) throw new NotFoundException();
        if(buyMapper.updateSellinfo(sellInfo) != 1){
            throw new BusinessException("更改供应信息 - 失败！ 请先刷新页面，再修改");
        }
        priceLadderMapper.deletePriceLadderList(sellInfo.getId(), 1);
        AddPriceLadder(priceLadderList, sellInfo.getId());
    }

    //删除供应信息
    public boolean deleteSellinfo(int id){
        int rows = buyMapper.updateSellInfoStatus(EnumSellInfo.Deleted, null, id);
        return rows==1 ? true:false;
    }

    //删除阶梯价-多个
    public boolean deletePriceLadderList(int sellinfoid, int squence){
        if(priceLadderMapper.countPriceLadderBySquence(sellinfoid, squence) > 0){
            int rows = priceLadderMapper.deletePriceLadderList(sellinfoid, squence);
            return rows>0 ? true:false;
        }
        return false;
    }

    //更新多个阶梯价
    public void updatePriceLadderList(int sellinfoid, int price, int amount1, int amount2, int squence){
        if(priceLadderMapper.getPriceLadderBySellinfoIdSquence(sellinfoid, squence) == null){
            priceLadderMapper.addPriceLadder(new PriceLadder(sellinfoid, squence, price, amount1, amount2, LocalDateTime.now(), session.getAdmin().getId(), session.getAdmin().getUsername()));
        } else{
            priceLadderMapper.updatePriceLadder(price, amount1, amount2, sellinfoid, squence);
        }
    }

    //获取交易员
    public Dealer doGetDealer(String region, String province, String harbour){
        List<Dealer> dealerList = dealerMapper.getDealerByProvincePlace(province, harbour);
        if(dealerList.size() == 0){
            dealerList = dealerMapper.getDealerByDeliveryprovince(province);
            if(dealerList.size() == 0){
                dealerList = dealerMapper.getDealerByDeliveryregion(region);
                if(dealerList.size() == 0){
                    dealerList = dealerMapper.getYMWKFDealer();
                }
            }
        }
        return dealerList.get(0);
    }

    //添加交易员
    public boolean addDealer(Dealer dealer, String username, String hellowords, String signature) throws Exception {
        dealerMapper.addDealer(dealer);
        if(adminMapper.getByUsername(username) != null){
            username = username + dealer.getId();
        }
        String pwdcode = code.CreateCode();
        String password = DigestUtils.md5Hex(pwdcode);
        Admin admin = new Admin(username, password, true, dealer.getDealerphone(), dealer.getDealerid(), dealer.getDealername());
        adminMapper.addAdmin(admin);
        userRoleMapper.insertUserRole(new UserRole(roleMapper.getRoleByRolename("交易员").getId(), admin.getId()));
        updateDealers(dealer);
        String content = ": 女士/先生，你好，你现在已经是交易员，负责：" + dealer.getDeliveryregion() + dealer.getDeliveryprovince() + dealer.getDeliveryplace() + "，你现在可以登录管理员平台查看相关工作信息。管理平台登陆账号：" + username + "，密码：" + pwdcode + "。";
        sms.send(dealer.getDealerphone(), content, hellowords, signature);
        return true;
    }


    public void updateDealers(Dealer dealer){
        List<SellInfo> sellInfoList = buyMapper.getSellInfoByRegionProvinceHarbour(dealer.getDeliveryregion(), dealer.getDeliveryprovince(), dealer.getDeliveryplace());
        List<Demand> demandList = demandMapper.getDemandListByRegionProvinceHarbour(dealer.getDeliveryregion(), dealer.getDeliveryprovince(), dealer.getDeliveryplace());
        for (SellInfo sellInfo : sellInfoList) {
            buyMapper.updateSellinfoDealer(dealer.getDealerid(), dealer.getDealername(), dealer.getDealerphone(), sellInfo.getId());
        }
        for(Demand demand : demandList) {
            demandMapper.updateDemandDealer(dealer.getDealerid(), dealer.getDealername(), dealer.getDealerphone(), demand.getId());
        }
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

    @Transactional
    public void updateSellInfoStatusByIdVersion(int id, int version) {
        if(buyMapper.setSellinfoStatus(EnumSellInfo.Canceled, id, version) != 1){
            throw new BusinessException("取消供应信息 - 失败,供应信息已经被修改，请刷新后重试");
        }
    }

    @Transactional
    public void verifySellinfo(int checkResult, int id, int version, Integer dealerId, String comment, SupplyVerify supplyVerify) {
        if (checkResult == 0) {
            Dealer dealer=dealerMapper.findDealerById(dealerId);
            if(buyMapper.verifySellinfoStatus(dealer.getDealerphone(), dealer.getDealername(), EnumSellInfo.VerifyPass, comment, LocalDateTime.now(), id, version, dealerId) != 1
                || buyMapper.verifySupplyInfo(EnumSellInfo.VerifyPass, session.getAdmin().getUsername(), comment, LocalDateTime.now(), supplyVerify.getId()) != 1){
                throw new BusinessException("(审核通过)供应信息已经被修改，请刷新后重试");
            }
        } else if (checkResult == 1) {
            if(buyMapper.verifySellinfoStatus(null, null, EnumSellInfo.VerifyNotPass, comment, LocalDateTime.now(), id, version, null) != 1
            || buyMapper.verifySupplyInfo(EnumSellInfo.VerifyNotPass, session.getAdmin().getUsername(), comment, LocalDateTime.now(), supplyVerify.getId()) != 1){
                throw new BusinessException("(审核未通过)供应信息已经被修改，请刷新后重试");
            }
        }
    }
}


