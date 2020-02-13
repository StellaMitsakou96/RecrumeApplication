package gr.codehub.api.exception.applicantException;

public class ApplicantErrorMessage extends Exception {
    public static final String NOT_FOUND_APPLICANT = "Applicant with given id did not found!";
    public static final String NOT_FOUND_LASTNAME = "Applicant with given last name did not found!";
    public static final String NOT_FOUND_REGION = "Region with given  name did not found!";

    public static String getHtmlDescription(Exception exception, Integer statusCode) {
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }
}