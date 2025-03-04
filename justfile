# This output
default:
  just --list

# Run Quarkus Dev Mode for PROJECT
dev project:
  ./gradlew {{project}}:quarkusDev
