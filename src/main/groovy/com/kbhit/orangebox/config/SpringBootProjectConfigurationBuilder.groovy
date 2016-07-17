package com.kbhit.orangebox.config

import org.gradle.api.Project

class SpringBootProjectConfigurationBuilder extends ProjectConfigurationBuilder{

    SpringBootProjectConfigurationBuilder(Project project) {
        super(project)
    }

    @Override
    protected configure(Project project) {
        project.apply(plugin: 'spring-boot')
        project.configure(project, {
            dependencyManagement{
                imports {
                    mavenBom 'org.springframework.cloud:spring-cloud-dependencies:Brixton.RELEASE'
                }
            }
        })
        project.getDependencies().add('compile', 'org.springframework.boot:spring-boot-starter-web')
        project.getDependencies().add('compile', 'org.springframework.boot:spring-boot-starter-jetty')
        project.getDependencies().add('compile', 'org.springframework.cloud:spring-cloud-starter-eureka')
        project.getDependencies().add('compile', 'org.springframework.cloud:spring-cloud-starter-config')
        project.getDependencies().add('compile', 'io.springfox:springfox-swagger2:2.5.0')
        project.getDependencies().add('compile', 'io.springfox:springfox-swagger-ui:2.5.0')
        project.getDependencies().add('testCompile', 'junit:junit')
        project.getDependencies().add('testCompile', 'org.springframework.boot:spring-boot-starter-test')
    }

    @Override
    protected createExtensions(Project project) {
        return null
    }

    @Override
    protected processExtensions(Project project) {
        return null
    }

    @Override
    protected configureTasks(Project project) {
        project.configure(project, {
            jar {
                baseName = project.orbService.name
                version = project.orbService.version
            }
        })
    }

}
