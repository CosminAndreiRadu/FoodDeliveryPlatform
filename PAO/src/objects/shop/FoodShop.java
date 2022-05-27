package objects.shop;

import java.util.*;

import objects.people.DeliveryPerson;
import objects.people.Owner;
import objects.food.*;


public abstract class FoodShop {

    protected String name;
    protected double reviews;
    protected Owner owner;
    protected List<DeliveryPerson> deliveryPeople;
    protected HashMap<String, Integer> stock;

    public FoodShop(){
        this.deliveryPeople = new ArrayList<DeliveryPerson>();
        this.stock = new HashMap<String, Integer>();
    }

    public FoodShop(String name, Owner owner, List<DeliveryPerson> deliveryPeople, HashMap<String, Integer> stock) {
        this.name = name;
        this.reviews = 0;
        this.owner = owner;
        this.deliveryPeople = deliveryPeople;
        this.stock = stock;
    }


    public abstract void scan();

    @Override
    public abstract String toString();

    public abstract List<Dish> getDishes();

    public String getName() {
        return name;
    }

    public void UpdateStock(String name){
        stock.replace(name, stock.get(name) - 1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReviews() {
        return reviews;
    }

    public void setReviews(double reviews) {
        this.reviews = reviews;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<DeliveryPerson> getDeliveryPeople() {
        return deliveryPeople;
    }

    public void setDeliveryPeople(List<DeliveryPerson> deliveryPeople) {
        this.deliveryPeople = deliveryPeople;
    }

    public HashMap<String, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<String, Integer> stock) {
        this.stock = stock;
    }
}
