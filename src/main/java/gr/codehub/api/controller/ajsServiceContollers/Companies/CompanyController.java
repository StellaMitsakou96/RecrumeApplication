package gr.codehub.api.controller.ajsServiceContollers.Companies;

import gr.codehub.api.dto.CompanyDTO;
import gr.codehub.api.exception.companyException.CompanyRegionNotFoundException;
import gr.codehub.api.model.Company;
import gr.codehub.api.model.JobOffer;
import gr.codehub.api.service.ajsService.companyService.CompanyService;
import gr.codehub.api.service.ajsService.jobOfferService.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JobOfferService jobOfferService;

    /**
     * @return return all companies
     */
    @GetMapping("companies")
    public List<Company> getCompanies() {
        return companyService.getAllCompaniesFromDB();
    }

    /**
     * search the first company by the given region
     *
     * @param region
     * @return the company
     * @throws CompanyRegionNotFoundException
     */
    @GetMapping("company/{region}")
    public Company getFirstCompanyByRegion(@PathVariable String region) throws CompanyRegionNotFoundException {
        Company company = companyService.getCompanyByRegion(region);
        //System.out.println(offer);
        return company;
    }

    /**
     * search all companies by region
     *
     * @param region
     * @return the companies
     * @throws CompanyRegionNotFoundException
     */
    @GetMapping("companies/{region}")
    public Optional<List<Company>> getJobOffersByRegion(@PathVariable String region) throws CompanyRegionNotFoundException {
        Optional<List<Company>> companies = companyService.getCompaniesByRegion(region);
        return companies;
    }

    /**
     * search the job offers from a specific company
     *
     * @param nameOfCompany
     * @param nameOfJob
     * @return the job offers from this company
     */
    @GetMapping("companies/{nameOfCompany}/joboffer/{nameOfJob}")
    public List<JobOffer> getJobOffersFromACompany(@PathVariable String nameOfCompany, @PathVariable String nameOfJob) {
        List<JobOffer> jobOffers = jobOfferService.getJobOfferOfACompany(nameOfCompany, nameOfJob);

        return jobOffers;
    }

    /**
     * request for adding a new company
     *
     * @param companyDTO
     * @return the new company
     * @throws Exception
     */
    @PostMapping("newcompany")
    public Company createCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        return companyService.createNewCompany(companyDTO);
    }
}
