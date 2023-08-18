public class Main {
    public static void main(String[] args) {
        Avl avl = new Avl();
        for (int i = 0; i < 1000000; i++) {
            avl.add(i);
        }
    }
}