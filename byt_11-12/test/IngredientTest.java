import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

    Ingredient ingredient;

    String str0 = new String(new char[0]);
    String str1 = new String(new char[1]);
    String str50 = new String(new char[50]);
    String str51 = new String(new char[51]);
    String str1000 = new String(new char[1000]);
    String str1001 = new String(new char[1001]);

    @Before
    public void setUp() throws Exception {
        ingredient = new Ingredient(str1, str1);
    }

    @After
    public void tearDown() throws Exception {
        ingredient = null;
    }

    @Test
    public void setNameTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            ingredient.setName(str1);
            ingredient.setName(str50);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            ingredient.setName(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            ingredient.setName(str51);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setDescriptionTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            ingredient.setDescription(str50);
            ingredient.setDescription(str51);
            ingredient.setDescription(str1000);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            ingredient.setDescription(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            ingredient.setDescription(str1001);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

}
