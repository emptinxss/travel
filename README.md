# PoolingAround

## Table of contents

-   [Overview](#overview)
    -   [Start2impact](#Start2impact)
    -   [Project](#Project)
    -   [Built with](#built-with)
-   [Getting started](#Getting-started)
    -   [Installation](#Installation)
    - [Build the jar file](#build-the-jar-file)
-   [Links](#Links)
-   [License](#License)

## Overview

### Start2impact

This is my first java project from the **Java Basics Start2Impact's** guide.
You can find all the info [here](https://www.start2impact.it/percorsi/) about start2impact courses.

### Project

The PoolingAround service was created to facilitate the travel of light travellers, away students and professionals. The service allows you to save money and emissions, allowing you to split the costs of the trip and fill the seats of a car that would otherwise have remained half-empty.

Assuming all that, the main goal is to create a command line application to view the travels and book a trip.

### Built with

-   [Maven](https://maven.apache.org/)
-   [Intellij IDEA 2023 (IDE)](https://www.jetbrains.com/idea/)
-   [JAVA 17](https://www.oracle.com/it/java/technologies/downloads/)


## Getting Started

Follow these steps to try the application in local

If you need to install [Git](https://git-scm.com/downloads).

### Installation

1. Clone the repository locally with the git command:

    ```sh
    git clone https://github.com/emptinxss/travel.git
    ```

2. You can now open the project with you preferred IDE download the dependecies with maven 
   and run the main class.

### Build the jar file

3. Before building the project you need to change the enviroment to "production".
    You will find it in com.travel.constants.GlobalConst.java:

   ```sh
   private static final String ENVIROMENT = "production";
   ```
4. If you decide to make the jar without your IDE feature make sure to have maven installed in your machine
   - [Download Maven](https://maven.apache.org/download.cgi)

5. Make sure to be in the path of the project.
   ```sh
   cd travel
   ```

6. Then create the 
   JAR file with you IDE feature or with Maven with the following command:

   ```sh
   mvn clean compile assembly:single
   ```

7. Switch to the jar folder:

   ```sh
   cd target
   ```
   
8. The JAR file name will be PoolingAround-1.0-jar-with-dependecies.jar. 
    Rename the jar file to PoolingAround.jar

9. You'll need to get the data folder and move it to the jar folder. Feel free to move the jar file into another folder, 
   but you must have this structure into it: 

    ```sh
   |
   |-data
   |  |-viaggi.csv
   |  |-prenotazioni.csv
   |  |-utenti.csv
   |-PoolingAround.jar
    ```
10. Run the jar file with the following command:

    ```sh
    java -jar PoolingAround.jar
    ```

## Links

-   My other projects on [Github](https://github.com/emptinxss)

## License

Distributed under the MIT License. See `LICENSE` for more information.