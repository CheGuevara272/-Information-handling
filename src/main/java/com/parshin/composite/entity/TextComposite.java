package com.parshin.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final TextComponentType type;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type){
        this.type = type;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChild() {
        return new ArrayList<>(components);
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public String toString() {
        StringBuilder component = new StringBuilder();
        component.append(type.getPrefix());
        for (TextComponent part : components) {
            component.append(part.toString());
        }
        component.append(type.getPostfix());
        return component.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        if (type != that.type) return false;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }
}
