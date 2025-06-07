package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface PopUpPinjamanProjection {
    UUID getIdCrosMasterPinjaman();

    String getNoPinjaman();

    String getJenisFasilitas();

    BigDecimal getBalanceOcur();

    BigDecimal getBungaOcur();

    BigDecimal getDendaOcur();

    BigDecimal getBalanceIdr();

    BigDecimal getBungaIdr();

    BigDecimal getDendaIdr();

    String getMataUang();

    Integer getHariTunggakan();

    LocalDate getTglTutupPinjaman();
}
