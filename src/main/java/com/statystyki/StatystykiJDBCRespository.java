package com.statystyki;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StatystykiJDBCRespository {

    private final JdbcTemplate jdbcTemplate;

    public StatystykiJDBCRespository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    class StatystkiRowMapper implements RowMapper<Statystyki> {

        @Override
        public Statystyki mapRow(ResultSet rs, int rowNum) throws SQLException {
            Statystyki stats = new Statystyki();
            stats.setAid(rs.getString("id"));
            stats.setPrzeciwnik(rs.getString("przeciwnik"));
            stats.setBramkiM(rs.getString("bramkiM"));
            stats.setBramkiG(rs.getString("bramkiG"));
            stats.setPosiadanieM(rs.getString("posiadanieM"));
            stats.setPosiadanieG(rs.getString("posiadanieG"));
            return stats;
        }
    }

    public void insert(Statystyki stats) {
        jdbcTemplate.update("insert into statystyki(id,przeciwnik,bramkiM,bramkiG,posiadanieM,posiadanieG)" + "values(?, ?, ?, ?, ?, ?)", stats.getAid(), stats.getPrzeciwnik(), stats.getBramkiM(), stats.getBramkiG(), stats.getPosiadanieM(), stats.getPosiadanieG());
    }

    public List<Statystyki> findAll()
    {
        return jdbcTemplate.query("select * from statystyki", new StatystkiRowMapper());
    }
}


