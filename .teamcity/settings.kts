import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    vcsRoot(HttpsGithubComOllvenSubmodulesRefsHeadsMain1)

    buildType(Child1)
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComOllvenSubmodulesRefsHeadsMain1)

        cleanCheckout = true
    }

    steps {
        script {
            name = "Tree"
            id = "Tree"
            scriptContent = """
                tree
                cat README.md
                cat level1-2/README.md
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object Child1 : BuildType({
    name = "child1"

    vcs {
        root(DslContext.settingsRoot)
    }
})

object HttpsGithubComOllvenSubmodulesRefsHeadsMain1 : GitVcsRoot({
    name = "https://github.com/ollven/Submodules#refs/heads/main (1)"
    url = "https://github.com/ollven/Submodules"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    checkoutSubmodules = GitVcsRoot.CheckoutSubmodules.IGNORE
    userForTags = "ollven<ollven@yandex.ru>"
    authMethod = password {
        userName = "ollven"
        password = "credentialsJSON:9fd10c2d-c48f-40aa-9156-19acfe23cab2"
    }
})
