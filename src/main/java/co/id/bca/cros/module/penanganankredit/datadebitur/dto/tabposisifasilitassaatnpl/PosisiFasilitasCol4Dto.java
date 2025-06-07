package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol4Projection;
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
public class PosisiFasilitasCol4Dto {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    @JsonProperty("col_4_balance_ocur")
    private BigDecimal col4BalanceOcur;
    @JsonProperty("col_4_bunga_ocur")
    private BigDecimal col4BungaOcur;
    @JsonProperty("col_4_denda_ocur")
    private BigDecimal col4DendaOcur;
    @JsonProperty("col_4_balance_idr")
    private BigDecimal col4BalanceIdr;
    @JsonProperty("col_4_bunga_idr")
    private BigDecimal col4BungaIdr;
    @JsonProperty("col_4_denda_idr")
    private BigDecimal col4DendaIdr;
    private String mataUang;
    @JsonProperty("tgl_kolek_4")
    private LocalDate tglKolek4;

    public static PosisiFasilitasCol4Dto fromProjection(PosisiFasilitasCol4Projection projection) {
        return PosisiFasilitasCol4Dto.builder()
                .idCrosNpl(projection.getIdCrosNpl())
                .noRekeningIls(projection.getNoRekeningIls())
                .noKomitmen(projection.getNoKomitmen())
                .jenisFasilitas(projection.getJenisFasilitas())
                .kolektibilitas(projection.getKolektibilitas())
                .namaKanwil(projection.getNamaKanwil())
                .namaKcu(projection.getNamaKcu())
                .col4BalanceOcur(projection.getCol4BalanceOcur())
                .col4BungaOcur(projection.getCol4BungaOcur())
                .col4DendaOcur(projection.getCol4DendaOcur())
                .col4BalanceIdr(projection.getCol4BalanceIdr())
                .col4BungaIdr(projection.getCol4BungaIdr())
                .col4DendaIdr(projection.getCol4DendaIdr())
                .mataUang(projection.getMataUang())
                .tglKolek4(projection.getTglKolek4() == null ? null : projection.getTglKolek4())
                .build();
    }
}
