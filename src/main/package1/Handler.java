import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Handler extends IoHandlerAdapter implements Cloneable {
    @Override
    protected Object clone() {
        return new Handler();
    }

    public static Map<String, String> onlineSet = new ConcurrentHashMap<>();
    //当session对象被创建的时候调用，对TCP连接来说，连接被接收的时候调用，但是要注意此时TCP连接
    //并没有建立，也就是说连接的对象IoSession被创建完成的时候，回调这种方法
    //对于UDP连接来说，当有数据包收到的时候回调这种方法，这是因为UDP是无连接的
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("客户端与服务器创建连接... " + session.getId() + " remoteAddress: " + session.getRemoteAddress() + " localAddress" + session.getLocalAddress());
    }
    //当一个客户端连接进入时
    //当连接被打开时调用，总是在sessionCreated()方法之后被调用，对于TCP来说，它是在连接被建立之后调用
    //可以进行一些认证操作、发送数据等
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        onlineSet.put(session.getRemoteAddress().toString(), session.getRemoteAddress().toString());

        System.out.println("incoming client : " + session.getRemoteAddress());
        System.out.println("连接数为： " + onlineSet.size());
    }
    //当客户端发送的消息到达时，普通情况下messqge是一个IoBuffer类，如果使用了协议编解码器，能够强制转换为需要的类型
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String s = message.toString();
        System.out.println(session.getRemoteAddress() + "   client send message is : " + s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd mm:hh:ss");
        Date date = new Date();
        session.write(simpleDateFormat.format(date));//返回当前时间的字符串
        System.out.println("message written...");
    }
    //当发送消息成功时调用，发送成功之后，并不是发送消息的时候
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("消息发送成功！");
    }
    //对于TCP来说，连接被关闭时候调用
    //对于UDP来说，IoSession的close()方法被调用时才会调用
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("one client closed");
    }
    //在IoSession的通道进入空暇状态时候调用，对于UDP协议来说始终不会调用
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("服务器进入空暇状态");
    }
    //在mina框架自身出现异常时回调，一般是关闭IoSession调用
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        session.close(true);
    }
}
