package demo.core.persistence;

import demo.core.domain.IndexBanner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by fanjun on 15-3-23.
 */
public interface IndexBannerMapper {

    //查询首页图片表是否有数据
    @Select("select count(*) from indexbanners")
    public int countIndexBanner();

    //保存图片相关信息
    @Insert("insert into indexbanners(name,path,sequence,limitnum,createtime) values(" +
            "#{name},#{path},#{sequence},#{limitnum},#{createtime})")
    public void addIndexBanner(IndexBanner indexBanner);

    //修改图片相关信息
    @Update("update indexbanners set path=#{path},sequence=#{sequence},limitnum=#{limitnum},createtime=#{createtime} where name=#{name}")
    public void modifyIndexBanner(IndexBanner indexBanner);

    //获取所有图片集合
    @Select("select * from indexbanners")
    public List<IndexBanner> getAllIndexBanners();

    //前台,获取图片前查询显示数量,做为参数获取图片
    @Select("select limitnum from indexbanners limit 1")
    public int getLimitnum();

    //前台获取图片
    @Select("select * from indexbanners order by sequence limit #{limitnum}")
    public List<IndexBanner> getIndexBannnersWithLimit(int limitnum);

}
