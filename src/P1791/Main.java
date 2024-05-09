package P1791;

import org.junit.Test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int left = scan.nextInt();
        int right = scan.nextInt();
        while (left != 0 && right != 0) {
            int sum = 0;
            int l = getFour(left - 1, 4);
            int r = getFour(right, 4);
            sum += r - l;
            l = getSixTwo(left - 1);
            r = getSixTwo(right);
            sum += r - l;
            System.out.println(right - left - sum + 1);
            left = scan.nextInt();
            right = scan.nextInt();
        }
    }

//    @Test
//    public void testSixTwo() {
//        for (int i = 100; i < 100000; i++) {
////            Sy stem.out.println(i + ":" + getSixTwo(i));
////            System.out.println(get(i));
//            int res1 = getSixTwo(i);
//            int res2 = get(i);
//            if (res1 != res2) {
//                System.out.println(i);
//                System.out.println(res1);
//                System.out.println(res2);
//            }
//        }
//
////        System.out.println(getSixTwoKNine(7));
////        System.out.println(get(9999999));
//
////        int num = 3859;
////        System.out.println(num + ":" + getSixTwo(num));
////        System.out.println(getSixTwoKNine(3));
////        int i = 6062;
////        int res1 = getSixTwo(i);
////        int res2 = get(i);
////        System.out.println(res1);
////        System.out.println(res2);
//    }

//    public static int get(int num) {
//        int count = 0;
//        for (int i = 0; i < num + 1; i++) {
//            String str = String.valueOf(i);
//            if (str.contains("62") && !str.contains("4")) {
//                count++;
////                System.out.println(count + ":" + str);
//            }
//        }
//        return count;
//    }


//    @Test
//    public void testSixTwoAndFour() {
////        for (int i = 0; i < 10000; i++) {
////            int res1 = getSixTwoAndFour(i);
////            int res2 = getSixTwoAndFour2(i);
////            if (res1 != res2) {
////                System.out.println(i);
////            }
////        }
//        int i = 400;
//        int res1 = getSixTwoAndFour(i);
//        int res2 = getSixTwoAndFour2(i);
//        System.out.println(res1);
//        System.out.println(res2);
//    }

    public static int getSixTwoAndFour2(int num) {
        int count = 0;
        for (int j = 0; j < num + 1; j++) {
            String str = String.valueOf(j);
            if (str.contains("62") && str.contains("4")) {
                count++;
            }
        }
        return count;
    }

//    public static int getSixTwoAndFour(int num) {
//        int digit = getDigits(num);
//        if (digit <= 2) {
//            return 0;
//        }
//        int high = num / (int) Math.pow(10, digit - 1);
//        int sec = num / (int) Math.pow(10, digit - 2) % 10;
//        int sum = 0;
//        int flag = high >= 4 ? digit - 3 : digit - 4;
//        //400
//        sum += high * (int) Math.pow(10, digit - 4) * (digit - 2);
//        if (high >= 4) {
//            sum += (int) Math.pow(10, digit - 3) * (digit - 2);
//        }
//        if (high > 6 || (high == 6 & sec >= 2)) {//可以出现62...
//            sum += Math.pow(10, digit - 3);
//        }
//        return sum;
//    }

    //获取0-num包含62的个数
    public static int getSixTwo(int num) {
        if (num < 100) {
            return num >= 62 ? 1 : 0;
        }
        int digit = getDigits(num);//获取num的位数
        int up = (int) Math.pow(10, digit - 1);//num=346 up=100
        int high = num / up;//获取num最高位的数
        int sum = 0;
        int counts = getSixTwoKNine(digit - 1);
        if (high < 6) {//最高位小于6
            sum += high * counts;
            sum += getSixTwo(num % up);
        } else {//最高位大于6
            sum += high * counts;
            int sec = num * 10 / up % 10;
            if (high == 6 && sec == 2) {//62...
                sum += getSixTwo(up / 10 * 2);
                sum += num % (up / 10) + 1 - getFour(num % (up / 10), 4);
            } else {
                sum += getSixTwo(num % up);
                if (high > 6 || (high == 6 && sec > 2)) {
                    sum -= getSixTwoKNine(digit - 2);
                    sum += up / 10 - getFourKNine(digit - 2, 4);
                }
            }
        }
        if (high > 4) {
            sum -= counts;
        }
        if (high == 4 && num % up >= 62) {
            sum -= getSixTwo(num % up);
        }
        return sum;
    }

    //获取0-99....9(k个)中包含62的个数
    public static int getSixTwoKNine(int k) {
        if (k < 2) {
            return 0;
        } else if (k == 2) {
            return 1;
        } else {
            int less = getSixTwoKNine(k - 1);
            return less * 9 + (int) Math.pow(10, k - 2) -
                    getSixTwoKNine(k - 2) - getFourKNine(k - 2, 4);
        }
    }

    //统计num的位数
    public static int getDigits(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    //获取0-num包含4的个数
    public static int getFour(int num, int target) {
        if (num < 10) {
            return num >= target ? 1 : 0;
        }
        int digit = getDigits(num);
        int up = (int) Math.pow(10, digit - 1);
        int high = num / up;//获取num最高位的数
        int sum = 0;
        if (high < target) {//最高位小于4
            sum += high * getFourKNine(digit - 1, target);
            sum += getFour(num % up, target);
        } else if (high == target) {//最高位等于4
            sum += high * getFourKNine(digit - 1, target);
            sum += num % up + 1;
        } else {//最高位大于4
            sum += (high - 1) * getFourKNine(digit - 1, target);
            sum += getFour(num % up, target) + up;
        }
        return sum;
    }

    //获取0-99....9(k个)中包含4的个数
    public static int getFourKNine(int k, int target) {
        if (k == 1) {
            return 1;
        } else {
            int less = getFourKNine(k - 1, target);
            return less * 9 + (int) Math.pow(10, k - 1);
        }
    }

//    @Test
//    public void testFour() {
//        for (int i = 0; i < 100000; i++) {
////            System.out.println(i + ":" + getFour(i, 4));
//            int count = 0;
//            for (int j = 0; j < i + 1; j++) {
//                if (String.valueOf(j).contains("4")) {
//                    count++;
//                }
//            }
////            System.out.println(count);
//            if (getFour(i, 4) != count) {
//                System.out.println(i);
//            }
//        }
////        int i = 3859;
////        System.out.println(i + ":" + getFour(i, 4));
//    }
}
