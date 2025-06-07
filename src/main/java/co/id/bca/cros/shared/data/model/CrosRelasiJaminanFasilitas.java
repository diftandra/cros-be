package co.id.bca.cros.shared.data.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cros_relasi_jaminan_fasilitas")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosRelasiJaminanFasilitas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosRelasiJaminanFasilitas;

    private LocalDate tglData;
    private Integer custLnCurrKey;
    private Integer acctLnCurrKey;
    private Integer commCurrKey;
    private Integer collCurrKey;
}
