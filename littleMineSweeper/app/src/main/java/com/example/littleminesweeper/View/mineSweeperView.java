package com.example.littleminesweeper.View;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.littleminesweeper.MainActivity;
import com.example.littleminesweeper.Model.Mine;
import com.example.littleminesweeper.R;

import java.io.InputStream;

public class mineSweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintText;
    private Paint paintText1;
    private Paint paintText2;
    private boolean mode;
    public short Flag = 01;
    private Bitmap bmp_flag;
    private Bitmap bmp_boom;

    public mineSweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.parseColor("#5f8c76"));
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        paintText = new Paint();
        paintText.setTextSize(60);
        paintText.setColor(Color.RED);

        paintText1 = new Paint();
        paintText1.setTextSize(60);
        paintText1.setColor(Color.parseColor("#e6e597"));

        paintText2 = new Paint();
        paintText2.setTextSize(60);
        paintText2.setColor(Color.parseColor("#e1a8a2"));

        bmp_flag = BitmapFactory.decodeResource(this.getResources(), R.drawable.flag);
        bmp_flag = scaleBitmap(bmp_flag, 110, 110);

        bmp_boom = BitmapFactory.decodeResource(this.getResources(), R.drawable.redboom);
        bmp_boom = scaleBitmap(bmp_boom, 150, 150);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(),
                paintBg);

        drawGameGrid(canvas);
        drawPlayers(canvas);

//        // Z-order is important!
//        canvas.drawText("3", 0, getHeight()/3, paintText);
//        canvas.drawText("1", getWidth() *2 / 3, getHeight() * 3 /3, paintText);


    }


    private void drawGameGrid(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        int lineNumber = 5;

        // draw horizontal lines
        for (int i = 0; i < lineNumber; i++) {
            canvas.drawLine(0, i * getHeight() / 5, getWidth(),
                    i * getHeight() / 5, paintLine);
        }


        // draw vertical lines
        for (int y = 0; y < lineNumber; y++) {
            canvas.drawLine(y * getWidth() / 5, 0, y * getWidth() / 5, getHeight(),
                    paintLine);
        }

    }

    private void drawPlayers(Canvas canvas) {


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (Mine.getInstance().getField(i, j) == Mine.Near1Boom) {

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 5 + getWidth() / 12;
                    float centerY = j * getHeight() / 5 + getHeight() / 8;
                    int radius = getHeight() / 10 - 5;

                    canvas.drawText("1", centerX, centerY, paintText1);
//                    canvas.drawCircle(centerX, centerY, radius, paintLine);

                }

                if (Mine.getInstance().getField(i, j) == Mine.Near2Boom) {

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 5 + getWidth() / 12;
                    float centerY = j * getHeight() / 5 + getHeight() / 8;

                    canvas.drawText("2", centerX, centerY, paintText2);
                }
                if (Mine.getInstance().getField(i, j) == Mine.BOOM) {
                    float centerX = i * getWidth() / 5 + getWidth() / 35;
                    float centerY = j * getHeight() / 5 + getHeight() / 30;

//                    canvas.drawText("BOOM", i * getWidth() / 5 + getWidth() / 10 - getWidth() / 12, j * getHeight() / 5 + getHeight() / 10, paintText);
                    canvas.drawBitmap(bmp_boom,centerX,centerY,paintLine);

                    // Game Over!
                    ((MainActivity)getContext()).setMessage();

//                    resetGame();
                }
                else if(Mine.getInstance().getField(i,j) == Mine.MARK){
                    float centerX = i * getWidth() / 5 + getWidth() / 22;
                    float centerY = j * getHeight() / 5 + getHeight() / 20;
                    int radius = getHeight() / 10  ;

//                    canvas.drawCircle(centerX, centerY, radius, paintLine);

                    canvas.drawBitmap(bmp_flag,centerX,centerY,paintLine);

                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int pX = (int) event.getX();
            int pY = (int) event.getY();
            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);



            if (Mine.getInstance().getField(tX, tY) == Mine.EMPTY) {

                if (Flag == 01) {

                    //(0,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > 0 && pY < getWidth() / 5) {
//                    Mine.getInstance().setField(tX,tY,Mine.BOOM);
                        Mine.getInstance().setField(0, 0, Mine.Near1Boom);
                    }
                    //(0,1)
                    if ((pX > getWidth() / 5 && pX < 2 * (getWidth() / 5)) && pY > 0 && pY < getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(0,2)
                    if ((pX > 2 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 0 && pY < getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(0,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 0 && pY < getWidth() / 5) {
//                    Mine.getInstance().setField(tX,tY-1,Mine.Near1Boom); // 0,2
//                    Mine.getInstance().setField(tX+1,tY-1,Mine.Near2Boom); // 1,2
//                    Mine.getInstance().setField(tX+1,tY,Mine.Near2Boom); // 1,3
//                    Mine.getInstance().setField(tX+1,tY+1,Mine.Near2Boom); // 1,4
                        Mine.getInstance().setField(3, 0, Mine.EMPTY); // 0,2
                        Mine.getInstance().setField(2, 0, Mine.Near1Boom); // 0,2
                        Mine.getInstance().setField(2, 1, Mine.Near2Boom); // 1,2
                        Mine.getInstance().setField(3, 1, Mine.Near1Boom); // 1,3
                        Mine.getInstance().setField(4, 1, Mine.Near1Boom); // 1,4
                    }
                    //(0,4)
                    if ((pX > 4 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 0 && pY < getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.EMPTY);
                        Mine.getInstance().setField(3, 0, Mine.EMPTY); // 0,2
                        Mine.getInstance().setField(2, 0, Mine.Near1Boom); // 0,2
                        Mine.getInstance().setField(2, 1, Mine.Near2Boom); // 1,2
                        Mine.getInstance().setField(3, 1, Mine.Near1Boom); // 1,3
                        Mine.getInstance().setField(4, 1, Mine.Near1Boom); // 1,4
                    }


                    //(1,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(1,1)
                    if ((pX > getWidth() / 5 && pX < 2 * getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.BOOM);
                    }
                    //(1,2)
                    if ((pX > 2 * getWidth() / 5 && pX < 3 * getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near2Boom);
                    }
                    //(1,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 4 * getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(1,4)
                    if ((pX > 4 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }

                    //(2,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(2,1)
                    if ((pX > getWidth() / 5 && pX < 2 * getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(2,2)
                    if ((pX > 2 * getWidth() / 5 && pX < 3 * getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near2Boom);
                    }
                    //(2,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 4 * getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.BOOM);
                    }
                    //(2,4)
                    if ((pX > 4 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }

                    //(3,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > 3 * getWidth() / 5 && pY < 4 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(3,1)
                    if ((pX > getWidth() / 5 && pX < 2 * getWidth() / 5) && pY > 3 * getWidth() / 5 && pY < 4 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(3,2)
                    if ((pX > 2 * getWidth() / 5 && pX < 3 * getWidth() / 5) && pY > 3 * getWidth() / 5 && pY < 4 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(3,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 4 * getWidth() / 5) && pY > 3 * getWidth() / 5 && pY < 4 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(3,4)
                    if ((pX > 4 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 3 * getWidth() / 5 && pY < 4 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }

                    //(4,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.BOOM);
                    }
                    //(4,1)
                    if ((pX > getWidth() / 5 && pX < 2 * getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.Near1Boom);
                    }
                    //(4,2)
                    if ((pX > 2 * getWidth() / 5 && pX < 3 * getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.EMPTY);
                        Mine.getInstance().setField(1, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(2, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(3, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(4, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(1, 4, Mine.Near1Boom);
                    }
                    //(4,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 4 * getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.EMPTY);
                        Mine.getInstance().setField(1, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(2, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(3, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(4, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(1, 4, Mine.Near1Boom);
                    }
                    //(4,4)
                    if ((pX > 4 * getWidth() / 5 && pX < 5 * getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        Mine.getInstance().setField(tX, tY, Mine.EMPTY);
                        Mine.getInstance().setField(1, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(2, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(3, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(4, 3, Mine.Near1Boom);
                        Mine.getInstance().setField(1, 4, Mine.Near1Boom);
                    }


                    invalidate();

                } else {

                    System.out.print("place a flag MODE.");
                    //1,1
                    if ((pX > getWidth() / 5 && pX < 2 * getWidth() / 5) && pY > getWidth() / 5 && pY < 2 * getWidth() / 5){
                        if (Mine.getInstance().getField(tX, tY) == Mine.EMPTY) {
                            Mine.getInstance().setField(tX, tY, Mine.MARK);
                            invalidate();
                        }
                    }
                    //(2,3)
                    if ((pX > 3 * getWidth() / 5 && pX < 4 * getWidth() / 5) && pY > 2 * getWidth() / 5 && pY < 3 * getWidth() / 5) {
                        if (Mine.getInstance().getField(tX, tY) == Mine.EMPTY) {
                            Mine.getInstance().setField(tX, tY, Mine.MARK);
                            invalidate();
                        }
                    }
                    //(4,0)
                    if ((pX > 0 && pX < getWidth() / 5) && pY > 4 * getWidth() / 5 && pY < 5 * getWidth() / 5) {
                        if (Mine.getInstance().getField(tX, tY) == Mine.EMPTY) {
                            Mine.getInstance().setField(tX, tY, Mine.MARK);
                            invalidate();
                        }
                    }
                    else {
                        if (Mine.getInstance().getField(tX, tY) == Mine.EMPTY) {
                            Mine.getInstance().setField(tX, tY, Mine.MARK);

                            invalidate();
                            //Game Over!
                            ((MainActivity) getContext()).setMessage();
                        }
                    }

                }

            }
        }


        return true;
    }

    public void resetGame() {
        Mine.getInstance().resetModel();
        Flag = 01;
        invalidate();
    }

    public void drawFlag(int mark) {
        if (mark == 100){
            Flag = 02; //NOW YOU CAN PLACE A FLAG.
        }
        else
        {
            Flag = 01;
        }

    }

    public Bitmap scaleBitmap(Bitmap bmp, int dreamWidth, int dreamHeight){
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        // how big I want
        int newWidth = dreamWidth;
        int newHeight = dreamHeight;
        // calculate rate
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // matrix pare
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // new map
        Bitmap newbmp = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix,
                true);

        return newbmp;
    }
}





