import org.junit.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;

public class RatingTest {

    Rating rating;
    static double MAX_ASSESSMENT;
    static double MIN_ASSESSMENT;

    @Before
    public void setUp() throws Exception {
        rating = new Rating(null, 1);
    }

    @After
    public void tearDown() throws Exception {
        rating = null;
    }

    @BeforeClass
    public static void loadBounds() throws Exception {
        Class<Rating> ratingClass = Rating.class;
        Field max = ratingClass.getDeclaredField("MAX_ASSESSMENT");
        Field min = ratingClass.getDeclaredField("MIN_ASSESSMENT");
        max.setAccessible(true);
        min.setAccessible(true);
        MAX_ASSESSMENT = max.getDouble(null);
        MIN_ASSESSMENT = min.getDouble(null);
    }

    @Test
    public void setAssessmentTest() {
        /*
         * checking if assigning correct values causes an exception
         * */
        try {
            for (double i = MIN_ASSESSMENT; i < MAX_ASSESSMENT; i++) {
                rating.setAssessment(i);
            }
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception
         * */
        try {
            rating.setAssessment(MIN_ASSESSMENT - 1);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            rating.setAssessment(MAX_ASSESSMENT + 1);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }
}
