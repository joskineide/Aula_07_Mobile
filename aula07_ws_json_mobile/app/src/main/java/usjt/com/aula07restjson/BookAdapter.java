package usjt.com.aula07restjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter <BookViewHolder> {
    private Context context;
    private List<Book> books;

    //construtor
    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }
    public BookViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(
                R.layout.list_item,
                parent,
                false
        );
        return new BookViewHolder(raiz);
    }
    public void onBindViewHolder (@NonNull BookViewHolder holder, int position) {
        Book book = this.books.get(position);
        holder.writerTextView.setText(book.getWriter());
        holder.titleTextView.setText("Autor: " + book.getTitle());
        holder.editionTextView.setText("Edição: " + book.getEdition());
    }
    public int getItemCount() {
        return books.size();
    }
}
