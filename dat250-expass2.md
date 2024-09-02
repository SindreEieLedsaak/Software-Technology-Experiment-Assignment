

# DAT250 Experiment Assignment 2 Report

## Introduction

The objective of this assignment was to develop a simple REST API for a Poll application using Spring Boot. The task involved implementing CRUD operations for various domain entities such as Users, Polls, Votes, and VoteOptions.
The project followed a test-driven approach and involved several steps, including setting up a repository, implementing the domain model, creating test scenarios, and automating tests.

## Step 1: Setting Up the Spring Project Repository

The first step was to set up a GitHub repository for the project.
I started by initializing a local Git repository within the root directory of the Spring Boot application.
After committing all the application files, I added the newly created GitHub repository as a remote and pushed the initial commit.


## Step 2: Domain Model Implementation

For the domain model, I implemented the classes for Users, Polls, Votes, and VoteOptions. 
These classes were designed as simple Java Beans with public getters and setters, along with a parameterless constructor.
Additionally, I created a `DomainManager` class annotated with `@Component`, which manages all domain objects in memory using HashMaps.
This setup allowed for easy dependency injection into the controller classes.

### Technical Challenges
- ID Management: One challenge was deciding on the appropriate type of ID for entities. I chose to use UUIDs for both users and polls to ensure unique identification across the application. The `POST` methods were adjusted to return a HashMap containing the UUID and the associated object.

- Testing: Testing was also a significant challenge, particularly ensuring that the REST API responded correctly to various operations. Handling the order of returned objects in JSON responses and making the tests order-agnostic required careful consideration. Additionally, adjusting the jsonPath expressions to match the actual JSON structure returned by the API, especially for nested objects, was complex and required iterative refinement.
## Step 3: Implementing Test Scenarios

 1. Create a new user
 2. List all users (-> shows the newly created user)
 3. Create another user
 4. List all users again (-> shows two users)
 5. User 1 creates a new poll
 6. List polls (-> shows the new poll)
 7. User 2 votes on the poll
 8. User 2 changes his vote
 9. List votes (-> shows the most recent vote for User 2)
 10. Delete the one poll


These tests were conducted using an HTTP client, ensuring that the application behaved as expected in various scenarios.

## Step 4: Implementing the Controllers

The controllers were implemented using the `@RestController` and `@...Mapping` annotations to handle CRUD operations for the different entities.
Each controller was designed to manage a specific entity, ensuring that all required operations (create, read, update, delete) were supported where applicable.


## Step 5: Automating Testing

To ensure the reliability of the application, I automated the test scenarios. 
This allowed me to run the tests without manual intervention, ensuring that all API endpoints worked correctly.
I used Spring's testing framework to write integration tests that could be executed automatically.

## Pending Issues

 All functionalities worked as expected, and the application met the requirements. 
 The use of UUIDs for unique identification and the proper handling of serialization issues were particularly successful.



