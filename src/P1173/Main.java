package P1173;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        readInfo();
    }

    public static String process(String cur, String str, Stack<String> forward, Stack<String> back) {
        if (str.indexOf("FORWARD") == 0) {
            if (forward.isEmpty()) {
                System.out.println("Ignored");
                return cur;
            }
            cur = forward(forward, back, cur);
        } else if (str.indexOf("BACK") == 0) {
            if (back.isEmpty()) {
                System.out.println("Ignored");
                return cur;
            }
            cur = back(forward, back, cur);
        } else if (str.indexOf("VISIT") == 0) {
            cur = visit(back, forward, cur, str.substring(6));
        } else if (str.indexOf("QUIT") == 0) {
            return cur;
        } else {
            throw new RuntimeException("command error");
        }
        System.out.println(cur);
        return cur;
    }

    //前进
    public static String forward(Stack<String> forward, Stack<String> back, String cur) {
        back.push(cur);
        if (forward.isEmpty()) {
            throw new RuntimeException("no forward pages");
        }
        return forward.pop();
    }

    //后退
    public static String back(Stack<String> forward, Stack<String> back, String cur) {
        forward.push(cur);
        if (back.isEmpty()) {
            throw new RuntimeException("no back pages");
        }
        return back.pop();
    }

    //前往指定网页
    public static String visit(Stack<String> back, Stack<String> forward, String cur, String str) {
        back.push(cur);
        forward.clear();
        return str;
    }

    public static void readInfo() {
        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        System.out.println("Case " + N + ":");
        String str = null;
        String cur = "http://www.acm.org/";
        Stack<String> forward = new Stack<>();
        Stack<String> back = new Stack<>();
        do {
            str = scan.nextLine();
            if (!str.equals("")) {
                cur = process(cur, str, forward, back);
            }
        } while (!str.equals("QUIT"));
    }
}
