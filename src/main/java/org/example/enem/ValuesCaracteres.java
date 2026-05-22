package org.example.enem;

public enum ValuesCaracteres {

    MAX_CHARACTERS_TITLE(30),
    MAX_CHARACTERS_DESCRIPTION(100),
    MIN_CHARACTERS(1);

    private final int value;

    ValuesCaracteres(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
