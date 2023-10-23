import java.util.LinkedList;
import java.util.Objects;

public class FriendshipList<T> {
    private final LinkedList<Inhabitant<T>> amis;

    protected static class Inhabitant<T> {
        private final T name; // TODO : nom à changer
        private Inhabitant<T> representant;

        public Inhabitant(T name, Inhabitant<T> representant) {
            this.name = name;
            this.representant = representant;
        }
        public Inhabitant(T name) {
            this.name = name;
            this.representant = null;
        }

        public void setRepresentant(Inhabitant<T> representant) {
            this.representant = representant;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Inhabitant<?> that = (Inhabitant<?>) o;
            return Objects.equals(name, that.name);
        }
    }
    public FriendshipList() {
        this.amis = new LinkedList<>();
    }

    /* public FriendshipList(int inhabitants) {
        this.amis = new LinkedList<>();
        for (int i = 0; i < inhabitants; i++) {
            this.addInhabitant(new Inhabitant<T>("denis"));
        }
    }*/


    public Inhabitant<T> find(T person) { //TODO : à modifier
        Inhabitant<T> representant = new Inhabitant<>(person);
        int i = amis.indexOf(representant);
        while (!representant.name.equals(amis.get(i))) {
            representant = amis.get(i);
            i = amis.indexOf(representant);
        }

        Inhabitant<T> representant2 = new Inhabitant<>(person);
        int j = amis.indexOf(representant2);
        while (!representant2.name.equals(amis.get(j))) {
            Inhabitant<T> tmp = representant2;
            representant2 = amis.get(j);
            amis.set(amis.indexOf(tmp), representant2);
            j = amis.indexOf(representant2);
        }
        return representant;
    }

    public void union(T person1, T person2) {
        Inhabitant<T> representant1 = find(person1);
        representant1.setRepresentant(find(person2));
        amis.set(amis.indexOf(representant1), representant1);
    }

    public void isolate(T person) { //TODO : à essayer
        Inhabitant<T> representantPerson = find(person);
        Inhabitant<T> newRepresentant = new Inhabitant<>(null);

        for (int i = 0; i < amis.size(); i++) {
            Inhabitant<T> representant = amis.get(i);
            if (representant.equals(representantPerson) && i != amis.indexOf(representantPerson)) {
                if (newRepresentant.representant == null) {
                    newRepresentant.setRepresentant(amis.get(i).representant);
                }
                amis.set(i, newRepresentant);
            }
        }
        amis.set(amis.indexOf(representantPerson.representant), null);
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
