
package gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Activites;
import services.ServiceActivites;
import java.util.ArrayList;

/**
 *
 * @author THINKPAD
 */
public class statistiques1  extends Form {
    
    ArrayList<Activites> mat;


  
    
    public float calcul_nbr_matiere(ArrayList<Activites> r,String ch){
        
         ArrayList<Activites> mat = new ArrayList<Activites>();
         mat =     ServiceActivites.getInstance().getAllActivites();

        
    int f=0;
    for(int i=0;i<mat.size();i++){
        if (mat.get(i).getCategorie().equals(ch)){ f++;}
    }
    return f;
}
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(70);
    renderer.setLegendTextSize(70);
    renderer.setMargins(new int[]{12, 14, 11, 10, 19,0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
    
    
  protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
        series.add("escalade", this.calcul_nbr_matiere(mat, "escalade") );
        series.add("kayak", this.calcul_nbr_matiere(mat, "kayak") );
         series.add("plage", this.calcul_nbr_matiere(mat, "plage") );
          series.add("randonnée", this.calcul_nbr_matiere(mat, "randonnée") );
        

    return series;
    
}

public Form createPieChartForm() {
    
    
    
     new Label("Statistiques categories");
    // Generate the values
    double[] values = new double[]{
        this.calcul_nbr_matiere(mat, "escalade"),
        this.calcul_nbr_matiere(mat, "kayak"),
        this.calcul_nbr_matiere(mat, "plage"),
        this.calcul_nbr_matiere(mat, "randonnée")
        };


    // Set up the renderer
    int[] colors = new int[]{ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.CYAN,ColorUtil.BLUE};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);





    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Calandrier_ex", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Stat", new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
            //hi.addComponent(BorderLayout.CENTER, clock);

        f.show();

      
   /* f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              Gestcalan f2 = new Gestcalan();
              f2.show();
            }
        });*/
return f;
    
    

}

}


