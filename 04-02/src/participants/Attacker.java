package participants;

public class Attacker extends Participant {

    public Attacker(String name, int[] coordinates, CharacterMediator mediator) {
        super(name, coordinates, mediator);
    }

    public void heavyAttack() {
        System.out.println(getName() + " performing heavy attack");
        getMediator().changeHP(100, -30, this);
    }

    public void lightAttack() {
        System.out.println(getName() + " performing light attack");
        getMediator().changeHP(100, -15, this);
    }
}
