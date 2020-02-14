insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('dimitris', 'iracleous', 'ath 5', 'athens', 'engineer')
insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('dim', 'cleous', 'sath 6', 'athens', 'doctor')
insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('sam', 'leous', 'atih 7', 'thesalia', 'engineer')
insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('mitris', 'racleous', 'athens', 'waitress', 'doctoras')
insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('riris', 'irac', 'kat 6', 'athens', 'software_engineer')

insert into company([company_name],[region]) values ('alphabank', 'athens')
insert into company([company_name],[region]) values ('eurobank', 'london')
insert into company([company_name],[region]) values ('fitbank', 'athens')


insert into job_offer([job_offer_name],[company_id],[applicant_id]) values ('java_developer', 1, 1)
insert into job_offer([job_offer_name],[company_id],[applicant_id]) values ('java', 2, 2)
insert into job_offer([job_offer_name],[company_id],[applicant_id]) values ('java_developerSSSS', 1, 3)
insert into job_offer([job_offer_name],[company_id],[applicant_id]) values ('developerSSSS', 3, 2)

insert into skill([skill_name]) values ('hard_worker')
insert into skill([skill_name]) values ('java_developer')

insert into skill_set([applicant_id], [skill_id]) values (1, 2)
insert into skill_set([applicant_id], [skill_id]) values (5, 2)
insert into skill_set([applicant_id], [skill_id]) values (1, 2)
insert into skill_set([applicant_id], [skill_id]) values (3, 1)
insert into skill_set([applicant_id], [skill_id]) values (4, 2)


insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (1, 2)
insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (2, 2)
insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (1, 2)
insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (3, 1)
insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (4, 2)

insert into job_offer([job_offer_name],[company_id]) values ('java_developer', 1)
insert into skill_set_for_job_offer([job_offer_id], [skill_id]) values (5, 1)
insert into applicant([first_name],[last_name],[address],[region],[education_level]) values ('LAkis', 'lalakis', 'rome 5', 'athens', 'Star')
insert into skill_set([applicant_id], [skill_id]) values (6, 1)





