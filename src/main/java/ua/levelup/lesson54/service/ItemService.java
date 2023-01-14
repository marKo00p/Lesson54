package ua.levelup.lesson54.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class ItemService {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public  void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }
}
