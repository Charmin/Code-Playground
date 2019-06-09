package problem_solving;

import java.io.*;
import java.util.*;

/**
 * Created by chaitra.kr on 08/07/18.
 */
public class Tourist {
    public static void main(String[] args) {
        try {
        Scanner scanner = new Scanner(new File("./src/datastructures/interviews/hackercup/tourist.txt"));
        while (scanner.hasNextLine()) {
            FileOutputStream outputStream = new FileOutputStream("output.txt",true);
            int testCases = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < testCases; i++) {
                int n = scanner.nextInt();
                int k = scanner.nextInt();
                long visit = scanner.nextLong();
                scanner.nextLine();
                List<Attractions> attractionsList = new ArrayList<>();
                for (int j = n; j > 0; j--) {
                    String attractionName = scanner.nextLine();
                    Attractions attractions = new Attractions(j, 0, attractionName);
                    attractionsList.add(attractions);
                }
                List<String> result = new ArrayList<>();
                StringBuilder prefix = new StringBuilder("Case #" + (i + 1) + ": ");
                System.out.print("Case #" + (i + 1) + ": ");
                prefix.append(printAttractions(attractionsList, k, visit));
                prefix.append("\n");
                byte[] resBytes = prefix.toString().getBytes();
                outputStream.write(resBytes);
                System.out.println();
            }
            outputStream.close();
        }
        scanner.close();

        } catch (FileNotFoundException e) {
        e.printStackTrace();
       } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String printAttractions(List<Attractions> attractionsList, int k, long visit) {
        PriorityQueue<Attractions> queue = new PriorityQueue<>(new AttractionComparator());
        List<Long> in = new LinkedList<>();
        queue.forEach(i -> in.add(i.visitedCount));
        for (Attractions attraction : attractionsList) {
            queue.add(attraction); //n
        }

        List<Attractions> chosenAttractions = new LinkedList<>();
        for (long v = 1; v < visit; v++) {  // v * k
            for (int m = 0; m < k; m++) {
                Attractions chosenAttraction = queue.remove();
                chosenAttraction.setVisitedCount(++chosenAttraction.visitedCount);
                queue.add(chosenAttraction);
            }
        }

        //in the vth visit
        for (int m = 0; m < k; m++) {
            Attractions chosenAttraction = queue.remove();
            chosenAttraction.setVisitedCount(++chosenAttraction.visitedCount);
            chosenAttractions.add(chosenAttraction);
        }

        StringBuilder solution = new StringBuilder();
        Collections.sort(chosenAttractions);
        for (int i = 0; i < k; i++) {
            Attractions attractions = chosenAttractions.get(i);
            System.out.print(attractions.getName());
            solution = solution.append(attractions.getName());
            if (i != k) {
                System.out.print(" ");
                solution.append(" ");
            }
        }
        return String.valueOf(solution);
    }


}

class Attractions implements Comparable<Attractions> {

    String name;
    int popularity;
    long visitedCount;

    public Attractions(int popularity, long visitedCount, String name) {
        this.popularity = popularity;
        this.visitedCount = visitedCount;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attractions that = (Attractions) o;

        return popularity == that.popularity;
    }

    @Override
    public int hashCode() {
        return popularity;
    }

    public void setVisitedCount(long visitedCount) {
        this.visitedCount = visitedCount;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Attractions o) {
        if (this.popularity > o.popularity) {
            return -1;
        } else {
            return 1;
        }
    }
}

class AttractionComparator implements Comparator<Attractions> {

    @Override
    public int compare(Attractions o1, Attractions o2) {
        if (o1.visitedCount == o2.visitedCount) {
            if (o1.popularity > o2.popularity) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (o1.visitedCount < o2.visitedCount) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}