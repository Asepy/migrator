package py.com.asepy.migrator.beans;

import java.util.ArrayList;
import java.util.List;

public class MigrationComplaint {

    private List<String> errors;
    private List<String> warnings;

    public MigrationComplaint() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    @Override
    public String toString() {
        return "MigrationComplaint{" +
                "errors=" + errors +
                ", warnings=" + warnings +
                '}';
    }
}
