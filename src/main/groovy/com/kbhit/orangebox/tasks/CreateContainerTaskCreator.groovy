package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class CreateContainerTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    CreateContainerTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('createContainer', type: DockerCreateContainer, group: 'docker') {
            dependsOn 'buildImage'
            targetImageId { project.buildImage.getImageId() }
            containerName extension.name
        }
    }

}
