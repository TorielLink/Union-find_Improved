import SubClasses.PrenomStat;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.rmi.NotBoundException;
import java.util.*;
class FriendshipListTestBigData {
    @Test
    void test() {
        final int percentage = 20;
        HashMap<String, PrenomStat> map = importData();
        final long startTime = System.currentTimeMillis();
        FriendshipList<PrenomStat> f = new FriendshipList<>(new LinkedList<>(map.values()));
        int nbOpMake = makeFriends(f, percentage);
        int nbOpIsolate = isolateFriends(f, percentage);
        int nbOpAdd = addFriends(f, percentage);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time for bigData test : " + (endTime - startTime) + " ms");
        System.out.println("NbOp for makeFriends : " + nbOpMake + ", NbOp for isolate : " + nbOpIsolate +
                ", NbOp for Add : " + nbOpAdd + ", total : " + (nbOpMake + nbOpIsolate + nbOpAdd));
    }

    private int addFriends(FriendshipList<PrenomStat> f, int percentage) {
        int nbOp = 0;
        //TODO
        return nbOp;
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