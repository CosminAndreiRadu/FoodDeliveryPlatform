package service;

import com.sun.tools.javac.Main;
import config.TableConfig;
import objects.food.Dessert;
import objects.food.Drink;
import objects.food.MainDish;
import repository.DessertRepository;
import repository.DrinkRepository;
import repository.MainDishRepository;

import java.util.Scanner;

public class DBService {
    private TableConfig data;
    private AuditService write;
    Scanner var;
    private static DBService single_instance = null;


    private DBService() {
        this.write = AuditService.getInstance();
        this.data = new TableConfig();
        this.var = new Scanner(System.in);

    }

    public static synchronized DBService getInstance() {
        if(single_instance == null) {
            single_instance = new DBService();

        }

        return single_instance;

    }

    private void load() {
        this.data.dropAllTables(); // golesc baza de date

        this.data.createTables();
        this.data.addRows();

    }

    private void insert() {
        write.WriteTimestamp("Insert in database");

        System.out.println("Choose what type of product you want to add: (dessert/drink/maindish");

        String choice = this.var.nextLine();

        if(choice.equalsIgnoreCase("dessert")){

            DessertRepository dessertRepository = new DessertRepository();
            Dessert dessert = new Dessert();
            dessert.scan();
            dessertRepository.insertDessert(dessert);

        }

        else if (choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            Drink drink = new Drink();
            drink.scan();
            drinkRepository.insertDrink(drink);

        }

        else if (choice.equalsIgnoreCase("maindish")){

            MainDishRepository mainDishRepository = new MainDishRepository();
            MainDish mainDish = new MainDish();
            mainDish.scan();
            mainDishRepository.insertMainDish(mainDish);

        }

    }

    private void get(){

        write.WriteTimestamp("Get from database ");

        System.out.println("What do you want to get: drink/dessert/maindish");

        String choice = this.var.nextLine();
        String name ="";

        if(choice.equalsIgnoreCase("dessert")){
            DessertRepository dessertRepository = new DessertRepository();
            System.out.println("Give the name of the dessert: ");
            name = var.nextLine();

            Dessert dessert = dessertRepository.getDessert(name);
            System.out.println(dessert);

        }

        else if ( choice.equalsIgnoreCase("drink")){
            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.println("Give the name of the drink: ");
            name = var.nextLine();

            Drink drink = drinkRepository.getDrink(name);
            System.out.println(drink);

        }

        else if (choice.equalsIgnoreCase("maindish")){
            MainDishRepository mainDishRepository = new MainDishRepository();
            System.out.println("Give the name of the maindish: ");
            name = var.nextLine();

            MainDish mainDish = mainDishRepository.getMaindish(name);
            System.out.println(mainDish);

        }


    }

    private void update(){
        write.WriteTimestamp("Update entry in database");

        System.out.println("What do you want to update: drink/dessert/maindish " );

        String choice = this.var.nextLine();
        String name = "";

        if(choice.equalsIgnoreCase("dessert")){

            DessertRepository dessertRepository = new DessertRepository();
            System.out.println("Give the name of the dessert you want to update: ");
            name = var.nextLine();
            System.out.println("New grams of sugar: ");
            int sugar = var.nextInt();
            var.nextLine();
            dessertRepository.updateDessert(name, sugar);
            Dessert dessert = dessertRepository.getDessert(name);
            System.out.println(dessert);


        }

        else if(choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.println("Give the name of the drink you want to update: ");
            name = var.nextLine();
            System.out.println("New price: ");
            Double price = var.nextDouble();
            var.nextLine();
            drinkRepository.updateDrink(name, price);
            Drink drink = drinkRepository.getDrink(name);
            System.out.println(drink);

        }

        else if(choice.equalsIgnoreCase("maindish")){

            MainDishRepository mainDishRepository = new MainDishRepository();
            System.out.println("Give the name of the maindish you want to update: ");
            name = var.nextLine();
            System.out.println("New price: ");
            Double price = var.nextDouble();
            var.nextLine();
            mainDishRepository.updateMainDish(name, price);
            MainDish mainDish = mainDishRepository.getMaindish(name);
            System.out.println(mainDish);


        }

    }


    private void delete(){
        write.WriteTimestamp("Delete from database");

        System.out.println("Choose what you want to remove: maindish/drink/dessert");

        String choice = this.var.nextLine();
        String name="";

        if(choice.equalsIgnoreCase("dessert")){

            DessertRepository dessertRepository = new DessertRepository();
            System.out.println("Give the name of the dessert: ");
            name = var.nextLine();
            dessertRepository.deleteDessert(name);

        }

        else if (choice.equalsIgnoreCase("drink")){

            DrinkRepository drinkRepository = new DrinkRepository();
            System.out.println("Give the name of the drink: ");
            name = var.nextLine();
            drinkRepository.deleteDrink(name);

        }

        else if(choice.equalsIgnoreCase("maindish")) {

            MainDishRepository mainDishRepository = new MainDishRepository();
            System.out.println("Give the name of the maindish: ");
            name = var.nextLine();
            mainDishRepository.deleteMainDish(name);

        }


    }

    public void service(){
        this.load();
        Scanner var2 = new Scanner(System.in);

        while(true) {

            System.out.println("    Database managing:   ");
            System.out.println();
            System.out.println("Choose action id: ");
            System.out.println();
            System.out.println("1 - List entries of a table");
            System.out.println("2 - Insert in table");
            System.out.println("3 - Get element by name");
            System.out.println("4 - Update entry");
            System.out.println("5 - Delete entry");
            System.out.println("6 - Clear all entries");
            System.out.println("7 - Drop all tables");

            int choice = var2.nextInt();

            if(choice == 1){
                this.write.WriteTimestamp("Display table");
                this.data.displayTable();
            }
            else if(choice == 2){
                this.write.WriteTimestamp("Insert in table");
                this.insert();
            }
            else if(choice == 3){
                this.write.WriteTimestamp("Display entry");
                this.get();
            }
            else if(choice == 4){
                this.write.WriteTimestamp("Update entry");
                this.update();
            }
            else if(choice == 5){
                this.write.WriteTimestamp("delete entry");
                this.delete();
            }
            else if(choice == 6){
                this.write.WriteTimestamp("delete tables");
                this.data.deleteAllRows();
            }
            else if(choice == 7){
                this.write.WriteTimestamp("Drop tables");
                this.data.dropAllTables();
            }

            System.out.println("you want another action? yes/no");
            var2.nextLine();
            String answer = var2.nextLine();

            if(!answer.equalsIgnoreCase("yes")){
                break;
            }

        }



    }


}
