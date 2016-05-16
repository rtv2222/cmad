#!/bin/bash

#Install docker
echo "Installing docker"
curl -fsSL https://get.docker.com/ | sh

#Install Java
echo "Installing Java"
wget http://cdn.azul.com/zulu/bin/zulu8.15.0.1-jdk8.0.92-linux_amd64.deb && sudo dpkg -i zulu8.15.0.1-jdk8.0.92-linux_amd64.deb

#Install maven
echo "Installing Maven"
sudo apt-get install maven

#Install GoCD
echo "Installing GoCD"
echo "deb https://download.go.cd /" | sudo tee /etc/apt/sources.list.d/gocd.list
curl https://download.go.cd/GOCD-GPG-KEY.asc | sudo apt-key add -
sudo apt-get update
sudo apt-get install go-server
sudo apt-get install go-agent

#Start the go agent
/etc/init.d/go-agent start

#Install Mongo docker instance
echo "Installing Mongo DB"
sudo docker run --name mymongo -p 27017:27017 -d mongo:latest

#Install SonarQube
echo "Installing SonarQube"
sudo docker run -d --name sonarqube -p 9000:9000 - 9092:9092 sonarqube
