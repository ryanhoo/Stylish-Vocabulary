package lab.android.hidev.io.vocabulary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import lab.android.hidev.io.vocabulary.entity.Vocabulary;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created with Android Studio.
 * User: ryan@whitedew.me
 * Date: 1/11/16
 * Time: 10:51 PM
 * Desc: SearchWordAsyncTask
 */
public class SearchWordAsyncTask extends AsyncTask<Void, Integer, Vocabulary> {

    private static final String TAG = "SearchWordAsyncTask";

    private Context mContext;
    private String mWord;

    public SearchWordAsyncTask(Context context, String word) {
        mContext = context;
        try {
            mWord = URLEncoder.encode(word, "utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Encode failed! " + word, e);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Arrays.toString(values));
    }

    @Override
    protected Vocabulary doInBackground(Void... params) {
        Vocabulary vocabulary = new Vocabulary(mWord);
        vocabulary.searchUrl = buildVocabularyUrl(mWord);
        try {
            Document document = Jsoup.connect(vocabulary.searchUrl).get();
            Elements blurbElements = document.select("div.section.blurb");
            Log.d(TAG, "search: " + blurbElements);
            Elements shortBlurb = blurbElements.select(".short");
            Elements longBlurb = blurbElements.select(".long");
            vocabulary.blurbShort = shortBlurb.text();
            vocabulary.blurbShortHtml = shortBlurb.html();
            vocabulary.blurbLong = longBlurb.text();
            vocabulary.blurbLongHtml = longBlurb.html();
        } catch (IOException e) {
            Log.e(TAG, "search: " + mWord, e);
        }
        return vocabulary;
    }


    public static String buildVocabularyUrl(String word) {
        return String.format("https://www.vocabulary.com/dictionary/%s", word);
    }
}
