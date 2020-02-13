package gr.codehub.api.controller.errorController;

import gr.codehub.api.exception.applicantException.ApplicantErrorMessage;
import gr.codehub.api.exception.applicantException.ApplicantIdNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantLastNameNotFoundException;
import gr.codehub.api.exception.applicantException.ApplicantRegionNotFoundException;
import gr.codehub.api.exception.companyException.CompanyErrorMessage;
import gr.codehub.api.exception.companyException.CompanyRegionNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferApplicantIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferErrorMessage;
import gr.codehub.api.exception.jobOfferException.JobOfferIdNotFoundException;
import gr.codehub.api.exception.jobOfferException.JobOfferNameNotFoundException;
import gr.codehub.api.exception.skillException.SkillErrorMessage;
import gr.codehub.api.exception.skillException.SkillIdNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecrumeApplicationErrorController implements ErrorController {
    @RequestMapping("error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        if (exception == null) {
            return "There is nothing here!";
        }

        Throwable causeException = exception.getCause();
        //applicants
        if (exception instanceof ApplicantIdNotFoundException || causeException instanceof ApplicantIdNotFoundException) {
            return ApplicantErrorMessage.NOT_FOUND_APPLICANT;
        }
        if (exception instanceof ApplicantLastNameNotFoundException || causeException instanceof ApplicantLastNameNotFoundException) {
            return ApplicantErrorMessage.NOT_FOUND_LASTNAME;
        }
        if (exception instanceof ApplicantRegionNotFoundException || causeException instanceof ApplicantRegionNotFoundException) {
            return ApplicantErrorMessage.NOT_FOUND_REGION;
        }
        //job offers
        if (exception instanceof JobOfferIdNotFoundException || causeException instanceof JobOfferIdNotFoundException) {
            return JobOfferErrorMessage.JOB_OFFER_ID_NOT_FOUND;
        }
        if (exception instanceof JobOfferNameNotFoundException || causeException instanceof JobOfferNameNotFoundException) {
            return JobOfferErrorMessage.JOB_OFFER_NAME_NOT_FOUND;
        }
        if (exception instanceof JobOfferApplicantIdNotFoundException || causeException instanceof JobOfferApplicantIdNotFoundException) {
            return JobOfferErrorMessage.JOB_OFFER_APPLICANTID_NOT_FOUND;
        }

        //company
        if (exception instanceof CompanyRegionNotFoundException || causeException instanceof CompanyRegionNotFoundException) {
            return CompanyErrorMessage.COMPANY_REGION_NOT_FOUND;
        }

        //skill
        if (exception instanceof SkillIdNotFoundException || causeException instanceof SkillIdNotFoundException) {
            return SkillErrorMessage.SKILL_ID_NOT_FOUND;
        }


        return ApplicantErrorMessage.getHtmlDescription(exception, statusCode);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}