package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol5Projection;
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
public class PosisiFasilitasCol5Dto {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    @JsonProperty("col_5_balance_ocur")
    private BigDecimal col5BalanceOcur;
    @JsonProperty("col_5_bunga_ocur")
    private BigDecimal col5BungaOcur;
    @JsonProperty("col_5_denda_ocur")
    private BigDecimal col5DendaOcur;
    @JsonProperty("col_5_balance_idr")
    private BigDecimal col5BalanceIdr;
    @JsonProperty("col_5_bunga_idr")
    private BigDecimal col5BungaIdr;
    @JsonProperty("col_5_denda_idr")
    private BigDecimal col5DendaIdr;
    private String mataUang;
    @JsonProperty("tgl_kolek_5")
    private LocalDate tglKolek5;

    public static PosisiFasilitasCol5Dto fromProjection(PosisiFasilitasCol5Projection projection) {
        return PosisiFasilitasCol5Dto.builder()
                .idCrosNpl(projection.getIdCrosNpl())
                .noRekeningIls(projection.getNoRekeningIls())
                .noKomitmen(projection.getNoKomitmen())
                .jenisFasilitas(projection.getJenisFasilitas())
                .kolektibilitas(projection.getKolektibilitas())
                .namaKanwil(projection.getNamaKanwil())
                .namaKcu(projection.getNamaKcu())
                .col5BalanceOcur(projection.getCol5BalanceOcur())
                .col5BungaOcur(projection.getCol5BungaOcur())
                .col5DendaOcur(projection.getCol5DendaOcur())
                .col5BalanceIdr(projection.getCol5BalanceIdr())
                .col5BungaIdr(projection.getCol5BungaIdr())
                .col5DendaIdr(projection.getCol5DendaIdr())
                .mataUang(projection.getMataUang())
                .tglKolek5(projection.getTglKolek5() == null ? null : projection.getTglKolek5())
                .build();
    }
}
