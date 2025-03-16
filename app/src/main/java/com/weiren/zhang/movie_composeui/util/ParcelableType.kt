package com.weiren.zhang.movie_composeui.util

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putString(key, json.encodeToString(value))
}

inline fun <reified list : List<Any>> serializableListType(
    isNullableAllowed: Boolean = false
) = object : NavType<list>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): list {
        return bundle.getSerializable(key) as list
    }

    override fun parseValue(value: String): list {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: list): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: list) {
        bundle.putSerializable(key, value as java.io.Serializable)
    }
}