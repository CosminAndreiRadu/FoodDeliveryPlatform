package repository;

import com.sun.tools.javac.Main;
import config.DBconfig;
import objects.food.MainDish;

import java.sql.*;

public class MainDishRepository {

    public void insertMainDish(MainDish drink) {

        String sql = "{call insertMaindish(?,?,?,?,?)}";

        Connection connection = DBconfig.getDBConnection();

        try {

            CallableStatement cs = connection.prepareCall(sql);
            cs.setString(1, drink.getName());
            cs.setDouble(2, drink.getPrice());
            cs.setString(3, drink.getAlergens());
            cs.setString(4, drink.getIngredients());
            cs.setInt(5, drink.getSpiciness());

            cs.registerOutParameter(1, Types.VARCHAR);

            cs.execute();


            System.out.println("Added MainDish " + cs.getString(1));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public MainDish getMaindish(String name) {

        String sql = "SELECT * FROM maindishes WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            return toMainDish(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    private MainDish toMainDish(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new MainDish(rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6));

        }
        return null;

    }

    public void updateMainDish(String name, Double price) {

        String sql = "UPDATE maindishes SET price=? WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteMainDish(String name) {
        String sql = "DELETE FROM maindishes WHERE name=?";

        Connection connection = DBconfig.getDBConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


}