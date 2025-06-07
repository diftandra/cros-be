package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpPinjamanProjectionImpl implements PopUpPinjamanProjection {
    private UUID idCrosMasterPinjaman;
    private String noPinjaman;
    private String jenisFasilitas;
    private BigDecimal balanceOcur;
    private BigDecimal bungaOcur;
    private BigDecimal dendaOcur;
    private BigDecimal balanceIdr;
    private BigDecimal bungaIdr;
    private BigDecimal dendaIdr;
    private String mataUang;
    private Integer hariTunggakan;
    private LocalDate tglTutupPinjaman;
}
