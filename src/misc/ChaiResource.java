package misc;

import java.util.ListResourceBundle;

/**
 * Created by chaitra.kr on 08/05/16.
 */
public class ChaiResource extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                //localize the second string of each array
                {"OpenButton","OPEN"},
                {"CloseBtton","CLOSE"}
        };
    }
}
