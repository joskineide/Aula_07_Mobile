package usjt.com.aula07restjson;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView writerTextView;
    public TextView editionTextView;
    public BookViewHolder(View raiz) {
        super (raiz);
        this.titleTextView = raiz.findViewById(R.id.titleTextView);
        this.writerTextView = raiz.findViewById(R.id.writerTextView);
        this.editionTextView = raiz.findViewById(R.id.editionTextView);
    }
}
