<h1 align="center">
  <img width="150" src="https://i.imgur.com/goZ8WXF.png" />
  <p>NotesApp</p>
</h1>

<h4 align="center">Java Spring Boot Microservices Project</h4>

[![forthebadge](https://forthebadge.com/images/badges/powered-by-netflix.svg)](https://forthebadge.com)

## About
This is a simple Java backend powered by microservices. Users can register, log in, create notes, optionally check them for spelling errors or convert them to a different format.

## Overview
![topology](img/topology.png)
![swagger](img/swagger1.png)


## Microservices
Below is a list of all required services to run this project:
* [users](user-service)
  * stores user credentials
  * MySQL database
* [notes](note-service)
  * stores notes
  * MongoDB database
* [converter](converter-service)
  * converts notes to different formats
* [spelling checker](spell-check-service)
  * checks notes for common misspelled words
* [zuul gateway](zuul-gateway)
  * proxy gateway that handles user authentication
  * authenticates users using the `user-service`
  * forwards requests to the `edge-service`
* [edge service](edge-service)
  * one API that calls multiple internal services
* [eureka server](discovery-server)
  * locates services
  * load balancer

For more detailed info, see the README of each service separately.

## Developers
| [<img src="https://avatars3.githubusercontent.com/u/30344294?s=460&v=4" width="100px;"/><br /><sub><b>Sleeyax</b></sub>](https://github.com/sleeyax)<br /> | [<img src="https://avatars1.githubusercontent.com/u/38490878?s=400&v=4" width="100px;"/><br /><sub><b>Dries Luyten</b></sub>](https://github.com/r0699049)<br /> |[<img src="https://avatars2.githubusercontent.com/u/38656091?s=460&v=4" width="100"/><br /><sub><b>stijnhhh</b></sub>](https://github.com/stijnhhh)<br /> | [<img src="https://avatars3.githubusercontent.com/u/38748248?s=400&v=4" width="100"/><br /><sub><b>Karel</b></sub>](https://github.com/karelroetss)<br />
| :---: | :---: | :---: | :---: | 