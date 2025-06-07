package co.id.bca.cros.module.search.dto.defaultfilter;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DefaultFilterDto {
    private String defaultKantorWilayahCode;
    private String defaultKantorWilayahName;
    private String defaultKantorCabangCode;
    private String defaultKantorCabangName;
    private BigDecimal totalOutstandingMaxBalance;
}
