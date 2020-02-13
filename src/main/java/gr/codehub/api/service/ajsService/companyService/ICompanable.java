package gr.codehub.api.service.ajsService.companyService;

import gr.codehub.api.dto.CompanyDTO;
import gr.codehub.api.exception.companyException.CompanyRegionNotFoundException;
import gr.codehub.api.model.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanable {
    Company getCompanyByRegion(String region) throws CompanyRegionNotFoundException;

    Optional<List<Company>> getCompaniesByRegion(String region) throws CompanyRegionNotFoundException;

    List<Company> getAllCompaniesFromDB();

    Company createNewCompany(CompanyDTO companyDTO);
}
