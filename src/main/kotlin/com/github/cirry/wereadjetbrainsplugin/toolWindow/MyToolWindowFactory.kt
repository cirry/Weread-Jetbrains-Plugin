package com.github.cirry.wereadjetbrainsplugin.toolWindow

import com.github.cirry.wereadjetbrainsplugin.MyBundle
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.awt.Dimension


class MyToolWindowFactory : ToolWindowFactory {

    init {

    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow()
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), "", false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow() {
        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val noSupportLabel = JBLabel(MyBundle.message("noSupport"))
            if (JBCefApp.isSupported()) {
                val jbcef = JBCefBrowser()
                jbcef.component.preferredSize = Dimension(900,900)
                add(jbcef.component, BorderLayout.CENTER)
                jbcef.loadURL("https://weread.qq.com/")
            } else {
                add(noSupportLabel)
            }
        }
    }
}
