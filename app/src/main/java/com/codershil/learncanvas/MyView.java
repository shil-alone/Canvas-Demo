package com.codershil.learncanvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.DisplayMetrics;
import android.view.View;

// this is the custom class for drawing the canvas and paint on it
public class MyView extends View {

    // declaring the paint class objects for coloring and styling the canvas
    Paint mPaint , otherPaint , outerPaint , mTextPaint ;
    // RectF class is used to draw the rectangle with float values
    RectF mRectF ;
    // variable for storing the padding variable
    int mPadding ;

    // declaring the arc values
    float arcLeft, arcRight , arcTop , arcBottom ;
    // declaring the path object
    Path mPath ;

    // a constructor matching super with context as the parameter
    public MyView(Context context) {
        super(context);

        // initializing the paint object
        mPaint = new Paint();
        // setting antialias as true which smooth the edges like in real life
        mPaint.setAntiAlias(true);
        // setting the style of the paint to stroke type which just draws the outer line
        mPaint.setStyle(Paint.Style.STROKE);
        // setting the colour of the paint
        mPaint.setColor(Color.BLUE);
        // initializing the stroke with of a paint
        mPaint.setStrokeWidth(5);


        // initializing the mTextPaint objects
        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        // setting the color for text
        mTextPaint.setColor(Color.BLACK);
        // setting size of the text by convert in the dp into pixel using formula dp*density(imaginary density)
        mTextPaint.setTextSize(pxFromDp(context,24));


        // initializing the other paint object
        otherPaint = new Paint();

        // initializing the outer paint
        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);

        // setting the padding value ;
        mPadding  = 100 ;

        // declaring and initializing the display matrix class
        // which is used to get all the information about the android phones display
        DisplayMetrics displayMetrics = new DisplayMetrics();

//        ((Activity)getContext()).getWindowManager().getCurrentWindowMetrics().getBounds()

        // returns a display upon which WindowManager instance will create a new windows
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        // getting the screenWidth and screenHeight using the displayMetrics class
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // initializing the arc values
        arcLeft = pxFromDp(context, 20);
        arcTop = pxFromDp(context, 20);
        arcRight = pxFromDp(context, 100);
        arcBottom = pxFromDp(context, 100);


        // setting the point values from the point class and passing them x and y coordinate values
        Point p1 = new Point((int) pxFromDp(context, 80) + (screenWidth / 2), (int) pxFromDp(context, 40));
        Point p2 = new Point((int) pxFromDp(context, 40) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p3 = new Point((int) pxFromDp(context, 120) + (screenWidth / 2), (int) pxFromDp(context, 80));

        // setting the path according to the point values
        // creating the path object
        mPath = new Path();
        // firstly path moves to the p1 point
        mPath.moveTo(p1.x, p1.y);
        // then it will draw a line till the p2 point
        mPath.lineTo(p2.x, p2.y);
        // then it will draw a line till the p3 point
        mPath.lineTo(p3.x, p3.y);
        // finally it closes the path
        mPath.close();


        // drawing a rectangle using the float values
        // also passing the point values for left,right,top,bottom
        mRectF = new RectF(screenWidth/4,screenHeight/3,screenWidth/6,screenWidth/2);

    }


    // this is the actual method for drawing something on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // this method draws the rectangle from the top left point 10,10 with the otherPaint
        canvas.drawRoundRect(mRectF,10,10,otherPaint);
        // this method clips the rectangle with canvas
        canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
        // sets the background paint of the whole canvas
        canvas.drawPaint(outerPaint);

        // this draw the line from specified points
        // not the origin is from top left corner
        canvas.drawLine(250,250,400,400,mPaint);

        // drawing the rectangle with values left , top , right and bottom with paint
        // here mPadding shows the top left position of the rectangle
        canvas.drawRect(mPadding,mPadding,getWidth()-mPadding,getHeight()-mPadding,mPaint);


        // drawing a arc with the top, left , right , bottom ,start angle, sweep angle and paint value
        canvas.drawArc(arcLeft, arcTop, arcRight, arcBottom, 75, 45, true, mPaint);

        // changing the attributes of other pain object
        otherPaint.setColor(Color.GREEN);
        otherPaint.setStyle(Paint.Style.FILL);

        // this is the code for drawing the rectangle on the center of the screen
        canvas.drawRect(
                getLeft() + (getRight() - getLeft()) / 3,
                getTop() + (getBottom() - getTop()) / 3,
                getRight() - (getRight() - getLeft()) / 3,
                getBottom() - (getBottom() - getTop()) / 3, otherPaint);


        // this will draw the path from the points
        canvas.drawPath(mPath,mPaint);

        // changing the attributes of otherPaint objects
        otherPaint.setColor(Color.BLACK);

        // this the for drawing the circle with given radius and paint
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, arcLeft, otherPaint);

        // this will draw the text on the screen with top left corner and mTextPaint paint object
        canvas.drawText("Canvas basics", (float) (getWidth() * 0.3), (float) (getHeight() * 0.8), mTextPaint);


    }


    // this method returns the dp value into pixel value
    public static float pxFromDp(final Context context,final float dp){
        return dp * context.getResources().getDisplayMetrics().density;
    }


}
