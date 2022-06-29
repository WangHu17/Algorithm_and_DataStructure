package quicksortproblems;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 非递归快速排序
 * @create 2022-06-06 16:25
 */
public class QuickSortNoRecursion {

    class Job{
        int l;
        int r;
        public Job(int l, int r){
            this.l = l;
            this.r = r;
        }
    }

    public void quickSort(int[] arr){
        if (arr == null || arr.length < 2)return;
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()){
            Job job = stack.pop();
            if (job.l < job.r){
                Swap.swap(arr, job.l + (int) (Math.random() * (job.r - job.l + 1)), job.r);
                int[] partition = Partition2.partition(arr, job.l, job.r);
                stack.push(new Job(job.l, partition[0] - 1));
                stack.push(new Job(partition[1] + 1, job.r));
            }
        }
    }

    @Test
    public void test(){
        int[] arr = {4,2,6,3,9,7,5,1,0,4};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
