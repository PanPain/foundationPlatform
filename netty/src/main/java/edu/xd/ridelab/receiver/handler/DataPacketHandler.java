package edu.xd.ridelab.receiver.handler;

import edu.xd.ridelab.foundationplatform.definition.MacDataType;
import edu.xd.ridelab.receiver.protocol.MacProtocol;
import edu.xd.ridelab.receiver.util.ByteUtils;
import edu.xd.ridelab.receiver.util.PropertiesUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author PanTeng
 * @version 1.0
 * @description 往kafka发送数据
 * @file DataPacketHandler.java
 * @date 2018/5/6
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */
@Slf4j
public class DataPacketHandler extends SimpleChannelInboundHandler<MacProtocol> {

    private String TOPIC;
    private static final KafkaProducer<String, Object> PRODUCER = createProducer();

    @Override
    protected void channelRead0(ChannelHandlerContext context, MacProtocol protocol) {

        if (protocol.getMacDataType() != MacDataType.RECEIVER_REMOTE_CONTROL) {
            logger.debug("{}", protocol);
            processNormal(context, protocol);
        } else {
            processRemoteControl(context, protocol);
        }
    }

    private void processRemoteControl(ChannelHandlerContext context, MacProtocol protocol) {
        // 对时
        // 2001 length " 3 length seconds "

        val time = (System.currentTimeMillis() / 1000 + "\r\n").getBytes(CharsetUtil.UTF_8);

        val content = ByteUtils.concat(
            ByteUtils.intToBytes(3),
            ByteUtils.intToBytes(time.length),
            time
        );

        val response = ByteUtils.concat(
            ByteUtils.intToBytes(2001),
            ByteUtils.intToBytes(content.length),
            content
        );

        context.channel().writeAndFlush(Unpooled.copiedBuffer(response)).addListener(ChannelFutureListener.CLOSE);
    }


    /**
     * @param
     * @return
     * @description 协议类型不是远程控制  可以正常处理 (按照类型转换成对象）
     * @author PanTeng
     * @date 下午2:05  2018/5/11
     */
    private void processNormal(ChannelHandlerContext context, @NonNull MacProtocol protocol) {
        //不同数据发往不同topic
        switch (protocol.getMacDataType()) {
            case RECEIVER_BASIC:
            case PLACE_BASIC:
            case MANUFACTURER_BASIC:
                TOPIC = PropertiesUtils.getProperty("basic_info_topic");
                break;
            case STATION_MAC:
            case AP_MAC:
            case VIRTUAL_IDENTITY:
                TOPIC = PropertiesUtils.getProperty("mac_topic");
                break;
            case RECEIVER_STATUS:
                TOPIC = PropertiesUtils.getProperty("heartbeat_topic");
                break;
            default: //TODO 配置其他类型数据发往的topic
        }
        for (Object o : protocol.parserContent2VOList()) {
            val record = new ProducerRecord<String, Object>(TOPIC, protocol.getMacDataType().name(), o);
            PRODUCER.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    System.out.println("The offset of the record we just sent is: " + metadata.offset());
                }
            });
        }
    }


    private static KafkaProducer<String, Object> createProducer() {
        val properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "edu.xd.ridelab.receiver.serialize.SimpleObjectSerializer");
        properties.put("zookeeper.connect", PropertiesUtils.getProperty("zookeeper.connect"));
        properties.put("bootstrap.servers", PropertiesUtils.getProperty("bootstrap.servers"));
        return new KafkaProducer<>(properties);
    }
}

