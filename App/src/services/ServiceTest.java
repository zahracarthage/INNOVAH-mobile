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
//import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import entities.events;
import utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 *
 * @author  BenHsounaMedNour
 */
public class ServiceTest {
    public ArrayList<events> tests;
    public  static ServiceTest instance;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceTest() {
      req = new ConnectionRequest();
    }

    public static ServiceTest getInstance(){
        if(instance == null){
            instance = new ServiceTest(); 
        }
         return instance;
    }
    
    
    public boolean addTest(events t){
        String url = "http://127.0.0.1:8000/add2?nom="+t.getNom()+"&date="+t.getDate()+"&capacite="+t.getCapacite()+"&q2="+t.getDescription()+"&adresse="+t.getAdresse();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode()==200;
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return resultOK;
    }
   public ArrayList<events> parseTest(String jsonText){
        try {
            tests = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> TestsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) TestsListJson.get("root");
            for (Map<String, Object> obj : list){
                events t= new events();
              
                t.setNom(obj.get("nom").toString());
                t.setDate(obj.get("date").toString());
                t.setCapacite(obj.get("capacite").toString());
                t.setDescription(obj.get("description").toString());
                t.setAdresse(obj.get("adresse").toString());
                tests.add(t);
                
                
            }
        } catch (Exception e) {
        }
        return tests;
    }
    
   public ArrayList<events> getAllTests() {
        String url = statics.BASE_URL+ "/affichejson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                tests = parseTest(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests;
    }
   public boolean deleteTest(events t) {
        String url = statics.BASE_URL + "/delete2/" + t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
    
}
