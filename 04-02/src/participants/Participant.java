package participants;

import java.util.Arrays;

public abstract class Participant {

    private final IMediator mediator;
    private final String name;
    private int[] coordinates;
    private int healthPoints;

    public Participant(String name, int[] coordinates, IMediator mediator) {
        this.name = name;
        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
        this.healthPoints = 100;
        this.mediator = mediator;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public IMediator getMediator() {
        return mediator;
    }

    public String getName() {
        return name;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        System.out.print(name + " ");
        if (healthPoints > 100) {
            System.out.println();
            this.healthPoints = 100;
            return;
        }
        if (healthPoints <= 0) {
            this.healthPoints = 0;
            System.out.println("died");
            return;
        }
        System.out.println("health changed from " + this.healthPoints + " to " + healthPoints);
        this.healthPoints = healthPoints;
    }

}
