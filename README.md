# booking-test
UI Test Automation project for [Booking.com](https://www.booking.com/)

It also would be nice to implement: 
- data provider for tests as well as base test with pre and post conditions; 
- environment properties and property controller; 
- configure downloading of chrome driver for platforms other than just windows; using BDD tool e.g. JBehave;
- using git branching strategy and a code review tool; 
- other points of improvement can rise after enabling CI, using different environments and configurations, expanding tests etc. 

Test reporting is already implemented at Serenity, so it can be used as it is. 

Use `mvn clean test serenity:aggregate` to run test and generate the test report. The report can be found at `target/site/serenity/index.html`
