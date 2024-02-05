package org.coding.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyClient {
    public static void client() throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        System.out.println("client init");
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8001).sync();
        channelFuture.channel().closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }
}
