package usjt.com.aula07restjson;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView livrosRecyclerView;
    private BookAdapter adapter;
    private List<Book> books;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RequestQueue requestQueue;
    FloatingActionButton btn;

    public String validador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        validador = "0";
        btn = findViewById(R.id.fab);
        livrosRecyclerView = findViewById(R.id.booksRecyclerView);
        books = new ArrayList<>();
        adapter = new BookAdapter(this, books);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        livrosRecyclerView.setAdapter(adapter);
        livrosRecyclerView.setLayoutManager(llm);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        requestQueue = Volley.newRequestQueue(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                obterLivros();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        // validador
        Intent origemIntent = getIntent();
        validador = origemIntent.getStringExtra("validador");
        if("1".equals(validador)) {
            obterLivros();
        }
        validador = "0";
    }

    // Montador da URL
    public String montaURL (String... args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
        }
        return sb.toString();
    }

    public void addBook(View view) {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    public void obterLivros () {
        String url = montaURL(
                getString(R.string.host_address),
                getString(R.string.host_port),
                getString(R.string.endpoint_base),
                getString(R.string.endpoint_listar)
        );
        requestQueue.add(new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        books.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject iPos = response.getJSONObject(i);
                                String titulo = iPos.getString("titulo");
                                String autor = iPos.getString("autor");
                                String edicao = iPos.getString("edicao");
                                books.add(new Book(titulo, autor, edicao));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
