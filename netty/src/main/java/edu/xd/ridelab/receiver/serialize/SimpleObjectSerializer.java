package edu.xd.ridelab.receiver.serialize;

import edu.xd.ridelab.receiver.util.SerializeUtils;
import lombok.NonNull;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 *
 */
public class SimpleObjectSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(String topic, @NonNull Object data) {
        return SerializeUtils.serialize(data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }

}
