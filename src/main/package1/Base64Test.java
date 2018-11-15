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
    }
}
