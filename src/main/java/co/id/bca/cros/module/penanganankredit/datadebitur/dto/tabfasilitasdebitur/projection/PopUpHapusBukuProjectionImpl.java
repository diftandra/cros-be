package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpHapusBukuProjectionImpl implements PopUpHapusBukuProjection {
    private UUID idCrosMasterKomitmen;
    private String noRekeningIls;
    private String noKomitmen;
    private BigDecimal balanceOcurHb;
    private BigDecimal bungaOcurHb;
    private BigDecimal dendaOcurHb;
    private BigDecimal balanceIdrHb;
    private BigDecimal bungaIdrHb;
    private BigDecimal dendaIdrHb;
    private String mataUang;
    private LocalDate tglHb;
}
