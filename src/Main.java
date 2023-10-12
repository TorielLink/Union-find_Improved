public class Main {
    public static void main(String[] args) {
        Amis a = new Amis(15);
        System.out.println(a.find(6));
        a.union(4, 6);
        a.union(8, 4);
        System.out.println(a.toString());
        a.add(5);
        a.union(9,5);
        System.out.println(a.toString());
        a.isolate(5);
        System.out.println(a.toString());
    }
}
