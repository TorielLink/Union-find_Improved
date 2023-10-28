import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
class FriendshipListTestBigData {
    @Test
    void test() {
        List<PrenomStat> list = importData();
        FriendshipList<PrenomStat> f = new FriendshipList<>(list);
    }
    private List<PrenomStat> importData(){
        LinkedList<PrenomStat> list = new LinkedList<>();
        try {
            FileReader fr = new FileReader("prenomsBoys.csv");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(";");
                list.add(new PrenomStat(elems[0], Integer.parseInt(elems[1]), Integer.parseInt(elems[2])));
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: prenoms.csv");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return list;
    }
}