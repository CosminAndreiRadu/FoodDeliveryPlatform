package repository;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

public class RepositoryHelper {

    private static RepositoryHelper single_instance;

    public RepositoryHelper() {}

    public static synchronized RepositoryHelper  getRepositoryHelper() {
        if(single_instance == null)
            single_instance = new RepositoryHelper();

        return single_instance;

    }


    public void executeSqlCommand(Connection connection, String sql) throws SQLException {

        Statement st = connection.createStatement();

        st.execute(sql);

    }

    public void executeSqlUpdateCommand(Connection connection, String sql) throws SQLException {

        Statement st = connection.createStatement();

        int i = st.executeUpdate(sql);

    }

    public ResultSet executeSqlQuerryCommand(Connection connection, String sql) throws SQLException {

        Statement st = connection.createStatement();

        return st.executeQuery(sql);

    }


}
