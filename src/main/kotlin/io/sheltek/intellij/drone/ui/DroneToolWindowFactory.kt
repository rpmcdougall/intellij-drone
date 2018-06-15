package io.sheltek.intellij.drone.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import io.sheltek.intellij.drone.controller.DroneClient
import javax.swing.JPanel
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.MutableTreeNode
import javax.swing.tree.TreeModel

class DroneToolWindowFactory : ToolWindowFactory {
    private lateinit var myToolWindowContent : JPanel
    private lateinit var tree : JTree
    private lateinit var myToolWindow : ToolWindow

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        var root : DefaultMutableTreeNode = DefaultMutableTreeNode("People")
        var c : DroneClient = DroneClient()

        c.userRepos().results.map {
            root.add(DefaultMutableTreeNode(it.name.first))
        }
        var model : TreeModel = DefaultTreeModel(root)
        tree.setModel(model)

        myToolWindow = toolWindow
        var contentFactory : ContentFactory = ContentFactory.SERVICE.getInstance()
        var content : Content = contentFactory.createContent(myToolWindowContent, "", false)
        toolWindow.contentManager.addContent(content)
    }
}