package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;
import com.parshin.composite.util.BitOperationCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserChain extends AbstractParserChain{
    private static final String LEXEME_REGEX = "\\s";
    private static final String BIT_OPERATION_REGEX = "([\\d+\\&\\|\\^\\(\\~<+\\>+\\)]){2,}";

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String lexeme = matcher.group();
            if (lexeme.matches(BIT_OPERATION_REGEX)) {
                BitOperationCalculator bitCalc = BitOperationCalculator.getInstance();
                String calculatedBitOperation;
                calculatedBitOperation = bitCalc.calculateBitExpression(lexeme);
                lexeme = calculatedBitOperation;
            }
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);

            nextChain = new LetterParserChain();
            nextChain.parse(lexemeComponent, lexeme);
        }
    }
}
