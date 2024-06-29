/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - MultipleChoiceQuestion
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// MultipleChoiceQuestion.java
package com.jsoftware.test;

import com.jsoftware.test.api.IQuestion;
import com.jsoftware.test.api.IMultipleChoiceQuestion;
import java.io.Serializable;
import java.util.Arrays;

// MultipleChoiceQuestion provides code for IQuestion and Serializable
public class MultipleChoiceQuestion implements IQuestion, Serializable {
    private String question;
    private String[] choices;
    private int correctAnswerIndex;

    // Constructor
    public MultipleChoiceQuestion(String question, String[] choices, int correctAnswerIndex) {
        this.question = question;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // MultipleChoiceQuestion getQuestion method
    @Override
    public String getQuestion() {
        StringBuilder sb = new StringBuilder(question);
        for (int i = 0; i < choices.length; i++) {
            sb.append("\n").append(i + 1).append(") ").append(choices[i]);
        }
        return sb.toString();
    }

    // MultipleChoiceQuestion checkAnswer method
    public boolean checkAnswer(int index) {
            return index == correctAnswerIndex;

    }
}
