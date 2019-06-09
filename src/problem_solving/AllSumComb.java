package problem_solving;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitra.kr on 23/02/19.
 */
public class AllSumComb {

    public static void main(String[] args) {
        List<Integer> output = new ArrayList<>();
        print_all_sum_iter(4, 0, 1, output, new ArrayList<Integer>());
        System.out.println(output);
    }

    static void print_all_sum_rec(
            int target,
            int current_sum,
            int start,
            List<Integer> output,
            List<Integer> result) {

        if (current_sum == target) {
            output.addAll(result);
            System.out.println("result:" + result);
            result.remove(result.size() - 1);
            return;
        }
        if (current_sum > target) {
            result.remove(result.size() - 1);
            return;
        }

        if (start >= target && result.size() > 0) {
            result.remove(result.size() - 1);
            return;
        } else if (start >= target){
            return;
        }
        //with
        result.add(start);
        print_all_sum_rec(target, current_sum + start, start, output, result);
        print_all_sum_rec(target, current_sum, start + 1, output, result);

    }

    static void print_all_sum_iter(
            int target,
            int current_sum,
            int start,
            List<Integer> output,
            List<Integer> result) {

        if (current_sum == target) {
            output.addAll(result);
            System.out.println("result:" + result);
            return;
        }
        //with
        for (int i = start; i < target; i++) {
            if (current_sum < target) {
                result.add(i);
                print_all_sum_iter(target, current_sum + i, i, output, result);
                result.remove(result.size() - 1);
            }
        }
    }
}
