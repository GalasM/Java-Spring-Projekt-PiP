package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CalendarJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CalendarJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class EventRowMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getString("id"));
            event.setTitle(rs.getString("title"));
            event.setStart(rs.getString(("start")));
            event.setEnd(rs.getString("end"));
            event.setType(rs.getString("type"));
            return event;
        }
    }

    List<Event> findAll() {
        return jdbcTemplate.query("select * from event", new CalendarJDBCRepository.EventRowMapper());
    }

    public Event findById(String id) {
        return jdbcTemplate.queryForObject("select * from event where id=?", new Object[] {id}, new EventRowMapper());
    }

    void deleteById(String id) {
        jdbcTemplate.update("delete from event where id=?", id);
    }

    void insert(Event event) {
        jdbcTemplate.update("insert into event (id, title, start, end, type) " + "values(?, ?, ?, ?, ?)", event.getId(), event.getTitle(), event.getStart(), event.getEnd(), event.getType());
    }

}
