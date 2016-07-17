package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class RemoveContainerTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    RemoveContainerTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('removeContainer', type: DockerRemoveContainer, group: 'docker') {
            targetContainerId { extension.name }
        }
    }

}