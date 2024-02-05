package org.coding.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class MyNetty {
    public static void main(String[] args) {

    }

    public static void server() throws InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup wordGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, wordGroup)
                .channel(NioSctpServerChannel.class)
                .option(ChannelOption.SO_BACKLOG, 8)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("socket init");
                        //向looGroup里面添加handler
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });


        ChannelFuture channelFuture = bootstrap.bind(8001).sync();

        //添加监听器
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("监听端口ing port:8001");
                }
            }
        });

        //TODO ???
        channelFuture.channel().closeFuture().sync();
        wordGroup.shutdownGracefully();
        boosGroup.shutdownGracefully();
    }
}
