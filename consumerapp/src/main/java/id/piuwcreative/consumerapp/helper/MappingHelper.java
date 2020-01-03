package id.piuwcreative.consumerapp.helper;

import android.database.Cursor;

import java.util.ArrayList;

import id.piuwcreative.consumerapp.db.DatabaseContract;
import id.piuwcreative.consumerapp.entity.Note;

public class MappingHelper {
    public static ArrayList<Note> mapCursorToArrayList(Cursor notescursor) {
        ArrayList<Note> notesList = new ArrayList<>();

        while (notescursor.moveToNext()) {
            int id = notescursor.getInt(notescursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = notescursor.getString(notescursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description = notescursor.getString(notescursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String date = notescursor.getString(notescursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));

            notesList.add(new Note(id, title, description, date));
        }

        return notesList;
    }

    public static Note mapCursorToObject(Cursor notesCursor) {
        notesCursor.moveToFirst();
        int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
        String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
        String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
        String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));

        return new Note(id, title, description, date);
    }
}