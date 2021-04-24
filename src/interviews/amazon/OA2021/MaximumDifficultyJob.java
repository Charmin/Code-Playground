package interviews.amazon.OA2021;

import java.util.List;

public class MaximumDifficultyJob {

    public static void main(String[] args) {

    }

    private int minDifficulty(List<Integer> job, int d) {

        if (d == 0) {
            return -1; //no days, no jobs
        }

        int[][] cache = new int[job.size()][d+1]; //0-n

        int diff = getDifficulty(0, job, d, cache);
        return diff;
    }

    private int getDifficulty(int start, List<Integer> job, int d, int[][] cache) {
        if (start == job.size() && d == 0) {
            return 0; //a valid break
        }

        if (start == job.size() || d == 0) {
            return Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        int maxForDay = job.get(start);

        for (int i = start; i < job.size(); i++) {
            maxForDay = Math.max(maxForDay, job.get(i));

            int minForNextDays = getDifficulty(i+1, job, d-1, cache);
            if (minForNextDays != Integer.MAX_VALUE) {
                res = Math.min(res, maxForDay + minForNextDays);
            }
        }

        cache[start][d] = res;
        return res;
    }
}
