package edu.xd.ridelab.heartbeat;

import edu.xd.ridelab.receiver.handler.DataPacketHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author PanTeng
 * @Description
 * @file UdpServerBootstrap.java
 * @date 2018/5/18
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
@Slf4j
public class UdpServerBootstrap {

    public void start(final int port) {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            val bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).
                channel(NioDatagramChannel.class).
                option(ChannelOption.SO_BROADCAST, true).
                handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) {
                        channel.pipeline().
                            addLast(new LoggingHandler()).
                            addLast(new DatagramDecoder()).
                            addLast(new DataPacketHandler());
                    }
                });

            bootstrap.bind(port).sync().channel().closeFuture().await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
