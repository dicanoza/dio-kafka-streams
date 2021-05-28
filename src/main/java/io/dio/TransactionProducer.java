package io.dio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dio.dto.Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class TransactionProducer {

    public static void main(final String[] args) throws Exception {
        final String bootstrapServers = "localhost:9092";
        UUID accountId = UUID.fromString("985A3FF-305C-4F3F-9A6D-55EA9EF646A0");

        List<UUID> transactions = Arrays.asList(UUID.fromString("D11B5E46-6B76-4C1C-A186-D679D42A8BA1"),
                UUID.fromString("653F9B73-3A93-42C3-9637-391AB475D6AE"),
                UUID.fromString("7FB5B497-F33C-46A8-9E8F-0B4F29166755"));

        List<Transaction> inputValues = Arrays.asList(
                new Transaction(transactions.get(0), BigDecimal.valueOf(10.5), accountId),
                new Transaction(transactions.get(1), BigDecimal.valueOf(2.5), accountId),
                new Transaction(transactions.get(2), BigDecimal.valueOf(7.3), accountId)
        );

        final Properties producerConfig = new Properties();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
        producerConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        final KafkaProducer<UUID, JsonNode>
                producer =
                new KafkaProducer<>(producerConfig, new UUIDSerializer(), new JsonSerializer());

        final Random random = new Random();
        for (Transaction i : inputValues) {
            JsonNode jsonNode = new ObjectMapper().valueToTree(i);
            producer.send(new ProducerRecord<UUID, JsonNode>("streams-transactions-ledger",
                    i.getAccountId(), jsonNode));
            Thread.sleep(500L);
        }
    }
}
