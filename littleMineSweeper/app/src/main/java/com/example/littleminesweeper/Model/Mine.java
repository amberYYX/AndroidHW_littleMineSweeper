package com.example.littleminesweeper.Model;

import android.util.Log;


public class Mine {

    private static Mine instance = null;

    private Mine() {
    }

    public static Mine getInstance() {
        if (instance == null) {
            instance = new Mine();
        }

        return instance;
    }

    public static final short EMPTY = 0;//empty
    public static final short Near1Boom = 1;//surrounded by one boom
    public static final short Near2Boom = 2;//surrounded by 2 boom
    public static final short BOOM = 4;//boom
    public static final short MARK = 100;//boom


    private short[][] model = {
//            {CIRCLE, CIRCLE, CIRCLE,EMPTY,EMPTY},
//            {CIRCLE, BOOM, CIRCLE,EMPTY,EMPTY},
//            {CIRCLE, CIRCLE, CIRCLE,EMPTY,EMPTY},
//            {CIRCLE, CIRCLE, EMPTY,CIRCLE,CIRCLE},
//            {BOOM, CIRCLE, EMPTY,CIRCLE,BOOM},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
            {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY},
    };

    public void setField(int x, int y, short player) {
        model[x][y] = player;
    }

    public short getField(int x, int y) {
        return model[x][y];
    }



    public void resetModel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                model[i][j] = EMPTY;
            }
        }
    }
}


