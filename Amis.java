public class Amis {
    private int[] listeAmis;
    public void initialiser(int n) {
        for (int i = 0; i < n - 1; i++) {
            listeAmis[i] = i;
        }
    }
    
    public int trouverRepresentant(int h) {
        int representant = h;
        while (representant != listeAmis[representant]) {
            representant = listeAmis[representant];
        }
        return representant;
    }

    public void unionAmis(int h1, int h2) {
        int representant1 = trouverRepresentant(h1);
        int representant2 = trouverRepresentant(h2);
        listeAmis[representant1] = representant2;
    }
}
