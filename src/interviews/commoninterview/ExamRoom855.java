package interviews.commoninterview;

public class ExamRoom855 {

    int[] seats;
    boolean empty;

    public static void main(String[] args) {
        ExamRoom855 r = new ExamRoom855(10);
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Leave: 0");
        r.leave(0);
        System.out.println("Leave: 4");
        r.leave(4);
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        System.out.println("Seat: " + r.seat());
        r.leave(0);
    }

    public ExamRoom855(int N) {
        seats = new int[N];
        empty = true;
    }

    public int seat() {
        int s = findSeat(seats, 0, 1);
        seats[s] = 1;
        return s;
    }

    public void leave(int p) {
        seats[p] = 0;
    }

    private int findSeat(int[] seats, int start, int end) {
        int seat = -1;
        int max = Integer.MIN_VALUE;
        while (start < end && end < seats.length) {
            while (start < seats.length && seats[start] == 1) {
                start++;
            }
            end = start + 1;
            //find the left most unoccupied;
            while (end < seats.length && seats[end] == 0) {
                end++;
            } //find the right most occupied seat after this
            end = end == seats.length ? end - 1 : end;
            int lmax = end - start;

            if (end == seats.length - 1) {  // ..1...end
                if (start == 0) return start;
                if (lmax > max + 1 && seats[end] == 0) return end;
            }

            if (lmax > max + 1) {
                if (start == 0) {
                    seat = start;
                } else {
                    seat = lmax % 2 == 0 ? (end + start) / 2 - 1: ((end + start) / 2);
                }
            }
            max = lmax;
            start = end + 1;
            end = start + 1;
        }

        return seat;
    }
}
