package objects.people;

import java.util.Scanner;

public class Customer extends Person{

    private String phoneNumber;

    public Customer(){}

    public Customer(String name, String email, String password, String phoneNumber) {
        super(name, email, password);
        this.phoneNumber = phoneNumber;
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

        String phoneNumber;
        //phone verification using regex
        while(true){
            System.out.println("Phone number: (exactly 10 digits, starting with 0)");
            phoneNumber = var.nextLine();
            boolean ok = phoneNumber.matches("0[0-9]{9}");

            if(ok)
                break;
            else
                System.out.println("Please provide a valid phone number!");

        }

        System.out.println("Password: ");
        String password = var.nextLine();

        this.name = name;
        this.email = email;
        this.password = password;

    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ",phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
