package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.*;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class AdministradorBean implements Serializable {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private TipoServicio tipoServicio;

    @Autowired
    @Getter
    @Setter
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private Moderador moderador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private PieChartModel pieLugaresTipo;

    @Getter @Setter
    private BarChartModel barLugaresCiudad;

    @Getter @Setter
    private BarChartModel barLugaresCalificacion;

    @PostConstruct
    public void inicializar() {
        this.moderador  = new Moderador();
        this.administrador = obtenerAdministrador();
        this.ciudades = ciudadServicio.listarCiudades();
        graficarLugaresTipo();
        graficarLugaresCiudad();
        graficarLugaresCalificacion();
    }

    public Administrador obtenerAdministrador(){

        Administrador administradorEncontrado = new Administrador();

        if(personaLogin!=null){

            try{

                administradorEncontrado = administradorServicio.obtenerAdministrador(personaLogin.getId());
                personaLogin.toString();

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return administradorEncontrado;

    }

    public void registrarModerador() {
        try {
            if (personaLogin != null) {
                moderador.setAdministrador((Administrador) personaLogin);
                moderadorServicio.registrarModerador(moderador);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se ha creado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void eliminarModerador(){

        try {
            if (personaLogin!=null ) {
                moderador.setAdministrador(null);
                moderadorServicio.eliminarModerador(moderador.getEmail(),moderador.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarModerador(){

        try{
            if(personaLogin!=null){

                Moderador moderadorAux = moderadorServicio.obtenerModerador(moderador.getId());

                if (moderadorAux!=null){

                    moderador.setAdministrador((Administrador) personaLogin);
                    moderadorServicio.actualizarModerador(moderador,moderadorAux.getEmail(),moderadorAux.getPassword());
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el moderador");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }

    public void graficarLugaresTipo(){

        pieLugaresTipo = new PieChartModel();

        List<Tipo>listaTipos = tipoServicio.listarTipos();

        for(Tipo t:listaTipos){

            List<Lugar>listaLugaresTipo=lugarServicio.obtenerLugaresPorTipo(t.getNombre());

            pieLugaresTipo.set(t.getNombre(),listaLugaresTipo.size());
        }

        pieLugaresTipo.setTitle("Cantidad de lugares por tipo");
        pieLugaresTipo.setLegendPosition("e");
        pieLugaresTipo.setFill(true);
        pieLugaresTipo.setShowDataLabels(true);
        pieLugaresTipo.setDiameter(200);

    }

    public BarChartOptions opcionesBarras(String titulo, int min, int max){
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMin(min);
        ticks.setMax(max);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText(titulo);
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        return options;
    }

    public void graficarLugaresCiudad(){

        barLugaresCiudad = new BarChartModel();
        List<Number>valores = new ArrayList<>();
        List<String>labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        BarChartDataSet barDataSet = new BarChartDataSet();
        ChartData data = new ChartData();
        barDataSet.setLabel("Ciudades");

        BarChartOptions opciones = opcionesBarras("Cantidad de lugares por ciudad",0,10);

        for(Ciudad c:ciudades){

            List<Lugar>lugares = lugarServicio.obtenerLugaresPorCiudad(c.getNombre());
            valores.add(lugares.size());
            labels.add(c.getNombre());
            bgColor.add("rgba(54, 162, 235, 0.2)");
        }

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setData(valores);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barLugaresCiudad.setData(data);

        barLugaresCiudad.setOptions(opciones);
    }

    public void graficarLugaresCalificacion(){

        barLugaresCalificacion = new BarChartModel();
        List<Number>valores = new ArrayList<>();
        List<String>labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        BarChartDataSet barDataSet = new BarChartDataSet();
        ChartData data = new ChartData();
        barDataSet.setLabel("Lugares");

        BarChartOptions opciones = opcionesBarras("Lugares populares e impopulares",0,5);

        List<Lugar>lugares=lugarServicio.listarLugares();

        for(Lugar l:lugares){

            try{
                valores.add(lugarServicio.obtenerCalificacionPromedio(l.getId()));
            }catch (Exception e){
                e.printStackTrace();
            }

            labels.add(l.getNombre());
            bgColor.add("rgba(54, 162, 235, 0.2)");
        }

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setData(valores);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barLugaresCalificacion.setData(data);

        barLugaresCalificacion.setOptions(opciones);
    }
}
