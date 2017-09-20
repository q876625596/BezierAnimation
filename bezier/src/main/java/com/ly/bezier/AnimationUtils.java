package com.ly.bezier;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class AnimationUtils {


    /**
     * 透明动画
     *
     * @param view                   控件
     * @param start                  开始的透明度（1为不透明，0为完全透明）
     * @param end                    结束的透明度
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator alphaAnimationObject(View view,
                                                float start,
                                                float end,
                                                long duration,
                                                @Nullable Interpolator interpolator,
                                                @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                @Nullable Animator.AnimatorListener animatorListener) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", start, end);
        alphaAnimator.setDuration(duration);
        if (animatorUpdateListener != null) {
            alphaAnimator.addUpdateListener(animatorUpdateListener);
        }
        if (interpolator != null) {
            alphaAnimator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            alphaAnimator.addListener(animatorListener);
        }
        return alphaAnimator;
    }

    /**
     * 缩放动画
     *
     * @param view                   控件
     * @param scaleBean              缩放属性
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator scaleAnimationObject(View view,
                                                ScaleBean scaleBean,
                                                long duration,
                                                @Nullable Interpolator interpolator,
                                                @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                @Nullable Animator.AnimatorListener animatorListener) {
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", scaleBean.getStartX(), scaleBean.getEndX());
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", scaleBean.getStartY(), scaleBean.getEndY());
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
        view.setPivotX(scaleBean.getPivotX());
        view.setPivotY(scaleBean.getPivotY());
        animator.setDuration(duration);
        if (animatorUpdateListener != null) {
            animator.addUpdateListener(animatorUpdateListener);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        return animator;
    }

    /**
     * 位移动画
     *
     * @param view                   控件
     * @param startPoint             开始的坐标点
     * @param endPoint               结束的坐标点
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator transAnimationObject(View view,
                                                Point startPoint,
                                                Point endPoint,
                                                long duration,
                                                @Nullable Interpolator interpolator,
                                                @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                @Nullable Animator.AnimatorListener animatorListener) {
        PropertyValuesHolder propertyValuesHolderX = PropertyValuesHolder.ofFloat("X", startPoint.x, endPoint.x);
        PropertyValuesHolder propertyValuesHolderY = PropertyValuesHolder.ofFloat("Y", startPoint.y, endPoint.y);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderX, propertyValuesHolderY);
        animator.setDuration(duration);
        if (animatorUpdateListener != null) {
            animator.addUpdateListener(animatorUpdateListener);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        return animator;
    }

    /**
     * 多属性动画,也可作为同时播放动画（PropertyValuesHolder）
     *
     * @param view                   控件
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @param p                      多个动画属性数组
     * @return
     */
    public static Animator multiPropertyAnimation(View view,
                                                  long duration,
                                                  @Nullable Interpolator interpolator,
                                                  @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                  @Nullable Animator.AnimatorListener animatorListener,
                                                  PropertyValuesHolder... p) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, p);
        animator.setDuration(duration);
        if (animatorUpdateListener != null) {
            animator.addUpdateListener(animatorUpdateListener);
        }
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        return animator;
    }

    /**
     * 同时播放动画（贝塞尔与trans都属于位移动画，因此当同时出现这两种的时候，只会播放最后的一个）
     *
     * @param animators 动画列表
     * @return
     */
    public static Animator multiAnimationObjectOneTime(Animator... animators) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.start();
        return animatorSet;
    }

    /**
     * 顺序播放动画
     *
     * @param animators 动画列表
     * @return
     */
    public static Animator multiAnimationObjectSequence(Animator... animators) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
        animatorSet.start();
        return animatorSet;
    }


    /**
     * 二次贝塞尔曲线动画
     *
     * @param view                   控件
     * @param startPoint             起始点
     * @param endPoint               结束点
     * @param controlPoint           控制点
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator bezierTwoAnimation(final View view,
                                              Point startPoint,
                                              Point endPoint,
                                              Point controlPoint,
                                              long duration,
                                              @Nullable Interpolator interpolator,
                                              @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                              @Nullable Animator.AnimatorListener animatorListener) {
        BezierUtils.TwoBezier bezierUtils = new BezierUtils.TwoBezier(controlPoint);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.setDuration(duration);
        if (interpolator != null) {
            valueAnimator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            valueAnimator.addListener(animatorListener);
        }
        valueAnimator.addUpdateListener(animatorUpdateListener != null ?
                animatorUpdateListener :
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setX(((Point) animation.getAnimatedValue()).x);
                        view.setY(((Point) animation.getAnimatedValue()).y);
                    }
                });
        return valueAnimator;
    }

    /**
     * 三次贝塞尔曲线动画
     *
     * @param view                   控件
     * @param startPoint             起始点
     * @param endPoint               结束点
     * @param controlPoint1          控制点1
     * @param controlPoint2          控制点2
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator bezierThreeAnimation(final View view,
                                                Point startPoint,
                                                Point endPoint,
                                                Point controlPoint1,
                                                Point controlPoint2,
                                                long duration,
                                                @Nullable Interpolator interpolator,
                                                @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                @Nullable Animator.AnimatorListener animatorListener) {
        BezierUtils.ThreeBezier bezierUtils = new BezierUtils.ThreeBezier(controlPoint1, controlPoint2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.setDuration(duration);
        if (interpolator != null) {
            valueAnimator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            valueAnimator.addListener(animatorListener);
        }
        valueAnimator.addUpdateListener(animatorUpdateListener != null ?
                animatorUpdateListener :
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setX(((Point) animation.getAnimatedValue()).x);
                        view.setY(((Point) animation.getAnimatedValue()).y);
                    }
                });
        return valueAnimator;
    }

    /**
     * 多次贝塞尔曲线动画
     *
     * @param view                   控件
     * @param startPoint             起始点
     * @param endPoint               结束点
     * @param points                 控制点列表（经测试最大size为41,加上起始点和结束点后为43）
     * @param duration               动画时长
     * @param interpolator           动画插值器
     * @param animatorUpdateListener 动画更新监听
     * @param animatorListener       动画启动，结束重置等监听
     * @return
     */
    public static Animator bezierMultiAnimation(final View view,
                                                Point startPoint,
                                                Point endPoint,
                                                List<Point> points,
                                                long duration,
                                                @Nullable Interpolator interpolator,
                                                @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener,
                                                @Nullable Animator.AnimatorListener animatorListener) {
        BezierUtils.MultiBezier bezierUtils = new BezierUtils.MultiBezier(points);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierUtils, startPoint, endPoint);
        valueAnimator.setDuration(duration);
        if (interpolator != null) {
            valueAnimator.setInterpolator(interpolator);
        }
        if (animatorListener != null) {
            valueAnimator.addListener(animatorListener);
        }
        valueAnimator.addUpdateListener(animatorUpdateListener != null ?
                animatorUpdateListener :
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setX(((Point) animation.getAnimatedValue()).x);
                        view.setY(((Point) animation.getAnimatedValue()).y);
                    }
                });
        return valueAnimator;
    }

    //缩放动画的属性类
    public static class ScaleBean {
        private float startX;//缩放开始的X轴比例
        private float endX;//缩放结束的X轴比例
        private float startY;//缩放开始的Y轴比例
        private float endY;//缩放结束的Y轴比例
        private float pivotX;//缩放中心的X（0-1，相对于view本身）
        private float pivotY;//缩放中心的Y（0-1，相对于view本身）


        public ScaleBean(float startX, float endX, float startY, float endY, float pivotX, float pivotY) {
            this.startX = startX;
            this.endX = endX;
            this.startY = startY;
            this.endY = endY;
            this.pivotX = pivotX;
            this.pivotY = pivotY;
        }

        public float getStartX() {
            return startX;
        }

        public void setStartX(float startX) {
            this.startX = startX;
        }

        public float getEndX() {
            return endX;
        }

        public void setEndX(float endX) {
            this.endX = endX;
        }

        public float getStartY() {
            return startY;
        }

        public void setStartY(float startY) {
            this.startY = startY;
        }

        public float getEndY() {
            return endY;
        }

        public void setEndY(float endY) {
            this.endY = endY;
        }

        public float getPivotX() {
            return pivotX;
        }

        public void setPivotX(float pivotX) {
            this.pivotX = pivotX;
        }

        public float getPivotY() {
            return pivotY;
        }

        public void setPivotY(float pivotY) {
            this.pivotY = pivotY;
        }
    }
}
