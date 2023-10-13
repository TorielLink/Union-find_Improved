
// TODO : test de generalisation a un type T, a finir

public class Friendships<T> {
    private T[] residents;
    public Friendships(int nb){
        this.residents = (T[]) new Object[nb];
        for (int i = 0; i < nb; i++) {
            this.residents[i] = null;
        }
    }
    public boolean union(T resident1, T resident2){
        return false;
        //TODO
    }
    public T find(T resident) {
        return null;
        //TODO
    }
}
