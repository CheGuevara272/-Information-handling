package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParserChain extends AbstractParserChain{
    private static final String PARAGRAPH_REGEX = "(^|\\n)(\\t|\\s{4})";

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String paragraph = matcher.group();
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);

            nextChain = new SentenceParserChain();
            nextChain.parse(paragraphComponent, paragraph);
        }
    }
}
