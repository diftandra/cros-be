package co.id.bca.cros.shared.data.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cros_npl")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosNpl {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosNpl;

    private Integer custLnCurrKey;
    private Integer acctLnCurrKey;
    private Integer commCurrKey;
    private Integer productSubTypeKey;
    private Integer facLnSegKey;
    private Integer collectKey;
    private Integer currKey;
    private Integer acctRegionKey;
    private Integer acctKcuKey;
    private String noRekeningPinjaman;
    private String noKomitmen;
    private String tipeProduk;
    private String kodeProduk;
    private String namaProduk;
    private LocalDate nplDate;
    private BigDecimal nplBalanceOcur;
    private BigDecimal nplBalanceIdr;
    private BigDecimal nplBungaOcur;
    private BigDecimal nplBungaIdr;
    private BigDecimal nplDendaOcur;
    private BigDecimal nplDendaIdr;
    @Column(name = "col_3_date")
    private LocalDate col3Date;
    @Column(name = "col_3_balance_ocur")
    private BigDecimal col3BalanceOcur;
    @Column(name = "col_3_balance_idr")
    private BigDecimal col3BalanceIdr;
    @Column(name = "col_3_bunga_ocur")
    private BigDecimal col3BungaOcur;
    @Column(name = "col_3_bunga_idr")
    private BigDecimal col3BungaIdr;
    @Column(name = "col_3_denda_ocur")
    private BigDecimal col3DendaOcur;
    @Column(name = "col_3_denda_idr")
    private BigDecimal col3DendaIdr;
    @Column(name = "col_4_date")
    private LocalDate col4Date;
    @Column(name = "col_4_balance_ocur")
    private BigDecimal col4BalanceOcur;
    @Column(name = "col_4_balance_idr")
    private BigDecimal col4BalanceIdr;
    @Column(name = "col_4_bunga_ocur")
    private BigDecimal col4BungaOcur;
    @Column(name = "col_4_bunga_idr")
    private BigDecimal col4BungaIdr;
    @Column(name = "col_4_denda_ocur")
    private BigDecimal col4DendaOcur;
    @Column(name = "col_4_denda_idr")
    private BigDecimal col4DendaIdr;
    @Column(name = "col_5_date")
    private LocalDate col5Date;
    @Column(name = "col_5_balance_ocur")
    private BigDecimal col5BalanceOcur;
    @Column(name = "col_5_balance_idr")
    private BigDecimal col5BalanceIdr;
    @Column(name = "col_5_bunga_ocur")
    private BigDecimal col5BungaOcur;
    @Column(name = "col_5_bunga_idr")
    private BigDecimal col5BungaIdr;
    @Column(name = "col_5_denda_ocur")
    private BigDecimal col5DendaOcur;
    @Column(name = "col_5_denda_idr")
    private BigDecimal col5DendaIdr;
}
