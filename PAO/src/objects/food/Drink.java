package objects.food;

import java.util.Scanner;

public class Drink extends Dish{

    private String temperature; // hot/cold
    private String flavour;

    public Drink(){}

    public Drink(String name, double price, String alergens, String temperature, String flavour) {
        super(name, price, alergens);
        this.temperature = temperature;
        this.flavour = flavour;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Drink name:");
        String name = var.nextLine();
        this.name = name;


        System.out.println("Temperature:[hot/cold] ");
        String temperature = var.nextLine();

        System.out.println("Flavour: ");
        String flavour = var.nextLine();

        System.out.println("Alergens: ");
        String alergens = var.nextLine();
        this.alergens = alergens;


        System.out.println("Price: ");
        Double price = var.nextDouble();
        var.nextLine();
        this.price = price;



    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", alergens='" + alergens + '\'' +
                ", temperature='" + temperature + '\'' +
                ", flavour='" + flavour + '\'' +
                '}';
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
}
