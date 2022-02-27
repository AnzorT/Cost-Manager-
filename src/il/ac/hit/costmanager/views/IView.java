package il.ac.hit.costmanager.views;

import il.ac.hit.costmanager.viewmodels.IViewModel;
import java.util.List;
/**
 * This Interface describes CostManagerView class, and it's methods that will help the user "communicate" with the program.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public interface IView
{
    /**
     * Shows the items of the user.
     * @param items Contains the list of items.
     */
    public void showItems(Item[] items);
    /**
     * Set up the user details after login for the program.
     * @param person Contains the details of the user who just logged in.
     */
    public void showPerson(Person person);
    /**
     * Displays the pop-up message to the user.
     * @param message Contains the message to display to the user in the pop-up.
     */
    public void showMessage(Message message);
    /**
     * Sets vm to vm.
     * @param vm The vm of the vm field.
     */
    public void setIViewModel(IViewModel vm);
    /**
     * CostManagerView's constructor.
     */
    public void init();
    /**
     * Prepares the CostManagerView's page for display.
     */
    public void start();
    /**
     * Presents the categories of the user.
     * @param categoryList Contains all the categories of the user.
     */
    public void showCategories(List<Category> categoryList);
    /**
     * Presents the currencies of the user.
     * @param currencyList Contains all the currencies of the user.
     */
    public void showCurrencies(List<Currency> currencyList);
    /**
     * Presents the items of the user that are between two dates that the user chose.
     * @param itemsByDates Contains all the items of the user between two dates that he entered.
     */
    public void showItemsByDate(List<Item> itemsByDates);
    /**
     * Adds the item to the database and updates the wallet balance of the user.
     * @param item Contains the item the user wants to insert.
     * @param updatedBudget Contains the sum that needs to be deducted from the user's wallet balance.
     */
    public void createTransaction(Item item,double updatedBudget);
}
