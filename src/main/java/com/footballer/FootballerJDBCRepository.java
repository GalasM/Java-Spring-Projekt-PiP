package com.footballer;

import com.footballer.Footballer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FootballerJDBCRepository {

        private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FootballerJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class FootballerRowMapper implements RowMapper<Footballer> {

        @Override
        public Footballer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Footballer footballer = new Footballer();
            footballer.setId(rs.getString("id"));
            footballer.setImie(rs.getString("imie"));
            footballer.setNazwisko(rs.getString(("nazwisko")));
            footballer.setPozycja(rs.getString("pozycja"));
            footballer.setStatus(rs.getString("status"));
            return footballer;
        }
    }

    public List<Footballer> findAll() {
        return jdbcTemplate.query("select * from footballer", new FootballerRowMapper());
    }

    public Footballer findById(String id) {
        return jdbcTemplate.queryForObject("select * from footballer where id=?", new Object[] {id},new BeanPropertyRowMapper<Footballer>(Footballer.class));
    }

    public String deleteById(String id) {
        return String.valueOf(jdbcTemplate.update("delete from footballer where id=?", id));
    }

    void insert(Footballer footballer) {
        jdbcTemplate.update("insert into footballer (id, imie, nazwisko, pozycja, status) " + "values(?, ?, ?, ?, ?)", footballer.getId(), footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja(), footballer.getStatus());
    }

    public int update(Footballer footballer) {
        return jdbcTemplate.update("update footballer " + " set name = ?, nazwisko = ?, pozycja = ?, status = ? " + " where id = ?", footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja(), footballer.getStatus(), footballer.getId());
    }

    public List<Footballer> findN() {
        return jdbcTemplate.query("select * from footballer where pozycja = 'N' and status = 'S'", new FootballerRowMapper());
    }

    public List<Footballer> findP() {
        return jdbcTemplate.query("select * from footballer where pozycja = 'P' and status = 'S'", new FootballerRowMapper());
    }

    public List<Footballer> findO() {
        return jdbcTemplate.query("select * from footballer where pozycja = 'O' and status = 'S'", new FootballerRowMapper());
    }

    public List<Footballer> findBR() {
        return jdbcTemplate.query("select * from footballer where pozycja = 'BR' and status = 'S'", new FootballerRowMapper());
    }

    public List<Footballer> findR() {
        return jdbcTemplate.query("select * from footballer where status = 'R'", new FootballerRowMapper());
    }

    public String addToTeam(String id) {
        return String.valueOf(jdbcTemplate.update("update footballer set status = 'S' where id=?", id));
    }

    public String addToReserve(String id) {
        return String.valueOf(jdbcTemplate.update("update footballer set status = 'R' where id=?", id));
    }
}
