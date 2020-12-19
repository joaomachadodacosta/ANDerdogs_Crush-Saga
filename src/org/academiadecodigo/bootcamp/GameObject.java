package org.academiadecodigo.bootcamp;

public abstract class GameObject {

    private Position position;
    private String string;
    private int value;


    public GameObject(Position position) {
        this.position = position;

    }

    public Position getPosition() {
        return position;
    }

    public String getPicture() {
        return string;
    }

    public int getValue() { return value; }

    public void changePosition(Position pos) { this.position = pos; }
}
