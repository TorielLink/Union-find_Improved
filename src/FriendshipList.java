import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FriendshipList<T> {
    private final LinkedList<Inhabitant<T>> friends;

    protected static class Inhabitant<T> {
        private final T name;
        private T representative;

        public Inhabitant(T name) {
            this.name = name;
            this.representative = name;
        }

        public void setRepresentative(T representative) {
            this.representative = representative;
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
        this.friends = new LinkedList<>();
    }

    public FriendshipList(List<T> names) {
        this.friends = new LinkedList<>();
        for (T name : names) {
            this.addInhabitant(name);
        }
    }

    private Inhabitant<T> getInhabitant(T name) {
        for (Inhabitant<T> friend : friends) {
            if(friend.name == name){
                return friend;
            }
        }
        return null;
    }

    public T find(T name) {
        Inhabitant<T> representative = getInhabitant(name);
        int i = friends.indexOf(representative);

        while (!Objects.requireNonNull(representative).name.equals(friends.get(i).name)) {
            System.out.println(friends.get(i).name);
            representative = friends.get(i);
            i = friends.indexOf(representative);
        }

        Inhabitant<T> representative2 = new Inhabitant<>(name);
        int j = friends.indexOf(representative2);
        while (!representative2.name.equals(friends.get(j).name)) {
            Inhabitant<T> tmp = representative2;
            representative2 = friends.get(j);
            friends.set(friends.indexOf(tmp), representative2);
            j = friends.indexOf(representative2);
        }
        return representative.representative;
    }

    public void union(T name1, T name2) {
        //TODO : mettre à jour le représentant de chacun des représentés du représentant précédant
        getInhabitant(find(name1)).setRepresentant(find(name2));
    }

    public void isolate(T name) { //TODO : à essayer
        Inhabitant<T> isolatePerson = getInhabitant(name);
        T newRepresentative = null;

        for (Inhabitant<T> friend : friends) {
            if (Objects.requireNonNull(isolatePerson).name.equals(friend.representative)
                    && !isolatePerson.name.equals(friend.name)) {
                if (newRepresentative == null) {
                    newRepresentative = friend.name;
                }
                friend.setRepresentative(newRepresentative);
                friends.set(friends.indexOf(friend), friend);
            }
        }
        Objects.requireNonNull(isolatePerson).setRepresentative(isolatePerson.name);
    }

    public void addInhabitant(T newPerson) {
        friends.add(new Inhabitant<>(newPerson));
    }

    @Override
    public String toString() {
        boolean first = true;
        StringBuilder sb = new StringBuilder("[");
        for (Inhabitant<T> friend : friends) {
            if(!first) sb.append(", ");
            else first = false;
            sb.append(friend.name).append(" -> ").append(friend.representative);
        }
        sb.append("]");return sb.toString();
    }
}
