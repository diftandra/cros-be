package co.id.bca.cros.module.auth.uidm.dto.logout;

import co.id.bca.cros.shared.dto.ErrorSchema;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LogoutOutputSchemaDto {
    private Integer logoutStatus;
    private ErrorSchema errorSchema;
}
