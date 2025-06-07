package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol3Projection;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasCol3Dto {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    @JsonProperty("col_3_balance_ocur")
    private BigDecimal col3BalanceOcur;
    @JsonProperty("col_3_bunga_ocur")
    private BigDecimal col3BungaOcur;
    @JsonProperty("col_3_denda_ocur")
    private BigDecimal col3DendaOcur;
    @JsonProperty("col_3_balance_idr")
    private BigDecimal col3BalanceIdr;
    @JsonProperty("col_3_bunga_idr")
    private BigDecimal col3BungaIdr;
    @JsonProperty("col_3_denda_idr")
    private BigDecimal col3DendaIdr;
    private String mataUang;
    @JsonProperty("tgl_kolek_3")
    private LocalDate tglKolek3;

    public static PosisiFasilitasCol3Dto fromProjection(PosisiFasilitasCol3Projection projection) {
        return PosisiFasilitasCol3Dto.builder()
                .idCrosNpl(projection.getIdCrosNpl())
                .noRekeningIls(projection.getNoRekeningIls())
                .noKomitmen(projection.getNoKomitmen())
                .jenisFasilitas(projection.getJenisFasilitas())
                .kolektibilitas(projection.getKolektibilitas())
                .namaKanwil(projection.getNamaKanwil())
                .namaKcu(projection.getNamaKcu())
                .col3BalanceOcur(projection.getCol3BalanceOcur())
                .col3BungaOcur(projection.getCol3BungaOcur())
                .col3DendaOcur(projection.getCol3DendaOcur())
                .col3BalanceIdr(projection.getCol3BalanceIdr())
                .col3BungaIdr(projection.getCol3BungaIdr())
                .col3DendaIdr(projection.getCol3DendaIdr())
                .mataUang(projection.getMataUang())
                .tglKolek3(projection.getTglKolek3() == null ? null : projection.getTglKolek3())
                .build();
    }
}
