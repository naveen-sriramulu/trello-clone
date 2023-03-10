# Trello Clone
This is a Trello clone application developed using Java 17, Spring Boot project built with Gradle. The purpose of this application is to provide a basic clone of the popular Trello application where users can create and manage boards, lists, and cards.

### Requirements
* Java 17 or later
* Gradle 7 or later

### Getting Started
#### Clone the repository
```
git clone https://github.com/{{your_username}}/trello-clone.git
```

#### Build the project
```
cd {{your_project}}
./gradlew build
```

#### Run the project
```
./gradlew bootRun
```

### Access the application
Once the project is running, you can access the application by visiting http://localhost:8090 in your web browser.

### Features
This application provides the following features:

* Get the board as a JSON message
* Search for cards containing a tag
* Search for cards in a column
* Retrieve cards created after a given timestamp
* Retrieve the cards to be highlighted for an user

### Future Scope
* Create and manage boards
* Create and manage lists within a board
* Create and manage cards within a list
* Move cards between lists