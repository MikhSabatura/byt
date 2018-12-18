public class Rating {

    private static final double MAX_ASSESSMENT = 5;
    private static final double MIN_ASSESSMENT = 0;

    private Recipe recipe;
    private double assessment; // >= MIN_ASSESSMENT && <= MAX_ASSESSMENT

    public Rating(Recipe recipe, double assessment) {
        this.recipe = recipe;
        this.assessment = assessment;
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        if (assessment < MIN_ASSESSMENT || assessment > MAX_ASSESSMENT) {
            throw new IllegalArgumentException("Assessment should be between 0 and 5");
        }
        this.assessment = assessment;
    }
}
