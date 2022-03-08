package com.parshin.composite.entity;

import java.util.List;

public interface TextComponent {
    TextComponentType getType();
    List<TextComponent> getChild();

    boolean add(TextComponent component);
    boolean remove(TextComponent component);
}
