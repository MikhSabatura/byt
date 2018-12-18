import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReviewTest {

    Recipe recipe;
    Review review;

    String str0 = new String(new char[0]);
    String str1 = new String(new char[1]);
    String str2000 = new String(new char[2000]);
    String str2001 = new String(new char[2001]);

    @Before
    public void setUp() throws Exception {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(str1, str1));
        recipe = new Recipe(str1, str1, ingredients);
        review = new Review(recipe, str1);
    }

    @After
    public void tearDown() throws Exception {
        recipe = null;
        review = null;
    }

    @Test
    public void setTextTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            review.setText(str1);
            review.setText(str2000);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            review.setText(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            review.setText(str2001);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

}
