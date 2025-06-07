package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur;

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
public class PopUpDetailJaminanDto {
    private UUID idCrosJaminanDebitur;
    private String noCollateral;
    private String jenisJaminan;
    private String detailJaminan;
    private BigDecimal nilaiPasarOcur;
    private BigDecimal hakTanggunganOcur;
    private BigDecimal nilaiPasarIdr;
    private BigDecimal hakTanggunganIdr;
    private String mataUang;
}
