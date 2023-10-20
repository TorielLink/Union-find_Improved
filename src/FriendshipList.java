import java.util.LinkedList;

public class FriendshipList<T> {
    private final LinkedList<Inhabitant<T>> amis;

    protected static class Inhabitant<T> {
        private static final int INHABITANT_INEXISTANT = -1;
        private T name; // TODO : nom à changer
        private int representant;

        public Inhabitant(T name, int representant) {
            this.name = name;
            this.representant = representant;
        }
        public Inhabitant(T name) {
            this.name = name;
            this.representant = INHABITANT_INEXISTANT;
        }
    }
    public FriendshipList() {
        this.amis = new LinkedList<T>();
    }

    /*public FriendshipList(int inhabitants) {
        this.amis = new LinkedList<T>();
        for (int i = 0; i < inhabitants; i++) {
            this.addInhabitant(new Inhabitant<T>("denis"));
        }
    }*/


    public T find(T person) {
        T representant = person;
        while (!representant.equals(amis[representant])) //TOD0 : redef equals
            representant = amis[representant];
        T other = person;
        while (!other.equals(amis[other])) {
            T tmp = other;
            other = amis[other];
            amis[tmp] = representant;
        }
        return representant;
    }

    public void union(T person1, T person2) {
        T representant1 = find(person1);
        T representant2 = find(person2);
        amis[representant1] = representant2;
    }

    public void isolate(T person) {
        //TODO
    }

    public void addInhabitant(T newPerson) {
        Inhabitant<T> newInhabitant = new Inhabitant<>(newPerson);
        amis.add(newInhabitant);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < amis.size(); i++) {
            sb.append(i).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
