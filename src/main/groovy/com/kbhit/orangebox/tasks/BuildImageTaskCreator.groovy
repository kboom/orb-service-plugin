package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class BuildImageTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    BuildImageTaskCreator(OrbServiceExtension extension) {
        this.extension = extension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('buildImage', type: DockerBuildImage, group: 'docker') {
            dependsOn 'bootRepackage', 'createDockerfile'
            inputDir = project.file(extension.buildDir)

            doFirst {
                println "Copying lib from ${project.buildDir}/libs/${extension.name}-${extension.version}.jar to ${extension.buildDir}/app.jar"
                project.copy {
                    from "${project.buildDir}/libs"
                    into extension.buildDir
                    include "${extension.name}-${extension.version}.jar"
                    rename "${extension.name}-${extension.version}.jar", 'app.jar'
                }
            }
        }
    }

}
