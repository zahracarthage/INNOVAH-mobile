/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import gui.ProfileForm;
import gui.SessionManager;
import utils.statics;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author malek
 */
public class UserService {
    
    public static UserService instance= null;
    
    public static boolean resultOK = true;
    
    private ConnectionRequest req;
    
    public static UserService getInstance(){
        if (instance== null)
            instance= new UserService();
        return instance;
       
    }
    
    public UserService(){
        req=new ConnectionRequest();
    }
    public void signup(TextField username,TextField password,TextField email,TextField confirmPassword, ComboBox<String> roles, Resources res){
        
       
        
        String url = statics.BASE_URL+"/user/signup?username="+username.getText().toString()+
        "&email="+email.getText().toString()+"&password="+password.getText().toString()+"&roles="+roles.getSelectedItem().toString();
        
        req.setUrl(url);
        
        if (username.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")){
            Dialog.show("erreur","veuillez remplir les champs","ok",null);
        }
        
        req.addResponseListener((e)->{
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            
    }
    
    public void signin( TextField email , TextField password , Resources rs) {
         String url =  statics.BASE_URL+"/user/signin?email="+email.getText()+"&password="+password.getText().toString() ; 
         req = new ConnectionRequest(url ,false) ;
                 
         req.setUrl(url) ; 
      req.addResponseListener(
    (e)->{
        JSONParser j = new JSONParser() ; 
        String json = new String(req.getResponseData()) + "" ; 
        
        try {
        if (json.equals("failed") || json.equals("passowrd not found") ){
            Dialog.show("echec d'autentification" , "username ou mot de passe oublie ","ok" , null ) ; 
        } 
        else {
            System.out.println("data ==== "+json);
            Map <String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray())) ; 
            
             //Session  Service
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                if(user.get("image") != null)
                    SessionManager.setImage(user.get("image").toString());
                
                
       System.out.println("Current user ==> "+SessionManager.getEmail()+", "+SessionManager.getUserName()+", "+SessionManager.getPassword());
            
           if(user.size() > 0) {
               new ProfileForm(rs).show() ; 
             } 
            
            
        }
        
        }
        catch(Exception ex){
        ex.printStackTrace() ; 
        }
        
    });
    
       NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    
    public static void EditUser(String username, String password, String email){
        
    String url = statics.BASE_URL+"/user/ediUser?username="+username+"&email="+email+"&password="+password;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("username", username);
                req.addArgument("password", password);
                req.addArgument("email", email);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                       
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
   
   
    
}
