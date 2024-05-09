package P1849;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//棋盘格子数
        int m = scan.nextInt();//爬行卡片数
        int[] scores = new int[n];//记录每个格子的分数
        for (int i = 0; i < n; i++) {
            scores[i] = scan.nextInt();//读取每个格子上面的分数
        }
        int[] type = new int[5];//记录每种类型的卡片数量
        for (int i = 0; i < m; i++) {
            int step = scan.nextInt();
            type[step]++;
        }
        int res = getMaxScore(scores, type, n,
                new int[type[1] + 1][type[2] + 1][type[3] + 1][type[4] + 1]);
        System.out.print(res);
    }

    /**
     * 在type里面的爬行卡牌情况下 爬到终点 最多获取的分数
     *
     * @param scores 记录每个格子的分数
     * @param type   记录每种类型剩余的卡片数量
     * @param n      终点
     * @return最多获取的分数
     */
    public static int getMaxScore(int[] scores, int[] type, int n, int[][][][] dp) {
        if (dp[type[1]][type[2]][type[3]][type[4]] != 0) {
            return dp[type[1]][type[2]][type[3]][type[4]];
        }
        int steps = 0;
        for (int i = 1; i < type.length; i++) {
            steps += type[i] * i;
        }
        steps = n - steps - 1;//实际所在的格子
        int score = 0;//初始获得当前格子的分数
        //可以选择每次爬的步数
        for (int j = 1; j < type.length; j++) {
            if (type[j] > 0) {//当前类型仍有卡牌
                type[j]--;//使用当前类型卡牌
                score = Math.max(score, getMaxScore(scores, type, n, dp));
                type[j]++;
            }
        }
        dp[type[1]][type[2]][type[3]][type[4]] = score + scores[steps];
        return score + scores[steps];
    }
}
