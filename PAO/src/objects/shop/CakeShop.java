package objects.shop;

import objects.food.Dessert;
import objects.food.Dish;
import objects.food.Drink;
import objects.food.MainDish;
import objects.people.DeliveryPerson;
import objects.people.Owner;

import java.util.*;

public class CakeShop extends FoodShop{

    private List<Dessert> desserts;

    public CakeShop() {
        this.desserts = new ArrayList<Dessert>();
    }

    public CakeShop(String name, Owner owner, List<DeliveryPerson> deliveryPeople, HashMap<String, Integer> stock, List<Dessert> desserts) {
        super(name, owner, deliveryPeople, stock);
        this.desserts = desserts;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("CakeShop Name: ");
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

        System.out.println("Desserts menu: ");
        System.out.println("Introduce number of desserts in the menu: ");
        int dsNumber = var.nextInt();
        for(int i = 0; i < dsNumber; i++){
            System.out.println("Introduce dessert  " + i + " : ");
            Dessert dessert = new Dessert();
            dessert.scan();
            this.desserts.add(dessert);

            System.out.println("Introduce the number of " + dessert.getName() +" desserts on stock: ");
            int dsQ = var.nextInt();
            stock.put(dessert.getName(), dsQ);

        }


    }

    @Override
    public String toString() {
        return "CakeShop{" +
                "desserts=" + desserts +
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

        for(Dessert it: desserts){
            temp.add(it);
        }

        return temp;

    }

    public List<Dessert> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<Dessert> desserts) {
        this.desserts = desserts;
    }
}
