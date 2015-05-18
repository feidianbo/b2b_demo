package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.JsonController;
import demo.admin.service.Auth;
import demo.core.domain.Article;
import demo.core.persistence.ArticleMapper;
import demo.core.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxj on 14/11/12.
 */
@RestController
public class ArticleController extends JsonController {
	@Autowired
	protected ArticleMapper articleMapper;
	@Autowired
	protected Auth auth;

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
    @VerifyAuthentication(NetworkEditor = true, Admin = true)
	public Object addArticle(Article article){
        article.setPath("/" + article.getTitle());
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if(!checkArticle(article)){
            map.put("error", "patherror");
        } else{
            article.setLastupdateman("id=" + session.getAdmin().getId());
            articleMapper.saveArticle(article);
            success = true;
        }
        map.put("success", success);
        return map;
	}

    private boolean checkArticle(Article article) {
        if(articleMapper.getArticleByPath(article.getPath()) == null){
            return true;
        } else{
            System.out.println("article-id: " + article.getId());
            Article article1 = articleMapper.getArticleByPath(article.getPath());
            if(article.getId() == null){
                if(article.getParentid() != article1.getParentid()){
                    return true;
                }
            } else {
                if(article.getId() == article1.getId() || article.getParentid() != article1.getParentid()) {
                    return true;
                }
            }
        }
        return false;
    }

    @RequestMapping("/article/queryById")
    @VerifyAuthentication(NetworkEditor = true, Admin = true)
	public Object queryArticle(int id){
        return new Object(){
            public boolean success = true;
            public Article article = articleMapper.getById(id);
        };
	}

	@RequestMapping("/article/delete")
    @VerifyAuthentication(NetworkEditor = true, Admin = true)
	public Object deleteArticle(@RequestParam("ids[]") int[] ids){
        for(int id : ids){
            articleMapper.deleteById(id);
        }
        return new Object(){
            public boolean success = true;
        };
	}

	@RequestMapping(value="/article/list", method=RequestMethod.POST)
    @VerifyAuthentication(NetworkEditor = true, Admin = true)
	public Object articleList(String content, int page, @RequestParam(value="parentid", required = false, defaultValue = "0")int parentid){
        return new Pager(articleMapper.pageArticleByParentidAndContent(parentid, content, page, 10)){
            public List<Article> parents=articleMapper.getAncestorsById(parentid);
        };
	}
    @RequestMapping("/article/changeSequence")
    @VerifyAuthentication(NetworkEditor = true, Admin = true)
    public Object doChangeSquence(@RequestParam("id")int id, @RequestParam("sequence")int sequence){
        articleMapper.changeSequence(sequence,id);
        return new JsonSuccess(){
            public Article article = articleMapper.getById(id);
        };
    }
}
