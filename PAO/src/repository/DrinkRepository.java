package repository;

import config.DBconfig;
import objects.food.Drink;

import java.sql.*;

public class DrinkRepository {

    public void insertDrink(Drink drink){

        String sql = "{call insertDrink(?,?,?,?,?)}";

        Connection connection = DBconfig.getDBConnection();

        try{

            CallableStatement cs = connection.prepareCall(sql);
            cs.setString(1, drink.getName());
            cs.setDouble(2,drink.getPrice());
            cs.setString(3, drink.getAlergens());
            cs.setString(4, drink.getTemperature());
            cs.setString(5,drink.getFlavour());

            cs.registerOutParameter(1, Types.VARCHAR);

            cs.execute();


            System.out.println("Added Drink " + cs.getString(1));

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Drink getDrink(String name) {

        String sql = "SELECT * FROM drinks WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            return toDrink(rs);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    private Drink toDrink(ResultSet rs) throws SQLException{
        if(rs.next()){
            return new Drink(rs.getString(2),rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getString(6));

        }
        return null;

    }

    public void updateDrink(String name, Double price) {

        String sql = "UPDATE drinks SET price=? WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteDrink(String name) {
        String sql = "DELETE FROM drinks WHERE name=?";

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
