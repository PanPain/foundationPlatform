package edu.xd.ridelab.heartbeat;

import edu.xd.ridelab.receiver.protocol.MacProtocol;
import edu.xd.ridelab.receiver.util.SocketAddressUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DatagramPacketDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.val;

import java.net.SocketAddress;
import java.util.List;

/**
 * @author PanTeng
 * @Description
 * @file DatagramDecoder.java
 * @date 2018/5/19
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class DatagramDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) {

        final ByteBuf in = packet.content();

        final byte[] bytes = new byte[in.readableBytes()];

        in.readBytes(bytes);

        val protocol = new MacProtocol(bytes, SocketAddressUtils.parseIp(packet.sender()));

        out.add(protocol);
    }

}
