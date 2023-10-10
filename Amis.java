public class Amis {
    private int[] amis;
    public void initialiser(int n) {
        for (int i = 0; i < n - 1; i++) {
            listeAmis[i] = i;
        }
    }

    public int find(int h) {
        int representant = h;
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

    public void union(int h1, int h2) {
        int representant1 = find(h1);
        int representant2 = find(h2);
        amis[representant1] = representant2;
    }
}
