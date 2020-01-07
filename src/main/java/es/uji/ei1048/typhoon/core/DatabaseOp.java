package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.core.IDataBaseOp;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalTime;

public class DatabaseOp implements IDataBaseOp {

    private Connection connectDB(){
        // SQLite connection string
        String url = "jdbc:sqlite::resource:typhoon.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Opened database successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void insertCity(City city, WeatherStatus w){
        String sql = "INSERT INTO weatherStatusCity(name, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName().toLowerCase());
            pstmt.setString(2, w.getTime().toString());
            pstmt.setDouble(3, w.getTemp());
            pstmt.setString(4, w.getDescription());
            pstmt.setDouble(5, w.getPressure());
            pstmt.setDouble(6, w.getHumidity());
            pstmt.setDouble(7, w.getTempMin());
            pstmt.setDouble(8, w.getTempMax());
            pstmt.setDouble(9, w.getWindSpeed());
            pstmt.executeUpdate();
            System.out.println("inserting city");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertCoord(Coordinates coordinates, WeatherStatus w){
        String sql = "INSERT INTO weatherStatusCoord(latitude, longitude, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getLatitude());
            pstmt.setDouble(2, coordinates.getLongitude());
            pstmt.setString(3, w.getTime().toString());
            pstmt.setDouble(4, w.getTemp());
            pstmt.setString(5, w.getDescription());
            pstmt.setDouble(6, w.getPressure());
            pstmt.setDouble(7, w.getHumidity());
            pstmt.setDouble(8, w.getTempMin());
            pstmt.setDouble(9, w.getTempMax());
            pstmt.setDouble(10, w.getWindSpeed());
            pstmt.executeUpdate();
            System.out.println("inserting coord");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public WeatherStatus getStatusCity(City city) throws StatusNotFound {
        String sql = "SELECT lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind FROM weatherStatusCity WHERE name = ?";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName().toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            System.out.println("select city");
            return new WeatherStatus(rs.getString("description"), rs.getDouble("temp"), rs.getDouble("pressure"),
                    rs.getDouble("humidity"), rs.getDouble("tempmin"), rs.getDouble("tempmax"), rs.getDouble("wind"), LocalTime.parse(rs.getString("lastcall")));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new StatusNotFound();
        }

    }

    @Override
    public WeatherStatus getStatusCoord(Coordinates coordinates) throws StatusNotFound {
        String sql = "SELECT lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind FROM weatherStatusCoord WHERE latitude = ? and longitude = ?";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getLatitude());
            pstmt.setDouble(2, coordinates.getLongitude());

            ResultSet rs = pstmt.executeQuery();

            return new WeatherStatus(rs.getString("description"), rs.getDouble("temp"), rs.getDouble("pressure"),
                    rs.getDouble("humidity"), rs.getDouble("tempmin"), rs.getDouble("tempmax"), rs.getDouble("wind"), LocalTime.parse(rs.getString("lastcall")));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new StatusNotFound();
        }

    }

    @Override
    public void deleteStatus(WeatherStatus status){
        String sql = "DELETE FROM weatherStatusCity WHERE lastcall <= ?";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, status.getTime().toString());
            pstmt.executeUpdate();
            System.out.println("deleting");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}