import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FriendshipList<T> {
    private final LinkedList<Inhabitant<T>> amis;

    protected static class Inhabitant<T> {
        private final T name;
        private T representant;

        public Inhabitant(T name) {
            this.name = name;
            this.representant = name;
        }

        public void setRepresentant(T representant) {
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

    public FriendshipList(List<T> names) {
        this.amis = new LinkedList<>();
        for (T name : names) {
            this.addInhabitant(name);
        }
    }

    private Inhabitant<T> getInhabitant(T name) {
        for (Inhabitant<T> ami : amis) {
            if(ami.name == name){
                return ami;
            }
        }
        return null;
    }

    public T find(T name) {
        Inhabitant<T> representant = getInhabitant(name);
        int i = amis.indexOf(representant);
        while (!representant.name.equals(amis.get(i).name)) {
            System.out.println(amis.get(i).name);
            representant = amis.get(i);
            i = amis.indexOf(representant);
        }

        Inhabitant<T> representant2 = new Inhabitant<>(name);
        int j = amis.indexOf(representant2);
        while (!representant2.name.equals(amis.get(j).name)) {
            Inhabitant<T> tmp = representant2;
            representant2 = amis.get(j);
            amis.set(amis.indexOf(tmp), representant2);
            j = amis.indexOf(representant2);
        }
        return representant.representant;
    }

    public void union(T name1, T name2) {
        //TODO : mettre à jour le représentant de chacun des représentés du représentant précédant
        getInhabitant(find(name1)).setRepresentant(find(name2));
    }

    public void isolate(T name) { //TODO : à essayer
        Inhabitant<T> isolatePerson = getInhabitant(name);
        T newRepresentant = null;

        for (Inhabitant<T> ami : amis) {
            if (isolatePerson.name.equals(ami.representant)
                    && !isolatePerson.name.equals(ami.name)) {
                if (newRepresentant == null) {
                    newRepresentant = ami.name;
                }
                ami.setRepresentant(newRepresentant);
                amis.set(amis.indexOf(ami), ami);
            }
        }
        isolatePerson.setRepresentant(isolatePerson.name);
    }

    public void addInhabitant(T newPerson) {
        amis.add(new Inhabitant<>(newPerson));
    }

    @Override
    public String toString() {
        boolean first = true;
        StringBuilder sb = new StringBuilder("[");
        for (Inhabitant<T> ami : amis) {
            if(!first)
                sb.append(", ");
            else first = false;
            sb.append(ami.name).append(" -> ").append(ami.representant);
        }
        sb.append("]");return sb.toString();
    }
}
