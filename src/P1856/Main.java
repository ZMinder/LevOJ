package P1856;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long countSum(int[][] map, int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                sum += map[i][j];//所有城市之间最短路径累加和
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());//n个节点
            int[][] map = new int[n + 1][n + 1];
            String[] split = null;
            for (int i = 1; i <= n; i++) {
                split = br.readLine().split(" ");
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(split[j - 1]);
                }
            }
            long sum = countSum(map, n);
            int k = Integer.parseInt(br.readLine());//k条路
            for (int i = 0; i < k; i++) {
                split = br.readLine().split(" ");
                int from = Integer.parseInt(split[0]);
                int to = Integer.parseInt(split[1]);
                int dis = Integer.parseInt(split[2]);
                if (dis >= map[from][to]) {
                    System.out.print(sum + " ");
                    continue;
                }
                map[from][to] = map[to][from] = dis;//更新from-to的最短距离
                //考虑新修的路影响的两个节点 以这两个节点分别作为中间节点m i-m-j 会不会比i-j更短
                for (int j = 1; j <= n; j++) {
                    for (int l = 1; l <= n; l++) {
                        map[j][l] = Math.min(map[j][l], map[j][from] + map[from][l]);
                    }
                }
                for (int j = 1; j <= n; j++) {
                    for (int l = 1; l <= n; l++) {
                        map[j][l] = Math.min(map[j][l], map[j][to] + map[to][l]);
                    }
                }
                sum = countSum(map, n);
                System.out.print(sum + " ");
            }
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
        }
    }
}