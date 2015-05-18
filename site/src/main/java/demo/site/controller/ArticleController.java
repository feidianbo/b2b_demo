package demo.site.controller;

import com.mysql.jdbc.StringUtils;
import demo.core.domain.Article;
import demo.core.persistence.ArticleMapper;
import demo.site.basic.JsonController;
import demo.site.basic.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by fanjun on 14-11-20.
 */
@Controller
public class ArticleController extends JsonController {
    @Autowired
    protected ArticleMapper articleMapper;

    // 获取新闻列表，根据path：获取指定子分类新闻列表
    @RequestMapping(value = "/getArticleList", method= RequestMethod.GET)
    public String getArticleList(@RequestParam(value = "path", required = false)String path,
                                 @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                 Map<String, Object> model){
        if(StringUtils.isNullOrEmpty(path)){
            throw new NotFoundException();
        }
        Article parent = articleMapper.getArticleByPath(path);
        if(parent == null){
            throw new NotFoundException();
        }
        model.put("parentPath", path.substring(1, path.length()));
        model.put("path", path);
        articleMapper.pageArticleByParentidAndContent(parent.getId(), null, page, 10).putToMap(model);
        return "articleList";
    }

    // 获取指定文章内容
    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    public String getArticle(@RequestParam(value = "id", required = true)int id, Map<String, Object> model){
        Article article = articleMapper.getById(id);
        Article parent = article.getParentid()!=0 ? articleMapper.getById(article.getParentid()) : null;
        model.put("article",article);
        model.put("parentPath", parent.getPath().substring(1, parent.getPath().length()));
        return "articleDetails";
    }


}
