package erph.kafkahomework.kafkaclasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import erph.kafkahomework.dto.TaxiSignalDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class TaxiSignalDeserializer implements Deserializer<TaxiSignalDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TaxiSignalDto deserialize(String s, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), TaxiSignalDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to TaxiSignalDto");
        }
    }
}
