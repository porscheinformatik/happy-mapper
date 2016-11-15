package at.porscheinformatik.antimapper;

public enum Hint
{

    /**
     * Tells the collection and map transformer and merger methods to keep null entries.
     */
    KEEP_NULL,

    /**
     * Tells the collection and map transformer to create unmodifiable collections and maps.
     */
    UNMODIFIABLE
}
