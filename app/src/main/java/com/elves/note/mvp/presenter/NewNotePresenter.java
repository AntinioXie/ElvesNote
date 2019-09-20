package com.elves.note.mvp.presenter;

import android.app.Activity;

import com.elves.note.mvp.model.INewNoteModel;
import com.elves.note.mvp.view.INewNoteView;

public class NewNotePresenter implements INewNoteProsenter {

    private INewNoteView mView;
    private INewNoteModel mModel;

    public NewNotePresenter(INewNoteView view, INewNoteModel model) {
        mView = view;
        mModel = model;
    }

}
