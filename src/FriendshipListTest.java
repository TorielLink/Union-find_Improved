import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FriendshipListTest {
    @Test
    public void test() {
        //TODO : faire des tests automatisés
            ArrayList<String> contenu = new ArrayList<>(Arrays.asList("Emmanuel", "Denis", "Lisa", "Nathalie", "Guillaume", "Florence"));
            FriendshipList<String> f = new FriendshipList<>(contenu);
            //assertArrayEquals(f, ...);
            f.union("Emmanuel", "Nathalie");

            f.union("Denis", "Guillaume");

            f.union("Denis", "Emmanuel");
            System.out.println(f);

            assertEquals(f.find("Lisa"), "Lisa");
            assertEquals(f.find("Denis"), "Nathalie");

            f.isolate("Nathalie");
            System.out.println(f);
    }
}