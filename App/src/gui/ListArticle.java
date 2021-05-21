package gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
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
import entities.Article;
import services.serviceArticle;
import java.util.ArrayList;


/**
 *
 * @author zemni
 */
public class ListArticle extends BaseForm{
    
     public  ListArticle(Resources res) {

      
        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des Articles");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

        Image img = res.getImage("articles.jpg");
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
        

  Button btnAddTask1 = new Button("Statistiques");
    
            

        btnAddTask1.addActionListener(e -> new Stat().createPieChartForm().show());
       
        addAll(btnAddTask1);
         
           

 


      
        serviceArticle es = new serviceArticle();
        ArrayList<Article> list = es.getAllArticle();

        {
           
            for (Article r : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
               
                 SpanLabel cat= new SpanLabel("Nom de l'article :" + r.getNom());
                 SpanLabel cat1= new SpanLabel("Entreprise de l'événement :" + r.getDescription());
                 SpanLabel cat2= new SpanLabel("Description de l'événement :" + r.getCategorie());
                 SpanLabel cat3= new SpanLabel("Adresse de l'événement :" + r.getPrix());
                 
               
                     
                      
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
       
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
                       es.Delete(r.getId());
                     
                        alert.dispose();
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
     
        }}}
    

