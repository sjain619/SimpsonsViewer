package com.sample.codebase_sdk.model

import com.sample.codebase_sdk.util.IMAGE_URL

data class CharacterProfile(
    val name: String,
    val description: String,
    val imageUrl: String? = null
)

fun List<Profile>?.mapToProfile(): List<CharacterProfile>{
    return this?.map{
        CharacterProfile(
            name = extractNameFromUrl(it.firstURL),
            description = it.text ?: "No description",
            imageUrl = it.icon?.uRL?.run { "$IMAGE_URL${this}" }
        )
    }?: emptyList()
}

fun extractNameFromUrl(url: String?): String { //This extract the name of the character from URL

    var name = ""
    url?.let {
        name = it.split('/').last()
        name = name.replace("_"," ")
        name = name.replace("%22"," ") //Some cases found in name
        name = name.replace("%2C"," ") //Some cases found in name
        name = name.replace("%26"," ") //Some cases found in name
    }

    return name
}