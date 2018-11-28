job('NodeJS Docker example') {
    scm {
        git('git://github.com/nicoben/ansible-examples.git') {
          node -> // This is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@example.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        // This is the name of the NodeJS installation in
        // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('nicobenaz/docker-nodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
