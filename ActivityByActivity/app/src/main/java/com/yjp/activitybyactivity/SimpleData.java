package com.yjp.activitybyactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;

    // 일반 생성자
    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    // 읽어주는 생성자(?)
    public SimpleData(Parcel src){
        number = src.readInt();
        message = src.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public SimpleData createFromParcel(Parcel src){
            return new SimpleData(src);
        }

        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    // SimpleData -> Parcel 로 변환 해주는 Method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }
}
