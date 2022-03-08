package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParserChain extends AbstractParserChain{
    private static final String SENTENCE_REGEX = "([A-Z]|[А-ЯЁ]).+?(\\.|\\!|\\?|\\u2026)";

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);

            nextChain = new LexemeParserChain();
            nextChain.parse(sentenceComponent, sentence);
        }
    }
}
