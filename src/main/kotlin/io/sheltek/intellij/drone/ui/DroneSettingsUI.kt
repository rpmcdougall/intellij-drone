package io.sheltek.intellij.drone.ui

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.options.Configurable
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import io.sheltek.intellij.drone.DronePlugin
import io.sheltek.intellij.drone.settings.DroneSettings
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent
import javax.swing.JTextField

class DroneSettingsUI: Configurable {
    class TextListener : ActionListener{
        var handMeDown: Boolean = false
        override fun actionPerformed(e: ActionEvent?) {
            handMeDown = true
            logger("drone").error("actionPerformed hit")
            logger("drone").error(e.toString())
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


    var modified: Boolean = false
    val droneToken: JTextField = JTextField()
    val droneUrl: JTextField = JTextField()
    var state: DroneSettings = DroneSettings()

    override fun isModified(): Boolean {
            return !((droneToken.text == PropertiesComponent.getInstance().getValue("drone_token",""))
                    && (droneUrl.text == PropertiesComponent.getInstance().getValue("drone_url","")))

    }

    override fun getDisplayName(): String {
        return DronePlugin.name
    }

    override fun reset() {
        this.droneToken.text = PropertiesComponent.getInstance().getValue("drone_token","")
        this.droneUrl.text = PropertiesComponent.getInstance().getValue("drone_url","")
    }
    
    override fun apply() {
        try {
            PropertiesComponent.getInstance().setValue("drone_token",droneToken.text)
            PropertiesComponent.getInstance().setValue("drone_url",droneUrl.text)
            this.modified = false
        } finally {

        }
    }

    override fun createComponent(): JComponent? {
        var t: TextListener = TextListener()
        t.handMeDown = this.modified
            droneToken.addActionListener(t)
            droneUrl.addActionListener(t)
        if (!PropertiesComponent.getInstance().getValue("drone_token").isNullOrEmpty()) {
            droneToken.text = PropertiesComponent.getInstance().getValue("drone_token")
        }
        if (!PropertiesComponent.getInstance().getValue("drone_url").isNullOrEmpty()) {
            droneUrl.text = PropertiesComponent.getInstance().getValue("drone_url")
        }
        
        return panel {
            row {
                label("Drone URL:")
                droneUrl(CCFlags.grow)
            }
            row {
                label("Drone Token:")
                droneToken(CCFlags.grow)
            }
        }
    }
}