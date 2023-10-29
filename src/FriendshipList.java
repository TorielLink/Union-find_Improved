/**
 * @author Rémi LEMAIRE, Clothilde PROUX
 * An union-find program with two more fonction : isolate et add, and whith a generic type T
 */

import java.util.*;

/**
 * Union-Find data structure with a generic type
 * @param <T> any type
 */
public class FriendshipList<T extends Comparable<T>> {
    /**
     *  Data structure for the "friends" list : a LinkedList <br/>
     *  Could a HashMap be better ?
     */
    private final LinkedList<Inhabitant<T>> friends;
    /**
     * Condition to verify the unit of a friend or not
     * change to false only if the data contains NO duplicates
     */
    public static final boolean TEST_EXISTS = false;

    /**
     * A nested class to represent an Inhabitant : his name and his representative
     * @param <T> any type
     */
    private static class Inhabitant<T extends Comparable<T>> {
        private final T name;
        private T representative;

        /**
         * Constructor
         * @param name the value of the Inhabitant
         */
        public Inhabitant(T name) {
            if(name == null){
                throw new IllegalArgumentException("incorrect name");
            }
            this.name = name;
            this.representative = name;
        }

        /**
         * Setter for the representative : when an inhabitant became friends or is isolated
         * @param representative the new representative
         */
        public void setRepresentative(T representative) {
            if(representative == null){
                throw new IllegalArgumentException("incorrect name");
            }
            this.representative = representative;
        }
    }

    /**
     * Empty constructor
     */
    public FriendshipList() {
        this.friends = new LinkedList<>();
    }

    /**
     * Constructor with a list of names
     * @param names a list of names of inhabitants
     */
    public FriendshipList(List<T> names) {
        if(names == null){
            throw new IllegalArgumentException("names list not instanced");
        }
        this.friends = new LinkedList<>();
        for (T name : names) {
            this.addInhabitant(name);
        }
    }

    /**
     * Function finding inhabitant based on his name
     * @param name the name of the Inhabitant
     * @return the Inhabitant structure
     */
    private Inhabitant<T> getInhabitant(T name) {
        if(name == null)
            throw new IllegalArgumentException("incorrect name");

        if(TEST_EXISTS)
            if(!this.containsName(name))
                throw new IllegalArgumentException("this person does not belong in the list");

        for (Inhabitant<T> friend : friends)
            if(friend.name.compareTo(name) == 0)
                return friend;
        return null;
    }

    /**
     * Function finding the representative of an inhabitant based on his name
     * @param name the name of the inhabitant
     * @return the name of the representative
     */
    public T find(T name) {
        if(name == null)
            throw new IllegalArgumentException("incorrect name");

        if(TEST_EXISTS)
            if(!this.containsName(name))
                throw new IllegalArgumentException("this person does not belong in the list");

        Inhabitant<T> representative = getInhabitant(name);
        int i = friends.indexOf(representative);

        while (!(Objects.requireNonNull(representative).name.compareTo(friends.get(i).name) == 0)) {
            System.out.println(friends.get(i).name);
            representative = friends.get(i);
            i = friends.indexOf(representative);
        }//TODO inutile ? cf. coverage

        Inhabitant<T> representative2 = getInhabitant(name);
        int j = friends.indexOf(representative2);
        while (!(Objects.requireNonNull(representative2).name.compareTo(friends.get(j).name) == 0)) {
            Inhabitant<T> tmp = representative2;
            representative2 = friends.get(j);
            friends.set(friends.indexOf(tmp), representative2);
            j = friends.indexOf(representative2);
        }//TODO inutile ? cf coverage
        return representative.representative;
    }

    /**
     * Function verifying if an inhabitant is contained in the list
     * @param name the name of the inhabitant
     * @return a boolean representing if the inhabitant is in the list
     */
    private boolean containsName(T name) {
        for(Inhabitant<T> friend : friends)
            if(friend.name.compareTo(name) == 0)
                return true;
        return false;
    }

    /**
     * Procedure to make two people friends
     * @param individual the first person
     * @param representativeTemp the second person. <br/>
     *                           If he has no representative, it will be the representative of the first person
     */
    public void union(T individual, T representativeTemp) {
        if(TEST_EXISTS)
            if(!this.containsName(individual) || !this.containsName(representativeTemp))
                throw new IllegalArgumentException("this person does not belong in the list");

        if(individual == null || representativeTemp == null)
            throw new IllegalArgumentException("incorrect name");

        T repIndividual = find(individual);
        T repRepTemp = find(representativeTemp);
        Objects.requireNonNull(getInhabitant(repIndividual)).setRepresentative(repRepTemp);
        for (Inhabitant<T> friend : friends)
            if (friend.representative.compareTo(repIndividual) == 0)
                friend.setRepresentative(repRepTemp);
    }

    /**
     * Function to isolate an inhabitant from his friends
     * @param name the name of the inhabitant to isolate
     */
    public void isolate(T name) {
        if(name == null)
            throw new IllegalArgumentException("incorrect name");

        if(TEST_EXISTS)
            if(!this.containsName(name))
                throw new IllegalArgumentException("this person does not belong in the list");

        Inhabitant<T> isolatePerson = getInhabitant(name);
        T newRepresentative = null;

        for (Inhabitant<T> friend : friends)
            if (Objects.requireNonNull(isolatePerson).name.compareTo(friend.representative) == 0
                    && !(isolatePerson.name.compareTo(friend.name) == 0)) {
                if (newRepresentative == null)
                    newRepresentative = friend.name;

                friend.setRepresentative(newRepresentative);
                friends.set(friends.indexOf(friend), friend);
            }
        Objects.requireNonNull(isolatePerson).setRepresentative(isolatePerson.name);
    }

    /**
     * Add an inhabitant to the friends list
     * @param newPerson the name of the inhabitant to add
     */
    public void addInhabitant(T newPerson) {
        if(newPerson == null)
            throw new IllegalArgumentException("incorrect name");

        if(TEST_EXISTS)
            if(this.containsName(newPerson))
                throw new IllegalArgumentException("this person is already in the list");

        friends.add(new Inhabitant<>(newPerson));
    }

    /**
     * Function to add multiple inhabitants at once
     * @param listPerson the list of inhabitants to add
     */
    public void addInhabitants(List<T> listPerson) {
        if(listPerson == null)
            throw new IllegalArgumentException("list of persons empty");

        for (T person : listPerson)
            this.addInhabitant(person);
    }

    /**
     * Function to export the data contained in "friends" <br/>
     * This returns a list containing two lists : <br/>
     * the first one is the list containing only the names of the inhabitants <br/>
     * and the second one their representatives
     * @return List<List<T>> a list containing the two lists
     */
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

    /**
     * Function to export the data contained in friends in a lisible format
     * @return the String representing "friends"
     */
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
