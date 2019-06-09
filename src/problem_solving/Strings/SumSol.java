package problem_solving.Strings;

import java.util.*;
import java.util.stream.Collectors;

public class SumSol {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //long sol = getSolutions(2);
        //System.out.println(sol);
        int[] input = {3, 4, 5, 6, 8, 10, 9, 12, 15, 18, 19};
        List<int[]> res = find_pythagorean_triplets(input);
        int n = 5;
        double x = -2;
        //double result = getPower(x,n);
//        if (n % 2 != 0) {
//            System.out.println(x * result);
//        } else {
//            System.out.println(result);
//        }

        List<Character> c = Arrays.asList('1', '2', '3', '4');
        List<StringBuilder> builderBag = new ArrayList<>();
        kthPerm(11, c, 0, builderBag, c.size());
        //kthPermNaive(c, 8, builderBag, 0, co);
        System.out.println(builderBag.get(0));
    }

    private static int getFactorial(int size) {
        if (size == 0) {
            return 1;
        }
        if (size == 1) {
            return size;
        }
        return size * getFactorial(size - 1);
    }

    private static long getSolutions(long s) {
        long solutions = 0;
        int[] inputs = {0, 0, 0, 0};
        int subSum = 0;
        int seed = 1;
        while (subSum <= s) {
            for (int i = 0; i < (1 << 4); i++) {
                int p = i;
                for (int j = 0; j < 4; j++) {
                    int l = p & 1;
                    if (l > 0) {
                        if (inputs[j] < seed) {
                            inputs[j] = seed;
                        }
                        if (j == 0) {
                            subSum += inputs[j];
                        } else if (j == 1) {
                            subSum += (inputs[j] * inputs[j]);
                        } else if (j == 2) {
                            subSum += (inputs[j] * inputs[j] * inputs[j]);
                        } else {
                            subSum += (inputs[j] * inputs[j] * inputs[j] * inputs[j]);
                        }
                    }
                    p = p >> 1;
                }
                if (subSum > 0 && subSum <= s) {
                    solutions++;
                }
                subSum = 0;
                Arrays.fill(inputs, 0);
            }
            seed++;
        }
        return solutions;
    }

    public static List<int[]> find_pythagorean_triplets(int[] arr) {
        //TODO: Write - Your - Code
        LinkedList<int[]> triplets = new LinkedList<>();
        List<Integer> input = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<Integer> original = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(input);
        Collections.reverse(input);
        List<Integer> squares = input.stream().map(i -> (i * i)).collect(Collectors.toList());
        for (int i = 0; i < squares.size(); i++) {
            int start = i + 1;
            int end = squares.size() - 1;
            int[] triples = searchTri(squares, start, end, squares.get(i));
            if (triples != null) triplets.push(triples);
        }
        return triplets;
    }

    private static int[] searchTri(List<Integer> squares, int start, int end, Integer t) {
        int[] result = new int[3];
        boolean found = false;
        while (start < end) {
            int diff = squares.get(end) + squares.get(start) - t;
            if (diff > 0) {
                start++;
            } else if (diff < 0) {
                end--;
            } else {
                result[1] = (int) Math.sqrt(squares.get(start));
                result[0] = (int) Math.sqrt(squares.get(end));
                result[2] = (int) Math.sqrt(t);
                found = true;
                break;
            }
        }
        if (!found) {
            return null;
        }
        return result;
    }

//    private static void swap(int i, int j, List<Integer> squares) {
//        if (i != j) {
//            int temp = squares.get(i);
//            squares.set(i, squares.get(j));
//            squares.set(j, temp);
//        }
//    }

    public static double getPower(double x, int n) {

        if (n == 1) {
            return x;
        }
        double res = getPower(x, n / 2);
        return res * res;
    }

    //BruteForce
    private static void kthPermNaive(List<Character> v, int k, List<StringBuilder> result, int index, List<Integer> counts) {
        if (index == v.size() - 1) {
            counts.set(0, counts.get(0) + 1);
            if (counts.get(0) == k) {
                List<Character> copy = new ArrayList<>(v);
                StringBuilder res = new StringBuilder();
                for (Character c : copy) {
                    res.append(c);
                }
                result.add(res);
            }
            return;
        }
        if (result.size() > 0) {
            return;
        }
        for (int i = index; i < v.size(); i++) {
            swap(i, index, v);
            kthPermNaive(v, k, result, index + 1, counts);
            swap(i, index, v);
        }
    }

    private static void kthPerm(int k, List<Character> c, int index, List<StringBuilder> builderBag, int permSize) {
        int perElementSize = getFactorial(permSize - 1);
        int blockIndexCounter = 0;
        int prev = blockIndexCounter;
        int blockIndex = index;
        if (k == 0 || k == 1) {
            StringBuilder res = new StringBuilder();
            for (Character ch : c) {
                res.append(ch);
            }
            builderBag.add(res);
            return;
        }
        for (int i = index; i < c.size() && blockIndexCounter < k; i++) {
            prev = blockIndexCounter;
            blockIndex = i;
            blockIndexCounter = blockIndexCounter + perElementSize;
        }
        swap(blockIndex, index, c);
        //k = k - Math.max(1, prev);
        kthPerm(k - prev, c, index + 1, builderBag, permSize - 1);
    }

    private static void swap(int i, int j, List<Character> copy) {
        Character temp = copy.get(i);
        copy.set(i, copy.get(j));
        copy.set(j, temp);
    }

}


