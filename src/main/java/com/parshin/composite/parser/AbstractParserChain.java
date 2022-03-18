package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;

public abstract class AbstractParserChain {
    protected AbstractParserChain nextChain; //TODO как правильно сделать приватным и работать с ним через get и set
    protected AbstractParserChain() {}

    public abstract void parse(TextComponent component, String data);
}
