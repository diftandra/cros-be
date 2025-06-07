package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasCol5ResultDto {
    private List<PosisiFasilitasCol5Dto> posisiFasilitasCol5;
    private BigDecimal outstandingTotal;
    private BigDecimal bungaTotal;
    private BigDecimal dendaTotal;
    private BigDecimal outstandingEquivalentIdr;
    private BigDecimal bungaEquivalentIdr;
    private BigDecimal dendaEquivalentIdr;
}
