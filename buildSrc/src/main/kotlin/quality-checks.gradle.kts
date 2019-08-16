import org.gradle.kotlin.dsl.pmd

// Quality Checks Profile
plugins { 
    pmd
}

configure<PmdExtension> {
    this.incrementalAnalysis.set(true) 
    this.isConsoleOutput = true
    
}