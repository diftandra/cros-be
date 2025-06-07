package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PopUpHapusTagihProjection {
    UUID getIdCrosMasterKomitmen();

    String getNoRekeningIls();

    String getNoKomitmen();

    BigDecimal getBalanceOcurHt();

    BigDecimal getBungaOcurHt();

    BigDecimal getDendaOcurHt();

    BigDecimal getBalanceIdrHt();

    BigDecimal getBungaIdrHt();

    BigDecimal getDendaIdrHt();

    String getMataUang();

    LocalDate getTglHt();
}
