public class UnionFind {

    public int bag[];

    UnionFind(int n) {
        bag = new int[n];
        for (int i = 0; i < n; i++) {
            bag[i] = i;
        }
    }

    boolean isSameBag(int a, int b) {
        return bag[a] == bag[b];
    }

    void mergeBags(int a, int b) {
        for (int i = 0; i < bag.length; i++) {
            if (bag[i] == bag[b]) {
                bag[i] = bag[a];
            }
        }
    }
}
