package P1475;

import P1797.Main1;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static class Game {
        public int time;
        public int money;

        public Game() {
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            int rest = scan.nextInt();
            int N = scan.nextInt();
            Game[] games = new Game[N];
            int maxTime = readInfo(games, scan);
            System.out.println(maxRest(games, rest, maxTime));
        }
        scan.close();
    }

    @Test
    public void test() {
        for (int j = 0; j < 100000; j++) {
            int rest = (int) (Math.random() * 20000) + 1;
            int N = (int) (Math.random() * 20) + 1;
            Game[] games = new Game[N];
            int maxTime = -1;

            for (int i = 0; i < N; i++) {
                games[i] = new Game();
                games[i].time = (int) (Math.random() * 500) + 1;
                maxTime = Math.max(maxTime, games[i].time);
            }
            for (int i = 0; i < N; i++) {
                games[i].money = (int) (Math.random() * 500) + 1;
            }
            Arrays.sort(games, new MyComparable());
            int res = maxRest(games, rest, maxTime);
//            System.out.println(maxRest(games, rest, maxTime));
        }
        System.out.println("ok");
    }

    public static int maxRest(Game[] games, int rest, int maxTime) {
        boolean[] pre = new boolean[maxTime + 100];//下标i记录i+1 时段是否已用
        for (int i = 0; i < games.length; i++) {
            int time = games[i].time;
            while (time > 0 && pre[time - 1]) {//寻找第一个可用的时段
                time--;
            }
            if (time == 0) {//不存在可用的时段
                rest -= games[i].money;
            } else {//存在可用的时段
                pre[time - 1] = true;
            }
        }
        return rest;
    }

    public static class MyComparable implements Comparator<Game> {
        @Override
        public int compare(Game o1, Game o2) {
            return o2.money - o1.money;
        }
    }

    public static int readInfo(Game[] games, Scanner scan) {
        int N = games.length;
        int maxTime = -1;
        for (int i = 0; i < N; i++) {
            games[i] = new Game();
            games[i].time = scan.nextInt();
            maxTime = Math.max(maxTime, games[i].time);
        }
        for (int i = 0; i < N; i++) {
            games[i].money = scan.nextInt();
        }
        Arrays.sort(games, new MyComparable());
        return maxTime;
    }
}
