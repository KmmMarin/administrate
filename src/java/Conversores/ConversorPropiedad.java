package Conversores;

import Fidelizacion.controladores.ControladorPropiedad;
import Fidelizacion.entidades.Propiedad;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kavantic S.A.S
 */
@FacesConverter("conversorPropiedad")
public class ConversorPropiedad implements Converter {
    
     /**
     * Devuelve la propiedad como objeto cuando se envia la descripción en texto
     * @param context
     * @param component
     * @param value
     * @return La propiedad que en la base de datos tiene esa descripción
     */
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        // Cargar el controlador de la propiedad
        ControladorPropiedad controlador = (ControladorPropiedad) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "controladorPropiedad");

        // Obtener la fachada y buscarla
        return controlador.getFachadaPropiedad().buscarPorDescripcion(value);
    }

    /**
     * Retorna el nombre de la propiedad si el objeto enviado es una
     * propiedad, si no devuelve la cadena desconocido
     * @param context
     * @param component
     * @param value
     * @return El nombre de la propiedad
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Verifica si es del tipo propiedad y le saca la descripción 
        if (value instanceof Propiedad) {
            Propiedad propiedad = (Propiedad) value;
            return propiedad.getDescripcion();
        }

        return "Desconocido";
    }
}
