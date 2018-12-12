package edu.xd.ridelab.receiver;

import edu.xd.ridelab.receiver.handler.DataPacketDecoder;
import edu.xd.ridelab.receiver.handler.DataPacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author PanTeng
 * @version 1.0
 * @file UdpServerBootstrap.java
 * @date 2018/5/5
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */
@Slf4j
public class TcpServerBootstrap {

    public void start(final int port) throws InterruptedException {
        final EventLoopGroup bossGroup = (Epoll.isAvailable()?new EpollEventLoopGroup(1):new NioEventLoopGroup(1));
        final EventLoopGroup workersGroup = (Epoll.isAvailable()?new EpollEventLoopGroup():new NioEventLoopGroup());

        try {
            val bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workersGroup).
                channel((Epoll.isAvailable()? EpollServerSocketChannel.class:NioServerSocketChannel.class)).
                option(ChannelOption.SO_REUSEADDR, true).
                option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT).
                option(ChannelOption.SO_RCVBUF, 32 * 1024).
                childOption(ChannelOption.SO_KEEPALIVE, true).
                childOption(ChannelOption.SO_REUSEADDR, true);

            bootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) {
                    channel.pipeline().
                        addLast(new LoggingHandler()).
                        addLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS)).
                        addLast(new DataPacketDecoder()).
                        addLast(new DataPacketHandler());
                }
            });

            val binding = bootstrap.bind(port).
                addListener((GenericFutureListener<Future<Void>>) future ->
                    logger.info("listening at port {}", port)
                ).
                sync();

            binding.channel().closeFuture().sync();
        } finally {
            workersGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
