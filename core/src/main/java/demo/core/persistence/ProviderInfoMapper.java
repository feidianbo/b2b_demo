package demo.core.persistence;

import demo.core.domain.ProviderInfo;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * Created by zhangbolun on 15/1/23.
 */
public interface ProviderInfoMapper {

    @Select("select * from providerinfo where id=#{id} and isdelete=false")
    public ProviderInfo getProviderInfoById(@Param("id")int id);

    @Select("select * from providerinfo where isdelete=false")
    public List<ProviderInfo> getAllProviderInfo();

    @Select("select * from providerinfo where providername=#{providername} and isdelete=false")
    public List<ProviderInfo> getProviderInfoByName(String providername);

    @Insert("insert into providerinfo(status,contacter,contactphone,isdelete,providername, enterpriseproperty, enterpriseaddress, registeredcapital,createtime) values(#{status},#{contacter},#{contactphone},false,#{providername}, #{enterpriseproperty}, #{enterpriseaddress}, #{registeredcapital},#{createtime})")
    @Options(useGeneratedKeys=true)
    public void addProviderInfo(ProviderInfo providerInfo);

    @Update("update providerinfo set status=#{status}, contacter=#{contacter},contactphone=#{contactphone},isdelete=#{isdelete}, providername=#{providername},enterpriseproperty=#{enterpriseproperty},enterpriseaddress=#{enterpriseaddress},registeredcapital=#{registeredcapital} where id=#{id} and isdelete=false")
    public void updateProviderInfo(ProviderInfo providerInfo);

    @Update("update providerinfo set status=#{status} where id=#{id} and isdelete=false")
    public void updateProviderStatusById(@Param("id")int id,@Param("status")String status);

    @Update("update providerinfo set isdelete=true where id=#{id} and isdelete=false")
    public void deleteProviderInfoByid(@Param("id")int id);

    //后台page
    @Select("select count(*) from providerinfo where isdelete=0")
    public int countAllSuppliers();

    @Select("select * from providerinfo where isdelete=0  limit #{limit} offset #{offset}")
    public List<ProviderInfo> listAllSuppliers(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<ProviderInfo> pageAllSuppliers(int page, int pagesize){
        return Pager.config(this.countAllSuppliers(), (int limit, int offset) -> this.listAllSuppliers(limit, offset))
                .page(page, pagesize);
    }
}
