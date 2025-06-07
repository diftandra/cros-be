package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasNplProjectionImpl implements PosisiFasilitasNplProjection {
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
}
