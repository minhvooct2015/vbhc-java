package doc.management.v2.DTO;

import java.util.List;

/**
 * Represents the diff between OrgDocJson (original imported document) and
 * OrgLatestDoc (user-edited version) stored in DataJson.
 *
 * Each entry corresponds to one field/path that the user changed.
 * "originalValue" is what the source document had.
 * "userUpdatedValue" is what the user entered — and is the suggested value
 * to persist into the database if it has not been applied yet.
 */
public class FieldSuggestionDTO {

    /** JSON path of the field, e.g. "trichYeu", "nguoiKy", "ngayBanHanh" */
    private String field;

    /** Value from OrgDocJson (original source document) */
    private Object originalValue;

    /** Value from OrgLatestDoc (what the user entered) */
    private Object userUpdatedValue;

    public FieldSuggestionDTO() {}

    public FieldSuggestionDTO(String field, Object originalValue, Object userUpdatedValue) {
        this.field = field;
        this.originalValue = originalValue;
        this.userUpdatedValue = userUpdatedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Object originalValue) {
        this.originalValue = originalValue;
    }

    public Object getUserUpdatedValue() {
        return userUpdatedValue;
    }

    public void setUserUpdatedValue(Object userUpdatedValue) {
        this.userUpdatedValue = userUpdatedValue;
    }

    // -----------------------------------------------------------------------
    // Wrapper returned by the API
    // -----------------------------------------------------------------------

    /**
     * The full suggestion response for one VanBanHanhChinh document.
     * Contains the document id and the list of fields the user modified
     * compared to the original source document.
     */
    public static class DocSuggestionDTO {

        private String vanBanHanhChinhId;
        private List<FieldSuggestionDTO> suggestions;

        public DocSuggestionDTO() {}

        public DocSuggestionDTO(String vanBanHanhChinhId, List<FieldSuggestionDTO> suggestions) {
            this.vanBanHanhChinhId = vanBanHanhChinhId;
            this.suggestions = suggestions;
        }

        public String getVanBanHanhChinhId() {
            return vanBanHanhChinhId;
        }

        public void setVanBanHanhChinhId(String vanBanHanhChinhId) {
            this.vanBanHanhChinhId = vanBanHanhChinhId;
        }

        public List<FieldSuggestionDTO> getSuggestions() {
            return suggestions;
        }

        public void setSuggestions(List<FieldSuggestionDTO> suggestions) {
            this.suggestions = suggestions;
        }
    }
}
