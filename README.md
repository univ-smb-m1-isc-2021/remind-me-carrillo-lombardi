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
Nous avons utilisé l'api Twitter avec twitter4j en libraire java pour pouvoir envoyer la notification en message direct 

Le code fonctionne mais nous avons cette "erreur"
![](https://i.postimg.cc/T2ZxHZnV/unknown.png)

En regardant, l'api il faut que le compte ait un acces plus éléver et donc il faut faire la demande 
Malheuresemnt, cette demande n'a pas été encore accepté (probablement dû au fait que le compte a été créer il y a quelque jours)

## Maquette

<br>

![alt text](/model/login.png "Login")

<br>

![alt text](/model/register.png "Register")

<br>

![alt text](/model/index.png "Index")

<br>

![alt text](/model/profile.png "Profil")
