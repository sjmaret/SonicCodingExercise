package com.exam;

/**
 * Represents a part or service that can be sold.
 *
 * care should be taken to ensure that this class is immutable since it
 * is sent to other systems for processing that should not be able to
 * change it in any way.
 *
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Exams are us</p>
 * @author Joe Blow
 * @version 1.0
 */
public final class Item {

    private final Integer key;
    private final String name;
    private final float price;
    private final Boolean isTaxable;

    public Item(Integer key, String name, float price, Boolean isTaxable) {
        this.key = key;
        this.name = name;
        this.price = price;
        this.isTaxable = isTaxable;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Boolean isTaxable() {
        return isTaxable;
    }

}
