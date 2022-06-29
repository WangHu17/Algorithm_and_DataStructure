package monotonicstack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 找数组中每个元素左边第一个比自己小的和右边第一个比自己小的位置，数组可能有重复值
 * @create 2022-06-28 16:24
 */
public class NearLess {

    public int[][] getNearLess(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i=0; i<arr.length; i++){
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> list = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (int j:list){
                    res[j][0] = leftLess;
                    res[j][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> list = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (int j:list){
                res[j][0] = leftLess;
                res[j][1] = -1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(NearLessNoRepeat.getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops1!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops2!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // for test
    public int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
