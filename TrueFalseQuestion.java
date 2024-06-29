/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - True/False Question
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// TrueFalseQuestion.java
package com.jsoftware.test;

import com.jsoftware.test.api.IQuestion;
import java.io.Serializable;

// true false question provides core for IQuestion and Serializable
public class TrueFalseQuestion implements IQuestion, Serializable {
    private String question;
    private boolean correctAnswer;

    // Constructor
    public TrueFalseQuestion(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    // return true false question
    @Override
    public String getQuestion() {
        return question + " True/False?";
    }

    // check answer if answer matches answer
    public boolean checkAnswer(boolean answer) {
        return answer == correctAnswer;
    }
}
