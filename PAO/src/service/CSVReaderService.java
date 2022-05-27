package service;


import com.sun.tools.javac.Main;
import objects.food.Dessert;
import objects.food.Drink;
import objects.food.MainDish;
import objects.people.*;
import objects.shop.CakeShop;
import objects.shop.FastFood;
import objects.shop.FoodShop;
import objects.shop.Restaurant;

import java.io.*;
import java.util.*;

public class CSVReaderService {

    private static CSVReaderService single_instance = null;
    private HashMap<Integer, FoodShop> foodshops;
    private int foodShopId;
    private Set<Person> accounts;

    public HashMap<Integer, FoodShop> getFoodshops() {
        return foodshops;
    }

    public void setFoodshops(HashMap<Integer, FoodShop> foodshops) {
        this.foodshops = foodshops;
    }

    public Set<Person> getAccounts() {
        return accounts;
    }

    public static synchronized CSVReaderService getInstance(){
        if (single_instance == null)
            single_instance = new CSVReaderService();
        return single_instance;
    }

    private void ReadCakeShop(String [] array, int k, Owner owner, List<DeliveryPerson> deliveryPeople){

        String name = array[k++];
        int n = Integer.parseInt(array[k++]);
        List<Dessert> desserts = new ArrayList<Dessert>();
        HashMap<String, Integer> stock = new HashMap<String, Integer>();

        for(int i = 0; i < n; i++){
            Dessert dessert = new Dessert(array[k++],Double.parseDouble(array[k++]),array[k++],Integer.parseInt(array[k++]));
            desserts.add(dessert);
            stock.put(dessert.getName(), Integer.parseInt(array[k++]));
        }

        CakeShop cakeShop = new CakeShop(name, owner, deliveryPeople, stock, desserts);

        this.foodShopId++;

        this.foodshops.put(foodShopId, cakeShop);


    }

    private void ReadFastFood(String [] array, int k, Owner owner, List<DeliveryPerson> deliveryPeople){

        String name = array[k++];
        int n = Integer.parseInt(array[k++]);
        List<MainDish> mainDishes = new ArrayList<MainDish>();
        HashMap<String, Integer> stock = new HashMap<String, Integer>();

        for(int i = 0; i < n; i++){
            MainDish mainDish = new MainDish(array[k++], Double.parseDouble(array[k++]), array[k++], array[k++], Integer.parseInt(array[k++]) );
            mainDishes.add(mainDish);
            stock.put(mainDish.getName(), Integer.parseInt(array[k++]));
        }

        n = Integer.parseInt(array[k++]);
        List<Drink> drinks = new ArrayList<Drink>();

        for(int i = 0; i < n; i++){
            Drink drink = new Drink(array[k++], Double.parseDouble(array[k++]), array[k++], array[k++], array[k++]);
            drinks.add(drink);
            stock.put(drink.getName(), Integer.parseInt(array[k++]));
        }

        FastFood fastFood = new FastFood(name, owner, deliveryPeople, stock, mainDishes, drinks);
        this.foodShopId++;
        this.foodshops.put(foodShopId, fastFood);

    }

    private void ReadRestaurant(String [] array, int k, Owner owner, List<DeliveryPerson> deliveryPeople){

        String name = array[k++];
        int n = Integer.parseInt(array[k++]);
        List<MainDish> mainDishes = new ArrayList<MainDish>();
        HashMap<String, Integer> stock = new HashMap<String, Integer>();

        for(int i = 0; i < n; i++){
            MainDish mainDish = new MainDish(array[k++], Double.parseDouble(array[k++]), array[k++], array[k++], Integer.parseInt(array[k++]) );
            mainDishes.add(mainDish);
            stock.put(mainDish.getName(), Integer.parseInt(array[k++]));
        }

        n = Integer.parseInt(array[k++]);
        List<Drink> drinks = new ArrayList<Drink>();

        for(int i = 0; i < n; i++){
            Drink drink = new Drink(array[k++], Double.parseDouble(array[k++]), array[k++], array[k++], array[k++]);
            drinks.add(drink);
            stock.put(drink.getName(), Integer.parseInt(array[k++]));
        }

        n = Integer.parseInt(array[k++]);
        List<Dessert> desserts = new ArrayList<Dessert>();

        for(int i = 0; i < n; i++){
            Dessert dessert = new Dessert(array[k++],Double.parseDouble(array[k++]),array[k++],Integer.parseInt(array[k++]));
            desserts.add(dessert);
            stock.put(dessert.getName(), Integer.parseInt(array[k++]));
        }

        Restaurant restaurant = new Restaurant(name, owner, deliveryPeople, stock, mainDishes, drinks, desserts);
        this.foodShopId++;
        this.foodshops.put(foodShopId, restaurant);

    }

    private <T> void serviceMethod(String path, Class<T> classOf){
        try{

            BufferedReader buffer = new BufferedReader(new FileReader(path));

            String line = buffer.readLine();

            while(line != null){
                String [] array = line.split(",");
                int k = 0;
                Owner owner =  new Owner(array[k++], array[k++], array[k++], array[k++]);
                int n = Integer.parseInt(array[k++]);
                List<DeliveryPerson> deliveryPeople = new ArrayList<DeliveryPerson>();

                for (int i = 0; i < n ; i++){
                    DeliveryPerson deliveryPerson = new DeliveryPerson(array[k++], array[k++], array[k++], array[k++]);
                    deliveryPeople.add(deliveryPerson);

                }

                if(classOf.toString().equals("class objects.shop.CakeShop")){
                    this.ReadCakeShop(array, k, owner, deliveryPeople);
                }
                else if(classOf.toString().equals("class objects.shop.FastFood")){
                    this.ReadFastFood(array, k, owner, deliveryPeople);
                }
                else if(classOf.toString().equals("class objects.shop.Restaurant")){
                    this.ReadRestaurant(array, k, owner, deliveryPeople);
                }
                else
                    System.out.println("Eroare la citirea din fisier");

                line = buffer.readLine();

            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void ReadPeople(){
        //functie de owner

        try{
            String path = "src/files/People.csv";
            BufferedReader buffer = new BufferedReader(new FileReader(path));

            String line = buffer.readLine();

            while(line != null){
                String [] array = line.split(",");
                if(array[0].equals("admin")){
                    Owner admin = new Owner(array[1], array[2], array[3], array[4]);
                    this.accounts.add(admin);
                    line = buffer.readLine();
                }

                else{
                    Customer customer = new Customer(array[1], array[2], array[3], array[4]);
                    this.accounts.add(customer);
                    line = buffer.readLine();
                }

            }

        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private CSVReaderService(){
            this.accounts = new HashSet<Person>();
            this.foodshops = new HashMap<Integer, FoodShop>();

            ReadPeople();

            this.serviceMethod("src/files/CakeShop.csv", CakeShop.class);
            this.serviceMethod("src/files/FastFood.csv", FastFood.class);
            this.serviceMethod("src/files/Restaurant.csv", Restaurant.class);

    }


}
