package objects.food;

public abstract class Dish {

    int id;
    protected String name;
    protected double price;
    protected String alergens;

    public Dish() {}

    public Dish(String name, double price, String alergens) {
        this.name = name;
        this.price = price;
        this.alergens = alergens;
    }

    public abstract void scan();

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAlergens() {
        return alergens;
    }

    public void setAlergens(String alergens) {
        this.alergens = alergens;
    }
}
