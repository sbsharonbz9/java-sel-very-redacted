# Galen-integration
In the past I wrote a template specific to one drug for one client. This is a framework modified for scalability after we got several more clients and separated the assessments and checkout portions into microservices. The test/java/galen/tenants are where the test folders for new clients are located and the page data (per Page Object Model) is in main/java/galen.

I used the pdf reporting format coded in GalenReport because it was a hard requirement. This requirement came from QA/RA to make the output identical to the manual test report, and was echoed by the VP. This also simplified sign-offs after formal test cycles.
