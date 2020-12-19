package org.academiadecodigo.bootcamp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    private Clip clip;
    private URL soundURL;
    public Audio(String var1) {
        this.initClip(var1);
    }
    public void play(boolean var1) {
        if (var1) {
            this.clip.setFramePosition(0);
        }
        this.clip.start();
    }
    public void stop() {
        this.clip.stop();
    }
    public void close() {
        this.clip.close();
    }
    public int getLength() {
        return this.clip.getFrameLength();
    }
    public void loopIndef() {
        this.clip.setLoopPoints(0, (int)((double)this.getLength() * 0.94D));
        this.clip.loop(-1);
    }
    public void reOpen() {
        try {
            AudioInputStream var1 = AudioSystem.getAudioInputStream(this.soundURL);
            this.clip.open(var1);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException var3) {
            System.out.println(var3.getMessage());
        }
    }
    private void initClip(String var1) {
        this.soundURL = Audio.class.getResource(var1);
        try {
            if (this.soundURL == null) {
                var1 = var1.substring(1);
                File var3 = new File(var1);
                this.soundURL = var3.toURI().toURL();
            }
            AudioInputStream var2 = AudioSystem.getAudioInputStream(this.soundURL);
            this.clip = AudioSystem.getClip();
            this.clip.open(var2);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException var4) {
            System.out.println(var4.getMessage());
        }
    }
}