/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import entities.reclamation;


import utils.statics;

/**
 *
 * @author zcart
 */
public class reclamationservice {
    
    public static reclamationservice instance = null;
    
    private ConnectionRequest req; 
    
    
    public static reclamationservice getInstance()
    {
        if(instance == null)
        {
            instance = new reclamationservice(); 
        }
                    return instance;

    }
    
          public reclamationservice()
          {
                    req = new ConnectionRequest(); 


          }

        public void ajouterreclamation(reclamation r)
        {
            String url=statics.BASE_URL+"/reclamation/newreclamationjson?nom="+r.getNom()+"&prenom="+r.getPrenom()+"&email="+r.getEmail()+"&subject="+r.getSubject()+"&message="+r.getMessage();

            req.setUrl(url);


            req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str); 

        });

                NetworkManager.getInstance().addToQueueAndWait(req);



        }

        
       
        

}
    
