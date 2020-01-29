package android.example.mybooklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);

            Book currentBook = getItem(position);

            TextView authorTV = (TextView)listItemView.findViewById(R.id.author);
            String author = currentBook.getAuthor();
            authorTV.setText(author);

            TextView titleTV = (TextView) listItemView.findViewById(R.id.title);
            String title = currentBook.getTitle();
            titleTV.setText(title);



        }return listItemView;
    }
}
