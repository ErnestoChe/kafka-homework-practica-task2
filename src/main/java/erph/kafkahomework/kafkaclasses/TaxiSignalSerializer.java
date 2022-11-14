package erph.kafkahomework.kafkaclasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import erph.kafkahomework.dto.TaxiSignalDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class TaxiSignalSerializer implements Serializer<TaxiSignalDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TaxiSignalDto data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing TaxiSignalDto to byte[]");
        }
    }
}
