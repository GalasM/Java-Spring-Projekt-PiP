package hello;

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

    List<Footballer> findAll() {
        return jdbcTemplate.query("select * from footballer", new FootballerRowMapper());
    }

    public Footballer findById(String id) {
        return jdbcTemplate.queryForObject("select * from footballer where id=?", new Object[] {id},new BeanPropertyRowMapper<Footballer>(Footballer.class));
    }

    public int deleteById(String id) {
        return jdbcTemplate.update("delete from footballer where id=?", id);
    }

    void insert(Footballer footballer) {
        jdbcTemplate.update("insert into footballer (id, imie, nazwisko, pozycja) " + "values(?, ?, ?, ?)", footballer.getId(), footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja());
    }

    public int update(Footballer footballer) {
        return jdbcTemplate.update("update footballer " + " set name = ?, nazwisko = ?, pozycja = ? " + " where id = ?", footballer.getImie(), footballer.getNazwisko(), footballer.getPozycja(), footballer.getId());
    }
}
