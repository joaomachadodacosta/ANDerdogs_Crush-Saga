package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Victal extends GameObject {

    public Victal(Position position) {
        super(position);
    }

    @Override
    public String getPicture() {
        return Faces.VICTAL.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.VICTAL.getValue();
    }
}
