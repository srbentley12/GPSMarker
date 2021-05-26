package com.example.gpsmarker;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;

    private final String host = "locationdb.ccquyj5sqdqq.us-east-2.rds.amazonaws.com";
    private final String database = "locations_db";
    private final int port = 5432;
    private final String user = "";
    private final String pass = "";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Database() {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        System.out.println("Database connection status:" + status);
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }

    }

    public Connection getExtraConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

}
