package sorting;

/**
 * Created by chaitra.kr on 01/05/16.
 */
public class Cat {

    double weight;
    double tailSize;
    int age;

    public Cat(double w,double t,int a){
        weight =w;
        tailSize =t;
        age =a;
    }

    public double getWeight() {
        return weight;
    }

    public double getTailSize() {
        return tailSize;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return "Age : "+age+","+" Weight : "+weight+" Tail Length : "+tailSize;
    }
}
