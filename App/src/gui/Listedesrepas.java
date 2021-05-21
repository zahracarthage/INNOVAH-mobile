/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Command;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.repas;
import java.util.ArrayList;
import services.repasservice;

/**
 *
 * @author zcart
 */
public class Listedesrepas extends BaseForm {

  
    Form current;
    public Listedesrepas(Resources res){
      
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des Articles");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("food.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);  
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        )
                        )
                )
        ));
   
 
      

     
        
        
        repasservice es = new repasservice();
                ArrayList<repas> list = es.Display() ;


        {
           
            for (repas r : list) {

          

                Container c3 = new Container(BoxLayout.y());
               
                 SpanLabel cat=  new SpanLabel("Nom de l'annonce repas :" + r.getNom());
                 SpanLabel cat1= new SpanLabel("Description :" + r.getDescription());
                 SpanLabel cat2= new SpanLabel("prix du repas :" + r.getPrice());
                 SpanLabel cat3= new SpanLabel("Adresse :" + r.getAdresse());
                 SpanLabel cat4= new SpanLabel("img :" + r.getImg());

                 
               
                     
                      
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
                        c3.add(cat4);
       
                         Button Delete =new Button("Delete");
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       es.Delete((int) r.getId());
                     alert.dispose();
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("repas SUPPRIME AVEC SUCCES");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
              
                      
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                //new ListArticle(previous).show();
              
                
             
            });
                       
                        
           System.out.println("");
              
  add(c3);
  
        
  
            
         
            }
          
        }
     
    }
    
        
        
    }


 
    

