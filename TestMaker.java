/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - Making a created test
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// TestMaker.java
package com.jsoftware.test;

import com.jsoftware.test.api.*;

import java.util.Scanner;

// TestMaker class
public class TestMaker {
    private static Scanner scanner = new Scanner(System.in);
    private static IQuestionFactory questionFactory = new QuestionFactory();

    private static boolean keepGoing = true;

    public static void main(String[] args) {
        // Welcome header prompting user what test they would like to make
        System.out.println("Welcome to the TestMaker program!");
        System.out.print("What would you like to call this test? ");
        String testName = scanner.nextLine();
        IQuestionSet testSet = questionFactory.makeEmptyQuestionSet();

        // main menu repeats until 6 is entered
        while (keepGoing) {
            System.out.println("What would you like to do?");
            System.out.println("1) add a multiple-choice question");
            System.out.println("2) add a true/false question");
            System.out.println("3) add a fill-in-the-blank question");
            System.out.println("4) add short answer question");
            System.out.println("5) remove a question");
            System.out.println("6) exit program");
            // User input for main menu
            System.out.print("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addMultipleChoiceQuestion(testSet);
                    break;
                case 2:
                    addTrueFalseQuestion(testSet);
                    break;
                case 3:
                    addFillInBlanksQuestion(testSet);
                    break;
                case 4:
                    addShortAnswerQuestion(testSet);
                    break;
                case 5:
                    removeQuestion(testSet);
                    break;
                case 6:
                    saveTest(testSet, testName);
                    System.out.println("Goodbye!");
                    keepGoing = false;
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // create a multiple choice question
    private static void addMultipleChoiceQuestion(IQuestionSet testSet) {
        System.out.print("What is your multiple-choice question? ");
        String question = scanner.nextLine();
        // create a choice for the 4 options
        String[] choices = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Please enter your choice " + (i + 1) + ": ");
            choices[i] = scanner.nextLine();
        }
        // get the answer
        System.out.print("What choice was the answer? (Enter #1-4): ");
        int answer = Integer.parseInt(scanner.nextLine()) - 1;

        // return multiple choice question choices and answer and add it to testSet
        IQuestion mcQuestion = questionFactory.makeMultipleChoice(question, choices, answer);
        testSet.add(mcQuestion);
    }

    // adding a true false question
    private static void addTrueFalseQuestion(IQuestionSet testSet) {
        // get question and answer
        System.out.print("What is your True/False question? ");
        String question = scanner.nextLine();
        System.out.print("What is the answer? (Please enter exactly true or false) ");
        boolean answer = Boolean.parseBoolean(scanner.nextLine());

        // add question and answer to testSet
        IQuestion tfQuestion = questionFactory.makeTrueFalse(question, answer);
        testSet.add(tfQuestion);
    }

    // add fill in the blanks question
    private static void addFillInBlanksQuestion(IQuestionSet testSet) {
        // get question and answers
        System.out.print("What is your fill in the blank question? ");
        String question = scanner.nextLine();
        System.out.print("What is the answer? Please separate answers with a comma: ");
        String[] answers = scanner.nextLine().split(",");

        // adds fill in the blanks to questionSet
        IQuestion fbQuestion = questionFactory.makeFillInBlank(question, answers);
        testSet.add(fbQuestion);
    }

    // add short answer question
    private static void addShortAnswerQuestion(IQuestionSet testSet) {
        // get question and number of keywords from user
        System.out.print("What is your short answer question? ");
        String question = scanner.nextLine();
        System.out.print("How many keywords does your short answer question have? ");
        int numKeywords = Integer.parseInt(scanner.nextLine());
        String[] keywords = new String[numKeywords];
        // for each keyword ask user what keyword it is
        for (int i = 0; i < numKeywords; i++) {
            System.out.print("What is a keyword in your short answer question? ");
            keywords[i] = scanner.nextLine();
        }

        // add short answer question to testSet
        IQuestion saQuestion = questionFactory.makeShortAnswer(question, keywords);
        testSet.add(saQuestion);
    }

    // removing a question
    private static void removeQuestion(IQuestionSet testSet) {
        // index each question
        System.out.println("Select the index of the question you would like to remove.");
        for (int i = 0; i < testSet.size(); i++) {
            System.out.println("Question " + i + ") " + testSet.getQuestion(i).getQuestion());
        }
        // remove question based on user input
        System.out.print("Your choice: ");
        int index = Integer.parseInt(scanner.nextLine());
        testSet.remove(index);
    }

    // save the test
    private static void saveTest(IQuestionSet testSet, String testName) {
        // take user testName and .test and have it write the file
        if (questionFactory.save(testSet, testName + ".test")) {
            System.out.println("Test saved.");
        } else {
            System.out.println("Failed to save test.");
        }

    }
}
