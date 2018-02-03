/*
 * Created by Sandeep Tadepalli on 04/02/18 03:20
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String USERNAME = "ooad";
    private static final String PASSWORD = "ooad";
    private static final String DATABASE_NAME = "amazon";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
