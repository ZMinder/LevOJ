package P1268;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int num;
        for (int i = 0; i < N; i++) {
            num = scan.nextInt();
            sum += num;
            //先进行比较再将sum置零 可以在num < 0的情况下获取所有数据中的最大值
            max = Math.max(sum, max);
            sum = sum < 0 ? 0 : sum;
        }
        System.out.println(max);
    }
}
