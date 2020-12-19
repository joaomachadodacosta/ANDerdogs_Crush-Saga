package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.UI.Grid;
import org.academiadecodigo.bootcamp.UI.Presentation;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Game implements MouseHandler {

    private int score;
    private int plays;
    Text numPlays;
    private Text scoreText;
    private Timer timer;
    private Text timerText;
    private int counter;
    Presentation presentation;
    private Grid grid;
    private GameObject[][] faces;
    MouseEventType event = MouseEventType.MOUSE_CLICKED;
    double xMouse;
    double yMouse;
    int xSel;
    int ySel;
    int selectCounter = 0;
    Picture[] selectedImgs = new Picture[2];
    GameObject[] selectedObj = new GameObject[2];
    int[][] selectedPos = new int[2][2];
    int secondsLeft = 30;
    int minutesLeft = 1;
    Picture timesup;
    Picture exit;
    boolean hasTimer;
    boolean equal = false;
    boolean hasexited = false;
    boolean canSelect = false;

    public void Game(int score, int counter) {
        this.score = score;
        this.counter = counter;
        hasTimer = false;
    }

    public void init() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        presentation = new Presentation();
        presentation.appear(this);

    }

    public void start() {

        presentation.disappear();

        Picture background = new Picture(10, 10, "resources/background.png");
        background.draw();

        numPlays = new Text(623,534,"0");
        numPlays.grow(6, 10);
        numPlays.setColor(Color.WHITE);
        numPlays.draw();

        scoreText = new Text(490, 534, "0");
        scoreText.grow(6, 10);
        scoreText.setColor(Color.WHITE);
        scoreText.draw();

        timerText = new Text(758, 534, "1:30");
        timerText.grow(20, 10);
        timerText.setColor(Color.WHITE);
        timerText.draw();

        Mouse mouse = new Mouse(this);

        mouse.addEventListener(event);

    }

    public void startTimer() {

        hasTimer = true;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(secondsLeft == 0 && minutesLeft > 0) {
                    minutesLeft--;
                    secondsLeft = 59;
                }

                if(secondsLeft < 10) {
                    timerText.setText(String.valueOf(minutesLeft) + ":0" + String.valueOf(secondsLeft));
                } else  {
                    timerText.setText(String.valueOf(minutesLeft) + ":" + String.valueOf(secondsLeft));
                }

                if(secondsLeft == 0 && minutesLeft == 0) {
//                    System.out.println("time's up");
                    stopTimer();
                }

                secondsLeft--;

            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void stopTimer(){

        hasTimer = false;

        timer.cancel();

        grid.delete();

        for(Picture selectedImg : selectedImgs) {
            if(selectedImg != null) {
                selectedImg.delete();
            }

        }

        timesup = new Picture(401, 109, "resources/timesup.png");
        timesup.draw();

    }

    /*public void pop() {
        checkEqual();
    }*/

    public Three checkEqual(GameObject[][] faces) {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if(j + 2 < 7) {
                    if (faces[i][j].getClass().equals(faces[i][j + 1].getClass()) && faces[i][j + 1].getClass().equals(faces[i][j + 2].getClass())) {
                        score += faces[i][j].getValue()*3;
                        if(score > 9) {
                            scoreText.delete();
                            scoreText = new Text(490, 534, String.valueOf(score));
                            scoreText.grow(12, 10);
                            scoreText.setColor(Color.WHITE);
                            scoreText.draw();
                        } else {
                            scoreText.setText(String.valueOf(score));
                            scoreText.draw();
                        }
                        faces[i][j] = null;
                        faces[i][j+1] = null;
                        faces[i][j+2] = null;
//                        System.out.println("works line " + i + " " + j);
                        return new Three(j, i, "row");
                    }
                }

                if(i + 2 < 7) {
                    if (faces[i][j].getClass().equals(faces[i + 1][j].getClass()) && faces[i + 1][j].getClass().equals(faces[i + 2][j].getClass())) {
                        score += faces[i][j].getValue()*3;
                        if(score > 9) {
                            scoreText.delete();
                            scoreText = new Text(490, 534, String.valueOf(score));
                            scoreText.grow(12, 10);
                            scoreText.setColor(Color.WHITE);
                            scoreText.draw();
                        } else {
                            scoreText.setText(String.valueOf(score));
                            scoreText.draw();
                        }
                        faces[i][j] = null;
                        faces[i+1][j] = null;
                        faces[i+2][j] = null;
//                        System.out.println("works column " + i + " " + j);
                        return new Three(j, i, "column");
                    }
                }

            }
        }
        return null;
    }

    public void downMove() {
        for (int i = 0; i < faces.length; i++) {
            for (int j = 0; j < faces.length; j++) {
                if (faces[i][j]==null) {
                    int k=j;

                    while (k != 0) {
                        faces[i][k] = faces[i][k-1];
                        k--;
                    }
                    Position pos = new Position(i*55+401, 109);
                    faces[i][0] = GameObjectFactory.createNewObj(pos);

                }
            }
        }
    }

    public void checkMoves(GameObject[][] faces) {
        GameObject holder;
        for (int i = 0; i < faces.length; i++) {
            for (int j = 0; j < faces.length; j++) {
                if(i < faces.length - 1) {
                    holder = faces[i][j];
                    faces[i][j] = faces[i + 1][j];
                    faces[i + 1][j] = holder;
                    Three check = checkEqual2(faces);
                    holder = faces[i][j];
                    faces[i][j] = faces[i + 1][j];
                    faces[i + 1][j] = holder;
                    if(check != null){
                        equal = true;
                    }
                }
                if(j < faces.length - 1) {
                    holder = faces[i][j];
                    faces[i][j] = faces[i][j + 1];
                    faces[i][j + 1] = holder;
                    checkEqual(faces);
                    Three check = checkEqual2(faces);
                    holder = faces[i][j];
                    faces[i][j] = faces[i][j + 1];
                    faces[i][j + 1] = holder;
                    if(check != null){
                        equal = true;
                    }
                }
            }
        }
    }

    public Three checkEqual2(GameObject[][] faces) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(j + 2 < 7) {
                    if (faces[i][j].getClass().equals(faces[i][j + 1].getClass()) && faces[i][j + 1].getClass().equals(faces[i][j + 2].getClass())) {
                        return new Three(j, i, "row");
                    }
                }
                if(i + 2 < 7) {
                    if (faces[i][j].getClass().equals(faces[i + 1][j].getClass()) && faces[i + 1][j].getClass().equals(faces[i + 2][j].getClass())) {
                        return new Three(j, i, "column");
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        xMouse = mouseEvent.getX();
        yMouse = mouseEvent.getY();

//        System.out.println(xMouse);
//        System.out.println(yMouse);

        if(xMouse > 765 && xMouse < 802 && yMouse > 70 && yMouse < 107) { //X button

            hasexited = true;
            if(hasTimer) {
                stopTimer();
            }

            exit = new Picture(10, 10, "resources/exit.png");
            exit.draw();

        } else if(xMouse > 229 && xMouse < 427 && yMouse > 297 && yMouse < 362 && hasexited) { //start again
            exit.delete();
            startGrid();
            hasexited = false;

        } else if(xMouse > 454 && xMouse < 651 && yMouse > 296 && yMouse < 363 && hasexited) { //quit
            System.exit(0);
        } else if(xMouse > 59 && xMouse < 199 && yMouse > 254 && yMouse < 302) { // go fetch
            startGrid();
        }  else if(xMouse > 401 && xMouse < 786 && yMouse > 138 && yMouse < 523) { //select face in grid
            select();
        }

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void startGrid() {

        if(hasTimer) {
            stopTimer();
        }

        secondsLeft = 30;
        minutesLeft = 1;

        score = 0;


        plays = 0;

        startTimer();

        if(timesup != null) {
            timesup.delete();
        }
        score = 0;
        scoreText.delete();
        scoreText = new Text(490, 534, String.valueOf(score));
        scoreText.grow(6, 10);
        scoreText.setColor(Color.WHITE);
        scoreText.draw();

        plays = 0;
        numPlays.delete();
        numPlays = new Text(623, 534, String.valueOf(plays));
        numPlays.grow(6, 10);
        numPlays.setColor(Color.WHITE);
        numPlays.draw();

        grid = new Grid(7,7);

        faces = grid.draw();

        canSelect = true;

        Three check = checkEqual(faces);

        downMove();

        while(check != null) {
            //imgs dissapear
            //score increases
            //faces move down
            //new faces are generated

            int xPos = check.column * 55;
            int yPos = check.row * 55;

            downMove();

            grid.delete();

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    Picture pic = new Picture(i*55+401, j*55+109, faces[i][j].getPicture());
                    pic.draw();
                }
            }

            check = checkEqual(faces);

        }

//        while(!equal) {
//            checkMoves(faces);
//            System.out.println("entrou");
//            if(!equal){
//                Picture pic = new Picture(401, 109, "resources/Changeboard.png");
//                pic.draw();
//                try
//                {
//                    TimeUnit.SECONDS.sleep(5);
//                }
//                catch(InterruptedException ex)
//                {
//                    Thread.currentThread().interrupt();
//                }
//                pic.delete();
//                faces = grid.draw();
//            }
//        }
//        equal = false;

        score = 0;
        scoreText.setText(String.valueOf(score));
        scoreText.draw();

    }

    public class Three {

        private int row;
        private int column;
        private String typeOfThree; //accepts row or column;

        private Three(int row, int column, String typeOfThree) {
            this.row = row;
            this.column = column;
            this.typeOfThree = typeOfThree;
        }
    }

    private void select() {

        if(selectCounter < 2 && canSelect) {
            xSel = 0;
            ySel = 0;

            if (xMouse > 401 && xMouse <= 456) {
                xSel = 401;
            } else if (xMouse > 456 && xMouse <= 511) {
                xSel = 456;
            } else if (xMouse > 511 && xMouse <= 566) {
                xSel = 511;
            } else if (xMouse > 566 && xMouse <= 621) {
                xSel = 566;
            } else if (xMouse > 621 && xMouse <= 676) {
                xSel = 621;
            } else if (xMouse > 676 && xMouse <= 731) {
                xSel = 676;
            } else if (xMouse > 731 && xMouse <= 786) {
                xSel = 731;
            }

            if (yMouse > 138 && yMouse <= 193) {  //1
                ySel = 109;
            } else if (yMouse > 193 && yMouse <= 248) { //2
                ySel = 164;
            } else if (yMouse > 248 && yMouse <= 303) { //3
                ySel = 219;
            } else if (yMouse > 303 && yMouse <= 358) { //4
                ySel = 274;
            } else if (yMouse > 358 && yMouse <= 413) { //5
                ySel = 329;
            } else if (yMouse > 413 && yMouse <= 468) { //6
                ySel = 384;
            } else if (yMouse > 468 && yMouse <= 523) { //7
                ySel = 439;
            }

            selectedImgs[selectCounter] = new Picture(xSel, ySel, "resources/selected.png");
            selectedImgs[selectCounter].draw();

            selectedObj[selectCounter] = faces[(xSel-401)/55][(ySel-109)/55];
            selectedPos[selectCounter][1] = (ySel-109)/55;
            selectedPos[selectCounter][0] = (xSel-401)/55;

            xMouse = 0;
            yMouse = 0;
            xSel = 0;
            ySel = 0;

            selectCounter++;

            if(selectCounter == 2) {

                if((selectedPos[0][0] != selectedPos[1][0] && selectedPos[0][1] != selectedPos[1][1])){
//                    System.out.println("Can't switch.");

                    for(Picture selectedImg : selectedImgs) {
                        selectedImg.delete();
                    }

                    selectCounter = 0;

                    return;
                }

                faces[selectedPos[0][0]][selectedPos[0][1]] = selectedObj[1];
                faces[selectedPos[1][0]][selectedPos[1][1]] = selectedObj[0];


                Three check = checkEqual(faces);

                for(Picture selectedImg : selectedImgs) {
                    selectedImg.delete();
                }

                if(check != null) {
                    plays++;
                    if(plays > 9) {
                        numPlays.delete();
                        numPlays = new Text(623, 534, String.valueOf(plays));
                        numPlays.grow(12, 10);
                        numPlays.setColor(Color.WHITE);
                        numPlays.draw();
                    } else {
                        numPlays.setText(String.valueOf(plays));
                        numPlays.draw();
                    }

                    while(check != null) {

                        downMove();

                        grid.delete();

                        for (int i = 0; i < 7; i++) {
                            for (int j = 0; j < 7; j++) {
                                Picture pic = new Picture(i * 55 + 401, j * 55 + 109, faces[i][j].getPicture());
                                pic.draw();
                            }

                        }

                        check = checkEqual(faces);

                    }
                } else  {

                    faces[selectedPos[0][0]][selectedPos[0][1]] = selectedObj[0];
                    faces[selectedPos[1][0]][selectedPos[1][1]] = selectedObj[1];

                }

                selectCounter = 0;

            }

        } else {

//            System.out.println("Max selected faces!");
        }


    }



}
