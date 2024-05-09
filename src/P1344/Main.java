package P1344;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        while (scan.hasNext()) {
            str = scan.next();
            System.out.println(getMaxLength(str.toCharArray()));
        }
    }

    public static int getMaxLength(char[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        //dp[i][j]表示字符串下标i-j最大回文长度
        for (int i = arr.length - 1; i >= 0; i--) {
            dp[i][i] = 1;//对角线始终为1
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {//如果arr[i]==arr[j] 说明可以同时作为回文子序列的首尾 只需要两个中间的最大长度+2即可
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {//否则 两者不可同时作为回文子序列的首尾
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][arr.length - 1];
    }
}
