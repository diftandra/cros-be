package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PosisiFasilitasCol5ProjectionImpl implements PosisiFasilitasCol5Projection {
    private UUID idCrosNpl;
    private String noRekeningIls;
    private String noKomitmen;
    private String jenisFasilitas;
    private Integer kolektibilitas;
    private String namaKanwil;
    private String namaKcu;
    private BigDecimal col5BalanceOcur;
    private BigDecimal col5BungaOcur;
    private BigDecimal col5DendaOcur;
    private BigDecimal col5BalanceIdr;
    private BigDecimal col5BungaIdr;
    private BigDecimal col5DendaIdr;
    private String mataUang;
    private LocalDate tglKolek5;
}
