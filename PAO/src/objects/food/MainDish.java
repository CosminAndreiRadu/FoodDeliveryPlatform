package objects.food;

import java.sql.SQLOutput;
import java.util.*;

public class MainDish  extends Dish{

    private String ingredients;
    private int spiciness;          //value 0/1/2 meaning not spicy / mild / very spicy

    public MainDish(){}

    public MainDish(String name, double price, String alergens, String ingredients, int spiciness) {
        super(name, price, alergens);
        this.ingredients = ingredients;
        this.spiciness = spiciness;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Dish name:");
        String name = var.nextLine();

        System.out.println("Ingredients: ");
        String ingredients = var.nextLine();

        System.out.println("Alergens: ");
        String alergens = var.nextLine();

        System.out.println("Spiciness: ");
        Integer spiciness = var.nextInt();
        var.nextLine();

        System.out.println("Price: ");
        Double price = var.nextDouble();
        var.nextLine();

        this.name = name;
        this.price = price;
        this.alergens = alergens;

    }

    @Override
    public String toString() {
        return "MainDish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", alergens='" + alergens + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", spiciness=" + spiciness +
                '}';
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getSpiciness() {
        return spiciness;
    }

    public void setSpiciness(int spiciness) {
        this.spiciness = spiciness;
    }
}
