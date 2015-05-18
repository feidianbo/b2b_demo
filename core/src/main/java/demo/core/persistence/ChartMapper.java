package demo.core.persistence;

import demo.core.domain.Chart;
import demo.core.domain.ChartConfine;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by fanjun on 15-2-7.
 */
public interface ChartMapper {

    //BSPI
    @Select("select count(*) from charts where type='BSPI'")
    public int countAllBSPI();

    @Select("select * from charts where type='BSPI' order by tradedate desc limit #{limit} offset #{offset}")
    public List<Chart> listAllBSPI(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Chart> pageAllBSPI(int page, int pagesize){
        return Pager.config(this.countAllBSPI(), (int limit, int offset) -> this.listAllBSPI(limit, offset))
                .page(page, pagesize);
    }

    //API8
    @Select("select count(*) from charts where type='API8'")
    public int countAllAPI8();

    @Select("select * from charts where type='API8' order by tradedate desc limit #{limit} offset #{offset}")
    public List<Chart> listAllAPI8(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Chart> pageAllAPI8(int page, int pagesize){
        return Pager.config(this.countAllAPI8(), (int limit, int offset) -> this.listAllAPI8(limit, offset))
                .page(page, pagesize);
    }

    //TC1505
    @Select("select count(*) from charts where type='TC1505'")
    public int countAllTC1505();

    @Select("select * from charts where type='TC1505' order by tradedate desc limit #{limit} offset #{offset}")
    public List<Chart> listAllTC1505(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Chart> pageAllTC1505(int page, int pagesize) {
        return Pager.config(this.countAllTC1505(), (int limit, int offset) -> this.listAllTC1505(limit, offset))
                .page(page, pagesize);
    }

    //插入图表数据
    @Insert("insert into charts(type,tradedate,averageprice,createtime) values(" +
            "#{type},#{tradedate},#{averageprice},now())")
    public int addChart(Chart chart);

    //删除记录
    @Delete("delete from charts where id=#{id}")
    public void deleteOne(int id);

    //查询BSPI的最近12条记录
    @Select("select * from charts where type='BSPI' order by tradedate desc limit 12")
    public List<Chart> getAllBSPI();

    //查询API8的最近10条记录
    @Select("select * from charts where type='API8' order by tradedate desc limit 10")
    public List<Chart> getAllAPI8();

    //查询TC1505的最近10条记录
    @Select("select * from charts where type='TC1505' order by tradedate desc limit 10")
    public List<Chart> getAllTC1505();

    //获取图表上限和下限
    @Select("select * from chartconfine where type=#{type}")
    public ChartConfine getChartConfineByType(String type);

    //修改图表上下限
    @Update("update chartconfine set highlimit=#{highlimit},lowlimit=#{lowlimit} where type=#{type}")
    public void modifyChartConfine(ChartConfine chartConfine);

    //新增上下限
    @Insert("insert into chartconfine(type,highlimit,lowlimit) values(#{type},#{highlimit},#{lowlimit})")
    public void addChartConfine(ChartConfine chartConfine);

    //根据类型查询记录数
    @Select("select count(*) from chartconfine where type=#{type}")
    public int countChartConfineByType(String type);

    //验证交易日期唯一性
    @Select("select count(*) from charts where tradedate=#{tradedate} and type=#{type}")
    public int checkTradedateSole(@Param("tradedate") String tradedate,@Param("type") String type);

}
