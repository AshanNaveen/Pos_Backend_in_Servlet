
# Pos Backend in Servlet
![MIT License](https://img.shields.io/badge/License-MIT-green.svg)

The backend of the POS system is developed using Java Servlets to manage the core functionalities required for retail operations. It is responsible for handling various aspects of the business, including customer management, inventory management, and order processing. The system is designed to be scalable and secure, ensuring smooth operations in a retail environment.


## Features
- ### Customer Management

    - Create, update, and delete customer profiles.
    - Manage customer details such as name, contact information,  and purchase history.

- ### Item Management

    - Add, update, and remove items from the inventory.
    - Track item details including quantity, pricing, and categories.
- ### Order Management

    - Create and process customer orders.
    - Manage order details like items purchased, quantities, and pricing.
    - Track order status and generate receipts.
## Technologies Used
- **Java Servlets**: Core backend framework for handling HTTP requests and responses.
- **JDBC (Java Database Connectivity)**: For connecting and interacting with the database.
- **MySQL**: The relational database system used for storing customer, item, and order data.
- **Apache Tomcat**: The web server used to deploy and run the Java Servlet application.
## Installation
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 9 or higher
- MySQL or any other compatible relational database
- A Java IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps

- #### Clone the Repository
    ```bash
    git clone https://github.com/yourusername/pos-system-backend.git

    cd pos-system-backend
    ```
- #### Set Up the Database

  ##### Create a MySQL database for the POS system.
  Import the schema.sql file provided in the `src/main/resources` folder to set up the database schema.
  ##### Configure the Database Connection

  Open the `context.xml` file in the `src/main/webapp/META-INF/context.xml` directory.
  Update the database URL, username, and password with your MySQL database credentials.

- #### Build the Project

  If using Maven, run the following command to build the project:

    ```bash
    mvn clean install
    ````
- #### Deploy on Apache Tomcat

  Copy the generated WAR file from the /target directory to the Tomcat webapps directory.
  Start Apache Tomcat and navigate to http://localhost:8080/pos-system-backend.

- #### Access the Application

  Use a REST client (e.g., Postman) or a front-end interface to interact with the backend APIs.

## Documentation

- [Documentation about CRUD operations](https://documenter.getpostman.com/view/32449934/2sA3s1pCA7)

### [MIT](./License.txt) License


## Feedbacks
Please give your valuable feedback through GitHub. 
