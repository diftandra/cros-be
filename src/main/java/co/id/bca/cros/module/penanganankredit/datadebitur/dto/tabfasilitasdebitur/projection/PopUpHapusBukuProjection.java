package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PopUpHapusBukuProjection {
    UUID getIdCrosMasterKomitmen();

    String getNoRekeningIls();

    String getNoKomitmen();

    BigDecimal getBalanceOcurHb();

    BigDecimal getBungaOcurHb();

    BigDecimal getDendaOcurHb();

    BigDecimal getBalanceIdrHb();

    BigDecimal getBungaIdrHb();

    BigDecimal getDendaIdrHb();

    String getMataUang();

    LocalDate getTglHb();
}
