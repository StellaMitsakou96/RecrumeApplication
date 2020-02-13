package gr.codehub.api.exception.companyException;

public class CompanyErrorMessage extends Exception {
    public static final String COMPANY_REGION_NOT_FOUND = "The region did not found!";

    public static String getHtmlDescription(Exception exception, Integer statusCode) {
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }
}