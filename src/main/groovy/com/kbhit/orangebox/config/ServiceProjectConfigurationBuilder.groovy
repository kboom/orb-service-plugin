package com.kbhit.orangebox.config

import com.kbhit.orangebox.OrbServiceExtension
import com.kbhit.orangebox.tasks.BuildImageTaskCreator
import com.kbhit.orangebox.tasks.CreateContainerTaskCreator
import com.kbhit.orangebox.tasks.CreateDockerfileTaskCreator
import com.kbhit.orangebox.tasks.GetContainerIpTaskCreator
import com.kbhit.orangebox.tasks.RemoveContainerTaskCreator
import com.kbhit.orangebox.tasks.StartContainerTaskCreator
import com.kbhit.orangebox.tasks.StopContainerTaskCreator
import org.gradle.api.Project

class ServiceProjectConfigurationBuilder extends ProjectConfigurationBuilder {

    private static final String EXTENSION_NAME = "orbService"

    OrbServiceExtension serviceExtension

    ServiceProjectConfigurationBuilder(Project project) {
        super(project)
    }

    @Override
    protected configure(Project project) {
        project.apply(plugin: 'com.bmuschko.docker-remote-api')
    }

    @Override
    protected createExtensions(Project project) {
        serviceExtension = project.extensions.create(EXTENSION_NAME, OrbServiceExtension)
    }

    @Override
    protected processExtensions(Project project) {
        serviceExtension.with {
            buildDir = "${project.buildDir}/docker"
        }
    }

    @Override
    protected configureTasks(Project project) {
        new CreateDockerfileTaskCreator(serviceExtension).createTaskFor(project)
        new CreateContainerTaskCreator(serviceExtension).createTaskFor(project)
        new BuildImageTaskCreator(serviceExtension).createTaskFor(project)
        new StartContainerTaskCreator(serviceExtension).createTaskFor(project)
        new StopContainerTaskCreator(serviceExtension).createTaskFor(project)
        new RemoveContainerTaskCreator(serviceExtension).createTaskFor(project)
        new GetContainerIpTaskCreator(serviceExtension).createTaskFor(project)
    }

}
