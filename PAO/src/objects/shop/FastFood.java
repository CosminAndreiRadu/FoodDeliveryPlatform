package objects.shop;


import objects.food.Dessert;
import objects.food.Dish;
import objects.food.Drink;
import objects.food.MainDish;
import objects.people.DeliveryPerson;
import objects.people.Owner;

import java.util.*;

public class FastFood extends FoodShop{

    private List<MainDish> mainDishes;
    private List<Drink> drinks;

    public FastFood() {
        this.mainDishes = new ArrayList<MainDish>();
        this.drinks = new ArrayList<Drink>();
    }

    public FastFood(String name, Owner owner, List<DeliveryPerson> deliveryPeople, HashMap<String, Integer> stock, List<MainDish> mainDishes, List<Drink> drinks) {
        super(name, owner, deliveryPeople, stock);
        this.mainDishes = mainDishes;
        this.drinks = drinks;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("FastFood Name: ");
        String name = var.nextLine();
        this.name = name;

        Owner owner = new Owner();
        owner.scan();
        this.owner = owner;

        System.out.println("Delivery People.csv: ");
        System.out.println("Introduce number of delivery employees: ");
        int eNumber = var.nextInt();

        for(int i = 0; i < eNumber; i++){
            DeliveryPerson deliveryPerson = new DeliveryPerson();
            deliveryPerson.scan();
            deliveryPeople.add(deliveryPerson);

        }

        System.out.println("Food menu: ");
        System.out.println("Introduce number of maindishes in the menu: ");
        int mNumber = var.nextInt();
        for(int i = 0; i < mNumber; i++){
            System.out.println("Introduce main dish " + i + " : ");
            MainDish mainDish = new MainDish();
            mainDish.scan();
            this.mainDishes.add(mainDish);

            System.out.println("Introduce the number of " + mainDish.getName() +" main dishes on stock: ");
            int mQ = var.nextInt();
            stock.put(mainDish.getName(), mQ);
        }

        System.out.println("Drinks menu: ");
        System.out.println("Introduce number of drinks in the menu: ");
        int dNumber = var.nextInt();
        for(int i = 0; i < dNumber; i++){
            System.out.println("Introduce drink " + i + " : ");
            Drink drink = new Drink();
            drink.scan();
            this.drinks.add(drink);

            System.out.println("Introduce the number of " + drink.getName() +" drinks on stock: ");
            int dQ = var.nextInt();
            stock.put(drink.getName(), dQ);


        }

    }

    @Override
    public String toString() {
        return "FastFood{" +
                "mainDishes=" + mainDishes +
                ", drinks=" + drinks +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                ", owner=" + owner +
                ", deliveryPeople=" + deliveryPeople +
                ", stock=" + stock +
                '}';
    }

    @Override
    public List<Dish> getDishes() {
        List<Dish> temp = new ArrayList<Dish>();

        for(MainDish it: mainDishes){
            temp.add(it);
        }

        for(Drink it: drinks){
            temp.add(it);
        }

        return temp;

    }

    public List<MainDish> getMainDishes() {
        return mainDishes;
    }

    public void setMainDishes(List<MainDish> mainDishes) {
        this.mainDishes = mainDishes;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
