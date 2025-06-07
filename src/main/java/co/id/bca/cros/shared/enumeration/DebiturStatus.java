package co.id.bca.cros.shared.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum DebiturStatus {
    CE(0, "CE"),
    HAPUS_BUKU(1, "HB"),
    HAPUS_TAGIH(-1, "HT"),
    NA(-99, "NA"); // is_active = False

    private static final Map<Integer, DebiturStatus> BY_FLAG_NUMBER = new HashMap<>();
    private static final Map<String, DebiturStatus> BY_FLAG_NAME = new HashMap<>();

    static {
        for (DebiturStatus status : DebiturStatus.values()) {
            BY_FLAG_NUMBER.put(status.flagNumber, status);
            BY_FLAG_NAME.put(status.flagName, status);
        }
    }

    public final int flagNumber;
    public final String flagName;

    DebiturStatus(int flagNumber, String flagName) {
        this.flagNumber = flagNumber;
        this.flagName = flagName;
    }

    public static DebiturStatus valueOfNumber(int flagNumber) {
        return BY_FLAG_NUMBER.get(flagNumber);
    }

    public static DebiturStatus valueOfName(String flagName) {
        return BY_FLAG_NAME.get(flagName);
    }

    public String toFlagName() {
        return this.flagName;
    }

    public Integer toFlagNumber() {
        return this.flagNumber;
    }
}
