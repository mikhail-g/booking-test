# booking-test
UI Test Automation project for [Booking.com](https://www.booking.com/)

Originally the purpose of this project was to demonstrate my knowledge in Software Testing Automation by performing a
 technical task. And now, after finishing the project I was interviewed for I decide to reuse this tech task to
  practice in creating Test Automation Framework.
  
### Objectives
for each set of tools for framework
- Implement parallel run of tests
- Running subset of tests by test levels and features
- Use config manager

###Links to other documentation:
- [Tryings and Conclusions](docs/experience.md)

### Further improvements
It also would be nice to implement: 
- data provider for tests as well as base test with pre and post conditions; 
- environment properties and property controller; 
- configure downloading of chrome driver for platforms other than just windows; using BDD tool e.g. JBehave;
- using git branching strategy and a code review tool; 
- other points of improvement can rise after enabling CI, using different environments and configurations, expanding tests etc. 

### Reporting

Test reporting is already implemented at Serenity, so it can be used as it is. 

Use `mvn clean test serenity:aggregate` to run test and generate the test report. The report can be found at `target/site/serenity/index.html`
