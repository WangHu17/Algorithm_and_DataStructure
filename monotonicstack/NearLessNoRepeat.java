package monotonicstack;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 找数组中每个元素左边第一个比自己小的和右边第一个比自己小的位置，数组无重复值
 * @create 2022-06-28 16:01
 */
public class NearLessNoRepeat {

    public static int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<arr.length; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                Integer j = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = leftLess;
                res[j][1] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()){
            Integer j = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek();
            res[j][0] = leftLess;
            res[j][1] = -1;
        }
        return res;
    }

}
