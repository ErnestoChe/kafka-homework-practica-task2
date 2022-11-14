package erph.kafkahomework.storage;

import erph.kafkahomework.dto.TaxiSignalDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TaxiDistanceStorage {

    private final HashMap<String, Double> traveledDistanceStorage = new HashMap<>();
    private final HashMap<String, TaxiSignalDto> lastCheckpointStorage = new HashMap<>();

    public Double updateDistance(TaxiSignalDto signal) {
        if (traveledDistanceStorage.get(signal.getId()) == null) {
            traveledDistanceStorage.put(signal.getId(), 0D);
        } else {
            Double aDouble = traveledDistanceStorage.get(signal.getId());
            TaxiSignalDto lastKnownSignal = lastCheckpointStorage.get(signal.getId());
            traveledDistanceStorage.put(signal.getId(),
                    distanceBetweenSignals(signal, lastKnownSignal) + aDouble);
        }
        lastCheckpointStorage.put(signal.getId(), signal);
        return traveledDistanceStorage.get(signal.getId());
    }

    private Double distanceBetweenSignals(TaxiSignalDto oldSignal, TaxiSignalDto newSignal) {
        return Math.sqrt(Math.pow(oldSignal.getCoordinateX() - newSignal.getCoordinateX(), 2) +
                Math.pow(oldSignal.getCoordinateY() - newSignal.getCoordinateY(), 2));
    }
}
