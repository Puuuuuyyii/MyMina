import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyDecoder extends CumulativeProtocolDecoder {
    public static Log logger = LogFactory.getLog(MyDecoder.class);

    /**
     * 这个方法的返回值是重点
     * 1、 当内容刚好时，返回false,告知父类接收下一批内容
     * 2、 内容不够时需要下一批发过来的内容，此时返回false，这样父类CumulativeProtocolDecoder会将内容放进IoSession中，等下次来数据后就自己主动拼装再交给本类的doDecode
     * 3、 当内容多时，返回ture,由于需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
     * @param session
     * @param in
     * @param out
     * @return
     * @throws Exception
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        logger.info("in.remaining:" + in.remaining());
        if (in.remaining() > 0) {
            //有数据时读取前8字节推断消息长度
            byte[] sizeBytes = new byte[8];
            in.mark();//标记当前位置，以便reset
            //这里前数据包的长度是保存在第4-8字节中
            in.get(sizeBytes, 0, 8);

        }
        return false;
    }
}
