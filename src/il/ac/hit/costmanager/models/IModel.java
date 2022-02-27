package il.ac.hit.costmanager.models;

import il.ac.hit.costmanager.views.Category;
import il.ac.hit.costmanager.views.Currency;
import il.ac.hit.costmanager.views.Item;
import il.ac.hit.costmanager.views.Person;
import java.time.LocalDate;
import java.util.List;
/**
 * This Interface describes CostManagerModel class, and it's methods that will work directly with the MySQL database.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public interface IModel
{
    /**
     * Adds a new item to the database.
     *
     * @param item              The data we want to INSERT into the database.
     * @param userId            User Id.
     * @param transactionAmount The amount that will be deducted from the user's wallet.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public int addItem(Item item, int userId, double transactionAmount) throws CostManagerException;
    /**
     * Adds a new category that the current user entered to the categories table in the database.
     * @param category The name of the category.
     * @param userId   id of the user
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public int addCategory(Category category, int userId) throws CostManagerException;
    /**
     * Adds new currency to the current user in the database.
     * @param currency The currency we want to add.
     * @param userId id of the user who wants to add a new currency.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public int addCurrency(Currency currency, int userId) throws CostManagerException;
    /**
     * Adds a new person to usersDetail table in the database.
     *
     * @param person The data we want to INSERT into usersDetails table.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     * @author Undescribed
     */
    public int addPerson(Person person) throws CostManagerException;
    /**
     * This function returns the items that belong to the current user.
     * @param personId id of the current user.
     * @return Array of Item that includes all the items of the current user.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public Item[] getItemsArrayListFromDBAccordingToPerson(int personId) throws CostManagerException;
    /**
     * This function returns the person from usersDetails table in the database that the name and the password belongs to him.
     * @param firstName The name of the user.
     * @param password  The password of the user.
     * @return Objects type Person.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public Person getPerson(String firstName, String password) throws CostManagerException;
    /**
     * This function returns the category from the database by name.
     * @param categoryName The name of the current category.
     * @param userId The id of the current user.
     * @return Category or null.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public Category getCategory(String categoryName, int userId) throws CostManagerException;
    /**
     * This function returns all the categories of the current user from the database.
     * @param userId The id of the current user.
     * @return List of categories.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public List<Category> getCategories(int userId) throws CostManagerException;
    /**
     * This function returns the currency from the database by name.
     * @param currencyName The name of the current currency.
     * @param userId The id of the current user.
     * @return Currency or null.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public Currency getCurrency(String currencyName, int userId) throws CostManagerException;
    /**
     * This function returns the currencies of the current user from the database.
     * @param userId Contains the id of the current user whose currencies we need to return.
     * @return List of currency.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public List<Currency> getCurrencies(int userId) throws CostManagerException;
    /**
     * This function returns all the data between 2 given dates for the current user.
     * @param personId  The id of the user.
     * @param firstDate The minimum date the items needs.
     * @param lastDate  The maximum date that we don't want the items to go above it.
     * @return Return list of items that include all the items of the current user and that are between the firsDate and lastDate.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public List<Item> getItemsByDates(int personId, LocalDate firstDate, LocalDate lastDate) throws CostManagerException;
    /**
     * Deletes an item from the database.
     *
     * @param itemId id of the item we wish to delete from the database.
     * @return Returns the number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public int deleteItem(int itemId) throws CostManagerException;
}
