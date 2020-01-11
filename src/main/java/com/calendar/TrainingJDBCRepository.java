package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TrainingJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrainingJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class TrainingRowMapper implements RowMapper<TrainingBefore> {

        @Override
        public TrainingBefore mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrainingBefore training = new TrainingBefore();
            training.setId(rs.getString("id"));
            training.setIdMatch(rs.getString("idMatch"));
            return training;
        }
    }

    void deleteById(String id) {
        jdbcTemplate.update("delete from trainingBefore where id=?", id);
    }

    TrainingBefore getTrainingById(String id) {
        String sql = "SELECT * FROM trainingBefore WHERE idMatch = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TrainingRowMapper());
    }

    void insert(TrainingBefore training) {
        jdbcTemplate.update("insert into trainingBefore (id, idMatch) " + "values(?, ?)", training.getId(), training.getIdMatch());
    }

    public boolean exist(String idMatch){
        int count =jdbcTemplate.queryForObject("select count(*) from trainingBefore where idMatch=?",new Object[]{idMatch},Integer.class);
        return count > 0;

    }
}
