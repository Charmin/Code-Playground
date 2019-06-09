package recursions;

/**
 * Created by chaitra.kr on 03/11/16.
 */
public class CommaSep {

    String commaSep = "12356743.88";
    String result  = "123,567,43.88";
    String outPut = getCommaSeperated(commaSep);

    public static void main(String[] args) {
        for (int i =1 ;i<10;i++)  {
            System.out.println(1 << i);
        }
        int[][] dp = new int[1 << 5][5];
        System.out.println(dp.length);

        String s = "fdfsdsbdbcnnvbnv";
        System.out.println(s.substring(1));
    }


    private String getCommaSeperated(String commaSep) {
        return null;
    }
}

