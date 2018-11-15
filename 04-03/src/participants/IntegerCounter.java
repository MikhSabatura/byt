package participants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//represents a list with 10000 integers
public class IntegerCounter {

    private List<Integer> integers;
    private static final int SIZE = 10000;

// initializes the integer list
    public IntegerCounter() {
        integers = new ArrayList<>(SIZE);
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            integers.add(i + random.nextInt(SIZE));
        }
    }

//    adds up all 1000 integers and returns a result
    public int calculateResult() {
        return integers.stream().mapToInt(i -> i).sum();
    }
}
