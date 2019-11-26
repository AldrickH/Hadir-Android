package com.aldricklevina.hadir;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class PBarAnimation extends Animation {
    private ProgressBar pBar;
    private float from, to;

    public PBarAnimation(ProgressBar _pbar, float from, float to) {
        this.pBar = _pbar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime;
        pBar.setProgress((int) value);
    }
}
