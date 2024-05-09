package P1477;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static class Coin {
        public int weight;
        public int value;
    }

    public static class MyComparable implements Comparator<Coin> {
        @Override
        public int compare(Coin o1, Coin o2) {
            double p1 = (double) o1.value / o1.weight;
            double p2 = (double) o2.value / o2.weight;
            return p1 > p2 ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int maxWeight = scan.nextInt();
        Coin[] coins = new Coin[N];
        for (int i = 0; i < N; i++) {
            coins[i] = new Coin();
            coins[i].weight = scan.nextInt();
            coins[i].value = scan.nextInt();
        }
        Arrays.sort(coins, new MyComparable());
        double maxValue = 0;
        for (int i = 0; i < N && maxWeight > 0; i++) {
            int weight = coins[i].weight <= maxWeight ? coins[i].weight : maxWeight;
            maxValue += weight * (double) coins[i].value / coins[i].weight;
            maxWeight -= weight;
        }
        System.out.println((int) maxValue);
        scan.close();
    }
}
