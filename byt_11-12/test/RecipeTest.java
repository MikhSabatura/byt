import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeTest {

    Recipe recipe;

    List<Ingredient> ingredients0;
    List<Ingredient> ingredients1;
    List<Ingredient> ingredients40;
    List<Ingredient> ingredients100;

    String str0 = new String(new char[0]);
    String str1 = new String(new char[1]);
    String str50 = new String(new char[50]);
    String str51 = new String(new char[51]);
    String str500 = new String(new char[500]);
    String str501 = new String(new char[501]);

    @Before
    public void setUp() throws Exception {
        ingredients0 = new ArrayList<>();
        ingredients1 = Stream.generate(() -> new Ingredient(str1, str1))
                .limit(1)
                .collect(Collectors.toList());
        ingredients40 = Stream.generate(() -> new Ingredient(str1, str1))
                .limit(40)
                .collect(Collectors.toList());
        ingredients100 = Stream.generate(() -> new Ingredient(str1, str1))
                .limit(100)
                .collect(Collectors.toList());

        recipe = new Recipe(str1, str1, ingredients1);
    }

    @After
    public void tearDown() throws Exception {
        recipe = null;
        ingredients0 = null;
        ingredients1 = null;
        ingredients40 = null;
        ingredients100 = null;
    }

    @Test
    public void setNameTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            recipe.setName(str1);
            recipe.setName(str50);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            recipe.setName(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            recipe.setName(str51);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setInstructionsTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            recipe.setInstructions(str1);
            recipe.setInstructions(str50);
            recipe.setInstructions(str500);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            recipe.setInstructions(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            recipe.setInstructions(str501);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setIngredientsTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            recipe.setIngredients(ingredients1);
            recipe.setIngredients(ingredients40);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            recipe.setIngredients(ingredients0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            recipe.setIngredients(ingredients100);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }
}
