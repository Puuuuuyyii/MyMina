import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * java8版本
 * base64是一种能将任意Binary资料用64种字元组合成字串的方法
 */
public class Base64Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "123321123";
        final byte[] textByte = text.getBytes("UTF-8");
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
        String num2 = "132321";
        String num1 = "132321";
        System.out.println(num1 == num2);
        String string = "    \u0023 132321    ";
        System.out.println(string.trim());
        System.out.println(string == num1);
        System.out.println(string == num2);
        System.out.println(num1.getBytes());
        System.out.println(num2.getBytes());
        System.out.println(string.getBytes());
        char a = 'a';
        int i = 96;
        //规则1，定义了数据类型的变量与未定义变量的数值，结果自动转换为精度高的
        System.out.println(2 == 2 ? i : 9.0);
        //jvm给数值分配的数据类型，98并不是int类型的，而是byte/short，故结果会变为ASCII码98对应的字符
        System.out.println(2 == 2 ? 98 : a);
        //规则2，两个已经定义数据类型的变量，结果自动转换为精度高的
        System.out.println(2 == 2 ? a : i);
        //规则3，两个未定义的数值，结果自动转换为精度高的
        System.out.println(2 == 2 ? 99 : 9.0);
        System.out.println(2 == 2 ? 99 : 'b');
    }
}
