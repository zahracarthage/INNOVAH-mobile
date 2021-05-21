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
import com.codename1.ui.events.ActionListener;
import entities.repas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.statics;

/**
 *
 * @author zcart
 */
public class repasservice {
    
    public static repasservice instance = null;
    
    private ConnectionRequest req; 
        public boolean resultOK;

    
     public String json;
  
    
    public static repasservice getInstance()
    {
        if(instance == null)
        {
            instance = new repasservice(); 
        }
                    return instance;

    }
    
          public repasservice()
          {
                    req = new ConnectionRequest(); 


          }

        public void ajouterrepas(repas r)
        {
            String url=statics.BASE_URL+"/repas/repas_new_json?nom="+r.getNom()+"&description="+r.getDescription()+"&price="+r.getPrice()+"&category="+r.getCategory()+"&adresse="+r.getAdresse()+"&img="+r.getImg();

            req.setUrl(url);


            req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str); 

        });

                NetworkManager.getInstance().addToQueueAndWait(req);



        }

     
                public ArrayList <repas> Display (){
                ArrayList<repas> result = new ArrayList<>();
                String url = statics.BASE_URL + "/repas/repas_list_json";
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
                        repas rep = new repas();

                       
                        float id=Float.parseFloat(obj.get("id").toString());
                                                String nom= obj.get("nom").toString(); 

                        String description= obj.get("description").toString(); 
                        float price=Float.parseFloat(obj.get("price").toString());
                        String category= obj.get("category").toString();
                        String adresse = obj.get("adresse").toString(); 
                        String img=obj.get("img").toString();
                        
                        
                        rep.setId((int) id); 
                        rep.setNom(nom);
                        rep.setPrice((int) price);
                        rep.setAdresse(adresse); 
                        rep.setDescription(description); 
                        rep.setCategory(category);
                         rep.setImg(img);
                        
                        
                       
                        
                        result.add(rep);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    
   }
        
        
        
        public  repas details(int id, repas rep)
        {
            
            String url=statics.BASE_URL+"/repas_mobile_"+id;
            req.setUrl(url);
            
            String str = new String(req.getResponseData()); 
            
            req.addResponseListener ((evt) -> {
            
                        
                JSONParser jsonp; 
                jsonp = new JSONParser(); 


                    
            try{
                
            Map<String,Object>obj;
            obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            
                        String nom= obj.get("nom").toString(); 
                        String description= obj.get("description").toString(); 
                        float price=Float.parseFloat(obj.get("price").toString());
                        String category= obj.get("category").toString();
                        String adresse = obj.get("adresse").toString(); 
                        String img=obj.get("img").toString();

            

                    rep.setPrice((int) price);
                    rep.setAdresse(adresse); 
                     rep.setDescription(description); 
                                 rep.setCategory(category);
                                 rep.setImg(img);
                     }
                
            catch(IOException ex){
                    System.out.println("Error related to sql:("+ex.getMessage());
                    
                    
            }
            
            System.out.println("data ==="+str);
             
        }
                    
         );
            NetworkManager.getInstance().addToQueueAndWait(req);
            return rep;

        }
        
        
        public boolean  Delete(int id){
       String url = statics.BASE_URL + "/repas/supprimerrepasJson/" +id;

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
    
