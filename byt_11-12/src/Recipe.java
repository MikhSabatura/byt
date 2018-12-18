import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name; //!empty && <= 50 symbols
    private String instructions; //!empty && <= 500 symbols
    private List<Ingredient> ingredients; //!empty && <= 40 ingredients

    private List<Rating> ratings;
    private List<Review> reviews;


    public Recipe(String name, String instructions, List<Ingredient> ingredients) {
        setName(name);
        setInstructions(instructions);
        setIngredients(ingredients);
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    /**
     * Adds this recipe to the passed user's cooked recipe list
     *
     * @param user user whose list this recipe will be added to
     */
    public void markAsCooked(User user) {
        user.addCookedRecipe(this);
    }


    /**
     * Gets user input and adds it to the list of ratings
     */
    public void rate() {
        Rating rating = null;
        /*
         * get input from the user
         * */
        ratings.add(rating);
    }

    /**
     * Gets user input and adds it to the list of reviews
     */
    public void writeReview() {
        Review review = null;
        /*
         * get input from the user
         * */
        reviews.add(review);
    }

    /**
     * Gets an average of all ratings of the recipe.
     *
     * @return avg of all ratings of the recipe
     */
    public double getRating() {
        return ratings.stream()
                .mapToDouble(Rating::getAssessment)
                .average()
                .getAsDouble();
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        if (instructions.length() == 0 || instructions.length() > 500) {
            throw new IllegalArgumentException("Instructions should be <= 500 symbols and not empty");
        }
        this.instructions = instructions;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        if (ingredients.isEmpty() || ingredients.size() > 40) {
            throw new IllegalArgumentException("There should be <= 40 ingredients and not empty");
        }
        this.ingredients = ingredients;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
