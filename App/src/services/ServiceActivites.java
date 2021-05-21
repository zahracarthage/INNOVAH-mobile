/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Activites;
import utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceActivites {

    public ArrayList<Activites> Activites;
    
    public static ServiceActivites instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceActivites() {
         req = new ConnectionRequest();
    }

    public static ServiceActivites getInstance() {
        if (instance == null) {
            instance = new ServiceActivites();
        }
        return instance;
    }

    public boolean addActivite(Activites a) {
        String url = statics.BASE_URL + "/addActiviteJSON?nom=" + a.getNom() + "&description=" + a.getDescription()+ "&categorie=" + a.getCategorie()+ /*"&date=" + a.getDate()+*/ "&image=" + a.getImage()+"&prix=" + a.getPrix(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    } 

    public ArrayList<Activites> parseActivites(String jsonText){
        try {
            Activites=new ArrayList<>();
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
            Map<String,Object> ActivitesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)ActivitesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Activites a = new Activites();
                float id = Float.parseFloat(obj.get("idact").toString());
                a.setIdact((int)id);
                a.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                a.setNom(obj.get("nom").toString());
                a.setDescription(obj.get("description").toString());
                a.setCategorie(obj.get("categorie").toString());
                a.setImage(obj.get("image").toString());
                a.setDate( obj.get("date").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Activites.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Activites;
    }
    
    /*public ArrayList<Activites> getAllActivites(){
        ArrayList <Activites> result = new ArrayList();
        String url = Statics.BASE_URL+"/displayActivites";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    
                    Map <String,Object> mapActivites = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listofmaps = (List<Map<String,Object>>)mapActivites.get("root");
                    for (Map <String,Object> obj : listofmaps){
                        Activites a = new Activites();
                        
                        float id =Float.parseFloat(obj.get("Idact").toString());
                        String nom =obj.get("nom").toString();
                        String description =obj.get("description").toString();
                        String categorie =obj.get("categorie").toString();
                        String image =obj.get("image").toString();
                        
                        float prix =Float.parseFloat(obj.get("prix").toString());
                        
                        a.setIdact((int)id);
                        a.setPrix((int)prix);
                        a.setNom(nom);
                        a.setDescription(description);
                        a.setCategorie(categorie);
                        a.setImage(image);
                        
                        String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+ 10, obj.get("obj").toString().lastIndexOf(")"));
                        Date currenttime = new Date (Double.valueOf(DateConverter).longValue()*1000);
                        SimpleDateFormat formatter = new SimpleDateFormat ();
                        String dateString = formatter.format(currenttime);
                        a.setDate(dateString);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
             
           
        }
       
});
        return null;
        
  } */
    
    
     public ArrayList<Activites> getAllActivites(){
         String url = statics.BASE_URL + "/displayActivites";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Activites = parseActivites(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Activites;
    }
     
     public boolean  Delete(int idact){
       String url = statics.BASE_URL + "/deleteActiviteJSON/" +idact;

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