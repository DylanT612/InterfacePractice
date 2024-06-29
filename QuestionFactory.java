/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - QuestionFactory
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// QuestionFactory.java
package com.jsoftware.test;

import com.jsoftware.test.api.*;
import java.io.*;

// QuestionFactory provides code for IQuestionFactory
public class QuestionFactory implements IQuestionFactory {

    // Multiple choice returns the question the choices and an int answer
    @Override
    public IQuestion makeMultipleChoice(String question, String[] choices, int answer) {
        return new MultipleChoiceQuestion(question, choices, answer);
    }

    // True/False returns the question and a boolean answer
    @Override
    public IQuestion makeTrueFalse(String question, boolean answer) {
        return new TrueFalseQuestion(question, answer);
    }

    // Fill in the blank returns a question and list of answers
    @Override
    public IQuestion makeFillInBlank(String question, String[] answers) {
        return new FillInBlanksQuestion(question, answers);
    }

    // SHort answer returns the question and a list of keywords
    @Override
    public IQuestion makeShortAnswer(String question, String[] keywords) {
        return new ShortAnswerQuestion(question, keywords);
    }

    // Load the file
    @Override
    public IQuestionSet load(String filename) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (IQuestionSet) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load class: " + e.getMessage());
            throw new IOException("Failed to load class", e);
        }
    }

    // save the test they created into the file they named
    @Override
    public boolean save(IQuestionSet testSet, String filename) {
        System.out.println();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(testSet);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to write file:" + e.getMessage());
            return false;
        }
    }

    // returns new QuestionSet
    @Override
    public IQuestionSet makeEmptyQuestionSet() {
        return new QuestionSet();
    }
}
