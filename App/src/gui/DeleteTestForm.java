/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
import entities.events;
import services.ServiceTest;

/**
 *
 * @author  BenHsounaMedNour
 */
public class DeleteTestForm extends BaseForm {
    public DeleteTestForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Effacer un evenement");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image imgg = res.getImage("profile-background.jpg");
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
        setTitle("Delete Test");
        setLayout(BoxLayout.y());
        
       
        TextField tfId= new TextField("","Id");
                        tfId.setUIID("TextFieldBlack"); 

        Button btnValider = new Button("Delete Test");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               events t= new events(Integer.parseInt(tfId.getText()));
                if( ServiceTest.getInstance().deleteTest(t))
                     System.out.println("Deleted successfully");    
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("EVENT SUPPRIMEE AVEC SUCCES");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
                    refreshTheme();

               
                   
            }
        });
        
        
        addAll(tfId,btnValider);
    }
    
    
}
