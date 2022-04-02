# remind-me-carrillo-lombardi
But :
    Réaliser une application multilingue de collecte et d'envoi de notifications
    
Fonctionnalités :

- [ ] application multi-utilisateur (s'enregistrer / supprimer son compte / social login)
- [x] Accessible en Français et en Anglais (détection de la langue du navigateur ET possibilité de forcer une langue)
- [x] En tant qu'utilisateur, je peux entrer manuellement des événements pour lesquels je souhaite être notifié.
- [ ] Une notification peut être faite par (mail / sms / whatsapp / discord) a un délai définit par rapport à l'événement. Un même événement peut avoir plusieurs notifications.
- [x] Une notification peut être validée, stoppant l'envoi de notification pour cet événement.
- [x] Un événement peut être défini comme périodique (un anniversaire, une fête) ou une fois seulement.
- [ ] Ma homepage me montre, classée par ordre de distance les notifications à venir.
- [ ] Je peux importer / exporter un fichier de notifications.

Bonus :

- [ ] Intégration web ou Android avec l'api Google ou Facebook permettant de récupérer les dates de naissance des contacts et écran de sélection / filtrage

<br>
<br>
<br>
<br>

http://www.plantuml.com/plantuml/uml/JOzD3e8m44Rtd6BMtNW192IM5I29HEDsAbKdeLD2MvNRInjDMCsRlFd9LpgFipV4Wy4f4o2r8kHC23Yhm3wi9A0X3XzeYNrgwx1H6wvb1KTjqtRJoYhMtexBSAqJUescwoEUq4tn3xp9Fm7XfUS5HiiFO3Gw7SjT4QUCkkKxLy2-WAvl3rkrtEclBdOCXcnMwZN7ByiN


## faut ranger les fichiers !!!!!!!!!!! (c'est pour que j'y pense)

Commandes:

sudo systemctl start docker.service
sudo docker-compose up

docker-compose up
mvn verify spring-boot:run

http://localhost:8080/home
http://localhost:5050 --> pgadmin: post@gres.sql password

TODO
    quand créer compte syncroniser avec login

Problème
    quand change de page on perd la langue --> session ?



Connexion exemple:
https://www.concretepage.com/spring/spring-security/spring-security-in-memory-authentication

Projet Spring pour s'inspirer:
https://github.com/joerodriguez/spring-boot-todo-example/tree/master/src/main/java/com/github/joerodriguez/springbootexample

Translation:
https://o7planning.org/11691/create-a-multi-language-web-application-with-spring-boot
