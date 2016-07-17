package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class StartContainerTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    StartContainerTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('startContainer', type: DockerStartContainer, group: 'docker') {
            dependsOn 'createContainer'
            targetContainerId { project.createContainer.getContainerId() }
        }
    }

}
