/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 5/24/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Program 3 Computerizing Tests - QuestionSet
Description: using abstract class, interface, package, serializable, factory design pattern to provide
software for the client to create tests that students can take on computers

 */

// QuestionSet.java
package com.jsoftware.test;

import com.jsoftware.test.api.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// QuestionSet provides code for IQuestionSet and Serializable
public class QuestionSet implements IQuestionSet, Serializable {
    private List<IQuestion> questions;

    // Initialize QuestionSet
    public QuestionSet() {
        this.questions = new ArrayList<>();
    }

    // returns a QuestionSet that is empty
    @Override
    public IQuestionSet emptyTestSet() {
        return new QuestionSet();
    }

    // when selecting random sample of questions
    @Override
    public IQuestionSet randomSample(int size) {
        // create new question set and ArrayList
        QuestionSet sampleSet = new QuestionSet();
        List<IQuestion> sampleList = new ArrayList<>(questions);
        Random random = new Random();

        // removes randomly chosen question and adds it to the sample set
        for (int i = 0; i < size && !sampleList.isEmpty(); i++) {
            sampleSet.add(sampleList.remove(random.nextInt(sampleList.size())));
        }

        return sampleSet;
    }

    // adds a question to the question list
    @Override
    public boolean add(IQuestion question) {
        return questions.add(question);
    }

    // removes a question from the question list
    @Override
    public boolean remove(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            return true;
        }
        return false;
    }

    // returns the question
    @Override
    public IQuestion getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    // returns the number of questions in QuestionSet
    @Override
    public int size() {
        return questions.size();
    }
}
