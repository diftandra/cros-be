package co.id.bca.cros.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTypeConstant {
    public static final String GCR_KOMKOR = "GCR - KomKor";
    public static final String GCR = "GCR";
    public static final String DBKK = "DBKK";
    public static final String PKW = "PKW";
    public static final String KKK = "KKK";
    public static final String KFKK = "KFKK";
    public static final String CABANG = "CABANG";

    // might be better to put this in db in the future so the data can be add / remove dynamically
    public static final Set<String> GCR_OFFICE_CODE_SET = Set.of("0998");
    public static final Set<String> DBKK_OFFICE_CODE_SET = Set.of("0970");
    public static final Set<String> PKW_OFFICE_CODE_SET = Set.of("0971","0972","0973","0974","0975","0976","0977","0978","0979","0980","0981","0982");
    public static final Set<String> KFKK_OFFICE_CODE_SET = Set.of("0937", "0962", "0963", "0967");
    public static final Set<String> KKK_OFFICE_CODE_SET = Set.of("0959", "0968", "0969");

    public static String getUserTypeByOfficeCode(String officeCode) {
        if (GCR_OFFICE_CODE_SET.contains(officeCode)) {
            return GCR;
        } else if (DBKK_OFFICE_CODE_SET.contains(officeCode)) {
            return DBKK;
        } else if (PKW_OFFICE_CODE_SET.contains(officeCode)) {
            return PKW;
        } else if (KFKK_OFFICE_CODE_SET.contains(officeCode)) {
            return KFKK;
        } else if (KKK_OFFICE_CODE_SET.contains(officeCode)) {
            return KKK;
        }
        return CABANG;
    }
}
