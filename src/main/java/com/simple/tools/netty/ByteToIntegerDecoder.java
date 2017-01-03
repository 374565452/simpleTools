package com.simple.tools.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ByteToIntegerDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("the readable bytes length is --"+in.readableBytes());
		if(in.readableBytes()>=4){
			//start 从这里开始，得到最原始的二进制数据
			byte[] msgBytes = new byte[in.readableBytes()];
			int readerIndex = in.readerIndex();
			System.out.println("the readerIndex is --"+readerIndex);
			in.getBytes(readerIndex, msgBytes);
			//end 到这里结束，得到最原始的二进制数据
			//byte[] buffer=in.array();
			for (byte b : msgBytes) {
				System.out.println(String .format("the b is %d ",b));
			}
			int read=in.readInt();
			
			System.out.println(String.format("in the byteToIntegerDecoder read the int is %d ---", read));
			out.add(read);
			
		}
	}

}
