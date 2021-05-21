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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import entities.reclamation;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import services.reclamationservice;
import utils.Mailing;
import static utils.Mailing.mailing;

/**
 *
 * @author zcart
 */

    
public class sendrecl extends BaseForm{
    
    Form current;
     TextField email;
     

     
    
    public sendrecl(Resources res) {
        
         super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des repas");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("getintouch.jpg");
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
        
       

   
        TextField nom = new TextField("","entrer votre nom"); 
        nom.setUIID("TextFieldBlack"); 
        addStringValue("nom",nom);
        
        TextField prenom = new TextField("","entrer votre prenom"); 
        prenom.setUIID("TextFieldBlack"); 
        addStringValue("prenom",prenom);
        
        TextField email = new TextField("","entrer votre email"); 
        email.setUIID("TextFieldBlack"); 
        addStringValue("email",email);
        
        TextField subject = new TextField("","entrer le sujet"); 
        subject.setUIID("TextFieldBlack"); 
        addStringValue("subject",subject);
        
        TextField message = new TextField("","entrer une message"); 
        message.setUIID("TextFieldBlack"); 
        addStringValue("message",message);
        
       
                
       Button btnajouter = new Button("Envoyer une reclamation");
       addStringValue("",btnajouter);

       
      

       btnajouter.addActionListener ( (ActionEvent e) -> {
            
            try{
                if( nom.getText()=="" || prenom.getText()== "" || email.getText()== "" ||  subject.getText() == ""|| message.getText()=="" )
                {
                    Dialog.show("veuillez completer tous les champs.","","annuler","ok"); 
                }
                
                else 
                {
                    InfiniteProgress ip = new InfiniteProgress();; //loading after inserting Data 
                    final Dialog iDialog = ip.showInfiniteBlocking(); 
                    
                    reclamation r = new reclamation(String.valueOf(nom.getText()),
                               String.valueOf(prenom.getText()), 
                            
                            String.valueOf(email.getText()), 
                            String.valueOf(subject.getText()), 
                            String.valueOf(message.getText())
                    
                    ); 
                    
                    System.out.println("RECLAMATION"+r);
                    
                    reclamationservice.getInstance().ajouterreclamation(r);
                     
                    Mailing.mailing(email.getText());
                    // sendMail(res);
                  //  mailing.sendMail(email.getText());
                    //mail.sendMail(reclamationemail.getText());

                    
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

 
   

    
}

    

