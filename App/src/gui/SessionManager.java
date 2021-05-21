/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Malek
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String username ; 
    private static String email; 
    private static String password ;
    private static String image;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserName() {
        return pref.get("username",username);
    }

    public static void setUserName(String username) {
         pref.set("username",username);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassword() {
        return pref.get("password",password);
    }

    public static void setPassword(String password) {
         pref.set("password",password);
    }

    public static String getImage() {
        return pref.get("photo",image);
    }

    public static void setImage(String image) {
         pref.set("photo",image);
    }
    
    
    
    
    
    
}
