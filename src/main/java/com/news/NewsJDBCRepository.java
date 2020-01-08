package com.news;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NewsJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    public NewsJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class NewsRowMapper implements RowMapper<News> {

        @Override
        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setAid(rs.getString("id"));
            news.setTytul(rs.getString("tytul"));
            news.setTresc(rs.getString(("tresc")));
            news.setData(rs.getString(("data")));
            return news;
        }
    }

    public void insert(News news) {
        jdbcTemplate.update("insert into news(id,tytul,tresc,data)" + "values(?, ?, ?, ?)", news.getAid(), news.getTytul(), news.getTresc(), news.getData());
    }

    public List<News> findAll() {
        return jdbcTemplate.query("select * from news order by data desc", new NewsRowMapper());
    }

}
