package il.ac.hit.costmanager.views;

import java.util.Date;
/**
 * This class describes Item.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Item {
    private int id;
    private String name;
    private double price;
    private Category category;
    private Currency currency;
    private int amount;
    private Date dateOfPurchase;

    /**
     * Constructor of Item, this constructor is for getting an item from the database.
     * @param id id of the item that was given to by the database.
     * @param name Name of the item the user purchased.
     * @param price Price of the item the user purchased.
     * @param currency Currency in which the item was purchased.
     * @param category The category to which the item belongs to.
     * @param amount How much of the item was purchased.
     * @param dateOfPurchase Date in which the item was purchased.
     */
    public Item(int id, String name, double price, Currency currency, Category category, int amount, Date dateOfPurchase) {
        setId(id);
        setName(name);
        setPrice(price);
        setCurrency(currency);
        setCategory(category);
        setAmount(amount);
        setDateOfPurchase(dateOfPurchase);
    }
    /**
     * Constructor of Item, this constructor is for adding new item to the database.
     * @param name Name of the item the user purchased.
     * @param price Price of the item the user purchased.
     * @param currency Currency in which the item was purchased.
     * @param category The category to which the item belongs to.
     * @param amount How much of the item was purchased.
     * @param dateOfPurchase Date in which the item was purchased.
     */
    public Item(String name, double price, Currency currency, Category category, int amount, Date dateOfPurchase) {
        this(0,name,price,currency,category,amount,dateOfPurchase);
    }
    /**
     * Returns Currency variable.
     * @return The current currency.
     */
    public Currency getCurrency() {
        return this.currency;
    }
    /**
     * Sets the currency in this currency field.
     * @param currency The currency of the currency field.
     */
    public void setCurrency(Currency currency) {
        if (currency != null) {
            this.currency = currency;
        }
    }
    /**
     * Returns Category variable.
     * @return Category of this category field.
     */
    public Category getCategory() { return this.category; }
    /**
     * Returns int variable.
     * @return id of this id field.
     */
    public int getId() { return this.id; }
    /**
     * Returns String variable.
     * @return Name of this name field.
     */
    public String getName() { return this.name; }
    /**
     * Returns double variable.
     * @return Price of this price field.
     */
    public double getPrice() { return this.price; }
    /**
     * Returns int variable.
     * @return Amount of this amount field.
     */
    public int getAmount() {
        return this.amount;
    }
    /**
     * Returns Date variable.
     * @return dateOfPurchase of this dateOfPurchase Date.
     */
    public Date getDateOfPurchase() {
        return this.dateOfPurchase;
    }
    /**
     * Sets the id in this id field.
     * @param id The id of the id field.
     */
    public void setId(int id) {
        if(id > 0) {
            this.id = id;
        }
    }
    /**
     * Sets the name in this name field.
     * @param name The name of the name field.
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()){
            this.name = name;
        }
    }
    /**
     * Sets the price in this price field.
     * @param price The price of the price field.
     */
    public void setPrice(double price) {
        if(price >= 0){
            this.price = price;
        }
    }
    /**
     * Sets the category in this category field.
     * @param category The category of the category field.
     */
    public void setCategory(Category category) {
        if (category != null) {
            this.category = category;
        }
    }
    /**
     * Sets the amount in this amount field.
     * @param amount The amount of the amount field.
     */
    public void setAmount(int amount) {
        if (amount > 0){
            this.amount = amount;
        }
    }
    /**
     * Sets the dateOfPurchase in this dateOfPurchase field.
     * @param dateOfPurchase The dateOfPurchase of the dateOfPurchase field.
     */
    public void setDateOfPurchase(Date dateOfPurchase) {
        if (dateOfPurchase != null){
            this.dateOfPurchase = dateOfPurchase;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", currency=" + currency +
                ", amount=" + amount +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }
}
