package id.piuwcreative.mynotesapp.helper;

import android.database.Cursor;

import java.util.ArrayList;

import id.piuwcreative.mynotesapp.db.DatabaseContract;
import id.piuwcreative.mynotesapp.entity.Note;

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
}
