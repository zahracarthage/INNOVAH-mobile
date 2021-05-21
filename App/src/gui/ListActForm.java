/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Activites;
import services.ServiceActivites;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListActForm extends BaseForm{

    /*public ListActForm(Form previous) {
        setTitle("List Activites");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceActivites.getInstance().getAllActivites().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }*/
    
    public ListActForm(Resources res) {

       super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des activités");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image imgg = res.getImage("food.jpg");
        if (imgg.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            imgg = imgg.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(imgg);
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
      
         
   
    Button btnAddTask1 = new Button("Statistiques");
    
            

        btnAddTask1.addActionListener(e -> new statistiques1().createPieChartForm().show());
       
        addAll(btnAddTask1);
         
        
      
        ServiceActivites as = new ServiceActivites();
        ArrayList<Activites> list = as.getAllActivites();

        {
           
            for (Activites a : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
               
                 SpanLabel cat= new SpanLabel("Nom de l'activité :" + a.getNom());
                 SpanLabel cat1= new SpanLabel("description :" + a.getDescription());
                 SpanLabel cat2= new SpanLabel("categorie :" + a.getCategorie());
                 SpanLabel cat3= new SpanLabel("date :" + a.getDate());
                SpanLabel cat4= new SpanLabel("image :" + a.getImage());
                 SpanLabel cat5= new SpanLabel("prix :" + a.getPrix());


                 
               
                     
                      
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
                        c3.add(cat4);
                        c3.add(cat5);
       
                         Button Delete =new Button("Delete");
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette activité?");
                alert.add(message);
                Button ok = new Button("oui");
                Button cancel = new Button(new Command("non"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       as.Delete(a.getIdact());
                     
                        alert.dispose();
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Activité SUPPRIMEE AVEC SUCCES");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
              
                      
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
             
              
             
            });
                     
                       
                        
           System.out.println("");
              
        add(c3);
              
            
       
                
            }
          
        }
     
    }

    
    
}
