package io.dio.dto;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class JsonSerdes {
    public static Serde<Transaction> Transaction() {
        JsonSerializer<Transaction> serializer = new JsonSerializer<>();
        JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<io.dio.dto.SepaTransaction> SepaTransaction() {
        JsonSerializer<io.dio.dto.SepaTransaction> serializer = new JsonSerializer<>();
        JsonDeserializer<io.dio.dto.SepaTransaction> deserializer = new JsonDeserializer<>(io.dio.dto.SepaTransaction.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
    public static Serde<io.dio.dto.MixedTransaction> MixedTransaction() {
        JsonSerializer<io.dio.dto.MixedTransaction> serializer = new JsonSerializer<>();
        JsonDeserializer<io.dio.dto.MixedTransaction> deserializer = new JsonDeserializer<>(io.dio.dto.MixedTransaction.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
