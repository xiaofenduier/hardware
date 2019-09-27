package com.sunshine.hardware.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<byte[]> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] in) throws Exception {
        //打印服务端的发送数据
        System.out.println(in);
    }
}
