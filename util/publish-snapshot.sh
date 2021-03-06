#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "andresmanz/ninebatch" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
    if [[ $(./gradlew -q getVersion) != *SNAPSHOT* ]]; then
        echo 'Travis can only publish snapshots.'
        return 0
    fi

    echo -e "Starting publish to Sonatype...\n"

    ./gradlew uploadArchives -PossrhUsername="${SONATYPE_USERNAME}" -PossrhPassword="${SONATYPE_PASSWORD}"
    RETVAL=$?

    if [ $RETVAL -eq 0 ]; then
        echo 'Completed publish!'
    else
        echo 'Publish failed.'
        return 1
    fi
fi

