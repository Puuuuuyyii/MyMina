import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ArrayCopyDemo {
    public static void main(String[] args) {
        Log logger = LogFactory.getLog(Dsss.class);
        String[] str1 = {"a", "b", "c", "d", "e"};
        String[] str2 = new String[5];
        System.arraycopy(str1, 0, str2, 0, str1.length);
        System.out.println(str1 == str2);
        str1[0] = "132" +
                "2";
        System.out.println(str1[0]);
        System.out.println(str2[0]);

    }
}

