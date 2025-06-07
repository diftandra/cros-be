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
public class FasilitasDebiturDto {
    private UUID idCrosMasterKomitmen;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String kantorWilayah;
    private String cabang;
    private BigDecimal balanceOcur;
    private BigDecimal bungaOcur;
    private BigDecimal dendaOcur;
    private BigDecimal balanceIdr;
    private BigDecimal bungaIdr;
    private BigDecimal dendaIdr;
    private String mataUang;
    private BigDecimal ppap;
    private BigDecimal ckpn;
}
