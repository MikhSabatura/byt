// The Person class has multiple clients, but all of the clients are in 
// one file for convenience.  Imagine them as non-test methods in separate 
// client classes.

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class PersonClientsTest {

    @Test
    public void testClients() throws IOException {
        Person bobSmith = new Person("Smith", "Bob", null);
        Person jennyJJones = new Person("Jones", "Jenny", "J");

        assertEquals("Bob Smith", bobSmith.toString());
        assertEquals("Jenny J Jones", jennyJJones.toString());

        assertEquals("Smith, Bob", bobSmith.formattedName());
        assertEquals("Jones, Jenny J", jennyJJones.formattedName());
    }
}
