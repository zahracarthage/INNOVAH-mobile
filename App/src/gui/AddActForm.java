/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.Activites;
import services.ServiceActivites;




import java.util.Date;



/**
 *
 * @author bhk
 */
public class AddActForm extends BaseForm{

    public AddActForm(Resources res) {
        
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
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter une Activite");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom activite");
         tfNom.setUIID("TextFieldBlack"); 
        TextField tfDescrip = new TextField("","tfDescrip");
         tfDescrip.setUIID("TextFieldBlack"); 
        TextField tfcategorie = new TextField("","tfcategorie");
         tfcategorie.setUIID("TextFieldBlack"); 
        TextField tfImage = new TextField("","tfImage");
         tfImage.setUIID("TextFieldBlack"); 
        TextField tfPrix = new TextField("","Prix");
                 tfPrix.setUIID("TextFieldBlack"); 
        
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        
       
      

        Button btnValider = new Button("Ajouter Activite");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfDescrip.getText().length()==0)||(tfcategorie.getText().length()==0)||(tfImage.getText().length()==0)||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
                    try {
                       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Activites a = new Activites( tfNom.getText(), tfDescrip.getText(), tfcategorie.getText(), format.format(datePicker.getDate()), tfImage.getText(), Integer.parseInt(tfPrix.getText()));
                        
                        if( ServiceActivites.getInstance().addActivite(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Activité ajoutée avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });    
        
        
        addAll(tfNom,tfDescrip,tfcategorie, datePicker, tfImage, tfPrix, btnValider);
   
                
    }
    
    

               
}

