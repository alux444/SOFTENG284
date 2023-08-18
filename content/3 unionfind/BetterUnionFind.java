public class BetterUnionFind {
    public int parent[];
    public int height[];

    BetterUnionFind(int n) {
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            height[i] = 0;
        }
    }

    public int findNum(int n) {
        int x = n;
        while (parent[n] != -1) {
            x = parent[n];
        }
        return x;
    }

    public boolean isSame(int a, int b) {
        return findNum(a) == findNum(b);
    }

    public void mergeBags(int a, int b) {
        a = findNum(a);
        b = findNum(b);

        if (height[a] > height[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
            if (height[a] == height[b]) {
                height[a]++;
            }
        }

    }
}
