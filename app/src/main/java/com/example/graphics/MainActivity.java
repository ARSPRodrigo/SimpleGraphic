package com.example.graphics;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    DrawingThread drawingThread;
    NewSurfaceView newSurfaceView;

    @RequiresApi( api = Build.VERSION_CODES.LOLLIPOP )
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        newSurfaceView = new NewSurfaceView( this, null, 0, 0 );
        drawingThread = new DrawingThread( newSurfaceView );
        setContentView( newSurfaceView );
        drawingThread.start();
    }


}
