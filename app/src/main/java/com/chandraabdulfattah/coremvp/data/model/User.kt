package com.chandraabdulfattah.coremvp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by bezzo on 14/11/17.
 */

open class User : RealmObject() {

    companion object {
        val ID = "id"
    }

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id : Int = 0

    @SerializedName("nama")
    @Expose
    var nama: String? = null

    @SerializedName("jabatan")
    @Expose
    var jabatan: String? = null
}
