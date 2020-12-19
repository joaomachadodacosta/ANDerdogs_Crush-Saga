package org.academiadecodigo.bootcamp.UI;

import org.academiadecodigo.bootcamp.Game;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Presentation implements MouseHandler {

    MouseEventType event = MouseEventType.MOUSE_CLICKED;
    Game game;
    double xMouse;
    double yMouse;
    Mouse mouse;
    Picture background;
    Picture play;

    public Presentation() {
        mouse = new Mouse(this);
    }

    public void appear(Game game) {

        this.game = game;

        background = new Picture(10,10,"resources/Presentation.png");
        background.draw();

        play = new Picture(505,369, "resources/dog-start-button.png");
        play.draw();

        mouse.addEventListener(event);
    }

    public void disappear() {
        background.delete();
        play.delete();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        xMouse = mouseEvent.getX();
        yMouse = mouseEvent.getY();

        evaluatePosition(game);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void evaluatePosition(Game game) {

        if ((xMouse > 502 && xMouse < 753) && (yMouse > 455 && yMouse < 543)) {

            mouse.removeEventListener(event);

            game.start();


        }
    }
}
