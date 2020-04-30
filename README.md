# sdet-test

Problem Statement:-

A) Using the provide REST service, create a program that returns, at minimum, capital city based on user input for name or code.

B) Write several tests that validate positive and negative scenario’s

User input can be done in any format, including but not limited to prompts and console entries.  

Program must continue running until the user exits the program.

Note: Do not use any other web services or data other then the one provide.

``WebService:- https://restcountries.eu/#similar-projects``


Solution:-

A) Run the class SearchByNameOrCode as java main program. You will be promted to enter a string for Name or Code. When a valid Name or Code is entered all the matching results will be printed accordingly. If the entered input does not match any valid Name or Code no data will be printed. 

Below Two services are invoked to get the results.

`NAME Service:- https://restcountries.eu/rest/v2/name/{name}`

`Code Service:- https://restcountries.eu/rest/v2/alpha/{code}`


Note:- Code service is invoked only if the input size is 2 or 3.


B) Test cases have been written for Name and Code services seperately.

Please run the command `mvn test` to execute the test cases


