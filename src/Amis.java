/**
 * PROF VERSION WITH IMPROVEMENTS
 */

import java.util.Arrays;

public class Amis {
    private int[] amis;
    public Amis(int nb) {
        this.amis = new int[nb];
        for (int i = 0; i < nb; i++) {
            amis[i] = i;
        }
    }

    public int find(int h) {
        int representative = h;
        while (representative != amis[representative]) {
            representative = amis[representative];
        }
        int a = h;
        while (a != amis[a]) {
            int temp = a;
            a = amis[a];
            amis[temp] = representative;
        }
        return representative;
    }

    public void union(int h1, int h2) {
        int rep1 = find(h1);
        int rep2 = find(h2);
        amis[rep1] = rep2;
    }

    public void isolate(int h){
        int repH = this.amis[h];
        //System.out.println(repH);
        int newRep = -1;
        for (int i = 0; i < this.amis.length; i++) {
            int rep = this.amis[i];
            if(rep == repH && i != repH){
                if(newRep == -1){
                    newRep = i;
                }
                this.amis[i] = newRep;
            }
        }
        this.amis[h] = h;
    }

    public void add(int ami){
        int taille = this.amis.length;
        int[] tmp = new int[taille+1];
        System.arraycopy(this.amis, 0, tmp, 0, this.amis.length);
        tmp[taille] = ami;
        this.amis = tmp;
    }

    @Override
    public String toString() {
        return "Amis : " + Arrays.toString(amis);
    }
}
