/**
 * DEPRECATED : use rather FriendshipListTest.java
 */

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /*public static void main(String[] args) {
        Amis a = new Amis(15);
        System.out.println(a.find(6));
        a.union(4, 6);
        a.union(8, 4);
        System.out.println(a.toString());
        a.add(5);
        a.union(9,5);
        System.out.println(a.toString());
        a.isolate(5);
        System.out.println(a.toString());
    }*/

    public static void main(String[] args) {
        ArrayList<String> contenu = new ArrayList<>(Arrays.asList("Emmanuel", "Denis", "Lisa", "Nathalie", "Guillaume", "Florence"));
        FriendshipList<String> f = new FriendshipList<>(contenu);
        System.out.println(f);
        f.union("Emmanuel", "Nathalie");
        f.union("Denis", "Guillaume");
        System.out.println(f);
        f.union("Denis", "Emmanuel");
        System.out.println(f);

        System.out.println(f.find("Lisa"));
        System.out.println(f.find("Denis"));

        f.isolate("Nathalie");
        System.out.println(f);
    }
}
