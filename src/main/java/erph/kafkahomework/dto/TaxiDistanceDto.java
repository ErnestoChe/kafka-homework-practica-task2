package erph.kafkahomework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaxiDistanceDto {

    private String id;
    private Double distance;

    public String toString(){
        return "Id: " + id + ", Distance: " + distance;
    }
}
