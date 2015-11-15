package gteeny.invisiblecow;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameControls implements OnTouchListener {
    int width;
    int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    private int radius = 25;
    public int initx = 550;
    public int inity = 1100;
    public int minx = initx-radius;
    private int maxx = initx+radius;
    private int miny = inity-radius;
    private int maxy = inity+radius;
    public Point _touchingPoint = new Point(initx,inity);
    public Point _pointerPosition = new Point(220,150);
    private Boolean _dragging = false;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        update(event);
        return true;
    }

    private MotionEvent lastEvent;
    public void update(MotionEvent event){

        if (event == null && lastEvent == null)
        {
            return;
        }else if(event == null && lastEvent != null){
            event = lastEvent;
        }else{
            lastEvent = event;
        }
        //drag drop
        if ( event.getAction() == MotionEvent.ACTION_DOWN ){
            _dragging = true;
        }else if ( event.getAction() == MotionEvent.ACTION_UP){
            _dragging = false;
        }

        if ( _dragging ){
            // get the pos
            _touchingPoint.x = (int)event.getX();
            _touchingPoint.y = (int)event.getY();

            // bound to a box
            _touchingPoint.x = Math.min(maxx,
                    Math.max(minx, _touchingPoint.x));
            _touchingPoint.y = Math.min(maxy,
                    Math.max(miny, _touchingPoint.y));

            //get the angle
            double angle = Math.atan2(_touchingPoint.y - inity,_touchingPoint.x - initx)/(Math.PI/180);

            // Move the beetle in proportion to how far
            // the joystick is dragged from its center
            _pointerPosition.y += Math.sin(angle * (Math.PI / 180))*(_touchingPoint.x/70);
            _pointerPosition.x += Math.cos(angle * (Math.PI / 180))*(_touchingPoint.x/70);




            //make the pointer go thru
            if ( _pointerPosition.x > width)
            {
                _pointerPosition.x= 0;
            }

            if ( _pointerPosition.x < 0){
                _pointerPosition.x = width;
            }

            if (_pointerPosition.y > height ){
                _pointerPosition.y = 0;
            }
            if (_pointerPosition.y < 0){
                _pointerPosition.y = height;
            }

        }else if (!_dragging){
            // Snap back to center when the joystick is released
            _touchingPoint.x = (int) initx;
            _touchingPoint.y = (int) inity;
            //shaft.alpha = 0;
        }
    }
}
