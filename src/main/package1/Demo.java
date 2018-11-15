public class Demo implements Cloneable {
    @Override
    protected Object clone() {
        return new Demo() ;
    }


    public Handler handler;
    public Demo() {
         handler = new Handler();
    }

    public static void main(String[] args) {

    }
}
