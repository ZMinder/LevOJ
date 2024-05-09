package P1395;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {//多组输入
            int num = scan.nextInt();
            ArrayList<String> list = new ArrayList<>();
            boolean[] isVisited = new boolean[num];
            process(list, "", 0, isVisited);
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    public static void process(ArrayList<String> list, String str, int i, boolean[] isVisited) {
        if (i == isVisited.length) {
            list.add(str);
            return;
        }
        for (int j = 1; j <= isVisited.length; j++) {
            if (!isVisited[j - 1]) {
                isVisited[j - 1] = true;
                process(list, str + j, i + 1, isVisited);
                isVisited[j - 1] = false;
            }
        }
    }
}
