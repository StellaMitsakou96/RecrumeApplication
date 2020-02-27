package gr.codehub.api.exception.jobOfferException;

public class JobOfferErrorMessage extends Exception {
    public static final String JOB_OFFER_ID_NOT_FOUND = "The job offer with the given id did not found!";
    public static final String JOB_OFFER_NAME_NOT_FOUND = "The job offer with the given name did not found!";
    public static final String JOB_OFFER_APPLICANTID_NOT_FOUND = "The applicant id for this job offer did not found!";

    public static String getHtmlDescription(Exception exception, Integer statusCode) {
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }
}