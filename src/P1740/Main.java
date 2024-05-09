package P1740;

import java.util.Scanner;

public class Main {

    public static long getDistance(int[] x, int[] y) {
        return (long) (Math.pow(y[0] - x[0], 2) + Math.pow(y[1] - x[1], 2));
    }

    public static void initMap(int[][] ink) {
        //初始化图
        for (int i = 0; i < ink.length; i++) {
            for (int j = i; j < ink.length; j++) {
                graph[i][j] = graph[j][i] = getDistance(ink[i], ink[j]);
            }
        }
    }

    public static int MAX_ = 5005;
    public static long[] lowest = new long[MAX_];
    public static long[][] graph = new long[MAX_][MAX_];

    public static long prim(int n) {
        long max = -1;
        for (int i = 1; i < n; i++) {
            lowest[i] = graph[0][i];
        }
        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;
            int minId = -1;
            for (int j = 1; j < n; j++) {//找到距离上一个节点最近的节点
                if (lowest[j] < min && lowest[j] != 0) {
                    min = lowest[j];
                    minId = j;//找到距离最小生成树最近的节点
                }
            }
            max = Math.max(max, min);//取最小生成树中最大的路径
            lowest[minId] = 0;//标记已经在最小生成树内
            //找到所有已经在最小生成树的最小的边，只需要看新加进来的点的边有没有比之前的小即可
            for (int j = 1; j < n; j++) {
                if (graph[minId][j] < lowest[j]) {
                    lowest[j] = graph[minId][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        //测试组数
        while (T-- > 0) {
            int n = scan.nextInt();
            //墨水的数量
            int[][] ink = new int[n][2];
            for (int i = 0; i < n; i++) {
                ink[i][0] = scan.nextInt();
                //第i滴墨水的x
                ink[i][1] = scan.nextInt();
                //第i滴墨水的y
            }
            initMap(ink);
            long max = prim(n);
            System.out.println(max);
        }
    }
}
