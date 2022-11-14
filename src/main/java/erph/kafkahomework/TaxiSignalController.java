package erph.kafkahomework;

import erph.kafkahomework.dto.TaxiSignalDto;
import erph.kafkahomework.service.KafkaInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "taxi/api")
@RequiredArgsConstructor
public class TaxiSignalController {

    private final KafkaInputService inputService;

    @PostMapping(value = "/signal")
    public void getTaxiSignal(@RequestBody TaxiSignalDto signalDto) {
        inputService.sendInput(signalDto);
    }

}
