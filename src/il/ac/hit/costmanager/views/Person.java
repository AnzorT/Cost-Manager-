package il.ac.hit.costmanager.views;

/**
 * This class describes Person.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Person {
    private int id;// id of the user.
    private String firstName;// Name of the person.
    private String lastName;// Last name of the user.
    private String password;// Password of the user.
    private int gender;// Gender of the user.
    private Currency currency;// The amount of money the user has.
    private double budget;// The currency of the user.
    private String email;// Emails of the user.
    
    /**
     * Person's constructor.
     * @param id id of the user.
     * @param firstName Name of the person.
     * @param lastName Last name of the user.
     * @param password Password of the user.
     * @param gender Gender of the user.
     * @param budget The amount of money the user has.
     * @param currency The currency of the user.
     * @param email Email of the user.
     */
    public Person(int id,String firstName, String lastName, String password, int gender, double budget, Currency currency, String email) {
        setId(id);// Use the set method of id.
        setFirstName(firstName);// Use the set method of firstName.
        setLastName(lastName);// Use the set method of lastName.
        setPassword(password);// Use the set method of password.
        setGender(gender);// Use the set method of gender.
        setBudget(budget);// Use the set method of budget.
        setCurrency(currency);// Use the set method of currency.
        setEmail(email);// Use the set method of email.
    }
    /**
     * Returns int variable.
     * @return Id of this id field.
     */
    public int getId() {
        return this.id;
    }
    /**
     * Sets the id in this id field.
     * @param id The Id of the Id field.
     */
    public void setId(int id) {
        if (id != 0) {
            this.id = id;
        }
    }
    /**
     * Returns String variable.
     * @return FirstName of this firstName field.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets firstName of this firstName field.
     * @param firstName Contains the data to insert to this firstName field.
     */
    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()){
            this.firstName = firstName;
        }
    }
    /**
     * Returns String variable.
     * @return LastName of this lastName field.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the lastName of this lastName field.
     * @param lastName Contains the data to insert into this lastName field.
     */
    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()){
            this.lastName = lastName;
        }
    }
    /**
     * Returns String variable.
     * @return Password of this password field.
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Sets the password of this password field.
     * @param password Contains the data to insert into this password field.
     */
    public void setPassword(String password) {
        if (password != null && !password.isEmpty()){
            this.password = password;
        }
    }
    /**
     * Returns int variable.
     * @return Gender of this gender field.
     */
    public int getGender() {
        return gender;
    }
    /**
     * Sets the gender of this gender field.
     * @param gender Contains the data to insert into this password field.
     */
    public void setGender(int gender) {
        if(gender ==1 || gender==0){
            this.gender = gender;
        }
    }

    /**
     * Returns double variable.
     * @return Budget of this budget field.
     */
    public double getBudget() {
        return budget;
    }
    /**
     * Sets the budget of this budget field.
     * @param budget Contains the data to insert into this budget field.
     */
    public void setBudget(double budget) {
        if(budget >0){
            this.budget = budget;
        }
    }
    /**
     * Returns Currency variable.
     * @return Currency of this currency field.
     */
    public Currency getCurrency() {
        return currency;
    }
    /**
     * Sets the currency of this currency field.
     * @param currency Contains the data to insert into this currency field.
     */
    public void setCurrency(Currency currency) {
        if(currency!=null){
            this.currency = currency;
        }
    }
    /**
     * Returns String variable.
     * @return Email of this email field.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email of this email field.
     * @param email Contains the data to insert into this email field.
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@gmail.com")){
            this.email = email;
        }
    }
    /**
     * Returns String variable.
     * @return New String that contains firstName and lastName of the current user.
     */
    @Override
    public String toString() {
        return String.valueOf(getFirstName()) +" "+ String.valueOf(getLastName());
    }
}
