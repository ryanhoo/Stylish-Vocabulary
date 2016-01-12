package lab.android.hidev.io.vocabulary.entity;

/**
 * Created with Android Studio.
 * User: ryan@whitedew.me
 * Date: 1/12/16
 * Time: 12:21 AM
 * Desc: WordClass
 */
public enum WordClass {

    NOUN("noun"),
    VERB("verb"),
    ADVERB("adverb"),
    ADJECTIVE("adjective");

    String wordClass;

    WordClass(String wordClass) {
        this.wordClass = wordClass;
    }

    @Override
    public String toString() {
        return wordClass;
    }
}
