package com.green.go.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by navega on 11/26/16.
 */

public class Denuncia implements Parcelable {

    private String mTitulo;
    private String mDescricao;
    private String mLocalizacao;

    public Denuncia() {
    }

    public Denuncia(String mTitulo, String mDescricao, String mLocalizacao) {
        this.mTitulo = mTitulo;
        this.mDescricao = mDescricao;
        this.mLocalizacao = mLocalizacao;
    }

    public String getmTitulo() {
        return mTitulo;
    }

    public void setmTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public String getmDescricao() {
        return mDescricao;
    }

    public void setmDescricao(String mDescricao) {
        this.mDescricao = mDescricao;
    }

    public String getmLocalizacao() {
        return mLocalizacao;
    }

    public void setmLocalizacao(String mLocalizacao) {
        this.mLocalizacao = mLocalizacao;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "mTitulo='" + mTitulo + '\'' +
                ", mDescricao='" + mDescricao + '\'' +
                ", mLocalizacao='" + mLocalizacao + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public Denuncia(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.mTitulo = data[0];
        this.mDescricao = data[1];
        this.mLocalizacao = data[2];
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.mTitulo, this.mDescricao, this.mLocalizacao});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Denuncia createFromParcel(Parcel in) {
            return new Denuncia(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Object[i];
        }
    };
}
