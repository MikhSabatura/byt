import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    User user;

    String str0 = new String(new char[0]);
    String str1 = new String(new char[1]);
    String str30 = new String(new char[30]);
    String str31 = new String(new char[31]);
    String str50 = new String(new char[50]);
    String str51 = new String(new char[51]);

    @Before
    public void setUp() throws Exception {
        user = new User(str1, str1, str1, str1);
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void setNameTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            user.setName(str1);
            user.setName(str50);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            user.setName(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            user.setName(str51);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setSurnameTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            user.setSurname(str1);
            user.setSurname(str50);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            user.setSurname(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            user.setSurname(str51);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setEmailTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            user.setEmail(str1);
            user.setEmail(str50);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            user.setEmail(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            user.setEmail(str51);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setPhoneNumberTest() {
        /*
         * checking if assigning correct values causes an exception:
         * */
        try {
            user.setPhoneNumber(str1);
            user.setPhoneNumber(str30);
        } catch (IllegalArgumentException e) {
            Assert.fail("Should not throw an exception");
        }
        /*
         * checking if assigning incorrect values causes an exception:
         * */
        try {
            user.setPhoneNumber(str0);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            user.setPhoneNumber(str31);
            Assert.fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
        }
    }
}
