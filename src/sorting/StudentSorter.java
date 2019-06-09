package sorting;

import java.util.*;
/**
 * Created by chaitra.kr on 09/06/16.
 */
public class StudentSorter {

    public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    int testCases = Integer.parseInt(in.nextLine());

    List<Student> studentList = new ArrayList<Student>();
    studentList.add(new Student(33,"Rumpa",3.68));
    studentList.add(new Student(85,"Ashish",3.85));
    studentList.add(new Student(56,"Samiha",3.75));
    studentList.add(new Student(19,"Samara",3.75));
    studentList.add(new Student(22,"Fahim",3.76));

    sortStudent(studentList);

    for(Student st: studentList){
        System.out.println(st.getFname());
    }
    }

    public static void sortStudent(List<Student> studentList){
        Comparator<Student> comparator = (Student s1, Student s2) -> {
            if(s1.getCgpa()!=s2.getCgpa())
                return Double.valueOf(s2.getCgpa()).compareTo(s1.getCgpa());
            else if(!s1.getFname().equals(s2.getFname())){
                return s1.getFname().compareTo(s2.getFname());
            }
            else return Integer.valueOf(s1.getId()).compareTo(s2.getId());
        };

        studentList.sort(comparator);
        HashSet<Integer> s = new HashSet();
        s.add(3);
        s.add(4);
        s.add(5);
        s.add(4);

        List<Integer> sl = new LinkedList<>();
        sl.add(3);
        sl.add(4);
        sl.add(5);
        sl.add(4);

        System.out.println("Set " + s);
        System.out.println("List " + sl);



    }
}


class Student {
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }
}