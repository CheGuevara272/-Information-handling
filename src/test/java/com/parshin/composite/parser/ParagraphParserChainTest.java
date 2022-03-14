package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponentType;
import com.parshin.composite.entity.TextComposite;
import com.parshin.composite.reader.impl.CustomFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParagraphParserChainTest {
    public final String fileName = "data/text.txt";
    ParagraphParserChain parser;
    CustomFileReader customFileReader;
    TextComposite textComposite;

    @BeforeMethod
    public void setUp() {
        customFileReader = CustomFileReader.getInstance();
        parser = new ParagraphParserChain();
        textComposite = new TextComposite(TextComponentType.TEXT);
    }

    @Test
    public void testParse() {
        String text = customFileReader.readFile(fileName);
        parser.parse(textComposite, text);
        String actual = textComposite.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into 52 electronic  typesetting, remaining 0 essentially 9 unchanged. It was popularised in the  5 with the release of Letraset sheets containing  Lorem Ipsum passages, and more recently with desktop publishing software like Aldus pneumonoultramicroscopicsilicovolcanoconiosis. \n" +
                "\tPageMaker including versions of Lorem Ipsum. It is a long established fact that a reader will be distracted by the readable content of a  page when looking at its layout. The point of using 78  Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content  here), content here', making it look like readable English. \n" +
                "\tIt is a 1202 established fact that a reader will be of a page  when looking at its layout. \n" +
                "\tBye pneumonoultramicroscopicsilicovolcanoconiosis. \n";
        assertEquals(actual, expected);
    }
}