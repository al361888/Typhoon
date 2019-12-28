package es.uji.ei1048.typhoon.weather.current;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.sql.*;

public class databaseOp {

    private Connection connectDB() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\Admin\\Desktop\\SQLite\\typhoon.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertCity(City city, WeatherStatus w){
        String sql = "INSERT INTO weatherStatusCity(name, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName());
            pstmt.setString(2, w.getTime().toString());
            pstmt.setDouble(3, w.getTemp());
            pstmt.setString(4, w.getDescription());
            pstmt.setDouble(5, w.getPressure());
            pstmt.setDouble(6, w.getHumidity());
            pstmt.setDouble(7, w.getTempMin());
            pstmt.setDouble(8, w.getTempMax());
            pstmt.setDouble(9, w.getWindSpeed());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCoord(Coordinates coordinates, WeatherStatus w){
        String sql = "INSERT INTO weatherStatusCoord(latitude, longitude, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getX());
            pstmt.setDouble(2, coordinates.getY());
            pstmt.setString(3, w.getTime().toString());
            pstmt.setDouble(4, w.getTemp());
            pstmt.setString(5, w.getDescription());
            pstmt.setDouble(6, w.getPressure());
            pstmt.setDouble(7, w.getHumidity());
            pstmt.setDouble(8, w.getTempMin());
            pstmt.setDouble(9, w.getTempMax());
            pstmt.setDouble(10, w.getWindSpeed());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}