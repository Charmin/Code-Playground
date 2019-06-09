package problem_solving;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 12/03/18.
 */

public class Tsunami {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        int noIslands = Integer.parseInt(scanner.nextLine());
        //scanner.nextLine();
        Map<Integer, Island> indexToIsland = new HashMap<>();
        Map<Integer, Event> eventsMap = new HashMap<>();
        for (int i = 0; i < noIslands; i++) {
            String[] inputs = scanner.nextLine().split(" ");
            Island island = new Island(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]));
            indexToIsland.put(i, island);
        }

        Integer eventsCount = Integer.valueOf(scanner.nextLine());
        for (int j = 0; j < eventsCount; j++) {
            eventsMap.put(j, new Event(scanner.next(), scanner.nextInt()));
        }
        List<String> solutions = processEvents(eventsMap, indexToIsland);
        solutions.stream().forEach(s -> System.out.println(s));
    }

    private static List<String> processEvents(Map<Integer, Event> eventsMap, Map<Integer, Island> indexToIsland) {
        List<Island> islandsSortedByLocation = indexToIsland.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        Collections.sort(islandsSortedByLocation, (a, b) -> {
            if (a.getlocation() > b.getlocation()) {
                return 1;
            } else if (a.getlocation() < b.getlocation())
                return -1;
            else
                return 0;
        });

        List<String> solutions = new ArrayList<>();
        for (int j = 0; j < eventsMap.size(); j++) {
            Event event = eventsMap.get(j);
            switch (eventsMap.get(j).getType()) {
                case "e": //evacuate index , drown location
                    int index = event.getIndication();
                    Island island = indexToIsland.get(index);
                    if (island.isDrowned) {
                        solutions.set(j, "DROWNED");
                    } else if (islandsSortedByLocation.contains(island)) {
                        int rank = islandsSortedByLocation.indexOf(island);
                        int k = rank + 1;
                        while (k < islandsSortedByLocation.size()) {
                            if (islandsSortedByLocation.get(k).getHeight() > island.getHeight() && !islandsSortedByLocation.get(k).isDrowned) {
                                solutions.set(j, String.valueOf(islandsSortedByLocation.get(k).getlocation()));
                                break;
                            }
                            k++;
                        }
                        if(k == islandsSortedByLocation.size()) {
                            solutions.set(j, "IMPOSSIBLE");
                        }
                    }
                    break;
                case "d":
                    int location = event.getIndication();
                    Island islandDrowned = islandsSortedByLocation.get(location);
                    islandDrowned.isDrowned = true;
                    for (Map.Entry<Integer, Island> entry : indexToIsland.entrySet()) {
                        if(entry.getValue().getlocation() == location) {
                            entry.getValue().isDrowned = true;
                            break;
                        }
                    }
                    break;

            }
        }
        return solutions;
    }
}

class Island {
    int location;
    int height;
    boolean isDrowned;

    public Island(int location, int height) {
        this.location = location;
        this.height = height;
        this.isDrowned = false;
    }

    public int getlocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDrowned(boolean drowned) {
        isDrowned = drowned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Island island = (Island) o;

        if (location != island.location) return false;
        return height == island.height;
    }

    @Override
    public int hashCode() {
        int result = location;
        result = 31 * result + height;
        return result;
    }
}

class Event {
    String type;
    Integer indication;

    public Event(String type, Integer indication) {
        this.type = type;
        this.indication = indication;
    }

    public String getType() {
        return type;
    }

    public Integer getIndication() {
        return indication;
    }
}