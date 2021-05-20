/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.serviceArticle;
import com.mycompany.myapp.services.serviceEvenement;
import java.util.ArrayList;

/**
 *
 * @author THINKPAD
 */
public class Stat extends Form {
    
    ArrayList<Article> mat;


  
    
    public float calcul_nbr_matiere(ArrayList<Article> r,String ch){
        
         ArrayList<Article> mat = new ArrayList<Article>();
         mat =     serviceArticle.getInstance().getAllArticle();

        
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
        series.add("Souvenirs", this.calcul_nbr_matiere(mat, "Souvenirs") );
        series.add("Décoration", this.calcul_nbr_matiere(mat, "Décoration") );
         series.add("Habits", this.calcul_nbr_matiere(mat, "Habits") );
          series.add("Gastronomie", this.calcul_nbr_matiere(mat, "Gastronomie") );
        

    return series;
    
}

public Form createPieChartForm() {
    
    
    
     new Label("Statistiques Evenements");
    // Generate the values
    double[] values = new double[]{
        this.calcul_nbr_matiere(mat, "Esprit"),
        this.calcul_nbr_matiere(mat, "Telnet"),
        this.calcul_nbr_matiere(mat, "Axia"),
        this.calcul_nbr_matiere(mat, "Sopra")
        };


    // Set up the renderer
    int[] colors = new int[]{ColorUtil.GRAY, ColorUtil.GREEN, ColorUtil.CYAN,ColorUtil.BLUE};
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
    Form f = new Form("Notes", new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
            //hi.addComponent(BorderLayout.CENTER, clock);

        f.show();

        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {new HomeForm().show();});
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
