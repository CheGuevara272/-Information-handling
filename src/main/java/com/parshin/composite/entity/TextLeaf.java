package com.parshin.composite.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TextLeaf implements TextComponent{
    private static final Logger log = LogManager.getLogger();
    char character;
    TextComponentType type;

    public TextLeaf(char character, TextComponentType type) {
        this.character = character;
        this.type = type;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        log.log(Level.ERROR, "This method is not supported");
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean add(TextComponent component) {
        log.log(Level.ERROR, "This method is not supported");
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public boolean remove(TextComponent component) {
        log.log(Level.ERROR, "This method is not supported");
        throw new UnsupportedOperationException("This method is not supported");
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextLeaf textLeaf = (TextLeaf) o;

        if (character != textLeaf.character) return false;
        return type == textLeaf.type;
    }

    @Override
    public int hashCode() {
        int result = character;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
