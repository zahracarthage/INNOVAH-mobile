/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
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
import entities.repas;
import java.io.IOException;
import java.io.OutputStream;
import services.repasservice;

/**
 *
 * @author zcart
 */
public class AddRepas extends BaseForm{
    
    Form current;
     private String filename = "";
    private String filepath = "";
    
    public AddRepas(Resources res) {
        
         super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des repas");
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
        
       
      
        
        TextField nom = new TextField("","entrer un nom"); 
        nom.setUIID("TextFieldBlack"); 
        addStringValue("nom",nom);
        
        TextField description = new TextField("","entrer une description"); 
        description.setUIID("TextFieldBlack"); 
        addStringValue("description",description);
        
        TextField price = new TextField("","entrer le prix"); 
        price.setUIID("TextFieldBlack"); 
        addStringValue("price",price);
        
        TextField category = new TextField("","entrer une categorie"); 
        category.setUIID("TextFieldBlack"); 
        addStringValue("category",category);
        
        
         TextField adresse = new TextField("","entrer une adresse"); 
        adresse.setUIID("TextFieldBlack"); 
        addStringValue("adresse",adresse);
        
        
         Button browse = new Button("Browse");       
         addStringValue("",browse);

         Label ivv = new Label(res.getImage("Logo.png"), "LogoLabel");
         addStringValue("",ivv);
                
       Button btnajouter = new Button("Ajouter une annonce repas");
       addStringValue("",btnajouter);

       
       browse.addActionListener((evt) -> {
            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filepath = (String) e.getSource();
                    try {
                        Image img = Image.createImage(filepath).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 2));
//                        ImageViewer iv = new ImageViewer(img);
//                        add(BorderLayout.CENTER_BEHAVIOR_SCALE,iv);
                        ivv.setIcon(img);
                        int fileNameIndex = filepath.lastIndexOf("/") + 1;
                        filename = filepath.substring(fileNameIndex);
                        revalidate();
                      


                    } catch (IOException ex) {

                    }
                }
            };
            Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
        });
       
    

       btnajouter.addActionListener ( (ActionEvent e) -> {
            
            try{
                if( nom.getText()=="" || description.getText()== "" || price.getText()== "" ||  category.getText() == ""|| adresse.getText()=="" || filename=="" )
                {
                    Dialog.show("veuillez completer tous les champs.","","annuler","ok"); 
                }
                
                else 
                {
                    InfiniteProgress ip = new InfiniteProgress();; //loading after inserting Data 
                    final Dialog iDialog = ip.showInfiniteBlocking(); 
                    
                    repas r = new repas(String.valueOf(nom.getText()),
                                String.valueOf(description.getText()), 
                            
                       //   Integer.parseInt(textFieldnb.getText()));
                                 Integer.parseInt(price.getText()),
                                String.valueOf(category.getText()), 
                                String.valueOf(adresse.getText()), 
                                filename
                    
                    ); 
                    
                    System.out.println("RECLAMATION"+r);
                    
                    repasservice.getInstance().ajouterrepas(r);
                    iDialog.dispose(); 
                    refreshTheme(); 
                    
                }
            }
            
            catch(Exception ex)
                  { 
                ex.printStackTrace(); 
                
                  }   
      
                });
                
    }

    private void addStringValue(String s, Component v) {
    add(BorderLayout.west(new Label(s,"PaddedLabel"))
            .add(BorderLayout.CENTER, v));
            add(createLineSeparator(0xeeeeee));
        
        }
    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight()<size){
            image = image.scaledHeight(size);
        }
        if(image.getHeight()>Display.getInstance().getDisplayHeight()/2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() /2);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay=new Label("","ImageOverlay");
        
      Container page1=LayeredLayout.encloseIn(imageScale,overLay,BorderLayout.south(BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText"),FlowLayout.encloseIn(null),spacer)));
        swipe.addTab("",res.getImage("food.jpg"),page1);
    }
    
     public void bindButtonSelection(Button btn, Label l){
    btn.addActionListener(e->{
    if(btn.isSelected()){
    updateArrowPosition(btn,l);
    }
    
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX()+btn.getWidth()/2-l.getWidth()/2);
    }

    public void localNotificationReceived(String notificationId) {
        System.out.println("Received local notification "+notificationId+" in callback localNotificationReceived");
    }


    
}
;