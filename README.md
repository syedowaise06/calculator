# Reward Calculator Application

This application is used to calculate the rewards points earned
by a customer in the last three months and total.
This application exposes the following REST Endpoints :-
* GET /rewards - fetch the rewards for all customers
* GET /rewards/{id} - fetch the rewards for the given customer id

## Running Application
* We can run the application from any IDE after importing the application as maven project.
* There is no special instructions needed to run this application in Intellij/Eclipe, we can simply run the `main` method present in `CalculatorApplication` class.
* Once the application is started, we can access the following endpoints :-
  * http://localhost:8080/rewards
  * http://localhost:8080/rewards/1
  * http://localhost:8080/rewards/2

## Assumptions
* I have assumed that we don't have to worry about the database as part of this assessment, otherwise the scope would have increased further.
I have rather created a variable to hold all the transactions in DAO class and written appropriate methods to return the expected data.
* We can ship the source code as is and don't have worry about exporting as a jar file and providing related instructions.
* In interest of time, I have not put any validation to check the input (i.e. if customer id is valid or not). It also depends on the requirement hwo do we want to handle these kind of edge cases.
