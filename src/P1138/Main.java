package P1138;

import java.util.HashMap;
import java.util.Scanner;

//根据一颗二叉树的前序遍历和中序遍历，求出后序遍历
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        String pre = scan.next();
        int len = in.length();
        char[] preArr = pre.toCharArray();
        char[] inArr = in.toCharArray();
        HashMap<Character, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inArr.length; i++) {
            inMap.put(inArr[i], i);
        }
        char[] post = new char[len];
        getPost(preArr, 0, len - 1, inArr, 0, len - 1, post, len - 1, inMap);
        System.out.println(String.valueOf(post));
    }

    public static void getPost(char[] pre, int preLeft, int preRight,
                               char[] in, int inLeft, int inRight,
                               char[] post, int postRight, HashMap<Character, Integer> inMap) {
        if (preLeft > preRight || inLeft > inRight) {
            return;
        }
        post[postRight] = pre[preLeft];
        int index = inMap.get(pre[preLeft]);
        getPost(pre, index - inLeft + preLeft + 1, preRight, in, index + 1, inRight,
                post, postRight - 1, inMap);
        getPost(pre, preLeft + 1, preLeft + index - inLeft, in, inLeft, index - 1,
                post, postRight - (inRight - index) - 1, inMap);
    }
}
