package co.id.bca.cros.module.search.dto.searchdatadebitur;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchDataDebiturOutputSchema {
    private UUID idCrosMasterDebitur;
    private Integer custLnCurrkey;
    private String noCis;
    private String namaDebitur;
    private String wilayah;
    private String cabang;
    private String kategoriKredit;
    private Integer kolektibilitas;
    private BigDecimal totalOutstanding;
    private Integer hariTunggakan;
    private String statusDebitur;
    private String penanganan;
}
