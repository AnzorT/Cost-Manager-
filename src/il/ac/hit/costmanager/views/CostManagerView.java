package il.ac.hit.costmanager.views;

import com.toedter.calendar.JDateChooser;
import il.ac.hit.costmanager.viewmodels.IViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

/**
 * This class describes CostManagerView.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class CostManagerView implements IView
{
    private boolean isLogin;// Checks of the user is logged in or not.
    private Person userDetails = null;// Contains the details of the user who logged in.
    private JFrame frame;// The frame in which we display the user the pages and the program.
    private JLabel loginAndOutJLabel;// Displays the message of hello to the user.
    // Buttons of show expenses, show all the categories, show report by dates, show all the currencies.
    private JButton showExpensesJBtn, showCategoryListJBtn, showDetailedReportsJBtn, showCurrencyListJBtn;
    private IViewModel vm;
    // Panels that help us construct a much more beautiful user interface.
    private JPanel framePanelOverAll, homePagePanelOverAll, panelNorth, panelCenter;
    private CardLayout cardLayout;
    private LoginPage loginPage;// The login page.
    private NavPage navPage;// The menu page, where you can choose to which page you want to go.
    private CostListPage costListPage;// Page that shows the user his expenses.
    private RegisterPage registerPage;// Registration page.
    private CategoryPage categoryPage;// Page that shows the user all his categories.
    private AddItemPage addItemsPage;// Page where the user can add the item to his page.
    private AddCategoryPage addCategoryPage;// Page that the user can add new category to his database.
    private DateReports dateReports;// Page where user can see his expenses between 2 dates that he chooses.
    private AddCurrencyPage addCurrencyPage;// Page where the user adds a new currency.
    private CurrencyPage currencyPage;// Page where the user can see all his currencies.
    private String activePageName;// Variable that tells us in which page the user currently is.
    /**
     * The constructor of ConstManagerView.
     */
    public void init() {
        // Changing the font of the UI texture.
        setFont();
        // The main frame.
        frame = new JFrame("Home Page");
        // The main layout.
        cardLayout = new CardLayout();

        // The main frame components
        loginAndOutJLabel = new JLabel("Hello stranger, please login :)");// Default value for the first time.
        showExpensesJBtn = new JButton("Show Expenses");// Default value for the first time.
        showCategoryListJBtn = new JButton("Show Category List");// Default value for the first time.
        showDetailedReportsJBtn = new JButton("Show Detail Reports");// Default value for the first time.
        showCurrencyListJBtn = new JButton("Show Currency List");// Default value for the first time.

        // The main panels.
        panelNorth = new JPanel();// Creating new JPanel.
        panelCenter = new JPanel();// Creating new JPanel.
        homePagePanelOverAll = new JPanel();// Creating new JPanel.
        framePanelOverAll = new JPanel();// Creating new JPanel.

        // Creating new pages.
        loginPage = new LoginPage();// Creating new LoginPage.
        navPage = new NavPage();// Creating new NavPage.
        costListPage = new CostListPage();// Creating new CostListPage.
        registerPage = new RegisterPage();// Creating new RegistrationPage.
        categoryPage = new CategoryPage();// Creating new CategoryPage.
        addItemsPage = new AddItemPage();// Creating new AddItemPage.
        dateReports = new DateReports();// Creating DateReports.
        addCategoryPage = new AddCategoryPage();// Creating new AddCategoryPage.
        addCurrencyPage = new AddCurrencyPage();// Creating new AddCurrencyPage.
        currencyPage = new CurrencyPage();// Creating new CurrencyPage.
        activePageName = "CostManagerView";// Setting the current page to CostManagerView.
        isLogin = false;// User is not login so isLogin must be false.
    }
    /**
     * This method prepares CostManagerView page for display.
     */
    public void start() {

        // panelNorth settings, construction.
        panelNorth.setBackground(Color.GREEN);// Setting the color of the panel.
        panelNorth.setLayout(new FlowLayout());// Setting the layout of the panel.
        panelNorth.add(loginAndOutJLabel);// Adding variable to the panel.

        // panelCenter settings construction.
        panelCenter.setBackground(Color.YELLOW);// Setting the color of the panel.
        panelCenter.setLayout(new FlowLayout());// Setting the layout of the panel.
        panelCenter.add(showExpensesJBtn);// Adding variable to the panel.
        panelCenter.add(showCategoryListJBtn);// Adding variable to the panel.
        panelCenter.add(showDetailedReportsJBtn);// Adding variable to the panel.
        panelCenter.add(showCurrencyListJBtn);// Adding variable to the panel.

        // homePagePanelOverAll settings construction.
        homePagePanelOverAll.setBackground(Color.GREEN);// Setting the color of the panel.
        homePagePanelOverAll.setLayout(new BorderLayout());// Setting the layout of the panel.
        homePagePanelOverAll.add(panelNorth, BorderLayout.NORTH);// Adding variable to the panel.
        homePagePanelOverAll.add(panelCenter, BorderLayout.CENTER);// Adding variable to the panel.

        // Activating the constructor (init) of each page.
        loginPage.init();
        navPage.init();
        costListPage.init();
        registerPage.init();
        categoryPage.init();
        addItemsPage.init();
        addCategoryPage.init();
        dateReports.init();
        addCurrencyPage.init();
        currencyPage.init();

        // framePanelOverAll setting, construction.
        // Each page we add to the framePanel, because we chose cardLayout  we can give each page a number and with that.
        // We can tell the panel to switch to any page we want.
        framePanelOverAll.setLayout(cardLayout);
        framePanelOverAll.add(homePagePanelOverAll, "1");
        framePanelOverAll.add(loginPage.loginPagePanelOverAll, "2");
        framePanelOverAll.add(navPage.navPagePanelOverAll, "3");
        framePanelOverAll.add(costListPage.costListPageOverAll, "4");
        framePanelOverAll.add(registerPage.registerPagePanelOverAll, "5");
        framePanelOverAll.add(categoryPage.categoryPagePanelOverAll, "6");
        framePanelOverAll.add(addItemsPage.addItemPageOverAllPanel, "7");
        framePanelOverAll.add(addCategoryPage.addCategoryPagePanelOverAll, "8");
        framePanelOverAll.add(dateReports.dateReportsPanelOverAll, "9");
        framePanelOverAll.add(addCurrencyPage.addCurrencyPagePanelOverAll, "10");
        framePanelOverAll.add(currencyPage.currencyPagePanelOverAll,"11");

        // settings, construction of frame.
        frame.setLayout(new BorderLayout());
        frame.add(framePanelOverAll);
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Checks when we close the program.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (confirmed == JOptionPane.NO_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // This actionListener directs us to login page if we are not logged in, else to the expenses page.
        showExpensesJBtn.addActionListener(e -> {
            if (isLogin){
                changeView("Expenses Page");
            }
            else {
                changeView("Login Page");
            }

        });
        // This actionListener directs us to login page if we are not logged in, else to the category page.
        showCategoryListJBtn.addActionListener(e -> {
            if (isLogin){
                changeView("Categories Page");
            }
            else {
                changeView("Login Page");
            }
        });
        // This actionListener directs us to login page if we are not logged in, else to the detail report page.
        showDetailedReportsJBtn.addActionListener(e -> {
            if (isLogin){
                changeView("Expenses By Dates Page");
            }
            else {
                changeView("Login Page");
            }
        });
        // This actionListener directs us to login page if we are not logged in, else to the currency page.
        showCurrencyListJBtn.addActionListener(e -> {
            if (isLogin){
                changeView("Currencies Page");
            }
            else {
                changeView("Login Page");
            }
        });
    }

    /**
     * This class defines LoginPage page.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class LoginPage {
        private boolean isFirst = false;// Checks if we already visited this page or not.
        private JLabel userName_text;// Will contain text "username:".
        private JLabel password_text;// Will contain text "password:".
        // register button takes us to registerPage page, login will check if the user exists.
        private JButton register, login;
        private JTextField userNameD;// Text that will contain the name the user enters.
        private JPasswordField passwordD;// Text that will contain the password the user enters.
        // Panels that help us construct a much more beautiful user interface.
        private JPanel loginPagePanelOverAll, panelNorth, panelCenter,panelSouth;

        /**
         * LoginPage's constructor.
         */
        public void init() {
            // Creating new jLabels and giving it default text.
            userName_text = new JLabel("userName: ");
            password_text = new JLabel("password: ");
            // Setting the size of the JTextField and JPasswordField.
            userNameD = new JTextField(10);
            passwordD = new JPasswordField(10);
            // Creating new JPanel and JButton and giving them text.
            login = new JButton("Login");
            register = new JButton("not a user? Register here");
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            panelSouth = new JPanel();
            loginPagePanelOverAll = new JPanel();
        }
        /**
         * This method prepares LoginPage page for display.
         */
        public void start() {

            isFirst = true;// We are visiting the page for the 1st time, and it needs to be true.
            // panelNorth settings,construction.
            panelNorth.setBackground(Color.GREEN);// Setting the color of the panel.
            panelNorth.setLayout(new FlowLayout());// Setting layout of the panel.
            panelNorth.add(loginAndOutJLabel);// Adding variable to the panel.
            // panelCenter settings,construction.
            panelCenter.setBackground(Color.YELLOW);// Setting the color of the panel.
            panelCenter.setLayout(new FlowLayout());// Setting the layout of the panel.
            panelCenter.add(userName_text);// Adding variable to the panel.
            panelCenter.add(userNameD);// Adding variable to the panel.
            panelCenter.add(password_text);// Adding variable to the panel.
            panelCenter.add(passwordD);// Adding variable to the panel.
            // panelSouth settings,construction.
            panelSouth.setLayout(new FlowLayout());// Setting the color of the panel.
            panelSouth.add(login);// Setting the layout of the panel.
            panelSouth.add(register);// Adding variable to the panel.
            // loginPagePanelOverAll settings,construction.
            loginPagePanelOverAll.setLayout(new BorderLayout());// Setting layout of the panel.
            loginPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);// Adding variable to the panel.
            loginPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);// Adding variable to the panel.
            loginPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);// Adding variable to the panel.
            // actionListener of login, checks if we can log in or not with the username and password the user inputted.
            login.addActionListener(e -> vm.checkUserExistence(userNameD.getText(), String.valueOf(passwordD.getPassword())));
            // actionListener of registration which takes us to registration page.
            register.addActionListener(e -> changeView("Registration Page"));
        }
        /**
         * This method activates the start method of LoginPage for first time and resets values of userNameD and passwordD every time.
         */
        public void update(){
            if (!isFirst) {
                start();
            }
            loginAndOutJLabel.setText("Hello stranger, please login :)");
            panelNorth.add(loginAndOutJLabel);// Adding variable to the panel.
            userNameD.setText("");
            passwordD.setText("");
        }
        /**
         * Assigned to a Person object the data of the user that logged successfully into the system, and changing the frame accordingly.
         * @param person Contains the data of the user that logged in.
         */
        public void showPerson(Person person) {
            if (person != null) {
                userDetails = person;
                isLogin = true;// True because user is logged in.
                changeView("Home Page");// Redirect to NavPage.
            }
        }
    }
    /**
     * This class describes NavPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class NavPage {
        private boolean isFirst = false;// Indicates if we already visited this page or not.
        private JButton logOutJBtn;// logout button.
        // Panels that help us construct a much more beautiful user interface.
        private JPanel navPagePanelOverAll, panelNorth, panelCenter;
        private JLabel userName;// Hello "username" text that we will display.

        /**
         * NavPage's constructor.
         */
        public void init() {
            userName = new JLabel();// Creating new JLabel.
            logOutJBtn = new JButton("LOGOUT");// Creating new JButton and setting its text.
            panelNorth = new JPanel();// Creating new JPanel.
            panelCenter = new JPanel();// Creating new JPanel.
            navPagePanelOverAll = new JPanel();// Creating new JPanel.
        }

        /**
         * This method prepares NavPage page for display.
         */
        public void start() {
            isFirst = true;// Indicates if we already visited this page or not.

            // panelNorth settings, construction.
            panelNorth.setBackground(Color.GREEN);// Setting the color of the panel.
            panelNorth.setLayout(new FlowLayout());// Setting the layout of the panel.
            panelNorth.add(userName);// Adding variable to the panel.

            // panelCenter settings, construction.
            panelCenter.setBackground(Color.YELLOW);// Setting the color of the panel.
            panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));// Setting the layout of the panel.
            panelCenter.add(showExpensesJBtn);// Adding variable to the panel.
            panelCenter.add(showCategoryListJBtn);// Adding variable to the panel.
            panelCenter.add(showDetailedReportsJBtn);// Adding variable to the panel.
            panelCenter.add(logOutJBtn);// Adding variable to the panel.
            panelCenter.add(showCurrencyListJBtn);// Adding variable to the panel.

            // navPagePanelOverAll settings, construction.
            navPagePanelOverAll.setLayout(new BorderLayout());// Setting the layout of the panel.
            navPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);// Adding variable to the panel.
            navPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);// Adding variable to the panel.

            // actionListener that resents values when user is logging out and redirect to loginPage.
            logOutJBtn.addActionListener(e -> {
                userDetails = null;
                isLogin = false;
                changeView("Login Page");
            });
        }

        /**
         * This method activates start() of NavPage for the first time and resents userName's text to Hello nameOfUser.
         */
        public void update(){
            if(!isFirst){
                start();
            }
            userName.setText("Hello "+ userDetails.toString());
        }
    }
    /**
     * This class describes CostListPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class CostListPage {
        private boolean isFirst = false;// Indicates if we already visited this page or not.
        private boolean isUpdate;// Prevents duplication of the items display in the table.
        private JTable table;// Table where we will display the list of items.
        private DefaultTableModel itemTable;// Will contain all the items of the user that returns from the database.
        // Activates add item method, activates delete item method, redirects us to NavPage.
        private JButton JButtonAddItem, JButtonDeleteSpecificItem, JButtonBackToHomePage;
        private JScrollPane tableJSP;
        private JLabel  userName, jLCurrency, jLBudget;
        private JPanel costListPageOverAll, panelSouth, panelNorth, panelCenter;
        /**
         * CostListPage's constructor.
         */
        public void init() {
            table= new JTable();// Creates new JTable.
            JButtonAddItem = new JButton("add item");// Creates new JButton and adds text.
            JButtonDeleteSpecificItem =new JButton("Delete Item");// Creates new JButton and adds text.
            JButtonBackToHomePage =new JButton("Home Page");// Creates new JButton and adds text.
            userName = new JLabel();// Creates new JLabel.
            jLBudget = new JLabel();// Creates new JLabel.
            jLCurrency = new JLabel();// Creates new JLabel.
            itemTable = new DefaultTableModel();// Creates new DefaultTableModel.
            panelSouth = new JPanel();// Creates new JPanel.
            panelNorth = new JPanel();// Creates new JPanel.
            panelCenter = new JPanel();// Creates new JPanel.
            tableJSP = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// Creates new JScrollPane.
            costListPageOverAll = new JPanel();// Creates new JPanel.
            table.setFillsViewportHeight(true);
        }
        /**
         * This method prepares CostListPage page for display.
         */
        public void start() {
            isFirst = true;// indicates if we visited the page already or not, true because we are right now.

            // panelNorth settings, construction
            panelNorth.setBackground(Color.GREEN);// Sets the color of the panel.
            panelNorth.setLayout(new FlowLayout());// Sets the layout of the panel.
            panelNorth.add(userName);// Adds variables to the panel.

            // panelSouth settings, construction.
            panelSouth.setBackground(Color.YELLOW);// Sets the color of the panel.
            panelSouth.setLayout(new FlowLayout());// Sets the layout of the panel.
            panelSouth.add(JButtonAddItem);// Adds variables to the panel.
            panelSouth.add(JButtonBackToHomePage);// Adds variables to the panel.
            panelSouth.add(JButtonDeleteSpecificItem);// Adds variables to the panel.
            panelSouth.add(jLBudget);// Adds variables to the panel.
            panelSouth.add(jLCurrency);// Adds variables to the panel.

            // panelCenter settings, construction.
            panelCenter.setLayout(new GridLayout(1,1));// Sets the layout of the panel.
            panelCenter.add(tableJSP);// Adds a variable to the panel.

            // costListPageOverAll settings, construction.
            costListPageOverAll.setLayout(new BorderLayout());// Sets the layout of the panel.
            costListPageOverAll.add(panelNorth, BorderLayout.NORTH);// Adds a variable to the panel.
            costListPageOverAll.add(panelCenter, BorderLayout.CENTER);// Adds a variable to the panel.
            costListPageOverAll.add(panelSouth, BorderLayout.SOUTH);// Adds a variable to the panel.

            // Redirects us to the addItemPage, before that we use "deleteTableItems()" to clean the itemTable from items.
            JButtonAddItem.addActionListener(e -> {
                // We clear the itemTable before redirecting to another page.
                deleteTableItems();
                changeView("Add Item Page");
            });
            // Delete an item the user chose and removes it from the itemTable.
            JButtonDeleteSpecificItem.addActionListener(e -> {
               if (table.getSelectedRowCount() == 1) {
                   int selectedRow= table.getSelectedRow();
                   vm.deleteItem(Integer.parseInt(itemTable.getValueAt(selectedRow,0).toString()), userDetails.getId());
                   itemTable.removeRow(selectedRow);
                   itemTable.fireTableDataChanged();
               }
               else {
                   showMessage(new Message("you need to select only one row"));
               }
            });
            // Redirects us back to NavPage, before that we use "deleteTableItems()" to clean the itemTable from items.
            JButtonBackToHomePage.addActionListener(e -> {
                deleteTableItems();
                changeView("Home Page");
            });
        }
        /**
         * This method cleans the itemTable.
         */
        public void deleteTableItems(){
            for (int i = itemTable.getRowCount() - 1; i >= 0;i--) {
                itemTable.removeRow(i);
            }
            itemTable.fireTableDataChanged();
        }
        /**
         * This method activates start() of CostListPage for the first time
         */
        public void update(){
            if (!isFirst) {
                start();
            }
            isUpdate = false;// 1st time false because we didn't yet show the user the table and there was no update.
            userName.setText("Hello "+ userDetails.toString());// Change the text "hello `name of user`".
            jLBudget.setText(" Wallet balance: " + userDetails.getBudget());// Present the wallet balance of the user.
            jLCurrency.setText(userDetails.getCurrency().getName());// Set the currency text.
            // Display the user's items.
            if (isLogin) {
                vm.getCategories(userDetails.getId());// Activates getCategories method of the current user's id.
                vm.getItemsForPerson(userDetails.getId());
            }
        }
        /**
         * This method puts the data of each item inside itemTable.
         * @param items Contains array of Items that contain the detail of each item.
         */
        public void showItems(Item[] items) {
            // Array of Object which will contain the data of a current item, and then we add it to itemTable.
            String[] columnsName = {"ID", "Name", "Price", "Currency", "Amount", "Date Of Purchase", "Category"};
            Object[] row = new Object[7];
            itemTable = new DefaultTableModel(null,columnsName);
            if (items.length != 0 && !isUpdate) {
                isUpdate = true;// We will now show the items, so isUpdate will be true in order to prevent duplicates.
                // This for adds into row the details of the current item and then inserts it into the itemTable.
                int size = items.length;
                for (int i = 0; i<size; i++){
                    Item item = items[i];
                    row[0]=item.getId();
                    row[1]=item.getName();
                    row[2]=item.getPrice();
                    row[3]=item.getCurrency().getName();
                    row[4]=item.getAmount();
                    row[5]=item.getDateOfPurchase();
                    row[6]=item.getCategory().getCategory();
                    // After we're done adding the details of the item we can insert it into itemTable.
                    itemTable.insertRow(i,row);
                }
            }
            // After we're done inserting all the items into the itemTable we can add them to the table.
            table.setModel(itemTable);// Empty the table.
        }
    }

    /**
     * This class describes RegisterPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class RegisterPage {
        private GridBagConstraints gbcData;
        // Will contain "first name:" text.
        private JLabel firstNameText;
        // Will contain "last name:" text.
        private JLabel lastNameText;
        // Will contain "password:" text.
        private JLabel passwordText;
        // Will contain "email:" text.
        private JLabel emailText;
        // Will contain "budget:" text.
        private JLabel budgetText;
        // Will contain "gender:" text.
        private JLabel genderText;
        // Will contain "currency:" text.
        private JLabel currencyText;
        // Will contain the currencies that the user's wallet will be in.
        private JComboBox<String> currencyD;
        // Register activates the register method, back button redirects us to loginPage.
        private JButton register,back;
        // TextFields that will contain the data that the user will insert.
        private JTextField firstNameD,lastNameD,passwordD,emailD,budgetD;
        // RadioButton to choose either male of female.
        private JRadioButton male,female;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel registerPagePanelOverAll, panelNorth, panelCenter,panelSouth;
        // Indicates if we already visited this page or not.
        private boolean isFirst = false;
        // The order of the data is : ID ,FirstName ,LastName, Gender, Email, Budget, PassWord.
        /**
         * RegistrationPage's constructor.
         */
        public void init() {
            // Creating new JLabel + text.
            firstNameText = new JLabel("First name: ");
            // Creating new JLabel + text.
            lastNameText = new JLabel("Last name: ");
            // Creating new JLabel + text.
            passwordText = new JLabel("Password: ");
            // Creating new JLabel + text.
            emailText = new JLabel("Email: ");
            // Creating new JLabel + text.
            budgetText = new JLabel("Budget: ");
            // Creating new JLabel + text.
            genderText = new JLabel("Gender: ");
            // Creating new JLabel + text.
            currencyText = new JLabel("Currency: ");

            // Creating new JTextField + size of its column.
            firstNameD = new JTextField(10);
            // Creating new JTextField + size of its column.
            lastNameD = new JTextField(10);
            // Creating new JTextField + size of its column.
            passwordD = new JTextField(10);
            // Creating new JTextField + size of its column.
            emailD = new JTextField(10);
            // Creating new JTextField + size of its column.
            budgetD = new JTextField(10);
            // Creating new JComboBox.
            currencyD = new JComboBox<>();
            // Setting the length of CurrencyD.
            currencyD.setPrototypeDisplayValue("___________");

            // Creating new JRadioButton and giving them text.
            male=new JRadioButton("Male");
            female=new JRadioButton("Female");

            // Creating new ButtonGroup and adding to it the JRadioButtons.
            // In order to select only male of female we put the radio buttons in a group and then only male or female can be selected.
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(male);
            genderGroup.add(female);

            // Creating new JButtons and giving them text.
            register = new JButton("Register now");
            back = new JButton("Back");

            // Creating new JPanels.
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            panelSouth= new JPanel();
            registerPagePanelOverAll = new JPanel();

            // Creating new GridBagConstraints.
            gbcData = new GridBagConstraints();

        }

        /**
         * This method prepares RegisterPage page for display.
         */
        public void start() {
            // We visited this page for the 1st time.
            isFirst = true;
            // Gives currencyD the default currencies (NIS and USD).
            vm.getCurrencies(-1);
            // panelNorth setting, construction.

            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // panelCenter setting, construction.
            // Sets the color of the panel.
            panelCenter.setBackground(Color.YELLOW);
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridBagLayout());

            // gridBagLayout components.
            gbcData.gridx=0;
            gbcData.gridy=0;
            panelCenter.add(firstNameText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=0;
            panelCenter.add(firstNameD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=1;
            panelCenter.add(lastNameText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=1;
            panelCenter.add(lastNameD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=2;
            panelCenter.add(passwordText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=2;
            panelCenter.add(passwordD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=3;
            panelCenter.add(emailText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=3;
            panelCenter.add(emailD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=4;
            panelCenter.add(budgetText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=4;
            panelCenter.add(budgetD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=5;
            panelCenter.add(currencyText, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=5;
            panelCenter.add(currencyD, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=6;
            panelCenter.add(genderText, gbcData);

            gbcData.gridx=0;
            gbcData.gridy=7;
            panelCenter.add(male, gbcData);
            gbcData.gridx=1;
            gbcData.gridy=7;
            panelCenter.add(female, gbcData);
            // panelSouth setting, construction
            // Sets the layout of the panel.
            panelSouth.setLayout(new FlowLayout());
            // Adds a variables to the panel.
            panelSouth.add(register);
            panelSouth.add(back);
            // registerPagePanelOverAll setting, construction.
            // Rets the layout of the panel.
            registerPagePanelOverAll.setLayout(new BorderLayout());
            // Adds a variables to the panel.
            registerPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);
            registerPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);
            registerPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);

            register.addActionListener(e -> {
                // These String variables get text from the JTextFields below.
                String firstName = firstNameD.getText();
                String lastName = lastNameD.getText();
                String password = passwordD.getText();
                String budgetString = budgetD.getText();
                String currency = (String) currencyD.getSelectedItem();
                String email = emailD.getText();

                // gender saves the selection of male or female.
                int gender=(male.isSelected())?1:0;

                // This if checks that all the fields were filled.
                if (firstName != null && !firstName.isEmpty() &&
                        lastName != null && !lastName.isEmpty() &&
                        password != null && !password.isEmpty() &&
                        budgetString != null && !budgetString.isEmpty() &&
                        currency != null && !currency.isEmpty() &&
                        email != null && !email.isEmpty()){
                    String[] currencySelected = currency.split(" ");
                    String currencyName = currencySelected[0];
                    double currencyValue = Double.parseDouble(currencySelected[1]);
                    // Coverts the budget the user inputted from String to double.
                    double budget = Double.parseDouble(budgetString);
                    // Create new Person with the data the user inserted in registration page.
                    userDetails = new Person(200, firstName, lastName, password, gender, budget, new Currency(currencyName,currencyValue), email);
                    // Add the new Person to the database.
                    vm.addPerson(userDetails);
                    // Redirect to the loginPage.
                    changeView("Login Page");
                }
                else {
                    // If failed present error message.
                    showMessage(new Message("One or more of the fields are empty!!! \n Please try again"));
                }
            });
            // Redirect to the previous page.
            back.addActionListener(e -> changeView("Login Page"));
        }

        /**
         * This method activates start() method of RegisterPage for the 1st time.
         */
        public void update() {
            if (!isFirst) {
                start();
            }
            // Message to display to the user.
            loginAndOutJLabel.setText("Hello, please fill your information to Register :)");
            panelNorth.add(loginAndOutJLabel);
        }
        /**
         * Adds the default currencies to the registration page.
         * @param currencyList Contains the list of currencies.
         */
        public void showCurrencies(List<Currency> currencyList) {
            // Adds the currencies name into currencyD.
            for (Currency cur: currencyList){
                currencyD.addItem(cur.toString());
            }
        }
    }
    /**
     * This class describes CategoryPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class CategoryPage {
        // Indicates if we visited this page or not.
        private boolean isFirst = false;
        // Presents greeting text.
        private JLabel jLGreetingText;
        // 1st button takes us to addCategoryPAge and the 2nd button takes us back to categoryPage.
        private JButton jBAddNewCategory,jBackToCategoryPage;
        // Area where we display the categories that belong to the user.
        private JTextArea ta;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel categoryPagePanelOverAll,panelCenter, panelSouth, panelNorth;
        /**
         * CategoryPage's constructor.
         */
        public void init() {
            // Creating new JLabel + setting its text.
            jLGreetingText = new JLabel("This is all the current categories");
            // Creating new JButton + setting its text.
            jBAddNewCategory = new JButton("add category");
            jBackToCategoryPage=new JButton("Home Page");
            // Creating new JTextArea.
            ta = new JTextArea();
            // Creating new JPanels.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            categoryPagePanelOverAll = new JPanel();
        }

        /**
         * This method prepares CategoryPage page for display.
         */
        public void start() {
            // We are visiting the page for the first, isFirst will be true.
            isFirst = true;
            // panelNorth setting, construction.
            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // Adds variable to the panel.
            panelNorth.add(jLGreetingText);
            // panelCenter settings, construction.
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridLayout(1,1));
            // Adds variables to the panel.
            panelCenter.add(ta);
            // panelSouth setting, construction.
            // Sets the color of the panel.
            panelSouth.setBackground(Color.YELLOW);
            // Sets the layout of the panel.
            panelSouth.setLayout(new FlowLayout());
            // Adds variable to panel.
            panelSouth.add(jBAddNewCategory);
            panelSouth.add(jBackToCategoryPage);
            // categoryPagePanelOverAll setting, construction.
            categoryPagePanelOverAll.setLayout(new BorderLayout());
            // Adds variables to the panel.
            categoryPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);
            categoryPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);
            categoryPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);
            // Changes the view to addCategoryPAge.
            jBAddNewCategory.addActionListener(e -> changeView("Add Category Page"));
            // Changes the view to navPage.
            jBackToCategoryPage.addActionListener(e -> changeView("Home Page"));
        }
        /**
         * This method displays the categories in CategoryPage page.
         * @param categoryList Contains the list of categories.
         */
        public void showCategories(List<Category> categoryList) {
            // This variable will include the content that will be displayed in the textArea.
            StringBuilder sb = new StringBuilder();
            int i = 1;
            // Adds the categories to the StringBuilder.
            for (Category categoryName : categoryList) {
                sb.append(i++).append(". ").append(categoryName.toString()).append("\n");
            }
            // At the end add the StringBuilder with the data to the textArea.
            ta.setText(sb.toString());
        }
        /**
         * This method activates CategoryPage's start() method for first time and activates Viewmodel's getCategory method.
         */
        public void update(){
            if (!isFirst){
                start();
            }
            // Gets the categories of the current user.
            vm.getCategories(userDetails.getId());
        }
    }
    /**
     * This class describes AddItemPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class AddItemPage {
        // Indicates if we are visiting the page for the 1st time.
        private boolean isFirst = false;
        private GridBagConstraints gbc_data;
        // Labels that will contain texts that will hint the user what to insert to each JTextField.
        private JLabel jLMessage,jLItemNameText,jLItemPriceText,jLItemCategoryText, jLItemAmountText, jLItemCurrencyText,
        jLItemDate, jLBudget, jLCurrency;
        // Will contain the data the user inputs.
        private JTextField tFItemNameD, tFItemPriceD, tFAmountD;
        // 1st button will add the method to the database, 2nd button will take us back to previous page.
        private JButton jBAddItem, jBBack, jBAddCategory;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel addItemPageOverAllPanel, panelSouth, panelNorth,panelCenter;
        // 1st one will contain the categories of the user, 2nd will include the currencies of the user.
        private JComboBox<String> jCategorieslistD, jCurrencyListD;
        // Will contain the date the item was created/purchased.
        private JDateChooser dateCreated;

        /**
         * AddItemPage's constructor.
         */
        public void init() {
            // Creating new JButton + setting its text.
            jBAddCategory = new JButton("add category");
            // Creating new JLabels + setting their texts.
            jLMessage = new JLabel("Hello please insert your new item");
            jLItemCategoryText =new JLabel("Category: ");
            jLItemAmountText = new JLabel("Amount: ");
            jLItemDate = new JLabel("Date of transaction: ");
            jLItemNameText = new JLabel("Name: ");
            jLItemPriceText = new JLabel("Price: ");
            jLItemCurrencyText = new JLabel("Currency");
            jLCurrency = new JLabel();
            jLBudget = new JLabel();
            addItemPageOverAllPanel = new JPanel();
            // Creating new JTExtFields and setting their column size.
            tFItemNameD = new JTextField(20);
            tFItemPriceD= new JTextField(20);
            tFAmountD = new JTextField(20);
            // Creating new JDateChooser.
            dateCreated = new JDateChooser();
            // set the size of the JDateChooser (dateCreated).
            dateCreated.setPreferredSize(new Dimension(220,20));
            // Creating new JButtons and setting their texts.
            jBAddItem = new JButton("Add item");
            jBBack =new JButton("Back");
            // Creating new JPanels.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            // Creating new GridBagConstraints.
            gbc_data= new GridBagConstraints();
            // Creating new JComboBox.
            jCategorieslistD = new JComboBox<>();
            jCurrencyListD = new JComboBox<>();
            // Setting the length of the JComboBoxes.
            jCategorieslistD.setPrototypeDisplayValue("________________________");
            jCurrencyListD.setPrototypeDisplayValue("________________________");
        }
        /**
         * This method prepares addItemPage page for display.
         */
        public void start() {
            // We visited the page for the first time so isFirst needs to be true.
            isFirst = true;
            // panelNorth setting, construction.
            // Setting the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Setting the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // Adding variable to the panel.
            panelNorth.add(jLMessage);
            // panelCenter setting, construction.
            // Setting the color of the panel.
            panelCenter.setBackground(Color.YELLOW);
            // Setting the layout of the panel.
            panelCenter.setLayout(new GridBagLayout());
            // gridBagLayout components.
            gbc_data.gridx=0;gbc_data.gridy=0;
            panelCenter.add(jLItemNameText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=0;
            panelCenter.add(tFItemNameD,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=1;
            panelCenter.add(jLItemPriceText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=1;
            panelCenter.add(tFItemPriceD,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=2;
            panelCenter.add(jLItemCategoryText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=2;
            panelCenter.add(jCategorieslistD,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=3;
            panelCenter.add(jLItemAmountText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=3;
            panelCenter.add(tFAmountD,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=4;
            panelCenter.add(jLItemCurrencyText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=4;
            panelCenter.add(jCurrencyListD,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=5;
            panelCenter.add(jLItemDate,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=5;
            panelCenter.add(dateCreated,gbc_data);
            // panelSouth setting, construction.
            // Setting the layout of the panel.
            panelSouth.setLayout(new FlowLayout());
            // Add the variables to the user.
            panelSouth.add(jBAddItem);
            panelSouth.add(jBBack);
            panelSouth.add(jBAddCategory);
            panelSouth.add(jLBudget);
            // Gets all the currencies of the current user so that he can choose from them in the add item page.
            jLCurrency.setText(userDetails.getCurrency().getName());
            panelSouth.add(jLCurrency);
            // addItemPageOverAllPanel setting, construction.
            // Sets the layout of the panel.
            addItemPageOverAllPanel.setLayout(new BorderLayout());
            // Adds variables to the panel.
            addItemPageOverAllPanel.add(panelSouth, BorderLayout.SOUTH);
            addItemPageOverAllPanel.add(panelCenter, BorderLayout.CENTER);
            addItemPageOverAllPanel.add(panelNorth, BorderLayout.NORTH);
            // Adds a new item to the database.
            jBAddItem.addActionListener(e -> {
                // Checks that all the fields are not empty.
                String currencySelected =(String) jCurrencyListD.getSelectedItem();

                if(!tFItemNameD.getText().isEmpty() && !tFItemPriceD.getText().isEmpty() &&
                        currencySelected != null && !currencySelected.isEmpty() && !tFAmountD.getText().isEmpty()
                        && jCategorieslistD.getSelectedIndex() != -1 && jCurrencyListD.getSelectedIndex() != -1) {
                    // Contains the selected currency of the new item.
                    String[] itemCurrency = currencySelected.split(" ");
                    Currency currencyItem = new Currency(itemCurrency[0],Double.parseDouble(itemCurrency[1]));
                    // Contains the price of the item multiplied by the amount of the item.
                    double calculatedPrice = Double.parseDouble(tFItemPriceD.getText()) * Double.parseDouble(tFAmountD.getText());
                    // Creating new item from the inserted values of the user.
                    Item item = new Item(tFItemNameD.getText(), Double.parseDouble(tFItemPriceD.getText()),
                            currencyItem, new Category((String) jCategorieslistD.getSelectedItem()), Integer.parseInt(tFAmountD.getText()), dateCreated.getDate());
                    // Checking that the transaction can be done.
                    vm.checkTransaction(userDetails, currencyItem, calculatedPrice, item);
                }else{
                    // Missing values error message.
                    showMessage(new Message("Item cannot be added! you need to fill all the fields"));
                }
            });
            // Redirect to CostList page.
            jBBack.addActionListener(e -> changeView("Expenses Page"));
            // Redirect to categoryPage.
            jBAddCategory.addActionListener(e -> changeView("Categories Page"));

        }

        /**
         *     This method activates AddItemPage's start() for 1st time and resets the values of jCategoryListD
         *     JCurrencyListD and jLBudget.
         */
        public void update(){
            if (!isFirst) {
                start();
            }
            jCategorieslistD.removeAllItems();// Cleans variable from data.
            jCurrencyListD.removeAllItems();// Cleans variable from data.
            tFItemNameD.setText("");// Cleans variable from data.
            tFItemPriceD.setText("");// Cleans variable from data.
            tFAmountD.setText("");// Cleans variable from data.
            dateCreated.cleanup();// Cleans variable from data.
            jLBudget.setText(" Wallet balance: " + userDetails.getBudget());// Present wallet balance of user.
            jLCurrency.setText(userDetails.getCurrency().getName());// Set the type of wallet currency.
            int userID = userDetails.getId();// Get the user id.
            if (isLogin) {
                vm.getItemsForPerson(userID);// Get the items of the user.
                vm.getCurrencies(userID);// Get the currencies of the user.
                vm.getCategories(userID);// Get the categories of the user.
            }
        }
        /**
         * This method shows the categories in AddItemPage page.
         * @param categoryList List of categories.
         */
        public void showCategories(List<Category> categoryList) {
            for (Category cat: categoryList){
                jCategorieslistD.addItem(cat.toString());
            }
        }
        /**
         * Adds the item to the database.
         * @param item The item we wish to add.
         * @param updatedBudget The amount to deduct from the user's wallet.
         */
        public void createTransaction(Item item, double updatedBudget){
            // Adding the item to the database.
            vm.addItem(item, userDetails.getId(), updatedBudget);
            // Cleaning the fields from data.
            tFItemNameD.setText("");
            tFItemPriceD.setText("");
            tFAmountD.setText("");
            jCategorieslistD.setSelectedIndex(-1);
            jCurrencyListD.setSelectedIndex(-1);
            userDetails.setBudget(userDetails.getBudget() - updatedBudget);
            // Updating the wallet balance in the page.
            jLBudget.setText(" Wallet balance: " + userDetails.getBudget());
        }
        /**
         * Adds the user's currencies to the currency comboBox so the user can choose from them the new item's currency.
         * @param currencyList Contains all the currencies of the user.
         */
        public void showCurrencies(List<Currency> currencyList) {
            for (Currency currency : currencyList) {
                jCurrencyListD.addItem(currency.toString());
            }
        }
        /**
         * Displays pop up message to the user.
         * @param message Contains the message to display to the user.
         */
        public void showMessage(Message message) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame,message.getText(),"Attention",JOptionPane.INFORMATION_MESSAGE));
        }
    }
    /**
     * This class describes AddCategoryPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class AddCategoryPage {
        // Indicates if we visited this page already.
        private boolean isFirst = false;
        private GridBagConstraints gbc_data;
        // JLabels to display text.
        private JLabel jLMessage,jLCategoryNameText;
        // texField that will contain the category the user wants to add.
        private JTextField tFCategoryNameD;
        // 1st button will add the category if it doesn't exist, 2nd button redirects us to NavPage.
        private JButton jBAddItem,jBBackToHomePage;
        // Panels that help us construct a much more beautiful user interface
        private JPanel addCategoryPagePanelOverAll, panelSouth, panelNorth, panelCenter;

        /**
         * AddCategory's constructor.
         */
        public void init() {
            // Creates new JLabel and adds text to them.
            jLMessage = new JLabel("Hello please insert your new Category");
            jLCategoryNameText = new JLabel("Category: ");
            // Create new JTextField and sets its column size.
            tFCategoryNameD = new JTextField(10);
            // Creates new buttons and sets their text.
            jBAddItem = new JButton("add category");
            jBBackToHomePage=new JButton("Back");
            // Creates new JPanels.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            addCategoryPagePanelOverAll = new JPanel();
            // Creates new GridBagConstraints.
            gbc_data= new GridBagConstraints();
        }
        /**
         * Prepares AddCategory's Page for display.
         */
        public void start() {
            // We are visiting the page for the first time so ifFirst = true.
            isFirst = true;
            // panelNorth setting, construction.
            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // Adds variable to the panel.
            panelNorth.add(jLMessage);

            // panelCenter setting, construction.
            // Sets the color of the panel.
            panelCenter.setBackground(Color.YELLOW);
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridBagLayout());

            // gridBagLayout components.
            gbc_data.gridx=0;gbc_data.gridy=0;
            panelCenter.add(jLCategoryNameText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=0;
            panelCenter.add(tFCategoryNameD,gbc_data);
            panelSouth.add(jBAddItem);
            panelSouth.add(jBBackToHomePage);

            // addCategoryPagePanelOverAll setting, construction.
            // Sets the layout of the panel.
            addCategoryPagePanelOverAll.setLayout(new BorderLayout());
            // Adds variables to the panel.
            addCategoryPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);
            addCategoryPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);
            addCategoryPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);

            // This action listener adds the category.
            jBAddItem.addActionListener(e -> {
                String categoryName = tFCategoryNameD.getText();
                tFCategoryNameD.setText("");
                // Calling addCategory method of ViewModel.
                vm.addCategory(new Category(categoryName), userDetails.getId());
                // Cleans the textField of all the previous values.

            });
            // Redirect us to categoryPage.
            jBBackToHomePage.addActionListener(e -> changeView("Categories Page"));
        }
        /**
         * Activates AddCategoryPage's start() method for the 1st time.
         */
        public void update(){
            if(!isFirst){
                start();
            }
        }
    }

    /**
     * This class describes CurrencyPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class CurrencyPage {
        // Indicates if we visited the page already or not.
        private boolean isFirst = false;
        // Display greeting text to the user.
        private JLabel jLGreetingText;
        // 1st button will add the currency , 2nd button will take us back to navPage.
        private JButton jBAddNewCurrency,jBackToHomePage;
        // JTextArea that will display all the user's currencies.
        private JTextArea ta;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel currencyPagePanelOverAll,panelCenter, panelSouth, panelNorth;
        /**
         * CurrencyPage's constructor.
         */
        public void init() {
            // Creates new JLabel and setting its text.
            jLGreetingText = new JLabel("This is all the current currencies");
            // Creates new JButtons and setting their text.
            jBAddNewCurrency = new JButton("add currency");
            jBackToHomePage = new JButton("Home Page");
            // Creating new JTextArea.
            ta = new JTextArea();
            // Creating new JPanel.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            currencyPagePanelOverAll = new JPanel();
        }
        /**
         * Prepares the CurrencyPage page ready for display.
         */
        public void start() {
            // We are visiting the page for the 1st time.
            isFirst = true;
            // panelNorth setting, construction.
            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // Adds variables to the panel.
            panelNorth.add(jLGreetingText);

            // panelCenter setting, construction.
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridLayout(1,1));
            // Add variable to the panel.
            panelCenter.add(ta);

            // panelSouth setting, construction.
            // Sets the color of the panel.
            panelSouth.setBackground(Color.YELLOW);
            // Sets the layout of the panel.
            panelSouth.setLayout(new FlowLayout());
            // Adds variables to the panel.
            panelSouth.add(jBAddNewCurrency);
            panelSouth.add(jBackToHomePage);

            // currencyPagePanelOverAll setting, construction.
            // Sets the layout of the panel.
            currencyPagePanelOverAll.setLayout(new BorderLayout());
            // Adds variables to the panel.
            currencyPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);
            currencyPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);
            currencyPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);

            // Redirects us to addCurrencyPage.
            jBAddNewCurrency.addActionListener(e -> changeView("Add Currency Page"));
            // Redirects us to navPage (home page).
            jBackToHomePage.addActionListener(e -> changeView("Home Page"));
        }
        /**
         * Adds the currencies to the JTexArea (ta) in order to present the user the currencies.
         * @param currencyList Contains all the currencies of the user.
         */
        public void showCurrencies(List<Currency> currencyList) {
            // This variable will contain all the currencies which then we will pass to the JTextField (ta).
            StringBuilder sb = new StringBuilder();
            // This for adds every currency to the StringBuilder.
            int i=1;
            for(Currency currency : currencyList){
                sb.append(i++).append(". ")
                        .append(currency.getName())
                        .append(": ")
                        .append(currency.getValue())
                        .append(" NIS\n");
            }
            // Add the StringBuilder text to the JTextField (ta).
            ta.setText(sb.toString());
        }
        /**
         * Activates CategoryPage's start() method for the first time and activates viewModel's getCategory method.
         */
        public void update(){
            if (!isFirst) {
                start();
            }
            // Activates getCurrencies method of ViewModel in order to get the user's currencies.
            vm.getCurrencies(userDetails.getId());
        }
    }

    /**
     * This class describes AddCurrencyPage.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class AddCurrencyPage {
        // Indicates if we visited the website for the 1st time or not.
        private boolean isFirst = false;
        private GridBagConstraints gbc_data;
        // Display text for the user.
        private JLabel jLMessage,jLCurrencyNameText, jLCurrencyConversionValueText;
        // JTExtFields that will contain currency data that the user inserts.
        private JTextField tFCurrencyNameD, tFCurrencyConversionValueD;
        // 1st button will activate add currency method.
        private JButton jBAddCurrency, jBBackToCurrencyPage;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel addCurrencyPagePanelOverAll, panelSouth, panelNorth, panelCenter;
        /**
         * AddCurrencyPage's constructor.
         */
        public void init() {
            // Creates new JLabels and sets their texts.
            jLMessage = new JLabel("Hello, please insert the new currency's signature and value of conversion");
            jLCurrencyNameText =new JLabel("Currency name: ");
            jLCurrencyConversionValueText =new JLabel("Conversion value: ");
            addCurrencyPagePanelOverAll = new JPanel();

            // Creates new JTextFields and sets their column sizes.
            tFCurrencyNameD = new JTextField(10);
            tFCurrencyConversionValueD = new JTextField(10);

            // Creates new JButtons and sets their texts.
            jBAddCurrency = new JButton("Add currency");
            jBBackToCurrencyPage =new JButton("Back");

            // Creates new JPanels.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();

            // Creates new GridBagConstraints.
            gbc_data = new GridBagConstraints();
        }

        /**
         * Prepares the AddCurrencyPage page for display.
         */
        public void start() {
            // We are visiting the page for the 1st time so isFirst is true.
            isFirst = true;
            // panelNorth setting, construction.
            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new FlowLayout());
            // Adds a variable to the panel.
            panelNorth.add(jLMessage);
            // panelCenter setting, construction.
            // Sets the color of the panel.
            panelCenter.setBackground(Color.YELLOW);
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridBagLayout());
            // gridBagLayout components.
            gbc_data.gridx=0;gbc_data.gridy=0;
            panelCenter.add(jLCurrencyNameText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=0;
            panelCenter.add(tFCurrencyNameD,gbc_data);
            gbc_data.gridx=0;gbc_data.gridy=1;
            panelCenter.add(jLCurrencyConversionValueText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=1;
            panelCenter.add(tFCurrencyConversionValueD,gbc_data);
            // panelSouth setting, construction.
            // Adds variables to the panel.
            panelSouth.add(jBAddCurrency);
            panelSouth.add(jBBackToCurrencyPage);

            // addCurrencyPagePanelOverAll setting, construction.
            // Sets the layout of the panel.
            addCurrencyPagePanelOverAll.setLayout(new BorderLayout());

            // Adds variables to the panel.
            addCurrencyPagePanelOverAll.add(panelSouth, BorderLayout.SOUTH);
            addCurrencyPagePanelOverAll.add(panelCenter, BorderLayout.CENTER);
            addCurrencyPagePanelOverAll.add(panelNorth, BorderLayout.NORTH);

            // Calls the addCurrency method of ViewModel in order to add a new currency.
            jBAddCurrency.addActionListener(e -> {
                String currencyName = tFCurrencyNameD.getText();
                String currencyValue = tFCurrencyConversionValueD.getText();
                tFCurrencyNameD.setText("");
                tFCurrencyConversionValueD.setText("");
                if(currencyName.equals("") || currencyValue.equals("")){
                    showMessage(new Message("There is something wrong with the data, please fill again"));
                }
                else{
                    vm.addCurrency(new Currency(currencyName, Double.parseDouble(currencyValue)), userDetails.getId());
                }
            });
            // Redirects us to the back to currencyListPage (CurrencyPage).
            jBBackToCurrencyPage.addActionListener(e -> changeView("Currencies Page"));

        }
        /**
         * Activates AddCategoryPage's start() method for the 1st time.
         */
        public void update(){
            if (!isFirst){
                start();
            }
        }
    }
    /**
     * This class describes DateReports.
     * @author Ziv Hochman, Anzor Torikashvili.
     */
    public class DateReports {
        // Indicates if we visited this page before or not.
        private boolean isFirst = false;
        private GridBagConstraints gbc_data;
        // Table that will display the data of report by date.
        private JTable table;
        // Will contain the items that were created between the specified dates.
        private DefaultTableModel itemTable;
        // The first min and max dates that the items we want to display can have.
        private JDateChooser startDate, endDate;
        // Will contain text to present to the user.
        private JLabel jLGreetingTextStart,jLGreetingTextEnd,startDateText,endDateText;
        private JScrollPane dateReportTableJSP;
        // 1st button will show the user the items between dates he selected, 2nd button takes us back to homePage.
        private JButton jBShowDetailReport, jBBack;
        // Panels that help us construct a much more beautiful user interface.
        private JPanel dateReportsPanelOverAll, panelSouth, panelNorth, panelCenter;
        /**
         * DateReports's constructor.
         */
        public void init() {
            // Creates new itemTable.
            itemTable = new DefaultTableModel();
            // Creates new JTable.
            table = new JTable();
            // Creates new GridBagConstraints.
            gbc_data= new GridBagConstraints();
            // Creates new JLabels and setting their texts.
            jLGreetingTextStart = new JLabel("Please choose the ");
            jLGreetingTextEnd = new JLabel("specific range:");
            startDateText=new JLabel("Start Date:");
            endDateText=new JLabel("Final Date:");
            // Creating new JButtons and setting their texts.
            jBShowDetailReport = new JButton("Show");
            jBBack = new JButton("Back");
            // Creating new JPanels.
            panelSouth = new JPanel();
            panelNorth = new JPanel();
            panelCenter = new JPanel();
            dateReportsPanelOverAll = new JPanel();
            // Creating default values for the start date and end date.
            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();
            calStart.add(Calendar.YEAR, -3);
            calEnd.add(Calendar.YEAR, 1);
            // Creating new JDateChooser and setting their default values. one presents the start date and the other the end date.
            startDate = new JDateChooser(calStart.getTime());
            endDate = new JDateChooser(calEnd.getTime());
            // Creates new JScrollPane.
            dateReportTableJSP = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            // Sets the visibility of the table to true (visible).
            table.setFillsViewportHeight(true);
        }
        /**
         * Prepares DateReports's Page for display.
         */
        public void start() {
            // We are visiting the page for the first time so isFirst needs to be true.
            isFirst = true;
            // panelNorth setting, construction.
            // Sets the color of the panel.
            panelNorth.setBackground(Color.GREEN);
            // Sets the layout of the panel.
            panelNorth.setLayout(new GridBagLayout());
            // gridBagLayout components.
            gbc_data.gridx=0;gbc_data.gridy=0;
            panelNorth.add(jLGreetingTextStart,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=0;
            panelNorth.add(jLGreetingTextEnd,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=1;
            panelNorth.add(startDateText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=1;
            panelNorth.add(startDate,gbc_data);

            gbc_data.gridx=0;gbc_data.gridy=2;
            panelNorth.add(endDateText,gbc_data);
            gbc_data.gridx=1;gbc_data.gridy=2;
            panelNorth.add(endDate,gbc_data);
            // panelCenter setting, construction.
            // Sets the color of the panel.
            panelCenter.setBackground((Color.BLUE));
            // Sets the layout of the panel.
            panelCenter.setLayout(new GridLayout(1,1));
            // Adds variables to the panel.
            panelCenter.add(dateReportTableJSP);
            panelSouth.setBackground(Color.YELLOW);

            // panelSouth setting, construction.
            // Sets the layout of the panel.
            panelSouth.setLayout(new FlowLayout());
            // Adds variables to the panel.
            panelSouth.add(jBShowDetailReport);
            panelSouth.add(jBBack);

            // dateReportsPanelOverAll setting, construction.
            // Sets the layout of the panel.
            dateReportsPanelOverAll.setLayout(new BorderLayout());
            // Adds variables to the panel.
            dateReportsPanelOverAll.add(BorderLayout.NORTH,panelNorth);
            dateReportsPanelOverAll.add(BorderLayout.CENTER, panelCenter);
            dateReportsPanelOverAll.add(BorderLayout.SOUTH,panelSouth);

            // Activates getDataByDates method of ViewModel and giving it the start and end date.
            jBShowDetailReport.addActionListener(e -> {
                // LocalDates that contain the start and end date the user selected.
                LocalDate localDateStart = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateEnd = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // Activates ViewModel's getDataByDates.
                if (localDateEnd.isBefore(localDateStart)){
                    showMessage(new Message("Error the Start date cannot be later then the final date"));
                } else {
                    vm.getDataByDates(userDetails.getId(), localDateStart,localDateEnd);
                }
            });
            // Redirects us to navPage (homePage).
            jBBack.addActionListener(e -> changeView("Home Page"));
        }
        /**
         * Activates DateReport's start() method for the 1st time and the method of getDateByDates from viewModel.
         */
        public void update(){
            if (!isFirst){
                start();
            }
            vm.getDataByDates(userDetails.getId(), startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        /**
         * Shows the items by date to the user by inserting the data in itemsByDates to the table variable.
         * @param itemsByDates Contains the items that were created between the dates the user picked.
         */
        public void showItemsByDate(List<Item> itemsByDates) {
            // Name of the column that will be displayed.
            String[] categoryColumns = {"ID", "Name", "Price","Currency", "Amount", "Total Amount", "Category", "Date Of Purchase"};
            // Creating new DefaultTableModel.
            itemTable=new DefaultTableModel(null,categoryColumns);
            // Creating new String array that will contain the data of each data, and then we will insert each one into itemTable.
            Object[] row = new Object[8];
            // Number of data that we need to insert into itemTable.
            int size = itemsByDates.size();
            double price;
            double amount;
            Currency currentCurrency;
            for (int i =0 ; i < size; i++){
                Item item = itemsByDates.get(i);
                price = item.getPrice();
                currentCurrency = item.getCurrency();
                amount = item.getAmount();
                row[0]=item.getId();
                row[1]=item.getName();
                row[2]=price;
                row[3]=currentCurrency.getName();
                row[4]=amount;
                row[5]= currentCurrency.getValue()*amount*price;
                row[6]=item.getDateOfPurchase();
                row[7]=item.getCategory().getCategory();
                // After we're done adding the details of the item we can insert it into itemTable.
                itemTable.insertRow(i,row);
            }
            // Inserting itemTable to table in order to display the items for the user.
            table.setModel(itemTable);
        }
    }
    /**
     * This method changes the page we are in according to the user's choice.
     * @param pageName Contains the name of the page the user wants to go to now.
     */
    public void changeView(String pageName){
        if (pageName != null && !pageName.isEmpty()) {
            activePageName = pageName;
            frame.setTitle(pageName);
            switch (pageName) {
                // change the page to login page
                case "Login Page": {
                    // the number of the page in framePanelOverAll's layout
                    cardLayout.show(framePanelOverAll, "2");
                    loginPage.update();
                    break;
                }
                // changing the page to navPage (menu)
                case "Home Page": {
                    // the number of the page in framePanelOverAll's layout
                    cardLayout.show(framePanelOverAll, "3");
                    navPage.update();
                    break;
                }
                // changing the page to costListPage (show expenses page)
                case "Expenses Page": {
                    // the number of the page in framePanelOverAll's layout
                    cardLayout.show(framePanelOverAll, "4");
                    costListPage.update();
                    break;
                }
                // changing the page to registerPage
                case "Registration Page": {
                    // the number of the page in framePanelOverAll's layout
                    cardLayout.show(framePanelOverAll, "5");
                    registerPage.update();
                    break;
                }
                // changing the page to categoryPage
                case "Categories Page": {
                    // the number of the page in framePanelOverAll's layout.
                    cardLayout.show(framePanelOverAll, "6");
                    categoryPage.update();
                    break;
                }
                // changing the page to addItemsPage
                case "Add Item Page": {
                    // the number of the page in framePanelOverAll's layout.
                    cardLayout.show(framePanelOverAll, "7");
                    addItemsPage.update();
                    break;
                }
                // changing the page to addCategoryPage
                case "Add Category Page": {
                    // the number of the page in framePanelOverAll's layout.
                    cardLayout.show(framePanelOverAll, "8");
                    addCategoryPage.update();
                    break;
                }
                // changing the page to dateReportsPage
                case "Expenses By Dates Page": {
                    // the number of the page in framePanelOverAll's layout.
                    cardLayout.show(framePanelOverAll, "9");
                    dateReports.update();
                    break;
                }
                // changing the page to addCurrencyPage
                case "Add Currency Page": {
                    // the number of the page in framePanelOverAll's layout.
                    cardLayout.show(framePanelOverAll, "10");
                    addCurrencyPage.update();
                    break;
                }
                // changing the page to currencyListPage
                case "Currencies Page": {
                    // the number of the page in framePanelOverAll's layout
                    cardLayout.show(framePanelOverAll, "11");
                    currencyPage.update();
                    break;
                }
            }
        }
    }
    /**
     * Overrides the method of the IView interface, Sets the vm in this amount vm.
     * @param vm The vm of the vm field.
     */
    @Override
    public void setIViewModel(IViewModel vm) {
            this.vm = vm;
    }
    /**
     * Overrides the method of the IView interface, activate the showItems method of CostListPage variable.
     * @param items Items we need to display to the user.
     */
    @Override
    public void showItems(Item[] items) {
        costListPage.showItems(items);
    }
    /**
     * <p>
     *     Overrides the method of the IView interface.
     *     Assigned to a Person object the data of the user that logged successfully into the system,
     *     and changing the frame accordingly.
     * </p>
     * @param person Contains the details of the person that is currently in use of the program.
     */
    @Override
    public void showPerson(Person person) {
        if (!isLogin){
            // Activates the showPerson method of loginPage if user is still not logged in.
            loginPage.showPerson(person);
        }
    }
    /**
     * Overrides the method of the IView interface, displays the messages to the user in pop-ups.
     * @param message The message to display to the user in the pop-up.
     */
    @Override
    public void showMessage(Message message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame,message.getText(),"Attention",JOptionPane.INFORMATION_MESSAGE));
    }
    /**
     * <p>
     *     Override method of showCategory in IView, activates the showCategories methods of the active page between
     *     categoryPage and addItemsPage in order to know to which variable we need to give the categories data.
     * </p>
     * @param categoryList List of categories that we will need to display to the user
     */
    @Override
    public void showCategories(List<Category> categoryList) {
        switch (activePageName) {
            // activePageName tells us to which page we need to display the category.
            case "Categories Page" :{ categoryPage.showCategories(categoryList);break;}
            case "Add Item Page" :{ addItemsPage.showCategories(categoryList);break;}
        }
    }
    /**
     * <p>
     * Override method of showCategory in IView, activates the showCurrencies method of the active page between
     * currencyListPage, registerPage and addItemsPage in order to know to which variable we need to give the data
     * in currencyList.
     * </p>
     * @param currencyList Contains the currencies of the user.
     */
    @Override
    public void showCurrencies(List<Currency> currencyList){
        switch (activePageName) {
            case "Currencies Page" :{ currencyPage.showCurrencies(currencyList);break;}
            case "Registration Page": { registerPage.showCurrencies(currencyList);break;}
            case "Add Item Page" :{ addItemsPage.showCurrencies(currencyList);break;}
        }
    }
    /**
     * Override of showItemByDate method of IView,activates showItemsByDate method of DateReports class.
     * @param itemsByDates Contains data of items that were created between two dates that the user chose.
     */
    @Override
    public void showItemsByDate(List<Item> itemsByDates) {
        // Calling showItemsByDate method of dateReports with itemsByDates containing the required data.
        dateReports.showItemsByDate(itemsByDates);
    }
    /**
     * <p>
     * Override of createTransaction method of IView, after the the validation that the transaction can be done
     * we call the createTransaction method of CostManagerView, this method will call createTransaction method of
     * addItemsPage and will add the new item to the database of the user and will update the wallet balance of the
     * user.
     * </p>
     * @param item Contains the item that the user wishes to add to the database.
     * @param updatedBudget The amount we need to deduct from the user's wallet.
     */
    @Override
    public void createTransaction (Item item, double updatedBudget) {
        if (item !=null && updatedBudget >= 0){
            addItemsPage.createTransaction( item, updatedBudget);
        } else {
            showMessage(new Message("There was a problem with the transaction please try again!"));
        }

    }
    /**
     * This method sets the font for each of the components that's displays
     */
    private void setFont() {
        Font myFont = new Font("comicSans",Font.PLAIN,14);
        Font boldFont = new Font("ComicSans",Font.BOLD,16);
        Font TAFont = new Font("comicSans",Font.BOLD,20);
        UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
        UIManager.put("Button.font", boldFont);
        UIManager.put("RadioButton.font", myFont);
        UIManager.put("ComboBox.font", myFont);
        UIManager.put("Label.font", boldFont);
        UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
        UIManager.put("RadioButtonMenuItem.font", myFont);
        UIManager.put("Panel.font", myFont);
        UIManager.put("ScrollPane.font", myFont);
        UIManager.put("Slider.font", myFont);
        UIManager.put("Table.font", myFont);
        UIManager.put("TableHeader.font", myFont);
        UIManager.put("TextField.font", myFont);
        UIManager.put("PasswordField.font", myFont);
        UIManager.put("TextArea.font", TAFont);
        UIManager.put("TextPane.font", myFont);
        UIManager.put("FormattedTextField.font", myFont);
        UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
        UIManager.put("InternalFrame.paletteTitleFont", myFont);
        UIManager.put("InternalFrame.titleFont", myFont);
    }
}