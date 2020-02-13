package gr.codehub.api.exception.jobOfferException;

public class JobOfferNameNotFoundException extends Exception {
    public JobOfferNameNotFoundException(String description){
        super(description);
    }
}