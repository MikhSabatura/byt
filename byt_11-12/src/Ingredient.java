public class Ingredient {

    private String name; // <= 50 symbols
    private String description; // <= 1000 symbols

    public Ingredient(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name should be <= 50 symbols");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 1000) {
            throw new IllegalArgumentException("Description should be <= 1000 symbols");
        }
        this.description = description;
    }
}
