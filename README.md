# ebanqAutomationTest

Download the project and then import as Maven project in eclipse 
# Or 
Clone the git repository in eclipse https://github.com/bhavin2p/ebanqAutomationTest

# Data update if any - update in data.properties file 
Any changes to data, example amount to be transferred or login id and password can be done in file /ebanqAutomationTest/src/main/resources/data.properties

# Webelement Object repository 
WebElements separately store in file /ebanqAutomationTest/src/main/java/ebanq/pages/EBanqObjRepo.java using POM design pattern

# validation 
Validations are stored in java file /ebanqAutomationTest/src/test/java/ebanq/test/EBanqTest.java

# To run the project :
Open the java file in eclipse /ebanqAutomationTest/src/test/java/ebanq/test/EBanqTest.java and run as TestNG
# Or
Open the right click on "testng.xml" file and run as testng

# test result can be generated after execution 
/ebanqAutomationTest/test-output/index.html
