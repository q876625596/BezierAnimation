package com.ly.bezier;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class AnimationUtils {

    public static void alphaAnimationObject(View view, float start, float end, boolean couldClick) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", start, end);
        alphaAnimator.setDuration(300L);
        alphaAnimator.setInterpolator(new AccelerateInterpolator());
        alphaAnimator.start();
        view.setClickable(couldClick);
    }

    public static void transAnimationObject(View view, float start, float end, boolean couldClick) {
        ObjectAnimator transAnimator = ObjectAnimator.ofFloat(view, "Y", start, end);
        AnimationSet set = new AnimationSet(true);
        transAnimator.setDuration(300L);
        transAnimator.setInterpolator(new AccelerateInterpolator());
        transAnimator.start();
        if (couldClick) {
            view.setClickable(true);
        } else {
            view.setClickable(false);
        }
    }

    public static void transAnimationObjectXY(View view, float startX, float endX, float startY, float endY) {

        PropertyValuesHolder propertyValuesHolderX = PropertyValuesHolder.ofFloat("X", startX, startX + (Math.abs(startX - endX) / 4), endX);
        PropertyValuesHolder propertyValuesHolderY = PropertyValuesHolder.ofFloat("Y", startY, startY + (Math.abs(startY - endY) / 4), endY);

        ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderX, propertyValuesHolderY).setDuration(300L).start();
    }

    public static void bezierTwoAnimation(final View view, Point startPoint, Point endPoint, Point controlPoint) {
        BezierUtils.TwoBezier bezierUtils = new BezierUtils.TwoBezier(controlPoint);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setX(((Point) animation.getAnimatedValue()).x);
                view.setY(((Point) animation.getAnimatedValue()).y);
            }
        });
        valueAnimator.setDuration(300L);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }

    public static void bezierThreeAnimation(final View view, Point startPoint, Point endPoint, Point controlPoint1, Point controlPoint2) {
        BezierUtils.ThreeBezier bezierUtils = new BezierUtils.ThreeBezier(controlPoint1, controlPoint2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setX(((Point) animation.getAnimatedValue()).x);
                view.setY(((Point) animation.getAnimatedValue()).y);
            }
        });
        valueAnimator.setDuration(1000L);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }

    public static void bezierMultiAnimation(final View view, Point startPoint, Point endPoint, List<Point> points) {
        BezierUtils.MultiBezier bezierUtils = new BezierUtils.MultiBezier(points);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setX(((Point) animation.getAnimatedValue()).x);
                view.setY(((Point) animation.getAnimatedValue()).y);
            }
        });
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }

    public static AlphaAnimation alpha0To1() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(300L);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return alphaAnimation;
    }

    public static AlphaAnimation alpha1To0() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(300L);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return alphaAnimation;
    }

    public static TranslateAnimation trans0To1() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0
                , Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(300L);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return translateAnimation;
    }

    public static TranslateAnimation trans1To0() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0
                , Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        translateAnimation.setDuration(300L);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return translateAnimation;
    }

    public static AnimationSet animationSet0To1FromCenter() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(200L);
        return animationSet;
    }

    public static AnimationSet animationSet1To0FromCenter() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setDuration(200L);
        return animationSet;
    }
}
