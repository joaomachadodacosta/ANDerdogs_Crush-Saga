package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.GameObject;
import org.academiadecodigo.bootcamp.GameObjectFactory;
import org.academiadecodigo.bootcamp.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid  {

    private GameObject[][] faces;
    private Picture[][] picFaces;

    public Grid(int numcol, int numrow) {
        this.faces = new GameObject[numcol][numrow];
        this.picFaces = new Picture[numcol][numrow];
    }

    public GameObject[][] draw() {

        int x = 401;
        int y = 109;

        for (int i = 0; i < 7; i++) {

            x = 401;

            for (int j = 0; j < 7; j++) {
                Position pos = new Position(x, y);

                faces[i][j] = GameObjectFactory.createNewObj(pos);

                picFaces[i][j] = new Picture(x, y, faces[i][j].getPicture());
                picFaces[i][j].draw();

                x += 55;
            }

            y += 55;
        }

        return faces;
    }

    public void delete() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                picFaces[i][j].delete();
            }
        }

    }




}
