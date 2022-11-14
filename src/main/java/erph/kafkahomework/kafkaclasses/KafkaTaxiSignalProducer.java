package erph.kafkahomework.kafkaclasses;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTaxiSignalProducer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> signalProducerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TaxiSignalSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, Object> signalProducerFactory(){
        return new DefaultKafkaProducerFactory<>(signalProducerConfig());
    }

    @Bean(name = "taxiSignalProducer")
    public KafkaTemplate<String, Object> kafkaSignalTemplate(){
        return new KafkaTemplate<>(signalProducerFactory());
    }

    //distance producer config

    public Map<String, Object> distanceProducerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, Object> distanceProducerFactory(){
        return new DefaultKafkaProducerFactory<>(distanceProducerConfig());
    }

    @Bean(name = "taxiDistanceProducer")
    public KafkaTemplate<String, Object> kafkaDistanceTemplate(){
        return new KafkaTemplate<>(distanceProducerFactory());
    }


}
