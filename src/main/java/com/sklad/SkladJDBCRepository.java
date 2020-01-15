package com.sklad;

import com.footballer.Footballer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SkladJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SkladJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class TeamRowMapper implements RowMapper<Sklad> {
        Sklad.Builder team = new Sklad.Builder();
        public Sklad mapRow(ResultSet rs, int rowNum) throws SQLException {
            do {
                team.id(rs.getString("team.id"))
                        .name(rs.getString("name"))
                        .formation(rs.getString("formation"));
                Footballer f = new Footballer();
                f.setId(rs.getString("idFootballer"));
                f.setImie(rs.getString("imie"));
                f.setNazwisko(rs.getString("nazwisko"));
                f.setStatus(rs.getString("status"));
                f.setPozycja(rs.getString("pozycja"));
                if (f.getStatus().equals("R")) {
                    team.addR(f);
                }
                switch (f.getPozycja()) {
                    case "N":
                        team.addN(f);
                        break;
                    case "P":
                        team.addP(f);
                        break;
                    case "O":
                        team.addO(f);
                        break;
                    case "BR":
                        team.addBR(f);
                        break;
                }
            }while(rs.next());
            return team.build();
        }
    }

    class SkladRowMapper implements RowMapper<Sklad> {
        Sklad.Builder team = new Sklad.Builder();

        public Sklad mapRow(ResultSet rs, int rowNum) throws SQLException {
            team.id(rs.getString("team.id"))
                    .name(rs.getString("name"))
                    .formation(rs.getString("formation"));
            return team.build();
        }
    }

    public List<Sklad> findAllTeams(){
      return jdbcTemplate.query("select * from team",new SkladRowMapper());

    }

    public Sklad findTeamById(String id){
        return jdbcTemplate.queryForObject("select * from team where id=?",new Object[]{id},new SkladRowMapper());
    }

    public Sklad findById(String id) {
        Sklad x = jdbcTemplate.queryForObject("select * from team inner join teamfootballer on teamfootballer.idteam=team.id inner join footballer on footballer.id=teamfootballer.idfootballer where team.id=?", new Object[] {id},new TeamRowMapper());

        return x;
    }

    public void insert(Sklad team){
        jdbcTemplate.update("insert into team (id, name, formation) " + "values(?, ?, ?)", team.getId(), team.getName(), team.getFormation());

    }

    public void insertFootballer(String id,String idTeam, String idFootballer, String status) {
        jdbcTemplate.update("insert into teamFootballer(id,idTeam,idFootballer,status)"+" values(?,?,?,?) ", id,idTeam,idFootballer,status);
    }

    public String updateFootballer(String idTeam, String idFootballer, String status) {
        return String.valueOf(jdbcTemplate.update("update teamFootballer set status = ? where idFootballer=? and idTeam=?", status,idFootballer,idTeam));
    }


    public String removeFootballer(String idTeam, String idFootballer) {
        return String.valueOf(jdbcTemplate.update("update teamFootballer set idTeam=null where idFootballer=? and idTeam=?", idFootballer,idTeam));
    }

    public boolean exist(String id,String idTeam){
        int count =jdbcTemplate.queryForObject("select count(*) from teamFootballer where idFootballer=? and idTeam=?",new Object[]{id,idTeam},Integer.class);
        return count > 0;

    }


}
