package participants;

public class Healer extends Participant {

    public Healer(String name, int[] coordinates, CharacterMediator mediator) {
        super(name, coordinates, mediator);
    }

    public void heal() {
        System.out.println(getName() + " healing");
        getMediator().changeHP(100, 10, this);
    }
}
