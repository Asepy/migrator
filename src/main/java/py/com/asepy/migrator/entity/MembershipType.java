package py.com.asepy.migrator.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public enum MembershipType {
    BASICO,
    PLUS_PENDIENTE,
    PLUS,
    EMBAJADOR,
    FUNDADOR,
    FUNDADOR_EMBAJADOR;

    private static List<String> defaultMappings = Arrays.asList("SOCIO", "EMPRENDEDOR");

    public static MembershipType fromCSVCellValue(String cell) {
        if (StringUtils.isBlank(cell)) {
            return BASICO;
        }

        if (defaultMappings.contains(cell.toUpperCase())) {
            return BASICO;
        }

        MembershipType membershipType = Arrays.stream(values())
                .filter(v -> v.name().toLowerCase().equals(cell.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid membership type: " + cell));
        return membershipType;
    }
}
