import subClasses.PrenomStat;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
class FriendshipListBigDataTest {
    @Test
    void test() {
        Random r = new Random();
        final int proportion = r.nextInt(10)+5;
        HashMap<String, PrenomStat> map = importData();
        final long startTime = System.currentTimeMillis();
        FriendshipList<PrenomStat> f = new FriendshipList<>(new LinkedList<>(map.values()));
        int nbOpMake = makeFriends(f, proportion);
        int nbOpIsolate = isolateFriends(f, proportion);
        int nbOpAdd = addFriends(f);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time for bigData test : " + (endTime - startTime) + " ms");
        System.out.println("NbOp for makeFriends : " + nbOpMake + ", NbOp for isolate : " + nbOpIsolate +
                ", NbOp for add : " + nbOpAdd + ", total : " + (nbOpMake + nbOpIsolate + nbOpAdd));
    }

    private int addFriends(FriendshipList<PrenomStat> f) {
        f.addInhabitant(new PrenomStat("ZORGLUB", 2, 56));
        f.addInhabitant(new PrenomStat("XANTHIA", 1, 31));
        f.addInhabitant(new PrenomStat("GRYFFINDOR", 1, 12));
        f.addInhabitant(new PrenomStat("QUAZAR", 2, 28));
        f.addInhabitant(new PrenomStat("ZYLOPHONE", 2, 19));
        f.addInhabitant(new PrenomStat("FLOOBERT", 2, 47));
        f.addInhabitant(new PrenomStat("WUMBLEDORE", 1, 10));
        f.addInhabitant(new PrenomStat("KLINGON", 1, 35));
        f.addInhabitant(new PrenomStat("HUMDINGER", 2, 440));
        f.addInhabitant(new PrenomStat("BLIBBER", 1, 21));
        f.addInhabitant(new PrenomStat("ZORKUL", 2, 18));
        f.addInhabitant(new PrenomStat("FLIBBERTIGIBBET", 1, 9));
        f.addInhabitant(new PrenomStat("QWERTY", 2, 30));
        f.addInhabitant(new PrenomStat("YXELLE", 2, 50));
        f.addInhabitant(new PrenomStat("PLUMBUM", 1, 14));
        f.addInhabitant(new PrenomStat("QUANDARY", 1, 25));
        f.addInhabitant(new PrenomStat("WIBBLYWOBBLER", 1, 15));
        f.addInhabitant(new PrenomStat("BLOBBY", 2, 33));
        f.addInhabitant(new PrenomStat("ZIGZAG", 2, 44));
        f.addInhabitant(new PrenomStat("KALAMAZOO", 1, 11));
        return 20;
    }

    private int isolateFriends(FriendshipList<PrenomStat> f, int percentage) {
        int nbOp = 0;
        Random r = new Random();
        int i = r.nextInt(10);
        for(PrenomStat p : f.toList().get(0)){
            i++;
            if(i%percentage == 0){
                f.isolate(p);
                nbOp++;
            }
        }
        return nbOp;
    }

    private int makeFriends(FriendshipList<PrenomStat> f, int percentage) {
        int nbOp = 0;
        Random r = new Random();
        int i = r.nextInt(10);
        PrenomStat pred = null;
        for(PrenomStat p : f.toList().get(0)){
            i++;
            if(i%percentage == 0){
                f.union(p, pred);
                nbOp++;
            }
            pred = p;
        }
        return nbOp;
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