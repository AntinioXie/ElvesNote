package com.elves.note.interfaces;

import com.elves.note.style.FontStyle;

public interface INoteEditActions {
    /**
     * set content font Style
     */

    void setBold(boolean isBold);

    void setItalic(boolean isItalic);

    void setUnderline(boolean isUnderLine);

    void setStrikethrough(boolean isStrikethrough);

    void setFontSize(int size);

    void setFontColor(int color);

    /**
     * set start margin
     */
    void setMarginLevel(int level);

    /**
     * set selection style
     */
    void setSelectionStyle(FontStyle.SelectionStyle selectionStyle);

    /**
     * set align style
     */
    void setAlignStyle(FontStyle.AlignStyle alignStyle);
}
