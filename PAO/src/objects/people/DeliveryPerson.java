package objects.people;

import java.util.Scanner;

public class DeliveryPerson extends Person{

    private String carPlate;

    public DeliveryPerson(){}

    public DeliveryPerson(String name, String email, String password, String carPlate) {
        super(name, email, password);
        this.carPlate = carPlate;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Full Name: ");
        String name = var.nextLine();

        String email;
        //email validation
        while(true){
            System.out.println("E-mail: (must include @ and .");
            email = var.nextLine();
            int ok1 = email.indexOf('@');
            int ok2 = email.indexOf('.');

            if(ok1 > 0 && ok2 > 0)
                break;
            else
                System.out.println("Please provide a valid e-mail!");

        }

        System.out.println("Car Plate Number: ");
        String carPlate = var.nextLine();

        System.out.println("Password: ");
        String password = var.nextLine();

        this.name = name;
        this.email = email;
        this.password = password;

    }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "carPlate='" + carPlate + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
