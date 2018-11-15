package participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterMediator implements IMediator {

    private List<Participant> participants;

    public CharacterMediator() {
        this.participants = new ArrayList<>();
    }

    @Override
    public void changeHP(int radius, int hp, Participant invoker) {
        participants.stream()
                .filter(c -> calculateDistance(c, invoker) < radius && c != invoker)
                .forEach(c -> c.setHealthPoints(c.getHealthPoints() + hp));
        participants = participants.stream()
                .filter(Participant::isAlive)
                .collect(Collectors.toList());
    }


    @Override
    public double calculateDistance(Participant a, Participant b) {
        double result = 0;
        for (int i = 0; i < a.getCoordinates().length; i++) {
            result += Math.sqrt(Math.pow(a.getCoordinates()[i], 2) - Math.pow(b.getCoordinates()[i], 2));
        }
        return result;
    }

    public void addCharacter(Participant c) {
        this.participants.add(c);
    }

    public void removeCharacter(Participant c) {
        this.participants.remove(c);
    }

}
