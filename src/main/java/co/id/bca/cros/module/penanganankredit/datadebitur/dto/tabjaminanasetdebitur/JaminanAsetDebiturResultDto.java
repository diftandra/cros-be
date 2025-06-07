package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur;

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
public class JaminanAsetDebiturResultDto {
    private List<JaminanAsetDebiturDto> jaminanAsetDebitur;
    private BigDecimal nilaiPasarTotal;
    private BigDecimal nilaiTaksasiTotal;
    private BigDecimal nilaiHakTanggunganTotal;
    private BigDecimal nilaiLikuidasiTotal;
    private BigDecimal nilaiPasarEquivalentIdr;
    private BigDecimal nilaiTaksasiEquivalentIdr;
    private BigDecimal nilaiHakTanggunganEquivalentIdr;
    private BigDecimal nilaiLikuidasiEquivalentIdr;
}
