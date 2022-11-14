package erph.kafkahomework.service;

import erph.kafkahomework.dto.TaxiDistanceDto;
import erph.kafkahomework.dto.TaxiSignalDto;
import erph.kafkahomework.storage.TaxiDistanceStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxiDistanceCalculatorService {

    private final TaxiDistanceStorage taxiDistanceStorage;
    private final KafkaInputService inputService;

    @KafkaListener(
            topics = "input",
            containerFactory = "taxiSignalKafkaListenerContainerFactory",
            groupId = "spring_application")
    public void taxiSignalListener(TaxiSignalDto signalDto) {
        TaxiDistanceDto distanceDto = TaxiDistanceDto.builder()
                .distance(taxiDistanceStorage.updateDistance(signalDto))
                .id(signalDto.getId())
                .build();
        inputService.sendDistance(distanceDto);
    }

}
