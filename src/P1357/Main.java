package P1357;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {//多组输入
            int n = scan.nextInt();//n个物种
            int m = scan.nextInt();//m条能量流动关系
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {//初始化能量流动图
                map.put(i + 1, new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {//读取能量流动关系
                int a = scan.nextInt();
                int b = scan.nextInt();
                map.get(a).add(b);
            }
            int[] dp = new int[n];//记录每个起点的食物链长度
            int maxLength = 0;
            for (int i = 1; i <= n; i++) {//获取每个起点的最长食物链并取较大值
                maxLength = Math.max(getLength(map, i, dp), maxLength);
            }
            System.out.println(maxLength);
        }
    }

    //从start开始最长的食物链
    public static int getLength(HashMap<Integer, ArrayList<Integer>> map, int start, int[] dp) {
        if (dp[start - 1] != 0) {//如果getLength计算过start 必然不为0
            return dp[start - 1];
        }
        ArrayList<Integer> next = map.get(start);
        int length = 0;
        for (Integer node : next) {
            length = Math.max(getLength(map, node, dp), length);
        }
        dp[start - 1] = length + 1;
        return length + 1;
    }
}
