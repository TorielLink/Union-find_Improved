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

    /**
     * Function to find the representative of an inhabitant
     * @param h the inhabitant
     * @return the representative
     */
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

    /**
     * Function to make two people friends
     * @param h1 the first inhabitant
     * @param h2 the second inhabitant
     */
    public void union(int h1, int h2) {
        int rep1 = find(h1);
        int rep2 = find(h2);
        amis[rep1] = rep2;
    }

    /**
     * Function to isolate a friend
     * @param h the friend to isolate
     */
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

    /**
     * Function to add an inhabitant to the list
     * @param ami the inhabitant to add
     */
    public void add(int ami){
        int taille = this.amis.length;
        int[] tmp = new int[taille+1];
        System.arraycopy(this.amis, 0, tmp, 0, this.amis.length);
        tmp[taille] = ami;
        this.amis = tmp;
    }

    /**
     * Function exporting the data as a string
     * @return the string
     */
    @Override
    public String toString() {
        return "Amis : " + Arrays.toString(amis);
    }
}
