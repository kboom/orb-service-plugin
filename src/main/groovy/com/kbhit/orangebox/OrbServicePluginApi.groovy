package com.kbhit.orangebox

import com.kbhit.orangebox.config.ServiceProjectConfigurationBuilder
import com.kbhit.orangebox.config.SpringBootProjectConfigurationBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project

class OrbServicePluginApi implements Plugin<Project> {

    void apply(Project project) {
        new ServiceProjectConfigurationBuilder(project).build()
        new SpringBootProjectConfigurationBuilder(project).build()
    }

}
