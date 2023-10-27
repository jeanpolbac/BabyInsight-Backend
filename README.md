# BabyInsight - Backend

## Status

🚧 **This project is currently in development.** 🚧

## Description
This is the backend repository for the BabyInsight project, designed to help parents manage and track important aspects of their child's healthcare. Features include authentication, a dashboard for a quick overview, a vaccine tracker, a medication log, and an interaction checker.

## Related Repositories
For the frontend part of this project, please visit [BabyInsight - Frontend](https://github.com/jeanpolbac/BabyInsight-Frontend).

## Planning Documentation
For detailed planning and task breakdown, please visit our [Trello Board](https://trello.com/b/PMlxH7hu).

---

### User-Centric Design

Our project starts with a focus on user stories, ensuring that every feature and functionality is designed with the end-users in mind. We've carefully crafted user stories to capture the essential interactions users will have with the Baby Insight application.

<details>
  <summary>User Registration and Login</summary>

**User Story**: As a new user, I want to be able to register for an account with an email and password.

**Acceptance Criteria**:
- Given I am a new user, when I register, then my account is created.

**User Story**: As a registered user, I want to be able to log in to my account securely.

**Acceptance Criteria**:
- Given I am a registered user, when I log in with my correct email and password, then I have access to the application features.
</details>

<details>
  <summary>Vaccine Tracker</summary>

**User Story**: As a parent, I want to track my newborn's vaccinations so that I can ensure they are administered on time.

**Acceptance Criteria**:
- The application should come with a pre-loaded list of standard vaccines for newborns.
- Each vaccine should have an "administered" option to mark it with the date it was given.
- Past due vaccines should be highlighted.
</details>

<details>
  <summary>Medication Log</summary>

**User Story**: As a parent, I want to log and track my child's medication to ensure they are given the correct dosage on time.

**Acceptance Criteria**:
- The log should have fields for medication name, dosage, frequency, and duration.
- The log should display a list of all active medications.
- Completed or expired medications should be archived and not shown in the active list.
</details>

<details>
  <summary>Interaction Checker</summary>

**User Story**: As a parent, I want to check if there are any interactions between different medications to ensure my child's safety.

**Acceptance Criteria**:
- Users should be able to input two or more medications.
- The application should check for interactions from a predefined list.
- If interactions are found, the application should display a warning message.
- If no interactions are found, a confirmation message should be shown.
</details>

---

## User-Centric Design
To model our data structure effectively, we've created an Entity-Relationship Diagram (ERD). This visual representation maps out the relationships between various entities in our system, such as users, child profiles, vaccines, and medications. The ERD serves as a blueprint for our database design, ensuring that data is organized and structured efficiently.

![ERD](assets%2Ferd-babyinsight.png)
---

## Planning Documentation
For detailed planning and task breakdown, please visit our [Trello Board](https://trello.com/b/PMlxH7hu).

---

## Features

### Track Vaccines for Your Child

Our application serves as a comprehensive guide to your child's immunization journey. Keep all your child's vaccine information in one place and have it at your fingertips, anytime, anywhere.

### Know Administered Vaccines

Say goodbye to sifting through papers and health records. Our app keeps a log of all vaccines your child has received. It gives you detailed information, including the type of vaccine and the date it was administered. This way, you are always aware of your child's immunization status and can easily provide records to healthcare providers or educational institutions.

### Overdue Alerts

Never miss a vaccine again! Our app sends you timely notifications for any vaccines that are overdue, ensuring your child's immunization schedule stays on track. In the overdue section, you can quickly check which vaccines you have missed along with the recommended time frame for each.

### Upcoming Vaccines

Planning is key to ensuring your child gets all the required vaccines on time. Our application provides you with a list of upcoming vaccines, complete with due dates and information about each vaccine. With these reminders, you can easily schedule appointments with healthcare providers well in advance.

### Peace of Mind

The uncertainty and complexity of managing your child's vaccination schedule can be stressful. Our app alleviates that stress and ensures you are well-informed, helping you make better healthcare decisions for your child.

### User-Friendly Dashboard

All of these features come wrapped in an intuitive, user-friendly interface. With simple navigation and easy-to-read displays, tracking your child's vaccines has never been this effortless.

---

## Project Approach

### The Challenge of New Parenthood

Becoming a parent is a monumental milestone filled with joy, love, and, naturally, challenges. One such challenge that new parents often face is navigating through the complex and sometimes overwhelming world of childhood vaccinations. As a parent of a newborn, I found myself jumping through hoops, scouring numerous resources, and dealing with a maze of healthcare guidelines to get accurate and timely information about vaccinations. While these initial years should be spent enjoying the miraculous journey of your child's growth, parents often find themselves bogged down by the intricacies of vaccine schedules, dosages, and medical jargon.

### The Inspiration

That's when it hit me—why not build something for future parents that would make those first years easier to enjoy? I wanted to create a tool that alleviates this stress, so parents can focus on what really matters: raising a happy, healthy child.

### The Solution: A One-Stop App for Child Immunization

And so, this app was born—crafted with the utmost care and diligence to serve as a one-stop solution for all your child’s vaccination needs. With features that allow you to track administered vaccines, set overdue alerts, and even get a heads-up on upcoming vaccinations, the app aims to empower parents to take control of their child's health in a simplified and user-friendly way.

### Empowering Parents

Through this app, I hope to bring peace of mind to you as a parent, because when you're informed and prepared, you can make better healthcare decisions for your child. With all the information and reminders at your fingertips, you can reclaim those joyful moments that might otherwise be lost to worry and uncertainty.

### The Reward

The true reward for this project lies in the knowledge that it may help parents breathe a little easier and enjoy the priceless first years of their child’s life a bit more fully. After all, informed parenting is empowered parenting, and that’s a gift that keeps on giving.

---

### Model View Controller Design (MVC)
Implement MVC Architecture to aid in separation of concerns.

***<p style="text-align: center;">Send Request &rarr; Controller &rarr; Service &rarr; Repository &rarr; Model &rarr; H2 Database</p>***

***<p style="text-align: center;">Receive Response &larr; Controller &larr; Service &larr; Repository &larr; Model &larr; H2 Database</p>***

---

### REST API Endpoints with OpenAPI Documentation
Our application's functionality is exposed through a set of REST API endpoints. These endpoints are meticulously designed to provide users with seamless access to BabyInsight's features.
Here's a glimpse of some of the key API endpoints:

| Entity              | HTTP Method | Endpoint                                     | Description                                 |
| ------------------- | ----------- | -------------------------------------------- | ------------------------------------------- |
| Dashboard           | GET         | `/dashboard/{parentID}`                     | Fetch dashboard data for a parent.          |
| Vaccine Tracker     | GET         | `/vaccines`                                 | Fetch the pre-loaded list of standard vaccines for newborns. |
|                     | GET         | `/vaccines/{childID}`                       | Fetch the list of vaccines for a specific child. |
|                     | PUT         | `/vaccines/{vaccineID}`                     | Update the "administered" status and date for a specific vaccine. |
| Medication Log      | GET         | `/medications/{childID}`                    | Fetch the list of active medications for a specific child. |
|                     | POST        | `/medications`                              | Add a new medication entry for a child.     |
|                     | PUT         | `/medications/{medicationID}`               | Update an existing medication entry.         |
|                     | DELETE      | `/medications/{medicationID}`               | Delete (or archive) a specific medication entry. |
| Interaction Checker | GET         | `/interactions?med1={medicationID1}&med2={medicationID2}` | Check for interactions between two medications. |
| User (Parent)       | POST        | `/users`                                    | Register a new user.                        |
|                     | PUT         | `/users/{parentID}`                         | Update user details.                         |
|                     | GET         | `/users/{parentID}`                         | Fetch user details.                          |
|                     | POST        | `/users/login`                              | Login endpoint.                              |
| Child               | POST        | `/children`                                 | Add a new child for a parent.               |
|                     | GET         | `/children/{parentID}`                      | Fetch the list of children for a specific parent. |
|                     | PUT         | `/children/{childID}`                       | Update child details.                       |
|                     | DELETE      | `/children/{childID}`                       | Delete a child profile.                    |


### Comprehensive Testing
In order to maintain code quality and reliability, I've integrated Cucumber testing into my development process. I perform extensive testing, including public and protected endpoint testing, token testing, and thorough unit testing of service and controller components.

---

## Tools and Technologies Used
- **IntelliJ IDEA/Java 17:** IDE used in conjunction with Java SDK.
    - **Maven:** Powerful build automation tool and dependency management framework for Java projects.
- **Postman:** API platform for building and using APIs.
- **Spring Boot:** [Version 2.7.16](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent/2.7.16) Powers the backend.
    - **Tomcat Server:** Hosts the application.
    - **Spring Security:** Ensures data security.
    - **Spring RESTful API:** Offers user-friendly interaction.
    - **MVC Architecture:** Follows a clean code structure.
- **H2 Database:**  Manages data storage.
- **JWT Tokens:** [Version 0.11.5](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.11.5) Provides authentication and authorization.
- **Cucumber JVM:** [Version 6.8.1](https://mvnrepository.com/artifact/io.cucumber/cucumber-java/6.8.1) Testing framework that supports Behavior Driven Development.
- **REST Assured:** [Version 4.3.0](https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.3.0) Java library that provides a domain-specific language for writing powerful, maintainable test for RESTful APIs.
- **Documentation:** Well-documented codebase.
- **GitHub:** Version control and collaboration platform.

---

## Challenges Encountered During Development

### Spring Security Setup

- **Challenge**: Integrating Spring Security for user authentication and authorization is a crucial but sometimes complex task. Configuring security rules, roles, and permissions can be challenging, especially for complex access control requirements.

### JWT Token Generation and Validation

- **Challenge**: Implementing JWT token generation and validation in Spring Security can be intricate. Ensuring that tokens are securely generated, stored, and validated is crucial for application security.

### Cucumber-JVM Testing

- **Challenge**: Using Cucumber-JVM for behavior-driven development (BDD) testing can be challenging, especially  new to this framework. Writing and maintaining feature files, step definitions, and ensuring effective test coverage can be hurdles.

### Integration Testing

- **Challenge**: Testing the integration of various components, including the Spring Boot application, Spring Security, and JWT token handling, can be complex. Properly setting up and running integration tests can be challenging.

### Documentation and Code Maintenance

- **Challenge**: Maintaining comprehensive documentation and keeping the codebase clean and well-documented can be challenging but is essential for long-term maintainability.

### Testing Edge Cases

- **Challenge**: Identifying and testing edge cases in your application, especially related to Spring Security and JWT token handling, can be challenging but is crucial for robustness.

### Error Handling

- **Challenge**: Properly handling and logging errors and exceptions in a consistent and meaningful way can be a hurdle. Ensuring that error messages do not expose sensitive information is important for security.

---

## Reflection on the Capstone Project

This Capstone project has been a heck of an emotional roller coaster. There were moments of frustration, exhilaration, and everything in between. However, what I have discovered throughout this journey is that despite all of these emotions and challenges, I truly enjoy what I do.

The ups and downs of this project have only strengthened my passion for software development. It's a field that constantly pushes me to learn, adapt, and overcome obstacles. It's where I find fulfillment in solving complex problems, creating innovative solutions, and seeing the tangible results of my work.

While this project has tested my patience and resilience, it has also reaffirmed my commitment to pursuing a career in software engineering. It's not just a job; it's a vocation that brings me joy and satisfaction.

As I navigate the ever-evolving landscape of technology and development, I carry with me the valuable experiences and lessons learned during this Capstone project. It's a reminder that even in the face of challenges, my passion for this field remains unwavering.

---

## Resources & Acknowledgements
### General Assembly Instructors
- Suresh Sigera: [GitHub](https://github.com/sureshmelvinsigera)
- Dhrubo Chowdhury: [GitHub](https://github.com/Dhrubo-Chowdhury)
- Leonardo Rodriguez: [GitHub](https://github.com/LRodriguez92)

### Special Thanks
- Gabrielle Ynara: [GitHub](https://github.com/GabrielleYnara)
- Rick Maya: [GitHub](https://github.com/rickstylz01)

### Links
- [Java Official Documentation](https://docs.oracle.com/en/java/): Official documentation for the Java programming language.
- [Maven Official Documentation](https://maven.apache.org/guides/): Official documentation for Apache Maven, a build automation and project management tool.
- [Spring Boot Official Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/): Official documentation for Spring Boot, a framework for building Java applications.
- [Spring Security Official Documentation](https://docs.spring.io/spring-security/site/docs/current/reference/html5/): Official documentation for Spring Security, which provides authentication and authorization support for Spring applications.
- [MDN Web Docs - CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS): Mozilla Developer Network documentation on CORS, an important topic for handling cross-origin requests in web applications.
- [Spring Data JPA - Associations](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-relations): Documentation on defining associations, including many-to-many and many-to-one relationships, in Spring Data JPA.
- [JSON.org](https://www.json.org/json-en.html): Official JSON website with information about the JSON data interchange format.
- [Cucumber Official Documentation](https://cucumber.io/docs/cucumber/): Official documentation for Cucumber, a tool for behavior-driven development (BDD) testing.
