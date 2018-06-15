package io.sheltek.intellij.drone.ui

import com.intellij.openapi.options.Configurable
import com.intellij.ui.layout.panel
import io.sheltek.intellij.drone.DronePlugin
import io.sheltek.intellij.drone.controller.DroneClient
import javax.swing.JComponent
import javax.swing.JTextField
import io.sheltek.intellij.drone.utils.IntelliJUtiliteis.Companion.getProperty
import io.sheltek.intellij.drone.utils.IntelliJUtiliteis.Companion.setProperty

class DroneSettingsUI: Configurable {

    val droneToken: JTextField = JTextField()
    val droneUrl: JTextField = JTextField()
//    TODO: save state to cloud
//    var state: DroneSettings = DroneSettings()

    override fun isModified(): Boolean {
        return !((droneToken.text == getProperty("drone_token", ""))
                && (droneUrl.text == getProperty("drone_url", "")))
    }

    override fun getDisplayName(): String {
        return DronePlugin.name
    }

    override fun reset() {
        this.droneToken.text = getProperty("drone_token", "")
        this.droneUrl.text = getProperty("drone_url", "")
    }
    
    override fun apply() {
        setProperty("drone_token", droneToken.text)
        setProperty("drone_url", droneUrl.text)
        var c : DroneClient = DroneClient()
        c.userRepos()
    }

    override fun createComponent(): JComponent? {
        droneToken.text = getProperty("drone_token", "")
        droneUrl.text = getProperty("drone_url", "")

        return panel {

            row {
                label("Drone URL:   ")
                droneUrl()
            }
            row {
                label("Drone Token:")
                droneToken()
            }
        }
    }

}
