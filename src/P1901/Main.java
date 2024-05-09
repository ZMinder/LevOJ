package P1901;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//数组长度
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();//读取数组内容
        }
        int res = maxLength(nums);
        System.out.println(res);
    }


//    @Test
//    public void test() {
//        for (int i = 0; i < 3; i++) {
//            int n = (int) (Math.random() * 10) + 1;
//            int[] nums = new int[n];
//            for (int j = 0; j < n; j++) {
//                nums[j] = (int) (Math.random() * 100) + 1;
//            }
//            int res = maxLength(nums);
//            System.out.println(Arrays.toString(nums));
//            System.out.println(res);
//        }
//    }

    public static int maxLength(int[] nums) {
        int[] length = new int[nums.length];//以第i个位置作为子序列结尾的情况下 该子序列最大长度
        int max = 0;
        for (int i = 0; i < length.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (isPrime(nums[i], nums[j])) {//保证互质的前提下
                    length[i] = Math.max(length[i], length[j]);//选取与自己互质的前一个数所构成的序列最长的
                }
            }
            length[i] += 1;//以自己作为结尾 长度至少为1
            max = Math.max(max, length[i]);//选取最大值
        }
        return max;
    }

    // 8 9 7 10 1 1
    //     1
    public static boolean isPrime(int n1, int n2) {
        while (n2 != 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1 == 1;
    }

}
