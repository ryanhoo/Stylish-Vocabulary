package lab.android.hidev.io.vocabulary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import lab.android.hidev.io.vocabulary.entity.Vocabulary;

public class StylishVocabularyActivity extends AppCompatActivity {

    private static final String TAG = "StylishVocabulary";

    TextView textViewBlurbShort, textViewBlurbLong;
    EditText editTextSearch;
    View viewClearSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_vocabulary);
        textViewBlurbShort = (TextView) findViewById(R.id.text_view_blurb_short);
        textViewBlurbLong = (TextView) findViewById(R.id.text_view_blurb_long);
        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        viewClearSearch = findViewById(R.id.image_view_clear_search);

        viewClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setText(null);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewClearSearch.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
            }
        });

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String word = editTextSearch.getText().toString();
                    if (!word.isEmpty()) {
                        search(word);
                    }
                }
                return false;
            }
        });
    }

    private void search(String word) {
        new SearchWordAsyncTask(this, word) {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textViewBlurbShort.setText(R.string.searching);
                textViewBlurbLong.setVisibility(View.GONE);
            }

            @Override
            protected void onPostExecute(Vocabulary vocabulary) {
                super.onPostExecute(vocabulary);
                textViewBlurbLong.setVisibility(View.VISIBLE);
                textViewBlurbShort.setText(vocabulary.blurbShort);
                textViewBlurbLong.setText(vocabulary.blurbLong);
            }
        }.execute();
    }
}
