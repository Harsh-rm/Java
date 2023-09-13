import java.util.*;

class UnionFindAlgorithm {

    int[] uf;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        if (n == 0) return 0;
        
        int total = 0;

        uf = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            uf[i] = i;
        }

        List<int[]> edges = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            edges.add(new int[] {0, i, wells[i - 1]});
        }
        for (int[] pipe: pipes) {
            edges.add(pipe);
        }
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            int px = find(x);
            int py = find(y);

            if (px != py) {
                total += edge[2];
                uf[px] = py;
            }
        }

        return total;
    }

    private int find(int x) {
        if (uf[x] != x) {
            // updating the parent in the union find array with the recursive call
            uf[x] = find(uf[x]);
        }
        return uf[x];
        // if (uf[x] == x) {
        //     return x;
        // }
        // return find(uf[x]);
    }
}