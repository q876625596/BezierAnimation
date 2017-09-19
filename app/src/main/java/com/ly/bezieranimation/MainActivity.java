package com.ly.bezieranimation;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ly.bezier.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button twoBezier;
    private Button threeBezier;
    private Button multiBezier;
    private Button reset;
    private ImageView image;

    private float resetX;
    private float resetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twoBezier = (Button) findViewById(R.id.twoBezier);
        threeBezier = (Button) findViewById(R.id.threeBezier);
        multiBezier = (Button) findViewById(R.id.multiBezier);
        reset = (Button) findViewById(R.id.reset);
        image = (ImageView) findViewById(R.id.image);
        twoBezier.setOnClickListener(this);
        threeBezier.setOnClickListener(this);
        multiBezier.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (resetX == 0) {
            resetX = image.getX();
            resetY = image.getY();
        }
        switch (view.getId()) {
            case R.id.twoBezier:
                AnimationUtils.bezierTwoAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX(), (int) image.getY() - 400),
                        new Point((int) image.getX() + 200, (int) image.getY() - 200));
                break;
            case R.id.threeBezier:
                AnimationUtils.bezierThreeAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX(), (int) image.getY() - 600),
                        new Point((int) image.getX() + 200, (int) image.getY() - 200),
                        new Point((int) image.getX() - 200, (int) image.getY() - 400));
                break;
            case R.id.multiBezier:
                List<Point> points = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    int x = (int) image.getX() + (int) ((Math.random() * 1000) - 500);
                    int y = (int) image.getY() + (int) ((Math.random() * 1000) - 500);
                    points.add(new Point(x, y));
                }
                AnimationUtils.bezierMultiAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX() + (int) ((Math.random() * 1000) - 500),
                                (int) image.getY() + (int) ((Math.random() * 1000) - 500)), points);
                break;
            case R.id.reset:
                if (resetX != 0) {
                    image.setX(resetX);
                    image.setY(resetY);
                }
                break;
        }
    }
}
