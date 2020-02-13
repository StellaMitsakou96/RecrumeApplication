//package gr.codehub.api.controller.ajsServiceContollers;
//
////import gr.codehub.api.dto.ApplicantDTO;
//import gr.codehub.api.dto.*;
//import gr.codehub.api.model.*;
//import gr.codehub.api.service.AJSService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class ControllerRestful {
//
//    @Autowired
//    private AJSService ajsService;
//
//   /* @GetMapping("applicants")
//    public List<Applicant> getAllApplicants()  {
//        return ajsService.getAllApplicantsFromDB();
//    }
//
//    @GetMapping("applicant/{id}")
//    public Applicant getAllApplicantById(@PathVariable int id) { //throws ApplicantNotFoundException {
//        return ajsService.getApplicantFromDB(id);
//    }
//
//    @GetMapping("applicants/{lastName}")
//    public Applicant getApplicantByName(@PathVariable String lastName) {
//        return ajsService.getApplicantByNameFromDB(lastName);
//    }
//
//    @GetMapping("applicantss/{region}")
//    public Optional<List<Applicant>>getApplicantByRegion(@PathVariable String region) {
//        Optional<List<Applicant>> applicants = ajsService.getApplicantByRegionFromDB(region);
//        return applicants;
//    }
//    @GetMapping("applicants/skill/{skill}")
//    public List<Applicant> getApplicantsBySkill(@PathVariable String skill) {
//        List<Applicant> applicants =  ajsService.getApplicantsBySkillFromDB(skill);
//        return applicants;
//    }
//    @GetMapping("applicants/fromExcel")
//    public List<Applicant> getApplicantFromExcel() throws IOException {
//        return ajsService.readApplicantFromExcel();
//    }
//    @PostMapping("newApplicant")
//    public Applicant newApplicant(@RequestBody ApplicantDTO applicantDTO) {
//        return ajsService.save(applicantDTO);
//    }*/
//
//
//    //-------------------------------------------------------------------------------------------------------------------------
//    /*@GetMapping("skill")
//    public List<Skill> getAllSkills() {
//        return ajsService.getAllSkillsFromDB();
//    }
//
//    @GetMapping("skill/{id}")
//    public Skill getSkill(@PathVariable int id) {
//        return ajsService.getSkillByIdFromDB(id);
//    }
//    @GetMapping("skills/fromExcel")
//    public List<Skill> getSkillFromExcel() throws IOException {
//        return ajsService.readFromExcel();
//    }
//    @PostMapping("newSkill")
//    public Skill newSkill(@RequestBody SkillDTO skillDTO) {
//        return ajsService.save(skillDTO);
//    }
//
//    @PostMapping("skill/merge")
//    public Skill mergeTwoSkills(@RequestBody SkillTwoDTO skillTwoDTO) {
//        System.out.println("XAXAAXAXXAAXAXAXXA----------------------------------");
//        return ajsService.mergeSkills(skillTwoDTO);
//    }
//
//    @PostMapping("skill/split")
//    public Skill splitOneSkill(@RequestBody SkillDTO skillDTO) {
//        return ajsService.split(skillDTO);
//    }
//
//    @PostMapping("skillset")
//    public SkillSet createSkillForApplicant(@RequestBody SkillSetDTO skillSetDTO) throws Exception {
//        return ajsService.createSkillSet(skillSetDTO);
//    }
//
//    @PostMapping("skillsetforjoboffer")
//    public SkillSetForJobOffer createSkillForJobOffer(@RequestBody SkillSetForJobOfferDTO skillSetForJobOfferDTO) throws Exception {
//        return ajsService.createSkillSetForJobOffer(skillSetForJobOfferDTO);
//    }
//
//
//    @PutMapping("skill/{id}")
//    public Skill updateOne(@PathVariable int id, @RequestBody SkillDTO skillDTO){
//        return ajsService.updateOne(id, skillDTO);
//    }
//
//    @PutMapping("skill/inactive/{id}")
//    public String softDelete( @PathVariable int id) {
//        return ajsService.softDelete(id);
//    }*/
////-------------------------------------------------------------------------------------------------------------------------
//
//    /*@GetMapping("joboffers")
//    public List<JobOffer> getAllJobOffers(){
//        List<JobOffer> offers = ajsService.getJobOffers();
//        System.out.println(offers.size());
//        return offers;
//    }
//
//    @GetMapping("joboffers/{id}")
//    public JobOffer getJobOfferById(@PathVariable int id){
//        JobOffer offer = ajsService.getJobOfferById(id);
//        return offer;
//    }
//
//    @GetMapping("joboffer/{name}")
//    public JobOffer getJobOfferByName(@PathVariable String name) {
//        JobOffer offer =  ajsService.getJobOfferByNameFromDB(name);
//        return offer;
//    }
//
//    @GetMapping("joboffer/applicant/{applicant_id}")
//    public List<JobOffer> getJobOfferByName(@PathVariable int applicant_id) {
//        List<JobOffer> offers =  ajsService.getJobOffersByApplicantID(applicant_id);
//        return offers;
//    }
//
//    @GetMapping("joboffers/skill/{skill}")
//    public List<JobOffer> getJobOffersBySkill(@PathVariable String skill) {
//        List<JobOffer> offer =  ajsService.getJobOffersBySkillFromDB(skill);
//        return offer;
//    }
//
//    @GetMapping("joboffers/fromExcel")
//    public List<JobOffer> getJobOffersFromExcel() throws IOException {
//        return ajsService.readJobOfferFromExcel();
//    }
//
//    @PostMapping("newJobOffer")
//    public JobOffer newJobOffer(@RequestBody JobOfferDTO jobOfferDTO) {
//        return ajsService.save(jobOfferDTO);
//    }*/
////-------------------------------------------------------------------------------------------------------------------------
//
//
//   /* @GetMapping("companies")
//    public List<Company> getCompanies() {
//        return ajsService.getAllCompaniesFromDB();
//    }
//    @GetMapping("company/{region}")  //Auth sou gyrnaei tin prwth etaireia pou tha vrei me region gia paradeigma Athens
//    public Company getFirstJobOfferByRegion(@PathVariable String region){
//        Company company = ajsService.getCompanyByRegion(region);
//        //System.out.println(offer);
//        return company;
//    }
//
//    @GetMapping("companies/{region}") //Enw edw sou gyrnaw oles tis etaireies me region tade.
//    public Optional<List<Company>> getJobOffersByRegion(@PathVariable String region){
//        Optional<List<Company>> companies = ajsService.getCompaniesByRegion(region);
//        //System.out.println(offer);
//        //----> Skills.Java.setSkillToDB();
//        return companies;
//    }
//
//    @GetMapping("companies/{nameOfCompany}/joboffer/{nameOfJob}")
//    public List<JobOffer> getJobOffersFromACompany(@PathVariable String nameOfCompany, @PathVariable String nameOfJob){
//        List<JobOffer> jobOffers = ajsService.getJobOfferOfACompany(nameOfCompany, nameOfJob);
//        //System.out.println(offer);
//        //----> Skills.Java.setSkillToDB();
//        return jobOffers;
//    }
//
//
//    @PostMapping("newcompany")
//    public Company createCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
//        return ajsService.createNewCompany(companyDTO);
//    }*/
////---------------------------------------------------------------------------------------------------------------------
//
//}
