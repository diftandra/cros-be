package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataDebiturProjectionImpl implements DataDebiturProjection {
    private UUID idCrosMasterDebitur;
    private String cin;
    private String namaDebitur;
    private String kantorWilayah;
    private String cabang;
    private String kategoriDebitur;
    private String collectKey;
    private String sektorEkonomi;
    private String namaGrup;
    private String pemegangSaham;
}
