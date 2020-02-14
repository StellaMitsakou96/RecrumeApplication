package gr.codehub.api.tests;

import gr.codehub.api.exception.companyException.CompanyRegionNotFoundException;
import gr.codehub.api.repositories.CompanyRepository;

import gr.codehub.api.service.ajsService.companyService.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    CompanyService companyService;

    @Test
    void getCompanyByRegion() throws CompanyRegionNotFoundException {
         assertEquals(5, companyService.getCompanyByRegion("Athens").getId());
    }

    @Test
    void getCompaniesByRegion() throws CompanyRegionNotFoundException {
        assertEquals(5, companyService.getCompaniesByRegion("Athens").stream().count());
    }

    @Test
    void getAllCompaniesFromDB() {
        assertEquals(5, companyService.getAllCompaniesFromDB().size());
    }

}