package demo.core.persistence;

import demo.core.domain.CompVerSus;
import demo.core.domain.Company;
import demo.core.domain.CompanyVerify;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by fanjun on 14-11-13.
 */
public interface CompanyMapper {

    //插入公司信息
    @Insert("insert into companies(name,address,phone,fax,legalperson,businesslicense,identificationnumber," +
            "organizationcode,operatinglicense,userid,legalpersonname,account,openingbank, traderphone," +
            "identificationnumword,zipcode,openinglicense,invoicinginformation) values(" +
            "#{name},#{address},#{phone},#{fax},#{legalperson},#{businesslicense},#{identificationnumber}," +
            "#{organizationcode},#{operatinglicense},#{userid},#{legalpersonname},#{account}," +
            "#{openingbank},#{traderphone},#{identificationnumword},#{zipcode},#{openinglicense}," +
            "#{invoicinginformation})")
    public int addCompany(Company company);

    //公司信息验证成功，备份公司信息
    @Insert("insert into compversus(name, address, phone, fax," +
            "legalperson, businesslicense, identificationnumber, organizationcode," +
            "operatinglicense, userid, legalpersonname, account, openingbank) values(" +
            "#{name}, #{address}, #{phone}, #{fax}, " +
            "#{legalperson}, #{businesslicense}, #{identificationnumber}, #{organizationcode}," +
            "#{operatinglicense}, #{userid}, #{legalpersonname}, #{account}, #{openingbank})")
    public void addCompVerSus(CompVerSus compVerSus);

    //判断是否已有该公司,做新增还是修改
    @Select("select count(*) from companies where userid=#{userid}")
    public int countCompany(int userid);

    //修改公司信息
    @Update("update companies set name=#{name},address=#{address},phone=#{phone},fax=#{fax}," +
            "legalperson=#{legalperson},businesslicense=#{businesslicense}," +
            "identificationnumber=#{identificationnumber},organizationcode=#{organizationcode}," +
            "operatinglicense=#{operatinglicense},legalpersonname=#{legalpersonname}," +
            "account=#{account},openingbank=#{openingbank},traderphone=#{traderphone}," +
            "identificationnumword=#{identificationnumword},zipcode=#{zipcode}, " +
            "openinglicense=#{openinglicense}, invoicinginformation=#{invoicinginformation}" +
            " where userid=#{userid}")
    public void modifyCompany(Company company);

    //获取公司对象
    @Select("select * from companies where userid=#{userid}")
    public Company getCompanyByUserid(int userid);

    //添加公司验证信息
    @Insert("insert into compverify(status,applytime,companyid,userid) values(#{status}, #{applytime}, #{companyid}, #{userid})")
    public void addCompVerify(CompanyVerify companyVertify);

    //修改公司验证状态
    @Update("update compverify set status=#{status} where id=#{id}")
    public void updateVerifyStatus(@Param("status") String status, @Param("id") int id);

    //查询公司id
    @Select("select id from companies where userid=#{userid}")
    public Integer getIdByUserid(int userid);

    //按照id查找公司
    @Select("select * from companies where id=#{id}")
    public Company getCompanyById(int id);

    //设置公司信息状态  待审核
    @Update("update companies set verifystatus=#{verifystatus} where id=#{id}")
    public void setCompanyStatusWait(@Param("verifystatus") String verifystatus, @Param("id") int id);


    //设置公司信息状态为 审核通过
    @Update("update companies set verifystatus=#{verifystatus}, remarks=#{remarks} where id=#{id}")
    public void setCompanyStatus(@Param("verifystatus") String verifystatus,
                                 @Param("remarks") String remarks,
                                 @Param("id") int id);

    //验证通过公司信息
    @Update("update compverify set status=#{status}, verifyman=#{verifyman}, " +
            "verifytime=#{verifytime}, remarks=#{remarks} " +
            "where companyid=#{id} and status='待审核'")
    public void setCompVerify(@Param("status") String status,
                              @Param("verifyman") String verifyman,
                              @Param("verifytime") LocalDateTime verifytime,
                              @Param("remarks") String remarks,
                              @Param("id") int id);

    @Select("select * from compverify where companyid=#{companyid}")
    public List<CompanyVerify> getVerifyList(int companyid);

    //统计公司名称是否存在
    @Select("select count(*) from companies where name=#{name} and userid <> #{id}")
    public int countCompanyIsExist(@Param("name") String name, @Param("id") int id);
}
