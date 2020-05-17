package interviews.tricks;

public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("408", "5"));
    }

    public static String addStrings(String num1, String num2) {

        int n1 = num1.length();
        int n2 = num2.length();

        int i = n1 - 1;
        int j = n2 - 1;

        int carry = 0;
        int small = n1 > n2 ? j : i;

        StringBuilder res = new StringBuilder();

        while (small >= 0) {
            int sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + carry;
            if (sum >= 10) {
                carry = sum / 10;
            } else {
                carry = 0;
            }
            res.append(sum % 10);
            small--;
            i--;
            j--;
        }

        int rem = 0;

        while (i >= 0) {
            rem = num1.charAt(i) - '0' + carry;
            if (rem >= 10) {
                carry = rem / 10;
            } else {
                carry = 0;
            }
            res.append(rem % 10);
            i--;
        }

        while (j >= 0) {
            rem = num2.charAt(j) - '0' + carry;
            if (rem >= 10) {
                carry = rem / 10;
            } else {
                carry = 0;
            }
            res.append(rem % 10);
            j--;
        }

        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();

    }

    public String addStringsNotMine(String num1, String num2) {
        int n1 = num1.length() - 1, n2 = num2.length() - 1, cnt = 0;
        if (n1 < 0) return n2 < 0 ? num1 : num2;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0) {
            int tmp = (n1 < 0 ? 0 : num1.charAt(n1--) - '0') + (n2 < 0 ? 0 : num2.charAt(n2--) - '0') + cnt;
            sb.append(tmp % 10);
            cnt = tmp / 10;
        }
        return cnt == 0 ? sb.reverse().toString() : cnt + sb.reverse().toString();
    }
}
