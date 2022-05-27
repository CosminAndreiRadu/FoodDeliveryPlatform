package repository;

import config.DBconfig;
import objects.food.Dessert;



import java.sql.*;

public class DessertRepository {

    public void insertDessert(Dessert dessert){

        String sql = "{call insertDessert(?,?,?,?)}";

        Connection connection = DBconfig.getDBConnection();

        try{

            CallableStatement cs = connection.prepareCall(sql);
            cs.setString(1, dessert.getName());
            cs.setDouble(2,dessert.getPrice());
            cs.setString(3, dessert.getAlergens());
            cs.setInt(4, dessert.getSugar());

            cs.registerOutParameter(1, Types.VARCHAR);

            cs.execute();


            System.out.println("Added Dessert " + cs.getString(1));

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Dessert getDessert(String name) {

        String sql = "SELECT * FROM desserts WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            return toDessert(rs);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    private Dessert toDessert(ResultSet rs) throws SQLException{
        if(rs.next()){
            return new Dessert(rs.getString(2),rs.getDouble(3), rs.getString(4), rs.getInt(5));

        }
        return null;

    }

    public void updateDessert(String name, Integer sugar) {

        String sql = "UPDATE desserts SET sugar=? WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, sugar);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteDessert(String name) {
        String sql = "DELETE FROM desserts WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


}
