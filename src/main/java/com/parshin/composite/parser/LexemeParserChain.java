package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;
import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;
import com.parshin.composite.util.BitOperationCalculator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserChain extends AbstractParserChain{
    private static final String LEXEME_REGEX = "\\s";
    private static final String BIT_OPERATION_REGEX = "([\\d+\\&\\|\\^\\(\\~<+\\>+\\)]){2,}";

    public LexemeParserChain() {
        nextChain = new WordParserChain();
    }

    @Override
    public void parse(TextComponent component, String data) {
        List<String> lexemeList = List.of(data.split(LEXEME_REGEX));

        for (var lexeme : lexemeList) {
            if (lexeme.matches(BIT_OPERATION_REGEX)) {
                BitOperationCalculator bitCalc = BitOperationCalculator.getInstance();
                String calculatedBitOperation;
                calculatedBitOperation = bitCalc.calculateBitExpression(lexeme);
                lexeme = calculatedBitOperation;
            }
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);

            nextChain.parse(lexemeComponent, lexeme);
        }
    }
}
