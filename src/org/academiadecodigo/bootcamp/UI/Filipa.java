package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Filipa extends GameObject {

    public Filipa(Position position) {
        super(position);
    }

    @Override
    public String getPicture() {
        return Faces.FILIPA.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.FILIPA.getValue();
    }
}
