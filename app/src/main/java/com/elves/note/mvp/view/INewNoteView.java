package com.elves.note.mvp.view;

import com.elves.note.style.FontStyle.*;

public interface INewNoteView {
    /**
     * set content font Style
     */

    void setBold(boolean isBold);

    void setItalic(boolean isItalic);

    void setUnderline(boolean isUnderLine);

    void setDeleteLine(boolean isDeleteLine);

    void setFontSize(int size);

    void setFontColor(int color);

    /**
     * set start margin
     */
    void setMarginLevel(int level);

    /**
     * set selection style
     */
    void setSelectionStyle(SelectionStyle selectionStyle);

    /**
     * set align style
     */
    void setAlignStyle(AlignStyle alignStyle);
}
