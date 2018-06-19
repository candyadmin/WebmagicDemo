package tk.lemi.webmagic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.lemi.dao.ArticleDao;
import tk.lemi.model.Article;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
@Component("jdbcPipeline")
public class JdbcPipeline implements Pipeline {

    @Autowired
    ArticleDao articleDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String,Object> items = resultItems.getAll();
        if(resultItems!=null&&resultItems.getAll().size()>0){
            System.out.println( items.get("title"));
            System.out.println( items.get("content"));
            System.out.println( items.get("source"));
            System.out.println( items.get("author"));
            System.out.println( items.get("url"));
            System.out.println( items.get("create"));

            Article article = new Article();
            article.setTitle((String) items.get("title"));
            article.setContent((String) items.get("content"));
            article.setSource((String) items.get("source"));
            article.setAuthor((String) items.get("author"));
            article.setUrl((String)items.get("url"));
            String dataStr = (String)items.get("create");
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(dataStr);
            if(matcher.find()){
                dataStr = matcher.group(0);
            }
            try {
                article.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dataStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            articleDao.save(article);
        }
    }
}
