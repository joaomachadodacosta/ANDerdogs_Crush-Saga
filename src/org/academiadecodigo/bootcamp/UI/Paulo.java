package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.Position;

public class Paulo extends GameObject {

    public Paulo(Position position) {
        super(position);
    }

    @Override
    public String getPicture() {
        return Faces.PAULO.getPicture();
    }

    @Override
    public int getValue() {
        return Faces.PAULO.getValue();
    }
}
