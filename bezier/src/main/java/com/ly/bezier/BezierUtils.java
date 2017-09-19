package com.ly.bezier;

import android.animation.TypeEvaluator;
import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class BezierUtils {

    //二次贝塞尔曲线
    static class TwoBezier implements TypeEvaluator<Point> {

        private Point controlPoint;

        TwoBezier(Point controlPoint) {
            this.controlPoint = controlPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            //根据二次贝塞尔方程求出运动轨迹
            int x = (int) (Math.pow((1 - t), 2) * startValue.x + 2 * t * (1 - t) * controlPoint.x + Math.pow(t, 2) * endValue.x);
            int y = (int) (Math.pow((1 - t), 2) * startValue.y + 2 * t * (1 - t) * controlPoint.y + Math.pow(t, 2) * endValue.y);
            return new Point(x, y);
        }
    }

    //三次贝塞尔曲线
    static class ThreeBezier implements TypeEvaluator<Point> {

        private Point controlPoint1;
        private Point controlPoint2;

        public ThreeBezier(Point controlPoint1, Point controlPoint2) {
            this.controlPoint1 = controlPoint1;
            this.controlPoint2 = controlPoint2;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            //根据三次贝塞尔方程求出运动轨迹
            int x = (int) ((int) (startValue.x * Math.pow((1 - t), 3)
                    + 3 * controlPoint1.x * t * Math.pow((1 - t), 2))
                    + 3 * controlPoint2.x * Math.pow(t, 2) * (1 - t)
                    + endValue.x * Math.pow(t, 3));
            int y = (int) ((int) (startValue.y * Math.pow((1 - t), 3)
                    + 3 * controlPoint1.y * t * Math.pow((1 - t), 2))
                    + 3 * controlPoint2.y * Math.pow(t, 2) * (1 - t)
                    + endValue.y * Math.pow(t, 3));
            return new Point(x, y);
        }
    }

    //多次贝塞尔曲线

    /*******************注意**********************/
    //allPoint集合的point个数有限制，测试最大个数为43个（包含起点和终点）
    static class MultiBezier implements TypeEvaluator<Point> {

        //控制点集合
        private List<Point> allPoints;

        public MultiBezier(List<Point> controlPoints) {
            this.allPoints = controlPoints;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            //判断坐标点列表中第一个点是否为起始点
            //如果不是那么将起点和终点添加到point集合
            if (startValue != allPoints.get(0)) {
                allPoints.add(0, startValue);
                allPoints.add(endValue);
            }
            //最终返回的point的x坐标
            long x = 0;
            //最终返回的point的y坐标
            long y = 0;
            //N次方贝兹曲线
            long n = allPoints.size() - 1;
            //循环集合中的每一个point
            for (int i = 0; i < allPoints.size(); i++) {
                //根据贝塞尔公式中第一个求组合数的式子 = n的阶乘/(i的阶乘 * n-i的阶乘)
                long nFactorial = 1;//n的阶乘
                long niiFactorial = 1;
                //求出组合数
                long combinatorialNumber = 0;
                //求出n的阶乘
                for (int j = 0; j < n; j++) {
                    //n的阶乘
                    nFactorial *= (n - j);
                    //i的阶乘
                    if (j < i) {
                        niiFactorial *= (i - j);
                    }
                    //n-i的阶乘
                    if (j < n - i) {
                        niiFactorial *= (n - i - j);
                    }
                    //求最大公约数
                    long method = method(nFactorial, niiFactorial);
                    nFactorial /= method;
                    niiFactorial /= method;
                    combinatorialNumber = nFactorial;

                }
                //根据贝塞尔的一般参数公式求出运动轨迹
                x += combinatorialNumber * allPoints.get(i).x * Math.pow((1 - t), n - i) * Math.pow(t, i);
                y += combinatorialNumber * allPoints.get(i).y * Math.pow((1 - t), n - i) * Math.pow(t, i);
            }
            return new Point((int) x, (int) y);
        }
    }


    //辗转相除法：返回公约数
    private static long method(long x, long y) {
        long a, b, c;
        a = x;
        b = y;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}
