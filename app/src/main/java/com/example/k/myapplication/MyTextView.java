package com.example.k.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by k on 2016/5/26.
 */
public class MyTextView extends TextView{
    //翻译，转化
    int mViewWidth,mTranslate,a=0,b=0;
    LinearGradient mLinearGradient;
    Paint p2;
    //模型，矩阵
    Matrix mMatrix;
    public MyTextView(Context context){
        super(context);
    }
    public MyTextView(Context context, AttributeSet attr){
        super(context,attr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        b++;
        if(mMatrix!=null){
            mTranslate+=mViewWidth/5;
            //作用为回到原来状态
            if(mTranslate>2*mViewWidth){
                mTranslate=-mViewWidth;
            }
            //设置不同的值渐变的效果也会不一样
            mMatrix.setTranslate(mTranslate,0);
            //设置本地矩阵
            mLinearGradient.setLocalMatrix(mMatrix);
//定时刷新
            postInvalidateDelayed(100);
        }
        super.onDraw(canvas);
        //canvas.restore();
    }
    //此方法先于onDraw()方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        a++;
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0){
                p2 = getPaint();
                //参数1为渐变起始点X坐标，参数2为渐变起始点Y坐标，参数3为渐变结束点X坐标，参数4为渐变结束点Y坐标，参数5为颜色数组，
                //数组中第一个颜色参数是渐变前的字体颜色，第二个为渐变的颜色，第三个为渐变中的字体颜色
                mLinearGradient=new LinearGradient(0,0,mViewWidth,0,new int[]{Color.YELLOW,Color.CYAN,Color.YELLOW},null, Shader.TileMode.CLAMP);
                p2.setShader(mLinearGradient);
                //实例化一个模型矩阵
                mMatrix = new Matrix();
            }
        }
    }
}
