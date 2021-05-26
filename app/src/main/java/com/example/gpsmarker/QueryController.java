package com.example.gpsmarker;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryController {
    Database db;
    private Connection connection;


    public QueryController (){
        db = new Database();
        connection = db.getExtraConnection();
    }

    public String getLatitude(int index){
        index++;
        String output = null;

        try{
            String query = "with temp as (SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNum " +
                    "FROM locations_table) " +
                    "select * from temp where rownum = " + index;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            output = rs.getString(2);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }

        return output;
    }

    public String getLongitude(int index){
        index++;
        String output = null;

        try{
            String query = "with temp as (SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNum " +
                    "FROM locations_table) " +
                    "select * from temp where rownum = " + index;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            output = rs.getString(3);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }

        return output;
    }

    public void addRow(String latitude, String longitude){

        try{
            String query = "INSERT INTO locations_table (latitude, longitude) VALUES (" + latitude + ", " + longitude + ")";
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }

    }

    public void deleteRow(int index){
        index++;

        try{
            String query = "Delete from locations_table where id = (with temp as " +
                            "(SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS RowNum FROM locations_table) " +
                            "select id from temp where rownum = " + index + ")";

            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
    }

    public ArrayList<Coordinates> fillList(){
        ArrayList<Coordinates> list = new ArrayList<>();

        try{
            String query = "SELECT * FROM locations_table";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            list.add(new Coordinates(rs.getString(2), rs.getString(3)));
            }
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }

        return list;
    }

    public String getQuery(){
        String output = null;

        try{
            String query = "SELECT latitude FROM locations_table WHERE id = 1";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            output = rs.getString(1);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return output;
    }

}
