package org.academiadecodigo.bootcamp.Controls;

import org.academiadecodigo.bootcamp.UI.Board;
import org.academiadecodigo.bootcamp.UI.Grid;
import org.academiadecodigo.bootcamp.UI.Presentation;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controls implements KeyboardHandler {

    private Keyboard keyboard;
    private Presentation presentation;

    public void init() {

//        keyboard = new Keyboard(this);
//
//        KeyboardEvent spacePressed = new KeyboardEvent();
//        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
//        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED.KEY_PRESSED);
//
//        keyboard.addEventListener(spacePressed);
    }

    public void setPresentation(Presentation presentation) { this.presentation = presentation; }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

//        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
//
//            Board board = new Board();
//            board.draw();
//        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
