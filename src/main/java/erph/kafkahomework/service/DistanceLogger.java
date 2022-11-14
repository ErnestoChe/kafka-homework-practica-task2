package erph.kafkahomework.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DistanceLogger {

    @KafkaListener(
            topics = "output",
            containerFactory = "taxiDistanceKafkaListenerContainerFactory",
            groupId = "spring_application")
    public void taxiDistanceListener(String distanceDto) {
        System.out.println("Distance in logger " + distanceDto);
    }

}
