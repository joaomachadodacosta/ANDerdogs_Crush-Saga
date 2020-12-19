package org.academiadecodigo.bootcamp.UI;

public enum Faces {
    FILIPA(1,"resources/filipa.png"),
    JOAO(1,"resources/joao.png"),
    PAULO(1,"resources/paulo.png"),
    NURIA(1,"resources/nuria.png"),
    VICTAL(1,"resources/victal.png"),
    ANDERDOG(3,"resources/dog.png");


    private int value;
    private String picture;

    Faces(int value,String picture){
        this.value = value;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public int getValue() {
        return value;
    }
}
