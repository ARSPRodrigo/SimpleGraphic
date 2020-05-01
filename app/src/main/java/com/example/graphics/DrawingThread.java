package com.example.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class DrawingThread extends Thread
{
    private static final String TAG = DrawingThread.class.getCanonicalName();
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Rect rectangle;
    private Paint paint;
    private int displayHeight;
    private int displayWidth;

    @RequiresApi( api = Build.VERSION_CODES.JELLY_BEAN_MR1 )
    public DrawingThread( SurfaceView surfaceView )
    {
        this.surfaceView = surfaceView;
        surfaceHolder = ( ( NewSurfaceView ) surfaceView ).getSurfaceHolder();

        setSize( surfaceView );
        paint = new Paint();
    }

    int prewColor = 0;
    int currentColor = 0;
    int[] colors = new int[] { Color.BLACK, Color.GRAY, Color.MAGENTA,
            Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE };

    public void run()
    {

        while( true )
        {
            if( surfaceHolder != null )
            {
                Canvas c = surfaceHolder.lockCanvas();
                if( c != null )
                {
                    setRectangle();
                    paint.setColor( colors[prewColor] );
                    c.drawColor( colors[currentColor] );
                    c.drawRect( rectangle, paint );
                    surfaceHolder.unlockCanvasAndPost( c );
                    prewColor = currentColor;
                    currentColor = ( ++currentColor ) % colors.length;
                    Log.d( TAG, "Color:" + currentColor );
                }
                else
                {
                    Log.d( TAG, "Null Canvas" );
                }

                try
                {
                    Thread.sleep( 1000 );
                }
                catch( InterruptedException e )
                {
                    e.printStackTrace();
                }

            }
            else
            {
                Log.d( TAG, "Null surfaceHolder" );
            }
            if( ( ( NewSurfaceView ) surfaceView ).isSurfaceDestroyed() )
            {
                Log.d( TAG, "Stopping Thread" );
                break;
            }
        }
    }

    private void setRectangle()
    {
        int sideLength = 200;
        Random r = new Random();
        int left = r.nextInt( displayWidth - sideLength );
        int top = r.nextInt( displayHeight - sideLength );
        int right = left + 200;
        int bottom = top + 200;
        rectangle = new Rect( left, top, right, bottom );
    }

    @RequiresApi( api = Build.VERSION_CODES.JELLY_BEAN_MR1 )
    private void setSize( SurfaceView surfaceView )
    {
        Display display = surfaceView.getDisplay();
        Point size = new Point();
        display.getSize( size );
        displayHeight = size.y;
        displayWidth = size.x;
    }

}
