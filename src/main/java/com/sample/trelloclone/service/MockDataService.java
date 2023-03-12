package com.sample.trelloclone.service;

import com.sample.trelloclone.entity.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MockDataService {

    private final Map<Integer, User> users = Map.of(
            1, User.builder().id(1).name("Naveen").lastActiveOn(Instant.parse("2023-03-10T10:20:00Z")).build(),
            2, User.builder().id(2).name("Arjun").lastActiveOn(Instant.parse("2023-03-10T06:37:42Z")).build(),
            3, User.builder().id(3).name("Senthil").lastActiveOn(Instant.parse("2023-03-10T13:34:51Z")).build(),
            4, User.builder().id(4).name("Kishore").lastActiveOn(Instant.parse("2023-03-10T12:19:26Z")).build()
    );

    private final Map<Integer, Label> labels = Map.of(
            1, Label.builder().id(1).tag("ui").build(),
            2, Label.builder().id(2).tag("backend").build(),
            3, Label.builder().id(3).tag("MVP").build(),
            4, Label.builder().id(4).tag("sprint-1").build(),
            5, Label.builder().id(5).tag("sprint-2").build()
    );

    private final Map<Integer, Card> cards = Map.of(
            1, Card.builder()
                    .id(1)
                    .title("Create Readme")
                    .reportedBy(users.get(4))
                    .assignedTo(users.get(1))
                    .createdOn(Instant.parse("2023-03-10T10:20:26Z"))
                    .labels(Set.of(labels.get(3), labels.get(4)))
                    .build(),
            2, Card.builder()
                    .id(2)
                    .title("Basic UI Design")
                    .reportedBy(users.get(4))
                    .assignedTo(users.get(2))
                    .createdOn(Instant.parse("2023-03-10T03:17:27Z"))
                    .labels(Set.of(labels.get(1), labels.get(3), labels.get(4)))
                    .build(),
            3, Card.builder()
                    .id(3)
                    .title("Design Login Screen")
                    .reportedBy(users.get(3))
                    .assignedTo(users.get(2))
                    .createdOn(Instant.parse("2023-03-08T13:48:25Z"))
                    .labels(Set.of(labels.get(1), labels.get(3), labels.get(4)))
                    .build(),
            4, Card.builder()
                    .id(4)
                    .title("Provide OAuth Security")
                    .reportedBy(users.get(4))
                    .assignedTo(users.get(1))
                    .createdOn(Instant.parse("2023-03-11T21:57:43Z"))
                    .labels(Set.of(labels.get(2)))
                    .build(),
            5, Card.builder()
                    .id(5)
                    .title("Role based authorization")
                    .reportedBy(users.get(3))
                    .assignedTo(users.get(1))
                    .createdOn(Instant.parse("2023-03-11T22:25:30Z"))
                    .labels(Set.of(labels.get(2)))
                    .build(),
            6, Card.builder()
                    .id(6)
                    .title("Service to return the board as JSON")
                    .reportedBy(users.get(3))
                    .assignedTo(users.get(1))
                    .createdOn(Instant.parse("2023-02-19T10:20:00Z"))
                    .labels(Set.of(labels.get(1), labels.get(2)))
                    .build(),
            7, Card.builder()
                    .id(7)
                    .title("API to return all cards containing a tag")
                    .reportedBy(users.get(3))
                    .assignedTo(users.get(2))
                    .createdOn(Instant.parse("2023-03-05T23:20:50Z"))
                    .labels(Set.of(labels.get(2), labels.get(5)))
                    .build(),
            8, Card.builder()
                    .id(8)
                    .title("Endpoint to return all cards of a column")
                    .reportedBy(users.get(3))
                    .assignedTo(users.get(1))
                    .createdOn(Instant.parse("2023-03-03T15:32:45Z"))
                    .labels(Set.of(labels.get(2), labels.get(5)))
                    .build(),
            9, Card.builder()
                    .id(9)
                    .title("REST API to return all cards created after a given timestamp")
                    .reportedBy(users.get(1))
                    .createdOn(Instant.parse("2023-02-28T14:38:12Z"))
                    .labels(Set.of(labels.get(2), labels.get(5)))
                    .build(),
            10, Card.builder()
                    .id(10)
                    .title("Add menu item")
                    .reportedBy(users.get(4))
                    .createdOn(Instant.parse("2023-02-24T10:20:00Z"))
                    .labels(Set.of(labels.get(1), labels.get(3)))
                    .build()
    );
    private final Map<Integer, Column> columns = Map.of(
            1, Column.builder()
                    .id(1)
                    .name("in-progress")
                    .cards(Set.of(cards.get(3), cards.get(6), cards.get(9)))
                    .build(),
            2, Column.builder()
                    .id(2)
                    .name("review")
                    .cards(Set.of(cards.get(1), cards.get(2), cards.get(7)))
                    .build(),
            3, Column.builder()
                    .id(3)
                    .name("test")
                    .cards(Set.of(cards.get(5), cards.get(10)))
                    .build(),
            4, Column.builder()
                    .id(4)
                    .name("done")
                    .cards(Set.of(cards.get(4), cards.get(8)))
                    .build()
//            5, Column.builder().id(5).name("dev").build(),
//            6, Column.builder().id(6).name("demo").build(),
//            7, Column.builder().id(7).name("closed").build()
    );

    private final Set<Board> boards = Set.of(
            Board.builder()
                    .id(1)
                    .name("Trello Clone")
                    .columns(columns
                            .values()
                            .stream()
                            .filter(column -> column.getId() <= 4)
                            .collect(Collectors.toSet()))
                    .build()
//            Board.builder()
//                    .id(2L)
//                    .name("IOM")
//                    .columns(columns
//                            .values()
//                            .stream()
//                            .filter(column -> column.getId() >= 5 && column.getId() <= 7)
//                            .collect(Collectors.toSet()))
//                    .build()
    );

    public Optional<Board> getBoard() {
        return boards.stream().findAny();
    }
}
