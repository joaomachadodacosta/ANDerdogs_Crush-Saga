package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Nuria extends GameObject {

    public Nuria(Position position) {
        super(position);
    }

    @Override
    public String getPicture() {
        return Faces.NURIA.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.NURIA.getValue();
    }
}

