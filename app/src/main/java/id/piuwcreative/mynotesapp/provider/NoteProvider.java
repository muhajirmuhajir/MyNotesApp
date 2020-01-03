package id.piuwcreative.mynotesapp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import id.piuwcreative.mynotesapp.db.NoteHelper;

import static id.piuwcreative.mynotesapp.db.DatabaseContract.AUTHORITY;
import static id.piuwcreative.mynotesapp.db.DatabaseContract.NoteColumns.CONTENT_URI;
import static id.piuwcreative.mynotesapp.db.DatabaseContract.NoteColumns.TABLE_NAME;

public class NoteProvider extends ContentProvider {

    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private NoteHelper noteHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, TABLE_NAME, NOTE);

        URI_MATCHER.addURI(AUTHORITY, TABLE_NAME + "/#", NOTE_ID);
    }

    public NoteProvider() {

    }

    @Override
    public boolean onCreate() {
        noteHelper = NoteHelper.getInstance(getContext());
        noteHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case NOTE:
                cursor = noteHelper.queryAll();
                break;
            case NOTE_ID:
                cursor = noteHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;

        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;

        switch (URI_MATCHER.match(uri)) {
            case NOTE:
                added = noteHelper.insert(values);
                break;
            default:
                added = 0;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updated;
        switch (URI_MATCHER.match(uri)) {
            case NOTE_ID:
                updated = noteHelper.update(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return updated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int delete;
        switch (URI_MATCHER.match(uri)) {
            case NOTE_ID:
                delete = noteHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return delete;
    }


}
