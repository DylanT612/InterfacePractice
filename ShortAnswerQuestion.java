/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - ShortAnswerQuestion
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// ShortAnswerQuestion.java
package com.jsoftware.test;

import com.jsoftware.test.api.IQuestion;
import java.io.Serializable;
import java.util.Arrays;

// short answer question provides code for IQuestion and Serializable
public class ShortAnswerQuestion implements IQuestion, Serializable {
    private String question;
    private String[] keywords;

    // Constructor
    public ShortAnswerQuestion(String question, String[] keywords) {
        this.question = question;
        this.keywords = keywords;
    }

    // returns short answer question getQuestion
    @Override
    public String getQuestion() {
        return question;
    }

    // returns lowercase version of the answer in case odd input
    public boolean checkAnswer(String answer) {
        for (String keyword : keywords) {
            if (!answer.toLowerCase().contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
