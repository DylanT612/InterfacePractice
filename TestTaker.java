/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - Taking a created test
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */



// TestTaker.java
package com.jsoftware.test;

import com.jsoftware.test.api.*;

import java.io.IOException;
import java.util.Scanner;

// TestTaker
public class TestTaker {
    private static Scanner scanner = new Scanner(System.in);
    private static IQuestionFactory questionFactory = new QuestionFactory();

    public static void main(String[] args) {
        // Welcome and ask user which test to take
        System.out.println("Welcome to the TestTaker program!");
        System.out.print("What test would you like to take? ");
        String testName = scanner.nextLine();

        IQuestionSet testSet = null;
        // load testName.test
        try {
            testSet = questionFactory.load(testName + ".test");
            // if cant find test
        } catch (IOException e) {
            System.out.println("Failed to load test. Exiting program.");
            return;
        }

        System.out.println("Test loaded successfully!");

        // ask if user wants to take full test of sample test
        System.out.print("Enter 1 to take a whole test or 2 to take a sample test: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            takeTest(testSet);
        } else if (choice == 2) {
            takeSampleTest(testSet);
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }
    }

    // starting test
    private static void takeTest(IQuestionSet testSet) {
        System.out.println("---------------------------- The test starts now! ----------------------------");
        int score = 0;
        // loops through each question in the testSet
        for (int i = 0; i < testSet.size(); i++) {
            IQuestion question = testSet.getQuestion(i);
            // if answer == question answer
            if (askQuestion(question)) {
                score++;
            }
        }
        System.out.println("You got " + score + " questions right out of " + testSet.size() + " questions total.");
    }

    // sample test
    private static void takeSampleTest(IQuestionSet testSet) {
        System.out.print("How many questions would you like to sample? ");
        int sampleSize = Integer.parseInt(scanner.nextLine());

        IQuestionSet sampleSet = testSet.randomSample(sampleSize);

        System.out.println("---------------------------- The test starts now! ----------------------------");
        int score = 0;
        // for the amount of questions they entered
        for (int i = 0; i < sampleSet.size(); i++) {
            IQuestion question = sampleSet.getQuestion(i);
            // if answer == question answer
            if (askQuestion(question)) {
                score++;
            }
        }
        System.out.println("You got " + score + " questions right out of " + sampleSet.size() + " questions total.");
    }

    // asking a question
    private static boolean askQuestion(IQuestion question) {
        System.out.println(question.getQuestion());

        // if multiple choice question matches or is wrong
        if (question instanceof MultipleChoiceQuestion) {
            System.out.print("Your answer: ");
            int answer = Integer.parseInt(scanner.nextLine()) - 1;
            if (((MultipleChoiceQuestion) question).checkAnswer(answer)) {
                System.out.println("You got it!");
                return true;
            } else {
                System.out.println("Wrong!");
                return false;
            }

        // if true false question matches or is wrong
        } else if (question instanceof TrueFalseQuestion) {
            System.out.print("Your answer: ");
            boolean answer = Boolean.parseBoolean(scanner.nextLine());
            if (((TrueFalseQuestion) question).checkAnswer(answer)) {
                System.out.println("You got it!");
                return true;
            } else {
                System.out.println("Wrong!");
                return false;
            }
            // if fill in blank question matches or is wrong
        } else if (question instanceof FillInBlanksQuestion) {
            System.out.print("Your answer: ");
            String[] answers = scanner.nextLine().split(",");
            if (((FillInBlanksQuestion) question).checkAnswer(answers)) {
                System.out.println("You got it!");
                return true;
            } else {
                System.out.println("Wrong!");
                return false;
            }
            // if short answer question matches or is wrong
        } else if (question instanceof ShortAnswerQuestion) {
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            if (((ShortAnswerQuestion) question).checkAnswer(answer)) {
                System.out.println("You got it!");
                return true;
            } else {
                System.out.println("Wrong!");
                return false;
            }
        }
        return false;
    }


}
