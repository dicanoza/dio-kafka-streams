package io.dio.dto;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

class JsonSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper =
            new ObjectMapper();

    /** Default constructor needed by Kafka */
    public JsonSerializer() {}

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {}

    @Override
    public byte[] serialize(String topic, T type) {
        try {
            return objectMapper.writeValueAsString(type).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {}
}
