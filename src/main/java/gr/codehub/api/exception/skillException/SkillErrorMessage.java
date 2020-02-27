package gr.codehub.api.exception.skillException;

public class SkillErrorMessage extends Exception {
    public static final String SKILL_ID_NOT_FOUND = "The given skill id did not found!";

    public static String getHtmlDescription(Exception exception, Integer statusCode) {
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }
}