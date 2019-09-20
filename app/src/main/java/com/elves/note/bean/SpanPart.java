package com.elves.note.bean;

import com.elves.note.style.FontStyle;

public class SpanPart extends FontStyle{
    public int start;
    public int end;

    public SpanPart(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public SpanPart(FontStyle fontStyle) {
        this.isBold = fontStyle.isBold;
        this.isItalic = fontStyle.isItalic;
        this.isStreak = fontStyle.isStreak;
        this.isUnderLine = fontStyle.isUnderLine;
        this.fontSize = fontStyle.fontSize;
    }
}
