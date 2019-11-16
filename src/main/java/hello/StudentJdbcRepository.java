package hello;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcRepository {

    private final
    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));
            student.setPassportNumber(rs.getString("passportNumber"));
            return student;
        }
    }

    List<Student> findAll() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }

    public Student findById(String id) {
        return jdbcTemplate.queryForObject("select * from student where id=?", new Object[]{
                        id
                },
                new BeanPropertyRowMapper<Student>(Student.class));
    }

    public int deleteById(String id) {
        return jdbcTemplate.update("delete from student where id=?", id);
    }

    void insert(Student student) {
        jdbcTemplate.update("insert into student (id, name, passportNumber) " + "values(?,  ?, ?)",

                student.getId(), student.getName(), student.getPassportNumber()
        );
    }

    public int update(Student student) {
        return jdbcTemplate.update("update student " + " set name = ?, passport_number = ? " + " where id = ?",

                        student.getName(), student.getPassportNumber(), student.getId()
                );
    }
}
