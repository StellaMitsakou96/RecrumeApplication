package gr.codehub.api.exception;

public class ApplicantNotFoundException extends Exception {
    public ApplicantNotFoundException(String description){
        super(description);
    }
}
