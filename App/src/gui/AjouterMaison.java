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
import entities.Maison;
import services.Service;

/**
 *
 * @author Asus
 */
public class AjouterMaison extends BaseForm{
    
    public AjouterMaison(Resources res) {
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
        setTitle("Ajouter une nouvelle maison");
        setLayout(BoxLayout.y());
        TextField tfNom = new TextField("","Nom");
                tfNom.setUIID("TextFieldBlack"); 

        TextField tfAdresse= new TextField("", "Adresse");
                tfAdresse.setUIID("TextFieldBlack"); 

        TextField tfNum = new TextField("","Numéro");
                tfNum.setUIID("TextFieldBlack"); 

        TextField tfPrix = new TextField("","Prix");
                tfPrix.setUIID("TextFieldBlack"); 

        TextField tfNote = new TextField("","Note");
                tfNote.setUIID("TextFieldBlack"); 

        TextField tfImage = new TextField("","Image");
                tfImage.setUIID("TextFieldBlack"); 

        Button btnValider = new Button("Ajouter maison");
        
        
          btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfNum.getText().length()==0)||(tfPrix.getText().length()==0)||(tfNote.getText().length()==0)||(tfImage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Maison m = new Maison(tfNom.getText(),tfAdresse.getText(),Integer.parseInt(tfNum.getText()),Integer.parseInt(tfPrix.getText()),Integer.parseInt(tfNote.getText()),tfImage.getText());
                      if( Service.getInstance().addMaison(m))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfAdresse,tfNum,tfPrix,tfNote,tfImage,btnValider);
        
        
        
        
    }
}


