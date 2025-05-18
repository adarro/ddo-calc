# This output
default:
    just --list

# Run Quarkus Dev Mode for PROJECT
dev project:
    #!/usr/bin/env bash
    set -euxo pipefail
    ./gradlew {{ project }}:quarkusDev

devui:
    ./gradlew quarkusDev

# Rebuilds development sql from ddo-core via etl (start ddo-etl:quarkusdev first)
gensql:
    #!/usr/bin/env bash
    set -euxo pipefail
    curl -X 'GET' \
      'http://localhost:8811/sql/Feats?tableName=public.feat' \
      -H 'accept: text/plain' -o subprojects/common/ddo-dal/src/main/resources/etl_feat.sql
    cd subprojects/common/ddo-dal/src/main/resources
    cat base_import.sql etl_feat.sql > import.sql


