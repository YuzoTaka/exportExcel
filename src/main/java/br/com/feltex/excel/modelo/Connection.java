/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.feltex.excel.modelo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author henri
 */
public class Connection {

    private JdbcTemplate connection;

    public Connection() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource​.setUrl("jdbc:mysql://localhost:3306/dyoung");

        dataSource​.setUsername("root");

        dataSource​.setPassword("#Gf45016792801");

        this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
}
