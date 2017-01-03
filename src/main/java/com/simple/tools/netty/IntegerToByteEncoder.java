package com.simple.tools.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntegerToByteEncoder  extends MessageToByteEncoder<Integer> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
		System.out.println("IntegerToByteEncoder msg is " + msg.toString());
		out.writeInt(msg);
	}

}
