package bikeproject.api.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "HashMap")
public class BikeErrorReponse {
    @ElementList(entry = "errores",inline = true)
    List<String> errores;
    public BikeErrorReponse(){
        errores = new ArrayList<>();
    }
    public List<String> getErrores() {
        return errores;
    }
    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
