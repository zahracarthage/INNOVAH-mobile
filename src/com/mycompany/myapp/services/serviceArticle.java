/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;


import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author zemni
 */
public class serviceArticle {
    
  public ArrayList<Article> Article;
    
    public static serviceArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public serviceArticle() {
         req = new ConnectionRequest();
    }

    public static serviceArticle getInstance() {
        if (instance == null) {
            instance = new serviceArticle();
        }
        return instance;
    }

//    public boolean addCat(Evenement t) {
//            String url = Statics.BASE_URL + "/addjs/new?type="+ t.getType();
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }

    public ArrayList<Article> parseCat(String jsonText){
        try {
            Article=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> ArticleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)ArticleListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               
               
                     Article e = new Article();
                    
                     try {
                float id = Float.parseFloat(obj.get("id").toString());
                
                
                      e.setId((int)id);
                     } 
                     catch (Exception e1) {
                    System.out.println("houni1");
        }
                      
                      try {
                      e.setNom(obj.get("nom").toString());
                      } catch (Exception e2) {
                    System.out.println("houni2");
        }
                     
                      try {
                      e.setDescription(obj.get("description").toString());
                      } catch (Exception e4) {
                    System.out.println("houni4");
        }
                      try {
                      e.setCategorie(obj.get("categorie").toString());
                      } catch (Exception e5) {
                    System.out.println("houni5");
        }
                        try {
                      e.setPrix(Float.parseFloat(obj.get("prix").toString()));
                      } catch (Exception e5) {
                    System.out.println("houni5");
        }
                
             try {
                Article.add(e);
                } catch (Exception e6) {
                    System.out.println("houni6");
        }
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Article;
    }
    
    public ArrayList<Article> getAllArticle(){
         String url = Statics.BASE_URL + "/AfficheJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Article = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Article;
    }
    public boolean  Delete(int id){
       String url = Statics.BASE_URL + "/SupprimerJson/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
    
    
}
