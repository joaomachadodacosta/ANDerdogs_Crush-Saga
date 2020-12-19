package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Controls.Controls;
import org.academiadecodigo.bootcamp.UI.Presentation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {

//    private Audio audio = new Audio("/resources/background.wav");

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Audio audio = new Audio("/resources/background.wav");
        audio.play(true);
        audio.loopIndef();

        Game game = new Game();

        game.init();


    }
}

