package il.ac.hit.costmanager.viewmodels;

import il.ac.hit.costmanager.views.*;
import il.ac.hit.costmanager.models.CostManagerException;
import il.ac.hit.costmanager.models.IModel;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 * This class mediates between view and model classes (CostManagerView and CostManagerModel) and describes CostManagerViewModel
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class CostManagerViewModel implements IViewModel {
    private IModel model;
    private IView view;
    private final ExecutorService service;

    /**
     * Constructor of SimpleViewModel.
     */
    public CostManagerViewModel() {
        this.service = Executors.newFixedThreadPool(3);
    }
    /**
     * Sets the view of this view field.
     * @param view Contains the data to insert into view.
     */
    @Override
    public void setView(IView view) {
        if(view != null){
            this.view = view;
        }
    }
    /**
     * Sets the model of this model field.
     * @param model Contains the data to insert into model.
     */
    @Override
    public void setModel(IModel model) {
        if (model != null){
            this.model = model;
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add an item that the user
     * inserted from the CostManagerView to the database with the help of the CostManagerModel.
     * @param item Contains data of the item the user wants to add.
     * @param personId The id of the user to which we need to refer the item to.
     * @param transaction The amount we need to subtract from the user's budget.
     */
    @Override
    public void addItem(Item item, int personId, double transaction) {
        if(item == null || personId == 0 || transaction < 0) {
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else {
            service.submit(() -> {
                try {
                    // Activates the addItem method of IModel.
                    model.addItem(item, personId, transaction);
                    Item []items = model.getItemsArrayListFromDBAccordingToPerson(personId);
                    SwingUtilities.invokeLater(() -> {
                        // Activates a method to display a pop-up message to the user.
                        view.showMessage(new Message("Item was added"));
                        view.showItems(items);
                    });
                } catch(CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message(e.getMessage())));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add a new person into the database.
     * @param person Contains the data of the new user that will be added to the database.
     */
    @Override
    public void addPerson(Person person) {
        if(person == null){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else{
            service.submit(() -> {
                try {
                    model.addPerson(person);
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("you register successfully! please login to enjoy our features")));
                } catch(CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message(e.getMessage())));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and  CostManagerModel in order to add a new category that the user has entered.
     * @param category Contains the data of the new category we wish to insert into the database.
     * @param userId The id of the user.
     */
    @Override
    public void addCategory(Category category, int userId) {
        if (category == null || category.getCategory() == null || userId == 0)
        {
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Category name can't be empty, please try again.")));
            return;
        }
        String categoryName = category.getCategory();
        service.submit(() -> {
            try {
                String message = "Category already exists";// Message that will be displayed in the end.
                Category currentCategory = model.getCategory(categoryName,userId);
                if(currentCategory == null){
                    message = "Category " +categoryName+ " was added";
                    model.addCategory(category, userId);
                }
                final String  messageToDisplay = message;// Because we have invoked later we need to give him a final value.
                List<Category> categoryList = model.getCategories(userId);
                SwingUtilities.invokeLater(() -> {
                    view.showMessage(new Message(messageToDisplay));
                    view.showCategories(categoryList);
                });
            } catch(CostManagerException e) {
                SwingUtilities.invokeLater(() -> view.showMessage(new Message(e.getMessage())));
            }
        });
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to add a new currency that the current user has entered.
     * @param currency The currency the user wants to add.
     * @param userId id of the current user.
     */
    @Override
    public void addCurrency(Currency currency, int userId) {
        if (currency == null || currency.getName()== null || currency.getValue() == 0 || userId == 0){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("There is something wrong with the data \nPlease try again.")));
        }
        else {
            String currencyName = currency.getName().toLowerCase();
            service.submit(() -> {
                try {
                    // Message to display at the end.
                    String message = "Currency already exists";
                    Currency currentCurrency = model.getCurrency(currencyName,userId);
                    // If the currency isn't in the database then we can add it.
                    if(currentCurrency == null){
                        message = new StringBuilder()
                                .append("Currency ").append(currencyName)
                                .append(" was added").toString();
                        model.addCurrency(currency, userId);// Add the new currency to the database.
                    }
                    final String messageToDisplay = message;
                    List<Currency> currencyList = model.getCurrencies(userId);
                    SwingUtilities.invokeLater(() -> {
                        view.showMessage(new Message(messageToDisplay));// Present the message to the user.
                        view.showCurrencies(currencyList);// Update the currency list.
                    });
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message(e.getMessage())));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to present the user the items he has in the database.
     * @param personId Contains the data of the user's ID so that we know which items we need to display and which we don't.
     */
    @Override
    public void getItemsForPerson(int personId) {
        if(personId == 0)
        {
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else
        {
            service.submit(() -> {
                try {
                    // Array of items that belong to the current user.
                    Item[] items = model.getItemsArrayListFromDBAccordingToPerson(personId);
                    SwingUtilities.invokeLater(() -> view.showItems(items));
                } catch(CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("Problem with fetching the items from the model")));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to presents the user the categories that
     * he has inside the database.
     * @param  userId The id of the user.
     */
    @Override
    public void getCategories(int userId) {
        if (userId == 0){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }else {
            service.submit(() -> {
                try {
                    List<Category> categoryList = model.getCategories(userId);// List of the categories of the current user.
                    SwingUtilities.invokeLater(() -> view.showCategories(categoryList));
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("problem with fetching the categories from the model")));
                }
            });
        }
    }
    /**
     * This method mediates between the CostManagerView and CostManagerModel in order presents the user the items that are between
     * the two dates that he chose (beginDate and endDate).
     * @param personId Contains the ID of the user that we need to present the relevant data to.
     * @param beginDate The minimum date the items need to have or above.
     * @param endDate The maximum date the items can have or below it.
     */
    @Override
    public void getDataByDates(int personId, LocalDate beginDate, LocalDate endDate) {
        if (personId == 0 || beginDate == null || endDate == null){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else {
            service.submit(() -> {
                try {
                    // List of all the items between beginDate and endDate.
                    List<Item> tableItemByDateList = model.getItemsByDates(personId, beginDate, endDate);
                    SwingUtilities.invokeLater(() -> view.showItemsByDate(tableItemByDateList));
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("Problem with fetching the items between the dates from the model ")));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to get the currencies of the current user.
     * @param userId id of the current user.
     */
    @Override
    public void getCurrencies(int userId) {
        if (userId == 0){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }else{
            service.submit(() -> {
                try {
                    // List of all the currencies that belong to the user.
                    List<Currency> currencyList = model.getCurrencies(userId);
                    SwingUtilities.invokeLater(() -> view.showCurrencies(currencyList));
                } catch(CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("Problem with fetching the currencies from the model")));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to check if the username and password that were entered
     * indeed exist in the database.
     * @param firstName Contains the username of the user that should already exist in the database.
     * @param password Contains the password of the user that should already exist in the database.
     */
    @Override
    public void checkUserExistence(String firstName, String password) {
        if(firstName.equals("")||password.equals("")){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("user name and password cant be empty!\nPlease try again.")));
        }
        else {
            service.submit(() -> {
                try {
                    Person person = model.getPerson(firstName, password);
                    SwingUtilities.invokeLater(() -> {
                        if (person == null) {
                            view.showMessage(new Message("There is something wrong with the name or the " +
                                    "password!\n please try again"));
                        } else {
                            view.showPerson(person);
                        }
                    });
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message(new StringBuilder()
                            .append("There is something wrong with the name or the ")
                            .append("password!\n please try again").toString())));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to check if the current user can make the
     * transaction or not.
     * @param person Details of the user who wants to create the transaction.
     * @param itemCurrency Currency of the item.
     * @param itemPrice Price of the item.
     * @param item Details of the item.
     */
    @Override
    public void checkTransaction(Person person, Currency itemCurrency, double itemPrice, Item item) {
        if (person == null || itemCurrency == null || itemPrice<0 || item == null){
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else {
            // Currency of the user.
            String userCurrency = person.getCurrency().getName();
            // The wallet of the user.
            double userBudget = person.getBudget();
            // id of the user.
            int id = person.getId();
            service.submit(() -> {
                try {
                    List<Currency> currencyList = model.getCurrencies(id);
                    double finalPrice = 1;// Currency of the item.
                    double finalBudgetCurrency = 1;// Currency of the user.
                    // Here if the currency is not NIS then we run with for to check which currency is the currency of the transaction.
                    if (!itemCurrency.getName().equals("NIS") || !userCurrency.equals("NIS")) {
                        for (Currency c : currencyList) {
                            if (c.getName().equals(userCurrency)) {
                                finalBudgetCurrency = c.getValue();
                            }
                            if (c.getName().equals(itemCurrency.getName())) {
                                finalPrice = c.getValue();
                            }
                        }
                    }
                    // Final price of the transaction that will be deducted from the wallet of the user.
                    final double transactionPrice = finalPrice * itemPrice / finalBudgetCurrency;
                    if (transactionPrice <= userBudget) {
                        SwingUtilities.invokeLater(() -> view.createTransaction(item, transactionPrice));
                    } else {
                        SwingUtilities.invokeLater(() -> view.showMessage(new Message("Transaction incomplete, you don't have enough money")));
                    }
                } catch (CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("Transaction incomplete,\n please try again")));
                }
            });
        }
    }
    /**
     * Mediates between CostManagerView and CostManagerModel in order to delete an item the user chose in the
     * CostManagerView and delete it from the database using the CostManagerModel.
     * @param itemId Contains the id of the item the user wishes to delete.
     * @param personId Contains the data of the user's id who wants to delete the item.
     */
    @Override
    public void deleteItem(int itemId,int personId) {
        if(itemId <= 0 || personId == 0)
        {
            SwingUtilities.invokeLater(() -> view.showMessage(new Message("Data error, please try again.")));
        }
        else
        {
            service.submit(() -> {
                try {
                    model.deleteItem(itemId);// Deletes an item according to the id of the item.
                    SwingUtilities.invokeLater(() -> {
                        view.showMessage(new Message("The item was deleted"));
                    });
                } catch(CostManagerException e) {
                    SwingUtilities.invokeLater(() -> view.showMessage(new Message("Problem with deleting the item")));
                }
            });
        }
    }

}
