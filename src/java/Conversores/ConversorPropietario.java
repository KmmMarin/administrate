package Conversores;

import Fidelizacion.controladores.ControladorPropietario;
import Fidelizacion.entidades.Propietario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Kavantic S.A.S
 */
@FacesConverter("conversorPropietario")
public class ConversorPropietario implements Converter {

    /**
     * Devuelve el propietario como objeto cuando se envia el nombre en texto
     * @param context
     * @param component
     * @param value
     * @return La propietario que en la base de datos tiene ese nombre
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        // Cargar el controlador del propietario
        ControladorPropietario controlador = (ControladorPropietario) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "controladorPropietario");

        // Obtener la fachada y buscarla
        return controlador.getFachadaPropietario().buscarPorNombre(value);
    }

    /**
     * Retorna el nombre del propietario si el objeto enviado es un
     * propietario, si no devuelve la cadena desconocido
     * @param context
     * @param component
     * @param value
     * @return El nombre del propietario
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Verifica si es del tipo propietario y le saca el nombre 
        if (value instanceof Propietario) {
            Propietario propietario = (Propietario) value;
            return propietario.getNombre();
        }

        return "Desconocido";
    }
}

