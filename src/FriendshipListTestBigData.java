import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
class FriendshipListTestBigData {
    @Test
    void test() {
        HashMap<String, PrenomStat> map = importData();
        final long startTime = System.currentTimeMillis();
        //FriendshipList<String> f = new FriendshipList<>(new LinkedList<>(map.keySet()));
        FriendshipList<PrenomStat> f = new FriendshipList<>(new LinkedList<>(map.values()));
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }
    private HashMap<String, PrenomStat> importData(){
        HashMap<String, PrenomStat> map = new HashMap<>();
        try {
            FileReader fr = new FileReader("prenoms.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(";");
                map.put(elems[0], new PrenomStat(elems[0], Integer.parseInt(elems[1]), Integer.parseInt(elems[2])));
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file");
        }
        return map;
    }
}