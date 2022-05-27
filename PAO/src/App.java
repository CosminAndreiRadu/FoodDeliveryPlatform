import java.util.Scanner;
import service.*;


public class App {
    //clasa singleton

    private static App single_instance = null;

    private App () {}

    public static synchronized App getInstance(){
        if(single_instance == null)
            single_instance = new App();

        return single_instance;
    }

    public void start() {
        Service service = Service.getInstance();

        if(service.logIn() == 1) {
            this.adminActions(service);
        }
        else this.userActions(service);
    }


    private void adminActions(Service service){

        Scanner var = new Scanner(System.in);

        while(true){

            System.out.println("Signed in as "+ service.getcurentUserEmail());
            System.out.println();
            System.out.println("Choose one of the following actions id: ");
            System.out.println();
            System.out.println("1 - Log Off");
            System.out.println("2 - Manage DB");
            System.out.println();


            int choice = var.nextInt();
            var.nextLine();

            if(choice == 1) {
                service.logOff();
                break;
            }

            else if(choice == 2) {
                DBService dbService = DBService.getInstance();
                dbService.service();
            }

            System.out.println("Do you want another action (yes/no)");
            String answer = var.nextLine();
            if(!answer.equalsIgnoreCase("yes"))
                break;
        }

    }


    private void userActions(Service service) {

        Scanner var = new Scanner(System.in);

        while(true){

            System.out.println("You are signed in as: " + service.getcurentUserEmail());
            System.out.println();
            System.out.println("Choose one of the following actions id: ");
            System.out.println();
            System.out.println("1 - Show all available foodshops");
            System.out.println("2 - Show given foodshop");
            System.out.println("3 - Show best foodshops");
            System.out.println("4 - Order food");
            System.out.println("5 - Cancel order");
            System.out.println("6 - Repeat order");
            System.out.println("7 - Give a review");
            System.out.println("8 - Write shop in file");
            System.out.println("9 - LogOff");
            System.out.println();

            int choice = var.nextInt();
            var.nextLine();

            if(choice == 1) {
                service.listFoodShops();
            }
            else if(choice == 2) {
                service.listFoodShop();
            }
            else if(choice == 3){
                service.listFoodShopsByReviews();
            }
            else if(choice == 4) {
                service.startOrder();
            }
            else if(choice == 5) {
                service.cancelOrder();
            }
            else if(choice == 6) {
                service.repeatOrder();
            }
            else if(choice == 7) {
                service.reviewFoodShop();
            }
            else if(choice == 8) {
                service.writeFoodShopInCSV();
            }
            else if(choice == 9) {
                service.logOff();
                break;
            }


        }

    }

}
