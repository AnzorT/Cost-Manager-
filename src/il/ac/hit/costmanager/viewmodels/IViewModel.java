package il.ac.hit.costmanager.viewmodels;

import il.ac.hit.costmanager.views.*;
import il.ac.hit.costmanager.models.IModel;
import java.time.LocalDate;
/**
 * This Interface describes CostManagerViewModel class, and it's methods that will work directly with the database.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public interface IViewModel {

    /**
     * Sets view to view.
     * @param view The IView we need to switch to.
     */
    public void setView(IView view);
    /**
     * Sets model to model.
     * @param model The IModel we need to switch to.
     */
    public void setModel(IModel model);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add an item that the user
     * inserted from the CostManagerView to the database with the help of the CostManagerModel.
     * @param item Contains data of the item the user wants to add.
     * @param personId The id of the user to which we need to refer the item to.
     * @param transaction The amount we need to subtract from the user's budget.
     */
    public void addItem(Item item, int personId, double transaction);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add a new person into the database.
     * @param person Contains the data of the new user that will be added to the database.
     */
    public void addPerson(Person person);
    /**
     * Mediates between CostManagerView and  CostManagerModel in order to add a new category that the user has entered.
     * @param category Contains the data of the new category we wish to insert into the database.
     * @param userId The id of the user.
     */
    public void addCategory(Category category, int userId);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add a new currency that the current user has entered.
     * @param currency The currency the user wants to add.
     * @param userId id of the current user.
     */
    public void addCurrency(Currency currency, int userId);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to present the user the items he has in the database.
     * @param personId Contains the data of the user's ID so that we know which items we need to display and which we don't.
     */
    public void getItemsForPerson(int personId);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to presents the user the categories that
     * he has inside the database.
     * @param  userId The id of the user.
     */
    public void getCategories(int userId);
    /**
     * This method mediates between the CostManagerView and CostManagerModel in order presents the user the items that are between
     * the two dates that he chose (beginDate and endDate).
     * @param personId Contains the ID of the user that we need to present the relevant data to.
     * @param beginDate The minimum date the items need to have or above.
     * @param endDate The maximum date the items can have or below it.
     */
    public void getDataByDates(int personId, LocalDate beginDate, LocalDate endDate);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to get the currencies of the current user.
     * @param userId id of the current user.
     */
    public void getCurrencies(int userId);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to check if the username and password that were entered
     * indeed exist in the database.
     * @param firstName Contains the username of the user that should already exist in the database.
     * @param password Contains the password of the user that should already exist in the database.
     */
    public void checkUserExistence(String firstName, String password);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to check if the current user can make the
     * transaction or not.
     * @param person Details of the user who wants to create the transaction.
     * @param itemCurrency Currency of the item.
     * @param itemPrice Price of the item.
     * @param item Details of the item.
     */
    public void checkTransaction(Person person, Currency itemCurrency, double itemPrice, Item item);
    /**
     * Mediates between CostManagerView and CostManagerModel in order to delete an item the user chose in the
     * CostManagerView and delete it from the database using the CostManagerModel.
     * @param itemId Contains the id of the item the user wishes to delete.
     * @param personId Contains the data of the user's id who wants to delete the item.
     */
    public void deleteItem(int itemId, int personId);
}