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
public class JaminanAsetDebiturDto {
    private UUID idCrosJaminanDebitur;
    private String noCollateral;
    private String jenisAgunan;
    private String detailJaminan;
    private BigDecimal nilaiPasarOcur;
    private BigDecimal nilaiTaksasiOcur;
    private BigDecimal nilaiHakTanggunganOcur;
    private BigDecimal nilaiLikuidasiOcur;
    private BigDecimal nilaiPasarIdr;
    private BigDecimal nilaiTaksasiIdr;
    private BigDecimal nilaiHakTanggunganIdr;
    private BigDecimal nilaiLikuidasiIdr;
    private String mataUang;
    private String jaminanSolid;
}
