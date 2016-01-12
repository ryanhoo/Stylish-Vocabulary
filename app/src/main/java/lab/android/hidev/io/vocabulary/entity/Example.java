package lab.android.hidev.io.vocabulary.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with Android Studio.
 * User: ryan@whitedew.me
 * Date: 1/12/16
 * Time: 12:29 AM
 * Desc: Example
 */
public class Example implements Parcelable {

    private String example;
    private String exampleHtml;

    public Example() {
    }

    // Getter & Setter

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExampleHtml() {
        return exampleHtml;
    }

    public void setExampleHtml(String exampleHtml) {
        this.exampleHtml = exampleHtml;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.example);
        dest.writeString(this.exampleHtml);
    }

    protected Example(Parcel in) {
        this.example = in.readString();
        this.exampleHtml = in.readString();
    }

    public static final Creator<Example> CREATOR = new Creator<Example>() {
        public Example createFromParcel(Parcel source) {
            return new Example(source);
        }

        public Example[] newArray(int size) {
            return new Example[size];
        }
    };
}
