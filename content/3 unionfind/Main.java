public class Main {
    public static void main(String[] args) {
        UnionFind union = new UnionFind(10);
        union.mergeBags(1, 2);
        union.mergeBags(2, 4);
        for (int num : union.bag) {
            System.out.println(num);
        }
    }
}
