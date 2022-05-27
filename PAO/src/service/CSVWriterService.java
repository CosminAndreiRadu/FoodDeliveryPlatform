package service;

import objects.shop.*;

import java.io.*;

public class CSVWriterService {
    //clasa singleton

    private static CSVWriterService single_instance = null;
    private BufferedWriter buffer;

    private CSVWriterService() {}

    public static synchronized CSVWriterService getInstance() {
        if(single_instance == null)
            single_instance = new CSVWriterService();
        return single_instance;
    }

    public <T> void writeShop(Object foodshop, Class<T> classOf, String path){

        try{
            buffer = new BufferedWriter(new FileWriter(path,true));

            //clean file before writing

            new FileWriter(path, false).close();

            if (classOf.toString().equals("class objects.shop.CakeShop")){
                System.out.println("CakeShop is being written");
                buffer.write(((CakeShop) foodshop).toString());
            }
            else if (classOf.toString().equals("class objects.shop.FastFood")){
                System.out.println("FastFood is being written");
                buffer.write(((FastFood) foodshop).toString());
            }
            else if (classOf.toString().equals("class objects.shop.Restaurant")){
                System.out.println("Restaurant is being written");
                buffer.write(((Restaurant) foodshop).toString());
            }

            buffer.flush();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
