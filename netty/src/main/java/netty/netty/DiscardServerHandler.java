package netty.netty;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 服务端处理通道。这个是打印一下请求的内容。后续代码的修改也可以对客户端的请求进行响应。
 * 继承ChannelHandlerAdapter，这个类实现了ChannelHandler接口，ChannelHandler提供了许多事件处理的接口方法，
 * 对这些方法，可以进行覆盖。
 * 现在仅仅只需要继承ChannelHandlerAdapter类而不是你自己去实现接口方法。
 * @author m2399
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

	private final Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 覆盖了channelRead()事件处理方法。channelRead()方法只是ChannelHandler接口的一个 事件 方法。
	 * 这个方法用于在收到消息时被调用。
	 * @param ctx
     *            通道处理的上下文信息
     * @param msg
     *            接收的消息
	 */
	@Override
	public  void channelRead(ChannelHandlerContext ctx, Object msg){
		
		try{
			ByteBuf in = (ByteBuf) msg;
			String intLogic = in.toString(CharsetUtil.UTF_8);
			System.out.println(intLogic+"login");
		}catch(Exception e){
			logger.error("netty服务端处理事件异常");
		}finally{
			/**
			 * ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放。
             * 请记住处理器的职责是释放所有传递到处理器的引用计数对象。
			 */
			//抛弃收到的数据
			ReferenceCountUtil.release(msg);
		}
		
		
		
	}
	
	 @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	        /**
	         * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO
	         * 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来 并且把关联的 channel
	         * 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不 同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。
	         */
	        // 出现异常就关闭
	        cause.printStackTrace();
	        ctx.close();
	    }
}
