    /*public static void main(String[] args) {
        String s1 = new String("Hello Word!");
        String s2 = new String(s1);
        String s3 = s1;
        s1 = null;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }*/
    class Outer123 {
        public int num = 10;
        class Inner {
            public int num = 20;
            public void show() {
                int num = 30;
                System.out.println(num);
                System.out.println(new Outer123().new Inner().num);
                System.out.println(new Outer123().num);
            }
        }
    }

    public class InnerClassTest {
        public static void main(String[] args) {
            Outer123.Inner oi = new Outer123().new Inner();
            oi.show();
        }
    }
