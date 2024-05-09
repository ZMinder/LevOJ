package P1851;

import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    //打表法
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        int n = scan.nextInt();
        HashMap<Integer, Integer> map = generateMap(x, y, n);
        n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int key = scan.nextInt();
            if (map.containsKey(key)) {
                System.out.println(map.get(key));
            } else {
                System.out.println(-1);
            }
        }
    }

    @Test
    public void test() {
        int x = 1;
        int y = 14;
        int n = 100;
        int[] arr = generateArr(x, y, n);
        System.out.println(0 + ":" + x);
        for (int i = 1; i <= n; i++) {
            if (arr[i] != arr[i - 1]) {
                System.out.println(i + ":" + arr[i]);
            }
        }
        System.out.println("--------------------------------");
        HashMap<Integer, Integer> map = generateMap(x, y, n);
        for (int value : map.keySet()) {
            System.out.println(map.get(value) + ":" + value);
        }
    }

    public static int[] generateArr(int x, int y, int n) {
        int[] arr = new int[n + 1];
        arr[0] = x;
        arr[1] = y;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i / 2] + arr[i / 4];
        }
        return arr;
    }

    public static HashMap<Integer, Integer> generateMap(int x, int y, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(x, 0);
        map.put(y, 1);
        int i = 2;
        while (i <= n) {
            int value = x + y;
            map.put(value, i);
            x = y;
            y = value;
            i *= 2;
        }
        return map;
    }
}
