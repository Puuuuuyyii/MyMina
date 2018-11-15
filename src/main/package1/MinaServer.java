import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MinaServer {
    private final static Log logger = LogFactory.getLog(MinaServer.class);
    public static final int PORT = 22222;//定义监听端口

    public static void main(String[] args) throws IOException {
        //创建一个非阻塞的Server端socket，用NIO
        IoAcceptor acceptor = null;
        try {
            acceptor = new NioSocketAcceptor();
            //创建接收数据的过滤器
            DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
            //设定这个过滤器将一行一行的读取数据（传输的以换行符为标识的数据）,TextLineCodecFactory编解码器工厂对字符串进行编解码处理
            chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
            //设置读取数据的缓冲区大小
            acceptor.getSessionConfig().setReadBufferSize(2048);
            //读写通道10秒内无操作进入空暇状态
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
            //指定编码过滤器
            //指定业务逻辑处理器
            acceptor.setHandler(new Handler());
            //设置端口号
            acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
            acceptor.bind();//启动监听
            logger.info("服务端启动成功....端口号为" + PORT);
            System.out.println("MinaServer is listen on : " + PORT);
        } catch (IOException e) {
            logger.error("服务端启动异常...", e);
            e.printStackTrace();
        }
    }
}
