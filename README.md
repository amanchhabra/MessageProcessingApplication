# Mesage Processing Application


![Maven](http://4.bp.blogspot.com/-vltC1shXVtM/VB_ERpPBMAI/AAAAAAAAA24/HzexNUF5R2U/s1600/maven.jpg)

A message processing application that is capable of processing different sales message, taking out the important information from them and do the necessary manipulations required.

This application logs the current sale stats after every 10th message received and can only process upto 50 messages.

Variaous different messages supported are :

    1. Single sale message like "apple at 10p"
    2. Multiple sales message like "20 sales of apples at 10p each"
    3. Update sale message like "Multiply 2 apples". Update operations allowed are:
        a. Add
        b. Subtract
        c. Multiply
     
     

[More Information...](https://amanchhabra.github.io/MessageProcessingApplication/)

## Installing and Runing the server

### Standalone

     1. Clone the repository
     2. Maven installation is a prerequisite 
     3. Install all dependencies in pom.xml
     4. Run the server as a normal JAVA application

### Using as a JAR in another project

     1. Clone the repository
     2. Maven installation is a prerequisite 
     3. Run "mvn compile assembly:single" command
     4. Use the JAR exported in the target folder
     

## Configuration

### Logging Configuration

You can also change the logging level and name by ameding the log4j.properties file.

## Logs

All the logs are currently printed in console and also logged in MessageProcessingApplication.log

## Service Functions

___________________________________________________________________________________________________________________
               Method and Description                                                                              |
-------------------------------------------------------------------------------------------------------------------|
void	                    processMessage(java.lang.String message)                                               |
                            To calculate the Dividend Yield of provided Stock instance                             |
-------------------------------------------------------------------------------------------------------------------|

## Class Diagram

 ![Class Diagram](/docs/design/class_diagram.png)
 
## Troubleshooting

Application is simple and easy to troubleshoot. You can enable debug logs or start as a stand alone application in debug. Also, 52 Test cases are written to ensure the application is always intact and any breaking change can easily be rectified.

## License
MIT

