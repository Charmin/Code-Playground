package interviews.amazon.OA2021;

import java.util.*;
import java.util.stream.Collectors;

/*
* A warehouse has one loading dock that workers use to load and unload goods.

Warehouse workers carrying the goods arrive at the loading dock at different times. They form two queues, a "loading" queue and an "unloading" queue. Within each queue, the workers are ordered by the time they arrive at the dock.

The arrival time (in minutes) array stores the minute the worker arrives at the loading dock. The direction array stores whether the worker is "loading" or "unloading", a value of 0 means loading and 1 means unloading. Loading/unloading takes 1 minute.

When a worker arrives at the loading dock, if no other worker is at the dock at the same time, then the worker can use the dock.

If a "loading" worker and an "unloading" worker arrive at the dock at the same time, then we decide who can use the dock with these rules:

if the loading dock was not in use in the previous minute, then the unloading worker can use the dock.
if the loading dock was just used by another unloading worker, then the unloading worker can use the dock.
if the loading dock was just used by another loading worker, then the loading worker can use the dock.
Return an array of the time (in minute) each worker uses the dock.

* Examples
Example 1:
Input:
time = [0, 0, 1, 6] direction = [0, 1, 1, 0]

Output:
[2, 0, 1, 6]

Explanation:
At time 0, worker 0 and 1 want to use the dock. Worker 0 wants to load and worker 1 wants to unload. The dock was not used in the previous minute, so worker 1 unload first.
At time 1, workers 0 and 2 want to use the rock. Worker 2 wants to unload, and at the previous minute the dock was used to unload, so worker 2 uses the dock.
At time 2, worker 0 is the only worker at the dock, so he uses the dock.
At time 6, worker 3 arrives at the empty dock and uses the dock.
We return [2, 0, 1, 6].

* */
public class Turnstile {

    public static List<Integer> getTimes(int numWorker, List<Integer> arrTime, List<Integer> direction) {

        if (numWorker == 0) {
            return new ArrayList<>();
        }

        LinkedList<int[]> loadQ = new LinkedList<>();
        LinkedList<int[]> unLoadQ = new LinkedList<>();

        for (int i = 0; i < arrTime.size(); i++) {
            int[] timeAndWorker = new int[2];
            timeAndWorker[0] = arrTime.get(i);
            timeAndWorker[1] = i;
            if (direction.get(i) == 0) {
                loadQ.add(timeAndWorker);
            } else {
                unLoadQ.add(timeAndWorker);
            }
        }

        Map<Integer, Integer> workTime = getWorkTimes(loadQ, unLoadQ, arrTime);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arrTime.size(); i++) {
            result.add(workTime.get(i));
        }

       return result;
    }

    private static Map<Integer, Integer> getWorkTimes(LinkedList<int[]> lq, LinkedList<int[]> uq, List<Integer> arrTime) {
        int curTime = arrTime.get(0);
        int curAct = -2; //-2 -> empty

        Map<Integer, Integer> workTime = new HashMap<>();
        while (!lq.isEmpty() && !uq.isEmpty()) {

            int[] topLoad =  lq.getFirst();
            int[] topUnload = uq.getFirst();

            int ltime = Math.max(curTime, topLoad[0]);
            int utime = Math.max(curTime, topUnload[0]);

            if (ltime == utime) {
                if (curAct == -2 || curAct == 1) {
                    curAct = 1;
                    workTime.put(uq.remove()[1], ltime);
                } else {
                    curAct = 0;
                    workTime.put(lq.remove()[1], utime);
                }
            } else {
                curAct = removeFirstArrivedAndReturnAction(lq, uq, workTime, curTime);
            }

            curTime++;
        }

        while (!lq.isEmpty()) {
            int t = Math.max(curTime, lq.peek()[0]);
            workTime.put(lq.remove()[1], t);
            curTime++;
        }

        while (!uq.isEmpty()) {
            int t = Math.max(curTime, uq.peek()[0]);
            workTime.put(uq.remove()[1], t);
            curTime++;
        }


        return workTime;
    }

    private static Integer removeFirstArrivedAndReturnAction(LinkedList<int[]> lq, LinkedList<int[]> uq, Map<Integer, Integer> workTime, int curTime) {
        int[] removed;
        int action;

        if (lq.isEmpty() && uq.isEmpty()) {
            return -1;
        }

        if (lq.getFirst()[0] < uq.getFirst()[0]) {
            removed = lq.remove();
            action = 0;
        } else {
            removed = uq.remove();
            action = 1;
        }

        curTime = Math.max(curTime, removed[0]);
        workTime.put(removed[1], curTime);
        return action;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //int numWorker = Integer.parseInt(scanner.nextLine());
        //List<Integer> arrTime = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        //List<Integer> direction = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int numWorker = 4;
        List<Integer> arrTime = Arrays.asList(0, 0, 1, 6);
        List<Integer> direction = Arrays.asList(0, 1, 1, 0);

        List<Integer> res = getTimes(numWorker, arrTime, direction);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

}
