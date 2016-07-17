package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task


class StopContainerTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    StopContainerTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('stopContainer', type: DockerStopContainer, group: 'docker') {
            targetContainerId { extension.name }
        }
    }

}
