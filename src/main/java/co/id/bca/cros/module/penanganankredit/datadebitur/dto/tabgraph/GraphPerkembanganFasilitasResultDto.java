package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GraphPerkembanganFasilitasResultDto {
    private List<GraphPerkembanganFasilitasDto> graphPerkembanganFasilitas;
}
