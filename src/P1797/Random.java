package P1797;

import org.junit.Test;

import java.util.Arrays;

public class Random {
    @Test
    public void test() {
        for (int j = 0; j < 100000; j++) {
            int n = (int) (Math.random() * 500) + 1;
            int[] king = new int[n];
            int[] soldier = new int[n];
            for (int i = 0; i < n; i++) {
                king[i] = (int) (Math.random() * 300) + 10;
                soldier[i] = (int) (Math.random() * 300) + 1;
            }
            int ans = Main.maxEarns(king, soldier);
//            System.out.println("ans");
            int ans1 = Main1.solve(soldier, king, n);
//            System.out.println(j + ":ans1");
            if (ans != ans1) {
                System.out.println(Arrays.toString(king));
                System.out.println(Arrays.toString(soldier));
                System.out.println(ans);
                System.out.println(ans1);
                break;
            }
        }
    }
}
