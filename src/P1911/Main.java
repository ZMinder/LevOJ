package P1911;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();//需要组成的钱数
        int res = x / 10 + x % 10;
        System.out.println(res);
        scan.close();
    }
}
