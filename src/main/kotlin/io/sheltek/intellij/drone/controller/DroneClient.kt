package io.sheltek.intellij.drone.controller
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.jacksonDeserializerOf
import io.sheltek.intellij.drone.utils.IntelliJUtiliteis.Companion.getProperty
import io.sheltek.intellij.drone.model.PersonDummy

class DroneClient {

    fun userRepos() : PersonDummy {
        var pd : PersonDummy? = null
        FuelManager.instance.basePath = getProperty("drone_url","")
        "/?results=20".httpGet().responseString { _, response, _ ->
            var des = jacksonDeserializerOf<PersonDummy>()
            pd = des.deserialize(response.data)
        }
        return pd!!
    }
}