public class Review {

    private Recipe recipe;
    private String text; //!empty && <= 2000 symbols

    public Review(Recipe recipe, String text) {
        this.recipe = recipe;
        this.text = text;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() == 0 || text.length() > 2000) {
            throw new IllegalArgumentException("Text of the review should be <= 2000 symbols and not empty");
        }
        this.text = text;
    }
}
