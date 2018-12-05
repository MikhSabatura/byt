public class Person {
    public String last;

    public String first;

    public String middle;

    public Person(String last, String first, String middle) {
        this.last = last;
        this.first = first;
        this.middle = middle;
    }

    public String formattedName() {
        return last + ", " + first + (middle == null ? "" : " " + middle);
    }

    @Override
    public String toString() {
        return first + " " + (middle == null ? "" : middle + " ") + last;
    }
}