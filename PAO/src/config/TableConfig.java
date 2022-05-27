package config;

import repository.RepositoryHelper;

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.*;

public class TableConfig {

    public void createTables() {

        File file = new File("src/files/CreateTablesQuerries.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String sqlCreateTable;

            while ((sqlCreateTable = br.readLine()) != null) {

                Connection connection = DBconfig.getDBConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try{
                    repositoryHelper.executeSqlCommand(connection, sqlCreateTable);
                }catch (SQLException ex) {
                    System.out.println("DB tables create error!");
                }


            }

        }catch(FileNotFoundException ex) {
            System.out.println("file not found!");
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void addRows() {

        File file = new File("src/files/AddRowsQuerries.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String sqlInsertRow;

            while((sqlInsertRow = br.readLine()) != null) {

                Connection connection = DBconfig.getDBConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeSqlUpdateCommand(connection, sqlInsertRow);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Db insert row error!");
                }

            }

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void deleteAllRows() {

        File file = new File("src/files/DeleteAllRowsQuerries.txt");

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String sqlDeleteRow;

            while((sqlDeleteRow = br.readLine()) != null) {
                Connection connection = DBconfig.getDBConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();
                repositoryHelper.executeSqlUpdateCommand(connection, sqlDeleteRow);

            }
            System.out.println("Rows deleted");

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (SQLException ex) {
            System.out.println("DB delete rows error!");
        }

    }

    public void dropAllTables(){
        File file = new File("src/files/DropAllTablesQuerries.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String sqlDropTable;

            while((sqlDropTable = br.readLine()) != null) {
                Connection connection = DBconfig.getDBConnection();
                RepositoryHelper repositoryHelper = new RepositoryHelper();

                repositoryHelper.executeSqlCommand(connection, sqlDropTable);

            }

        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("DB drop table error");
        }

    }

    public void displayTable() {
        String sql = "";
        System.out.println("Introduce the name of the table you want to list");
        Scanner var = new Scanner(System.in);
        String name = var.nextLine();

        if (name.equalsIgnoreCase("desserts"))
            sql = "SELECT * FROM desserts";
        else if (name.equalsIgnoreCase("drinks"))
            sql = "SELECT * FROM drinks";
        else if (name.equalsIgnoreCase("maindishes"))
            sql = "SELECT * FROM maindishes";

        Connection connection = DBconfig.getDBConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try{
            ResultSet rs = repositoryHelper.executeSqlQuerryCommand(connection, sql);

                while(rs.next()){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int nrc = rsmd.getColumnCount();

                    for(int i = 2; i<= nrc; i++)
                        System.out.println(rs.getString(i) + " , ");

                }


        }catch(SQLException ex) {
            ex.printStackTrace();
        }



    }



}
