package P1972;

import org.junit.Test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int m = scan.nextInt();
        int n = scan.nextInt();
        char[][] arr = new char[m][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < m; j++) {
                arr[j] = scan.next().toCharArray();
            }
            int[][][] dp = new int[m][n][3];
            arr[0][0] = 'W';
            int res = isValid(arr, 3, 0, 0, dp);
            System.out.println(res == 1 ? "YES" : "NO");
        }
    }

    public static int isValid(char[][] arr, int rest, int i, int j, int[][][] dp) {
        if (dp[i][j][rest - 1] != 0) {
            return dp[i][j][rest - 1];
        }
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            dp[i][j][rest - 1] = 1;
            return dp[i][j][rest - 1];
        }
        int flag = -1;
        if (i > 0) {//上
            if (arr[i - 1][j] == 'G') {
                arr[i - 1][j] = 'W';
                flag = isValid(arr, rest, i - 1, j, dp);
                arr[i - 1][j] = 'G';
            } else if (arr[i - 1][j] == 'X' && rest > 1) {
                arr[i - 1][j] = 'W';
                flag = isValid(arr, rest - 1, i - 1, j, dp);
                arr[i - 1][j] = 'X';
            }
        }
        if (flag == -1 && i < arr.length - 1) {//下
            if (arr[i + 1][j] == 'G') {
                arr[i + 1][j] = 'W';
                flag = isValid(arr, rest, i + 1, j, dp);
                arr[i + 1][j] = 'G';
            } else if (arr[i + 1][j] == 'X' && rest > 1) {
                arr[i + 1][j] = 'W';
                flag = isValid(arr, rest - 1, i + 1, j, dp);
                arr[i + 1][j] = 'X';
            }
        }
        if (flag == -1 && j > 0) {//左
            if (arr[i][j - 1] == 'G') {
                arr[i][j - 1] = 'W';
                flag = isValid(arr, rest, i, j - 1, dp);
                arr[i][j - 1] = 'G';
            } else if (arr[i][j - 1] == 'X' && rest > 1) {
                arr[i][j - 1] = 'W';
                flag = isValid(arr, rest - 1, i, j - 1, dp);
                arr[i][j - 1] = 'X';
            }
        }
        if (flag == -1 && j < arr[0].length - 1) {//右
            if (arr[i][j + 1] == 'G') {
                arr[i][j + 1] = 'W';
                flag = isValid(arr, rest, i, j + 1, dp);
                arr[i][j + 1] = 'G';
            } else if (arr[i][j + 1] == 'X' && rest > 1) {
                arr[i][j + 1] = 'W';
                flag = isValid(arr, rest - 1, i, j + 1, dp);
                arr[i][j + 1] = 'X';
            }
        }
        dp[i][j][rest - 1] = flag;
        return dp[i][j][rest - 1];
    }
}
