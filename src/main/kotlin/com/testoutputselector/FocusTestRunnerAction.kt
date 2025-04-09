package com.testoutputselector

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.wm.ToolWindowId
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.Content
import java.awt.Component
import java.awt.Container
import javax.swing.text.JTextComponent

class FocusTestRunnerAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        if (DumbService.isDumb(project)) return

        val toolWindowManager = ToolWindowManager.getInstance(project)
        val toolWindow = toolWindowManager.getToolWindow(ToolWindowId.RUN)
            ?: toolWindowManager.getToolWindow("Test Results")
            ?: return

        toolWindow.activate({
            val contentManager = toolWindow.contentManager
            val selectedContent: Content = contentManager.selectedContent ?: return@activate
            val rootComponent = selectedContent.component

            val outputComponent = findTextOutputComponent(rootComponent)
            outputComponent?.requestFocusInWindow()
        }, true)
    }

    private fun findTextOutputComponent(component: Component?): JTextComponent? {
        if (component == null) return null
        if (component is JTextComponent && component.isFocusable && component.isVisible) {
            return component
        }
        if (component is Container) {
            for (child in component.components) {
                val result = findTextOutputComponent(child)
                if (result != null) return result
            }
        }
        return null
    }
}
