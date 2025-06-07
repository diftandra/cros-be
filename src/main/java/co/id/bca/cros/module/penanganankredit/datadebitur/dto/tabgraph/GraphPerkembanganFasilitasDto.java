package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GraphPerkembanganFasilitasDto {
    private String jenisFasilitas;
    private String bulan;
    private BigDecimal outstanding;
}
