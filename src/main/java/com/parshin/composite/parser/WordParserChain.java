package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;
import com.parshin.composite.entity.TextLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParserChain extends AbstractParserChain{
    private static final String WORD_AND_PUNCTUATION_REGEX = "[\\wа-яА-ЯёЁ']+|[\\p{Punct}\\u2026]";
    private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ']+";
    private static final String PUNCTUATION_REGEX = "[\\p{Punct}|\\u2026]";

    public WordParserChain() {
        nextChain = new LetterParserChain();
    }

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(WORD_AND_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String wordOrPunctuation = matcher.group();
            if (wordOrPunctuation.matches(WORD_REGEX)) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.add(wordComponent);

                nextChain.parse(wordComponent, wordOrPunctuation);
            } else if (wordOrPunctuation.matches(PUNCTUATION_REGEX)) {
                TextLeaf punctuationLeaf = new TextLeaf(wordOrPunctuation.charAt(0), TextComponentType.PUNCTUATION_MARK);
                component.add(punctuationLeaf);
            }
        }
    }
}
