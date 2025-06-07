package co.id.bca.cros.shared.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorSchema {
    private String errorCode;
    private ErrorMessage errorMessage;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ErrorMessage {
        private String english;
        private String indonesian;

        public ErrorMessage(String english, String indonesian) {
            this.english = english;
            this.indonesian = indonesian;
        }
    }
}
