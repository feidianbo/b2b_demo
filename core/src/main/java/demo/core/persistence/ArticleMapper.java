package demo.core.persistence;

import demo.core.domain.Article;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;
import java.util.List;

import static demo.ext.mybatis.Where.*;

/**
 * Created by lxj on 14/11/12.
 */
public interface ArticleMapper {
    final String startWhere = "<where>";
    final String endWhere = "</where>";
    final String SelectArticle1 = "<if test='parentid!=0'> parentid = #{parentid}</if>";
    String $articles=" articles ";
    String $isDeleteFalse=" isdelete=false ";

    @Select("select * from articles where id=#{id} and isdelete=0")
    public Article $getById(int id);

    public default Article getById(int id){
        return id!=0 ? $getById(id):null;
    }

    //添加文章
	@Insert("insert into articles(title, summary, keywords, content, author, source, createtime, " +
            "parentid, path, lastupdateman) values(#{title}, #{summary}, #{keywords}, #{content}, #{author}," +
            "#{source}, now(), #{parentid}, #{path}, #{lastupdateman})")
    @Options(useGeneratedKeys=true)
    public void $insertArticle(Article article);
    //更新文章
    @Update("update articles set title=#{title}, summary=#{summary}, keywords=#{keywords}," +
            " content=#{content}, author=#{author}, source=#{source}, path=#{path}, " +
            "lastupdateman=#{lastupdateman} where id=#{id}" )
    public void $updateArticle(Article article);

    @Update("update articles set path=CONCAT(#{newPath}, SUBSTRING(path, length(#{oldPath})) where path like CONCAT(#{oldPath}, '/%')")
    void $updateDescendantPathByPath(String newPath, String oldPath);

    @Update("update articles, (select count(*)>0 as cnt from articles where parentid=#{id}) as cc set articles.haschild=cnt where id=#{id}")
    void $updateHasChildById(int id);

    public default void saveArticle(Article article){
        Article parent = null;
        if(article.getParentid() != 0){
            parent = this.$getById(article.getParentid());
            if(parent == null)
                throw new RuntimeException("parent id:" + article.getParentid() + " is not right");
            article.setPath(parent.getPath()+article.getPath());
        }
        if(article.getId() != null){
            Article articleInDb=$getById(article.getId());
            if(articleInDb!=null) {
                $updateArticle(article);
                /*if(!articleInDb.getPath().equals(article.getPath())){
                    $updateDescendantPathByPath(article.getPath(), articleInDb.getPath());
                }*/
            }
        } else{
            $insertArticle(article);
            if(parent!=null)
                $updateHasChildById(article.getParentid());
        }
    }
    public default List<Article> getAncestorsById(int id){
        List<Article> list=new ArrayList<Article>();
        Article article=$getById(id);
        while(article!=null){
            list.add(article);
            if(article.getParentid()!=0)
                article=$getById(article.getParentid());
            else
                break;
        }
        return list;
    }

    String $whereArticleByParentAndContent= where+"parentid=#{parentid} and isdelete=false <if test='content!=null'> and title like #{content}</if>"+$where;
    String $orderbycreatetimedesc = "order by createtime desc";
    @Select(script+selectCountAllFrom+$articles+$whereArticleByParentAndContent+$script)
    public int $countArticleByParentidAndContent(@Param("parentid")int parentid, @Param("content")String content);

    @Select(script+selectAllFrom+$articles+$whereArticleByParentAndContent + $orderbycreatetimedesc + limitAndOffset+$script)
    public List<Article> $listArticleByParentidAndContent(@Param("parentid") int parentid, @Param("content")String content, @Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Article> pageArticleByParentidAndContent(int parentid, String content, int page, int pagesize){
        return new Pager(
                $countArticleByParentidAndContent(parentid, $like$(content)),
                (int limit, int offset)->$listArticleByParentidAndContent(parentid, $like$(content), limit, offset),
                page,
                pagesize);
    }

    @Delete("delete from articles where id=#{id}")
    void $deleteById(int id);

    public default void deleteById(int id){
        Article article=getById(id);
        $deleteById(id);
        if(article!=null && article.getParentid()!=0)
            $updateHasChildById(article.getParentid());
    }
	//根据parentid查询文章
	@Select("select * from articles where parentid=#{id} and isdelete=0 order by sequence asc, createtime desc limit #{limit}")
	public List<Article> getSeveralArticles(@Param("id")int i, @Param("limit")int limit);

	//通过path查找Article
	@Select("select * from articles where path=#{path} and isdelete=0")
	public Article getArticleByPath(String path);

    //通过path, parentid查找Article
    @Select("select * from articles where path=#{path} and parentid=#{parentid} and isdelete=0")
    public Article getArticleByPathParentid(@Param("path")String path,
                                            @Param("parentid")Integer parentid);

	//根据path查询id
	@Select("select id from articles where path=#{path} and isdelete=0")
	public Integer getIdByPath(String path);

	public default List<Article> getSeveralArticlesByParentPath(String path, int limit){
		Integer id=getIdByPath(path);
		if(id!=null)
			return getSeveralArticles(id, limit);
		return null;
	}

    //改变固顶级别
    @Update("update articles set sequence=#{sequence} where id=#{id}")
    public void changeSequence(@Param("sequence")int sequence, @Param("id")int id);

}
