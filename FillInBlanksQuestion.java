/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - FillInBlanksQuestion
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// FillInBlanksQuestion.java
package com.jsoftware.test;

import com.jsoftware.test.api.IQuestion;
import java.io.Serializable;
import java.util.Arrays;

// FillInBlanksQuestion provides code for IQuestion and Serializable
public class FillInBlanksQuestion implements IQuestion, Serializable {
    private String question;
    private String[] answers;

    // Constructor
    public FillInBlanksQuestion(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    // FillInBlanksQuestion getQuestion method()
    @Override
    public String getQuestion() {
        return question;
    }

    // FillInBlanksQuestion checkAnswer to make sure the answers match the keywords
    public boolean checkAnswer(String[] keywords) {
        return Arrays.equals(answers, keywords);
    }
}

