package co.id.bca.cros.shared.enumeration;

public enum Errors {
    SUCCESS,
    EMPTY_USER_ID,
    EMPTY_PASSWORD,
    NO_DATA_FOUND,
    EMPTY_DATA,
    GENERAL_ERROR,
    UNAUTHORIZED,
    INTERNAL_SERVER_ERROR;

    public String getCode() {
        return switch (this) {
            case SUCCESS -> "200";
            case GENERAL_ERROR -> "100";
            case EMPTY_USER_ID, EMPTY_PASSWORD, NO_DATA_FOUND, EMPTY_DATA -> "400";
            case UNAUTHORIZED -> "401";
            case INTERNAL_SERVER_ERROR -> "500";
        };
    }

    public String toIndonesian() {
        return switch (this) {
            case SUCCESS -> "Berhasil.";
            case GENERAL_ERROR -> "Oops.. terjadi kesalahan, silakan coba lagi.";
            case EMPTY_USER_ID -> "User id harus diisi.";
            case EMPTY_PASSWORD -> "Password harus diisi.";
            case NO_DATA_FOUND -> "Data tidak ditemukan.";
            case EMPTY_DATA -> "Tidak ada data.";
            case UNAUTHORIZED -> "Tidak ada akses.";
            case INTERNAL_SERVER_ERROR -> "Sistem tidak tersedia.";
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case SUCCESS -> "Success.";
            case GENERAL_ERROR -> "Oops.. something went wrong, please try again.";
            case EMPTY_USER_ID -> "User id must be filled.";
            case EMPTY_PASSWORD -> "Password must be filled.";
            case NO_DATA_FOUND -> "No data found.";
            case EMPTY_DATA -> "Empty data.";
            case UNAUTHORIZED -> "Unauthorized.";
            case INTERNAL_SERVER_ERROR -> "System unavailable.";
        };
    }
}
