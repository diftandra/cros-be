package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasNplProjection;
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
public class PosisiFasilitasNplDto {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    private BigDecimal nplBalanceOcur;
    private BigDecimal nplBungaOcur;
    private BigDecimal nplDendaOcur;
    private BigDecimal nplBalanceIdr;
    private BigDecimal nplBungaIdr;
    private BigDecimal nplDendaIdr;
    private String mataUang;
    private LocalDate tglNpl;

    public static PosisiFasilitasNplDto fromProjection(PosisiFasilitasNplProjection projection) {
        return PosisiFasilitasNplDto.builder()
                .idCrosNpl(projection.getIdCrosNpl())
                .noRekeningIls(projection.getNoRekeningIls())
                .noKomitmen(projection.getNoKomitmen())
                .jenisFasilitas(projection.getJenisFasilitas())
                .kolektibilitas(projection.getKolektibilitas())
                .namaKanwil(projection.getNamaKanwil())
                .namaKcu(projection.getNamaKcu())
                .nplBalanceOcur(projection.getNplBalanceOcur())
                .nplBungaOcur(projection.getNplBungaOcur())
                .nplDendaOcur(projection.getNplDendaOcur())
                .nplBalanceIdr(projection.getNplBalanceIdr())
                .nplBungaIdr(projection.getNplBungaIdr())
                .nplDendaIdr(projection.getNplDendaIdr())
                .mataUang(projection.getMataUang())
                .tglNpl(projection.getTglNpl() == null ? null : projection.getTglNpl())
                .build();
    }
}
