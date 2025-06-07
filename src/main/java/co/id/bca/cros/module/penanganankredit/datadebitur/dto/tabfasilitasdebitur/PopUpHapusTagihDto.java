package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpHapusTagihProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpHapusTagihDto {
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

    public PopUpHapusTagihDto(PopUpHapusTagihProjection projection) {
        this.idCrosMasterKomitmen = projection.getIdCrosMasterKomitmen();
        this.noRekeningIls = projection.getNoRekeningIls();
        this.noKomitmen = projection.getNoKomitmen();
        this.balanceOcurHt = projection.getBalanceOcurHt();
        this.bungaOcurHt = projection.getBungaOcurHt();
        this.dendaOcurHt = projection.getDendaOcurHt();
        this.balanceIdrHt = projection.getBalanceIdrHt();
        this.bungaIdrHt = projection.getBungaIdrHt();
        this.dendaIdrHt = projection.getDendaIdrHt();
        this.mataUang = projection.getMataUang();
        this.tglHt = projection.getTglHt() == null ? null : projection.getTglHt();
    }
}
