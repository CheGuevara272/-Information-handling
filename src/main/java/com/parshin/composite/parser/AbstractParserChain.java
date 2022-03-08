package com.parshin.composite.parser;

import com.parshin.composite.entity.TextComponent;

public abstract class AbstractParserChain {
    protected AbstractParserChain nextChain;
    public abstract void parse(TextComponent component, String data);
}
