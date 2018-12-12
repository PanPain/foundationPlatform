package edu.xd.ridelab.receiver.handler;

import edu.xd.ridelab.receiver.protocol.MacProtocol;
import edu.xd.ridelab.receiver.util.SocketAddressUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file DataPacketDecoder.java
 * @date 2018/5/6
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */
@Slf4j
public class DataPacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf in, List<Object> out) {
        if (isFullyReceived(in)){

            final byte[] bytes = new byte[in.readableBytes()];

            in.readBytes(bytes);
            logger.trace("received bytes (length {}): {}", bytes.length, bytes);

            final String ip = SocketAddressUtils.parseIp(context.channel().remoteAddress());
            logger.trace("remote ip : {}", ip);

            final MacProtocol protocol = new MacProtocol(bytes, ip);
            logger.trace("decoded {}", protocol);

            out.add(protocol);
        }

    }

    /**
     * @description 判断数据是否接受完全
     * @author PanTeng
     * @date 下午1:21  2018/5/11
     * @param
     * @return
     */
    private boolean isFullyReceived(ByteBuf in){
        // type(4) + length(4) + content
        return (
            in.readableBytes() >= 8 &&
                in.readableBytes() >= in.getInt(4) + 8
        );
    }
}
