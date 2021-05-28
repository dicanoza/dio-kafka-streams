package io.dio;

import io.dio.dto.JsonSerdes;
import io.dio.dto.MixedTransaction;
import io.dio.dto.SepaTransaction;
import io.dio.dto.Transaction;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;

import java.util.Properties;
import java.util.UUID;

public class TransactionTablesJoiner {

    static final String sepaTopic = "streams-transactions-sepa";

    static final String transactionTopic = "streams-transactions-ledger";

    static final String outputMixedTopic = "streams-transactions-mixed";

    public static void main(final String[] args) {
        final String bootstrapServers = "localhost:9092";

        final Properties streamsConfiguration = getStreamsConfiguration(bootstrapServers);
        StreamsBuilder builder = new StreamsBuilder();
        final KTable<UUID, Transaction> transactionStream = builder.stream(transactionTopic,
                Consumed.with(Serdes.UUID(), JsonSerdes.Transaction())).selectKey((k, v) -> v.getTransactionId()).toTable();

        final KTable<UUID, SepaTransaction> sepaStream = builder.stream(sepaTopic,
                Consumed.with(Serdes.UUID(), JsonSerdes.SepaTransaction())).selectKey((k,v) -> v.getTransactionId()).toTable();

        ValueJoiner<SepaTransaction, Transaction, MixedTransaction> valueJoiner = (sepa, transaction) -> {
            return new MixedTransaction(transaction.getTransactionId(),transaction.getValue(),sepa.getAccountId(),sepa.getInitiatorIban(), sepa.getRecipientIban());
        };

        Joined<UUID, SepaTransaction,Transaction> streamJoined = Joined.with(Serdes.UUID(), JsonSerdes.SepaTransaction(), JsonSerdes.Transaction());


        sepaStream.join(transactionStream, valueJoiner).toStream()
                .selectKey((k,v) -> v.getAccountId()).to(outputMixedTopic, Produced.with(Serdes.UUID(), JsonSerdes.MixedTransaction()));

        final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);

        streams.cleanUp();
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    static Properties getStreamsConfiguration(final String bootstrapServers) {
        final Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "mixer-stateful");
        streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "mixer-client-stateful");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.UUID().getClass().getName());
        streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
        streamsConfiguration.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, "/tmp/kafka-tmp");
        return streamsConfiguration;
    }

}
