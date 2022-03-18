package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;

import java.util.List;
import java.util.stream.Stream;

public class ParagraphParserChain extends AbstractParserChain{
    private static final String PARAGRAPH_REGEX = "(^|\\n)(\\t|\\s{4})";

    public ParagraphParserChain() {
        nextChain = new SentenceParserChain();
    }

    @Override
    public void parse(TextComponent component, String data) {
        List<String> paragraphsList = Stream.of(data.split(PARAGRAPH_REGEX))
                .filter(p -> !p.isEmpty())
                .toList();

        for (var paragraph : paragraphsList) {
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);

            nextChain.parse(paragraphComponent, paragraph);
        }
    }
}
