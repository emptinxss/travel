# VOCI

## Table of contents

-   [Overview](#overview)
    -   [Start2impact](#Start2impact)
    -   [Project](#Project)
    -   [Built with](#built-with)
-   [Getting started](#Getting-started)
    -   [Installation](#Installation)
-   [Usage](#Usage)
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


## Getting Started

Follow these stepsto try the application in local. (RECOMMENDED)

If you need to install [Git](https://git-scm.com/downloads).

### Installation

1. Clone the repository locally with the git command:

    ```sh
    git clone https://github.com/emptinxss/travel.git
    ```

2. Switch to the project folder:

    ```sh
    cd app
    ```
8. Switch to the jar folder:

   ```sh
   cd path/to/app.jar
   ```
   
9. Run the jar file with the following command:

    ```sh
    java -jar app.jar
    ```

## Usage

Raccomended tool to try the requests. [Postman](https://www.postman.com/)

REST API endpoints:

### Authors

-   **GET** /api/v1/authors
-   **GET** /api/v1/authors/{id}
-   **POST** /api/v1/authors
-   **PUT** /api/v1/authors/{id}
-   **PATCH** /api/v1/authors/{id}
-   **DELETE** /api/v1/authors/{id}

### Media

-   **GET** /api/v1/media
-   **GET** /api/v1/media/{id}
-   **POST** /api/v1/media
-   **PUT** /api/v1/media/{id}
-   **PATCH** /api/v1/media/{id}
-   **DELETE** /api/v1/media/{id}

> **Note**
> For a succesful POST request you have to manually add the media file to path "public/uploads/media" and write the exact name of the file like the example below:

![Immagine 2022-10-09 092537](https://user-images.githubusercontent.com/83363396/194754715-2de0c1e7-96ab-4f00-a654-759e680f1f8d.png)

Make sure to select raw and JSON when performing POST PUT and PATCH request.

EXTENSION SUPPORTED:

-   IMAGE: jpg|png|jpeg|gif
-   AUDIO: mp3|ogg|wav
-   VIDEO: mp4

TROUBLESHOOTING:
If you dont get the response automatically check on the headers tab if you get **Content/type** application/json.

![Immagine 2022-10-09 093802](https://user-images.githubusercontent.com/83363396/194754726-fa020f35-0119-4319-b2e4-7bf4ba832c95.png)

Alternatively, you can force it by adding a new header **Accept** application/json.

![Immagine 2022-10-09 093918](https://user-images.githubusercontent.com/83363396/194754728-877d9ca6-9345-430e-83d1-b8da7b90b991.png)


## Links

-   My other projects on [Github](https://github.com/emptinxss)

## License

Distributed under the MIT License. See `LICENSE` for more information.