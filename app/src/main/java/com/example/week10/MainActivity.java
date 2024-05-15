package com.example.week10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setContentView(new MyGraphicView(this));
        Log.d("minseok", "onCreate called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("Menu", "onCreateOptionsMenu called");
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    static final int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.line) {
            curShape = LINE;
        } else if(item.getItemId() == R.id.circle) {
            curShape = CIRCLE;
        } else if(item.getItemId() == R.id.rect) {
            curShape = RECT;
        }
//        switch(item.getItemId()) {
//            case R.id.line:
//                curShape = LINE;
//                break;
//            case R.id.circle:
//                curShape = CIRCLE;
//                break;
//            case R.id.rect:
//                curShape = RECT;
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(@NonNull MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("hwang", "move:x=" + event.getX() + "  y=" + event.getY()+ "  RawX="+event.getRawX()+" RawY="+event.getRawY());
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);

            switch (curShape) {
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;

                case RECT:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect,paint);
                    break;
            }

        }
    }
}