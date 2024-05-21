package com.app.cleanarchitecture.data.model


class UserDataResponse {

    val results: ArrayList<Results> = ArrayList()

}

class Results {

    var gender: String = ""
    var email: String = ""
    var phone: String = ""
    var cell: String = ""
    val name: Name = Name()
    val location: Location = Location()
    val picture: Picture = Picture()

}

class Name {

    var title: String = ""
    var last: String = ""
    var first: String = ""

    fun userName(): String {
        return "$first $last"
    }
}

class Location {

    var city: String = ""
    var state: String = ""
    var country: String = ""
    var postcode: String = ""

    fun fullAddress(): String {
        return "$city $state $country $postcode"
    }
}

class Picture {

    var large: String = ""
    var medium: String = ""
    var thumbnail: String = ""
}
