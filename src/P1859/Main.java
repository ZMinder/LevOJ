package P1859;

import java.util.Scanner;

/*
单词接龙是一个与我们经常玩的成语接龙相类似的游戏，现在我们已知一组单词，且给定一个开头的字母，
要求出以这个字母开头的最长的 “龙”（每个单词都最多在 “龙” 中出现两次），在两个单词相连时，
其重合的开头字母与结尾字母合为一个字母，例如 beast 和 tree ，如果接成一条龙则变为 beastree 。
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        String[] strs = new String[count];//数据
        int[] vis = new int[count];
        char first = readInfo(strs, scan);
        int length = getLength(strs, vis, first);
        System.out.print(length + 1);
        scan.close();
    }

    //返回以当前字符开头的最大长度
    public static int getLength(String[] strs, int[] vis, char first) {
        int maxLength = 0;
        for (int i = 0; i < strs.length; i++) {
            int len = strs[i].length();
            if (first == strs[i].charAt(0) && vis[i] < 2) {//单词可以接上，并且未使用超过两次
                vis[i]++;
                int length = len - 1 + getLength(strs, vis, strs[i].charAt(len - 1));
                maxLength = Math.max(length, maxLength);
                vis[i]--;
            }
        }
        return maxLength;
    }

    public static char readInfo(String[] strs, Scanner scan) {
        for (int i = 0; i < strs.length; i++) {
            strs[i] = scan.next();
        }
        char first = scan.next().charAt(0);
        return first;
    }
}
