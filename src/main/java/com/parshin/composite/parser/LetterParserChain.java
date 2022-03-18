package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextLeaf;

public class LetterParserChain extends AbstractParserChain{
    private static final char APOSTROPHE_REGEX = '\'';

    @Override
    public void parse(TextComponent component, String data) {
        char[] chars = data.toCharArray();
        for (var character : chars) {
            if (character == APOSTROPHE_REGEX) {
                TextLeaf punctuationMark = new TextLeaf(character, TextComponentType.PUNCTUATION_MARK);
                component.add(punctuationMark);
            } else {
                TextLeaf letter = new TextLeaf(character, TextComponentType.LETTER);
                component.add(letter);
            }
        }
    }
}
