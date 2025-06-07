package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasCol4ProjectionImpl implements PosisiFasilitasCol4Projection {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    private BigDecimal col4BalanceOcur;
    private BigDecimal col4BungaOcur;
    private BigDecimal col4DendaOcur;
    private BigDecimal col4BalanceIdr;
    private BigDecimal col4BungaIdr;
    private BigDecimal col4DendaIdr;
    private String mataUang;
    private LocalDate tglKolek4;
}
