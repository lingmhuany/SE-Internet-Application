# Word Ladder with Java

## Requirements
You need to install java and maven first.

## Run
There are two ways to run the program:
1. Run `mvn compile` and then `mvn exec:java` in the command line.
2. Run `mvn install` in the command line, enter the directory `/target`, and then run `java -jar wordladder-1.0-SNAPSHOT.jar`.

The file name of the dictionary is `dictionary.txt`. If you want to run the program in the second way, make sure that you have a copy of the dictionary file in `/target`.

## Test
If you have run the program in the second way, you must have seen the unit tests running automatically. You can also use `mvn test` to run the tests without installing the program.
