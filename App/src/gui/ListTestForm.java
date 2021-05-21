/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
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
import entities.events;
import services.ServiceTest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import javax.swing.text.Document;

/**
 *
 * @author  BenHsounaMedNour
 * 
 */
public class ListTestForm extends BaseForm {

    public ListTestForm(Resources res) {
         super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des evenements");
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
        SpanLabel sp = new SpanLabel();
        
        sp.setText(ServiceTest.getInstance().getAllTests().toString());
        add(sp);
   
            //// f twig 
    Button pdf=new Button("pdf");
 add(pdf);  
 pdf.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                        String path="";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"Events.pdf"));
           document.open();
          PdfPTable tb1 = new PdfPTable(11);
          tb1.addCell("Nom");
          tb1.addCell("Date");
          tb1.addCell("Capacite");
          tb1.addCell("Description");
          tb1.addCell("Adresse");
         
          
         ServiceTest es = new ServiceTest();
        ArrayList<events> list = es.getAllTests();
          for (int i = 0; i < list.size(); i++) {
            
              String Nom= String.valueOf(list.get(i).getNom());
              String Date= String.valueOf(list.get(i).getDate());
              String Capacite= String.valueOf(list.get(i).getCapacite());
              String Description = String.valueOf(list.get(i).getAdresse());
              String Adresse= String.valueOf(list.get(i).getDescription());
              
          tb1.addCell(Nom);
          tb1.addCell(Date);
          tb1.addCell(Capacite);
          tb1.addCell(Description);
          tb1.addCell(Adresse);
          
         
         
          }
         document.add(new Paragraph("Test"));
         document.add(tb1);
         document.close();
         writer.close();
        com.codename1.io.File file=new com.codename1.io.File("test.pdf");
        //desktop.open(file);
      } catch (DocumentException e)
      {
         e.printStackTrace();
      }catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
                        //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                     
                    }
                    
                }
                
                
                );
        
    }
    
    
}
