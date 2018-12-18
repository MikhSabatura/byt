import java.util.ArrayList;
import java.util.List;

public class User {

    private String name; // <= 50
    private String surname; // <= 50

    private String email; // <= 50
    private String phoneNumber; // <= 30

    private List<User> friends;
    private List<Recipe> cookedRecipes;

    public User(String name, String surname, String email, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.friends = new ArrayList<>();
        this.cookedRecipes = new ArrayList<>();
    }

    /**
     * Logs the user into the system.
     *
     * @param login    login of the user
     * @param password password of the user
     * @return true in case the login is successful, false otherwise
     */
    public boolean logIn(String login, String password) {
        return true;
    }

    /**
     * Shares the cooking list on a chosen media platform.
     */
    public void shareCookingList() {
        /*
         * share cooling list on chosen media platform
         * */
    }

    /**
     * Displays cooking lists of the user's friends.
     */
    public void firendsCookingLists() {
        /*
         * display cooking lists of friends
         * */
    }

    /**
     * Gets a suggested recipe from the user and adds it to the list of available recipes.
     */
    public void addRecipe() {
        /*
         * Get input from the user
         * Add the suggested recipe to the list of available recipes
         * */
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    public List<User> getFriends() {
        return friends;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.length() > 50) {
            throw new IllegalArgumentException("Surname should be <= 50 symbols");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.length() > 50) {
            throw new IllegalArgumentException("Email should be <= 50 symbols");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > 30) {
            throw new IllegalArgumentException("Phone number should be <= 30 symbols");
        }
        this.phoneNumber = phoneNumber;
    }

    public void addCookedRecipe(Recipe recipe) {
        cookedRecipes.add(recipe);
    }

    public void removeCookedRecipe(Recipe recipe) {
        cookedRecipes.remove(recipe);
    }
}
