package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpHapusBukuProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpHapusBukuDto {
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

    public PopUpHapusBukuDto(PopUpHapusBukuProjection projection) {
        this.idCrosMasterKomitmen = projection.getIdCrosMasterKomitmen();
        this.noRekeningIls = projection.getNoRekeningIls();
        this.noKomitmen = projection.getNoKomitmen();
        this.balanceOcurHb = projection.getBalanceOcurHb();
        this.bungaOcurHb = projection.getBungaOcurHb();
        this.dendaOcurHb = projection.getDendaOcurHb();
        this.balanceIdrHb = projection.getBalanceIdrHb();
        this.bungaIdrHb = projection.getBungaIdrHb();
        this.dendaIdrHb = projection.getDendaIdrHb();
        this.mataUang = projection.getMataUang();
        this.tglHb = projection.getTglHb() == null ? null : projection.getTglHb();
    }
}
