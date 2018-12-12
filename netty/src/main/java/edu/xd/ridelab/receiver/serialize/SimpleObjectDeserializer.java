package edu.xd.ridelab.receiver.serialize;

import edu.xd.ridelab.receiver.util.SerializeUtils;
import lombok.NonNull;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author PanTeng
 * @Description
 * @file SimpleObjectDeserializer.java
 * @date 2018/5/15
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class SimpleObjectDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, @NonNull byte[] data) {
            return SerializeUtils.deserialize(data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}
