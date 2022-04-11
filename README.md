# remind-me-carrillo-lombardi

But :
    Réaliser une application multilingue de collecte et d'envoi de notifications
    
Fonctionnalités :

- [x] application multi-utilisateur (s'enregistrer / supprimer son compte / social login)
- [x] Accessible en Français et en Anglais (détection de la langue du navigateur ET possibilité de forcer une langue)
- [x] En tant qu'utilisateur, je peux entrer manuellement des événements pour lesquels je souhaite être notifié.
- [ ] Une notification peut être faite par (mail / sms / whatsapp / discord) a un délai définit par rapport à l'événement. Un même événement peut avoir plusieurs notifications. (voir note en bas)
- [x] Une notification peut être validée, stoppant l'envoi de notification pour cet événement.
- [x] Un événement peut être défini comme périodique (un anniversaire, une fête) ou une fois seulement.
- [x] Ma homepage me montre, classée par ordre de distance les notifications à venir.
- [x] Je peux importer / exporter un fichier de notifications.

Bonus :

- [ ] Intégration web ou Android avec l'api Google ou Facebook permettant de récupérer les dates de naissance des contacts et écran de sélection / filtrage

<br>

![alt text](/model/mindmap.png "Mindmap")

Commandes:
```
sudo systemctl start docker.service
sudo docker-compose up

docker-compose up
mvn verify spring-boot:run
```

Note :
Nous avons essayer d'envoyer des messages par SMS, mais cela était disponnible gratuitement que entre numéro américain. Donc nous avons abandonné.

Ensuite nous avons essayé whatsapp mais il fallait aussi un numéro et il y avait des personnes qui ont été bannis après avoir utiliser une third-party app
Nous avons vu Reddit mais les libraires qui marchent sont en python et celles en java sont soient trop anciennes et donc ne marche plus soient n'ont pas la fonction compose de l'api reddit et donc l'impossibilite d'envoyer un message direct à un utilisateur

Nous avons utilisé l'api Twitter avec twitter4j en libraire java pour pouvoir envoyer la notification en message direct 

Le code fonctionne mais nous avons cette "erreur"
![](https://i.postimg.cc/T2ZxHZnV/unknown.png)

En regardant, l'api il faut que le compte ait un acces plus éléver et donc il faut faire la demande 
Malheuresement, cette demande n'a pas été encore accepté (probablement dû au fait que le compte a été créer il y a quelque jours)

## Maquette

<br>

![alt text](/model/login.png "Login")

<br>

![alt text](/model/register.png "Register")

<br>

![alt text](/model/index.png "Index")

<br>

![alt text](/model/profile.png "Profil")

<br>

Bien sur ce maquettage est bien différent de notre application, on a bien évidement changer les couleurs.
Il nous manque certaines fonctionnalités comme l'envoie sur discord, messenger, SMS ou certaines traductions.

```
main 
└───java/com/example/remindme
│   └───classes
│       └───persistence
│           │   Event.java
│           │   EventRepository.java
│           │   Initializer.java
│           │   UserEntity.java
│           │   UserEntityRepository.java
│       │   FormWrapper.java
│   └───config
│       │   LanguageConfig.java
│       │   SecurityConfig.java
│   └───controller
│       │   AdminController.java
│       │   EventController.java
│       │   LoginController.java
│       │   ProfileController.java
│       │   RegisterController.java
│   └───service
│       │   EventService.java
│       │   UserEntityService.java
│   │   RemindeMeApplication.java
│       
│
└───resources
│   │   application.properties: fichier de propriétés de l'application
│   └───static
│   │   └───main.css: Fichier pour le css
│   └───templates
│   │   └───admin.html: template pour la page admin
│   │   └───login.html: template pour la page login
│   │   └───profile.html: template pour la page profile
│   │   └───register.html: template pour la page register
│   └───translations
│   │   └───messages_en.properties: traduction en anglais
│   │   └───messages_fr.properties: traduction en français
```