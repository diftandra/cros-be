package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasCol3ProjectionImpl implements PosisiFasilitasCol3Projection {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    private BigDecimal col3BalanceOcur;
    private BigDecimal col3BungaOcur;
    private BigDecimal col3DendaOcur;
    private BigDecimal col3BalanceIdr;
    private BigDecimal col3BungaIdr;
    private BigDecimal col3DendaIdr;
    private String mataUang;
    private LocalDate tglKolek3;
}
