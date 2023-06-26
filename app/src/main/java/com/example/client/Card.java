package com.example.client;

public class Card {
    private int id,
                color,
                shape,
                fill,
                count;

    public Card(int id, int color, int shape, int fill, int count) {
        this.id = id;
        this.color = color;
        this.shape = shape;
        this.fill = fill;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
