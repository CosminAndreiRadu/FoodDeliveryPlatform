package objects.order;

import objects.food.Dish;
import objects.people.Customer;
import objects.people.DeliveryPerson;
import objects.people.Person;
import objects.shop.CakeShop;
import objects.shop.FastFood;
import objects.shop.FoodShop;
import objects.shop.Restaurant;

import java.util.*;

public class Order {

    private Person customer;
    private FoodShop foodshop;
    private DeliveryPerson deliveryPerson;
    private List<Dish> dishes;
    private String address;
    private double total;

    public Order(){
        this.dishes = new ArrayList<Dish>();
    }

    public Order(Customer customer, FoodShop foodshop, DeliveryPerson deliveryPerson, List<Dish> dishes, String address, double total) {
        this.customer = customer;
        this.foodshop = foodshop;
        this.deliveryPerson = deliveryPerson;
        this.dishes = dishes;
        this.address = address;
        this.total = total;
    }

    public void scan(HashMap<Integer, FoodShop> foodShops){
        Scanner var = new Scanner(System.in);

        System.out.println("Introduce order details: \n");

        System.out.println("Introduce delivery address: ");
        String address = var.nextLine();
        this.address = address;

        System.out.println("Available Foodshops: ");
        if(foodShops.isEmpty()){
            System.out.println("No shops available!");
        }
        else{
            Set tempSet = foodShops.entrySet();
            Iterator it = tempSet.iterator();

            while (it.hasNext()){

                Map.Entry entry = (Map.Entry) it.next();
                System.out.print(entry.getKey());
                System.out.print(" - ");
                System.out.print(((FoodShop) entry.getValue()).getName() + "\n");
            }

            System.out.println("Choose shop number: ");
            Integer id = var.nextInt();
            var.nextLine();


            it = tempSet.iterator();

            while (it.hasNext()){

                Map.Entry entry = (Map.Entry) it.next();

                if(entry.getKey().equals(id)){
                    if(entry.getValue() instanceof Restaurant)
                        this.foodshop = (Restaurant) entry.getValue();
                    else if (entry.getValue() instanceof FastFood)
                        this.foodshop = (FastFood) entry.getValue();
                    else
                        this.foodshop = (CakeShop) entry.getValue();

                    break;
                }


            }

            //iau un livrator random
            DeliveryPerson dp = this.getRandomDeliveryPerson();
            this.setDeliveryPerson(dp);
            int choice = 0 ;

            List<Dish> dishes = this.foodshop.getDishes();
            System.out.println("Available Dishes: ");

            for (int i = 0; i < dishes.size(); i++){
                System.out.println("Dish number - " + i + ": ");
                System.out.println(dishes.get(i));
            }



            System.out.println("Would you like to add a dish? (yes/no): " );
            String answer = var.nextLine();
            if (answer.equalsIgnoreCase("yes")){
                while(true) {
                    System.out.println("Choose dish number: ");
                    choice = var.nextInt();
                    var.nextLine();
                    this.addDish(dishes.get(choice));

                    //actualizez stocul
                    this.foodshop.UpdateStock((dishes.get(choice)).getName());

                    System.out.println("Do you want to add another dish? (yes/no): ");
                    answer = var.nextLine();
                    if(answer.equalsIgnoreCase("no"))
                        break;

                }
            }


        }


    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", foodshop=" + foodshop +
                ", deliveryPerson=" + deliveryPerson +
                ", dishes=" + dishes +
                ", address='" + address + '\'' +
                ", total=" + total +
                '}';
    }

    public DeliveryPerson getRandomDeliveryPerson(){
        List<DeliveryPerson> temp = this.foodshop.getDeliveryPeople();
        int random = (int) (Math.random() * temp.size());

        return temp.get(random);
    }

    public int verifyCupon(String code){
        if (code.substring(0, 5).toLowerCase() == "COSMIN"){
            return Integer.parseInt(code.substring(5, code.length()));

        }
        return 0;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }


    public FoodShop getFoodshop() {
        return foodshop;
    }

    public void setFoodshop(FoodShop foodshop) {
        this.foodshop = foodshop;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        double total = 0;

        for(Dish it: this.dishes){
            total += it.getPrice();
        }

        this.total = total;

    }
}
