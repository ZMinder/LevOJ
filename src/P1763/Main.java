package P1763;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    //一个集合内必然是存在朋友关系将所有人连接起来 这一连串必须都去
    public static final int MAX = 300005;
    public static int[] root = new int[MAX];//并查集的集合的根节点
    public static int[] friend = new int[MAX];//一个集合内的朋友关系数量
    public static int[] size = new int[MAX];//一个集合的人数

    public static int find(int i) {
        ArrayList<Integer> list = new ArrayList<>();
        while (root[i] != i) {
            list.add(i);
            i = root[i];
        }
        for (Integer item : list) {//路径压缩 直接追溯到根节点
            root[item] = i;
        }
        return i;
    }

    //并查集共初始情况共n个
    public static void init(int n) {
        for (int i = 1; i <= n; i++) {
            root[i] = i;
            size[i] = 1;
            friend[i] = 0;
        }
    }

    //合并i和j所在的集合
    public static void merge(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if (fi == fj) {//两个元素在同一个集合
            friend[fi]++;//根节点所在集合朋友关系加1
        } else {
            size[fi] += size[fj];//人数相加
            friend[fi] += friend[fj] + 1;//朋友关系两个集合相加再加上i和j
            root[fj] = fi;//fj认fi为爹
        }
    }

    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());//T组数据
            for (int i = 1; i <= T; i++) {
                String[] split = br.readLine().split(" ");
                int n = Integer.parseInt(split[0]);
                int m = Integer.parseInt(split[1]);
                init(n);
                for (int j = 0; j < m; j++) {
                    split = br.readLine().split(" ");
                    int fi = Integer.parseInt(split[0]);
                    int fj = Integer.parseInt(split[1]);
                    merge(fi, fj);
                }
                int res = 0;
                for (int j = 1; j <= n; j++) {
                    int father = find(j);
                    if (father == j) {//选取根节点结算一个集合 一个集合必须全部参与 不然贡献不可能为正
                        int ans = friend[j] - size[j];
                        if (ans > 0) {
                            res += ans;
                        }
                    }
                }
                System.out.println("Case #" + i + ": " + res);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}