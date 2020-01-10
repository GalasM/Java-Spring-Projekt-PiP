package com.footballer;

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
        jdbcTemplate.update("insert into footballer (id, imie, nazwisko, pozycja) " + "values(?, ?, ?, ?)", footballer.getId(), footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja());
    }

    public int update(Footballer footballer) {
        return jdbcTemplate.update("update footballer " + " set name = ?, nazwisko = ?, pozycja = ?, status = ? " + " where id = ?", footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja(), footballer.getStatus(), footballer.getId());
    }

    public List<Footballer> findN(String id) {
        return jdbcTemplate.query("select footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamFootballer on teamfootballer.idFootballer=footballer.id where teamfootballer.idteam=? and footballer.pozycja='N' and teamFootballer.status='S'",new Object[]{id}, new FootballerRowMapper());
    }

    public List<Footballer> findP(String id) {
        return jdbcTemplate.query("select footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamFootballer on teamfootballer.idFootballer=footballer.id where teamfootballer.idteam=?and footballer.pozycja='P' and teamFootballer.status='S'",new Object[]{id}, new FootballerRowMapper());
    }

    public List<Footballer> findO(String id) {
        return jdbcTemplate.query("select footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamFootballer on teamfootballer.idFootballer=footballer.id where teamfootballer.idteam=?and footballer.pozycja='O' and teamFootballer.status='S'",new Object[]{id}, new FootballerRowMapper());
    }

    public List<Footballer> findBR(String id) {
        return jdbcTemplate.query("select footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamFootballer on teamfootballer.idFootballer=footballer.id where teamfootballer.idteam=?and footballer.pozycja='BR' and teamFootballer.status='S'",new Object[]{id}, new FootballerRowMapper());
    }

    public List<Footballer> findR(String id) {
        return jdbcTemplate.query("select footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamFootballer on teamfootballer.idFootballer=footballer.id where teamfootballer.idteam=? and teamFootballer.status='R'",new Object[]{id}, new FootballerRowMapper());
    }

    public List<Footballer> findAllWithoutOne(String id) {
        return jdbcTemplate.query("select distinct footballer.id,footballer.imie,footballer.nazwisko, footballer.pozycja from footballer inner join teamfootballer on teamfootballer.idfootballer=footballer.id where teamfootballer.idteam is not ? and footballer.id not in (select idfootballer from teamfootballer where idteam is ?)",new Object[]{id,id}, new FootballerRowMapper());
    }
}
