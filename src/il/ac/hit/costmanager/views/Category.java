package il.ac.hit.costmanager.views;

/**
 * This class describes Category.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Category {
    private String category = null;
    /**
     * Constructor of Category.
     * @param category Name of the category.
     */
    public Category(String category) {
        setCategory(category);
    }
    /**
     * Returns String variable that represent the category's name..
     * @return Category of this category field.
     */
    public String getCategory() {
        return category;
    }
    /**
     * Sets the category in this category field..
     * @param category The category of the category field.
     */
    public void setCategory(String category) {
        if (category != null && !category.isEmpty()){
            this.category = category;
        }
    }
    /**
     * Returns String variable.
     * @return New String that contains name of the category.
     */
    @Override
    public String toString() {
        return String.valueOf(getCategory());
    }
}




















