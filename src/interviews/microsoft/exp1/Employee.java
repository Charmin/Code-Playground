package interviews.microsoft.exp1;

import java.util.*;

/**
 * Two tables employee (contains name and Id) and employee details having work experience history (contains Id, fromYear, toYear) – find out all the employees who worked w/o career break.
 * Java implementation for the same.
 * From the same table list the name, fromYear, toYear for all employee.
 */

//Could be similar to merge intervals if that is expected
public class Employee {

    private static Map<Integer, String> empIdMap = new HashMap<>();
    private static List<EmployeeDetails> empDetails = new ArrayList<>();

    //Using a map for the tables for the 2 tables
    public static void main(String[] args) {
        populateTestDate();
        List<String> careerBreak = getEmpsWithCareerBreak();
        careerBreak.forEach(System.out::println);
    }

    private static void populateTestDate() {
        empIdMap.put(1, "A");
        empIdMap.put(2, "B");
        empIdMap.put(3, "C");
        empIdMap.put(4, "D");
        empIdMap.put(5, "E");

        int i = 0;
        for (Map.Entry<Integer, String> m : empIdMap.entrySet()) {
            EmployeeDetails emp = new EmployeeDetails(m.getKey(), 2001 + i, 2004 + i);
            empDetails.add(emp);
            i++;
        }
        empDetails.add(new EmployeeDetails(1, 2009, 2011));
        empDetails.add(new EmployeeDetails(2, 2010, 2015));
    }

    private static List<String> getEmpsWithCareerBreak() {
        Map<Integer, Integer> breakCount = new HashMap<>();
        for (EmployeeDetails emp : empDetails) {
            breakCount.putIfAbsent(emp.id, 0);
            breakCount.put(emp.id, breakCount.get(emp.id)+1);
        }

        List<String> withCareerBreak = new ArrayList<>();
        for (Map.Entry<Integer, Integer> c : breakCount.entrySet()) {
            if (c.getValue() > 1) {
                withCareerBreak.add(empIdMap.get(c.getKey()));
            }
        }
        return withCareerBreak;
    }

}

class EmployeeDetails {
    int id;
    private long fromYear;
    private long toYear;

    public EmployeeDetails(int id, long from, long to) {
        this.id = id;
        this.fromYear = from;
        this.toYear = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDetails that = (EmployeeDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
