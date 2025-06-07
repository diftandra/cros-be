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
@Table(name = "cros_pemegang_saham")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosPemegangSaham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosPemegangSaham;

    private LocalDate tglData;
    private Integer custLnCurrKey;
    private String namaPemegangSaham;
    private String pengurusPemilikSahamInd;
    private BigDecimal ownerShare;
}
