package android.example.mybooklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String LOG_TAG = BookLoader.class.getName();

    private String mUrl;

    public BookLoader(Context context, String mUrl) {
        super(context);
        Log.d(LOG_TAG, "Loader initialized");
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "Loading started");
        forceLoad();

    }


    @Override
    public List<Book> loadInBackground() {
        Log.d(LOG_TAG, "Loading completed");
        if (mUrl == null) {
            return null;
        }
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}

