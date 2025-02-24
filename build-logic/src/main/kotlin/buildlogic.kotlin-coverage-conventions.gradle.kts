plugins {
    id("buildlogic.java-coverage-conventions")
}

/*
There is no kotlin specific coverage tool at this time.
We're just using Java's (jacoco)
Scala will utilize scoverage instead.

NOTICE: You must still include a kotlin app / library etc. profile as we do not auto-include kotlin / java library conventions here.
 */