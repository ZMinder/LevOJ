package P1743;

import java.util.Scanner;

public class Main {
    public static int[][] noises = new int[105][105];//记录两点之间的噪声
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = 1;
        while (scan.hasNextInt()) {
            int vertex = scan.nextInt();//节点的个数
            int edges = scan.nextInt();//边的个数
            int query = scan.nextInt();//询问次数
            if(vertex == 0 && edges == 0 && query == 0){
                break;
            }
            for (int i = 1; i < vertex + 1; i++) {//初始化为Int的最大值
                for (int j = 1; j < vertex + 1; j++) {
                    if (i != j) {
                        noises[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            for (int i = 0; i < edges; i++) {//读取数据
                int from = scan.nextInt();
                int to = scan.nextInt();
                int noise = scan.nextInt();
                noises[from][to] = noises[to][from] = noise;
            }
            //借用弗洛伊德算法
            for (int k = 1; k < vertex + 1; k++) {
                for (int i = 1; i < vertex + 1; i++) {
                    for (int j = 1; j < vertex + 1; j++) {
                        //不同路径的最少值
                        noises[i][j] = Math.min(noises[i][j],
                                //同一路径的最大值
                                Math.max(noises[i][k], noises[k][j]));
                    }
                }
            }
            System.out.println("Case #" + t++);
            for (int i = 0; i < query; i++) {
                int start = scan.nextInt();
                int end = scan.nextInt();
                System.out.println(noises[start][end] == Integer.MAX_VALUE ?
                        "no path" : noises[start][end]);
            }
        }
    }
}