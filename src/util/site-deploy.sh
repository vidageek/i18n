#! /bin/bash -i

ROOT=$PWD/../..;


SITE_DIR=$ROOT/target/tubaina/html/i18n;

REPORTS_DIR=$ROOT/target/site;

UTIL_DIR=$ROOT/src/util;

SERVER=vidageek@vidageek.net:/home/vidageek/projetos.vidageek.net/i18n/;

cd $ROOT;

mvn clean -o;

#Generate english version of I18n's site
mvn tubaina:build -o;

#copy redirect file (Needed due to tubaina's structure)
cp $UTIL_DIR/index.html $SITE_DIR/.;

#copy files to server
scp -rC $SITE_DIR/* $SERVER/.;

echo "Please check the site and make sure everything is ok!";
echo "Site deploy successful";
