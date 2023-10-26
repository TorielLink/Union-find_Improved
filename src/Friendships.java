
// TODO : test de generalisation a un type T, a finir

import java.util.Arrays;
import java.util.HashMap;

public class Friendships<T> {
    private final HashMap<Integer, T> amis;
    public Friendships(int nb){
        this.amis = new HashMap<>();
        for (int i = 0; i < nb; i++) {
            this.amis.put(i, null);
        }
    }
    /*
    public int find(T h) {
        int representant = amis.get(h);
        while (representant != amis[representant]) {
            representant = amis[representant];
        }
        int a = h;
        while (a != amis[a]) {
            int temp = a;
            a = amis[a];
            amis[temp] = representant;
        }
        return representant;
    }

    public void union(T h1, T h2) {
        int representant1 = find(h1);
        int representant2 = find(h2);
        amis.put(representant1, h2);
    }

    public void isolate(T h){
        //TODO voir comment ameliorer (si possible)
        int repH = this.amis[h];
        //System.out.println(repH);
        int newRep = -1;
        for (int i = 0; i < this.amis.size(); i++) {
            int rep = this.amis[i];
            if(rep == repH && i != repH){
                if(newRep == -1){
                    newRep = i;
                }
                this.amis[i] = newRep;
            }
        }
        this.amis[h] = h;
    }*/

    public void add(){
        this.amis.put(amis.size(), null);
    }

    @Override
    public String toString() {
        return "Amis : " + amis.toString();
    }
}

