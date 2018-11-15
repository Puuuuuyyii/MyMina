import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Dsss {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        Log logger = LogFactory.getLog(Dsss.class);
        Demo c1 = new Demo();
        Demo c2 = new Demo();
        Demo c3 = new Demo();
        Demo[] a = {c1, c2, c3};
        Demo[] b = new Demo[3];
        System.arraycopy(a, 0, b, 0, 3);
        System.out.println(a[0] == b[0]);
        System.out.println(a[1] == b[1]);
        System.out.println(a[0].handler == b[0].handler);
        System.out.println(a[0].handler);
        System.out.println(b[0].handler);
        b[0].handler = (Handler) a[0].handler.clone();
        System.out.println(a[0].handler);
        System.out.println(b[0].handler);
        Integer a1 = 30;
        Integer a2 = 30;
        System.out.println(a1 == a2);
        logger.info("已经输出结果");
        int s = (int) 0xff;

        System.out.println(s);

    }
}
