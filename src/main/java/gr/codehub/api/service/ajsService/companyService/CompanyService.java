package gr.codehub.api.service.ajsService.companyService;

import gr.codehub.api.dto.CompanyDTO;
import gr.codehub.api.exception.companyException.CompanyRegionNotFoundException;
import gr.codehub.api.model.Company;
import gr.codehub.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanable {
    private ApplicantRepository applicantsRepo;

    private CompanyRepository companiesRepo;

    private JobOfferRepository jobOffersRepo;

    private SkillRepository skillRepo;

    private SkillSetRepository skillSetsRepo;

    private SkillSetForJobOfferRepository skillSetForJobOffersRepo;

    @Autowired
    public CompanyService(ApplicantRepository applicantsRepo, CompanyRepository companiesRepo, JobOfferRepository jobOffersRepo, SkillRepository skillRepo, SkillSetRepository skillSetsRepo, SkillSetForJobOfferRepository skillSetForJobOffersRepo) {
        this.applicantsRepo = applicantsRepo;
        this.companiesRepo = companiesRepo;
        this.jobOffersRepo = jobOffersRepo;
        this.skillRepo = skillRepo;
        this.skillSetsRepo = skillSetsRepo;
        this.skillSetForJobOffersRepo = skillSetForJobOffersRepo;
    }

    /**
     * @param region
     * @return from data base the first company by region
     * throws CompanyRegionNotFoundException
     */
    public Company getCompanyByRegion(String region) throws CompanyRegionNotFoundException {
        // return companiesRepo.findFirstByRegion(region);
        Company company = companiesRepo.findFirstByRegion(region);
        if (company == null) {
            throw new CompanyRegionNotFoundException("Region = " + region);
        }
        return company;
    }

    /**
     * @param region
     * @return from database all companies by the given region
     * throws CompanyRegionNotFoundException
     */
    public Optional<List<Company>> getCompaniesByRegion(String region) throws CompanyRegionNotFoundException {
        //return companiesRepo.findAllByRegion(region);
        Optional<List<Company>> company = companiesRepo.findAllByRegion(region);
        if (!company.isPresent()) {
            throw new CompanyRegionNotFoundException("Region = " + region);
        }
        return company;
    }

    /**
     * @return from data base all companies
     */
    public List<Company> getAllCompaniesFromDB() {
        return companiesRepo.findAll();
    }

    /**
     * request for adding new company
     *
     * @param companyDTO
     * @return the new company and save in data base
     */
    public Company createNewCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setRegion(companyDTO.getRegion());
        return companiesRepo.save(company);
    }

}
