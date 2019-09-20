package com.elves.note.mvp.view;

import android.widget.EditText;

import com.elves.note.style.FontStyle;
import com.elves.note.widget.RichEditText;

public class NewNoteView implements INewNoteView {
    RichEditText mEditTextView;
    public NewNoteView(RichEditText editText){
        mEditTextView = editText;
    }
    @Override
    public void setBold(boolean isBold) {
        
    }

    @Override
    public void setItalic(boolean isItalic) {

    }

    @Override
    public void setUnderline(boolean isUnderLine) {

    }

    @Override
    public void setDeleteLine(boolean isDeleteLine) {

    }

    @Override
    public void setFontSize(int size) {

    }

    @Override
    public void setFontColor(int color) {

    }

    @Override
    public void setMarginLevel(int level) {

    }

    @Override
    public void setSelectionStyle(FontStyle.SelectionStyle selectionStyle) {

    }

    @Override
    public void setAlignStyle(FontStyle.AlignStyle alignStyle) {

    }
}
