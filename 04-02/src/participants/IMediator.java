package participants;

public interface IMediator {
    void changeHP(int radius, int hp, Participant invoker);

    double calculateDistance(Participant a, Participant b);

    void addCharacter(Participant c);

    void removeCharacter(Participant c);
}
