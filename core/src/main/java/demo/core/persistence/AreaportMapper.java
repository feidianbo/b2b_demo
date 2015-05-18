package demo.core.persistence;

import demo.core.domain.Areaport;
import demo.core.util.Pager;
import demo.ext.mybatis.Where;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by fanjun on 14-12-17.
 */
public interface AreaportMapper {

    //根据父id获取子集
    @Select("select * from areaports where parentid=#{parentid} and isdelete=0")
    public List<Areaport> getProcvincesOrPortsByParentid(int parentid);

    //获取所有省份划区
    @Select("select * from areaports where code='area'")
    public List<Areaport> getAllArea();

    @Select("select parentid from areaports where name=#{value}")
    public Integer getParentIdByName(String name);

    //获取所有省份
    @Select("select * from areaports where code='province'")
    public List<Areaport> getAllProvince();

    //根据父名称获取子集
    @Select("select * from areaports where isdelete=0 and parentid=(select id from areaports where name=#{parentname})")
    public List<Areaport> getProcvincesOrPortsByParentname(String parentname);

    //根据子名称查询父名称
    @Select("select name from areaports where isdelete=0 and id=(select parentid from areaports where name=#{name})")
    public String getNameBySonName(String name);

    //根据id查询名字
    @Select("select name from areaports where id=#{id}")
    public String getNameById(int id);

    //根据id查询
    @Select("select * from areaports where isdelete=0 and parentid=#{id}")
    public List<Areaport> getProvincesOrPortsByParentid(int id);

    //根据name查询id
    @Select("select id from areaports where name=#{name}")
    public Integer getIdByName(String name);

    //根据id查询parentid
    @Select("select parentid from areaports where id=#{id}")
    public Integer getParentidById(int id);

    //根据省份Id获取地区对象
    @Select("select * from areaports where id=(select parentid from areaports where id=#{provinceid})")
    public Areaport getAreaByProvinceId(int provinceid);

    //根据省份名称获取地区对象
    @Select("select * from areaports where id=(select parentid from areaports where name=#{provincename})")
    public Areaport getAreaByProvincename(String provincename);

    /**********************author zxy**************************/

    @Select("select id,name from areaports where id = #{value}")
    public Areaport findPortById(int id);

    @Select("<script>select count(1) from areaports a where a.code='port' <if test='provinceId!=-1'> and parentid=#{provinceId}</if><if test='portName!=null'> and name like  #{portName}</if></script>")
    public int countAllPort(@Param("provinceId")int provinceId,@Param("portName")String portName);

    @Select("<script>select a.id,a.name,count(dp.portid) dealerNum from areaports a left join dealerport dp on a.id=dp.portid where a.code='port' <if test='provinceId!=-1'>  and parentid = #{provinceId}</if><if test='portName!=null'>  and name like #{portName}</if>  group by a.id,a.name order by a.id desc  limit #{limit} offset #{offset}</script>")
    public List<Areaport> listAllPort(@Param("provinceId")int provinceId,@Param("portName")String portName,@Param("limit")int limit,@Param("offset")int offset);

    public default Pager<Areaport> pageAllPort(int provinceId,final String name,int page, int pagesize) {
        String portName=StringUtils.isEmpty(name)==true?null:Where.$like$(name);
        return Pager.config(this.countAllPort(provinceId,portName), (int limit, int offset) -> this.listAllPort(provinceId,portName,limit, offset))
                .page(page, pagesize);
    }
    //交易员id查找港口信息
    @Select("select  a.id,a.name from areaports a,dealerport d where a.id=d.portid and d.dealerid=#{value}")
    public List<Areaport> findPortByDealerId(int id);

    @Select("select a.id,a.name from areaports a left join dealerport dp on a.id=dp.portid where a.code='port' and  a.isdelete=0  group by a.id,a.name order by count(dp.portid) desc ")
    public List<Areaport> findAllPort();



}
