package lab.android.hidev.io.vocabulary.entity;

/**
 * Created with Android Studio.
 * User: ryan@whitedew.me
 * Date: 1/11/16
 * Time: 10:41 PM
 * Desc: Vocabulary
 */
public class Vocabulary {

    public Vocabulary() {

    }

    public Vocabulary(String word) {
        this.word = word;
    }

    public String word;

    public String searchUrl;

    public String blurbShort;
    public String blurbShortHtml;

    public String blurbLong;
    public String blurbLongHtml;
}
