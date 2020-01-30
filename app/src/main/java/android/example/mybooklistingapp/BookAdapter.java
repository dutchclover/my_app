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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
            holder = new ViewHolder();
            holder.authorTV = convertView.findViewById(R.id.author);
            holder.titleTV = convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book currentBook = getItem(position);
        holder.titleTV.setText(currentBook.getTitle());
        holder.authorTV.setText(currentBook.getAuthor());
        return convertView;
    }

    private static class ViewHolder{
        TextView authorTV;
        TextView titleTV;
    }
}
