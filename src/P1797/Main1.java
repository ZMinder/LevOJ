package P1797;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

    static int solve(int[] a, int[] b, int n) {
        Arrays.sort(a, 0, n);
        Arrays.sort(b, 0, n);
        int ans = 0;
        int lefta = 0, leftb = 0;
        int righta = n - 1, rightb = n - 1;
        while (lefta <= righta) {
            if (a[righta] > b[rightb]) {
                righta--;
                rightb--;
                ans += 200;
            } else if (a[righta] < b[rightb]) {
                lefta++;
                rightb--;
                ans -= 200;
            } else {
                if (a[lefta] > b[leftb]) {
                    ans += 200;
                    lefta++;
                    leftb++;
                } else {
                    if (a[lefta] < b[rightb]) {
                        ans -= 200;
                    }
                    lefta++;
                    rightb--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        int ans = solve(a, b, n);
        System.out.println(ans);
        scanner.close();
    }
}
