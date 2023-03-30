package com.company;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();
        String chars = "~@#$%^&*()_+=-<>?";
        int height = 19;
        int width = 10;
        char[][] picture = new char[height][width];
        char[][] rotatedPicture = new char[width][height];

        buildPicture(rnd, chars, height, width, picture);
        printThePicture(picture);
        System.out.println();
        rotateThePicture(picture, rotatedPicture, width, height);
        printThePicture(rotatedPicture);
    }

    private static void rotateThePicture(char[][] picture, char[][] rotatedPicture, int width, int height) {
        for(int a = 0 ; a < width; a++){
            for(int j = height-1; j >= 0; j--){
                rotatedPicture[a][j] = picture[j][a];
            }
        }
    }

    private static void printThePicture(char[][] picture) {
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                System.out.print(picture[i][j] + "");
            }
            System.out.println();
        }
    }

    private static void buildPicture(Random rnd, String chars, int height, int width, char[][] picture) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i == 0 || j == 0) || (i == height -1 || j == width -1)){
                    picture[i][j] = '*';
                } else if (i < height /2) {
                    picture[i][j] = chars.charAt(rnd.nextInt(chars.length()));
                } else picture[i][j] = (char) ('a' + rnd.nextInt(26));
            }
        }
    }
}
