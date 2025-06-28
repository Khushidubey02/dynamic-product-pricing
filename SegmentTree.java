import java.util.*;

class SegmentTree {
    int[] tree, arr;
    int n;

    SegmentTree(int[] prices) {
        n = prices.length;
        arr = prices;
        tree = new int[4 * n];
        build(0, 0, n - 1);
    }

    void build(int node, int l, int r) {
        if (l == r) tree[node] = arr[l];
        else {
            int mid = (l + r) / 2;
            build(2 * node + 1, l, mid);
            build(2 * node + 2, mid + 1, r);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    int queryMin(int node, int l, int r, int ql, int qr) {
        if (qr < l || ql > r) return Integer.MAX_VALUE;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        return Math.min(queryMin(2 * node + 1, l, mid, ql, qr),
                        queryMin(2 * node + 2, mid + 1, r, ql, qr));
    }

    public static void main(String[] args) {
        int[] prices = {120, 80, 200, 90, 150};
        SegmentTree st = new SegmentTree(prices);
        System.out.println("Min price in [1,3]: " + st.queryMin(0, 0, 4, 1, 3));
    }
}
