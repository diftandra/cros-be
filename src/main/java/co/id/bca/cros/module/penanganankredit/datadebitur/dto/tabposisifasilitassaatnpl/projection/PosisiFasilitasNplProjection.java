package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PosisiFasilitasNplProjection {
    UUID getIdCrosNpl();

    String getNoRekeningIls();

    String getNoKomitmen();

    String getJenisFasilitas();

    Integer getKolektibilitas();

    String getNamaKanwil();

    String getNamaKcu();

    BigDecimal getNplBalanceOcur();

    BigDecimal getNplBungaOcur();

    BigDecimal getNplDendaOcur();

    BigDecimal getNplBalanceIdr();

    BigDecimal getNplBungaIdr();

    BigDecimal getNplDendaIdr();

    String getMataUang();

    LocalDate getTglNpl();
}
