package il.ac.hit.costmanager.views;

import java.util.Locale;

/**
 * This class describes Currency.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Currency {
    private String name = null;
    private double value = 0;
    /**
     * Constructor of Currency.
     * @param name Name of the currency.
     * @param value The value of the conversion to/from NIS.
     */
    public Currency(String name, double value) {
        setName(name);
        setValue(value);
    }
    /**
     * Returns String variable.
     * @return Name of this name field.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name in this name field.
     * @param name The name of the name field.
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()){
            this.name = name.toLowerCase();
        }
    }
    /**
     * Returns double variable.
     * @return Value of this value field.
     */
    public double getValue() {
        return value;
    }
    /**
     * Sets the value in this value field.
     * @param value The value of the value field.
     */
    public void setValue(double value) {
        if (value >= 0) {
            this.value = value;
        }
    }
    /**
     * Returns String variable.
     * @return New String that contains name of the currency and his worth value.
     */
    @Override
    public String toString() {
        return String.valueOf(getName()) + " " +String.valueOf(getValue());
    }
}