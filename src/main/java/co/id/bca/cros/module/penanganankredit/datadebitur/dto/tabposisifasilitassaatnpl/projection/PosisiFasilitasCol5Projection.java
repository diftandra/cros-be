package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PosisiFasilitasCol5Projection {
    UUID getIdCrosNpl();

    String getNoRekeningIls();

    String getNoKomitmen();

    String getJenisFasilitas();

    Integer getKolektibilitas();

    String getNamaKanwil();

    String getNamaKcu();

    BigDecimal getCol5BalanceOcur();

    BigDecimal getCol5BungaOcur();

    BigDecimal getCol5DendaOcur();

    BigDecimal getCol5BalanceIdr();

    BigDecimal getCol5BungaIdr();

    BigDecimal getCol5DendaIdr();

    String getMataUang();

    LocalDate getTglKolek5();
}
