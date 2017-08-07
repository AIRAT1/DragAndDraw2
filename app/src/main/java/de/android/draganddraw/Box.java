package de.android.draganddraw;


import android.graphics.PointF;

public class Box {
    private PointF origin, current;

    public Box(PointF origin) {
        this.origin = origin;
        this.current = origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public PointF getOrigin() {
        return origin;
    }
}
