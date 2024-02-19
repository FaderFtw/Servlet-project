package servlets.utils;

public class AssertValue {
    public static boolean valueExistsNotNull(String value) {
        return value != null && !value.isEmpty();
    }
}
