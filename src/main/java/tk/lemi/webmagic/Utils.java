package tk.lemi.webmagic;

import tk.lemi.model.Article;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Utils {

    /**
     * 创建Article
     * @return
     */
    public static Article createArticle(String title, String content, String source, String author, String url, String create){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSource(source);
        article.setAuthor(author);
        try {
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(create);
            if(matcher.find()){
                create = matcher.group(0);
            }
            article.setPubdate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(create));
        }catch (Exception e){
        }
        article.setUrl(url);
        return article;
    }

    /**
     * html字符过滤
     * @param str
     * @return
     */
    public static String replaceHTML(String str){
        return str!=null?str.replaceAll("\\<.*?>","").replaceAll("&nbsp;",""):"";
    }
}
