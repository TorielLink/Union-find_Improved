import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic tests
 */
public class FriendshipListTest {
    public static final int INDEX_NAMES = 0;
    public static final int INDEX_REPRESENTATIVES = 1;

    /**
     * Test for Integer type
     */
    @Test
    void testInteger() {
        FriendshipList<Integer> f = new FriendshipList<>();
        List<Integer> numbers = Arrays.asList(205,404,42,-1);

        f.addInhabitants(numbers);
        assertArrayEquals(numbers.toArray(), f.toList().get(INDEX_NAMES).toArray());
        assertArrayEquals(numbers.toArray(), f.toList().get(INDEX_REPRESENTATIVES).toArray());

        assertEquals((Integer) 205, f.find(205));

        f.union(404, -1);
        assertArrayEquals(Arrays.asList(205,-1,42,-1).toArray(), f.toList().get(INDEX_REPRESENTATIVES).toArray());
        assertEquals((Integer) (-1), f.find(404));
    }

    /**
     * Test for String type
     */
    @Test
    void testString() {
        ArrayList<String> contenu = new ArrayList<>(Arrays.asList("Emmanuel", "Denis", "Lisa", "Nathalie", "Guillaume",
                "Florence"));
        FriendshipList<String> f = new FriendshipList<>(contenu);
        assertArrayEquals(contenu.toArray(), f.toList().get(INDEX_NAMES).toArray());
        assertArrayEquals(contenu.toArray(), f.toList().get(INDEX_REPRESENTATIVES).toArray());

        f.union("Emmanuel", "Nathalie");
        f.union("Denis", "Guillaume");
        assertArrayEquals(Arrays.asList("Nathalie", "Guillaume", "Lisa", "Nathalie", "Guillaume", "Florence").toArray(),
                f.toList().get(1).toArray());
        f.union("Denis", "Emmanuel");
        assertArrayEquals(Arrays.asList("Nathalie", "Nathalie", "Lisa", "Nathalie", "Nathalie", "Florence").toArray(),
                f.toList().get(1).toArray());

        assertEquals("Lisa", f.find("Lisa"));
        assertEquals("Nathalie", f.find("Denis"));

        assertThrows(RuntimeException.class, () -> f.find("Jamy"));

        f.isolate("Nathalie");
        assertArrayEquals(Arrays.asList("Emmanuel", "Emmanuel", "Lisa", "Nathalie", "Emmanuel", "Florence").toArray(),
                f.toList().get(1).toArray());

        //to check if the list have not changed since the begging of the test
        assertArrayEquals(contenu.toArray(), f.toList().get(0).toArray());
    }
}