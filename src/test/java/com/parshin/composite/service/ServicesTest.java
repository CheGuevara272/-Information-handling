package com.parshin.composite.service;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;
import com.parshin.composite.parser.ParagraphParserChain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ServicesTest {
    public final String testText = "\tFirst paragraph with three sentences. \nSentence two! Longer sentence three, with longest word pneumonoultramicroscopicsilicovolcanoconiosis?\n" +
            "\tSecond paragraph with one sentence...\n" +
            "\tThird paragraph with four sentences and longest word pneumonoultramicroscopicsilicovolcanoconiosis? Second sentence! Sentence three. And sentence four...\n" +
            "\tFourth paragraph with two sentences. Sentence number two?!";
    public final String sortedTestText = "\tSecond paragraph with one sentence... \n" +
            "\tFourth paragraph with two sentences. Sentence number two?! \n" +
            "\tFirst paragraph with three sentences. Sentence two! Longer sentence three, with longest word pneumonoultramicroscopicsilicovolcanoconiosis? \n" +
            "\tThird paragraph with four sentences and longest word pneumonoultramicroscopicsilicovolcanoconiosis? Second sentence! Sentence three. And sentence four... \n";
    public final String sentencesWithLongestWord = "Longer sentence three, with longest word pneumonoultramicroscopicsilicovolcanoconiosis? " +
            "Third paragraph with four sentences and longest word pneumonoultramicroscopicsilicovolcanoconiosis? ";
    public final String mostFrequentWord = "sentence=7";
    Services services;
    ParagraphParserChain parser;
    TextComposite textComposite;

    @BeforeMethod
    public void setUp() {
        services = Services.getInstance();
        parser = new ParagraphParserChain();
        textComposite = new TextComposite(TextComponentType.TEXT);
    }

    @Test
    public void testSortParagraphsBySentenceNumber() {
        parser.parse(textComposite, testText);
        List<TextComponent> sorted = services.sortParagraphsBySentenceNumber(textComposite);
        StringBuilder str = new StringBuilder();
        for (var paragraph : sorted) {
            str.append(paragraph.toString());
        }
        String actual = str.toString();
        String expected = sortedTestText;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindSentencesWithLongestWord() {
        parser.parse(textComposite, testText);
        List<TextComponent> sentences = services.findSentencesWithLongestWord(textComposite);
        StringBuilder str = new StringBuilder();
        for (var paragraph : sentences) {
            str.append(paragraph.toString());
        }
        String actual = str.toString();
        String expected = sentencesWithLongestWord;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindDuplicateNumber() {
        parser.parse(textComposite, testText);
        String actual = services.findDuplicateNumber(textComposite);
        String expected = mostFrequentWord;
        assertEquals(actual, expected);
    }
}