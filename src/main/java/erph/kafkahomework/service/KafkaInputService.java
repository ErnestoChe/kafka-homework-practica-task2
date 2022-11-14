package erph.kafkahomework.service;

import erph.kafkahomework.dto.TaxiDistanceDto;
import erph.kafkahomework.dto.TaxiSignalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class KafkaInputService{

    public static final String TOPIC_INPUT = "input";
    public static final String TOPIC_OUTPUT = "output";

    @Autowired
    @Qualifier("taxiSignalProducer")
    private KafkaTemplate<String, Object> kafkaSignalTemplate;

    @Autowired
    @Qualifier("taxiDistanceProducer")
    private KafkaTemplate<String, Object> kafkaDistanceTemplate;

    public void sendInput(TaxiSignalDto signalDto) {
        if (isSignalValid(signalDto)) {
            kafkaSignalTemplate.send(TOPIC_INPUT, signalDto.getId(), signalDto);
        }
    }

    public void sendDistance(TaxiDistanceDto distanceDto){
        kafkaDistanceTemplate.send(TOPIC_OUTPUT, distanceDto.getId(), distanceDto.toString());
    }

    private boolean isSignalValid(TaxiSignalDto signalDto) {
        return isNotNull(signalDto) && isNotNull(signalDto.getId()) &&
                isNotNull(signalDto.getCoordinateX()) &&
                isNotNull(signalDto.getCoordinateY());
    }

    private boolean isNotNull(Object object) {
        return !Objects.isNull(object);
    }
}
