package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Anderdog extends GameObject {

    public Anderdog(Position position) {
        super(position);
    }
    @Override
    public String getPicture() {
        return Faces.ANDERDOG.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.ANDERDOG.getValue();
    }
}
