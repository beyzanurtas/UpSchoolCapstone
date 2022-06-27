package com.beyzanurtas.upschool_capstoneproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDResponse(@SerializedName("status")
                        @Expose
                        var status : Int,
                        @SerializedName ("message")
                        @Expose
                        var message : String){

}
