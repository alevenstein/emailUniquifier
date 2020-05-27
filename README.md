Thanks for checking out my sample code:

This application is built using the Grails application framework (www.grails.org), which uses the Groovy language. It's a great way to create sophisticated Java-based web applications much more quickly than Spring Boot.

To execute, download Grails and then run this application from the command line using "grails run-app."

To run the request, send a POST request to localhost:8080/home using the following JSON format:

{
    emails: [
             "test@gmail.com", 
             "test2@gmail.com" 
            ]
}

It will then, per requirements, provide an integer with the unique number of emails using "gmail rules."

I used a simple comparator approach, rather than anything involving a complex loop or messing with a HashMap.

As a bonus, in the offhand chance you're offering extra credit, I also added a couple of unit tests (see the src/test/groovy directory). To run them, type "grails test-app" from the project root directory.

