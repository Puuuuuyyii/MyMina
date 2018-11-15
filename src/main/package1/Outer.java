interface Inter {void show();}
public class Outer {
    public static Inter method(){
        System.out.println("jjj");
        return new Inter() {
            @Override
            public void show() {

            }
        };
    }
}
    class OuterDemo {
        public static void main(String[] args) {
            Outer.method().show();
        }
    }
