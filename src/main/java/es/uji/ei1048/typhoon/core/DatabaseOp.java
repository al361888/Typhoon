package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.core.IDataBaseOp;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOp implements IDataBaseOp {

    private Connection connectDB(){
        // SQLite connection string
        //Aqui se tiene que poner la url que sale en properties de la base de datos
        String url = "jdbc:sqlite:C:\\Users\\mario\\IdeaProjects\\WeatherApp\\src\\main\\resources\\typhoon.db";
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
        String sql = "INSERT INTO weatherStatusCity(name, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?);";
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
        String sql = "INSERT INTO weatherStatusCoord(latitude, longitude, lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind) VALUES(?,?,?,?,?,?,?,?,?,?);";
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
        String sql = "SELECT lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind FROM weatherStatusCity WHERE name = ?;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName().toLowerCase());
            ResultSet rs = pstmt.executeQuery();

            System.out.println("select city");
            return new WeatherStatus(rs.getString("description"), rs.getDouble("temp"), rs.getDouble("pressure"),
                    rs.getDouble("humidity"), rs.getDouble("tempmin"), rs.getDouble("tempmax"), rs.getDouble("wind"), LocalDateTime.parse(rs.getString("lastcall")));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new StatusNotFound();
        }

    }

    @Override
    public WeatherStatus getStatusCoord(Coordinates coordinates) throws StatusNotFound {
        String sql = "SELECT lastcall, temp, description, pressure, humidity, tempmin, tempmax, wind FROM weatherStatusCoord WHERE latitude = ? and longitude = ?;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getLatitude());
            pstmt.setDouble(2, coordinates.getLongitude());

            ResultSet rs = pstmt.executeQuery();
            return new WeatherStatus(rs.getString("description"), rs.getDouble("temp"), rs.getDouble("pressure"),
                    rs.getDouble("humidity"), rs.getDouble("tempmin"), rs.getDouble("tempmax"), rs.getDouble("wind"), LocalDateTime.parse(rs.getString("lastcall")));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new StatusNotFound();
        }

    }

    @Override
    public void updateFavouriteCity(City city) {
        String sql = "UPDATE weatherStatusCity SET favorite = 1 WHERE name = ? ;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateFavouriteCoord(Coordinates coordinates) {
        String sql = "UPDATE weatherStatusCoord SET favorite = 1 WHERE latitude = ? AND longitude = ?;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getLatitude());
            pstmt.setDouble(2, coordinates.getLongitude());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteFavouriteCity(City city) {
        String sql = "UPDATE weatherStatusCity SET favorite = 0 WHERE name = ? AND favorite = 1;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public void deleteFavouriteCoord(Coordinates coordinates) {
        String sql = "UPDATE weatherStatusCoord SET favorite = 0 WHERE latitude = ? AND longitude = ? AND favorite = 1;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, coordinates.getLatitude());
            pstmt.setDouble(1, coordinates.getLongitude());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public List<City> getFavouriteCity(){
        String sql = "SELECT DISTINCT(name) FROM weatherStatusCity where favorite = 1;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            List<City> cities = new ArrayList<>();
            while(rs.next()){
                cities.add(new City(rs.getString("name")));
            }
            System.out.println("cities");
            return cities;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


    @Override
    public void deleteStatus(City city){
        String sql = "DELETE FROM weatherStatusCity WHERE name = ? AND favorite = 0;";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, city.getName());
            pstmt.executeUpdate();
            System.out.println("deleting");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}