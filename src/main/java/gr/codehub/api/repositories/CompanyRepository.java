package gr.codehub.api.repositories;

import gr.codehub.api.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findFirstByRegion(String region);

    Optional<List<Company>> findAllByRegion(String region);

    Company findFirstById(int company_id);


    Optional<List<Company>> findAllByCompanyName(String companyName);


}
