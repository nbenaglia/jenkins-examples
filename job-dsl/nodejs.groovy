job('NodeJS example') {
    scm {
        git('git://github.com/nicoben/ansible-examples.git') {
          node -> // this is the hudson.plugins.git.GitSCM
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
        shell("npm install")
    }
}
