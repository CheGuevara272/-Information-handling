package com.parshin.composite.service;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;

import java.util.*;
import java.util.stream.Collectors;

public class TextService {
    private static final TextService instance = new TextService();

    public static TextService getInstance() {
        return instance;
    }

    public List<TextComponent> sortParagraphsBySentenceNumber(TextComposite textComposite) {
        List<TextComponent> paragraphs = textComposite.getChildren();

        return paragraphs.stream().sorted(Comparator.comparingInt(p -> p.getChildren().size())).collect(Collectors.toList());
    }

    public List<TextComponent> findSentencesWithLongestWord(TextComposite textComposite) {
        List<TextComponent> paragraphs = textComposite.getChildren();
        ArrayList<String> wordList = getListOfWord(textComposite);
        int sizeOfLongestWord = wordList.stream().mapToInt(String::length).max().getAsInt();

        return paragraphs.stream().flatMap(paragraph -> paragraph.getChildren().stream())
                .filter(sentence -> sentence.getChildren().stream()
                        .anyMatch(lexeme -> lexeme.getChildren().stream()
                                .filter(word -> word.getType().equals(TextComponentType.WORD))
                                .anyMatch(leaf -> leaf.getChildren().size() == sizeOfLongestWord)))
                .collect(Collectors.toList());
    }

    public String findDuplicateNumber(TextComposite textComposite) {
        ArrayList<String> wordList = getListOfWord(textComposite);
        Map<String, Long> map = wordList.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        Map.Entry<String, Long> maxEntry = null;

        for (Map.Entry<String, Long> entry : map.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return String.valueOf(maxEntry);
    }

    private ArrayList<String> getListOfWord(TextComposite textComposite) {
        List<TextComponent> paragraphs = textComposite.getChildren();
        List<TextComponent> sentences;
        List<TextComponent> lexemes;
        List<TextComponent> words;
        ArrayList<String> wordList = new ArrayList<>();

        for (var paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            for (var sentence : sentences) {
                lexemes = sentence.getChildren();
                for (var lexeme : lexemes) {
                    words = lexeme.getChildren();
                    for (var word : words) {
                        if (word.getType().equals(TextComponentType.WORD)) {
                            wordList.add(word.toString().toLowerCase(Locale.ROOT));
                        }
                    }
                }
            }
        }

        return wordList;
    }
}
