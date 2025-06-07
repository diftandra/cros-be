package co.id.bca.cros.module.auth.uidm.dto.userfeature;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserFeatureDto {
    private String userId;
    private String featureId;
    private String parentFeatureId;
    private String featureGroupCode;
    private String featureCode;
    private String featureName;
    private String featureIcon;
    private String featurePosition;
    private String treeLevel;
    private String nodePath;
    private String accessLink;
    private String sameWithPrevTreeLevel;
    private String isLastFeature;
}
