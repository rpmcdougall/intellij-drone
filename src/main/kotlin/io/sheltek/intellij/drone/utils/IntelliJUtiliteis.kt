package io.sheltek.intellij.drone.utils

import com.intellij.ide.util.PropertiesComponent

class IntelliJUtiliteis {
    companion object {
        fun getProperty(key : String, default : String) = PropertiesComponent.getInstance().getValue(key, default)
        fun setProperty(key : String, value : String) = PropertiesComponent.getInstance().setValue(key, value)
    }


}