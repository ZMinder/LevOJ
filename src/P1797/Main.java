package P1797;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] king = new int[N];
        int[] soldier = new int[N];
        for (int i = 0; i < N; i++) {
            soldier[i] = scan.nextInt();
        }
        for (int i = 0; i < N; i++) {
            king[i] = scan.nextInt();
        }
        System.out.println(maxEarns(king, soldier));
    }

    //    public static int maxEarns(int[] king, int[] soldier) {
//        Arrays.sort(king);
//        Arrays.sort(soldier);
//        int res = 0;
//        int kingL = 0;
//        int kingR = king.length - 1;
//        int soldierL = 0;
//        int soldierR = soldier.length - 1;
//        while (soldierL <= soldierR && kingL <= kingR) {
//            if (king[kingR] < soldier[soldierR]) {//田忌最快的马比王最快的马快 两者进行比赛 田忌赢
//                res += 200;
//                kingR--;
//                soldierR--;
//            } else if (king[kingR] > soldier[soldierR]) {//田忌最快的马比王最快的马慢
//                //田忌最慢的马于王最快的比赛 田忌输
//                res -= 200;
//                soldierL++;
//                kingR--;
//            } else {//田忌最快的马与王最快的马速度相同
//                if (soldier[soldierL] > king[kingL]) {
//                    //田忌最慢的马比王最慢的马快 优先最慢的马先比赛 直到找到最慢的马田忌比不过的时候 用最慢的马去跟王最快的马比赛
//                    res += 200;
//                    soldierL++;
//                    kingL++;
//                } else {
//                    //田忌最慢的马比不过王最慢的马 田忌最慢的马肯定比不过王最快的马
//                    if (soldier[soldierL] < king[kingR]) {
//                        //直到找到最慢的马田忌比不过的时候 并且最慢的马比不过王最快的马 用最慢的马去跟王最快的马比赛
//                        res -= 200;
//                    }
//                    //田忌最慢的马与王最慢的马速度相同 最快的马速度也相同
//                    soldierL++;
//                    kingR--;
//                }
//            }
//        }
//        return res;
//    }
    public static int maxEarns(int[] king, int[] soldier) {
        Arrays.sort(king);
        Arrays.sort(soldier);
        int res = 0;
        int kingL = 0;
        int kingR = king.length - 1;
        int soldierL = 0;
        int soldierR = soldier.length - 1;
        while (soldierL <= soldierR && kingL <= kingR) {
            if (king[kingL] < soldier[soldierL]) {//田忌最慢的马比王最慢的马快 两者进行比赛 田忌赢
                res += 200;
                kingL++;
                soldierL++;
            } else if (king[kingL] > soldier[soldierL]) {//田忌最慢的马比王最慢的马慢
                //田忌最慢的马于王最快的比赛 田忌输
                res -= 200;
                soldierL++;
                kingR--;
            } else {//田忌最慢的马与王最慢的马速度相同
                if (soldier[soldierR] > king[kingR]) {
                    //田忌最快的马比王最快的马快 优先最快的马先比赛 直到找到最快的马田忌比不过的时候 用最慢的马去跟王最快的马比赛
                    res += 200;
                    soldierR--;
                    kingR--;
                } else if (soldier[soldierR] < king[kingR]) {
                    //直到找到最快的马田忌比不过的时候 田忌最慢的马肯定小于王最快的马两者进行比赛
                    res -= 200;
                    soldierL++;
                    kingR--;
                } else {
                    //最慢的马和最快的马速度都相同 并且田忌最慢的马跑不过王最快的马  否则所有马速度相同
                    if (soldier[soldierL] < king[kingR]) {
                        res -= 200;
                    }
                    soldierL++;
                    kingR--;
                }
            }
        }
        return res;
    }
}
