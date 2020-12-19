package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Joao extends GameObject {


    public Joao(Position position) {
        super(position);
    }

    @Override
    public String getPicture() {
        return Faces.JOAO.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.JOAO.getValue();
    }
}
