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
import java.util.List;
import com.codename1.ui.events.ActionListener;
import entities.Maison;
import entities.repas;
import utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service {
    
    

    public ArrayList<Maison> maisons;
    
    public static Service instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Service() {
         req = new ConnectionRequest();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
       public boolean addMaison(Maison m) {
           
           
 String url = statics.BASE_URL + "/ajoutmaison?nom="+m.getNom()+ "&adresse="+m.getAdresse()+ "&num=" +m.getNum()+"&prix="+m.getPrix()+"&note="+m.getNote()+"&image="+m.getImage(); 
    
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   

       
    public ArrayList<Maison> parseMaisons(){
        
         ArrayList<Maison> result  =new ArrayList<>();
            String url = statics.BASE_URL + "/listmaison";
                req.setUrl(url);
                req.addResponseListener(new ActionListener<NetworkEvent>() {
   
                   @Override
                   public void actionPerformed(NetworkEvent evt) {

                JSONParser json;
                json = new JSONParser();

          
            
             
            try {
                    Map<String, Object> mapEvents = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    java.util.List<Map<String, Object>> listofMaps = (java.util.List<Map<String, Object>>) mapEvents.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                //Création des tâches et récupération de leurs données
                Maison m = new Maison();
                float id = Float.parseFloat(obj.get("id").toString());
                
                m.setId((int)id);
                m.setNom(obj.get("nom").toString());
                m.setAdresse(obj.get("adresse").toString());
                m.setNum(((int)Float.parseFloat(obj.get("num").toString())));
                m.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                m.setNote(((int)Float.parseFloat(obj.get("note").toString())));
                m.setImage(obj.get("image").toString());
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                result.add(m);
            }
            
            
        } 
            catch (Exception ex) {
             ex.printStackTrace();
        }
                   }
                });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
               
    
    
  /*  public ArrayList<Maison> getAllMaison(){
        
        String url = statics.BASE_URL+"/listmaison";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                maisons = parseMaisons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return maisons;
    }
    */
    public boolean  Delete(int id){
       String url = statics.BASE_URL + "/suppmaison/" +id;

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
