package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PosisiFasilitasCol4Projection {
    UUID getIdCrosNpl();

    String getNoRekeningIls();

    String getNoKomitmen();

    String getJenisFasilitas();

    Integer getKolektibilitas();

    String getNamaKanwil();

    String getNamaKcu();

    BigDecimal getCol4BalanceOcur();

    BigDecimal getCol4BungaOcur();

    BigDecimal getCol4DendaOcur();

    BigDecimal getCol4BalanceIdr();

    BigDecimal getCol4BungaIdr();

    BigDecimal getCol4DendaIdr();

    String getMataUang();

    LocalDate getTglKolek4();
}
