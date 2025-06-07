package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpHapusTagihProjectionImpl implements PopUpHapusTagihProjection {
    private UUID idCrosMasterKomitmen;
    private String noRekeningIls;
    private String noKomitmen;
    private BigDecimal balanceOcurHt;
    private BigDecimal bungaOcurHt;
    private BigDecimal dendaOcurHt;
    private BigDecimal balanceIdrHt;
    private BigDecimal bungaIdrHt;
    private BigDecimal dendaIdrHt;
    private String mataUang;
    private LocalDate tglHt;
}
