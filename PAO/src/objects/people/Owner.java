package objects.people;

import java.util.Scanner;

public class Owner extends Person{

    private String function;

    public Owner(){}

    public Owner(String name, String email, String password, String function) {
        super(name, email, password);
        this.function = function;
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

        System.out.println("Function: ");
        String function = var.nextLine();

        System.out.println("Password: ");
        String password = var.nextLine();

        this.name = name;
        this.email = email;
        this.password = password;

    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", function='" + function + '\'' +
                '}';
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
