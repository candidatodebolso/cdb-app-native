package br.com.candidatodebolso.app.model;

import com.google.gson.annotations.SerializedName;

public enum Answer {
    @SerializedName("YES") YES,
    @SerializedName("NO") NO,
    @SerializedName("DEPENDS") DEPENDS
}
