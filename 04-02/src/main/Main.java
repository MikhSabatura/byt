package main;

import participants.Attacker;
import participants.Participant;
import participants.CharacterMediator;
import participants.Healer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

//    implementing mediator pattern
    public static void main(String[] args) {
        CharacterMediator mediator = new CharacterMediator();
        List<Participant> participants = generateParticipants(100, mediator);

        Random random = new Random();
        while (participants.size() > 5) {
            Participant performer = participants.get(random.nextInt(participants.size()));
            if (performer instanceof Healer) {
                Healer healer = (Healer) performer;
                healer.heal();
            } else {
                Attacker attacker = (Attacker) performer;
                if (random.nextInt(2) == 0) {
                    attacker.lightAttack();
                } else {
                    attacker.heavyAttack();
                }
            }
            participants = participants.stream()
                    .filter(Participant::isAlive)
                    .collect(Collectors.toList());
        }

    }

    public static List<Participant> generateParticipants(int amount, CharacterMediator mediator) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }
        List<Participant> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            Participant c = null;
            if (random.nextInt(6) > 4) {
                c = new Healer("healer " + (i + 1), new int[]{i, i + random.nextInt(3)}, mediator);
            } else {
                c = new Attacker("attacker " + (i + 1), new int[]{i, i + random.nextInt(3)}, mediator);
            }
            result.add(c);
            mediator.addCharacter(c);
        }
        return result;
    }
}
