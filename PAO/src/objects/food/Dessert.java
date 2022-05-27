package objects.food;

import java.util.Scanner;

public class Dessert extends Dish{

    private int sugar; //grams of sugar

    public Dessert(){}

    public Dessert(String name, double price, String alergens, int sugar) {
        super(name, price, alergens);
        this.sugar = sugar;
    }

    @Override
    public void scan() {
        Scanner var = new Scanner(System.in);

        System.out.println("Dessert name:");
        String name = var.nextLine();

        System.out.println("Alergens: ");
        String alergens = var.nextLine();

        System.out.println("Price: ");
        Double price = var.nextDouble();

        System.out.println("Sugar(grams): ");
        Integer sugar = var.nextInt();
        var.nextLine();



        this.name = name;
        this.price = price;
        this.alergens = alergens;
        this.sugar = sugar;

    }

    @Override
    public String toString() {
        return "Dessert{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", alergens='" + alergens + '\'' +
                ", sugar=" + sugar +
                '}';
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
