package usjt.com.aula07restjson;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AddBookActivity extends AppCompatActivity {

    private EditText titleET;
    private EditText writerET;
    private EditText nPagesET;
    private EditText editionET;
    FloatingActionButton sendFAB;

    private RequestQueue requestQueue;

    MainActivity main = new MainActivity();

    private String v = "0";

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent origemIntent = getIntent();
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleET = findViewById(R.id.titleEditText);
        writerET = findViewById(R.id.writerEditText);
        nPagesET = findViewById(R.id.nPagesEditText);
        editionET = findViewById(R.id.editionEditText);
        requestQueue = Volley.newRequestQueue(this);
        sendFAB = findViewById(R.id.sendFAB);

    }

    private String montaURL (String... args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
        }
        return sb.toString();
    }

    private JSONObject geraLivroJSON (Book book) {
        JSONObject jo = new JSONObject();

        try {
            jo.put("titulo", book.getTitle());
            jo.put("autor", book.getWriter());
            jo.put("numeroPaginas", book.getNumberPages());
            jo.put("edicao", book.getEdition());
            Toast.makeText(this, R.string.book_succes_message, Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }

    private void cadastrarLivros (JSONObject body) {
        String url = montaURL(
                getString(R.string.host_address),
                getString(R.string.host_port),
                getString(R.string.endpoint_base),
                getString(R.string.endpoint_salvar)
        );
        requestQueue.add(new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ));
    }

    public void sendBook(View view) {
        String titulo = titleET.getText().toString();
        String autor = writerET.getText().toString();
        int numeroPaginas = Integer.parseInt(nPagesET.getText().toString());
        String edicao = editionET.getText().toString();

        Book book = new Book(titulo, autor, numeroPaginas, edicao);
        book.setTitle(titulo);
        book.setWriter(autor);
        book.setNumberPages(numeroPaginas);
        book.setEdition(edicao);

        JSONObject body = geraLivroJSON(book);
        cadastrarLivros(body);
        setV("1");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("validador", getV());
        startActivity(intent);
    }

}
