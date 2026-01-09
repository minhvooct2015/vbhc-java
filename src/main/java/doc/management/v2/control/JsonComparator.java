package doc.management.v2.control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JsonComparator {

    public record JsonDifference(String path, JsonNode orgValue, JsonNode latestValue) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<JsonDifference> diff(String leftJson,
                                            String rightJson,
                                            Set<String> excludedFieldsOrPaths) throws Exception {
        JsonNode left = MAPPER.readTree(leftJson);
        JsonNode right = MAPPER.readTree(rightJson);
        List<JsonDifference> diffs = new ArrayList<>();
        compare(left, right, "", normalizeExcludes(excludedFieldsOrPaths), diffs);
        return diffs;
    }

    private static Set<String> normalizeExcludes(Set<String> excludes) {
        if (excludes == null) return Collections.emptySet();
        return excludes.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    private static void compare(JsonNode left, JsonNode right, String path,
                                Set<String> excludes, List<JsonDifference> diffs) {
        // Bỏ qua nếu path hoặc tên field cuối cùng nằm trong exclude
        String lastSegment = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1) : path;
        if (!path.isEmpty() && (excludes.contains(path) || excludes.contains(lastSegment))) {
            return;
        }

        // Cùng kiểu Object
        if (left != null && right != null && left.isObject() && right.isObject()) {
            Set<String> fields = new HashSet<>();
            left.fieldNames().forEachRemaining(fields::add);
            right.fieldNames().forEachRemaining(fields::add);
            for (String f : fields) {
                String childPath = path.isEmpty() ? f : path + "." + f;
                compare(
                        left.get(f) == null ? MAPPER.nullNode() : left.get(f),
                        right.get(f) == null ? MAPPER.nullNode() : right.get(f),
                        childPath,
                        excludes,
                        diffs
                );
            }
            return;
        }

        // Cùng kiểu Array
        if (left != null && right != null && left.isArray() && right.isArray()) {
            int max = Math.max(left.size(), right.size());
            for (int i = 0; i < max; i++) {
                String childPath = String.format("%s[%d]", path, i);
                JsonNode l = i < left.size() ? left.get(i) : MAPPER.nullNode();
                JsonNode r = i < right.size() ? right.get(i) : MAPPER.nullNode();
                compare(l, r, childPath, excludes, diffs);
            }
            return;
        }

        // Khác loại hoặc khác giá trị
        if (!Objects.equals(left, right)) {
            diffs.add(new JsonDifference(path.isEmpty() ? "$" : path, left, right));
        }
    }

}
