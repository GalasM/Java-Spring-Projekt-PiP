package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    List<Event> findAllMatches() {
        return jdbcTemplate.query("select * from event where type='match'", new CalendarJDBCRepository.EventRowMapper());
    }

    public Event findById(String id) {
        return jdbcTemplate.queryForObject("select * from event where id=?", new Object[] {id}, new EventRowMapper());
    }

    void deleteById(String id) {
        jdbcTemplate.update("delete from event where id=?", id);
    }

    void insert(Event event) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        String id = UUID.randomUUID().toString();

        jdbcTemplate.update("insert into event (id, title, start, end, type) " + "values(?, ?, ?, ?, ?)", event.getId(), event.getTitle(), event.getStart(), event.getEnd(), event.getType());
        jdbcTemplate.update("insert into news(id, tytul,tresc,data)" + " values(?, 'Do kalendarza dodano nowy wpis!', ?,?)", id, event.getTitle(), strDate);
    }
}