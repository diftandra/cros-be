package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PosisiFasilitasCol3Projection {
    UUID getIdCrosNpl();

    String getNoRekeningIls();

    String getNoKomitmen();

    String getJenisFasilitas();

    Integer getKolektibilitas();

    String getNamaKanwil();

    String getNamaKcu();

    BigDecimal getCol3BalanceOcur();

    BigDecimal getCol3BungaOcur();

    BigDecimal getCol3DendaOcur();

    BigDecimal getCol3BalanceIdr();

    BigDecimal getCol3BungaIdr();

    BigDecimal getCol3DendaIdr();

    String getMataUang();

    LocalDate getTglKolek3();
}
