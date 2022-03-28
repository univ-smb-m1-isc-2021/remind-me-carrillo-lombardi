# remind-me-carrillo-lombardi
But :
    Réaliser une application multilingue de collecte et d'envoi de notifications
    
Fonctionnalitées :

- [ ] application mutli-utilisateur (s'enregistrer / supprimer son compte / social login)
- [ ] Accessible en Francais et en Anglais, (Détection de la langue du navigateur ET possibilité de forcer une langue)
- [ ] En tant qu'utilisateur je peux entrer manuellement des évènements pour lesquels je souhaite être notifiés.
- [ ] Une notification peut être faite par (mail / sms / whatsapp / discord) a un délais definit par rapport a l'évéenment. un même événement peut avoir plusieurs noifications
- [ ] Une notification peut être validé, stoppant l'envoi de notification pour cet événement
- [ ] Un événement peut être défini comme périodique (un anniversaire, une fête) ou une fois seulement.
- [ ] Ma homepage me montre, classée par ordre de distance les notifications a venir.
- [ ] Je peux importer / exporter une fichier de notifications

Bonus :

- [ ] Intégration web ou android avec l api google ou facebook permettant de récupérer les dates de naissance des contacts et écran de sélection / filtrage

<br>
<br>
<br>
<br>

Commandes:

sudo systemctl start docker.service
sudo docker-compose up

docker-compose up
mvn verify spring-boot:run

http://localhost:8080/home
http://localhost:5050 --> pgadmin: post@gres.sql password

http://www.plantuml.com/plantuml/uml/JOzD3e8m44Rtd6BMtNW192IM5I29HEDsAbKdeLD2MvNRInjDMCsRlFd9LpgFipV4Wy4f4o2r8kHC23Yhm3wi9A0X3XzeYNrgwx1H6wvb1KTjqtRJoYhMtexBSAqJUescwoEUq4tn3xp9Fm7XfUS5HiiFO3Gw7SjT4QUCkkKxLy2-WAvl3rkrtEclBdOCXcnMwZN7ByiN

Projet Spring pour s'inspirer:
https://github.com/joerodriguez/spring-boot-todo-example/tree/master/src/main/java/com/github/joerodriguez/springbootexample

Translation:
https://o7planning.org/11691/create-a-multi-language-web-application-with-spring-boot