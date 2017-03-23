package com.example.chris.blatoph.TestsAppareilsPhoto;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by chris on 17/03/2017.
 */

public class PrevisualisationAppareilPhoto extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surface;
    private Camera appareilPhoto;

    public PrevisualisationAppareilPhoto(Context context, Camera camera) {
        super(context);
        appareilPhoto = camera;

        // SurfaceHolder.Callback permet de notifier de la création et la destruction de la surface
        surface = getHolder();
        surface.addCallback(this);
        surface.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // LA surface est crée, on informe l'appreil photo de l'endroit où afficher la prévisualisation
        try {
            appareilPhoto.setPreviewDisplay(holder);
            appareilPhoto.startPreview();
        } catch (IOException e) {
            Log.d("ERREUR", "Problème lors de la prévisualisation de l'appareil photo: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (surface.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            appareilPhoto.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            appareilPhoto.setPreviewDisplay(surface);
            appareilPhoto.startPreview();

        } catch (Exception e){
            Log.d("ERREUR", "Error starting camera preview: " + e.getMessage());
        }
    }
}
