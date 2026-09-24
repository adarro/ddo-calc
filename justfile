# This output
default:
  just --list

# Run Quarkus Dev Mode for PROJECT
dev project:
  ./gradlew {{project}}:quarkusDev

# Attempt to compile using full JDK 21
testJdk21:
  #!/usr/bin/env bash
  set -euo pipefail
  jdkVersion="21.0.2-graalce"
  # check for SDKMan
  if type sdk &>/dev/null; then
    echo "SDKMAN is loaded and ready"
    if [ -d "$HOME/.sdkman/candidates/java/$jdkVersion" ]; then
      echo "Java version $jdkVersion is installed."
    else
      echo "Java version $jdkVersion is NOT installed, adding."
      echo "You may be prompted to set as default."
      sdk install java "$jdkVersion"
    fi
    # check for JDK 21 GraalVM
    sdk use java "$jdkVersion"
  fi
  # Gradle Daemon JVM and toolchain configuration both use JDK 21
  ./gradlew --stop
  ./gradlew --version
  ./gradlew :ddo-etl:tasks

# Test JDK 17 with Default Gradle (9.7)
testJdk17:
  export JAVA_HOME="${JAVA_HOME:-$HOME/.sdkman/candidates/java/17.0.9-graalce}"
  export PATH="$JAVA_HOME/bin:$PATH"
  ./gradlew --stop
  ./gradlew --version -PdefaultJavaToolChainVersion=17
  ./gradlew :ddo-etl:tasks -PdefaultJavaToolChainVersion=17 --stacktrace

# Test JDK 17 with Gradle 8.14.5
testJdk17Gradle8:
  gradle --stop && \
  ./gradlew --stop && \
  sdk use gradle 8.14.5 && \
  gradle --version -PdefaultJavaToolChainVersion=17
  ./gradlew :ddo-etl:tasks -PdefaultJavaToolChainVersion=17 --stacktrace

# Clean Local Project and Gradle Cache
purgeLocal:
  #!/usr/bin/env bash
  set -euo pipefail
  ./gradlew clean
  rm -rf .gradle/

# Purge Gradle Cache (Global and local)
nukeCache:
  #!/usr/bin/env bash
  set -euo pipefail
  ./gradlew --stop
  rm -rf ~/.gradle/caches/
  rm -rf .gradle/


# Real Time Local Dev with coordinated ports (Experimental)
dockerDev:
  echo "This will one day launch services in dev mode"
