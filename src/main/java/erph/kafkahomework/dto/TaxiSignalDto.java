package erph.kafkahomework.dto;

import lombok.Data;

@Data
public class TaxiSignalDto {

    private String id;
    private Integer coordinateX;
    private Integer coordinateY;

    public String toString(){
        return "Id: " + id + ", coordinateX: " + coordinateX + ", coordinateY: " + coordinateY;
    }

}
