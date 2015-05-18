package demo.core.dao;

import demo.core.domain.Article;
import demo.core.persistence.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by joe on 3/3/15.
 */
@Service
public class DemoDao {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    PlatformTransactionManager transactionManager;

    public void testTransaction(int order){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            articleMapper.$insertArticle(new Article("t1", "xx", "xx", "aa", "ss", "ss", order, "ss"));
            if(order>10){
                throw new Exception("xxx");
            }
            transactionManager.commit(status);
            System.out.println(" transation commit");
        } catch (Exception ex){
            transactionManager.rollback(status);
            System.out.println(" transation rollback");
        }
    }
}
