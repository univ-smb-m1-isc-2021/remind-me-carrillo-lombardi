# remind-me-carrillo-lombardi
But :
    Réaliser une application multilingue de collecte et d'envoi de notifications
    
Fonctionnalitées :

    application mutli-utilisateur (s'enregistrer / supprimer son compte / social login)
    Accessible en Francais et en Anglais, (Détection de la langue du navigateur ET possibilité de forcer une langue)
    en tant qu'utilisateur je peux entrer manuellement des évènements pour lesquels je souhaite être notifiés.
    une notification peut être faite par (mail / sms / whatsapp / discord) a un délais definit par rapport a l'évéenment. un même événement peut avoir plusieurs noifications
    une notification peut être validé, stoppant l'envoi de notification pour cet événement
    un événement peut être défini comme périodique (un anniversaire, une fête) ou une fois seulement.
    ma homepage me montre, classée par ordre de distance les notifications a venir.
    je peux importer / exporter une fichier de notifications

Bonus :

    Intégration web ou android avec l api google ou facebook permettant de récupérer les dates de naissance des contacts et écran de sélection / filtrage


Commandes:

docker-compose up
mvn spring-boot:run

http://localhost/home
http://localhost:5050 --> pgadmin: post@gres.sql password