package P1848;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.next();
        String str2 = scan.next();
        int res = getMinOperations(str1.toCharArray(), str2.toCharArray());
        System.out.println(res);
        scan.close();
    }

    //使用最少的操作数将str1转换成str2
    //操作包括：1.插入一个字符
    //        2.删除一个字符
    //        3.将一个字符转换为另一个字符
    public static int getMinOperations(char[] str1, char[] str2) {
        //dp[i][j]表示str1的前i个字符转换为str2前j个字符所需要的最少操作数
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i <= str1.length; i++) {//初始化第0列
            dp[i][0] = i;
        }
        for (int i = 0; i <= str2.length; i++) {//初始化第0行
            dp[0][i] = i;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    //str1[i]==str2[j]
                    //那么str1转换为str2有没有i和j这两个字符都没有关系 dp[i][j]==dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //str1[i]!=str2[j]
                    dp[i][j] = dp[i - 1][j - 1] + 1;//修改str1[i]和str2[j]
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);//删除str1[i]
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);//str1[i]后面增加一个与str2[j]相同的字符
            }
        }
        return dp[str1.length][str2.length];
    }
}
