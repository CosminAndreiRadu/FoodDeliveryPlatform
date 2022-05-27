package service;

import objects.order.Order;
import objects.people.Customer;
import objects.people.Person;
import objects.shop.FoodShop;

import java.util.*;

class Sort implements Comparator<Map.Entry<Integer, FoodShop>> {

    //folosim pentru sortarea in treeset
    //comparam dupa rating

    public int compare(Map.Entry<Integer, FoodShop> entry1, Map.Entry<Integer, FoodShop> entry2){
        FoodShop reviews1 = entry1.getValue();
        FoodShop reviews2 = entry2.getValue();

        if (reviews1.getReviews() > reviews2.getReviews())
            return -1;
        return 1;

    }

}


public class Service {
    //clasa singleton

    private static Service single_instance = null;
    private LogInService login;
    private HashMap<Integer, FoodShop> foodshops;
    private HashMap<Integer, Order> orders;
    private Person curentUser;
    private int foodShopId;
    private int orderId;
    private CSVReaderService read;
    private AuditService write;

    private Service(){
        this.read = CSVReaderService.getInstance();
        this.write = AuditService.getInstance();
        this.foodshops = new HashMap<Integer, FoodShop>();
        this.orders = new HashMap<Integer, Order>();

        this.foodshops = this.read.getFoodshops();

    }

    public static synchronized Service getInstance(){
        if (single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    public void startOrder() {
        write.WriteTimestamp("Create Order");

        Scanner var = new Scanner(System.in);

        System.out.println("Place order: ");

        Order order = new Order();



        order.setCustomer(curentUser);
        order.scan(foodshops);

        orderId += 1;

        order.setTotal();

        if(order.getTotal() > 0 ) {
            System.out.println("You have to pay: " + order.getTotal() + "RON");
            System.out.println("You have the order: ");
            System.out.println("     Id: " + orderId);
            System.out.println(order);
            System.out.println("Do you want to place your order? (yes/no) ");

            String answer = var.nextLine();
            if(answer.equals("yes")) {
                orders.put(orderId, order);
                System.out.println("You have placed you order!");
            }
        }

    }


    public void cancelOrder() {
        write.WriteTimestamp("Cancel order");

        Scanner var = new Scanner(System.in);
        System.out.println("Introduce the id of the order you want to cancel: ");

        int id = var.nextInt();

        if(this.orders.containsKey(id)){
            this.orders.remove(id);
            System.out.println("Order " + id + " has been removed successfully!");

        }else {
            System.out.println("Order doesn't exist!");
        }

    }

    public void repeatOrder(){
        write.WriteTimestamp("Repeat order");

        Scanner var = new Scanner(System.in);
        System.out.println("Your last Orders: ");

        Set set  = orders.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if (((Order) entry.getValue()).getCustomer().getName().equals(curentUser.getName()))
                System.out.println(" Id comanda: " + (Integer) entry.getKey() + " - Pret:  " + ((Order) entry.getValue()).getTotal());

        }

        System.out.println("Choose the id of the order you want to repeat: ");
        int id = var.nextInt();

        if(this.orders.containsKey(id)){
            this.orders.put(orderId+1, orders.get(orderId));
            orderId += 1;
            System.out.println("Order has been repeated successfully, new id is: "+ orderId);


        } else{
            System.out.println("Order doesn't exist!");
        }



    }

    public void listFoodShops(){

        write.WriteTimestamp("List FoodShops");

        System.out.println("FoodShops list: ");

        Set set = foodshops.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println((FoodShop) entry.getValue());
            System.out.println("\n");
        }

    }

    public void listFoodShop(){
        write.WriteTimestamp("List FoodShop");

        Scanner var = new Scanner(System.in);

        System.out.println("Introduce name of the show you want to list");
        String name = var.nextLine();
        System.out.println("Foodshop:          "+ name);

        Set set = foodshops.entrySet();
        Iterator it = set.iterator();

        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if(((FoodShop) entry.getValue()).getName().equalsIgnoreCase(name)){
                System.out.println((FoodShop) entry.getValue());
                break;
            }
        }

    }

    public void listFoodShopsByReviews() {
        write.WriteTimestamp("List FoodShops By Reviews");

        Set<Map.Entry<Integer, FoodShop>> set = new TreeSet<>(new Sort());
        set.addAll(this.foodshops.entrySet());

        for(Map.Entry<Integer, FoodShop> entry : set){
            System.out.println(entry.getValue().getName()+ " Rating: "+entry.getValue().getReviews());
        }
    }

    public void reviewFoodShop() {
        write.WriteTimestamp("Review FoodShop");

        Scanner var = new Scanner(System.in);
        System.out.println("FoodShops: ");

        if(foodshops.isEmpty()){
            System.out.println("No foodshop available");
        }else {
            Set set = foodshops.entrySet();
            Iterator it = set.iterator();

            while(it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println(((FoodShop) entry.getValue()).getName());
            }

            System.out.println("Choose the name of the shop you want to review: ");
            String name = var.nextLine();

            it = set.iterator();
            FoodShop foodShop;
            while(it.hasNext()) {

                Map.Entry entry = (Map.Entry) it.next();

                if (((FoodShop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                    foodShop = (FoodShop) entry.getValue();
                    System.out.println("Choose a number of stars between 1 and 5: ");
                    int review = var.nextInt();
                    foodShop.setReviews(review);
                    System.out.println("Thank you for your review!");
                    break;
                }

            }


        }
    }

    public void writeFoodShopInCSV() {
        write.WriteTimestamp("Write FoodShop in CSV File");

        Scanner var = new Scanner(System.in);
        CSVWriterService csvWrite = CSVWriterService.getInstance();

        System.out.println("Introduce the name of the foodshop you want to write: ");
        String name = var.nextLine();

        System.out.println("Choose file path (ex: f1/f2/File.csv):  ");
        String path = var.nextLine();

        Set set = foodshops.entrySet();
        Iterator it = set.iterator();
        Boolean ok = false;

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            if (((FoodShop) entry.getValue()).getName().equalsIgnoreCase(name)) {
                System.out.println("Writing foodshop: " + name);
                ok = true;
                csvWrite.writeShop(entry.getValue(), entry.getValue().getClass(), path);
                break;
            }

        }
        if (ok == false)
            System.out.println("The shop name you have introduced isn't valid!");

    }

    public int logIn(){

        login = LogInService.getInstance();
        login.setAccounts(this.read.getAccounts());

        int type = 0;

        while( true) {
            Scanner var = new Scanner(System.in);
            System.out.println("Sign in / Sign up [0/1]: ");
            Integer answer = var.nextInt();
            var.nextLine();

            if(answer == 0) {
                write.WriteTimestamp("Sign in");

                String email;
                String password;

                System.out.println("Email: ");
                email = var.nextLine();

                System.out.println("password: ");
                password = var.nextLine();

                if(login.signIn(email, password)) {
                    System.out.println("You have signed in succesfully!");
                    curentUser = login.getCurentUser();
                    if(email.equals("admin@admin.com"))
                        type = 1;
                    break;
                } else {
                    System.out.println("Wrong password/email!");
                }
            } else {
                write.WriteTimestamp("Sign up");

                Person customer = new Customer();
                customer.scan();

                if(login.signUp(customer)) {
                    System.out.println("You have signed up successfully!");
                }
                else
                    System.out.println("Already signed in!");
            }

        }

        return type;
    }

    public void logOff() {

        write.WriteTimestamp("Log off");

        login.setCurentUser(null);
        System.out.println("You have been successfully logged off!");

    }

    public String getcurentUserEmail(){
        return curentUser.getEmail();
    }

}
