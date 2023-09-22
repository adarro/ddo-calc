plugins {
    jacoco
}


tasks.withType(jacocoTestReport::class) {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}