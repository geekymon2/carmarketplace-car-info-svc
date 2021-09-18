#! /bin/bash
source docker.properties

ENV=$1
DB_NAME=$2
DB_PASSWORD=$3

if [ -z "$ENV" ]
then
    echo 'Environment cannot be blank!'
    exit 0
fi

if [ -z "$DB_NAME" ]
then 
    echo 'DB_NAME cannot be blank!'
    exit 0
fi

if [ -z "$DB_PASSWORD" ]
then 
    echo 'DB_PASSWORD cannot be blank!'
    exit 0
fi

if [ -f "docker.properties" ]
then
    source docker.properties
else
    echo 'docker.properties not found!'
fi


echo "Starting Deployment for Image: $IMAGE_NAME."
echo "- Creating Environment Variables"
printf "ENVIRONMENT=$ENV\nSPRING_PROFILES_ACTIVE=$ENV\nCARINFODB_NAME=$DB_NAME\nCARINFODB_PASSWD=$DB_PASSWORD" >> .env
echo "- Loading Environment Variables"
if [ -f .env ]
then
  export $(cat .env | sed 's/#.*//g' | xargs)
fi
echo "- Stopping containers"
docker-compose -f "docker-compose.yml" stop
echo "- Pull the latest docker image"
docker pull "geekymon2/$IMAGE_NAME"
echo "- Starting Container"
docker-compose -f "docker-compose.yml" up -d
echo "- Deployment Complete."