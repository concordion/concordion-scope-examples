#!/bin/bash

REPO=concordion/concordion-scope-examples

if [ "$TRAVIS_REPO_SLUG" == "$REPO" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then

  echo -e "Publishing output...\n"

  cp -R build/reports/spec $HOME/spec-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/$REPO gh-pages > /dev/null
  
  cd gh-pages
  git rm -rf ./spec/$TRAVIS_BRANCH
  cp -Rf $HOME/spec-latest ./spec/$TRAVIS_BRANCH/$TRAVIS_JDK_VERSION
  git add -f .
  git commit -m "Latest output on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published output to gh-pages/spec/$TRAVIS_BRANCH/$TRAVIS_JDK_VERSION .\n"
  
fi