package com.elves.note.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.elves.note.R;
import com.elves.note.utils.CustomHtml;
import com.elves.note.widget.RichEditText;

import java.io.File;

public class ImagePlate {
    private Context mContext;
    private RequestManager glideRequests;
    private RichEditText view;

    public ImagePlate(RichEditText view, Context context) {
        this.view = view;
        this.mContext = context;
        glideRequests = Glide.with( context );
    }

    /**
     * 图片加载
     *
     * @param path
     */
    public void image(String path) {
        final Uri uri = Uri.parse( path );
        final int maxWidth = view.getMeasuredWidth() - view.getPaddingLeft() - view.getPaddingRight();
        glideRequests.load( new File( path ) ).asBitmap()
                .placeholder( R.mipmap.ic_launcher )
                .error( R.mipmap.ic_launcher )
                .into( new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = zoomBitmapToFixWidth( resource, maxWidth );
                        image( uri, bitmap );
                    }
                } );
    }


    public void image(Uri uri, Bitmap pic) {
        int start = view.getSelectionStart();
        int end = view.getSelectionEnd();
        int currentStrLength = view.getEditableText().length();
        String currentStr = view.getEditableText().toString();
        Log.i( "test", "length = " + currentStrLength + "  currentStr = " + currentStr );
        if (start == end) {
            NoteImageGroupSpan[] spans1 = view.getEditableText().getSpans( 0, start, NoteImageGroupSpan.class );
            NoteImageGroupSpan[] spans2 = view.getEditableText().getSpans( start, currentStrLength, NoteImageGroupSpan.class );
            //获取当前位置的前面的最后一个NoteImageGroupSpan
            //NoteImageGroupSpan span1 = spans1.length > 0 ? spans1[spans1.length - 1] : null;
            //NoteImageGroupSpan span2 = spans2.length > 0 ? spans2[0] : null;
            NoteImageGroupSpan tempSpan =null;
            for(NoteImageGroupSpan span:spans1){
                if(tempSpan==null){
                    tempSpan = span;
                }else{
                    int i = view.getEditableText().getSpanEnd( tempSpan );
                    int j = view.getEditableText().getSpanEnd( span );
                    if(i< j){
                        tempSpan = span;
                    }
                }
            }
            NoteImageGroupSpan span1 = tempSpan;

            NoteImageGroupSpan tempSpan2 =null;

            for(NoteImageGroupSpan span:spans2){
                if(tempSpan==null){
                    tempSpan2 = span;
                }else{
                    int i = view.getEditableText().getSpanStart( tempSpan2 );
                    int j = view.getEditableText().getSpanStart( span );
                    if(i>j){
                        tempSpan2 = span;
                    }
                }
            }
            NoteImageGroupSpan span2 = tempSpan2;
            int start1 = view.getEditableText().getSpanStart( span1 );
            int end1 = view.getEditableText().getSpanEnd( span1 );

            int start2 = view.getEditableText().getSpanStart( span2 );
            int end2 = view.getEditableText().getSpanEnd( span2 );

            boolean isStartNewLine = false;
            boolean isEndNewLine = false;
            int newLineStartIndex = currentStr.lastIndexOf( "\n", start );
            if (newLineStartIndex != -1 && newLineStartIndex + 1 == start) {
                isStartNewLine = true;
            }

            int newLineEndIndex = currentStr.indexOf( "\n", end );
            if (newLineEndIndex != -1 && newLineEndIndex == end) {
                isEndNewLine = true;
            }

            Log.i( "test", "isStartNewLine:" + isStartNewLine + " ,  isEndNewLine:" + isEndNewLine );
            Log.i( "test", "start:" + start + " ,  " + "  end:" + end );
            Log.i( "test", "start1:" + start1 + "  end1:" + end1 );
            Log.i( "test", "start2:" + start2 + "  end2:" + end2 );
            NoteImageGroupSpan newGroupSpan = null;
            boolean isNewGroup = false;
            boolean isPreInsert = false;

            Log.i("test","span1 size "+(span1==null?-1:span1.getSubSpanSzie()));
            Log.i("test","span2 size "+(span2==null?-1:span2.getSubSpanSzie()));
            if (span1 != null && start - 1 <= end1 && span1.getSubSpanSzie() < 4) {
                //与span1合并
                Log.i( "test", "与前面的Ｉｍａｇｅ group 合并" );
                newGroupSpan = span1;
                isPreInsert = true;
            } else if (span2 != null && end + 1 >= start2 && span2.getSubSpanSzie() < 4) {
                //与span2合并
                Log.i( "test", "与后面的Ｉｍａｇｅ group 合并" );
                newGroupSpan = span2;
            } else {
                //新建一个NoteImageGroupSpan
                Log.i( "test", "新建一个Ｉｍａｇｅ group" );
                newGroupSpan = new NoteImageGroupSpan( mContext, pic, uri );
                isNewGroup = true;
            }

            NoteImageSpan newSpan = new NoteImageGroupSpan( mContext, pic, uri );
            newGroupSpan.addSubSpan( newSpan );
            StringBuilder placeHolder = newGroupSpan.getSpanPlaceHoder();
            int spanStart = 0;
            int endStart = placeHolder.length();
            int groupInsertStart = start;
            if (!isStartNewLine&&newLineStartIndex!=-1) {
                if(isPreInsert){
                    spanStart = 0;
                }else if(isNewGroup){
                    placeHolder.insert( 0, "\n" );
                    spanStart = 1;
                }

                if (isNewGroup) {
                    groupInsertStart = start;
                } else {
                    groupInsertStart = start1 == -1 ? 0 : start1;
                }
            }
            if (!isEndNewLine) {
                if(isPreInsert) {
                    placeHolder.append( "\n" );
                }
                if (isNewGroup) {
                    groupInsertStart = start;
                } else {
                    groupInsertStart = start2 == -1 ? 0 : start2;
                }
            }
            Log.i( "test", "groupInsertStart : " + groupInsertStart + "  spanStart:" + spanStart + "  endStart:" + endStart );
            SpannableString ss = new SpannableString( placeHolder.toString() );
            ss.setSpan( newGroupSpan, spanStart, spanStart + endStart, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
            if (isNewGroup) {
                view.getEditableText().insert( groupInsertStart, ss );
            } else {
                if (isPreInsert) {
                    view.getEditableText().replace( start1, end1, ss );
                } else {
                    Log.i( "xxxx", "before:" + view.getEditableText().toString() );
                    view.getEditableText().replace( start2, end2, ss );
                    Log.i( "xxxx", "end:" + view.getEditableText().toString() );
                }
            }
            view.requestFocus();

        }
    }

    public void setClick(int start, int end, final String path) {
        view.setMovementMethod( LinkMovementMethod.getInstance() );
        ClickableSpan click_span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService( Context.INPUT_METHOD_SERVICE );
                imm.hideSoftInputFromWindow( view.getWindowToken(), 0 ); //强制隐藏键盘
                Toast.makeText( mContext, path, Toast.LENGTH_SHORT ).show();
            }
        };
        view.getEditableText().setSpan( click_span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
    }


    public static Bitmap zoomBitmapToFixWidth(Bitmap bitmap, int maxWidth) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int newHight = maxWidth * h / w;
        return zoomBitmap( bitmap, maxWidth, newHight );
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale( scaleWidth, scaleHeight );
        return Bitmap.createBitmap( bitmap, 0, 0, w, h, matrix, true );
    }
}
