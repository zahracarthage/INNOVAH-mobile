/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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
public class AddTestForm extends BaseForm {

    public AddTestForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("ajouter un evenement");
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
        TextField tfnom = new TextField("","nom");
                tfnom.setUIID("TextFieldBlack"); 

        TextField tfdate = new TextField("","date");
                tfdate.setUIID("TextFieldBlack"); 

        TextField tfcapacite = new TextField("","capacite");
                tfcapacite.setUIID("TextFieldBlack"); 

        TextField tfdescription = new TextField("","decription");
                tfdescription.setUIID("TextFieldBlack"); 

        TextField tfadresse = new TextField("","adresse");
                tfadresse.setUIID("TextFieldBlack"); 

       
        Button btnValider = new Button("Add Test");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(((tfnom.getText().length()==0)||(tfdate.getText().length()==0)||(tfcapacite.getText().length()==0)||(tfdescription.getText().length()==0)||tfadresse.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields",new Command("OK"));
                }
                else {
                    try {
                       events t = new events(tfnom.getText(),tfdate.getText(),tfcapacite.getText(),tfdescription.getText(),tfadresse.getText());
                           if(new ServiceTest().addTest(t)){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                            
                        }
                        else {
                            Dialog .show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be string", new Command("OK"));
                    }
                }
            }
        });
        addAll(tfnom,tfdate,tfcapacite,tfdescription,tfadresse,btnValider);
        
        
        }

}
