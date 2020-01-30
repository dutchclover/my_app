package android.example.mybooklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    TextView emptyTV;
    ProgressBar pb;

    String query;

    private BookAdapter mAdapter;

    boolean isConnected;

    private static final String LOG_TAG = ResultActivity.class.getName();

    private static final int BOOK_LOADER_ID = 1;

    private static final String book_request_url = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent intent = getIntent();
        query = intent.getStringExtra("search");

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        pb = (ProgressBar) findViewById(R.id.loading_spinner);
        pb.setIndeterminate(true);
        ListView bookListView = (ListView) findViewById(R.id.list);
        emptyTV = (TextView) findViewById(R.id.empty_tv);
        bookListView.setEmptyView(emptyTV);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);


        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = mAdapter.getItem(i);
                Uri bookUrl = Uri.parse(book.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, bookUrl);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        LoaderManager loaderManager = getLoaderManager();
        if (isConnected) {
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            pb.setVisibility(View.GONE);
            emptyTV.setText(R.string.no_connection);
        }
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.d(LOG_TAG, "Loader created");
        return new BookLoader(ResultActivity.this, book_request_url.concat(query).concat("&key=AIzaSyC_HpBlqlA0Ni6v6zAGVucSWL6xch7tFno"));
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        pb.setVisibility(View.GONE);
        Log.d(LOG_TAG, "Loader finished");
        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            Log.d(LOG_TAG, "books size "+books.size());
            mAdapter.addAll(books);
        }
        emptyTV.setText(R.string.empty);
    }


    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}




