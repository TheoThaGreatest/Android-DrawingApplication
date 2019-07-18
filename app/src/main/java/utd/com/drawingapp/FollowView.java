package utd.com.drawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class FollowView extends View implements View.OnTouchListener {

    private Path drawPath;
    private Paint drawLine, canvasPaint;
    private int color;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private float posX;
    private float posY;
    private int BRUSH_SIZE = 20;
    public ArrayList <Path> paths = new ArrayList<>();
    public ArrayList <Path> undonePath = new ArrayList<>();
    public ArrayList <Paint> paints = new ArrayList<>();


    public FollowView(Context context)
    {
        this(context, null);
    }
    public FollowView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setup();
    }

    /***********************************************************************************************
     * Function: setup
     * Returns: nothing
     *
     * Description: this function initalizes the canvasPaint and the drawLine
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    private void setup() {

        drawLine = new Paint();
        drawPath = new Path();
        drawLine.setAntiAlias(true);
        drawLine.setStyle(Paint.Style.STROKE);
        drawLine.setStrokeJoin(Paint.Join.ROUND);
        drawLine.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
    /***********************************************************************************************
     * Function: onSizeChanged
     * Returns: nothing
     *
     * Description: this override method creates the canvas bitmap
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }
    /***********************************************************************************************
     * Function: onTouchEvent
     * Returns: nothing
     *
     * Description: this function removes the path
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posX = event.getX();
        posY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(posX, posY);
                undonePath.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(posX, posY);

                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawLine);
                paths.add(drawPath);
                paints.add(drawLine);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
    /***********************************************************************************************
     * Function: onDraw
     * Returns: nothing
     *
     * Description: this override function is called whenever invalidate() is used. it updates all
     *  the changes made to the drawLine function.
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    @Override
    public void onDraw(Canvas canvas)
    {
        drawLine.setColor(color);
        drawLine.setStrokeWidth(BRUSH_SIZE);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        //canvas.drawLine(posX, posY, 100, drawLine);
        canvas.drawPath(drawPath, drawLine);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    /***********************************************************************************************
     * Function: setBrushColor
     * Returns: nothing
     *
     * Description: this function is called when a new color is to be set
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void setBrushColor(int color)
    {
        this.color = color;
    }
    /***********************************************************************************************
     * Function: setBrushSize
     * Returns: nothing
     *
     * Description: this function is called when a new brushsize is to be set
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void setBrushSize(int brushSize)
    {
        this.BRUSH_SIZE = brushSize;
        invalidate();
    }
    /***********************************************************************************************
     * Function: undo
     * Returns: nothing
     *
     * Description: this function removes the path
     *
     * Written By Theophilus Ojukwu
     **********************************************************************************************/
    public void undo()
    {
        if(paths.size() > 0)
        {
            undonePath.add(paths.remove(paths.size() - 1));
            invalidate();
        }

    }

}