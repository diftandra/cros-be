package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.projection;

import java.util.UUID;

public interface DataDebiturProjection {
    UUID getIdCrosMasterDebitur();

    String getCin();

    String getNamaDebitur();

    String getKantorWilayah();

    String getCabang();

    String getKategoriDebitur();

    String getCollectKey();

    String getSektorEkonomi();

    String getNamaGrup();

    String getPemegangSaham();
}
