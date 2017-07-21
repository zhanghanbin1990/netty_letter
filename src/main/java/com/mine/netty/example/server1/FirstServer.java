package com.mine.netty.example.server1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhanghanbin on 2017/7/19.
 */
public class FirstServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//一个线程接收
        EventLoopGroup workerGroup = new NioEventLoopGroup();//一个线程处理
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new FirstServerInit());
            ChannelFuture future =  serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
