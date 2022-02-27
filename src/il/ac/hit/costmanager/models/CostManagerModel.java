package il.ac.hit.costmanager.models;

import il.ac.hit.costmanager.views.Category;
import il.ac.hit.costmanager.views.Currency;
import il.ac.hit.costmanager.views.Item;
import il.ac.hit.costmanager.views.Person;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * This class describes CostManagerModel, implements the IModel interface.
 * It manages the reading and writing data to the database.
 * </p>
 *
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class CostManagerModel implements IModel {
    //the url we need to connect to for the database.
    private final String protocol = "jdbc:mysql://localhost:3306/costManager";

    /**
     * CostManagerModel's constructor.
     *
     * @throws CostManagerException an exception thrown if the driver not found.
     */
    public CostManagerModel() throws CostManagerException {
        try {
            String driverFullQualifiedName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverFullQualifiedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CostManagerException("problem with loading the driver, please contact the manager", e);
        }
    }

    /**
     * Adds a new item to the database.
     *
     * @param item              The data we want to INSERT into the database.
     * @param userId            User Id.
     * @param transactionAmount The amount that will be deducted from the user's wallet.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public int addItem(Item item, int userId, double transactionAmount) throws CostManagerException {
        if (item == null || userId < 0 || transactionAmount < 0) {
            throw new CostManagerException("There is something wrong with the item or there is an illegal price!!\n Please enter a price that is bigger than 0.");
        } else {
            PreparedStatement myStmt = null;
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
                connection.setAutoCommit(false);
                myStmt = connection.prepareStatement(new StringBuilder()
                        .append("INSERT into `costList` (costName ,price, currency, categoryId, userId, amount, dateOfPurchase) ")
                        .append("values (?,?,?,(SELECT categoryId FROM `categoriesList` WHERE categoryName = ? AND userId = ?),?,?,?)").toString());
                myStmt.setString(1, item.getName());
                myStmt.setDouble(2, item.getPrice());
                myStmt.setString(3, item.getCurrency().getName());
                myStmt.setString(4, item.getCategory().getCategory());
                myStmt.setInt(5, userId);
                myStmt.setInt(6, userId);
                myStmt.setInt(7, item.getAmount());
                myStmt.setDate(8, java.sql.Date.valueOf(
                        item.getDateOfPurchase().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
                myStmt.executeUpdate();
                myStmt = connection.prepareStatement("UPDATE usersDetails SET budget = budget - ? WHERE Id = ?");
                myStmt.setDouble(1, transactionAmount);
                myStmt.setInt(2, userId);
                int updateResult = myStmt.executeUpdate();
                connection.commit();
                return updateResult;
            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException ex) {
                }
                throw new CostManagerException("Adding item failed please try again.", e);
            } finally {
                if (myStmt != null) try {
                    myStmt.close();
                } catch (SQLException ignore) {
                }
                if (connection != null) try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    /**
     * Adds a new category that the current user entered to the categories table in the database.
     * @param category The name of the category.
     * @param userId   id of the user
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public int addCategory(Category category, int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        if (userId == -1 || category == null) {
            throw new CostManagerException("There was a problem with the user");
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("INSERT into `categoriesList` (categoryName, userId) values (?, ?)");
            myStmt.setString(1, category.getCategory());
            myStmt.setInt(2, userId);
            return myStmt.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException("An error inserting the category! Please try again", e);
        } finally {
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

    /**
     * Adds new currency to the current user in the database.
     * @param currency The currency we want to add.
     * @param userId id of the user who wants to add a new currency.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public int addCurrency(Currency currency, int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        if (currency == null || userId == -1) {
            throw new CostManagerException("There was a problem with the user");
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("INSERT into `currencies` (name, value, userId) values (?, ?, ?)");
            myStmt.setString(1, currency.getName());
            myStmt.setDouble(2, currency.getValue());
            myStmt.setInt(3, userId);
            return myStmt.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException("DB access failed", e);
        } finally {
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

    /**
     * Adds a new person to usersDetail table in the database.
     *
     * @param person The data we want to INSERT into usersDetails table.
     * @return The number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     * @author Undescribed
     */
    @Override
    public int addPerson(Person person) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        if (person == null) {
            return 0;
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("INSERT into `usersDetails` (firstName ,lastName, gender,email,budget,currency,password) values (?,?,?,?,?,?,?)");
            myStmt.setString(1, person.getFirstName());
            myStmt.setString(2, person.getLastName());
            myStmt.setInt(3, person.getGender());
            myStmt.setString(4, person.getEmail());
            myStmt.setDouble(5, person.getBudget());
            myStmt.setString(6, person.getCurrency().getName());
            myStmt.setString(7, person.getPassword());
            return myStmt.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException("There was a problem with adding a person to the DB please try again later", e);
        } finally {
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

    /**
     * This function returns the items that belong to the current user.
     * @param personId id of the current user.
     * @return Array of Item that includes all the items of the current user.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public Item[] getItemsArrayListFromDBAccordingToPerson(int personId) throws CostManagerException {
        LinkedList<Item> allPersonItems = new LinkedList<>();
        PreparedStatement myStmt = null;
        Connection connection = null;
        if (personId == -1) { return allPersonItems.toArray(new Item[]{}); }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            // Because the text is too long to display in the document we used StringBuilder to concat the Strings of the sql statement.
            myStmt = connection.prepareStatement(new StringBuilder()
                    .append("SELECT costId,costName,price,currency,value,amount,dateOfPurchase,categoryName FROM `costList`")
                    .append(" INNER JOIN `categoriesList` on categoriesList.categoryId = costList.categoryId INNER JOIN `currencies` on costList.currency = currencies.Name WHERE costList.UserID = ?").toString());
            myStmt.setInt(1, personId);
            ResultSet resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String currencyName = resultSet.getString(4);
                double currencyValue = resultSet.getDouble(5);
                int amount = resultSet.getInt(6);
                Date date = resultSet.getDate(7);
                String category = resultSet.getString(8);
                Item item = new Item(id, name, price, new Currency(currencyName, currencyValue), new Category(category), amount, date);
                allPersonItems.add(item);
            }
            return allPersonItems.toArray(new Item[]{});
        } catch (SQLException e) {
            throw new CostManagerException("There is something wrong with fetching the items for the current user");
        } finally {
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

    /**
     * This function returns the person from usersDetails table in the database that the name and the password belongs to him.
     * @param firstName The name of the user.
     * @param password  The password of the user.
     * @return Objects type Person.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public Person getPerson(String firstName, String password) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        if (firstName.equals("") || password.equals("")) {
            return null;
        }
        try {
            connection = DriverManager.getConnection(protocol,
                    "Anzor", "0506534697");
            myStmt = connection.prepareStatement("SELECT * FROM `usersDetails` JOIN `currencies` ON(usersDetails.Currency = currencies.Name) WHERE firstName=? and password=?");
            myStmt.setString(1, firstName);
            myStmt.setString(2, password);
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String lastName = resultSet.getString(3);
                int gender = resultSet.getInt(4);
                String email = resultSet.getString(5);
                double budget = resultSet.getDouble(6);
                String currencyName = resultSet.getString(7);
                double currencyValue = resultSet.getDouble(10);
                return new Person(id, firstName, lastName, password, gender, budget, new Currency(currencyName, currencyValue), email);
            }
        } catch (SQLException e) {
            throw new CostManagerException("There was a problem with the user", e);
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
        return null;
    }

    /**
     * This function returns the category from the database by name.
     * @param categoryName The name of the current category.
     * @param userId The id of the current user.
     * @return Category or null.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public Category getCategory(String categoryName, int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        if (categoryName == null || categoryName.equals("") || userId == 0) {
            return null;
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("SELECT * FROM `categorieslist` WHERE CategoryName=? AND UserID=?;");
            myStmt.setString(1, categoryName);
            myStmt.setInt(2, userId);
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString(2);
                return new Category(categoryName);
            }
        } catch (SQLException e) {
            throw new CostManagerException("There was a problem with the user", e);
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
        return null;
    }

    /**
     * This function returns all the categories of the current user from the database.
     * @param userId The id of the current user.
     * @return List of categories.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public List<Category> getCategories(int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Category> categoryNames = new ArrayList<>();
        if (userId == 0) {
            throw new CostManagerException("There was a problem with the user");
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("SELECT * FROM `categoriesList` WHERE userId =? OR userId = ?;");
            myStmt.setInt(1, userId);
            myStmt.setInt(2, -1);
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                categoryNames.add(new Category(name));
            }
        } catch (SQLException e) {
            throw new CostManagerException("Failed to get the categories");
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
        return categoryNames;
    }

    /**
     * This function returns the currency from the database by name.
     * @param currencyName The name of the current currency.
     * @param userId The id of the current user.
     * @return Currency or null.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public Currency getCurrency(String currencyName, int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        if (currencyName == null || currencyName.equals("") || userId == 0) {
            return null;
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("SELECT * FROM `currencies` WHERE Name=? AND UserID=?;");
            myStmt.setString(1, currencyName);
            myStmt.setInt(2, userId);
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                String currency = resultSet.getString(1);
                double currencyValue = resultSet.getDouble(2);
                return new Currency(currency,currencyValue);
            }
        } catch (SQLException e) {
            throw new CostManagerException("There was a problem with the user", e);
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
        return null;
    }

    /**
     * This function returns the currencies of the current user from the database.
     * @param userId Contains the id of the current user whose currencies we need to return.
     * @return List of currency.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public List<Currency> getCurrencies(int userId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Currency> currencyList = new ArrayList<>();
        if (userId == 0) {
            throw new CostManagerException("There was a problem with the user");
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("SELECT name, value FROM `currencies` WHERE userId = ? OR userId = ?;");
            myStmt.setInt(1, userId);
            myStmt.setInt(2, -1);
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                double value = resultSet.getDouble(2);
                currencyList.add(new Currency(name, value));
            }
        } catch (SQLException e) {
            throw new CostManagerException("Failed to get the currencies");
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
        return currencyList;
    }

    /**
     * This function returns all the data between 2 given dates for the current user.
     * @param personId  The id of the user.
     * @param firstDate The minimum date the items needs.
     * @param lastDate  The maximum date that we don't want the items to go above it.
     * @return Return list of items that include all the items of the current user and that are between the firsDate and lastDate.
     * @throws CostManagerException an exception thrown from the DB.
     */
    @Override
    public List<Item> getItemsByDates(int personId, LocalDate firstDate, LocalDate lastDate) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Item> selectedDatesData = new ArrayList<>();
        if (personId <= 0 || firstDate == null || lastDate == null) {
            throw new CostManagerException("There was a problem with the values");
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement(new StringBuilder()
                    .append("SELECT costId,costName,price,currency,value,amount,dateOfPurchase,categoryName FROM")
                    .append(" `costList` INNER JOIN `categoriesList` ON categoriesList.categoryId =costList.categoryId INNER JOIN `currencies` on costList.currency = currencies.name WHERE")
                    .append(" costList.userId = ? AND dateOfPurchase BETWEEN ? AND ?;").toString());
            myStmt.setInt(1, personId);
            myStmt.setDate(2, java.sql.Date.valueOf(firstDate));
            myStmt.setDate(3, java.sql.Date.valueOf(lastDate));
            resultSet = myStmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String currencyName = resultSet.getString(4);
                double currencyValue = resultSet.getDouble(5);
                int amount = resultSet.getInt(6);
                Date date = resultSet.getDate(7);
                String category = resultSet.getString(8);
                Item item = new Item(id, name, price, new Currency(currencyName, currencyValue), new Category(category), amount, date);
                selectedDatesData.add(item);
            }
            return selectedDatesData;
        } catch (SQLException e) {
            System.out.println("Unable to connect to the server");
            throw new CostManagerException("Failed to locate the data for the specific dates", e);
        } finally {
            if (resultSet != null) { try { resultSet.close();} catch (SQLException ignore) {} }
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

    /**
     * Deletes an item from the database.
     *
     * @param itemId id of the item we wish to delete from the database.
     * @return Returns the number of rows affected by the SQL statement.
     * @throws CostManagerException an exception thrown from the DB.
     */
    public int deleteItem(int itemId) throws CostManagerException {
        PreparedStatement myStmt = null;
        Connection connection = null;
        if (itemId == -1) {
            return 0;
        }
        try {
            connection = DriverManager.getConnection(protocol, "Anzor", "0506534697");
            myStmt = connection.prepareStatement("DELETE from `costList` where costId= ?");
            myStmt.setInt(1, itemId);
            return myStmt.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException("There was an error while trying to delete the item please try again", e);
        } finally {
            if (myStmt != null) { try { myStmt.close();} catch (SQLException ignore) {} }
            if (connection != null) { try { connection.close(); } catch (SQLException ignore) {} }
        }
    }

}
