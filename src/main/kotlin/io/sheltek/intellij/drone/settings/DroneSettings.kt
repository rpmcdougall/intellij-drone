package io.sheltek.intellij.drone.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "drone")
@Storage("drone.xml")
class DroneSettings : PersistentStateComponent<DroneSettings.State> {

    class State {
        var droneUrl: String = ""
        var droneToken: String = ""
    }

    var myState: State = State()

    override fun getState(): State {
        return this.myState
    }

    override fun loadState(state: State) {
        this.myState = state
    }
}