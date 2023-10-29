/**
 * PERSONAL VERSION
 */

import java.util.*;

/**
 * Union-Find data structure with a generic type
 * @param <T> any type
 */
public class FriendshipList<T extends Comparable<T>> {
    private final LinkedList<Inhabitant<T>> friends;
    //TODO éventuellement mettre en HashMap ?
    public static final boolean TEST_EXISTS = true;

    private static class Inhabitant<T extends Comparable<T>> {
        private final T name;
        private T representative;

        public Inhabitant(T name) {
            if(name == null){
                throw new IllegalArgumentException("incorrect name");
            }
            this.name = name;
            this.representative = name;
        }

        public void setRepresentative(T representative) {
            if(representative == null){
                throw new IllegalArgumentException("incorrect name");
            }
            this.representative = representative;
        }
    }

    public FriendshipList() {
        this.friends = new LinkedList<>();
    }

    public FriendshipList(List<T> names) {
        if(names == null){
            throw new IllegalArgumentException("names list not instanced");
        }
        this.friends = new LinkedList<>();
        for (T name : names) {
            this.addInhabitant(name);
        }
    }

    private Inhabitant<T> getInhabitant(T name) {
        if(name == null){
        throw new IllegalArgumentException("incorrect name");
        }
        if(TEST_EXISTS){
            if(!this.containsName(name)){
                throw new IllegalArgumentException("this person does not belong in the list");
            }
        }
        for (Inhabitant<T> friend : friends) {
            if(friend.name.compareTo(name) == 0){
                return friend;
            }
        }
        return null;
    }

    public T find(T name) {
        if(name == null){
            throw new IllegalArgumentException("incorrect name");
        }
        if(TEST_EXISTS){
            if(!this.containsName(name)){
                throw new IllegalArgumentException("this person does not belong in the list");
            }
        }
        Inhabitant<T> representative = getInhabitant(name);
        int i = friends.indexOf(representative);

        while (!(Objects.requireNonNull(representative).name.compareTo(friends.get(i).name) == 0)) {
            System.out.println(friends.get(i).name);
            representative = friends.get(i);
            i = friends.indexOf(representative);
        }//TODO inutile ? cf. coverage

        Inhabitant<T> representative2 = getInhabitant(name);
        int j = friends.indexOf(representative2);
        while (!(representative2.name.compareTo(friends.get(j).name) == 0)) {
            Inhabitant<T> tmp = representative2;
            representative2 = friends.get(j);
            friends.set(friends.indexOf(tmp), representative2);
            j = friends.indexOf(representative2);
        }//TODO inutile ? cf coverage
        return representative.representative;
    }

    private boolean containsName(T name) {
        for(Inhabitant<T> friend : friends){
            if(friend.name.equals(name)){
                return true;
            }
        }
        return false;
    }

    public void union(T individual, T representativeTemp) {
        if(TEST_EXISTS){
            if(!this.containsName(individual) || !this.containsName(representativeTemp)){
                throw new IllegalArgumentException("this person does not belong in the list");
            }
        }
        if(individual == null || representativeTemp == null){
            throw new IllegalArgumentException("incorrect name");
        }
        T representant1 = find(individual);
        T representant2 = find(representativeTemp);
        Objects.requireNonNull(getInhabitant(representant1)).setRepresentative(representant2);
        for (Inhabitant<T> friend : friends) {
            if (friend.representative.compareTo(representant1) == 0){
                friend.setRepresentative(representant2);
            }
        }
    }

    public void isolate(T name) {
        if(name == null){
            throw new IllegalArgumentException("incorrect name");
        }
        if(TEST_EXISTS){
            if(!this.containsName(name)){
                throw new IllegalArgumentException("this person does not belong in the list");
            }
        }
        Inhabitant<T> isolatePerson = getInhabitant(name);
        T newRepresentative = null;

        for (Inhabitant<T> friend : friends) {
            if (Objects.requireNonNull(isolatePerson).name.compareTo(friend.representative) == 0
                    && !(isolatePerson.name.compareTo(friend.name) == 0)) {
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
        if(newPerson == null){
            throw new IllegalArgumentException("incorrect name");
        }
        if(TEST_EXISTS){
            if(this.containsName(newPerson)){
                throw new IllegalArgumentException("this person is already in the list");
            }
        }
        friends.add(new Inhabitant<>(newPerson));
    }

    public void addInhabitants(List<T> listPerson) {
        if(listPerson == null){
            throw new IllegalArgumentException("list of persons empty");
        }
        for (T person : listPerson) {
            this.addInhabitant(person);
        }
    }

    public List<List<T>> toList(){
        List<List<T>> returnList = new ArrayList<>(2);
        List<T> names = new LinkedList<>();
        List<T> representatives = new LinkedList<>();
        for(Inhabitant<T> friend : friends){
            names.add(friend.name);
            representatives.add(friend.representative);
        }
        returnList.add(names);
        returnList.add(representatives);
        return returnList;
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
