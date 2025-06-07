package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpPinjamanProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PopUpPinjamanDto {
    private UUID idCrosMasterPinjaman;
    private String noPinjaman;
    private String jenisFasilitas;
    private BigDecimal balanceOcur;
    private BigDecimal bungaOcur;
    private BigDecimal dendaOcur;
    private BigDecimal balanceIdr;
    private BigDecimal bungaIdr;
    private BigDecimal dendaIdr;
    private String mataUang;
    private Integer hariTunggakan;
    private LocalDate tglTutupPinjaman;

    public PopUpPinjamanDto(PopUpPinjamanProjection projection) {
        this.idCrosMasterPinjaman = projection.getIdCrosMasterPinjaman();
        this.noPinjaman = projection.getNoPinjaman();
        this.jenisFasilitas = projection.getJenisFasilitas();
        this.balanceOcur = projection.getBalanceOcur();
        this.bungaOcur = projection.getBungaOcur();
        this.dendaOcur = projection.getDendaOcur();
        this.balanceIdr = projection.getBalanceIdr();
        this.bungaIdr = projection.getBungaIdr();
        this.dendaIdr = projection.getDendaIdr();
        this.mataUang = projection.getMataUang();
        this.hariTunggakan = projection.getHariTunggakan();
        this.tglTutupPinjaman = projection.getTglTutupPinjaman() == null ? null : projection.getTglTutupPinjaman();
    }
}
