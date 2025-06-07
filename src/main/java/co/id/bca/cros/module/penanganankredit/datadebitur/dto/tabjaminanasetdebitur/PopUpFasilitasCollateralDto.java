package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpFasilitasCollateralDto {
    private UUID idCrosJaminanDebitur;
    private String namaDebitur;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private BigDecimal balanceOcur;
    private BigDecimal balanceIdr;
    private String mataUang;
}
