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
            stats.setStrzalyM(rs.getString("strzalyM"));
            stats.setStrzalyG(rs.getString("strzalyG"));
            stats.setZolteKartkiM(rs.getString("zolteKartkiM"));
            stats.setZolteKartkiG(rs.getString("zolteKartkiG"));
            stats.setCzerwoneKartkiM(rs.getString("czerwoneKartkiM"));
            stats.setCzerwoneKartkiG(rs.getString("czerwoneKartkiG"));
            stats.setFauleM(rs.getString("fauleM"));
            stats.setFauleG(rs.getString("fauleG"));
            stats.setSpaloneM(rs.getString("spaloneM"));
            stats.setSpaloneG(rs.getString("spaloneG"));


            return stats;
        }
    }

    public void insert(Statystyki stats) {
        int posiadanieG = 100 - Integer.parseInt(stats.getPosiadanieM());

        jdbcTemplate.update("insert into statystyki(id,przeciwnik,bramkiM,bramkiG,posiadanieM,posiadanieG, strzalyM, strzalyG, zolteKartkiM, zolteKartkiG, czerwoneKartkiM, czerwoneKartkiG, fauleM, fauleG, spaloneM, spaloneG)" + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)", stats.getAid(), stats.getPrzeciwnik(), stats.getBramkiM(), stats.getBramkiG(), stats.getPosiadanieM(), posiadanieG, stats.getStrzalyM(), stats.getStrzalyG(), stats.getZolteKartkiM(), stats.getZolteKartkiG(), stats.getCzerwoneKartkiM(), stats.getCzerwoneKartkiG(), stats.getFauleM(), stats.getFauleG(), stats.getSpaloneM(), stats.getSpaloneG());
    }

    public List<Statystyki> findAll()
    {
        return jdbcTemplate.query("select * from statystyki", new StatystkiRowMapper());
    }
}


