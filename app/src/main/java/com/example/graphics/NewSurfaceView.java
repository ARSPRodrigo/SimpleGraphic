package com.example.graphics;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

public class NewSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    public static String TAG = NewSurfaceView.class.getCanonicalName();

    private boolean isSurfaceDestroyed = false;
    private SurfaceHolder surfaceHolder;
    private Context context;

    @RequiresApi( api = Build.VERSION_CODES.LOLLIPOP )
    public NewSurfaceView( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes )
    {
        super( context, attrs, defStyleAttr, defStyleRes );
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback( this );
    }

    public boolean isSurfaceDestroyed()
    {
        return isSurfaceDestroyed;
    }

    public SurfaceHolder getSurfaceHolder()
    {
        return surfaceHolder;
    }

    public Display getDisplay()
    {
        WindowManager windowManager = ( WindowManager ) context.getSystemService( Context.WINDOW_SERVICE );
        return windowManager.getDefaultDisplay();
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder )
    {
        Log.d( TAG, "surfaceCreated" );
    }

    @Override
    public void surfaceChanged( SurfaceHolder holder, int format, int width, int height )
    {
        Log.d( TAG, String.format( "surfaceChanged format:%d width:%d height:%d", format, width, height ) );
    }

    @Override
    public void surfaceDestroyed( SurfaceHolder holder )
    {
        Log.d( TAG, "surfaceDestroyed" );
        isSurfaceDestroyed = true;
    }


}
