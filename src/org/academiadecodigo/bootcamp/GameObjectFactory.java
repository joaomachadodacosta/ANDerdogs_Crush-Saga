package org.academiadecodigo.bootcamp;


import org.academiadecodigo.bootcamp.UI.*;

public class GameObjectFactory {

    public static GameObject createNewObj(Position position) {

        int index = (int) Math.floor(Math.random()* Faces.values().length);

        if (Faces.values()[index] == Faces.FILIPA) {
            return new Filipa(position);

        } else if (Faces.values()[index] == Faces.JOAO) {
            return new Joao(position);

        } else if (Faces.values()[index] == Faces.PAULO) {
            return new Paulo(position);

        } else if (Faces.values()[index] == Faces.NURIA) {
            return new Nuria(position);

        } else if (Faces.values()[index] == Faces.VICTAL) {
            return new Victal(position);

        } else {
            return new Anderdog(position);
        }
    }

    public static GameObject createNewObj(Position position, String string) {

        int index = (int) Math.floor(Math.random()* Faces.values().length);

        if (Faces.values()[index] == Faces.FILIPA) {
            return new Filipa(position);

        } else if (Faces.values()[index] == Faces.JOAO) {
            return new Joao(position);

        } else if (Faces.values()[index] == Faces.PAULO) {
            return new Paulo(position);

        } else if (Faces.values()[index] == Faces.NURIA) {
            return new Nuria(position);

        } else if (Faces.values()[index] == Faces.VICTAL) {
            return new Victal(position);

        } else {
            return new Anderdog(position);
        }
    }
}
