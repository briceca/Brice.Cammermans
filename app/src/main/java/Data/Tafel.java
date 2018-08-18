package Data;


import android.os.Parcel;
import android.os.Parcelable;

public class Tafel implements Parcelable {
    private String id;
    private int x_as;
    private int y_as;
    private int nummer;
    private Boolean bezet;
    private String transactieId;
    {
        //default constructor
    }
    public Tafel(int x_as, int y_as, Boolean bezet,String transactieId, int nummer)
    {
        this.x_as = x_as;
        this.y_as = y_as;
        this.bezet = bezet;
        this.nummer = nummer;
        this.transactieId=transactieId;
    }
    public Tafel(String id, int x_as,int y_as, Boolean bezet, String transactieId, int nummer)
    {
        this.id = id;
        this.x_as = x_as;
        this.y_as = y_as;
        this.bezet = bezet;
        this.transactieId = transactieId;
        this.nummer = nummer;
    }

    public Tafel(Parcel in) {
        this.id = in.readString();
        this.x_as = in.readInt();
        this.y_as = in.readInt();
        this.nummer = in.readInt();
        byte tmpBezet = in.readByte();
        this.bezet = tmpBezet == 0 ? null : tmpBezet == 1;
        this.transactieId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(x_as);
        dest.writeInt(y_as);
        dest.writeInt(nummer);
        dest.writeByte((byte) (bezet == null ? 0 : bezet ? 1 : 2));
        dest.writeString(transactieId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Tafel> CREATOR = new Parcelable.Creator<Tafel>() {
        @Override
        public Tafel createFromParcel(Parcel in) {
            return new Tafel(in);
        }

        @Override
        public Tafel[] newArray(int size) {
            return new Tafel[size];
        }
    };

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX_as() {
        return x_as;
    }

    public void setX_as(int x_as) {
        this.x_as = x_as;
    }

    public int getY_as() {
        return y_as;
    }

    public void setY_as(int y_as) {
        this.y_as = y_as;
    }

    public Boolean getBezet() {
        return bezet;
    }

    public void setBezet(Boolean bezet) {
        this.bezet = bezet;
    }

    public String getTransactieId() {
        return transactieId;
    }

    public void setTransactieId(String transactieId) {
        this.transactieId = transactieId;
    }
}
