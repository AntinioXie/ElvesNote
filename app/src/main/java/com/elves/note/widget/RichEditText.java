package com.elves.note.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;

import com.elves.note.R;
import com.elves.note.bean.SpanPart;
import com.elves.note.interfaces.INoteEditActions;
import com.elves.note.style.FontStyle;
import com.elves.note.style.FontStyle.*;
import com.elves.note.style.ImagePlate;

import java.util.ArrayList;
import java.util.List;

public class RichEditText extends AppCompatEditText implements INoteEditActions {
    private boolean  isHandleEvent = false;
    private int mScaledTouchSlop;
    private float mDownY;
    private InputMethodManager mImm;
    private Paint mLinePaint = new Paint();
    private int LINE_RIGHT_OFFSET =25;
    public static final int EXCLUD_MODE= Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
    public static final int EXCLUD_INCLUD_MODE= Spannable.SPAN_EXCLUSIVE_INCLUSIVE;
    public static final int INCLUD_INCLUD_MODE= Spannable.SPAN_INCLUSIVE_INCLUSIVE;

    public RichEditText(Context context) {
        super( context );
        init(context);
    }

    private void init(Context context) {
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mLinePaint.setColor(0x36bca47e);
        mLinePaint.setAntiAlias(false); // 这里必须设置为false，不然画出来的线是2pixel宽
        mLinePaint.setStyle( Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(0);
        LINE_RIGHT_OFFSET = context.getResources().getDimensionPixelSize( R.dimen.new_note_line_padding_right);
    }

    public RichEditText(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        Log.i("test","onSelectionChanged:selStart = "+selStart+"  ,  selfEnd = "+selEnd+"  "+getEditableText().toString());
        String currentStr = getEditableText().toString();
        int newLineStartIndex = currentStr.lastIndexOf( "\n",selStart);
        Log.i("test","newLineStartIndex  =" +newLineStartIndex);
        super.onSelectionChanged( selStart, selEnd );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isHandleEvent){
            return super.onTouchEvent( event );
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                if (Math.abs(event.getY() - mDownY) >= mScaledTouchSlop) {
                    closeSoftInputWindow();
                }
                break;
        }
        return super.onTouchEvent( event );
    }

    private void closeSoftInputWindow() {
        if (mImm == null) {
            mImm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (mImm.isActive(this)) {
            mImm.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLines( canvas );
        super.onDraw( canvas );
    }

    private void drawLines(Canvas canvas) {
        final int height = getHeight();
        final int lineCount = getLineCount();
        final int left = 0;
        final int right = getWidth() - LINE_RIGHT_OFFSET;
        final Layout layout = getLayout();

        final int lineHeight = getLineHeight();
        int bottom = 0;
        canvas.save();
        canvas.translate(0, getCompoundPaddingTop() + getScaleY());
        canvas.drawLine(left, -getCompoundPaddingTop()+1 , right, -getCompoundPaddingTop()+1 , mLinePaint);
        for (int i = 0; i < lineCount; i ++) {
            bottom = layout.getLineBottom(i);
            if(i==lineCount-1){
                canvas.drawLine(left, bottom +getCompoundPaddingTop(), right, bottom +getCompoundPaddingTop(), mLinePaint);
            }else{
                canvas.drawLine(left, bottom -getCompoundPaddingTop(), right, bottom - getCompoundPaddingTop(), mLinePaint);
            }
        }
        while (bottom < height) {
            bottom += lineHeight;
            canvas.drawLine(left, bottom+getCompoundPaddingTop(), right, bottom+getCompoundPaddingTop(), mLinePaint);
        }
        canvas.restore();
    }

    /* Set font Style begin*/
    @Override
    public void setBold(boolean isBold) {
        FontStyle fontStyle = new FontStyle();
        fontStyle.isBold= true;
        setSpan(fontStyle,isBold, StyleSpan.class);
    }

    @Override
    public void setItalic(boolean isItalic) {
        FontStyle fontStyle = new FontStyle();
        fontStyle.isItalic= true;
        setSpan(fontStyle,isItalic, StyleSpan.class);

    }

    @Override
    public void setUnderline(boolean isUnderLine) {
        FontStyle fontStyle = new FontStyle();
        fontStyle.isUnderLine= true;
        setSpan(fontStyle,isUnderLine, StyleSpan.class);
    }

    @Override
    public void setStrikethrough(boolean isStrikethrough) {
        FontStyle fontStyle = new FontStyle();
        fontStyle.isStreak=true;
        setSpan(fontStyle,isStrikethrough,StrikethroughSpan.class);
    }

    @Override
    public void setFontSize(int size) {
        if(size==0){
            size = 42;
        }
        FontStyle fontStyle = new FontStyle();
        fontStyle.fontSize =size;
        setSpan(fontStyle,true, AbsoluteSizeSpan.class);
    }

    @Override
    public void setFontColor(int color) {
        if(color==0){
            color= Color.BLACK;
        }
        FontStyle  fontStyle = new FontStyle();
        fontStyle.fontColor = color;
        setSpan(fontStyle,true, ForegroundColorSpan.class);
    }

    @Override
    public void setMarginLevel(int level) {

    }

    @Override
    public void setSelectionStyle(SelectionStyle selectionStyle) {

    }

    @Override
    public void setAlignStyle(AlignStyle alignStyle) {

    }

    @Override
    public void insertImage(String path) {
        if(!TextUtils.isEmpty( path )){
            Log.i("test","path :"+path);
            ImagePlate plate = new ImagePlate(this, this.getContext());
            plate.image(path);
        }
    }

    private <T> void setSpan(FontStyle fontStyle,boolean isSet,Class<T> tClass){
        int start = getSelectionStart();
        int end = getSelectionEnd();
        int mode = EXCLUD_INCLUD_MODE;
        T[] spans = getEditableText().getSpans(start,end,tClass);
        List<SpanPart> spanStyles = getOldFontSytles(spans,fontStyle);
        for(SpanPart spanStyle : spanStyles){
            if(spanStyle.start<start){
                if(start==end){mode=EXCLUD_MODE;}
                getEditableText().setSpan(getInitSpan(spanStyle), spanStyle.start,start,mode);
            }
            if(spanStyle.end>end){
                getEditableText().setSpan(getInitSpan(spanStyle),end, spanStyle.end,mode);
            }
        }
        if(isSet){
            if(start==end){
                mode=INCLUD_INCLUD_MODE;
            }
            getEditableText().setSpan(getInitSpan(fontStyle),start,end,mode);
        }
    }

    private <T> List<SpanPart> getOldFontSytles(T[] spans, FontStyle fontStyle){
        List<SpanPart> spanStyles = new ArrayList<>();
        for(T span:spans){
            boolean isRemove=false;
            if(span instanceof StyleSpan){
                int style_type = ((StyleSpan) span).getStyle();
                if((fontStyle.isBold&& style_type== Typeface.BOLD)
                        || (fontStyle.isItalic&&style_type== Typeface.ITALIC)){
                    isRemove=true;
                }
            }else{
                isRemove=true;
            }
            if(isRemove) {
                SpanPart spanStyle = new SpanPart(fontStyle);
                spanStyle.start = getEditableText().getSpanStart(span);
                spanStyle.end = getEditableText().getSpanEnd(span);
                if(span instanceof AbsoluteSizeSpan){
                    spanStyle.fontSize = ((AbsoluteSizeSpan) span).getSize();
                }else if(span instanceof ForegroundColorSpan){
                    spanStyle.fontColor = ((ForegroundColorSpan) span).getForegroundColor();
                }
                spanStyles.add(spanStyle);
                getEditableText().removeSpan(span);
            }
        }
        return spanStyles;
    }


    private CharacterStyle getInitSpan(FontStyle fontStyle){
        if(fontStyle.isBold){
            return new StyleSpan(Typeface.BOLD);
        }else if(fontStyle.isItalic){
            return new StyleSpan(Typeface.ITALIC);
        }else if(fontStyle.isUnderLine){
            return new UnderlineSpan();
        }else if(fontStyle.isStreak){
            return new StrikethroughSpan();
        }else if(fontStyle.fontSize>0){
            return new AbsoluteSizeSpan(fontStyle.fontSize,true);
        }else if(fontStyle.fontColor!=0){
            return new ForegroundColorSpan(fontStyle.fontColor);
        }
        return  null;
    }

    /* Set font Style end*/
}
