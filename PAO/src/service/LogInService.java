package service;

import objects.order.Order;
import objects.people.*;
import objects.shop.FoodShop;

import java.util.*;

public class LogInService {
    private Set <Person> accounts;
    private static LogInService single_instance = null;
    private Person curentUser;





    private void LogIn(){
        this.accounts = new HashSet<Person>();
        this.curentUser = null;
    }

    public static LogInService getInstance(){
        if (single_instance == null)
            single_instance = new LogInService();

        return single_instance;
    }

    //login method:
    public boolean signIn(String email, String password){
        if (accounts != null) {
            for (Person it: accounts){
                System.out.println("returned:  " + it.getEmail() + " " + it.getPassword());
                System.out.println("given:  " + email + " "+ password);
                if (email.equals(it.getEmail()) && password.equals(it.getPassword())){
                    this.curentUser = it;
                    return true;
                }
            }
        }
        return false;

    }

    public boolean signUp(Person person){
        if(accounts != null){
            for(Person it : accounts){
                if (person.getEmail().equals(it.getEmail()) && person.getPassword().equals(it.getPassword())){
                    return false;
                }
            }
        }
        this.accounts.add(person);
        return true;

    }

    public Person getCurentUser() {
        return curentUser;
    }

    public void setCurentUser(Person curentUser) {
        this.curentUser = curentUser;
    }

    public void setAccounts(Set<Person> accounts){
        this.accounts = accounts;
    }

}
