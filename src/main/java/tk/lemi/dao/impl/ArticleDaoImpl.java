package tk.lemi.dao.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import tk.lemi.dao.ArticleDao;
import tk.lemi.model.Article;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void save(final Article article) {
        String sql = "INSERT INTO news (title,content,url,source,author,pubdate) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,article.getTitle());
                ps.setString(2,article.getContent());
                ps.setString(3,article.getUrl());
                ps.setString(4,article.getSource());
                ps.setString(5,article.getAuthor());
                ps.setDate(6, new Date(article.getPubdate().getTime()));
            }
        });
    }
}
