package P1795;

import java.io.*;

public class Main {

    //检测该位置的颜色是否可以放置
    public static boolean check(int[][] map, int[] color, int i) {
        for (int j = 1; j < i; j++) {
            if (map[j][i] == 1 && color[j] == color[i]) {//连通并且颜色相同
                return false;
            }
        }
        return true;
    }

    public static int solve(int n, int m, int[][] map, int i, int[] color) {
        if (i > n) {
            return 1;
        }
        int res = 0;
        for (int j = 1; j <= m; j++) {
            color[i] = j;//当前节点放置j颜色
            if (check(map, color, i)) {
                res += solve(n, m, map, i + 1, color);
            }
            color[i] = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(new OutputStreamWriter(System.out));
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);//n个顶点
            int k = Integer.parseInt(split[1]);//k条边
            int m = Integer.parseInt(split[2]);//m种颜色
            int[][] map = new int[n + 1][n + 1];
            int[] color = new int[n + 1];//记录每个节点的颜色
            for (int i = 0; i < k; i++) {
                split = br.readLine().split(" ");
                int start = Integer.parseInt(split[0]);
                int end = Integer.parseInt(split[1]);
                map[start][end] = map[end][start] = 1;
            }
            int res = solve(n, m, map, 1, color);
            pw.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }
}
