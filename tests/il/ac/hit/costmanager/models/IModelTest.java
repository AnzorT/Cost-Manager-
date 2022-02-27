package il.ac.hit.costmanager.models;

import il.ac.hit.costmanager.views.Category;
import il.ac.hit.costmanager.views.Currency;
import il.ac.hit.costmanager.views.Item;
import il.ac.hit.costmanager.views.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * This class describes IModelTest.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IModelTest {

    // Variable that will allow us to use the methods from the CostManagerModel.
    private CostManagerModel costManagerTest;
    {
        try {
            costManagerTest = new CostManagerModel();
        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
    private Person personToAdd;// Person variable for the tests.
    private Item item;// Item variable for the tests.
    private Currency currency;// Currency variable for the tests.
    private Category category;// Category variable for the tests.
    /**
     * Prepare the variables for the tests.
     * 
     */
    @Before
    public void setUp()  {
        // Creating new Calendar variable in order to describe the date of purchase of the item.
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date date = calendar.getTime();

        // Creating new Person variable.
        personToAdd = new Person(0,"Test","Test","1",1,1472082.6,new Currency("USD",3.14),"test@gmail.com");
        // Creating new Category variable.
        category = new Category("Gaming");
        // Creating new Currency variable.
        currency = new Currency("EUR",3.57);
        // Creating new Item variable.
        item = new Item("Grand Theft Auto V",230.0, currency, category, 2,date);

    }
    /**
     * Checks the method of add person that it actually adds the user to the database.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void aAddPerson() throws CostManagerException {
        // Checks if the method costManagerTest.addPerson() was successful in adding a new person or not.
        assertEquals(1,costManagerTest.addPerson(personToAdd));
    }
    /**
     * This method checks that the addCategory method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void bAddCategory() throws CostManagerException {
        // Checks if the method costManagerTest.addCategory() was successful in adding a new category or not.
        assertEquals(1,costManagerTest.addCategory(category,costManagerTest.getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId()));
    }
    /**
     * This method checks that the addCurrency method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void cAddCurrency() throws CostManagerException {
        // Checks if the method costManagerTest.addCurrency() was successful in adding new currency or not.
        assertEquals(1,costManagerTest.addCurrency(currency,costManagerTest.getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId()));
    }
    /**
     * This method checks that the addPerson method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void dGetPerson() throws CostManagerException {
        // Checks if the method costManagerTest.getPerson() was successful in return the details of the user or not.
        assertEquals(personToAdd.toString(),costManagerTest.getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).toString());
    }
    /**
     * This method checks that the getCategories method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void eGetCategories() throws CostManagerException {
        // Checks if the method costManagerTest.getCategories() was successful in getting the categories of the user or not.
        assertEquals(category.getCategory(),costManagerTest.getCategories(costManagerTest.
                getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId()).get(0).getCategory());
    }
    /**
     * This method checks that the getCurrencies method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void fGetCurrencies() throws CostManagerException {
        // Creating new List of Currencies to get list of currencies from costManagerTest.getCurrencies method.
        List <Currency> result = costManagerTest.getCurrencies(costManagerTest.getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId());
        // Checks of the method costManagerTest.getCurrencies() returned the currencies that belong to the current user or not.
        assertEquals(currency.getName(),result.get(result.size()-1).getName());
    }
    /**
     * This method checks that the addItem method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void gAddItem() throws CostManagerException {
        // Checks of the method costManagerTest.addItem() was successful in adding a new item or not.
         assertEquals(1,costManagerTest.addItem(item, costManagerTest
                 .getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId(),
                 item.getAmount()*item.getPrice()*currency.getValue()));
    }
    /**
     * This method checks that the getItemsArrayListFromDBAccordingToPerson method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void hGetItemsArrayListFromDBAccordingToPerson() throws CostManagerException {
        // Checks of the method costManagerTest.getItemsArrayListFromDBAccordingToPerson() was successful.
        //  in returning the items of user or not.
        assertEquals(item.getName(),costManagerTest.getItemsArrayListFromDBAccordingToPerson(costManagerTest
                .getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId())[0].getName());
    }
    /**
     * This method checks that the getDates method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void iGetDates() throws CostManagerException {
        //  Creating new Calendar variables start and end which will contain the minimum and maximum dates.
        // of the items we wish to present.
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2020, Calendar.JANUARY, 1);
        end.set(2030, Calendar.JANUARY, 1);

        // Creating new List of Items which receives items between start and end date from costManagerTest.getDates() method
        List <Item> result = costManagerTest.getItemsByDates(costManagerTest.getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId(),
                start.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                end.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        // Checks if the costManagerTest.getDates() method returned the correct items of the user or not
        assertEquals(item.getName(),result.get(1).getName());
    }
    /**
     * This method checks that the deleteItem method of CostManagerModel works correctly.
     * 
     * @throws CostManagerException Class that throws exceptions if the method was unsuccessful.
     */
    @Test
    public void jDeleteItem() throws CostManagerException {
        // Checks of the costManagerTest.deleteItem() was successfully in deleting an item or not.
        assertEquals(1,costManagerTest.deleteItem(costManagerTest
                .getItemsArrayListFromDBAccordingToPerson(costManagerTest
                        .getPerson(personToAdd.getFirstName(),personToAdd.getPassword()).getId())[0].getId()));
    }
    /**
     * Return to the previous mark.
     * 
     */
    @After
    public void tearDown() {

    }
}


