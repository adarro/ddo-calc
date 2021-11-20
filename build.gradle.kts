
plugins {
    id("org.kordamp.gradle.project")
}


// general project information
val projectName = project.name
val gitHubAccountName = "truthencode"
val gitHubBaseSite = "https://github.com/$gitHubAccountName/${project.name}"
val siteIssueTracker = "${gitHubBaseSite}/issues"
val gitExtension = "${project.name}.git"
val siteScm = "${gitHubBaseSite}/$gitExtension"

val releaseActive: Boolean? = rootProject.findProperty("release") as Boolean?


config {
    release = if (releaseActive != null) releaseActive!! else false
    info {
        name = "DDO Calculations"
        vendor = "TruthEncode"
        description = "DDO Character Analyzer and Planner"
        inceptionYear = "2015"

        links {
            website = gitHubBaseSite
            issueTracker = siteIssueTracker
            scm = siteScm
        }

        scm {
            url = gitHubBaseSite
            developerConnection = "scm:git:git@github.com:$gitHubAccountName/${gitExtension}"
            connection = "scm:git:git://github.com/github.com/$gitHubAccountName/$gitExtension"
        }

        people {
            person {
                id = "adarro"
                name = "Andre White"
                roles = listOf("developer", "owner")
            }
        }
    }

    licensing {
        licenses {
            license {
                id = "Apache-2.0" //org.kordamp.gradle.plugin.base.model.LicenseId.APACHE_2_0
            }
        }
    }
}


allprojects{
    repositories{
        mavenCentral()
    }
}