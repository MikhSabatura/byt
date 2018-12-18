public class Ingredient {

    private String name; // not empty && <= 50 symbols
    private String description; // not empty && <= 1000 symbols

    public Ingredient(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() == 0 || name.length() > 50) {
            throw new IllegalArgumentException("Name should be <= 50 symbols and not empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() == 0 || description.length() > 1000) {
            throw new IllegalArgumentException("Description should be <= 1000 symbols and not empty");
        }
        this.description = description;
    }
}
